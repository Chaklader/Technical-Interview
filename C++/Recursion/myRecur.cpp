#include<iostream>
#include <cstring>
#include <vector>
#include <cstdio>
#include <cmath>
#include <map>

#define GRID_SIZE 8


using namespace std;  

typedef vector<string> vStr;
typedef vector<vector<int> > vi;

// 9-11 
typedef map<int, int> mi;







/*9-1*/
int noOfSteps(int n, int arr[]){

	if(arr[n])
		return arr[n];

	if( n < 4){

		if(n==1)
			arr[n] = 1;
		
		else if(n==2){
		
			arr[2] = 2;
		}

		else if(n==3){
			arr[3] = 4;
		}
	}
	else
		arr[n] = noOfSteps(n-1, arr) + noOfSteps(n-2, arr) + noOfSteps(n-3, arr);
	return arr[n];
}
/*END of solution 9-1*/






/* question 9-2*/

int getPath(int x, int y, vector<vector<int> > &vec){

	if(vec[x][y])
		return vec[x][y];


	if(x==0 || y==0){
	
		if(x==0 && y==0)
			vec[x][y] = 1;
		else if(x==0)
			vec[x][y] = getPath(x, y-1, vec);
		else
			vec[x][y] = getPath(x-1, y, vec);
	}
	
	else
		vec[x][y] = getPath(x-1, y, vec) + getPath(x, y-1, vec);
	
	return vec[x][y];
}

void pint(){

	cout<<"@"<<'\n';
}

int getPath1(int x, int y, vector<vector<int> > &vec){

	if(vec[x][y]){

		if(vec[x][y] != -1)
			return vec[x][y];
		return 0;
	}

	if(x==0 || y==0){

		if(x==0 && y==0)
			vec[x][y] = 1;
		else if(x==0)
			vec[x][y] = getPath1(x, y-1, vec);
		else
			vec[x][y] = getPath1(x-1, y, vec);
	}

	else{

		vec[x][y] = getPath1(x-1, y, vec) + getPath1(x, y-1, vec);
	}

	return vec[x][y];
}
/*END of solution 9-2*/







/*9-3*/


//For distinct and sorted array
int findMagicIndex(int arr[], int startInd, int endInd){

	if(endInd < startInd)
		return -1;

	int midInd = startInd + (endInd - startInd)/2;
	if(arr[midInd] > midInd)
		return findMagicIndex(arr, startInd, midInd);
	else if(arr[midInd] < midInd)
		return findMagicIndex(arr, midInd+1, endInd);
	else
		return midInd;
}

//For non-distinct and sorted array
int findMagicIndex1(int arr[], int startInd, int endInd){


	if(endInd < startInd)
		return -1;
	
	int midInd = startInd + (endInd - startInd)/2;
	if (arr[midInd] == midInd)
		return midInd;

	//Left search
	int leftInd = min(arr[midInd], midInd-1);
	int left = findMagicIndex1(arr, startInd, leftInd);
	
	if(left > -1)
		return left;

	//right search
	int rightInd = max(arr[midInd], midInd+1);
	int right = findMagicIndex1(arr, rightInd, endInd);
	
	if(right > -1)
		return right;
}
/*END of solution 9-3*/







/*9-4*/
void printSubset(vector<vector<int> > &subset, int arr[], int n){


	if(n<0)
		return;
	
	printSubset(subset, arr, n-1);
	int m = subset.size();
	
	for (int i=0; i < m; i++){
	
		vector<int> v;
		for (int j = 0; j < subset[i].size(); j++){
			v.push_back(subset[i][j]);
		}
		v.push_back(arr[n]);
		subset.push_back(v);
	}
	
	vector<int> v;
	v.push_back(arr[n]);
	subset.push_back(v);
}
/*END of solution 9-4*/





/*9-5*/


vStr printPermutation(string str){


	vStr v, vs;
	
	if(str.length()==0){
	
		vs.push_back("");
		return vs;
	}
	
	string a = str.substr(0, 1);
	string b;
	v = printPermutation(str.substr(1));
	
	for (int i= 0; i < v.size(); ++i){
	
		for (int j = 0; j <= v[i].length(); ++j) {
	
			b = v[i].substr(0, j) + a + v[i].substr(j);
			vs.push_back(b);
		}
	}
	return vs;
}
/*9-5*/




/*9-6*/
void generateParen(string str, int left, int right){


	if (left == 0 && right == 0) {

		//It can be stored into some variable here
		cout<<str<<'\n';
		return;
	}

	if (left == right) {

		generateParen(str + "(", left - 1, right);
		return;
	}

	else{
		if (left) {
			generateParen(str + "(", left - 1, right);
		}
		generateParen(str + ")", left, right - 1);
	}
}
/*9-6*/





/*9-7*/
enum Color {

	Bl, Wh, Gr, Bu, Or
};

void paintFill(Color **screen, int m, int n, int x, int y, Color oldColor, Color newColor) {

	//Here x is x-coordinate not no. of rows
	if (x<0 || x>=n || y<0 || y>=m) {

		return;
	}

	if (screen[y][x] == oldColor) {

		screen[y][x] = newColor;
		paintFill(screen, m, n, x-1, y, oldColor, newColor);
		paintFill(screen, m, n, x, y-1, oldColor, newColor);
		paintFill(screen, m, n, x+1, y, oldColor, newColor);
		paintFill(screen, m, n, x, y+1, oldColor, newColor);
	}
	return;
}
/*END of solution 9-7*/









/*9-8*/
int makeChange(int n, int prev){

	if (n == 0)
		return 1;

	int ways = 0;

	if(n > 0) {

		if (prev >= 25)
			ways += makeChange(n-25, 25);
		if(prev >= 10)
			ways += makeChange(n-10, 10);
		if(prev >= 5)
			ways += makeChange(n-5, 5);
		if(prev >= 1)
			ways += 1;
	}

	return ways;
}

int makeChange1(int n, int denom){
    
    int next_denom = 0;
    
    switch(denom){

	    case 25:
	        next_denom = 10;
	        break;
	    
	    case 10:
	        next_denom = 5;
	        break;
	    
	    case 5:
	        next_denom = 1;
	        break;
	    
	    case 1:
	        return 1;
    }

    int ways = 0;
    
    for(int i=0; i*denom<=n; ++i)
        ways += makeChange1(n-i*denom, next_denom);
    
    return ways;
}
/*END of solution 9-8*/






/*9-9*/



// To check if Queen palced is in valid spot in comaparison to previous one
bool checkValid(vector<int> coloums, int row1, int col1) {


	for (int row2 = 0; row2 < row1; ++row2) {
	
		int col2 = coloums[row2];
	
		if (col1 == col2)
			return false;
	
		int rowDist = row1 - row2;
		int colDist = abs(col1 - col2);
	
		if (rowDist == colDist)
			return false;
	}
	
	return true;
}

void placeQueens(int row, vector<int> &colums, vi &result){
	
	if (row == GRID_SIZE)	//Placed all Queens
		result.push_back(colums);
	
	else {
	
		for (int col = 0; col < GRID_SIZE; ++col) {
			if (checkValid(colums, row, col)) {
				colums[row] = col;		//Place Queen
				placeQueens(row+1, colums, result);
			}
		}
	}
}
/*END of solution 9-9*/









/*9-10*/
class box {

	private:
		float h, w, d;	

	public:
		box(float H, float W, float D){
			h = H;
			w = W;
			d =	D;
		}
		bool canBeAbove(box B);
		float getDepth();
	};

	bool box::canBeAbove(box B){

		if (h<B.h && w<B.w && d<B.d)
			return true;

		return false;
	}

	float box::getDepth(){

		return d;
	}

	typedef vector<box> vb;
	typedef map<int, vb> mvb;

	float stackHeight(vb Boxes){

		float height = 0;

		for (int i = 0; i < Boxes.size(); ++i) {

			height += Boxes[i].getDepth();
		}
		return height;
	}

	void print(){

		cout<<"@";
	}

	vb createStack(mvb &Map, vb Boxes, int bottom) {

		if (Map.count(bottom)!= 0 && bottom < Boxes.size()) {
		
			return Map[bottom];
		}
		
		int max_height = 0;
		
		vb max_stack;
		
		if (bottom >= Boxes.size()) {
		
			return max_stack;
		}
		
		for (int i = 0; i < Boxes.size(); ++i) {
		
			if (Boxes[i].canBeAbove(Boxes[bottom])) {
				vb tmpStack = createStack(Map, Boxes, i);
				int tmpHeight = stackHeight(tmpStack);
				if (tmpHeight > max_height) {
					print();
					max_stack = tmpStack;
					max_height = tmpHeight;
				}
			}
	}


	//Push Boxes[bottom] to font hee
	max_stack.push_back(Boxes[bottom]);
	
	Map[bottom] = max_stack;
	return max_stack;
}
/*9-10*/






/*9-11*/
int placeParen(string exp, int result, int start, int end, mi &Map){


	int key = result*200 + start*7 + end*3;
	
	if (Map.count(key))
		return Map[key];
	
	if (start == end) {
	
		if (exp[start] == '1' && result == 1)
			return 1;
		else if (exp[start] == '0' && result == 0)
			return 1;
		return 0;
	}
	
	int c=0;
	
	if (result) {

		for (int i = start + 1; i < end; i+=2) {
		
			char op = exp[i];
		
			if (op == '&')
				c += placeParen(exp, 1, start, i-1, Map) * placeParen(exp, 1, i+1, end, Map);
		
			else if (op == '|') {
				c += placeParen(exp, 1, start, i-1, Map) * placeParen(exp, 0, i+1, end, Map);
				c += placeParen(exp, 0, start, i-1, Map) * placeParen(exp, 1, i+1, end, Map);
				c += placeParen(exp, 1,start, i-1, Map) * placeParen(exp, 1, i+1, end, Map);
			}
			else if (op == '^' ) {
				c += placeParen(exp, 1, start, i-1, Map) * placeParen(exp, 0, i+1, end, Map);
				c += placeParen(exp, 0, start, i-1, Map) * placeParen(exp, 1, i+1, end, Map);
			}
		}
	}


	else{
	
		for (int i = start + 1; i < end; i+=2) {
	
			char op = exp[i];
	
			if (op == '&') {
				c += placeParen(exp, 0, start, i-1, Map) * placeParen(exp, 1, i+1, end, Map);
				c += placeParen(exp, 1, start, i-1, Map) * placeParen(exp, 0, i+1, end, Map);
				c += placeParen(exp, 0, start, i-1, Map) * placeParen(exp, 0, i+1, end, Map);
			}
	
			else if (op == '|')
				c += placeParen(exp, 0, start, i-1, Map) * placeParen(exp, 0, i+1, end, Map);
	
			else if (op == '^' ) {

				c += placeParen(exp, 1, start, i-1, Map) * placeParen(exp, 1, i+1, end, Map);
				c += placeParen(exp, 0, start, i-1, Map) * placeParen(exp, 0, i+1, end, Map);
			}

		}
	}
	Map[key] = c;
	return c;
}

/*9-11*/



int main(int argc, char const *argv[]){


	cout << "Hello World" << endl;


	/*9-1*/
	// =====

	/*int n = 36;
	int arr[n+1] = {-1};
	cout<<noOfSteps(n, arr);*/

	/*9-1*/
	// ----





	/*9-2*/
	// ----

	/*int X=3;
	int Y=3;

	vector<vector<int> > a;
	a.resize(X);
	
	for(int i=0; i<X; i++)
		a[i].resize(Y);
	
	cout<<getPath(X-1, Y-1, a)<<'\n';
	a[0][2] = -1;

	//Huddle represented as -1
	vector<vector<int> > b;
	b.resize(X);
	
	for(int i=0; i<X; i++)
		b[i].resize(Y);
	
	b[0][1] = -1;
	cout<<getPath1(X-1, Y-1, b)<<'\n';*/

	/*9-2*/
	//=====







	/*9-3*/
	//-----

	/*int arr[] = {-40, -30, -1, 1, 2, 3, 5, 7, 9, 12, 13};
	cout<<findMagicIndex(arr, 0, 10)<<'\n';

	int arr1[] = {-10, -5, 2, 2, 2, 3, 4, 8, 9, 12, 13};
	cout<<findMagicIndex1(arr1, 0, 10);*/

	/*9-3*/
	//-----





	/*9-4*/
	//------
	/*int arr[] = {1, 2, 3, 6};
	vector<vector<int> > subset;
	printSubset(subset, arr, 3);
	
	for (int i=0; i < subset.size(); i++){
	
		for (int j = 0; j < subset[i].size(); j++){
			cout<<subset[i][j]<<" ";
		}
	
		cout<<'\n';
	}*/
	/*9-4*/






	/*9-5*/
	//------
	/*string str = "abcd";
	vStr res;

	res = printPermutation(str);
	
	for (int i = 0; i < res.size(); ++i) {
	
		cout<<res[i]<<'\n';
	}*/
	/*9-5*/




	/*9-6*/
	/*int n = 3;	
	generateParen("", n, n);*/
	/*9-6*/








	/*9-7*/
	//=====
	
	/*freopen("9_7.in", "r", stdin);
	int m, n;
	
	cin>>m>>n;
	Color **screen = new Color*[m];
	
	for (int i = 0; i < m; ++i) {
	
		screen[i] = new Color[n];
	}
	
	for (int i = 0; i < m; ++i) {
	
		for (int j = 0; j < n; ++j) {
	
			int tmp;
			cin>>tmp;
			screen[i][j] =(Color)tmp;
		}
	}
	
	cout<<"Original Screen"<<'\n';

	for (int i = 0; i < m; ++i) {
	
		for (int j = 0; j < n; ++j) {
	
			cout<<screen[i][j]<<" ";
		}
	
		cout<<'\n';
	}
	
	paintFill(screen, m, n, 3, 2, Bl, Or);
	cout<<"New Screen"<<'\n';
	
	for (int i = 0; i < m; ++i) {
	
		for (int j = 0; j < n; ++j) {
	
			cout<<screen[i][j]<<" ";
		}
		cout<<'\n';
	}*/
	/*9-7*/






	/*9-8*/
	/*int n = 100;
	int count = 0;
	cout<<makeChange(n, 25)<<'\n';
	cout<<makeChange1(n, 25);*/

	// ====
	/*9-8*/




	/*9-9*/
	// ====
	/*vi v;
	vector<int> colums;
	colums.resize(8);
	placeQueens(0, colums, v);
	
	for (int i = 0; i < v.size(); ++i) {
	
		for (int j = 0; j < GRID_SIZE; ++j) {
	
			cout<<v[i][j]<<" ";
		}
		cout<<'\n';
	}*/
	/*9-9*/









	/*9-10*/
	//=====

	/*freopen("9_10.in", "r", stdin);
	int n;

	cin>>n;
	mvb Map;
	vb Boxes, result;
	
	float H, W, D;
	
	for (int i = 0; i < n; ++i) {
	
		cin>>H>>W>>D;
		Boxes.push_back(box(H, W, D));
	}
	
	result = createStack(Map, Boxes, 0);
	
	for (int i = 0; i < result.size(); ++i) {
	
		cout<<result[i].getDepth()<<" ";
	}*/
	/*9-10*/






	/*9-11*/
	//------

	/*mi Map;
	string s = "1&0&1|1";
	cout<<placeParen(s, 1, 0, s.length()-1, Map)<<endl;*/
	/*9-11*/

	return 0;
}