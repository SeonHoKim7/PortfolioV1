FROM gradle:7.6-jdk17 AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]