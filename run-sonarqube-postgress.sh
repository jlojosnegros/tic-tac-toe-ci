#!/usr/bin/env bash

sudo docker run -itd  --name codeurjc-forge-vc-sonarqube-postgres -v /var/lib/postgresql/data ubuntu

sudo docker run --name codeurjc-forge-sonarqube-postgres --network=ci-network --volumes-from codeurjc-forge-vc-sonarqube-postgres -p 5555:5432 -e POSTGRESS_PASSWORD=mysecretpassword -d postgres
