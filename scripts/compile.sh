#!/bin/sh

cd $(dirname $0)/..
[ -d build ] || mkdir build
javac -d build -cp ".:lib/*" src/element/*.java src/ia/*.java src/sokoban/*.java src/vue/*.java

