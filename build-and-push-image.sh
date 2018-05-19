#!/usr/bin/env bash

tag=$1
docker build --build-arg GIT_COMMIT=$(git rev-parse HEAD) --build-arg COMMIT_DATE=$(git log -1 --format=%cd --date=format:%Y-%m-%dT%H:%M:%S) -f Dockerfile_2 -t practicacimasterupm/curso-ci-practica:${tag} .

 docker push practicacimasterupm/curso-ci-practica:${tag}
