FROM node:lts AS nodejs
FROM gradle:8.7-jdk17
WORKDIR /app
COPY --from=nodejs . .
COPY . .
EXPOSE 8080
CMD ["./gradlew", ":composeApp:wasmJsRun"]
