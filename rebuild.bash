#!/bin/bash
cd ./mc-archive
mvn clean package
cd ../mc-core
mvn clean package
cd ../mc-gateway
mvn clean package
docker-compose build