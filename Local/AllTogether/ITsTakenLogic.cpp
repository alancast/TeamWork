//
//  main.cpp
//  ITsTakenLogic
//
//  Created by Alex Lancaster on 7/25/14.
//  Copyright (c) 2014 Alex Lancaster. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <sstream>

using namespace std;

/*
    arguments 
        <input coordinates>
        <APR locations>
 
 */

const int num_args = 3;

int main(int argc, char * argv[])
{
	//	Open the input file and output file
	ifstream inFile;
	inFile.open(argv[1]);
	if (!inFile){
		cout << "Error with input file\n";
		exit(1);
	}
    ifstream APRInFile;
	APRInFile.open(argv[2]);
	if (!APRInFile){
		cout << "Error with input file\n";
		exit(2);
	}
	double x, y;
    inFile>>x>>y;
    double APRLowX, APRHighX, APRLowY, APRHighY;
    string APRNum;
    while(APRInFile)
    {
        APRInFile>>APRNum>>APRLowX>>APRHighX>>APRLowY>>APRHighY;
        if(!APRInFile)
        {
            break;
        }
        if(x>APRLowX && x<APRHighX && y>APRLowY && y<APRHighY)
        {
            cout<<APRNum<<endl;
            //post to the server here
            return 0;
        }
    }
    APRNum = "-1";
    cout<<APRNum<<endl;
}

