FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /app
COPY . /app
ENV dbhost=mahosql
RUN mvn clean package -DskipTests

FROM alpine:3
RUN apk add --no-cache openjdk21
WORKDIR /app
COPY --from=build /app/target/exchangerate-0.0.1.jar /app/myapp.jar
ENV active=prod
EXPOSE 8081
CMD ["java","-Duser.timezone=Asia/Taipei" ,"-jar", "myapp.jar"]
