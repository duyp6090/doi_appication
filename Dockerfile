# Generate environmet
FROM openjdk:17-jdk-alpine AS Builder

# Set the working directory inside the container
WORKDIR /app

# Copy file jar to the container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

# Second stage: Create the final image
FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY --from=Builder /app/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]