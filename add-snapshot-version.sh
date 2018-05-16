#!/usr/bin/env bash


current_pom_version=$1
match_expression='^([0-9]*)\.([0-9]*)\.([0-9]*)-SNAPSHOT$'

new_version=""
if [[ ${current_pom_version} =~ $match_expression ]]; then
    new_version=${current_pom_version}
else
    new_version=${current_pom_version}'-SNAPSHOT'
fi

echo $new_version
