FROM bellsoft/liberica-openjdk-debian:17

WORKDIR /app

COPY ./target/mc-core-v1.jar ./application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

