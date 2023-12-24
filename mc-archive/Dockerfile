FROM bellsoft/liberica-openjdk-debian:17

RUN adduser --system app && addgroup --system app_group && adduser app app_group
USER app

WORKDIR /app

COPY ./target/mc-archive-v1.jar ./application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

