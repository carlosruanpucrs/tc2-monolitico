FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-21-jdk

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

FROM openjdk:21

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/build/libs/*.jar tc2-monolitico.jar

ENTRYPOINT ["java", "-jar", "tc2-monolitico.jar"]