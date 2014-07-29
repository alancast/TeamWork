//
//  main.cpp
//  TriangulateRSSI
//
//  Created by Alex Lancaster on 7/24/14.
//  Copyright (c) 2014 Alex Lancaster. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <sstream>
#include <map>
#include <unordered_map>
#include <queue>
#include <algorithm>

const double gridScale = 7.5;

using namespace std;

struct router
{
    string BSSID;
    int x;
    int y;
    friend istream & operator>>(istream & stream, router & temp);
};

struct RSSI
{
    string BSSID;
    int power;
    friend istream & operator>>(istream & stream, RSSI & temp);
    friend bool operator< (const RSSI &one, const RSSI &two);
};

const int num_args = 4;

int main(int argc, const char * argv[])
{
    unordered_map<string, router> routerMap;
    //priority_queue<RSSI> RSSIqueue;
    vector<RSSI> RSSIVector;
    ofstream outfile;
    outfile.open(argv[2], iostream::app);
    if (argc < num_args){
        cout << "Not enough args!\n";
        exit(1);
    }
    // Open the input file
    ifstream inFile;
    inFile.open(argv[1]);
    if (!inFile){
        cout << "Error with input file\n";
        exit(2);
    }
    
    router temp;
    while (inFile)
    {
        inFile >> temp;
        routerMap[temp.BSSID] = temp;
    }
    // Open the RSSI input file
    ifstream RSSIInFile;
    RSSIInFile.open(argv[3]);
    if (!RSSIInFile){
        cout << "Error with input file\n";
        exit(3);
    }
    //Read RSSI information from RSSIInFile
    RSSI tempRSSI;
    while (RSSIInFile)
    {
        RSSIInFile >> tempRSSI;
        if(!RSSIInFile)
        {
            break;
        }
        if(routerMap.find(tempRSSI.BSSID) != routerMap.end())
        {
            RSSIVector.push_back(tempRSSI);
            sort(RSSIVector.begin(), RSSIVector.end());
        }
    }
    //calculate distance to router
    double possibleXPlus, possibleXMinus, x2;
    double possibleYPlus, possibleYMinus, y2;
    for(int i=0; i<1; i++)
    {
        //router 1 info
        double xCoordinate;
        double yCoordinate;
        int power;
        double distance;
        RSSI powerRSSI = RSSIVector[i];
        xCoordinate = routerMap[powerRSSI.BSSID].x*gridScale;
        yCoordinate = routerMap[powerRSSI.BSSID].y*gridScale;
        power = powerRSSI.power;
        //distance = (-.7*power)-44;
        distance = ((power+44)/-.7);
        if(distance<0)
        {
            distance=0;
        }
        //router 2 info
        double xCoordinate2;
        double yCoordinate2;
        int power2;
        double distance2;
        RSSI powerRSSI2 = RSSIVector[i+1];
        xCoordinate2 = routerMap[powerRSSI2.BSSID].x*gridScale;
        yCoordinate2 = routerMap[powerRSSI2.BSSID].y*gridScale;
        power2 = powerRSSI2.power;
        //distance2 = (-.7*power2)-44;
        distance2 = ((power2+44)/-.7);
        if(distance2<0)
        {
            distance2=0;
        }
        //distance between routers
        double routerDistance = sqrt(pow(xCoordinate-xCoordinate2,2) + pow(yCoordinate - yCoordinate2, 2));
        double a = (pow(distance,2) - pow(distance2, 2) + pow(routerDistance,2))/(2*routerDistance);
        double h = sqrt(abs(pow(distance, 2) - pow(routerDistance, 2)));
        x2 = xCoordinate + (a*((xCoordinate2-xCoordinate)/routerDistance));
        y2 = yCoordinate + (a*((yCoordinate2-yCoordinate)/routerDistance));
        /*
        possibleXPlus = x2 + (h*((yCoordinate2-yCoordinate)/routerDistance));
        possibleXMinus = x2 - (h*((yCoordinate2-yCoordinate)/routerDistance));
        possibleYPlus = y2 + (h*((xCoordinate2-xCoordinate)/routerDistance));
        possibleYMinus = y2 - (h*((xCoordinate2-xCoordinate)/routerDistance));
         */
        
        /*
        //Let the centers be: (a,b), (c,d)
        //Let the radii be: r, s
        
        double e = abs(xCoordinate2 - xCoordinate);                         //[difference in x coordinates]
        double f = abs(yCoordinate2 - yCoordinate);                         //[difference in y coordinates]
        double p = sqrt(pow(e, 2) + pow(f, 2));                                 //[distance between centers]
        double k = abs((pow(p, 2) + pow(distance, 2) - pow(distance2, 2))/(2*p));         //[distance from center 1 to line joining points of intersection]
        possibleXPlus = xCoordinate + ((e*k)/p) + ((f/p)*sqrt(pow(distance, 2) - pow(k, 2)));
        possibleYMinus = yCoordinate + ((f*k)/p) - ((e/p)*sqrt(pow(distance, 2) - pow(k, 2)));
        possibleXMinus = xCoordinate + ((e*k)/p) - ((f/p)*sqrt(pow(distance, 2) - pow(k, 2)));
        possibleYPlus = yCoordinate + ((f*k)/p) + ((e/p)*sqrt(pow(distance, 2) - pow(k, 2)));
         */
    }
    //outfile<<"(X Plus Y Minus) X: "<<possibleXPlus<<" Y: "<<possibleYMinus<<endl;
    //outfile<<"(X Minus Y Plus) X: "<<possibleXMinus<<" Y: "<<possibleYPlus<<endl;
    //outfile<< possibleXPlus << " " << possibleYMinus<<endl;
    //outfile<< possibleXMinus << " " << possibleYPlus<<endl;
    outfile<<x2<<" "<<y2<<endl;
    return 0;
}

istream & operator>>(istream & stream, router & temp)
{
    stream >> temp.BSSID >> temp.x >> temp.y;
    return stream;
}

istream & operator>>(istream & stream, RSSI & temp)
{
    stream >> temp.BSSID >> temp.power;
    return stream;
}

bool operator< (const RSSI &one, const RSSI &two)
{
    return one.power > two.power;
}

//output "x y"




