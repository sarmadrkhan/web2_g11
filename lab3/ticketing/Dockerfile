FROM openjdk:17-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]
