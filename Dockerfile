FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy built jar into container
COPY target/SampleWebApplication.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
