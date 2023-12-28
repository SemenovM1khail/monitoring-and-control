#!/bin/bash
mvn clean package -Dmaven.test.skip -f ./mc-archive/pom.xml &
mvn clean package -Dmaven.test.skip -f ./mc-core/pom.xml &
mvn clean package -Dmaven.test.skip -f ./mc-gateway/pom.xml
wait
docker compose build