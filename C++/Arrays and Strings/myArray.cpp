/*Complie from the Terminal
-------------------------
1. 	g++ myArray.cpp -o myArray.out
2. 	./myArray.out

put without mentioning the file name
------------------------------------
1.   g++ myArray.cpp
2.   ./a.out*/


#include <iostream>
#include <string>
#include <sstream>

#include "myArray.h"

using namespace std;

using std::cout;
using std::endl;


// ===============================================
/*int myArray::main(int argc, char const *argv[]){

    std::cout<< "Hello World\n";
    return 0;
}*/

/*int myArray::main(int argc, char const *argv[]){

    std::cout<< "Hello World\n";
    return 0;
}*/
// ===============================================




/*question 1-1 : design an algorithm to determine 
if a string has all the unique characters*/


/*solution-a*/
bool myArray::isUniqueChars(const string& str){

    if (str.length() > 256) 
    {
        return false;
    }

    unsigned int checker = 0;
    
    for (int i = 0; i < str.length(); ++i) 
    {
        int value = str[i] - 'a';

        if ((checker & (1 << value)) != 0)
        {
            return false;
        }

        checker |= (1 << value);
    }
    
    return true;
}
/* END of solution-a*/


/*solution-b*/
bool myArray::isUniqueChars2(const string& str) {

    if (str.length() > 256) {
        return false;
    }

    bool ascii_set[256] = { false };

    for (int i = 0; i < str.length(); ++i) {

        int value = str[i];

        if (ascii_set[value]) {

            return false;
        }

        ascii_set[value] = true;    
    }

    return true;
}
/* END of solution-b*/

/* END of solution 1-1 : design an algorithm to determine 
if a string has all the unique characters*/





/*question 1-2 : design an algorithm to 
reverse a String*/

/*solution-a*/
void myArray::reverse(char* str) {

    // get the first character of the string 
    char *ptrEnd = str;
    char temp;

    if (str){

        while (*ptrEnd) {
            ptrEnd++;
        }

        ptrEnd--;

        // as long the first adddress is lesser than the end 
        while (str < ptrEnd) {

            temp = *str;
            *str++ = *ptrEnd;            
            *ptrEnd-- = temp;
        }
    }
}
/*END of solution-a*/


/*solution-b*/
void myArray::reverse1(){

    vector<string> values = {"miami", "seattle", "berlin"};

    for_each(values.begin(), values.end(), [](auto& s) { 
        reverse(s.begin(), s.end()); 
    });
   
    for (auto& v : values) {
        cout << v << "\n";
    } 
}
/*END of solution-b*/


/*END of solution 1-2 : design an 
algorithm to reverse a String*/









/*question 1-3: design an algorithm to 
check whether two strings are anagram*/
// ANAGRAM: creating a new word by rearranging the 
// same letters of the previous word exactly once 


/*solution-a*/
bool myArray::permutation(const string& a, const string& b) {

    // Create a copy of the two strings, we do not wish to modify those that were passed as parameters.
    string str_a(a);
    string str_b(b);

    if (str_a.length() != str_b.length()){
        return false;
    }

    sort(str_a.begin(), str_a.end());
    sort(str_b.begin(), str_b.end());
    
    for (int i = 0; i < str_a.length(); i++) {

        if(str_a[i] != str_b[i]){
            return false;
        }
    }

    return true;
}
/*END of solution-a*/



/*solution-b*/
bool myArray::permutation1(const string& a, const string& b){
    
    if (a.length() != b.length()){
        return false;
    }

    int ascii_set[256] = {0};

    for (int i = 0; i < a.length(); i++) 
    {
        int val = static_cast<int>(a[i]);
        ascii_set[val]++;
    }

    for (int i = 0; i < b.length(); i++) 
    {
        int val = static_cast<int>(b[i]);

        if ((--ascii_set[val]) < 0) 
        {
            return false;
        }
    }

    return true;
}
/*END of solution-b*/

/*END of solution 1-3: design an algorithm to 
check whether two strings are anagram*/





/*question 1-4 : design an algorithm to 
replace all spaces in a string with ‘%20’*/

void myArray::replaceSpaces(unique_ptr<char[]> &str, int length) //char str[]
{
    int newLength, spaceCount = 0;

    //count the number of spaces in the given string.
    for (int i = 0; i < length; i++) 
    {
        if (str[i] == ' ') 
        {
            spaceCount++;
        }
    }

    //calculate new string size.
    newLength = length + spaceCount * 2;
    str[newLength] = '\0';

    //copying the characters backwards and inserting %20
    for (int i = length - 1; i >= 0; i--) {        

        if (str[i] == ' ') 
        {
            str[newLength - 1] = '0';
            str[newLength - 2] = '2';
            str[newLength - 3] = '%';
            newLength -= 3;
        } 

        else
        {
            str[newLength - 1] = str[i];
            newLength--;
        }
    }
}
/*END of solution 1-4: design an algorithm to 
replace all spaces in a string with ‘%20’*/







/*question 1-5 : design an algorithm to 
compress string with the character and their 
counts*/
int myArray::stringToInt(const string& value){

    int temp;
    stringstream(value) >> temp;

    return temp;
}


string myArray::intToString(int value) {

    string temp;
    stringstream convert;
    convert << value;
    temp = convert.str();

    return temp;
}


string myArray::compressBetter(const string& str) {

    int size = countCompression(str);

    if(size >= str.length()) 
    {
        return str;
    }

    string newstr;

    char last = str.at(0);
    int count = 1;

    for (int i = 1; i < str.length(); i++){

        if (str.at(i) == last) {
            count++;
        }

        else {
            newstr += last;
            newstr.append(intToString(count));
            last = str.at(i);
            count = 1;
        }
    }

    newstr += last;
    newstr.append(intToString(count));

    return newstr;
}

int myArray::countCompression(const string& str){

    if (str.length() == 0){
        return 0;
    }

    char last = str.at(0);

    int size = 0;
    int count = 1;

    for (int i = 1; i < str.length(); i++){

        if (str.at(i) == last){
            count++;
        }

        else{
            
            last = str.at(i);
            size = size + 1 + intToString(count).length();
            count = 1;
        }
    }

    size = size + 1 + intToString(count).length();

    return size;
}
/*END of solution 1-5 : design an algorithm to 
compress string with the character and their counts*/





/*question 1-6 : design an algorithm  to rotate an 
image represented in N x N  matrix by 90 degrees*/
void myArray::rotate(int* matrix, int n){

    for (int layer = 0; layer < n / 2; ++layer){

        int first = layer;
        int last = n - 1 - layer;
    
        for (int i = first; i < last; ++i) {

            int offset =  i - first;
            // save top
            int top = matrix[first * n + i];

            // left to top
            matrix[first * n + i] = matrix[(last-offset) * n + first];

            // bottom to left
            matrix[(last-offset) * n + first] = matrix[last * n + (last-offset)];

            // right to bottom
            matrix[last * n + (last-offset)] = matrix[i * n + last];

            // top to right
            matrix[i * n + last] = top;
        }
    }
}


void myArray::printMatrix(int* matrix, int m, int n) {

    for (int i = 0; i < m; ++i) {

        for (int j = 0; j < n; ++j){

            cout << matrix[i * n + j] << " ";
        }

        cout << endl;
    }
}
/*END of solution 1-6 : design an algorithm  
to rotate an image represented in N x N  
matrix by 90 degrees*/





/*question 1-7 design an algorithm such 
that if an element in an MxN matrix is 0, 
its entire row and column is zero*/
void myArray::setZeros(int* matrix, int m, int n) {

    // Assuming M,N <= 32, we'll use a bit vector to 
    // represent whether a row/col should be set with zeros.
    int m_rows = 0;
    int m_cols = 0;

    for (int i = 0; i < m; ++i) 
    {
        for (int j = 0; j < n; ++j) 
        {
            if (matrix[i * n + j] == 0) 
            {
                m_rows |= (1 << i);
                m_cols |= (1 << j);
            }
        }
    }

    for (int i = 0; i < m; ++i) 
    {
        for (int j = 0; j < n; ++j) 
        {
            if (((m_rows & (1 << i)) != 0) || ((m_cols & (1 << j)) != 0)) 
            {
                matrix[i * n + j] = 0;
            }
        }
    }
}
/*END of solution 1-7 design an algorithm such 
that if an element in an MxN matrix is 0, 
its entire row and column is zero*/







/*question 1-8 : design an algorithm to check for 
two strings, s1 and s2 if s2 is a rotation of s1*/
/*'waterbottle'  is the rotation of 'erbottlewat'*/
bool myArray::isRotation(const string& s1, const string& s2) {

    int len = s1.length();

    if(len == s2.length() && len > 0){

        string s1s1 = s1 + s1;
        return s1s1.find(s2) != string::npos;
    }

    return false;
}
/*END of solution 1-8 : design an algorithm to check for 
two strings, s1 and s2 if s2 is a rotation of s1*/
/*'waterbottle'  is the rotation of 'erbottlewat'*/






string myArray::result(bool value){

    if (value) {        
        return "True";
    }

    return "False";
}





int myArray::run() {


    /*question 1-1 : design an algorithm to determine 
    if a string has all the unique characters*/

    /*string input[] ={"abcde", "aba"};

    for (int i = 0; i < 2; i++) 
    {
        cout << input[i] << " has unique characters: " << result(isUniqueChars(input[i])) << endl;
        cout << input[i] << " has unique characters: " << result(isUniqueChars2(input[i])) << endl;
    }*/

    /*END of solution 1-1 : design an algorithm to 
    determine if a string has all the unique characters*/




    /*question 1-2 : design an 
    algorithm to reverse a String*/

    /*char input[][10] = { "abcde", "cat" };

    for (int i = 0; i < 2; i++) 
    {
        cout << "reversing the string: " << input[i] << endl;
        reverse(input[i]);
        cout << "reverse of input string is " << input[i] << endl;
    }*/

    /*END of solution 1-2 : design an 
    algorithm to reverse a String*/




    /*question 1-3: design an algorithm to 
    check whether two strings are anagram*/

    /*string a = "apple";
    string b = "papel";
    cout << "Result for " << a << " and " << b << " is " << result(permutation(a, b)) << endl;*/

    /*END of solution 1-3: design an algorithm to 
    check whether two strings are anagram*/






    /*question 1-4 : design an algorithm to 
    replace all spaces in a string with ‘%20’*/

    /*string str = "abc d e f";

    // Increasing length of the string to meet question requirement of 'true' length by using char array. (Note: using a unique_ptr here)
    auto newStr = make_unique<char[]>(str.length() + 3 * 2 + 1);

    for (int i = 0; i < str.length(); i++){

        newStr[i] = str[i];
    }

    cout << "Original string is " << str << endl;
    replaceSpaces(newStr, str.length());
    cout << "New string with %20 is " << newStr.get() << endl;*/
    
    /*END of solution 1-4 : design an algorithm to 
    replace all spaces in a string with ‘%20’*/





    /*question 1-5 : design an algorithm to 
    compress string with the character numbers*/
   
    /*string str = "abbccccccde";
    string newstr = compressBetter(str);
    cout << "Original string is " << str << endl;
    cout << "Compressed string is " << newstr << endl;*/

    /*END of soluton 1-5 : design an algorithm to 
    compress string with the character numbers*/





    /*question 1-6 : design an algorithm  
    to rotate an image represented in N x N  
    matrix by 90 degrees*/

    /*int matrix[][5] ={{1, 2, 3, 4, 5},
                      {6, 7, 8, 9, 10},
                      {11, 12, 13, 14, 15},
                      {16, 17, 18, 19, 20},
                      {21, 22, 23, 24, 25}};
    int* matrixPtr = (int*)matrix;
    
    cout << "original matrix is :" << endl;
    printMatrix(matrixPtr, 5, 5);
    rotate(matrixPtr, 5);
    cout << "rotated matrix is: " << endl;
    printMatrix(matrixPtr, 5, 5);*/

    /*END of solution 1-6 : design an algorithm  
    to rotate an image represented in N x N  
    matrix by 90 degrees*/







    /*question 1-7 design an algorithm such 
    that if an element in an MxN matrix is 0, 
    its entire row and column is zero*/

    /*int matrix[4][5] ={{1, 2, 3, 4, 5},
                       {6, 7, 8, 9, 10},
                       {11, 12, 0, 14, 15},
                       {16, 17, 18, 0, 20}};
    int* matrixPtr = (int*)matrix;
    cout << "original matrix is :" << endl;
    printMatrix(matrixPtr, 4, 5);
    
    setZeros(matrixPtr, 4, 5);
    cout << "zeroed matrix is: " << endl;
    printMatrix(matrixPtr, 4, 5);*/
 
    /*END of solution 1-7 design an algorithm such 
    that if an element in an MxN matrix is 0, 
    its entire row and column is zero*/






    /*question 1-8 : design an algorithm to check for 
    two strings, s1 and s2 if s2 is a rotation of s1*/

    /*string a = "apple";
    string b = "leapp";
    cout << "Checking if string: " << a << " is a rotation of string: " << b << ": "
         << result(isRotation(a, b)) << endl;

    a = "james";
    b = "mesje";
    cout << "Checking if string: " << a << " is a rotation of string: " << b << ": "
        << result(isRotation(a, b)) << endl;*/

    /*END of solution 1-8 : design an algorithm to check for 
    two strings, s1 and s2 if s2 is a rotation of s1*/

    return 0;
}



int main(int argc, char const *argv[]){

    myArray myObject;

    std::cout << "Hello World from main\n";
    return myObject.run();

    // making the call using the class object 
    // return myObject.main(argc, argv);

    // for the static functions 
    // return myArray::main(argc, argv);
}


