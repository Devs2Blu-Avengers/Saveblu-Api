# --- Build Stage ---
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in Docker
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .



# Download all required dependencies in advance (this will be cached until the pom.xml changes)
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src src/

# VARIAVEIS DE AMBIENTE
ARG SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL
ARG SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME
ARG SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD


# Build the application
RUN mvn -U clean install package

# --- Run Stage ---
FROM openjdk:17-slim

# Copy the built war file from the build stage
COPY --from=build /app/target/saveblu-api-0.0.1.war saveblu-api-0.0.1.jar

# Set the port the app listens on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "saveblu-api-0.0.1.jar"]