//
//  main.cpp
//  ParseRSSI
//
//  Created by Alex Lancaster on 7/24/14.
//  Perfected by James McNulty
//  Copyright (c) 2014 Alex Lancaster. All rights reserved.
//

/*  
    Utility to parse airport output into blizzard
    RSSI and Router Mac Address Information
    
    Arguments:
        <airport_output_file>
        <output_file_name>
 
 */

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <sstream>

using namespace std;

struct routerInfo
{
	string name;
	string BSSID;
	int RSSI;
	string channel;
	char ht;
	string cc;
	string security;
	friend istream & operator>>(istream & stream, routerInfo & rI);
	void reset();
};


const int num_args = 3;
const int kBuffer_size = 2048;

void parse_file(ifstream & stream, const char outFile[]);
void write_to_output_file(const routerInfo & rI, const char outFile[]);

int main(int argc, char * argv[])
{
	ofstream outfile;
	outfile.open(argv[2]);
	outfile.close();
	if (argc < num_args){
		cout << "Not enough args!\n";
		exit(1);
	}
	//	Open the input file and output file
	ifstream inFile;
	inFile.open(argv[1]);
	if (!inFile){
		cout << "Error with input/output files\n";
		exit(2);
	}
	//Eat the column headers
	char buffer[kBuffer_size];
	inFile.getline(buffer, kBuffer_size - 1);
    
	parse_file(inFile, argv[2]);
	
	
}

//	Functions in struct
//================================================

istream & operator>>(istream & stream, routerInfo & temp){
	stream >> temp.BSSID >> temp.RSSI >> temp.channel >> temp.ht >> temp.cc >> temp.security;
	return stream;
}

void routerInfo::reset(){
	name = "";
	BSSID = "";
	RSSI = -1;
	channel = "";
	ht = '\0';
	cc = "";
	security = "";
    
}

void parse_file(ifstream & inFile, const char filename[]){
	routerInfo temp;
	while (inFile) {
		// First, we check the internet's name
		string name;
		inFile >> name;
		//	 If it's not a blizzard router
		if (name != "blizzard"){
			inFile.ignore(kBuffer_size, '\n');
			continue;
		}
		//	Now, it definitely is, write to it
		inFile >> temp;
		temp.name = name;
		write_to_output_file(temp, filename);
	}
}

void write_to_output_file(const routerInfo & rI, const char filename[]){
	ofstream outfile;
	outfile.open(filename, std::iostream::app);
	if (!outfile){
		cout << "Error opening output file\n";
	}
    
	outfile << rI.BSSID << " " << rI.RSSI << "\n";
    
}