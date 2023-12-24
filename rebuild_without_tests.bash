#!/bin/bash
cd ./mc-archive
mvn clean package -Dmaven.test.skip
cd ../mc-core
mvn clean package -Dmaven.test.skip
cd ../mc-gateway
mvn clean package -Dmaven.test.skip
docker-compose build