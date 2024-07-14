# Dockerfile

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the compiled Java application JAR file into the container at /app
COPY target/CalcMinCoins-0.0.1-SNAPSHOT.jar /app/CalcMinCoins.jar

# Expose port 3000 (the port your application will run on inside the Docker container)
EXPOSE 3000

# Define the command to run your application when the container starts
CMD ["java", "-jar", "CalcMinCoins.jar"]
