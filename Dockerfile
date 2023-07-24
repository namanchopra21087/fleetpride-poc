# Use the desired Java version as the base image
FROM lippert/adoptopenjdk-17
# Set the working directory in the Docker image
WORKDIR /app

# Copy the application files into the Docker image
COPY . /app

# Build the Spring Boot application
#RUN ./mvnw clean package

# Expose the application port
EXPOSE 9090

# Specify the application startup command
CMD ["java", "-jar", "target/fleetpride-poc-0.0.1-SNAPSHOT.jar"]
