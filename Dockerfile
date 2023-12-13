FROM maven:3.8.3-openjdk-17 as builder


# Copiar Source files...
COPY . /app

WORKDIR /app

RUN mvn clean package -DskipTests

FROM khipu/openjdk17-alpine:latest

WORKDIR /app
COPY --from=builder /app/target/ms-mercadopago-*.jar /app/ms-mercadopago.jar
COPY --from=builder /app/target/classes/siressoftware.com.pfx /app/siressoftware.com.pfx
EXPOSE 80
CMD ["java", "-jar", "/app/ms-mercadopago.jar"]

