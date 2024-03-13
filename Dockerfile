FROM maven:3.9.6-eclipse-temurin-17 as builder
WORKDIR /app
COPY ./ /app/.
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app /app
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app/target/jira-1.0.jar"]
