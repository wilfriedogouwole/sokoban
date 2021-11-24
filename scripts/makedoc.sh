#!/bin/sh

cd $(dirname $0)/..
sh scripts/compile.sh
[ -d doc ] || mkdir doc
javadoc "doc" src/ia/*.java src/sokoban/*.java src/elements/*.java  src/vue/*.java
