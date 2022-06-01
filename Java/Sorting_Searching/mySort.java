import java.util.*;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;




/*question 9-2 : write a method to sort an array 
of strings so that all the anagrams are next to 
each other*/
class AnagramComparator implements Comparator<String> {

	
	public String sortChars(String s) {

		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
	
    public int compare(String s1, String s2) {
    	return sortChars(s1).compareTo(sortChars(s2));
    }
}
/*ENd of solution 9-2 : write a method to sort 
an array of strings so that all the anagrams 
are next to each other*/






/*question 9-6(b): Given a matrix in which each 
row and each column is sorted, write a method to 
find an element in it*/
class Coordinate implements Cloneable {

	public int row;
	public int column;

	public Coordinate(int r, int c) {
		row = r;
		column = c;
	}
	
	public boolean inbounds(int[][] matrix) {

		return 	row >= 0 &&
				column >= 0 &&
				row < matrix.length &&
				column < matrix[0].length;
	}
	
	public boolean isBefore(Coordinate p) {
		return row <= p.row && column <= p.column;
	}
	
	public Object clone() { 
		return new Coordinate(row, column);
	}
	
	public void moveDownRight() {
		row++;
		column++;
	}
	
	public void setToAverage(Coordinate min, Coordinate max) {
		row = (min.row + max.row) / 2;
		column = (min.column + max.column) / 2;
	}
}
/*END fo solution 9-6 (b): Given a matrix in which 
each row and each column is sorted, write a method 
to find an element in it.*/



/*question: design an algorithm to 
implement merge sort using ll*/
class Node {


    public int item;
    public Node next;
 
    public Node(int val) {
        this.item = val;
    }
 
    public Node(){

    }
 
    public void displayNode(){
        System.out.print("[" + item + "] ");
    }
}

 
class myMergesort {

    private Node first;
 
    public myLinkedList(){
        first = null;
    }
 
    public boolean isEmpty(){
        return (first == null);
    }
 
    public void insert(int val){

        Node newNode = new Node(val);
        
        // add in the back of the queue 
        newNode.next = first;
        first = newNode;
    }
 
    public void append( Node result ){
        first = result;
    }
 
    public void display(){

        Node current = first;
        
        while (current != null){
            current.displayNode();
            current = current.next;
        }

        System.out.println("");
    }

    // get the end of the queue  
    public Node extractFirst(){
        return first;
    }

 	// this MergeSort returns the head of the sorted LL
    public Node MergeSort(Node headOriginal) {

        if (headOriginal == null || headOriginal.next == null ){
        	return headOriginal;
        }

        Node a = headOriginal;
        Node b = headOriginal.next;

        // split the linked list with two parts
        while ((b != null ) &&  ( b.next != null)){
            headOriginal = headOriginal.next;
            b = (b.next).next;
        }

        b = headOriginal.next;
        headOriginal.next = null;
        
        return merge( MergeSort(a), MergeSort(b));
    }
 
    public Node merge(Node a, Node b) {

        Node head = new Node();
        Node c = head;

        while ((a != null) && (b != null)) {

            if ( a.item <= b.item ){
                c.next = a;
                c = a;
                a = a.next;
            }

            else {
                c.next = b;
                c = b;
                b = b.next;
            }
        }

        // define the last element of the ll 
        c.next = (a == null) ? b : a;
        return head.next;        
    }    
}
/*END of solution: design an algorithm to 
implement merge sort using ll*/









/*question: design a program 
to implement heap sort*/
class Heap {

    private Heap(){ }

    public static void sort(Comparable[] pq) {

        int N = pq.length;
        
        for (int k = N/2; k >= 1; k--){
            sink(pq, k, N);
        }
            
        while (N > 1) {
            exch(pq, 1, N--);
            sink(pq, 1, N);
        }
    }
   
    // Helper functions to restore the heap invariant
    private static void sink(Comparable[] pq, int k, int N) {

        while (2*k <= N) {

            int j = 2*k;
            if (j < N && less(pq, j, j+1)) 
                j++;

            if (!less(pq, k, j)) 
                break;

            exch(pq, k, j);
            k = j;
        }
    }

    /*Helper functions for comparisons and swaps. Indices 
    are "off-by-one" to support 1-based indexing*/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
    /*Check if array is sorted - useful for debugging*/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; heapsorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void testHeapSort() {

        String[] a = {12,34,5,67};
        Heap.sort(a);
        show(a);
    }
}
/*END of solution: design a program 
to imeplement heap sort*/




public class mySort {




    /*
    * radix sort is a non-comparative integer sorting algorithm that 
    * sorts data with integer keys by grouping keys by the individual 
    * digits which share the same significant position and value. A 
    * positional notation is required, but because integers can represent 
    * strings of characters (e.g., names or dates) and specially formatted 
    * floating point numbers, radix sort is not limited to integers. 
    */

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    public static void radixsort(int arr[], int n){

        // Find the maximum number to know number of digits
        int m = getMax(arr, n);
 
        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10){
            countSort(arr, n, exp);
        }
    }

    // A utility function to get maximum value in arr[]
    public static int getMax(int arr[], int n){

        int mx = arr[0];

        for (int i = 1; i < n; i++){

            if (arr[i] > mx){
                mx = arr[i];
            }
        }
        return mx;
    }
 
    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    public  static void countSort(int arr[], int n, int exp){

        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        
        Arrays.fill(count,0);
 
        // Store count of occurrences in count[]
        for (i = 0; i < n; i++){
            count[ (arr[i]/exp)%10 ]++;
        }
 
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++){
            count[i] += count[i - 1];
        }
 
        // Build the output array
        for (i = n - 1; i >= 0; i--){

            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }
 
        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++){
            arr[i] = output[i];
        }
    }
 
    // A utility function to print an array
    public static void print(int arr[], int n){

        for (int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }
    }








	/*question: design an algorithm 
	to implement binary search*/	

	/*solution-a, recursive version*/ 
	public int binarySearch(int[] a, int x) { 
	   return binarySearch( a, x, 0, a.length - 1 );
	}

	private int binarySearch(int[ ] a, int x, int low, int high) {

        if ( a == null || a.length == 0){
            return -1;
        }

        if (low > high) {
            return -1; 
        }
            
        int mid = (low + high)/2;

        if (a[mid] == x) {
            return mid;
        }
            
        else if (a[mid] < x){
            return binarySearch(a, x, mid+1, high);
        }
        
        else { // last possibility: a[mid] > x
            return binarySearch( a, x, low, mid-1 );
        }             
	}
	/*END of solution-a*/



	/*solution-b, iterative version*/
	public int binarySearch1(int[] a, int x) {

        int low = 0;
        int high = a.length - 1;

        while (low <= high) {

            int mid = (low + high)/2;

            if (a[mid] == x) {
                return mid;
            }

            else if (a[mid] < x) {
                low = mid + 1;
            }

            else 
                high = mid - 1;
        }

		return -1;
	}
	/*END of solution-b*/

	/*END of solution: design an algorithm 
	to implement binary search*/







	/*question: design an algorithm 
	to implement selection sort*/

	// time complexity: O(n^2)
    // bring the samllest elemenst in the beginning one by one 
    // move forward to the end 
    public static int[] selectionSort(int arr[]){

        int N = arr.length;
        int i, j, pos, temp;

        for (i = 0; i < N - 1 ; i++) {

            pos = i;

            for (j = i+1; j < N; j++) {

                if ( arr[j] < arr[pos] ){
                    pos = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[pos];
            arr[pos]= temp;

            // if (pos != i) {
            //     arr[i] ^= arr[pos];
            //     arr[pos] ^= arr[i];
            //     arr[i] ^= arr[pos];
            // }            
        }

        return arr;         
    }
	/*END of solution: design an algorithm 
	to implement selection sort*/









	/*question: design an algorithm 
	to implement bubble sort*/

	// time complexity:  O(n) best case, 
    // O(n^2) in average and worst 
    // space complexity: O(n)

    // place the bigger values one-by-one in the end 
    // and move backward to the beginning 
    public static int[] bubbleSort( int[] arr ) {


        for (int i = 0; i < arr.length; i++){

            // put the largest number in the end of the 
            // array and continue the operation 
        	for (int j = 0; j < arr.length - 1; j++){

            	if (arr[j] > arr[j + 1]) {

                    // arr[j] = arr[j] + arr[j + 1];
                    // arr[j + 1] = arr[j] - arr[j + 1];
                    // arr[j] = arr[j] - arr[j + 1];

                    arr[j]   ^= arr[j+1];
                    arr[j+1] ^= arr[j];
                    arr[j]   ^= arr[j+1];
                }
            }
        }  

        return arr;
    }
	/*END of solution: design an algorithm 
	to implement bubble sort*/








    /*question: design an algorithm 
    to implement insertion sort*/

    /*END of solution: design an algorithm 
    to implement insertion sort*/








	/*question: Design an algorithm 
	to implement quick sort*/

    // Time complexity  O(n log n) best and average cases , 
    //  O(n^2) in the worst case
    // Space complexity: O(n log n)

	/*Pick a random element and partition the array, such that 
	all numbers that are less than it come before  and all elements 
	that are greater than it comes after that element. Then, do that 
	for each half, then each quarer, etc.*/

	/*solution -a*/
    // BETTER VERSION 
	public static void quickSort (int arr[], int left, int right) {

		int index = partition(arr, left, right);

		if (left < index - 1){
		    quickSort(arr, left, index - 1);
        }

		if (index < right){
		    quickSort(arr, index, right);
        }
	}


	public static int partition(int arr[], int left, int right) {

		int i = left, j = right;
		int tmp;

		/* Math.random() returns a number from 0.0 to 1.0
		 Math.random()*n returns a number between 0 to (n -1) 
		(int)(Math.random() * 101); return in the range of
		0 to 100*/

        // Math.random()*(n+1) returns a number between "1 to n"
		Random rand = new Random();

		// select a number randomly between 
        // left and right (inclusive)


        // rand.nextInt(n)
        // The method call returns a pseudorandom, uniformly 
        // distributed int value between 0 and (n-1)         

		int pivotIndex = left + rand.nextInt( right - left + 1);
		int pivot = arr[ pivotIndex ]; 

		while(i <= j){

		    while ( arr[i] < pivot) {
                i++;
            }
		          
		    while (arr[j] > pivot){
                j--;
            }
		          
		    // necessary condition 
		    if (i <= j) {

                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;

                // if (i != j) {
                //     arr[i] ^= arr[j];
                //     arr[j] ^= arr[i];
                //     arr[i] ^= arr[j];
                // }

                i++;
                j--;
		    }
		}

		return i;
	}	
	/*END solution - a*/





    /*solution-b*/
    public static void quickSort1( int [] arr){
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort1( int[] arr, int left, int right ) {

        int index = partition(arr, left, right);

        // we already sorted based on the index
        if(left < index) {
            quickSort(arr, left, index-1);
        }
        
        if(right > index) {
            quickSort( arr, index+1, right);
        }             
    }


    /*
    * we get the arr[right] in the index 
    * of i and return the index-i
    */
    public static int partition1(int[] arr, int left, int right) {

        int value = arr[right], i = left;

        for (int j = left; j < right; j++){

            if( arr[j] < value){
                arr[j] = swap(arr[i], arr[i] = arr[j]);
                i++;
            }
        }

        arr[i] = swap(arr[right], arr[right] = arr[i]);
        return i; 
    }


    public static int swap1( int real, int dummy ){
        return real; 
    }
    /*END of solution-b*/


	/*solution -c*/
    public static int N = 20;
    public static int[] sequence = new int[N];
 
    public static void QuickSort2(int left, int right) {

        if (right - left <= 0) {
        	return;
        }

        else {

            Random rand = new Random();

            // rand.nextInt(n)
            // The method call returns a pseudorandom, uniformly 
            // distributed int value between 0 and (n-1) 
            int pivotIndex = left + rand.nextInt(right - left + 1);

            // swap changes the values of the array elements 
            swap( pivotIndex, right );

            int pivot = sequence[right];
            int index = partitionIt( left, right, pivot);
 
            QuickSort(left, index - 1);
            QuickSort( index + 1, right);
        }
    }

 
    public static int partition2(int left, int right, long pivot) {

        int leftPtr = left - 1;
        int rightPtr = right;
        
        while (true) {

            while (sequence[++leftPtr] < pivot);

            // pivot is the element in the right index
            // so, we won't consider that in the while loop
            // and, rightPtr starts from (right-1) position
            while (rightPtr > 0 && sequence[--rightPtr] > pivot);

            // left == right if the array size is odd and 
            // all the elements left of median and right of median 
            // satisfies the condition  
            if (leftPtr >= rightPtr) 
            	break;

            else 
            	swap(leftPtr, rightPtr);
        }

        // finally, swap with the elments in the right index: pivot 
        swap(leftPtr, right);
        return leftPtr;
    }
 
    public static void swap2(int dex1, int dex2) {

        int temp = sequence[dex1];

        sequence[dex1] = sequence[dex2];
        sequence[dex2] = temp;
    }
    /*END solution - c*/


	/*END of solution: design an algorithm 
	to implement quick sort*/









	/*question: design an algorithm 
	to implement merge sort*/

	/*Sort each pair of elements. Then, sort every four elements 
	by merging every two pairs. Then, sort every 8 elements, etc.*/
	// time complexity:  O(n log n) usual, worst case: O(n log n)
    // space complexity: O(n)
	public static int[] mergeSort( int[] a, int low, int high ) {


        int N = high - low;         

        if ( N <= 1 ) {
        	return a; 
        }

        int mid = (low + high)/2; 

        mergeSort(a, low, mid); 
        mergeSort(a, mid, high); 

        int[] temp = new int[ N ];
        int i = low, j = mid;

        for (int k = 0; k < N; k++){

            if (i == mid){  
                temp[k] = a[j++];
            }

            else if (j == high) { 
                temp[k] = a[i++];
            }    

            else if ( a[j] < a[i] ) {
                temp[k] = a[j++];
            } 
                
            else {
                temp[k] = a[i++];
            }                 
        }    

        for (int k = 0; k < N; k++) {
        	a[low + k] = temp[k];
        }

        return a;                      
    }
	/*END of solution: design an algorithm 
	to implement merge sort*/






	/*question: design an program 
	to implement bucket sort*/

	/*Partition the array into a finite number of buckets, 
	and then sort each bucket individually.*/ 
    // time complexity : O(n + m) best and average cases 
    // where n = number of items and m = number of distinct items
    // in worst case O(n^2)
    // space complexity O(n)


    // A = [5, 4, 2]
	static int[] bucketSort(int[] a){

        // As we start to count from 0, we need max +1 to accomodate
        //  max value in the bucket
        int maxVal = IntStream.of(a).max().getAsInt();

        int [] bucket = new int[ maxVal+1 ];

        for (int i = 0; i < a.length; i++){        
            bucket[a[i]]++;
        }
        
        int index = 0;
        
        for (int i = 0; i<bucket.length; i++){


            //                if(j == 0 && bucket[i] == 0){
//                    System.out.println("Will not enter inside");
//                }

            System.out.println("[i, j] = " + i + " " + j);

            for (int j=0; j<bucket[i]; j++){
                a[index++] = i;
            }
        }

        return a; 
    }
 

 
	//get the maximum value of the array, then, 
	// pass inside the bucketSort method 
    static int maxValue(int[] arr) {


        // 1. convert int[] to Integer[] 
        // 2. convert Integer[] to List<Integer>

        /*List<Integer> lis = Arrays.asList(Arrays.stream(arr).boxed().toArray(Integer[]::new));
        return Collections.max(lis);
        */ 

        int maxValue = 0;

        for ( int i = 0; i < arr.length; i++ ){            
            if ( arr[i] > maxValue ){
                maxValue = arr[i];
            }
        }
        return maxValue;
    }
	/*END of solution: design an algorithm 
	to implement bucket sort*/






	/*question: design an algorithm 
	to implement heapsort*/

	/*An array sorted from lowest to highest is a min-heap when using 
	the array-based heap implementation. The heap property that the 
	parent node is greater than it's child nodes (2i + 1 and 2i + 2, 
	using zero-based arrays) holds for all nodes that have children.

	The minimum value of a max heap is in one of the leaf nodes, but 
	you don't know which. Since the minimum node cannot, by definition, 
	have any child nodes, it must be a leaf. The heap property, however, 
	does not specify how leaf nodes compare with each other, only with 
	their parent.*/

	// time complexity: O(n log(n)) best, average and worst
    // space complexity: O(1) worst case 


	private static int total;

	public static void heapSort(Comparable[] arr){

        total = arr.length - 1;

        for (int i = total / 2; i >= 0; i--){
            heapify(arr, i);
        }

        for (int i = total; i > 0; i--) {
            doSwap(arr, 0, i);
            total--;
            heapify(arr, 0);
        }
    }

    private static void heapify(Comparable[] arr, int i) {

        int lft = i * 2;
        int rgt = lft + 1; // (i*2 + 1)
        int grt = i;

        if (lft <= total && arr[lft].compareTo(arr[grt]) > 0) grt = lft;
        if (rgt <= total && arr[rgt].compareTo(arr[grt]) > 0) grt = rgt;
        
        if (grt != i) {

            doSwap(arr, i, grt);
            heapify(arr, grt);
        }
    }


    // change the value of the array elements 
    private static void doSwap(Comparable[] arr, int a, int b){

        Comparable tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
	/*END of solution: design an 
	algorithm to implement heapsort*/




	/*question 9-1: design an algorithm to merge 
	two sorted array, the large one comes with same 
	size buffer in the end to hold the small one*/

	// a[] is the larger array
	// b[] is the smaller array 
	public static void merge( int[] a, int[] b, int lastA, int lastB ) {

		int indexMerged = lastB + lastA - 1;

		int indexA = lastA - 1; 
		int indexB = lastB - 1; 

		while ( indexB >= 0 ) {

			if ( indexA >= 0 && a[indexA] > b[indexB] ) { 
				a[indexMerged] = a[indexA]; 
				indexA--; 
			} 

			else {
				a[indexMerged] = b[indexB]; 
				indexB--;
			}

			indexMerged--; 		
		}

	}


	public static String arrayToString( int[] array ) {

		StringBuilder sb = new StringBuilder();

		for (int v : array) {
			sb.append( v + ", ");
		}

		return sb.toString();
	}
	/*END of solution 9-1: design an algorithm to merge 
	two sorted array, the large one comes with same size 
	buffer in the end to hold the small one*/








	/*question 9-2 : write a method to sort an array of strings 
	so that all the anagrams are next to each other*/
	public static void sort( String[] array ) {

		Hashtable< String, LinkedList<String>> hash = new Hashtable< String, LinkedList<String> >();
		
		for ( String s : array ) {

			String key = sortChars(s); 

			if (!hash.containsKey(key)) {
				hash.put( key, new LinkedList<String>() );
			}

			LinkedList<String> anagrams = hash.get(key);
			anagrams.push(s);
		}
		
		int index = 0;

		for (String key : hash.keySet()){

			LinkedList<String> list = hash.get(key);

			for ( String t : list ) {
				array[index] = t;
				index++;
			}
		}
	}

	public static String sortChars(String s) {

		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
		// return String.valueOf(content);
	}

	/*END of solution 9-2 : write a method to sort an 
	array of strings so that all the anagrams are next 
	to each other*/







	/*question 9-3 : a sorted array of n integers that has 
	been rotated an unknown number of times, give an O(log n) 
	algorithm that finds an element in the array.*/

	public static int search( int a[], int left, int right, int x) {

		int mid = (left + right) / 2;

		if (x == a[mid] ) { // Found element			
			return mid;
		}

		if (right < left) {			
			return -1;
		}

		if (a[left] < a[mid]) { // Left is normally ordered.

			if ( x >= a[left] && x <= a[mid] ) { 
				return search( a, left, mid - 1, x );
			} 

			else {
				return search(a, mid + 1, right, x);
			}
		} 

		else if ( a[mid] < a[left] ) { // Right is normally ordered.

			if ( x >= a[mid] && x <= a[right] ) {
				return search( a, mid + 1, right, x );
			} 

			else {
				return search(a, left, mid - 1, x);
			}				
		} 

		else if (a[left] == a[mid] ) { 

			if (a[mid] != a[right]) { 
				return search(a, mid + 1, right, x);
			} 

			else { // Else, we have to search both halves

				int result = search(a, left, mid - 1, x); 

				if (result == -1) {
					return search( a, mid + 1, right, x ); 
				} 
				else {
					return result;
				}
			}
		}
		return -1;
	}

	/*END of solution 9-3 : a sorted array of n integers that 
	has been rotated an unknown number of times, give an O(log n) 
	algorithm that finds an element in the array.*/







	/*question 9-5: design an algorithm to find the index 
	of a given string from a sorted array of strings with 
	empty strings*/

	/*solution - a*/
    // using iteration 
	public static int searchI( String[] strings, String str, int first, int last) {

		while ( first <= last ) {

			int mid = (last + first) / 2;			

            // if the mid Stirng of the array is empty.
            // find the nearest non-empty String to the mid 
			if ( strings[mid].isEmpty() ) { 

                // left and right of the mid 
				int left = mid - 1;
				int right = mid + 1;

				while (true) {

					if ( left < first && right > last) {
						return -1;
					} 

					else if (right <= last && !strings[right].isEmpty()) {
						mid = right;
						break;
					} 

					else if ( left >= first && !strings[left].isEmpty() ) {
						mid = left;
						break;
					}

                    // <-- || -->
					right++;
					left--;
				}
			}
			
			int res = strings[mid].compareTo(str);

			// compareTo return 0 when a match found 
			if (res == 0) { 
				return mid;
			} 

			// Search right
			else if (res < 0) { 
				first = mid + 1;
			} 

			// Search left
			else { 
				last = mid - 1;
			}
		}

		return -1;
	}	
	/*END of solution - a*/




	/*solution - b*/
    // using recursion 
    public static int search1( String[] strings, String str) {

        if ( strings == null || str == null || str.isEmpty() ) {
            return -1;
        }

        return searchR(strings, str, 0, strings.length - 1);
    }

	public static int searchR ( String[] strings, String str, int first, int last ) {

		if (first > last) {
			return -1;
		}

		int mid = (last + first) / 2;
		
		if ( strings[mid].isEmpty() ) { 

			int left = mid - 1;
			int right = mid + 1;

			while (true) {

				if ( left < first && right > last ) {
					return -1;
				}

				else if ( right <= last && !strings[right].isEmpty() ) {

					mid = right;
					break;
				} 

				else if (left >= first && !strings[left].isEmpty()) {
					mid = left;
					break;
				}

				right++;
				left--;
			}
		}

		if ( str.equals(strings[mid]) ) { // Found it!
			return mid;
		} 


		/* one.compareTo(two) == 0 if one == two 
		big.compareTo(small) > 0
		small.compareTo(big) < 0*/

		else if (strings[mid].compareTo(str) < 0) { // Search right
			return searchR( strings, str, mid + 1, last );
		} 

		else { // Search left

			return searchR( strings, str, first, mid - 1 );
		}
	}	
	/*END of solution - b*/


	/*END of solution 9-5: design an algorithm to find the 
	index of a given string from a sorted array of strings
	with empty strings*/








	/*question 9- 6: Given a matrix in which each 
	row and each column is sorted, write a method 
	to find an element in it.*/

	// assumption: matrix is sorted in ascending  
	// order from left to right and top to bottom

	/*solution - a*/
	public static boolean findElement( int[][] matrix, int elem ) {
		
		int row = 0;
		int col = matrix[0].length - 1; 

		while ( row < matrix.length && col >= 0 ) {

			if ( matrix[row][col] == elem ) {

				return true;
			} 

			else if ( matrix[row][col] > elem) {
				col--;
			} 

			else {
				row++; 
			}
		} 

		return false; 
	}
	/*END of solution - a*/


	/*solution - b*/
	public static Coordinate findElement1(int[][] matrix, int x) {

		Coordinate origin = new Coordinate(0, 0);
		Coordinate dest = new Coordinate(matrix.length - 1, matrix[0].length - 1);
		return findElement1(matrix, origin, dest, x);
	}

	public static Coordinate findElement1(int[][] matrix, Coordinate origin, Coordinate dest, int x) {

		if (!origin.inbounds(matrix) || !dest.inbounds(matrix)) {
			return null;
		}

		if (matrix[origin.row][origin.column] == x) {
			return origin;
		} 

		else if (!origin.isBefore(dest)) {
			return null;
		}
		
		/*Set start to start of diagonal and end to 
		the end of the diagonal. Since the grid may 
		not be square, the end of the diagonal may 
		not equal dest*/

		Coordinate start = (Coordinate) origin.clone();
		int diagDist = Math.min(dest.row - origin.row, dest.column - origin.column);
		Coordinate end = new Coordinate(start.row + diagDist, start.column + diagDist);
		Coordinate p = new Coordinate(0, 0);
		
		/*Do binary search on the diagonal, looking 
		for the first element greater than x*/
		while (start.isBefore(end)) {

			p.setToAverage(start, end);

			if ( x > matrix[p.row][p.column] ) {

				start.row = p.row + 1;
				start.column = p.column + 1;
			} 

			else {

				end.row = p.row - 1;
				end.column = p.column - 1;
			}
		}
		
		/*Split the grid into quadrants. Search 
		the bottom left and the top right.*/ 
		return partitionAndSearch(matrix, origin, dest, start, x);
	}

	public static Coordinate partitionAndSearch(int[][] matrix, Coordinate origin, 
		                                  Coordinate dest, Coordinate pivot, int elem) {
		
		Coordinate lowerLeftOrigin = new Coordinate(pivot.row, origin.column);
		Coordinate lowerLeftDest = new Coordinate(dest.row, pivot.column - 1);

		Coordinate upperRightOrigin = new Coordinate(origin.row, pivot.column);
		Coordinate upperRightDest = new Coordinate(pivot.row - 1, dest.column);
		
		Coordinate lowerLeft = findElement1(matrix, lowerLeftOrigin, lowerLeftDest, elem);

		if (lowerLeft == null) {

			return findElement1(matrix, upperRightOrigin, upperRightDest, elem);
		}

		return lowerLeft;
	}
	
	/*END of solution - b*/

	/*END fo solution 9- 6: Given a matrix in which each 
	row and each column is sorted, write a method to find 
	an element in it.*/





    /*question 9-7: design an algorithm to compute the 
	largest possible number of people in the tower with 
	descending weight and height*/

	static class Property implements Comparable {


        private int Height;
        private int Weight;

        public Property(int h, int w) {
            this.Height = h;
            this.Weight = w;
        }

        public int compareTo(Object o) {

            Property other = (Property) o;

            if (this.Height != other.Height) {
                return ((Integer) this.Height).compareTo(other.Height);
            } else {
                return ((Integer) this.Weight).compareTo(other.Weight);
            }
        }

        public boolean isBefore(Property other) {

            if (this.Height < other.Height && this.Weight < other.Weight) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return "Property{" +
                    "Height=" + Height +
                    ", Weight=" + Weight +
                    '}';
        }
    }


    /**
     * Design an algorithm to compute the largest possible
     * number of people in the tower with descending weight
     * and height
     */
    public static ArrayList<Property> initialize() {


        ArrayList<Property> people = new ArrayList<Property>();

        Property item = new Property(65, 60);
        people.add(item);

        item = new Property(70, 150);
        people.add(item);

        item = new Property(56, 90);
        people.add(item);

        item = new Property(75, 190);
        people.add(item);

        item = new Property(60, 95);
        people.add(item);

        item = new Property(68, 110);
        people.add(item);

        item = new Property(35, 65);
        people.add(item);

        item = new Property(40, 60);
        people.add(item);

        item = new Property(45, 63);
        people.add(item);

        return people;
    }


    public static ArrayList<Property> getIncreasingSequence(ArrayList<Property> items) {

        Collections.sort(items);
        return longestIncreasingSubsequence(items);
    }

    private static ArrayList<Property> longestIncreasingSubsequence(ArrayList<Property> people) {

        ArrayList<Property>[] updatedPeople = new ArrayList[people.size()];
        longestIncreasingSubsequence(people, updatedPeople, 0);

        ArrayList<Property> bestSequence = null;

        for (int i = 0; i < people.size(); i++) {
            bestSequence = largerSequence(bestSequence, updatedPeople[i]);
        }

        return bestSequence;
    }


    private static void longestIncreasingSubsequence(ArrayList<Property> people, ArrayList<Property>[] updatedPeople, int currentindex) {


        if (currentindex < 0 || currentindex >= people.size()) {
            return;
        }

        Property currentelement = people.get(currentindex);

        // Find longest sequence that we can append currentelement to
        ArrayList<Property> bestsequence = null;

        for (int i = 0; i < currentindex; i++) {

            // If currentelement is bigger than list tail
            // Set bestsequence to our new max
            if (people.get(i).isBefore(currentelement)) {
                bestsequence = largerSequence(bestsequence, updatedPeople[i]);
            }
        }

        // Append currentelement
        ArrayList<Property> newsolution = new ArrayList<Property>();

        if (bestsequence != null) {
            newsolution.addAll(bestsequence);
        }

        newsolution.add(currentelement);

        // Add to list and recurse
        updatedPeople[currentindex] = newsolution;
        longestIncreasingSubsequence(people, updatedPeople, currentindex + 1);
    }


    // Returns longer sequence
    private static ArrayList<Property> largerSequence(ArrayList<Property> seq1, ArrayList<Property> seq2) {

        if (seq1 == null) {
            return seq2;
        } else if (seq2 == null) {
            return seq1;
        }

        return seq1.size() > seq2.size() ? seq1 : seq2;
    }

    public static void printList(ArrayList<Property> list) {

        for (Property item : list) {
            System.out.println(item.toString() + " ");
        }
    }
	/*END of solution 9-7: design an algorithm to compute 
	the largest possible number of people in the tower with 
	descending weight and height*/
	



	public static void main(String[] args) {

		/* question 9-1 */
		/*You are given two sorted arrays, A and B, and A has a 
		large enough buffer at the end to hold B. Write a method 
		to merge B into A in sorted order.*/

		/*
		int[] a = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
		int[] b = {1, 4, 7, 6, 7, 7};
		merge(a, b, 8, 6);
		System.out.println( arrayToString(a));
		*/






		/* question 9-2 */
		/*Write a method to sort an array of strings so that all the anagrams 
		are next to each other.*/

		/*
		String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
		Arrays.sort(array, new AnagramComparator());
		*/





		/* question 9-3 */
		/*int[] a = { 2, 3, 2, 2, 2, 2, 2, 2 , 2 , 2 };

		System.out.println(search(a, 0, a.length - 1, 2));
		System.out.println(search(a, 0, a.length - 1, 3));
		System.out.println(search(a, 0, a.length - 1, 4));
		System.out.println(search(a, 0, a.length - 1, 1));
		System.out.println(search(a, 0, a.length - 1, 8));*/

		/* end of question 9-3 */



		/* question 9-5 */
		/*String[] stringList = {"apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower"};

        System.out.println( "ac is in the list ?" + search1(stringList, "ac"));
        System.out.println("carrot is in the list ? "+ search1(stringList, "carrot"));*/
		/* end question 9-5 */






		/* question 9-6 */
		/* 
		Given a matrix in which each row and each column is 
		sorted, write a method to find an element in it.
		*/

		/* solution - A question 9-6 */
		/*int M = 10;
		int N = 5;
		int[][] matrix = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = 10 * i + j;
			}
		}
		
		printMatrix(matrix);
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				int v = 10 * i + j;
				System.out.println(v + ": " + findElement(matrix, v));
			}
		}*/
		/* END solution - A question 9-6 */

		/* solution - B question 9-6 */

		/*int[][] matrix = {{15, 30,  50,  70,  73}, 
				 	 	  {35, 40, 100, 102, 120},
				 	 	  {36, 42, 105, 110, 125},
				 	 	  {46, 51, 106, 111, 130},
				 	 	  {48, 55, 109, 140, 150}};
	
		printMatrix(matrix);
		int m = matrix.length;
		int n = matrix[0].length;
		
		int count = 0;
		int littleOverTheMax = matrix[m - 1][n - 1] + 10;
		for (int i = 0; i < littleOverTheMax; i++) {
			Coordinate c = findElement1(matrix, i);
			if (c != null) {
				System.out.println(i + ": (" + c.row + ", " + c.column + ")");
				count++;
			}
		}
		System.out.println("Found " + count + " unique elements.");*/
		/* END - solution - B question 9-6 */







		/* question 9-7 : design an algorithm to compute the largest possible number of peo- ple in the tower. */
		/*
			ArrayList<HtWt> items = initialize();
			ArrayList<HtWt> solution = getIncreasingSequence(items);
			printList(solution);
		*/
		/* END solution test 9-7 */





		/* bubble sort*/

		/*System.out.println("Sorting of randomly generated numbers using BUBBLE SORT");

        Random random = new Random();
        int N = 20;
        int[] sequence = new int[N];
 
        for (int i = 0; i < N; i++)
            sequence[i] = Math.abs(random.nextInt(1000));
 
        System.out.println("\nOriginal Sequence: ");
        printSequence(sequence);
 
        System.out.println("\nSorted Sequence: ");
        printSequence(bubbleSort(sequence));*/
		/* END bubble sort*/






		/* bucket sort*/
		/*System.out.println("Sorting of randomly generated numbers using BUCKET SORT");
        Random random = new Random();
        int N = 20;
        int[] sequence = new int[N];
 
        for (int i = 0; i < N; i++)
            sequence[i] = Math.abs(random.nextInt(100));
 
        int maxValue = maxValue(sequence);
 
        System.out.println("\nOriginal Sequence: ");
        printSequence(sequence);
 
        System.out.println("\nSorted Sequence: ");
        printSequence(bucketSort(sequence, maxValue));
        System.out.println("\n\n");*/

		/* END of bucket sort */






		/* merge sort*/
		/*Random random = new Random();
        int N = 10;
        int[] sequence = new int[N];
 
        for (int i = 0; i < N; i++)
            sequence[i] = Math.abs(random.nextInt(1000));
 
        System.out.println("\nOriginal Sequence: ");
        printSequence(sequence);
 
        System.out.println("\nSorted Sequence: ");
        printSequence(mergeSort(sequence, 0, sequence.length ));  
        System.out.println("\n\n");*/
		/* END of merge sort */





		/* merge sort using linked list */
		/* myMergesort object = new myMergesort();
        Random random = new Random();
        int N = 20;
        for (int i = 0; i < N; i++)
            object.insert(Math.abs(random.nextInt(100)));
 
        System.out.println("List items before sorting :");
        object.display();
        object.append(object.MergeSort(object.extractFirst()));
        System.out.println("List items after sorting :");
        object.display();*/
		/* END merge sort using linkedlist */




		/* quck sort*/
		/*System.out.println("Sorting of randomly generated numbers using RANDOMIZED QUICK SORT");
        Random random = new Random();
 
        for (int i = 0; i < N; i++)
            sequence[i] = Math.abs(random.nextInt(100));
 
        System.out.println("\nOriginal Sequence: ");
        printSequence(sequence);
        System.out.println("\n Quick Sorted Sequence: ");

        QuickSort(0, N - 1);
        printSequence(sequence);
        System.out.println("\n\n");*/
		/* END of quic sort*/







		// selection sort

		/*Random random = new Random();
        int N = 10;
        int[] sequence = new int[N];
 
        for (int i = 0; i < N; i++)
            sequence[i] = Math.abs(random.nextInt(1000));
 
        System.out.println("\nOriginal Sequence: ");
        printSequence(sequence);
 
        System.out.println("\nSorted Sequence: ");
        printSequence(selectionSort(sequence)); */
		/* END selection sort */
	
	}
}
