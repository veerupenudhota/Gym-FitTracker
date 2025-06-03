# --- Stage 1: Build the application ---
FROM maven:3.9-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy only the pom.xml first to leverage Docker caching.
# If pom.xml doesn't change, Docker can reuse the layers below.
COPY pom.xml .

# Download dependencies specified in pom.xml.
# This ensures that if only source code changes, this step doesn't re-run.
RUN mvn dependency:go-offline

# Copy the entire source code (including your main application class)
COPY src src/

# Build the application.
# `mvn clean package` will compile your code, run tests (skipped here),
# and then use spring-boot-maven-plugin to create the executable JAR.
# The `repackage` goal runs automatically as part of the `package` lifecycle phase.
RUN mvn clean package -DskipTests

# --- Stage 2: Create the final, lean production image ---
# Use a smaller JRE image for the final application, as Maven is no longer needed.
FROM eclipse-temurin:21-jre-alpine

# Set the working directory for the application
WORKDIR /app

# Copy the executable JAR from the 'build' stage into the final image
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot application listens on (default is 8080)
EXPOSE 8080

# Command to run your Spring Boot application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]