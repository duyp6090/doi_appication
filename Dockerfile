# Generate environmet
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy file jar to the container
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]