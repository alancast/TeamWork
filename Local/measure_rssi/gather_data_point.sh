#!/bin/bash

# $1 = router_id
# $2 = distance 

airport -s | grep $1 > airport.out
./parse $2 airport.out $1
