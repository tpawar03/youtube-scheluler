FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/youtube-scheduler-1.0.0.jar"]