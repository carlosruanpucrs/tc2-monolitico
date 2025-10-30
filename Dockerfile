FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21

EXPOSE 8080

COPY --from=build /target/tc2-monolitico-0.0.1-SNAPSHOT.jar tc2-monolitico.jar

ENTRYPOINT ["java", "-jar", "tc2-monolitico.jar"]