FROM node:lts as nodejs
FROM gradle:8.7-jdk17
WORKDIR /app
COPY --from=nodejs / /app/
COPY / /app/
EXPOSE 8080
CMD ["./gradlew", ":composeApp:wasmJsRun"]
