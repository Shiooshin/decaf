FROM eclipse-temurin:17-jdk-alpine

RUN addgroup springgroup; adduser --ingroup springgroup --disabled-password springuser
USER springuser

WORKDIR /app

ENV USERNAME=''
ENV USERPASS=''

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

RUN ./mvnw dependency:go-offline

CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.arguments=--spring.security.user.name=${USERNAME} --spring.security.user.password=${USERPASS}"]