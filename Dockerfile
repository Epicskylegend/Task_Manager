# Use an appropriate base image with Java and your desired JDK version
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /usr/src/app

# Copy the application JAR file into the container
COPY build/libs/Task_Manager-1.0-SNAPSHOT.jar .

# Command to run the application when the container starts
CMD ["java", "-jar", "Task_Manager-1.0-SNAPSHOT.jar"]
