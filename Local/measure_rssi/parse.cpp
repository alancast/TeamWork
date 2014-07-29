#include <iostream> 
#include <cstdlib>
#include <fstream>

using namespace std;

int main(int argc, char *argv[]){
	
	if (argc == 1){
		cout << "First argument: Distance from router in feet\n";
		cout << "Second argument: Input file\n";
		cout << "Third argument: router id\n";
		exit(0);

	}
	//	First argument distance from router (2d) in feet
	ofstream out_file;
	out_file.open("output.csv", iostream::app);
	if (!out_file){
		cerr << "Error with opening output file\n";
		exit(1);
	}	

	//	Second argument input file
	ifstream in_file;
	in_file.open(argv[2]);
	if (!in_file){
		cerr << "Error with opening airport file\n";
		exit(1);
	}

	//	Third argument router_id
	out_file << argv[3] << "," << argv[1] << ",";

	string input;
	in_file >> input >> input >> input;
	out_file << input << "\n"; 

}
