FROM maven:3.9.4-eclipse-temurin-17-focal

WORKDIR /app

COPY src ./src
COPY pom.xml ./
RUN mvn clean install

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]