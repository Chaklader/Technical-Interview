#ifndef __myArray_h__
#define __myArray_h__

#include <string>

using std::string;



class myArray{

	public:	  

		int run();
		string result(bool value);


		/*question 1-1 : design an algorithm to determine 
    	if a string has all the unique characters*/
	    
	    bool isUniqueChars(const string& str);
	    bool isUniqueChars2(const string& str);
	    /*END of solution 1-1 : design an algorithm to 
    	determine if a string has all the unique characters*/



	    /*question 1-2 : design an 
    	algorithm to reverse a String*/
	    void reverse(char* str);
	    /*END of solution 1-2 : design an 
    	algorithm to reverse a String*/




	    /*question 1-3: design an algorithm to 
    	check whether two strings are anagram*/
	    bool permutation(const string& a, const string& b);
	    bool permutation1(const string& a, const string& b);
	    /*END of solution 1-3: design an algorithm to 
    	check whether two strings are anagram*/



	    /*question 1-4 : design an algorithm to 
    	replace all spaces in a string with ‘%20’*/
	    void replaceSpaces(std::unique_ptr<char[]>&, int length);
	    /*END of solution 1-4 : design an algorithm to 
    	replace all spaces in a string with ‘%20’*/


	    // unused methods 
	    // --------------
	    //int main(int, char const**);
	    // static int main(int, char *[]);





	    /*question 1-5 : design an algorithm to 
    	compress string with the character numbers*/   
	    int stringToInt(const string& value);
	    string intToString(int value);
	    int countCompression(const string& str);

	    /// C++ std::string is efficient and no need to use a "StringBuffer"-like structure.
	    string compressBetter(const string& str);
	    /*END of soluton 1-5 : design an algorithm to 
    	compress string with the character numbers*/




	    /*question 1-6 : design an algorithm  
	    to rotate an image represented in N x N  
	    matrix by 90 degrees*/
	    void rotate(int* matrix, int n);
    	void printMatrix(int* matrix, int m, int n);
    	/*END of solution 1-6 : design an algorithm  
	    to rotate an image represented in N x N  
	    matrix by 90 degrees*/




    	/*question 1-7 design an algorithm such 
	    that if an element in an MxN matrix is 0, 
	    its entire row and column is zero*/
    	void setZeros(int* matrix, int m, int n);
    	/*END of solution 1-7 design an algorithm such 
	    that if an element in an MxN matrix is 0, 
	    its entire row and column is zero*/




    	/*question 1-8 : design an algorithm to check for 
    	two strings, s1 and s2 if s2 is a rotation of s1*/
    	
    	bool isRotation(const string& s1, const string& s2);
    	/*END of solution 1-8 : design an algorithm to check for 
    	two strings, s1 and s2 if s2 is a rotation of s1*/
}; 

#endif // __myArray_h__