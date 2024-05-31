# syntax=docker/dockerfile:1
FROM eclipse-temurin:21-jre-jammy
# Source
WORKDIR biblianime/
ARG JAR_FILE=target/biblianime-api-0.0.1-SNAPSHOT.jar
COPY $JAR_FILE biblianime-api.jar
# Volume
VOLUME /biblianime/data
# Port
EXPOSE 80
#Point d'entré
ENTRYPOINT ["java","-jar","biblianime-api.jar"]