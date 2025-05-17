FROM gradle:8.10.2-jdk21 AS builder

WORKDIR /com/higeco
COPY --chown=gradle:gradle . .

RUN gradle build -x test --no-daemon

FROM azul/zulu-openjdk:21-latest

WORKDIR /com/higeco
COPY --from=builder /com/higeco/build/libs/fridge-performance-0.0.1-SNAPSHOT.jar /com/higeco/fridge-performance-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/com/higeco/fridge-performance-0.0.1-SNAPSHOT.jar"]
