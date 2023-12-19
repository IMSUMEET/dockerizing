FROM maven:3.9.5-eclipse-temurin-21-alpine
WORKDIR /app
ENV QUERY="*5"
COPY pom.xml .
COPY src/ src/
RUN mvn -f pom.xml clean package
ENTRYPOINT java -jar "target/calc-dockerized-app.jar" $QUERY