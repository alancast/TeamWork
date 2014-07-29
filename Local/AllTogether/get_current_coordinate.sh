#!/bin/bash

airport -s | grep blizzard > airport.out
./parse airport.out rssi.log
./triangulate routersMap.txt currentCoordinate.out rssi.log