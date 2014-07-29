#!/bin/bash

rm currentCoordinate.out
for i in 1 2 3 4 5 6 7; do sh get_current_coordinate.sh
done
./average currentCoordinate.out avCoordinate.out
./ITsTakenLogic avCoordinate.out APRLocations.txt
