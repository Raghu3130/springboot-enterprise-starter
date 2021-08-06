# Base Image
FROM openjdk:11

ARG REVISION

EXPOSE 5000:8080

COPY ./target/springstarter-${REVISION}-SNAPSHOT.jar /usr/src/myapp/app.jar
WORKDIR /usr/src/myapp

CMD [ "java", "-jar", "app.jar" ]

