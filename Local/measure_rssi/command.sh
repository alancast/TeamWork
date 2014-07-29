#!/bin/bash

#$1 = router id
#$2 = distance in feet

for i in 1 2 3 4 5 6 7 8 9 10 11 12; do sh gather_data_point.sh $1 $2; done
