FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . .
RUN ./mvnw dependency:go-offline
ENTRYPOINT ["./mvnw","spring-boot:run"]