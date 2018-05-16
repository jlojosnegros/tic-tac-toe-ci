#!/usr/bin/env bash


current_pom_version=$1
match_expression='^([0-9]*)\.([0-9]*)\.([0-9]*)-SNAPSHOT$'

release_version=""
if [[ ${current_pom_version} =~ $match_expression ]]; then
    release_version=${BASH_REMATCH[1]}\.${BASH_REMATCH[2]}\.${BASH_REMATCH[3]}
fi

echo $release_version

