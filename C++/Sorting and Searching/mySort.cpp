#include <iostream>
#include <string>
#include <cmath>
#include <map>
#include <vector>


using namespace std;

// various sorting methods 
template <class T>
void Swap(T &a, T &b){

	T tmp = a;
	a = b;
	b = tmp;
}


// Runtime average and worst case O(n2). Memory O(1)
template <class U>
void bubbleSort(U &arr, int low, int high){

	if (high <= low) {	
		return;
	}
	
	int noOfElements = high - low + 1;
	
	for (int i = 0; i < noOfElements-1; ++i) {
	
		for (int j = low; j < high; ++j) {
	
			if (int(arr[j]) > int(arr[j+1])) {

				Swap(arr[j], arr[j+1]);
			}
		}
	}
}

// insertion sort 
void insertionSort(int arr[], int size){

	int j, temp;

	for(int i = 1; i < size; i++){

		j = i;

		while(j > 0 && arr[j-1] > arr[j]){

			temp = arr[j];
			arr[j] = arr[j-1];

			arr[j-1] = temp;
			j--; 
		}
	}
}

void display(int arr[] , int size){

	for(int j = 0;j < size; j++){
		cout << arr[j] << " ";
	}

	cout << endl;
}

void insertionSortTest(){

	const int size =10;
	int numbers[size];

	srand(unsigned(time(0)));

	for(int i = 0; i < size; i++){
		numbers[i] = (rand() % 100)+ 1;
		cout << numbers[i] << " ";
	}

	cout<< endl;

	insertionSort(numbers, size);
	display(numbers, size);

	return; 
}




// Runtime average and worst case O(n2). Memory O(1)
void selectionSort(int arr[], int low, int high){

	if (high <= low) {
		return;
	}

	int noOfElements = high - low + 1;

	for (int i = 0; i < noOfElements-1; ++i) {
		
		int minIndex = low+i;
		
		for (int j = i+low; j <= high; ++j) {
		
			if (arr[minIndex] > arr[j]) {
				minIndex = j;
			}
		}
		
		Swap(arr[i+low], arr[minIndex]);
	}
}

#include <iostream>
#include <string>
#include <cmath>
#include <map>
#include <vector>



using namespace std;


// various sorting methods 

template <class T>
void Swap(T &a, T &b){

	T tmp = a;
	a = b;
	b = tmp;
}


// Runtime average and worst case O(n2). Memory O(1)
template <class U>
void bubbleSort(U &arr, int low, int high){

	if (high <= low) {	
		return;
	}
	
	int noOfElements = high - low + 1;
	
	for (int i = 0; i < noOfElements-1; ++i) {
	
		for (int j = low; j < high; ++j) {
	
			if (int(arr[j]) > int(arr[j+1])) {
				Swap(arr[j], arr[j+1]);
			}
		}
	}
}



// insertion sort 
void insertionSort(int arr[], int size) {

	int j, temp;

	for(int i = 1; i < size; i++) {

		j = i;

		while(j > 0 && arr[j-1] > arr[j]){

			temp = arr[j];
			arr[j] = arr[j-1];

			arr[j-1] = temp;
			j--; 
		}
	}
}


void display(int arr[] , int size){

	for(int j = 0;j < size; j++){
		cout << arr[j] << " ";
	}

	cout << endl;
}


void insertionSortTest(){

	const int size =10;
	int numbers[size];

	srand(unsigned(time(0)));

	for(int i = 0; i < size; i++){

		numbers[i] = (rand() % 100)+ 1;
		cout << numbers[i] << " ";
	}

	cout<< endl;

	insertionSort(numbers, size);
	display(numbers, size);

	return; 
}




// Runtime average and worst case O(n2). Memory O(1)
void selectionSort(int arr[], int low, int high){

	if (high <= low) {
		return;
	}

	int noOfElements = high - low + 1;

	for (int i = 0; i < noOfElements-1; ++i) {
		
		int minIndex = low+i;
		
		for (int j = i+low; j <= high; ++j) {
		
			if (arr[minIndex] > arr[j]) {
				minIndex = j;
			}
		}
		
		Swap(arr[i+low], arr[minIndex]);
	}
}







// this one is not working and returning segmentation fault 

/*question: design an algorithm 
to implement quick sort*/
int partition(int arr[], int left, int right);
void quickSort (int arr[], int left, int right) {

	int index = partition( arr, left, right );

	if ( left < index - 1 )
	    quickSort(arr, left, index - 1);

	if (index < right)
	    quickSort(arr, index, right);
}


int partition(int arr[], int left, int right){

	int i = left, j = right;
	int tmp;

	int pivotIndex = left + rand()%(right- left + 1) +left;
	int pivot = arr[ pivotIndex ]; 

	while ( i <= j ) {

	    while ( arr[i] < pivot )
	          i++;

	    while (arr[j] > pivot)
	          j--;

	    if (i <= j) {

	          tmp = arr[i];
	          arr[i] = arr[j];
	          arr[j] = tmp;

	          i++;
	          j--;
	    }
	}

	return i;
}	
/*END of solution: design an algorithm 
to implement quick sort*/





// this one is not working and returning segmentation fault 

/*question: design an algorithm 
to implement quick sort*/
int partition(int arr[], int left, int right);
void quickSort (int arr[], int left, int right) {

	int index = partition( arr, left, right );

	if ( left < index - 1 )
	    quickSort(arr, left, index - 1);

	if (index < right)
	    quickSort(arr, index, right);
}


int partition(int arr[], int left, int right){

	int i = left, j = right;
	int tmp;

	int pivotIndex = left + rand()%(right- left + 1) +left;
	int pivot = arr[ pivotIndex ]; 

	while ( i <= j ) {

	    while ( arr[i] < pivot )
	          i++;

	    while (arr[j] > pivot)
	          j--;

	    if (i <= j) {

	          tmp = arr[i];
	          arr[i] = arr[j];
	          arr[j] = tmp;

	          i++;
	          j--;
	    }
	}

	return i;
}	

/*END of solution: design an algorithm 
to implement quick sort*/










/*question: design an algorithm 
	to implement merge sort*/

/*Sort each pair of elements. Then, sort every four elements 
by merging every two pairs. Then, sort every 8 elements, etc.*/
// time complexity:  O(n log n) usual, worst case: O(n log n)
// space complexity: O(n)


// Java implementation of merge Sort
// ---------------------------------

/*public static int[] mergeSort( int[] a, int low, int high ) {

    int N = high - low;         

    if ( N <= 1 ) 
    	return a; 

    int mid = (low + high)/2; 

    mergeSort( a, low, mid); 
    mergeSort( a, mid, high); 

    int[] temp = new int[ N ];
    int i = low, j = mid;

    for (int k = 0; k < N; k++){

        if (i == mid)  
            temp[k] = a[j++];

        else if (j == high) 
            temp[k] = a[i++];

        else if ( a[j] < a[i] )  
            temp[k] = a[j++];

        else 
            temp[k] = a[i++];
    }    

    for (int k = 0; k < N; k++) {

    	a[low + k] = temp[k];
    }

    return a;                      
}*/
/*END of solution: design an algorithm 
to implement merge sort*/


// Runtime average and worst case O(n log(n)). Memory Depends
void merge(int arr[], int low, int middle, int high){

	int helper[high-low+1];
	
	for (int i = low; i <= high; ++i) {
	
		helper[i-low] = arr[i];
	}
	
	int i=low, j=middle+1, k=low;
	
	while(i<=middle and j<=high){
	
		if (helper[i-low] < helper[j-low]) {
			arr[k] = helper[i-low];
			i++;
		}
	
		else {
			arr[k] = helper[j-low];
			j++;
		}
	
		k++;
	}
	
	if (i>middle) {
	
		for (; j <= high; ++j) {
	
			arr[k] = helper[j-low];
			k++;
		}
	}
	
	else {
		for (; i <= middle; ++i) {
			arr[k] = helper[i-low];
			k++;
		}
	}
}



void mergeSort(int arr[], int low, int high){

	if (high <= low) {
		return;
	}
	
	int noOfElements = high - low + 1;
	int middleElement = (high+low)/2;
	
	mergeSort(arr, low, middleElement);
	mergeSort(arr, middleElement+1, high);
	merge(arr, low, middleElement, high);
}


// Runtime average: O(n log(n)) and worst case O(n2). Memory O(log(n))
int partition(int arr[], int low, int high) {

	int pivot = arr[int((low+high)/2)];

	while(low <= high) {

		while(arr[low] < pivot)
			low++;

		while(arr[high] > pivot) 
			high--;

		if(low <= high) {
			Swap(arr[low], arr[high]);
			low++;
			high--;
		}
	}
	return low;
}

void quickSort(int arr[], int low, int high) {
	int index = partition(arr, low, high);
	if (low<index-1)
		quickSort(arr, low, index-1);
	if (index<high)
		quickSort(arr, index, high);
}

// Runtime average: Depends and worst case O(log(n)). Memory O(1)
int binarySearch(int arr[], int low, int high, int x) {
	while(high >= low) {
		int middle = (low+high)/2;
		if (arr[middle] > x)
			high = middle-1;
		else if (arr[middle] < x)
			low = middle+1;
		else
			return middle;

	}
	return -1;
}
// various sorting 



/*question: design an algorithm 
to implement merge sort*/

/*Sort each pair of elements. Then, sort every four elements 
by merging every two pairs. Then, sort every 8 elements, etc.*/
// time complexity:  O(n log n) usual, worst case: O(n log n)
// space complexity: O(n)



// Java implementation of merge Sort
// ---------------------------------

/*public static int[] mergeSort( int[] a, int low, int high ) {

    int N = high - low;         

    if ( N <= 1 ) 
    	return a; 

    int mid = (low + high)/2; 

    mergeSort( a, low, mid); 
    mergeSort( a, mid, high); 

    int[] temp = new int[ N ];
    int i = low, j = mid;

    for (int k = 0; k < N; k++){

        if (i == mid)  
            temp[k] = a[j++];

        else if (j == high) 
            temp[k] = a[i++];

        else if ( a[j] < a[i] )  
            temp[k] = a[j++];

        else 
            temp[k] = a[i++];
    }    

    for (int k = 0; k < N; k++) {

    	a[low + k] = temp[k];
    }

    return a;                      
}*/
/*END of solution: design an algorithm 
to implement merge sort*/



// Runtime average and worst case O(n log(n)). Memory Depends
void merge(int arr[], int low, int middle, int high) {

	int helper[high-low+1];
	
	for (int i = low; i <= high; ++i) {
	
		helper[i-low] = arr[i];
	}
	
	int i=low, j=middle+1, k=low;
	
	while(i<=middle and j<=high){
	
		if (helper[i-low] < helper[j-low]) {
			arr[k] = helper[i-low];
			i++;
		}
	
		else {
			arr[k] = helper[j-low];
			j++;
		}
	
		k++;
	}
	
	if (i>middle) {
	
		for (; j <= high; ++j) {
	
			arr[k] = helper[j-low];
			k++;
		}
	}
	
	// else condition 
	else {
		for (; i <= middle; ++i) {
			arr[k] = helper[i-low];
			k++;
		}
	}
}


void mergeSort(int arr[], int low, int high){

	if (high <= low) {
		return;
	}
	
	int noOfElements = high - low + 1;
	int middleElement = (high+low)/2;
	
	mergeSort(arr, low, middleElement);
	mergeSort(arr, middleElement+1, high);
	merge(arr, low, middleElement, high);
}


// Runtime average: O(n log(n)) and worst case O(n2). Memory O(log(n))
int partition(int arr[], int low, int high) {

	int pivot = arr[int((low+high)/2)];

	while(low <= high) {

		while(arr[low] < pivot)
			low++;

		while(arr[high] > pivot) 
			high--;

		if(low <= high) {
			Swap(arr[low], arr[high]);
			low++;
			high--;
		}
	}
	return low;
}


void quickSort(int arr[], int low, int high) {

	int index = partition(arr, low, high);

	if (low<index-1)
		quickSort(arr, low, index-1);

	if (index<high)
		quickSort(arr, index, high);
}


// Runtime average: Depends and worst case O(log(n)). Memory O(1)
int binarySearch(int arr[], int low, int high, int x) {

	while(high >= low) {

		int middle = (low+high)/2;
		if (arr[middle] > x)
			high = middle-1;
		else if (arr[middle] < x)
			low = middle+1;
		else
			return middle;
	}

	return -1;
}
// various sorting 



/*11-1*/
void merge(int a[], int lastA, int b[], int lastB) {

	int index = lastB + lastA + 1;

	while(index >= 0) {

		if (a[lastA] > b[lastB]) {
			a[index] = a[lastA];
			lastA--;
		}
		
		else {
			a[index] = b[lastB];
			lastB--;
		}

		index--;
	}
}
/*11-1*/




/*11-2*/
typedef map<string, vector<string> > mvs;

void sortAnagramWise(string arr[], int n){

	mvs Map;
	
	for(int i=0; i<n; i++) {	
		string tmp = arr[i];
		bubbleSort(arr[i], 0, arr[i].length()-1);
		Map[arr[i]].push_back(tmp);
	}
	
	mvs::iterator iter = Map.begin();

	int ind=0;

	for (; iter != Map.end(); ++iter) {
	
		vector<string> tmp = iter->second;

		for (int i = 0; i < tmp.size(); ++i) {
			arr[ind]=tmp[i];
			ind++;
		}
	}
}
/*11-2*/



/*11-1*/
void merge(int a[], int lastA, int b[], int lastB) {

	int index = lastB + lastA + 1;

	while(index >= 0) {

		if (a[lastA] > b[lastB]) {
			a[index] = a[lastA];
			lastA--;
		}

		else {
			a[index] = b[lastB];
			lastB--;
		}
		index--;
	}
}
/*11-1*/




/*11-2*/
typedef map<string, vector<string> > mvs;

void sortAnagramWise(string arr[], int n){

	mvs Map;

	for(int i=0; i<n; i++) {
		string tmp = arr[i];
		bubbleSort(arr[i], 0, arr[i].length()-1);
		Map[arr[i]].push_back(tmp);
	}
		mvs::iterator iter = Map.begin();
	int ind=0;
	for (; iter != Map.end(); ++iter) {
		vector<string> tmp = iter->second;
		for (int i = 0; i < tmp.size(); ++i) {
			arr[ind]=tmp[i];
			ind++;
		}
	}
}

/*11-2*/









/*11-3*/
int search(int arr[], int low, int high, int x) {


	int mid = (low+high)/2;
	
	if(arr[mid]==x)
		return mid;
	
	if (low > high)
		return -1;
	
	if(arr[low] < arr[mid]) {
	
		if(x <= arr[mid] && x >= arr[low])
			return search(arr, low, mid-1, x);
	
		else
			return search(arr, mid+1, high, x);
	}
	
	else if(arr[mid] < arr[high]){
	
		if (x >= arr[mid] && x <= arr[high])
			return search(arr, mid+1, high, x);
	
		else
			return search(arr, low, mid-1, x);
	}
	
	else if(arr[low] == arr[mid]) {
	
		if(arr[mid] != arr[high])
			return search(arr, mid+1, high, x);
		else {
			int result = search(arr, low, mid-1, x);
			if (result == -1)
				return search(arr, mid+1, high, x);
			else
				return -1;
		}
	}
	return -1;
}
/*11-3*/



/*11-5*/

int search1 (string arr[], int low, int high, string str){

	int mid = (low+high)/2;

	if(arr[mid]==str)
		return mid;

	if(low >= high)
		return -1;

	if(arr[mid] == ""){

		int i=1;
		while(mid-i >= low || mid+i <= high){

			if(mid-i >= low && arr[mid-i] != "" && arr[mid-i] >= str)
				return search1(arr, low, mid-i, str);
			
			if(mid+i <= high && arr[mid+i] != "" && arr[mid+i] <= str)
				return search1(arr, mid+i, high, str);
			
			i++;
		}
	}

	else {

		if (arr[mid] < str)
			return search1(arr, mid+1, high, str);
		
		else
			return search1(arr, low, mid-1, str);
	}

	return -1;
}

/*11-5*/



/*11-3*/
int search(int arr[], int low, int high, int x) {

	int mid = (low+high)/2;
	
	if(arr[mid]==x)
		return mid;
	
	if (low > high)
		return -1;
	
	if(arr[low] < arr[mid]) {
	
		if(x <= arr[mid] && x >= arr[low])
			return search(arr, low, mid-1, x);
	
		else
			return search(arr, mid+1, high, x);
	}
	
	else if(arr[mid] < arr[high]){
	
		if (x >= arr[mid] && x <= arr[high])
			return search(arr, mid+1, high, x);
	
		else
			return search(arr, low, mid-1, x);
	}
	
	else if(arr[low] == arr[mid]) {
	
		if(arr[mid] != arr[high])
			return search(arr, mid+1, high, x);
		else {
			int result = search(arr, low, mid-1, x);
			if (result == -1)
				return search(arr, mid+1, high, x);
			else
				return -1;
		}
	}
	return -1;
}
/*11-3*/



/*11-5*/
int search1 (string arr[], int low, int high, string str){

	int mid = (low+high)/2;

	if(arr[mid]==str)
		return mid;

	if(low >= high)
		return -1;

	if(arr[mid] == ""){

		int i=1;

		while(mid-i >= low || mid+i <= high){

			if(mid-i >= low && arr[mid-i] != "" && arr[mid-i] >= str)
				return search1(arr, low, mid-i, str);
			
			if(mid+i <= high && arr[mid+i] != "" && arr[mid+i] <= str)
				return search1(arr, mid+i, high, str);
			
			i++;
		}
	}

	else {

		if (arr[mid] < str) {
			return search1(arr, mid+1, high, str);
		}
		
		else {
			return search1(arr, low, mid-1, str);
		}
			
	}

	return -1;
}

/*11-5*/


/*11-6*/
bool search2 (int *arr, int M, int N, int ele){

	int row = 0;
	int col = N-1;
	
	while(row < M && col >= 0) {
	
		if (*(arr+row*N+col) == ele){	
			return true;
		}
	
		else if (*(arr+row*N+col) > ele) {
			col--;
		}

		else {
			row++;
		}
	}
	return false;
}


bool binarySearch2(int *arr, int MStart, int NStart, int MEnd, 
	int NEnd, int N, int ele) {
	
	int MMid = (MStart+MEnd)/2;
	int NMid = (NStart+NEnd)/2;
	int midEle = *(arr+MMid*N+NMid);
	
	if (midEle == ele){
		return true;
	}
		
	if (MStart >= MEnd && NStart >= NEnd)
		return false;
	
	bool a = false, b=false, c=false, d=false;

	// if element in first partition
	if (*(arr+MStart*N+NStart) <= ele && *(arr+MMid*N+NMid) >= ele) {
		a = binarySearch2(arr, MStart, NStart, MMid, NMid, N, ele);
	}
	
	// if element in second partition
	if (*(arr+MStart*N+NMid+1) <= ele && *(arr+MMid*N+NEnd) >= ele) {
		
		b = binarySearch2(arr, MStart, NMid+1, MMid, NEnd, N, ele);
	}
	
	// if element in third partition
	if (*(arr+(MMid+1)*N+NStart) <= ele && *(arr+MEnd*N+NMid) >= ele) {
		
		c = binarySearch2(arr, MMid+1, NStart, MEnd, NMid, N, ele);
	}
	
	// if element in forth partition
	if (*(arr+(MMid+1)*N+NEnd) <= ele && *(arr+MEnd*N+NEnd) >= ele) {
		
		d = binarySearch2(arr, MMid+1, NEnd, MEnd, NEnd, N, ele);
	}
	
	return (a+b+c+d);
}
/*11-6*/


/*11-6*/
bool search2 (int *arr, int M, int N, int ele){

	int row = 0;
	int col = N-1;
	
	while(row < M && col >= 0) {
	
		if (*(arr+row*N+col) == ele){
			return true;
		}
	
		else if (*(arr+row*N+col) > ele) {
			col--;
		}

		else {
			row++;
		}
	}
	return false;
}



bool binarySearch2(int *arr, int MStart, int NStart, int MEnd, 
	int NEnd, int N, int ele) {
	
	int MMid = (MStart+MEnd)/2;
	int NMid = (NStart+NEnd)/2;
	int midEle = *(arr+MMid*N+NMid);
	
	if (midEle == ele)
		return true;
	
	if (MStart >= MEnd && NStart >= NEnd)
		return false;
	
	bool a=false, b=false, c=false, d=false;

	// if element in first partition
	if (*(arr+MStart*N+NStart) <= ele && *(arr+MMid*N+NMid) >= ele) {

		a = binarySearch2(arr, MStart, NStart, MMid, NMid, N, ele);
	}
	
	// if element in second partition
	if (*(arr+MStart*N+NMid+1) <= ele && *(arr+MMid*N+NEnd) >= ele) {
		
		b = binarySearch2(arr, MStart, NMid+1, MMid, NEnd, N, ele);
	}
	
	// if element in third partition
	if (*(arr+(MMid+1)*N+NStart) <= ele && *(arr+MEnd*N+NMid) >= ele) {
		
		c = binarySearch2(arr, MMid+1, NStart, MEnd, NMid, N, ele);
	}
	
	// if element in forth partition
	if (*(arr+(MMid+1)*N+NEnd) <= ele && *(arr+MEnd*N+NEnd) >= ele) {
		
		d = binarySearch2(arr, MMid+1, NEnd, MEnd, NEnd, N, ele);
	}
	
	return (a+b+c+d);
}
/*11-6*/








/* question 11-7*/
struct htWt {

	int height, weight;
};


void sortHeight(htWt arr[], int n) {

	for (int i=0; i<n; i++) {

		for (int j=0; j<n-1; j++){

			if (arr[j].height>arr[j+1].height) {

				htWt tmp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = tmp;
			}

		}
	}
}


int getIncreasingSequence(htWt arr[], int n) {

	sortHeight(arr, n);
	vector<vector<int> > wt;
	
	for (int i=0; i<n; i++) {

		bool flag = true;

		for (int j=0; j<wt.size(); j++) {

			if (arr[i].weight >= wt[j].back()) {
				flag = false;
				wt[j].push_back(arr[i].weight);
			}
		}

		if (flag) {

			vector<int> v;
			v.push_back(arr[i].weight);
			wt.push_back(v);
		}
	}

	int max = 0;

	for (int i=0; i<wt.size(); i++) {

		if (max < wt[i].size())

			max = wt[i].size();
	}

	return max;
}
/*11-7*/


/* question 11-7*/
struct htWt {
	int height, weight;
};


void sortHeight(htWt arr[], int n) {

	for (int i=0; i<n; i++) {

		for (int j=0; j<n-1; j++){

			if (arr[j].height>arr[j+1].height) {

				htWt tmp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = tmp;
			}

		}
	}
}


int getIncreasingSequence(htWt arr[], int n) {

	sortHeight(arr, n);
	vector<vector<int> > wt;
	
	for (int i=0; i<n; i++) {

		bool flag = true;

		for (int j=0; j<wt.size(); j++) {

			if (arr[i].weight >= wt[j].back()) {
				flag = false;
				wt[j].push_back(arr[i].weight);
			}
		}

		if (flag) {

			vector<int> v;
			v.push_back(arr[i].weight);
			wt.push_back(v);
		}
	}

	int max = 0;

	for (int i=0; i<wt.size(); i++) {

		if (max < wt[i].size())

			max = wt[i].size();
	}

	return max;
}
/*11-7*/



/*11-8*/
struct node {

	int data;
	node* right;
	node* left;
	int leftSize;
};

void track(int k, node* &root){

	if (root == NULL) {

		root = new node;
		root->data = k;
		root->right = root->left = NULL;
		root->leftSize = 0;
	}

	else {

		if (root->data > k) {
			root->leftSize++;
			track(k, root->left);
		}

		else {
			track(k, root->right);
		}
	}
}

int getRank(int x, node* root) {

	int data = root->data;
	
	if (data == x)
		return root->leftSize;
	
	if (data > x)
		return getRank(x, root->left);
	
	else {
		return root->leftSize + 1 + getRank(x, root->right);
	}
}
/*11-8*/



int main(int argc, char const *argv[]){

	cout << "Hello Sort and Search" << endl;

	/*11-1*/
	
	/*int a[13] = {0, 4, 14, 23, 29, 30, 31};
	int b[] = {6, 7, 29, 31, 100, 200};
	merge(a, 6, b, 5);
	for (int i = 0; i < 13; ++i) {
		cout<<a[i]<<" ";
	}*/
	/*11-1*/

}	
/*11-8*/



struct node {

	int data;

	node* right;
	node* left;

	int leftSize;
};


void track(int k, node* &root){

	if (root == NULL) {

		root = new node;
		root->data = k;
		root->right = root->left = NULL;
		root->leftSize = 0;
	}

	else {

		if (root->data > k) {
			root->leftSize++;
			track(k, root->left);
		}

		else {
			track(k, root->right);
		}
	}
}


int getRank(int x, node* root) {

	int data = root->data;
	
	if (data == x)
		return root->leftSize;
	
	if (data > x)
		return getRank(x, root->left);
	
	else {
		return root->leftSize + 1 + getRank(x, root->right);
	}
}
/*11-8*/



int main(int argc, char const *argv[]){

	cout << "Hello Sort and Search" << endl;

	/*11-1*/
	
	/*int a[13] = {0, 4, 14, 23, 29, 30, 31};
	int b[] = {6, 7, 29, 31, 100, 200};
	merge(a, 6, b, 5);
	for (int i = 0; i < 13; ++i) {
		cout<<a[i]<<" ";
	}*/
	/*11-1*/

	/*11-2*/

	/*string arr[] = {"abc", "bca", "aaaa", "abccde", "edbcac", "cbed", "cdeb"};
	sortAnagramWise(arr, 7);
	for (int i = 0; i < 7; ++i)
		cout<<arr[i]<<'\n';*/
	/*11-2*/
		

	/*11-2*/

	/*string arr[] = {"abc", "bca", "aaaa", "abccde", "edbcac", "cbed", "cdeb"};
	sortAnagramWise(arr, 7);
	for (int i = 0; i < 7; ++i)
		cout<<arr[i]<<'\n';*/
	/*11-2*/


	/*11-3*/

	/*int arr[] = {5, 8, 16, 17, 20, 23, 0, 2, 3, 4};
	cout<<search(arr, 0, 9, 8)<<endl;*/
	/*11-3*/

	/*11-3*/

	/*int arr[] = {5, 8, 16, 17, 20, 23, 0, 2, 3, 4};
	cout<<search(arr, 0, 9, 8)<<endl;*/
	/*11-3*/

	/*11-5*/

	/*string arr[] = {"abc", "", "", "", "ball", "cat", "", "dog", "elephant", "fox", "", "", "", "goat"};
	cout<<search1(arr, 0, 13, "goat")<<'\n'<<endl;
	cout<<search1(arr, 0, 12, "goat")<<'\n'<<endl;*/
	/*11-5*/


	/*11-5*/

	/*string arr[] = {"abc", "", "", "", "ball", "cat", "", "dog", "elephant", "fox", "", "", "", "goat"};
	cout<<search1(arr, 0, 13, "goat")<<'\n'<<endl;
	cout<<search1(arr, 0, 12, "goat")<<'\n'<<endl;*/
	/*11-5*/


	/*11-6*/

	/*int arr[4][5] = {

		{15, 20, 70, 85, 90},
		{25, 35, 80, 95, 100},
		{30, 55, 95, 105, 105},
		{40, 80, 120, 120, 150}
	};

	cout<<search2(*arr, 4, 5, 15)<<'\n'<<endl;
	cout<<binarySearch2(*arr, 0, 0, 3, 4, 5, 95)<<endl;*/
	/*11-6*/


	/*11-6*/

	/*int arr[4][5] = {

		{15, 20, 70, 85, 90},
		{25, 35, 80, 95, 100},
		{30, 55, 95, 105, 105},
		{40, 80, 120, 120, 150}
	};

	cout<<search2(*arr, 4, 5, 15)<<'\n'<<endl;
	cout<<binarySearch2(*arr, 0, 0, 3, 4, 5, 95)<<endl;*/
	/*11-6*/


	/*11-7*/
	// htWt arr[] = {{12, 13}, {11, 15}, {9, 20}, {20, 20}, {40, 21}, {8, 42}};
	// cout<<getIncreasingSequence(arr, 6);
	/*11-7*/

	/*11-7*/
	// htWt arr[] = {{12, 13}, {11, 15}, {9, 20}, {20, 20}, {40, 21}, {8, 42}};
	// cout<<getIncreasingSequence(arr, 6);
	/*11-7*/







	/*11-8*/
	//======
	/* node *root = NULL;
	int arr[] = {12, 15, 12, 11, 10, 10, 11, 10};
	
	for (int i = 0; i < 8; ++i) {
	
		track(arr[i], root);
	}

	cout<<getRank(15, root)<<endl;*/
	/*11-8*/
	// =====
	
	return 0;
}

