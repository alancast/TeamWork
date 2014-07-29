#include <iostream>
#include <fstream>
#include <string>

using namespace std;

//	Arguments:
//		<current_room_file>
//		<previous_room_file>

int main(int argc, char* argv[]){
	ifstream cur_file;
	cur_file.open(argv[1]);
	if (!cur_file){
		exit(1);
	}
	ifstream prev_file;
	prev_file.open(argv[2]);
	if (!prev_file){
		exit(2);
	}
	string prev, cur;
    cur_file >> cur;
    prev_file >> prev;
	if (prev != cur){
		ofstream left_file;
		left_file.open("left_room");
		if (!left_file){
			exit(3);
		}
		left_file << "false";
	}
}
