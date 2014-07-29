#!/bin/bash

#$1 = Room Number

n=$(cat $1)

if [ -f left_room ]
then
echo $n
fi