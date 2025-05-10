# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# -------- RUN STAGE --------
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy the built JAR file
COPY target/Gym-FitTracker-0.0.1-SNAPSHOT.jar /app/Gym-FitTracker.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/Gym-FitTracker.jar"]
