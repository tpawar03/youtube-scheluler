# Use a base image with both JDK and Maven preinstalled
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

# Build the application without running tests
RUN mvn clean package -DskipTests

# Use a slim runtime image for smaller size
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy only the built JAR from the build stage
COPY --from=build /app/target/youtube-scheduler-0.0.1-SNAPSHOT.jar app.jar

# Expose the Spring Boot port
EXPOSE 8080

# Start the app
ENTRYPOINT ["java", "-jar", "app.jar"]