#include<iostream>
#include<string>
#include<sstream>
#include<cstring>
#include<vector>
#include<list>
#include<deque>
#include<map>
#include<set>
#include <stdexcept>
#include <fstream>
#include<utility> // for using the pair 
#include<algorithm>
#include<numeric>
#include<cmath>
#include<cstdlib>
#include<time.h>
#include<array>

<<<<<<< HEAD

=======
>>>>>>> c586b42b63e538829554061f2141f5789b21adcc
#define WIDTH 5
#define HEIGHT 3

using namespace std;

<<<<<<< HEAD
=======

>>>>>>> c586b42b63e538829554061f2141f5789b21adcc
int jimmy [HEIGHT][WIDTH];
int n,m;




<<<<<<< HEAD
=======


>>>>>>> c586b42b63e538829554061f2141f5789b21adcc
/*Animal*/
class Animal{

	public:
		virtual void talk() = 0;
};


class Dog: public Animal{


	public:
		virtual void talk(){

			cout << "Bow Bow" << endl;
		}

};


class Cat: public Animal{

	public:

		virtual void talk(){

			cout << "Meaw meaw" << endl;
		}

};
/* ENd of solution: Animal*/















/* Quadrilateral */
class Quadrilateral{


	protected:
		double side1, side2, side3, side4;

	public:

		Quadrilateral(double s1, double s2, double s3, double s4){

			side1 = s1; 
			side2 = s2; 
			side3 = s3;
			side4 = s4;
		}

		virtual void display(){

			cout << "Quadrilateral with sides :"<< 
			side1<< " "<< side2 <<  " " << side3<< " " << side4<< endl;
		}
};




class Trapezoid : public Quadrilateral{

	public:

		Trapezoid(double s1, double s2, 
			double s3, double s4) : Quadrilateral(s1, s2, s3, s4){

		};

		virtual void dispaly(){

			cout << "Trapezoid with sides :"<< 
			side1<< " "<< side2 <<  " " << side3<< " " << side4<< endl;
		}

};




class Square : public Quadrilateral{

	public:

		Square(double side ) : Quadrilateral( side, side, side, side){

		};

		virtual void dispaly(){

			cout << "Quadrilateral with sides :"<< 
			side1<< " "<< side2 <<  " " << side3<< " " << side4<< endl;
		}

		virtual void display(){

			cout << "Square with sides :"<< 
			side1<< " "<< side2 <<  " " << side3<< " " << side4<< endl;
		}
};
/* Quadrilateral */





/*Shape*/
class Shape{


	virtual void setX(int xcor) = 0;
	virtual void setY(int ycor) = 0;

	virtual int getX() const = 0;
	virtual int getY() const = 0;

	virtual void draw() const = 0;
};



class Circle: public Shape{


	private:
		int x, y, radius;


	public:

		Circle(int xcor, int ycor, int r){

			x = xcor;
			y = ycor;
			radius = r;
		}

		virtual void setX(int xcor){

			x = xcor;
		}


		virtual void setY(int ycor){

			y = ycor;
		}

		virtual void setRadius(int r){

			radius = r;
		}

		virtual int getX() const{

			return x;
		}


		virtual int getY() const{

			return y;
		}


		virtual int getRadius() const {

			return radius;
		}


		virtual void draw() const{

			cout << "Drawing the circle "<< getX() << " , "
			<< getY() << "With radius of " << getRadius() << endl;
		}
}; 
/*Shape*/






// learn more about inheritance, abstract class, 
// ploymorphism and virtual functions


/* Need to know more about the abstract 
class and the virtual functions*/

/*Employee*/
class Employee{

	protected:
		string name;
		double pay;

	public:

		Employee(){

			name = "";
			pay = 0;
		}

		~Employee(){


		}

		Employee(string empName, double payRate){

			name = empName;
			pay = payRate;
		}

		string getName(){

			return name;
		}

		void setName(string empName){

			name = empName;
		}

		double getPay(){

			return pay;
		}

		void setPay(double payRate){

			pay = payRate;
		}

		string toString(){

			stringstream stm;
			stm << name << " : "<< pay;

			return stm.str();
		}


		virtual double grossPay(int hours){

			return pay * hours;
		}
};




class Manager : public Employee{


	private:

		bool salaried;

	public:

		Manager(): salaried(true){

		}

		Manager(string name, double payRate, 
			bool issalaried) : Employee(name, payRate){

			salaried = issalaried;
		}


		~Manager(){

		}

		bool getSalaried(){

			return salaried;
		}


		virtual double grossPay(int hours){

			if(salaried){

				return pay;
			}

			else
				return pay * hours;
		}


		string toString(){

			stringstream stm;
			string salary;

			if (salaried){

				salary = "Salaried";
			}

			else
				salary = "Hourly";

			stm << name << " " << pay << salary << endl; 
			return stm.str();
		}
};
/*Employee*/











// if not initialized, will be set to zero 
int static_storage;

void start(){

	cout << "Hello World"<<endl;

	int a, b, c;
	a= b = c = 10;

	cout <<"the values are = "<<a<<" "<<b<<" "<<c<< endl;

	a = (b=3, b+2);
	cout <<"the values are = "<<a<<" "<<b<<" "<<c<< endl;

	a = sizeof (char);


	string mystr;

	float price=0;
	int quantity=0;

	cout << "Enter price: ";
	getline (cin,mystr);
	stringstream(mystr) >> price;
	cout << "Enter quantity: ";
	getline (cin,mystr);
	stringstream(mystr) >> quantity;
	cout << "Total price: " << price*quantity << endl;

}




void datatypes(){


	int myarray[3] = {10,20,30};

	for (int i=0; i<3; ++i){

		++myarray[i];
	}

	for (int elem : myarray){

		cout << elem << '\n';
	}

	char myword[] = { 'H', 'e', 'l', 'l', 'o', '\0' }; 

	char question1[] = "What is your name? ";
	string question2 = "Where do you live? ";
	char answer1 [80];
	string answer2;

	cout << question1;
	cin >> answer1;
	cout << question2;

	cin >> answer2;
	cout << "Hello, " << answer1;
	cout << " from " << answer2 << "!\n";


	char myntcs[] = "some text";

	string mystring = myntcs;  // convert c-string to string
	cout << mystring;          // printed as a library string
	cout << mystring.c_str();  // printed as a c-string 
}






// control structures 
void control(){


	int i, n; 

	for ( n=0, i=100 ; n!=i ; ++n, --i ){

		// cout <<"n = "<< n<<" i = "<<i << endl;
	}

	string str = "Hello!";

	for (char c : str){

		cout << "[" << c << "]";
	}
	std::cout << '\n';


	for (int n=10; n>0; n--){

		cout << n << ", ";
		if (n==3)
		{
			cout << "countdown aborted!";
			break;
		}
	}

	for (int n=10; n>0; n--) {
		
		if (n==5) continue;
		cout << n << ", ";
	}

	cout << "liftoff!\n";

	// using of the goto 
	n=10;

	mylabel:
	cout << n << ", ";

	n--;
	if (n>0) goto mylabel;
	cout << "liftoff!\n";


	// using of the switch statement 
	int x;

	switch (x) {

		case 1:
			cout << "x is 1";
			break;
		
		case 2:
			cout << "x is 2";
			break;
		
		default:
			cout << "value of x unknown";
	}


	//  will initiallize with zeros 
	int baz [5] = { };
}








// functions 
void duplicate (int& a, int& b, int& c){

	a*=2;
	b*=2;
	c*=2;
}

inline string concatenate (const string& a, const string& b){

	return a+b;
}

long factorial (long a){

	if (a > 1)
		return (a * factorial (a-1));
	else
		return 1;
}


template <class T>
T sum (T a, T b){

	T result;
	result = a + b;
	return result;
}


template <class T, class U>
bool are_equal (T a, U b){

	return (a==b);
}


template <class T, int N>
T fixed_multiply (T val){

	return val * N;
}


namespace foo{

	int value() { return 5; }
}

namespace bar {

	const double pi = 3.1416;
	double value() { return 2*pi; }
}

namespace first{

	int x = 5;
	int y = 10;
}

namespace second{

	double x = 3.1416;
	double y = 2.7183;
}






// POINTERS IN ACTION 
// ------------------

void myPointers(){


	cout << "Hello Pointers" << endl;

	int firstvalue, secondvalue;
	int * mypointer;

	mypointer = &firstvalue;
	*mypointer = 10;
	mypointer = &secondvalue;
	*mypointer = 20;
	cout << "firstvalue is " << firstvalue << '\n';
	cout << "secondvalue is " << secondvalue << '\n';


	firstvalue = 5, secondvalue = 15;
	int * p1, * p2;

	p1 = &firstvalue;  // p1 = address of firstvalue
	p2 = &secondvalue; // p2 = address of secondvalue

	*p1 = 10;          // value pointed to by p1 = 10
	*p2 = *p1;         // value pointed to by p2 = value pointed to by p1
	p1 = p2;           // p1 = p2 (value of pointer is copied)
	*p1 = 20;          // value pointed to by p1 = 20

	cout << "firstvalue is " << firstvalue << '\n';
	cout << "secondvalue is " << secondvalue << '\n';



	int numbers[5];
	int * p;


	p = numbers;  *p = 10;
	p++;  *p = 20;
	p = &numbers[2];  *p = 30;
	p = numbers + 3;  *p = 40;
	p = numbers;  *(p+4) = 50;


	for (int n=0; n<5; n++)
		cout << numbers[n] << ", ";


	int a[5];

	a[5] = 0;       // a [offset of 5] = 0
	*(a+5) = 0;     // pointed to by (a+5) = 0  

	return;
} 



void increment_all (int* start, int* stop){

	int * current = start;

	while (current != stop) {

		++(*current);  // increment value pointed
		++current;     // increment pointer
	}
}


void print_all (const int* start, const int* stop){

	const int * current = start;

	while (current != stop) {

		cout << *current << '\n';
		++current;     // increment pointer
	}
}


void increase (void* data, int psize){

	if ( psize == sizeof(char) ){ 

		char* pchar; pchar=(char*)data; ++(*pchar); 
	}

	else if (psize == sizeof(int) ){ 

		int* pint; 
		pint=(int*)data; ++(*pint); 
	}
}


int addition (int a, int b){ 

	return (a+b); 
}


int subtraction (int a, int b){ 

	return (a-b); 
}


int operation (int x, int y, int (*functocall)(int,int)){

	int g;
	g = (*functocall)(x,y);
	return (g);
}

<<<<<<< HEAD


=======
>>>>>>> c586b42b63e538829554061f2141f5789b21adcc
// POINTERS IN ACTION 
// ------------------

//  C++ tutorials: <http://www.cplusplus.com/doc/tutorial/>

// Need to check the topics
// ------------------------

/*
=============================

1. Pointers 
2. Dynamic Memory
3. Data structures
4. Other data types
5. Classes l & ll
6. Special members
7. Friendship and inheritance
8. Polymorphism
9. Type conversions
10. Exceptions
11. Preprocessor directives
12. Input/Output with files

=============================
*/






// ======================

// advanced C++ tutorial
// =====================







/* 15 and 16 */
template <typename T>
void dispaly(T arr[], int size ){

	cout << "Hello Display" << endl;

	for (int i = 0; i < size; ++i){

		cout << arr[i] << endl;
	}

	return;
}

template <typename T>
T max(T &arg1, T &arg2){


	if(arg1 > arg2)
		return arg1;

	else 
		return arg2;
}
/*15 and 16*/








/* 17 -20*/


template <typename T>
class Stack{

	private:
		
		T datastore[100];
		int top;

	public:

		Stack(){

			top = -1;
		}

		void push(T num){

			++top;
			datastore[top] = num;
		}

		T pop(){

			T val = datastore[top];
			datastore[top] = 0;
			--top;
			return val;
		}

		T peek(){

			return datastore[top];
		}
};




template<>
class Stack<string>{

	private:
		
		string datastore[100];
		int top;

	public:

		Stack(){

			top = -1;
		}

		void push(string num){

			++top;
			datastore[top] = num;
		}

		string pop(){

			string val = datastore[top];
			datastore[top] = "";
			--top;
			return val;
		}

		string peek(){

			return datastore[top];
		}

};



// function templates with multiple datatypes
template<typename T, typename U>
T maxValue(T arg1, U arg2){

	return (arg1 > arg2)? arg1: arg2;
}



template<class T, class U>
class CMap{

	private:

		vector<T> keys;
		vector<U> values; 

	public:

		void insert(T key, U value){

			keys.push_back(key);
			values.push_back(value);
		}

		void get(int index){

			cout << "Key = "<<keys[index] << "\tValue = "<< values[index] << endl;
		}

};




template <typename T>
class List{


	private:
		vector<T> data;

	public:

		List(){ }

		void add(T item){

			data.push_back(item);
		}

		void display(){

			for( int i = 0; i < data.size(); i++){

				cout << data[i] << endl;
			}
		}

};
/* 17 -20*/


// 23

const int DivideByZero = 1;

void exceptionTest(){

	try{


		int number = 12;
		int denom = 0;

		if(denom == 0){

			throw DivideByZero;
		}

		else
			cout << number/denom << endl;
	}

	catch(int e){

		if (e == DivideByZero)
			cout << "can't divided by zero" << endl;
	}
}




// 24 

class DividedByZero : public runtime_error{

	public:
		DividedByZero():

			runtime_error("Divide by zero exception"){

			}

};


double quotient(double number, double denom){

	if(denom == 0){
		throw DividedByZero();
	}

	else{
		return number/denom;
	}
}





// the file is  not opening 
// using this method 
void fileException(){


	ifstream file;
	file.exceptions(ifstream:: failbit 
		| ifstream:: badbit);

	try {

		file.open("~/Desktop/t.txt");
		
		while(!file.eof()){
			cout << file.get() << endl;
		}
	}

	catch(ifstream::failure e) {

		cout << e.what() << endl;
		cout << "Error opening the file" << endl;
	
		return;
	}

	file.close();
}




// STREAMS IN THE C++
// ==================

// sequences of bytes 
// iostream = cin, cout, cerr
// fstream = ifstream, ofstream   



void myStream(){

	cout.put('h');
	cout.put('e');
	cout.put('l');
	cout.put('l');
	cout.put('o');

	cout.put('w');
	cout.put('o');
	cout.put('r');
	cout.put('l');
	cout.put('d');

	cout.put('!').put('\n');


	cout << "hello world!" << endl;
	cout << "hello world" << flush;
	cout << "hello world!" << ends;

	return;
}




// 30 managing stream input 
void stringStream(){

	char c;
	c = cin.get();

	do {

		cout.put(c);
		c = cin.get();
	}

	while(!cin.eof());
}





// 31 String Streams 
class Person{

	private:

		string first, middle, last; 
		int age;

	public: 

		Person(string f, string m, string l, int ag){

			first = f;
			middle = m;
			last =  l;

			age = ag;
		}


		string ToString(){

			// return first+" "+ middle+ " "+ last+ " "+ age;

			stringstream stm;
			stm << first << " " <<  middle << " " << last << " " << age;
		
			return stm.str();
		}
};


void mytest1(){

	ifstream gradeFile; 
	stringstream grades; 

	int grade;
	int total = 0;

	// how to open the file for the Mac?
	gradeFile.open("c:\\data\\grades.txt");

	string line;
	getline(gradeFile, line);

	grades << line;
	gradeFile.close();

	for( int j = 0; j < 5; ++j){

		grades >> grade;
		total += grade; 
	}

	double average = total/ 5;
	cout << "Average = " << average << endl;
}




// 33 vector sequencial container 
void myVectors(){

	vector<int> numbers(10);

	for(int j = 0; j < 11; ++j){

		numbers.push_back(j);
	}

	int sum = 0;

	for(int j =0; j < numbers.size(); j++){
		sum +=  numbers[j];
	}

	cout << "the total is " << sum << endl;
	return; 
} 





// 34 list part 1 and 2 

// dispaly the elements of the list
void display(list<string> lyst) {

	list<string>::iterator iter = lyst.begin();

	while(iter != lyst.end()){
		cout << *iter << endl;
		++iter;
	}
}


void myList() {

	list<string> names;

	names.push_back("Mary");
	names.push_back("Zach");
	names.push_back("Arefe");

	list<string>::iterator iter = names.begin();

	while(iter != names.end()){

		cout << *iter << endl;
		++iter;
	}

	cout << "first name = "<< names.front() << endl;
	cout << "last name = "<< names.back() << endl;

	names.reverse();

	list<string>::iterator riter = names.begin();

	while(riter != names.end()){

		cout << *riter << endl;
		++riter;
	}

	names.sort();
	list<string>::iterator siter = names.begin();

	while(siter != names.end()){

		cout << *siter << endl;
		++siter;
	} 

	names.sort();


	display(names);
	names.push_front("Barry");

	display(names);
	cout << "the size of the list "<< names.size() << endl;

	names.remove("Barry");
	display(names);

	names.pop_front();
	names.pop_back();

	display(names);
	names.clear();

	if(names.empty()){

		cout << "the list is empty" << endl;
	}

	else
		cout << "the list os not empty" << endl;
	
	return;
}





// 36 and 37 
// find an element inside the deque 
int find( deque<string> d, string value){

	for( int j = 0; j < d.size(); j++){

		if(d.at(j) == value) {
			return j;
		}
			
	}

	return -1;
}


void myDeque() {

	deque<string> line;

	line.push_back("Customer 1");
	line.push_front("Customer 2");
	line.push_back("Customer 3");

	for(int i = 0; i < line.size(); i++){
		cout << line[i] << endl;
	}

	line.pop_back();
	line.pop_front();

	for(int i = 0; i < line.size(); i++){

		cout << line[i] << endl;
	}

	cout << "size of the seque"<< line.size() << endl;

	return;	
}




void dequeTest(){


	deque<string> line;

	line.push_back("berlin");
	line.push_front("munich");
	line.push_back("seattle");
	line.push_back("dhaka");

	deque<string>::iterator iter = line.begin();
	++iter;

	line.insert(iter, "khulna");

	for(iter = line.begin(); iter < line.end(); ++iter){

		cout << *iter << endl;
	}	

	return;
}


void insertMax(list<int> &lis, int value){

	lis.sort();

	int max = lis.back();

	if (value > max){

		lis.push_back(value);
	}
} 



void dequeTest1(){

	deque<string> names;
	ifstream nameList;

	nameList.open("c:\\data\\names.txt");
	string line;

	while(!nameList.eof()){

		getline(nameList, line);

		string::iterator it = line.end();

		--it;

		size_t pos = line.find(" ");
		string name = line.substr(0, pos);

		if(*it == 'p') {
			names.push_front(name);
		}
			
		else {
			names.push_front(name);
		} 			
	}

	deque<string>::iterator it = names.begin();

	while (it != names.end()){
		cout << *it << endl;
		++it;
	}

	nameList.close();
}
// 36 sequencial containers : deque 



void myPair(){

	pair<string, string> number("John", "123");
	cout << number.first << " "<< number.second<<endl;

	pair<string, int> student("Emmy", 100);

	cout << student.first<<" "<< student.second<< " "<< endl;
}





// 41 
void myMap() {

	map<string, int> numbers;

	numbers["Johns"] = 345;
	numbers["Smith"] = 12;
	numbers["Brown"] = 78;

	cout << numbers["Johns"] << endl;
	cout << numbers.size() << endl;

	numbers.erase("Johns");


	// Though the "John" is not in the map
	// it wont present any error 

	// numbers.erase("John");
	cout << "Size of the map = "<< numbers.size() << endl;

	string name;

	cin>> name;

	if (numbers.find(name) !=  numbers.end()){

		cout << "Not found" << endl;
	}

	double average = 0.0;

	int sum = 0;
	map<string, int>::iterator it = numbers.begin();


	while( it != numbers.end()){

		cout << it->second << endl;
		sum += it->second;
		++it;
	}

	average = sum/ numbers.size();

	cout << "the average grade is = "<< average << endl;
}




// 43 Sets in the C++   
void myset(){

	set<string> words;
	string word = "";

	do {

		cout << "Enter a word (type 'quit' to  quit )  "<< endl;
		cin >> word; 
		words.insert(word); 
	}

	while(word !=  "quit");

	set<string>::iterator it = words.begin();

	while(it != words.end()){
		cout << *it << endl;
		++it;
	}
}






// 44 
void myMultimap(){

	multimap<string, string> numbers;

	numbers.insert(pair<string, string>("Johns", "123"));
	numbers.insert(pair<string, string>("Robert", "567"));
	numbers.insert(pair<string, string>("Robert1", "555667"));
	numbers.insert(pair<string, string>("Robert2", "57"));
	numbers.insert(pair<string, string>("Robert3", "67"));
	numbers.insert(pair<string, string>("Robert56", "567111"));
	numbers.insert(pair<string, string>("Johns", "9000"));

	string searchName = "Johns";

	multimap<string, string>::iterator iter = 
			numbers.find(searchName);

	multimap<string, string>::iterator last = 
			numbers.upper_bound(searchName);	

	for(; iter != last; ++iter){
		cout << " First = "<< iter->first<< " ,  Second "<<iter->second<< endl;
	}
}




void showNumber(map<string, string> phonelist){

	string name; 
	cout <<  "Enter a name : " << endl;

	cin >> name;

	cout << endl<< "the number is : " << phonelist[name] << endl;
}


void showMenu(){

	cout << "1 . get number " << endl;

	cout << "2. Quit " << endl;
	cout << " " << endl;

	cout << "Enter choice " << endl;
}


string getName(string line){

	int pos = line.find(",");
	return line.substr(0, pos);
}

string getNumber(string line){

	int pos =  line.find(",");
	return line.substr(pos+1);
}


void mytest2(){

	map<string, string> phoneList;
	ifstream inFile("c:\\data\\phonelist.txt");

	string line, name, number;

	while(!inFile.eof()){

		getline(inFile, line);
		name =  getName(line);
		number = getNumber(line);
		phoneList[name] = number;
	}

	inFile.close();

	int choice = 1;

	while(choice != 2){

		showMenu();
		cin >> choice;

		if (choice == 1){
			showNumber(phoneList);
		}
	}
}




// not working properly 
void primeGeneration(int n){

	set<int> primes;
	int count;

	for(int nums = 2; nums <= n; ++nums){
		primes.insert(nums);
	}

	set<int>::iterator it;

	for(int mults =2; mults*mults <= n; ++mults){

		it = primes.find(mults);

		if (it != primes.end()){

			for (int k = 2*mults; k <= n; k += mults){

				it = primes.find(k);

				if (it != primes.end()){

					primes.erase(*it);
				}
			}
		}
	}

	count =1;

	for(set<int>::iterator it1 = primes.begin(); 
		it1 != primes.end(); ++it1){

		// print the prime numbers 
		cout << *it <<" "<< endl;

		if(count++ % 10 == 0){
			cout <<endl;
		}
	}
}



void myAlgorithm(){

	vector<int> numbers;

	for(int i =0; i <=10; i++){
		numbers.push_back(i);
	}

	int sum = accumulate(numbers.begin(), numbers.end(), 0);
	cout << "the sum is : "<< sum << endl;

	vector<string> words;

	words.push_back("a");
	words.push_back("seattle");
	words.push_back("miami");
	words.push_back("bs");
	words.push_back("new");
	words.push_back("york");

	string palindrome =  accumulate(words.begin(), words.end(), string(""));
	cout << "The palindrome is : "<< palindrome << endl;

	vector<int> nums(10);

	fill(nums.begin(), nums.end(), 0);

	for( int j =0; j < nums.size(); j++){

		cout<< nums[j] <<" ";
	}	

	cout<< endl;


	// replace "miami" with the word "hello"
	replace(words.begin(), words.end(), "miami", "hello");

	for( int j =0; j < words.size(); j++){

		cout<< words[j] <<" ";
	}	

	cout<< endl;	

	return;
}




// 49 - sorting algorithms 
// ==================

template <typename T>
void display(vector<T> nums){

	for(int j = 0; j < nums.size(); ++j){
		cout << nums[j] << " ";
	}	

	cout<< endl;
}



template <typename T>
void reverse(vector<T> nums){

	for(vector<int>::reverse_iterator riter = nums.rbegin();
		riter != nums.rend(); ++riter){

		cout << *riter << endl;
	}

	cout<< endl;
}



template <typename T>
void display(deque<T> nums){

	for(int j = 0; j < nums.size(); ++j){
		cout << nums[j] << " ";
	}	

	cout<< endl;
}


void mySort(){

	const int sizeNumbers = 10;

	int numbers[] = {1,4,6,7,8,9,10, 2,3,5};

	vector<int> nums(numbers, numbers + sizeNumbers);

	display(nums);

	sort(nums.begin(), nums.end());

	display(nums);
}


int genRandom();


void backInsert(){

	vector<int> v1;
	vector<int> v2; 

	v1.push_back(1);
	v1.push_back(2);
	v1.push_back(3);

	v2.push_back(4);
	v2.push_back(5);
	v2.push_back(6);

	copy(v2.begin(), v2.end(), back_inserter(v1));

	display(v1);


	deque<int> d1;

	d1.push_front(1);
	d1.push_front(2);
	d1.push_front(3);


	copy(v2.begin(), v2.end(), front_inserter(d1));


	cout << d1.front() << endl;
	// 6 
	

	display(d1);
	// output 
	// ------
	// 6 5 4 3 2 1 


	vector<int> nubs(10);
	srand(time(NULL));

	cout << endl;

	/*for(int j = 0; j < nubs.size(); ++j){

		nubs[j] = rand() % 100 + 1;
	}
	*/

	generate(nubs.begin(), nubs.end(), genRandom);
	display(nubs);
}



int genRandom(){
	return rand() % 100 +1;
}



double getTime(clock_t time1, clock_t time2){
	double ticks = time1 - time2;
	return (ticks*10)/ CLOCKS_PER_SEC;
}



void getData(int arr[], int size){

	srand(time(NULL));

	for(int i = 0; i < size; i++){
		cout << arr[i] << endl;
	}

	clock_t begin = clock();

	// some code here 
	clock_t end =  clock();
	cout << getTime(begin, end) << endl;
}



// INTRO TO NAMESPACE 
namespace minMax{

	int min(int num1, int num2){

		if(num1 < num2)
			return num1;

		else 
			return num2;
	}

	int max(int num1, int num2){

		if(num1 < num2)
			return num2;

		else 
			return num1;		
	}
}


namespace People {

	class Person{

		private:
			string name;
			string sex;

		public:

			Person(string n, string s){
				name =  n;
				sex = s;
			}

			string get(){
				return name +"  , "+ sex;  
			}
	};
}



void myString(){


	string str0;
	string str1 = "";
	string str2(str1);

	string str3("I string 3");
	string str4(10, 'a');

	string str5 = str1+ " "+ str3+ "!";

	// will produce an error 
	// string str6 = "hello" + "my";

	string str7 = "clear";
	string str8 = "clean";

	if (str7 > str8){
		cout << str7<<" is greater than "<< str8 << endl;
	}
		
	else {
		cout << str8<<" is greater than "<< str7 << endl;
	}
		
	// compare returns 0 or 1

	// will return 1 as "clear" is greater than "clean" 
	cout << str7.compare(str8) << endl;
	
	// will return 0 
	cout << str8.compare(str7) << endl;


	string st = "its pin hard to find a needle in the pin grass";
	int pos =  st.find("pin");

	if( pos >= 0) {
		cout << "find the needle in the position = "<< pos << endl;
	}

	else {
		cout << "there is no needle" << endl;
	}	

	int pos1 = st.rfind("pin");
	cout << "the last occurance = "<< pos1 << endl;

	string numbers1 = "0123456789";
	string identifier = "nam6sdfr";

	int pos5 =  identifier.find_first_of(numbers1);


	// the identifier contains char of numbers1
	if(pos5 >= 0) {
		cout << "illegal identifier" << endl;
	}
		
	else
		cout << "Legal stuff" << endl;


	string ss1 =  "a needle in a haystack";
	string word = "needle";

	int pos2 = ss1.find(word);

	string ss2 =  ss1.substr(pos2, word.length());

	cout << ss2 << endl;

	string ss3 =  ss1.substr(pos2 + word.length()+1);
	cout << ss3 << endl;

	ss1.replace(pos2, word.length(), "pin");


	char c1[] = {'h', 'e', 'l', 'l', 'o', '\0'};
	char c2 [] = "world!";

	cout << c1 << " "<< c2<<endl; 
	int value = strcmp(c1, c2);

	cout << value << endl;

	// concatinate c2 with c1 
	strcat(c1, c2);

	cout << c1 << endl;
	return;
}



int find (string word, char c){

	for(int j = 0; j < word.length(); j++){

		if(word[j] == c)
			return j;
	}

	return -1;
}


string replace(string word , char oldChar, char newChar){

	int pos = find(word, oldChar);

	word = word.substr(0, pos) + newChar + word.substr(pos+1);
	return word;
}



void procedure (int myarray[][3][4]){
	cout << "Need to define the last two dimensions of the array" << endl;
}


// ========================================



int main(int argc, char const *argv[]){


	cout << "Getting started with C++\n" << endl;

	// start();
	// control();
	// functions();


	// fucntion templates 
	// ------------------


	/*int i=5, j=6, k;
	double f=2.0, g=0.5, h;

	k=sum<int>(i,j);
	// k = sum(i, j ); // also, valid 

	h=sum<double>(f,g);
	// h = sum(f, g); // also, valid 

	cout << k << '\n';
	cout << h << '\n';*/


	// cout << fixed_multiply<int,2>(10) << '\n';
	// cout << fixed_multiply<int,3>(10) << '\n';

	// cout << foo::value() << '\n';
	// cout << bar::value() << '\n';
	// cout << bar::pi << '\n';


	/*using first::x;
	using second::y;

	cout << x << '\n';
	cout << y << '\n';
	cout << first::y << '\n';
	cout << second::x << '\n';

	{
		using namespace first;
		cout << x << '\n';
	}

	{
		using namespace second;
		cout << x << '\n';
	}*/


	// global variable and not initialized, so, it will be set to zero.
	// cout << "static storage = "<<static_storage << endl;


	// pointers();



	// 15 and 16 

	/*const int size = 10;
	int numbers[size];

	for (int i = 0; i < size; i++){

		numbers[i] = i+1;  
	}

	string names[] = { "seattle", "miami", "boston"};

	// dispaly(names, 3);


	// cout << max(1.4, 1.9) << endl;

	string first = "seattle";
	string second = "boston";

	// cout << max(first, second) << endl;*/

	/*15 and 16 */







	/* 17 -20*/
	/*Stack<string> names;

	names.push("miami");
	names.push("seattle");
	names.push("munich");

	cout << names.peek() << endl;
	string val = names.pop();
	cout << names.peek() << endl; */


	// function templates with multiple datatypes
	// =========================================
	/*int num1 = 12;
	double num2 = 13.456;

	cout << maxValue(num1, num2) << endl;*/


	// 20.  Templates With Multiple Data Types
	/*CMap<string, int> grades;
	grades.insert("John", 12);
	grades.insert("Arefe", 14);
	grades.get(0);*/




	/*List<string> names;
	names.add("Seattle");
	names.add("New York");
	names.add("Berlin");

	names.display();*/
	/*17-20*/




	// 25 

	/*double number1, number2, ratio; 

	cout << "Enter a numerator:	" << endl; 
	cin >> number1;

	cout << "Enter a denomenator: " << endl;
	cin >> number2;


	try{

		ratio = quotient(number1, number2);
		cout << "Result is : " << ratio <<endl;
	}*/

	/*catch(DividedByZero &except){

		cout << except.what() << endl;
	}*/


	/*catch(...){

		cout << "Exception thrown and caught "<< endl;
	}*/



	// fileException();
	// stringStream();


	// 31 string stream

	/*Person myself("Chak", "Asfak", "Arefe", 33);
	cout <<  myself.ToString() << endl; */


		// myAlgorithm();
	// mySort();

	// backInsert();

	/*using namespace minMax;


	int a = 12;
	int b =  678;

	cout << min(a,b) << endl;
	cout << max(a,b) << endl;

	using namespace People;
	People::Person you("Jone Due", "F");
	cout << you.get() << endl;*/


	/*Employee emp1("Jane Smith", 35000);
	Manager mgr1("Mark John", 78000, true);

	Employee *empPtr;

	empPtr = &emp1;

	cout << empPtr->getName() << endl;
	cout << empPtr->grossPay(40) << endl;

	empPtr = &mgr1;

	cout << empPtr->getName() << endl;
	cout << empPtr->grossPay(40) << endl;



	vector<Employee*> employees;

	employees.push_back(&emp1);
	employees.push_back(&mgr1);

	for (int j = 0; j < employees.size(); j++){

		cout << employees[j]->getName() << 
			employees[j]->grossPay(40) << endl;
	}*/



	/*Circle c1(2,3,5);
	c1.draw();*/



	/*Trapezoid*/
	/*Trapezoid t1(3,5,5,2);
	Square s1(6);

	vector<Quadrilateral*> quads;

	quads.push_back(&t1);
	quads.push_back(&s1); 

	for(int j = 0; j < quads.size(); j++){

		quads[j]->display();
		cout<< endl;
	}*/
	/*Trapezoid*/



	/*Animal*/
	/*Dog fido;
	Cat kitty;

	fido.talk();
	kitty.talk();*/
	/*Animal*/




	// multi-dimentional array  
	/*for (n=0; n<HEIGHT; n++)
	for (m=0; m<WIDTH; m++){


	  jimmy[n][m]=(n+1)*(m+1);
	}*/


	// container library array
	/*array<int,3> myarray = {10,20,30};

	for (int i=0; i<myarray.size(); ++i){

		++myarray[i];
	}
	*/


	/*char myword[] = { 'B', 'y', 'e', '\0' };

	char myntcs[] = "some text";
	string mystring = myntcs;  // convert c-string to string
	cout << mystring;          // printed as a library string
	cout << mystring.c_str();  // printed as a c-string */







	// POINTERS IN ACTION 
	// ------------------

	myPointers();

	int numbers[] = {10,20,30};
  	increment_all (numbers,numbers+3);
  	print_all (numbers,numbers+3);

  	char a = 'x';
	int b = 1602;
	increase (&a,sizeof(a));
	increase (&b,sizeof(b));
	cout << a << ", " << b << '\n';

	int m,n;
	int (*minus)(int,int) = subtraction;

	m = operation (7, 5, addition);
	n = operation (20, m, minus);
	cout <<n;

	// POINTERS IN ACTION 
	// ------------------

	return 0;
}

