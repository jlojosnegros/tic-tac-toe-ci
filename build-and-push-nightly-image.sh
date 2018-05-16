#!/usr/bin/env bash

#current_pom_version=`mvn -q -Dexec.executable="echo" -Dexec.args='${project.version}' --non-recursive org.codehaus.mojo:exec-maven-plugin:1.3.1:exec`
#echo "input="$1
current_pom_version=$1
date=`date +%Y%m%d`
string='nightly'

#echo "current_pom_version=$current_pom_version"
#echo "date=$date"

match_expression='^([0-9]*)\.([0-9]*)\.([0-9]*)-(.*)$'

tag=""
if [[ ${current_pom_version} =~ $match_expression ]]; then
    tag=${BASH_REMATCH[1]}\.${BASH_REMATCH[2]}\.${BASH_REMATCH[3]}\.${string}\.${date}
fi

echo $tag

# docker build --build-arg GIT_COMMIT=$(git rev-parse HEAD) --build-arg COMMIT_DATE=$(git log -1 --format=%cd --date=format:%Y-%m-%dT%H:%M:%S) -f Dockerfile_Nightly -t practicacimasterupm/curso-ci-practica:${tag} .

# docker push practicacimasterupm/curso-ci-practica:${tag}
