#!/usr/bin/env bash

sudo docker run -d --name codeurjc-forge-sonarqube --network=ci-network --link codeurjc-forge-sonarqube-postgres:sonarqube-postgres  -p 9000:9000 -p 9092:9092 -e SONARQUBE_JDBC_USERNAME=postgres -e SONARQUBE_JDBC_PASSWORD=mysecretpassword -e SONARQUBE_JDBC_URL=jdbc:postgresql://sonarqube-postgres/sonar sonarqube
