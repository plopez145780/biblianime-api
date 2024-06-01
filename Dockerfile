# syntax=docker/dockerfile:1

################################## DEPENDANCES #################################

# Créer une étape pour la résolution et le téléchargement des dépendances.
FROM eclipse-temurin:21-jdk-jammy as deps

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
FROM deps as package

WORKDIR /build

COPY ./src src/
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw package -DskipTests && \
    mv target/$(./mvnw help:evaluate -Dexpression=project.artifactId -q -DforceStdout)-$(./mvnw help:evaluate -Dexpression=project.version -q -DforceStdout).jar target/app.jar

################################## EXTRACTION ##################################

# Créer une étape pour extraire l'application en couches séparées.
# Profitez des outils de couche de Spring Boot et de la mise en cache de Docker en extrayant l'application packagée
# en couches séparées qui peuvent être copiées dans l'étape finale. Voir la documentation de Spring pour référence :
# https://docs.spring.io/spring-boot/docs/current/reference/html/container-images.html
FROM package as extract

WORKDIR /build

RUN java -Djarmode=layertools -jar target/app.jar extract --destination target/extracted

##################################### FINAL ####################################

# Créer une nouvelle étape pour l'exécution de l'application qui contient les dépendances minimales d'exécution de l'application.
# Cette étape utilise souvent une image de base différente de l'étape d'installation
# ou de construction où les fichiers nécessaires sont copiés à partir de l'étape d'installation.
#
# L'exemple ci-dessous utilise l'image JRE d'eclipse-turmin comme base pour l'exécution de l'application.
# En spécifiant la balise « 21-jre-jammy », il utilisera également la version la plus récente de cette balise
# lorsque vous construirez votre fichier Docker.
# Si la reproductibilité est importante, envisagez d'utiliser une SHA digest spécifique, comme
# eclipse-temurin@sha256:99cede493dfd88720b610eb8077c8688d3cca50003d76d1d539b0efc8cca72b4.
FROM eclipse-temurin:21-jre-jammy AS final

# Créer un utilisateur non privilégié sous lequel l'application s'exécutera.
# https://docs.docker.com/go/dockerfile-user-best-practices/
#ARG UID=10001
#RUN adduser \
#    --disabled-password \
#    --gecos "" \
#    --home "/nonexistent" \
#--shell "/sbin/nologin" \
#    --no-create-home \
#    --uid "${UID}" \
#    appuser
#USER appuser

# Copier l'exécutable à partir de l'étape « package ».
COPY --from=extract build/target/extracted/dependencies/ ./
COPY --from=extract build/target/extracted/spring-boot-loader/ ./
COPY --from=extract build/target/extracted/snapshot-dependencies/ ./
COPY --from=extract build/target/extracted/application/ ./

EXPOSE 80

VOLUME /data

ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]
