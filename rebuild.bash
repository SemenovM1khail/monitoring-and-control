#!/bin/bash
mvn clean package -f ./mc-archive/pom.xml &
mvn clean package -f ./mc-core/pom.xml &
mvn clean package -f ./mc-gateway/pom.xml
wait
docker compose build