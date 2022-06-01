#include<iostream>
#include<string>

using namespace std;


void dispaly(int arr[], int size ){

    cout << "Hello Display" << endl;

    for (int i = 0; i < size; ++i){

        cout << arr[i] << endl;
    }

    return;
}





int quickSort(int arr[], int left, int right){

	int i = left;
	int j = right;
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

	if (left < j)
		quickSort(arr, left, j);

	if (i < right)
		quickSort(arr, i, right);

}	




int main(int argc, char const *argv[]){

    cout << "Hello Test World" << endl;

    int arr[] = {12,34,5,6,7};

    dispaly(arr, 5);


    quickSort(arr, 0, 4);

    dispaly(arr, 5);
    return 0;
}


