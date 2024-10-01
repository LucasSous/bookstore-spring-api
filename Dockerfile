FROM maven:3.8.4-openjdk-17-slim AS build

COPY pom.xml ./
COPY .mvn .mvn
COPY src src

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]