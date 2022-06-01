#include<iostream>
#include<string>
#include<climits>
#include<cmath>

using namespace std;


/* 5-1*/
int insertBit(int N, int M, int i, int j){

    int allOnes = ~0;
    int left = allOnes << (j+1);
    int right = (1<<i)-1;
    int mask = left|right;
    int clr = N & mask;
    int shifted = M<<i;
    return shifted | clr;
}
/*END of 5-1*/





/*5-2*/
string decimalToBinary(double a) {

    if(a<0 || a>= 1)
        return "ERROR!";

    string res="";
    res = res + "0.";

    while(a>0) {

        if(res.length()>32){
            return "ERROR!";
        }

        a *=2;

        if(a>=1){
            res =res+"1";
            a -=1.0;
        }

        else{
            res = res+"0";
        }
    }
    return res;
}
/*END of 5-2*/







/* 5-3*/
void next_smallest(int n){

	int len = 0;
	int tmp = n;
	int res;

	while(len<31){

		if((tmp&1) == 0){

			tmp >>= 1;
			len++;

			if(tmp&1 == 1){

				//10 occured
				res = n - (1<<len) + (1<<(len-1)); 
				printBinary(res);
				return;
			}
		}

		else{
			tmp >>=1;
			len++;
		}
	}

	//All 1's
	cout<<INT_MIN<<'\n';
}

void next_largest(int n){

	int len = 0;
	int tmp = n;
	int res;

	while (len<31) {

		if(tmp&1 == 1){

			tmp >>= 1;
			len++;

			if((tmp&1) == 0){
				//01 occured
				res = n + (1<<len) - (1<<(len-1));
				printBinary(res);
				return;
			}
		}

		else {
			tmp >>=1;
			len++;
		}
	}

	cout<<INT_MAX;
}
/* END of 5-3*/





/* 5-5*/
int noOfBits(int a, int b) {

	int len = 32;
	int count=0;

	while(len--){

		if((a&1) != (b&1)) {
			count++;
		}
			
		a >>=1;
		b >>=1;
	}
	
	return count;
}

int noOfBits1(int a, int b){

	int count = 0;

	for(int i=a^b; i != 0; i >>=1){
		count += (i&1);
	}

	return count;
}
/*END of 5-5*/







/*5-6*/
int swapOddEven(int a){

	int mask = 0xaaaaaaaa;	//.....10101010
	int mask1 = 0x55555555;	//.....01010101
	int oddBits = a&mask;	//Store odd bits
	int evenBits = a&mask1;	//Store even bits

	oddBits >>=1;
	evenBits <<=1;			//Swaping possitions
	return (oddBits|evenBits);
}
/*END of 5-6*/




/*5-7*/

int fetchBit(int bit, int no){
	no >>=bit;
	return (no&1);
}

int findMissingUtils(int A[], int n, int col){

	if(n < 2){
		return 0;
	}

	int b0[(n/2)+1], b1[(n/2)+1];
	int count0 = 0;
	int count1 = 0;

	for(int i=0; i<n; i++) {

		if(fetchBit(col, A[i])){
			b1[count1] = A[i];
			count1++;
		}

		else{
			b0[count0] = A[i];
			count0++;
		}
	}

	int v;

	if(count0<=count1){
		v = findMissingUtils(b0, count0, col+1);
		return (v<<1) | 0;
	}

	else
		v = findMissingUtils(b1, count1, col+1);
		return (v<<1) | 1;
}

void findMissing(int A[], int n){
	cout<<findMissingUtils(A, n, 0);
}
/*5-7*/




/*===================*/
void printBinary(int n){

    int arr[32];
    int len = 8*sizeof(n);
    int mask = 1;
    int index = 0;

    while(len--){

        if(n&mask)
            arr[index] = 1;
        else
            arr[index] = 0;
        index++;
        mask <<= 1;
    }

    for(int i=31; i>=0; i--){
        cout<<arr[i];
    }
    cout<<'\n';
}

/*===================*/








int main(int argc, char const *argv[]){
	
	cout << "Hello Bitwsie" << endl;


	/*5-1*/

	/*int N, M, i, j;

    N = 1<<10;      //10000000000
    M = 19;         //10011
    i=2;
    j=6;
    printBinary(N);
    printBinary(M);
    int res = insertBit(N, M, i, j);
    printBinary(res);*/
	/*END of 5-1*/




    /*5-2*/

    /*double a = 0.125;
    cout<<decimalToBinary(a)<<'\n';*/
    /*END of 5-2*/



    /*5-3*/

    /*int a = (1<<31)+(1<<29);
	cout<<a<<'\n';
	printBinary(a);
	next_smallest(a);
	next_largest(a);*/
    /*5-3*/




    /*5-5*/

	int a = 9456;
	int b = 12000;
	cout<<noOfBits1(a, b);
    /*5-5*/


	/*5-6*/

	int a = 0xaaaaaaa;
	printBinary(a);
	printBinary(swapOddEven(a));
	/*5-6*/



	/*5-7*/
	int A[] = {2, 5, 6, 0, 1, 3, 4, 8, 9, 10, 11, 12};
	findMissing(A, 12);
	/*5-7*/

	return 0;
}