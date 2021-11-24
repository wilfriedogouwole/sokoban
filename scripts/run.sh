#!/bin/sh

cd $(dirname $0)/..
sh scripts/compile.sh
java -cp "build:lib/*" sokoban.Main $*

