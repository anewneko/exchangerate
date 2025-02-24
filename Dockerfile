FROM maven:3.8.6-openjdk-21 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM alpine:3
RUN apk add --no-cache openjdk21
WORKDIR /app
COPY ./target/exchangerate-1.0.0.jar /app/myapp.jar
ENV active=prod
EXPOSE 8080
CMD ["java", "-jar", "myapp.jar"]