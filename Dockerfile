# syntax=docker/dockerfile:1
FROM eclipse-temurin:21-jre-jammy
MAINTAINER plopez
WORKDIR ./
COPY target/biblianime-api-0.0.1-SNAPSHOT.jar biblianime-api.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/biblianime-api.jar"]