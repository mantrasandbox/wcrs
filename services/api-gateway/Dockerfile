# --- Stage 1: Build the application ---
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Metadata labels for builder (optional)
LABEL org.opencontainers.image.source="https://github.com/mantrasandbox/wcrs" \
      org.opencontainers.image.description="API-Gateway microservice" \
      org.opencontainers.image.licenses="Apache-2.0"

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean install -DskipTests

# --- Stage 2: Run the application ---
FROM openjdk:21-jdk-slim as runner

WORKDIR /app

# Metadata labels for runtime image
LABEL org.opencontainers.image.title="API-Gateway microservice" \
      org.opencontainers.image.description="API-Gateway microservice" \
      org.opencontainers.image.version="1.0.0" \
      org.opencontainers.image.licenses="Apache-2.0" \
      org.opencontainers.image.source="https://github.com/mantrasandbox/wcrs"

# Add non-root user for security
RUN addgroup --system spring && adduser --system --ingroup spring springuser

COPY --from=builder ./app/target/api-gateway-0.0.1-SNAPSHOT.jar ./api-gateway.jar

USER springuser

EXPOSE 4040

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "api-gateway.jar"]