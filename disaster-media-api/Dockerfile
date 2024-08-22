FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /usr/src/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/disaster-media-api.jar app.jar
ENTRYPOINT ["java", "-jar", "/usr/src/app/app.jar"]