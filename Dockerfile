# syntax=docker/dockerfile:1

################################## DEPENDANCES #################################

# Créer une étape pour la résolution et le téléchargement des dépendances.

FROM eclipse-temurin:21-jdk-alpine AS dependances

WORKDIR /build

# Copier le wrapper mvnw avec les permissions d'exécution.
# /!\ Vérifier encodage en LF de mvnw (depuis Windows)
COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/

# Télécharger les dépendances dans une étape séparée pour profiter de la mise en cache de Docker.
# Tirer parti d'un montage de cache sur /root/.m2 pour que les constructions suivantes n'aient pas à retélécharger les paquets.
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 ./mvnw dependency:go-offline -DskipTests


#################################### PACKAGE ###################################

# Créez une étape pour construire l'application basée sur l'étape avec les dépendances téléchargées.
# Ce fichier Docker est optimisé pour les applications Java qui produisent un uber jar,
# qui inclut toutes les dépendances nécessaires pour exécuter votre application dans une JVM.
# Si votre application ne produit pas d'uber jar et s'appuie plutôt sur un serveur d'application comme Apache Tomcat,
# vous devrez mettre à jour cette étape avec le nom de fichier correct de votre paquetage
# et mettre à jour l'image de base de l'étape « final » en utilisant le serveur d'application approprié,
# par exemple en utilisant tomcat (https://hub.docker.com/_/tomcat/) comme image de base.

FROM dependances AS package

WORKDIR /build

COPY ./src src/

RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    mv target/$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout).jar target/app.jar


################################## JDEPS ##################################

# Créez une étape pour analyser et enregistrer les dépendances de classe Java de l'application jar,
# basée sur l'étape de construction du package.
# jdeps est un outil d'analyse des dépendances de classe (https://docs.oracle.com/en/java/javase/11/tools/jdeps.html)
# La liste des dépendances de classes Java de l'application est enregistré dans un fichier nommée 'modules.txt'

FROM package AS jdeps

WORKDIR /build

RUN jdeps --ignore-missing-deps -q \
    --recursive \
    --multi-release 21 \
    --print-module-deps \
    --class-path 'BOOT-INF/lib/*' \
    target/app.jar > modules.txt


################################## EXTRACTION ##################################

# Créer une étape pour extraire l'application en couches séparées.
# Profitez des outils de couche de Spring Boot et de la mise en cache de Docker en extrayant l'application packagée
# en couches séparées qui peuvent être copiées dans l'étape finale. Voir la documentation de Spring pour référence :
# https://docs.spring.io/spring-boot/docs/current/reference/html/container-images.html

FROM package AS extract

WORKDIR /build

RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted


################################## CUSTOM JRE ##################################

# Créez une étape pour construire un Java Runtime Environment (JRE) personnalisé et allégé
# jlink est l'outil java pour assembler et optimiser un ensemble de modules et leurs dépendances
# dans une image d'exécution personnalisée. (https://docs.oracle.com/en/java/javase/11/tools/jlink.html)

FROM eclipse-temurin:21-jdk-alpine AS jre-builder

COPY --from=jdeps build/modules.txt ./modules.txt

RUN apk update &&  \
    apk add binutils

RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules $(cat modules.txt) \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /optimized-jre-21


##################################### FINAL ####################################

# Créer une nouvelle étape pour l'exécution de l'application qui contient les dépendances minimales d'exécution de l'application.
# Cette étape utilise souvent une image de base différente de l'étape d'installation
# ou de construction où les fichiers nécessaires sont copiés à partir de l'étape d'installation.
#
# Si la reproductibilité est importante, envisagez d'utiliser une SHA digest spécifique, comme
# eclipse-temurin@sha256:99cede493dfd88720b610eb8077c8688d3cca50003d76d1d539b0efc8cca72b4.

FROM alpine:3 AS final

ENV JAVA_HOME=/opt/jre/jre-21
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Copier le JRE personalisé dans le dossier de java
COPY --from=jre-builder /optimized-jre-21 $JAVA_HOME

# Créer un utilisateur non privilégié sous lequel l'application s'exécutera.
# https://docs.docker.com/go/dockerfile-user-best-practices/
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

USER appuser

# Copier l'exécutable à partir de l'étape « package ».
COPY --from=extract build/target/extracted/dependencies/ ./
COPY --from=extract build/target/extracted/spring-boot-loader/ ./
COPY --from=extract build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract build/target/extracted/application/ ./

EXPOSE 8080

ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]
