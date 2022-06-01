import java.util.* ;



/*14-6*/
class CircularArray<T> implements Iterable<T> {


	private T[] items;
	private int head = 0;
	
	public CircularArray(int size) {	
		items = (T[]) new Object[size];
	}
	
	private int convert(int index) {
	
		if (index < 0) {
			index += items.length;
		}
	
		return (head + index) % items.length;
	}
	
	public void rotate(int shiftRight) {
	
		head = convert(shiftRight);
	}
	
	public T get(int i) {
	
		if (i < 0 || i >= items.length) {
	
			throw new java.lang.IndexOutOfBoundsException("Index " + i + " is out of bounds");
		}
	
		return items[convert(i)];
	}
	
	public void set(int i, T item) {
		items[convert(i)] = item;
	}
	
	public Iterator<T> iterator() {
		return new CircularArrayIterator<T>(this);
	}

	
	private class CircularArrayIterator<TI> implements Iterator<TI> {

	
		private int current = -1;
		private TI[] items;
		
		public CircularArrayIterator(CircularArray<TI> circularArray) {
			items = circularArray.items;
		}
		
		@Override
		public boolean hasNext() {
			return current < items.length - 1;
		}
		
		@Override
		public TI next() {
			current++;
			TI item = (TI) items[convert(current)];
			return item;
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove is not supported by CircularArray");
		}
	}
}
/*14-6*/



/*question 4-2: desing an algorithm to get
the n-th number from last in a linked list*/
class Result {

	public  Node node;
	public int count;

	public Result( Node n, int c) {

		node = n;
		count = c;
	}
}
/*END of solution 4-2: desing an algorithm 
to getthe n-th number from last in a linked 
list*/




// Node class for ll implementation 
class Node {

	public int data;	

	public Node next; 
	public Node previous; 

	public Node(int data){

		this.data = data;
	}

	public Node (){

	}
	
	public void display(int data ){
		
		System.out.print( Integer.toString(data));
	}
}





public class myLL {
	
	public Node head; 
	public Node tail; 
	public Node current; 
	
	myLL(){
		
		head = null;
		tail = null;
		current = null; 		
	}

	public boolean isEmpty(){
		
		return( head == null );		
	}


	/*qustion: design an algorithm to insert new 
	node to the tail of the linked list and also, 
	if necessary convert it to doubly ll*/
	public void insertToTail( int data) {
		
		Node newNode = new Node(data);
		Node tmp = tail; 

		if ( head == null ){

			head = newNode;
			tail = head;
			head.previous = null;
		}

		else {

			tail.next = newNode;
			tail = newNode;
		}	

		// convert to  doubly ll
		tail.previous = tmp;

		/* // in this way the last insertion will be the head 
		if (head == null){
	
			head = node;
		}

		else {
	
			node.next = head;
			head = node;
		}
		*/		 
	}
	/*END of solution: design an algorithm to 
	insert new node to the tail of the linked 
	list and also, if necessary convert it to 
	doubly ll*/



	public Node removeHead(){
		
		Node headReference = head;
		
		if( !isEmpty() ){

			// move the head upfront 
			head = head.next;
		} 

		else {
			
			System.out.println("Empty LinkedList");
		}

		// this is what replaced 
		return headReference;		
	}





	/*question: design an algorithm to reverse a 
	LinkedList with passing the head as parameter*/
	public static Node reverseLinkedList(Node currentNode){


		Node previousNode=null;  
		Node nextNode;  

		while(currentNode != null){

			nextNode=currentNode.next;    
			currentNode.next=previousNode;  
			previousNode=currentNode;  
			currentNode=nextNode;  
		}  

		return previousNode; 
	 } 
	 /*END of solution: design an algorithm to reverse 
	 a LinkedList with passing the head as parameter*/







	/*question: design an algorithm to print a 
	LinkedList that suppose to work even with 
	loop*/
	public void display(){
		
		Node current = head;
		List<Node> myNodeList = new ArrayList<Node>();

		System.out.println();

		while ( current != null){
			
			System.out.print( current.data );

			// this helps for printing circular linked list 
			if ( myNodeList.contains(current))
				break;

			myNodeList.add(current);

			if (current.next != null)
				System.out.print(" -> ");

			current = current.next;			
		}

		System.out.println();	
	}
	/*END of solution: design an algorithm 
	to print a LinkedList that suppose to 
	work even with loop*/





	
	/*question: design an algorithm to print a LinkedList 
	with it's next and previous elements that suppose to 
	work even with loop*/
	public void displayInDetails(){
				
		Node current = head;
		List<Node> myNodeList = new ArrayList<Node>();

		System.out.println();

		while(current != null){
			
			if ( current.previous != null && current != null && current.next != null )
				System.out.println( current.previous.data + " <- "+ current.data+ " -> "+ current.next.data ) ;

			// for circular doubly linked list 
			if ( myNodeList.contains(current))
				break;

			myNodeList.add(current);
			current = current.next;			
		}

		System.out.println();		
	}
	/*END pf slution: design an algorithm to print a 
	LinkedList with it's next and previous elements 
	that suppose to work even with loop*/








	/*question: design an algorithm to 
	determine the length of linked list*/
	public int getLength(){

		int length = 0;

		if ( this ==  null)
			return -1;

		Node current = this.head;

		while( current != null){
			length += 1;
			current = current.next;
		}

		return length;
	}
	/*ENd of solution: design an algorithm 
	to determine the length of linked list*/







	/*question: design an algorithm to get 
	a node of the linked list with key*/
	public Node getNode( int data){

		Node current = head;
		
		if( !isEmpty() ){
		
			while(current.data != data ){
			
				if (current.next == null){

					return null;
				} 
		
				current = current.next;								
			}			
		} 

		else {

			System.out.println("Empty LinkedList");
		}

		return current;
	}
	/*END of solution: design an algorithm 
	to get a node of the linked list with 
	key*/





	
	/*question: design an algorithm to remove 
	a node of the linked list with given key*/
	public Node removeLink (int data){
		
		Node currentLink = head;
		Node previousLink = head;
		
		while ( currentLink.data != data ){
			
			if (currentLink.next == null){
				
				return null; 				
			} 

			else {
				
				previousLink = currentLink; 
				currentLink = currentLink.next;
			}			

		}
		
		if (currentLink == head){
			
			head = head.next;			
		} 

		else {

			previousLink.next = currentLink.next;			
		}		

		return currentLink;	
	}
	/*END of solution: design an algorithm 
	to remove a node of the linked list with 
	given key*/




	/*question: design an algorithm to reverse a 
	LinkedList based on a given length where output 
	will be as following,

	Inputs:  1->2->3->4->5->6->7->8->NULL and k = 3 
	Output:  3->2->1->6->5->4->8->7->NULL. 
	Inputs:   1->2->3->4->5->6->7->8->NULL and k = 5
	Output:  5->4->3->2->1->8->7->6->NULL*/

	Node reverse(Node head, int k){

       Node current = head;
       Node next = null;
       Node prev = null;
        
       int count = 0;
 

       // with  limit k = 3, after one iteration, 
       // the scene will as as following, 
       // ===================
       // | 1 | 2 |  3 |  4 |
       // |   |   |prev|next|
       // ===================
       // head = 1 in all segments, hence, head.next = prev 
 
       while (count < k && current != null) {

           next = current.next;
           current.next = prev;
           prev = current;
           current = next;

           count++;
       } 

       if (next != null) 
          head.next = reverse(next, k);
 
       // prev is now head of input list
       return prev;
    } 
    /*END of solution: design an algorithm to 
    reverse a LinkedList based on a given length*/ 








    /*question: design an algorithm to create a 
	circular ll w/ circumference of k nodes (w or 
	w/o open end )*/
	public void circularLinkedList(int k) {

		if ( this.getLength() <  k )
			return;

		else {

			// curr is the k-th node from the tail 
			Node curr = nthToLast( head, k );
			tail.next = curr;
		}
	}
	/*END of solution: design an algorithm to 
	create a circular ll w/ circumference of 
	k nodes w or w/o open end */







	/*question: design an algorithm to remove 
	single occurance of an integer from a doubly 
	ll if that exist*/
	public static void removeDoublyLL (Node head, int value){

		Node cur =  head;
		
		while ( cur != null){

			// if the int is found 
			if ( cur.value == value ){

				if ( cur.prev != null){

					cur.prev.next =  cur.next;					
				}
				if ( cur.next != null){

					cur.next.prev = cur.prev;
				}
				break;
			}
			cur = cur.next; 		
		}
	}
	/*END of solution: design an algorithm to 
	remove single occurance of an integer from 
	a doubly ll if that exist*/









    /*question 2-1: design an algorithm to remove 
    the duplicates from an unsorted linked list*/

    /*solution-a*/
	public static void deleteDups1( Node head) {

		if (head == null) return;	

		Node current = head;

		while (current != null) {

			Node runner = current;

			// move runner till the end 
			while ( runner.next != null ) { 

				if (runner.next.data == current.data) {
					runner.next = runner.next.next;
				} 

				else 
					runner = runner.next;				}
			}

			current = current.next;
		}
	}
	/*END of solution-a*/




	/*solution-b*/
	public static void deleteDups2( Node n) {

		HashSet<Integer> set = new HashSet<Integer>();
		Node previous = null;

		while (n != null) {

			if (set.contains(n.data)) {

				previous.next = n.next;			
			} 

			else {

				set.add(n.data);
				previous = n;
			}

			n = n.next;
		}
	}
	/*END of solution-b*/






	/*solution-c*/
	public static void deleteDups3(Node head) {

		if (head == null) return;
		
		Node previous = head;
		Node current = previous.next;

		while ( current != null ) {

			// Look backwards for dups, and 
			//remove any that you see.			
			Node runner = head;

			while (runner != current) { 

				if (runner.data == current.data) {

					Node tmp = current.next;
					previous.next = tmp;
					current = tmp;

					// what if there is more than one dups ? 
					// the break keeps out of thw while loop 
				    break;
				}

				runner = runner.next;
			}


			// make one step forward for the previous and current 
			if (runner == current) {

				previous = current;
		        current = current.next;
			}

		} 
	}
	/*END of solution-c*/

	/*END of solution 2-1: design an algorithm 
	to remove the duplicates from an unsorted 
	linked list*/









	/*question 4-2: desing an algorithm to get
	the n-th number from last in a linked list*/

	/*solution - a*/
	public static Node nthToLast1( Node head, int n) {

		Node p1 = head;
		Node p2 = head;
		
		if (n <= 0) return null;
		
		// Move p2 n nodes into the list.  
		// Keep n1 in the same position.
		for (int i = 0; i < n - 1; i++) { 
		
			p2 = p2.next;
			if (p2 == null) return null;
		}

		// move p2 in the end of the ll, then, 
		// p1 will be the nth node from the last
		while ( p2.next != null ) {

			p1 = p1.next;
			p2 = p2.next;
	  	}

	  	return p1;
	}
	/*END of solution - a*/







	/*solution - b*/
	// using recursion to find the n-th node from the end 
	public static Node nthToLast2( Node head, int k) {

		Result res = nthToLast2Helper(head, k);

		if (res != null) {

			return res.node;
		}

		return null;
	}	

	// using recursion to find the node 
	public static Result nthToLast2Helper( Node head, int k) {

		if (head == null) {

			return new Result(null, 0);
		}

		Result res = nthToLast2Helper( head.next, k);

		if ( res.node == null ) {

			res.count++;

			if ( res.count == k) {

				res.node = head;
			} 
		}

		return res;		
	}	
	/*END of solution - b*/






	/*solution - c*/
	public static int nthToLast3( Node head, int n) {

		if (n == 0 || head == null) {

			return 0;
		}

		int k = nthToLast3( head.next , n ) + 1;

		if (k == n) {

			System.out.println(n + "th to last node is " + head.data);
		}

		return k;
	}	
	/*END solution - c*/

	/*END solution 4-2: desing an algorithm to get
	the n-th number from last in a linked list*/






	/*question 2-3 :  design an algorithm to delete a node 
	to middle of a singly linked list given only access to 
	that node*/ 
	// assumption: 'n' is not the tail 

	public static boolean deleteNode( Node n) {

		if (n == null || n.next == null) {

			return false; // Failure
		} 

		Node next = n.next; 

		n.data = next.data; 
		n.next = next.next; 

		return true;
	}
	/*END solution 2-3 :  design an algorithm to delete a node 
	to middle of a singly linked list given only access to 
	that node*/ 








	
	/*question 2-4 : desing an algorithm to add 
	values in two linked list Input keeping the 
	length as same, 

	Input: (3 -> 1 -> 5) + (5 -> 9 -> 2) 
	Output: 8 -> 0 -> 8*/	

	public int addListsHelper(Node first, Node second){

		int addvalue = 0 ;

		if( first != null ){

			addvalue += first.data;
		}

		if ( second != null ){

			addvalue += second.data; 
		}

		return addvalue;
	}


	private  void addLists( myLL l1, myLL l2) {

		if ( l1 == null && l2 == null ) {
             return;
		}

		int carry = 0 ;

		Node curr1 = l1.head;
		Node curr2  =  l2.head; 

		// Node res1 = result.head;

		int [] fresult = null; 

		while ( curr1 != null || curr2 != null) {

			int ll = addListsHelper( curr1 , curr2 );

			System.out.println( "carry = " + carry );
			int tt = ( ll + carry ) % 10;
			// carry =0;

			if ( ll >= 10){

				carry = 1;
			}
				 
			curr1 = curr1.next;
			curr2 = curr2.next;

			System.out.println(" "+ tt );
		}

	}
	/*ENd of solution 2-4: desing an algorithm 
	to add values in two linked list Input 
	keeping the length as same*/








	/*question 2-5 : design an algorithm to find 
	the head of the circular linked list*/
	public static Node findBeginning(Node head) {

		Node slow = head;
		Node fast = head; 
		
		// if the ll is circular, they will meet over the ll
		while (fast != null && fast.next != null ) { 

			slow = slow.next; 
			fast = fast.next.next;

			if (slow == fast) {	
				break;
			}					
		}

		if (fast == null || fast.next == null) {
			return null;
		}

		slow = head; 

		while (slow != fast) { 

			slow = slow.next; 
			fast = fast.next; 
		}
		
		// Both now point to the start of the loop.
		return fast;
	}
	/*question 2-5 : design an algorithm to find 
	the head of the circular linked list*/




	
	public static void main(String[] args) {


		/*14-6*/
		int size = 10;
		CircularArray<String> array = new CircularArray<String>(size);
		for (int i = 0; i < size; i++) {
			array.set(i, String.valueOf(i));
		}
		
		array.rotate(3);
		for (int i = 0; i < size; i++) {
			System.out.println(array.get(i));
		}
		
		System.out.println("");
		
		array.rotate(2);
		for (String s : array) {
			System.out.println(s);
		}
		/*14-6*/


		/*question :  Implement Hash Tables chaining with Singly Linked Lists*/
		int size = 10; 
        HashTable ht = new HashTable(size);
 
 		for (int j = 0; j < size; j++ ){
 			ht.insert( j+3 );
 		}
 		
 		htcsll.print(); 
 		/* END solution test ; Implement Hash Tables chaining with Singly Linked Lists */
		






		/*LinkedList myLL = new LinkedList();

		int length = 10;
		// insert values ( int ) to the tail of the linked list 
		for ( int j=0; j < length; j++ ){

			myLL.insertToTail( j*2 );
		}
		
		myLL.display();	
		System.out.println();*/

		/* display the linked list with it's previous, current and the next values */
		/*myLL.displayInDetails();	
		System.out.println( );*/


		// int listlength = 100;
		// int k = 10;
		

		// LinkedListNode[] nodes = new LinkedListNode[listlength];
		// for (int i = 0; i < listlength; i++) {
		// 	nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
		// }


		/*int nToLast = 4;
		System.out.println( myLL.nthToLastR3( myLL.head, nToLast ).data );
		int test =  myLL.nthToLastR1( myLL.head, nToLast );*/


		/* question 2-1 */
		/* remove the duplicates from an unsorted linked list */
		/*myLL.deleteDupsC( myLL.head);
		myLL.display();	
		System.out.println();*/

		/* find the node with it's int value */
		/*
		int getNode = 12;
		System.out.println( myLL.getNode(getNode).data );
		*/

		/* question 2-3 */
		/* write an algorithm to delete a node to middle of a singly linked list given only access to that node */
		/*int deleteNode = 12;
		myLL.deleteNode( myLL.getNode(deleteNode) );
		myLL.display();
		System.out.println();*/


		/* question 2-4 */
		/*		
		System.out.println("the length of the linked list = "+ myLL2.getLength( ) );
		myLL.addLists(myLL1, myLL2 );	
		*/


		/* question 2-5 */
		/* find the head of the circular linked list */
		/*myLL.circularLinkedList( 15 );
		myLL.display();	
		System.out.println();

		if ( myLL.findBeginning( myLL.head ) == null ){
			System.out.println("No cycle");
		}
		else
			System.out.println( "head of the loop is = "+ myLL.findBeginning( myLL.head ).data );*/


		/* Write a function to remove a single occurance of an integer from a doubly linked list 
		if it's present. */
	}
	
}