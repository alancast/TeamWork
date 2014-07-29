#!/bin/bash

#First, determine if in an apr
sh run_11.sh > cur_apr;
./did_leave_room cur_apr prev_apr

#!/bin/bash

#$1 = Room Number
p=$(cat prev_apr)

if [ -f left_room ]
then
curl --data "buildingID=SJC-MR3&roomNo=$p&occupied=false" 10.154.240.144:1234/Smart_Room/RoomManagerServlet
fi


n=$(cat cur_apr)
curl --data "buildingID=SJC-MR3&roomNo=$n&occupied=true" 10.154.240.144:1234/Smart_Room/RoomManagerServlet
rm prev_apr
mv cur_apr prev_apr

if [ -f left_room ]
then
rm left_room
fi