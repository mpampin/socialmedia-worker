FROM maven:3.5.3-jdk-8-alpine

RUN mkdir /app
WORKDIR /app

COPY src/ ./src/
COPY pom.xml .
COPY twitter4j.properties .

RUN mvn clean install

EXPOSE 8080

CMD java -jar target/socialmedia-0.0.1-SNAPSHOT.jar
