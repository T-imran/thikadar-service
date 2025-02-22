# First stage: Build the JAR file using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Second stage: Run the JAR file with OpenJDK
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/task-0.0.1-SNAPSHOT.jar task.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "task.jar"]

