#!/usr/bin/env bash


current_pom_version=$1
date=`date +%Y%m%d`
string='nightly'

match_expression='^([0-9]*)\.([0-9]*)\.([0-9]*)-(.*)$'

tag=""
if [[ ${current_pom_version} =~ $match_expression ]]; then
    tag=${BASH_REMATCH[1]}\.${BASH_REMATCH[2]}\.${BASH_REMATCH[3]}\.${string}\.${date}
fi

echo $tag

