//
//  main.cpp
//  ITsTakenAverage
//
//  Created by Alex Lancaster on 7/25/14.
//  Copyright (c) 2014 Alex Lancaster. All rights reserved.
//

#include <iostream>
#include <fstream>
#include <vector>

/*	Arguments:
 <input_file>
 <output_file>
 */

using namespace std;

struct Point{
	double x;
	double y;
	friend istream & operator>>(istream & stream, Point & point);
};

Point average(const vector<Point> & points);

int main(int argc, char* argv[]){
	//	Open the input file
	ifstream in_file;
	in_file.open(argv[1]);
	if (!in_file){
		exit(1);
	}
	
	//	Read data into array of points
	vector<Point> points;
	while (in_file){
		Point p;
		in_file >> p;
		points.push_back(p);
	}
	Point p = average(points);
	//	Open the output file
	ofstream out_file;
	out_file.open(argv[2]);
	if (!out_file){
		exit(2);
	}
	out_file << p.x << " " << p.y << "\n";
	
}

istream & operator>>(istream & stream, Point & point){
	stream >> point.x >> point.y;
	return stream;
}

Point average(const vector<Point> & points){
	double sumx, sumy;
	for (size_t i = 0; i < points.size(); ++i){
		sumx += points[i].x;
		sumy += points[i].y;
	}
	Point returner;
	returner.x = sumx / points.size();
	returner.y = sumy / points.size();
	return returner;
}
