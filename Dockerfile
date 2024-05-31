# syntax=docker/dockerfile:1
FROM eclipse-temurin:21-jre-jammy
# Source
WORKDIR biblianime/
ARG JAR_FILE=target/biblianime-api-0.0.1-SNAPSHOT.jar
COPY $JAR_FILE biblianime-api.jar
COPY data/biblianimedb.mv.db data/biblianimedb.mv.db
# User non root
ARG UID=1000
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser
USER appuser

# Volume
VOLUME /biblianime/data
# Port
EXPOSE 80
#Point d'entré
ENTRYPOINT ["java","-jar","biblianime-api.jar"]
