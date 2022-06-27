FROM gradle:latest as builder
WORKDIR /src
COPY . .
RUN gradle bootJar

FROM openjdk:11-jre-slim
COPY --from=builder /src/build/libs/*.jar /app/app.jar
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]