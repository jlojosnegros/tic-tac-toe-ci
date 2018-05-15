#!/bin/bash

docker build --build-arg GIT_COMMIT=$(git rev-parse HEAD) --build-arg COMMIT_DATE=$(git log -1 --format=%cd --date=format:%Y-%m-%dT%H:%M:%S) -f Dockerfile_Merge -t practicacimasterupm/curso-ci-practica:dev .
