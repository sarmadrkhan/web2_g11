# Use the official Gradle image as the build environment
FROM gradle:jdk17  AS build

# Copy the source code to the build container
COPY . /home/gradle/src

# Set the working directory to the project root
WORKDIR /home/gradle/src

# Build the app
RUN gradle clean build --no-daemon -x test

# Use a lightweight JDK 17 image as the runtime environment
FROM openjdk:19-jdk-alpine

# Set the working directory to the app directory
WORKDIR /app

# Copy the app jar from the build container
COPY --from=build /home/gradle/src/build/libs/*.jar ./app.jar

# Expose port 8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
