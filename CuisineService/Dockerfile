# Build Stage
FROM openjdk:17-oracle as buildstage
WORKDIR /app

# Copy Maven wrapper and source files
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Build the application
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests

# Runtime Stage
FROM openjdk:17-oracle
WORKDIR /app
RUN mkdir -p /app/resources

# Copy the built jar from the build stage
COPY --from=buildstage /app/target/*.jar cuisine_service.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "cuisine_service.jar"]
