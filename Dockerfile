FROM openjdk:21
WORKDIR /app
ADD ./build/libs/tc2-monolitico-0.0.1-SNAPSHOT.jar tc2-monolitico.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tc2-monolitico.jar"]