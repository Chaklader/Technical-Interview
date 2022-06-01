import java.io.*;
import java.util.*;
import java.util.Stack;
import java.util.stream.*;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/*question : implement a program 
for queueing various animals*/
abstract class Animal {

	private int order; 
	protected String name;
	
	public Animal(String n) {
		name = n;
	}
	
	public abstract String name();
	
	public void setOrder(int ord) {
		order = ord;
	}
	
	public int getOrder() {
		return order;
	}
	
	public boolean isOlderThan(Animal a) {
		return this.order < a.getOrder();
	}
}




class Cat extends Animal {

	public Cat(String n) {
		super(n);
	}
	
	public String name() {
		return "Cat: " + name;
	}
}


class Dog extends Animal {

	public Dog(String n) {
		super(n);
	}
	
	public String name() {
		return "Dog: " + name;
	}
}


class AnimalQueue {

	private int order = 0;

	LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();

	public void enqueue(Animal a) {

		a.setOrder(order);
		order++;

		if (a instanceof Dog) {
			dogs.addLast((Dog) a);
		} 

        else if (a instanceof Cat) {		
        	cats.addLast((Cat)a);
		}
	}
	
	public Animal dequeueAny() {

		if (dogs.size() == 0) {
			return dequeueCats();
		} 

		else if (cats.size() == 0) {		
			return dequeueDogs();
		}

		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		
		if (dog.isOlderThan(cat)) {		
			return dogs.poll();
		} 

		else {
			return cats.poll();
		}
	}
	
	public Animal peek() {

		if (dogs.size() == 0) {		
			return cats.peek();
		} 

		else if (cats.size() == 0) {		
			return dogs.peek();
		}		

		Dog dog = dogs.peek();
		Cat cat = cats.peek();

		if (dog.isOlderThan(cat)) {
			return dog;
		} 

		else 
			return cat;
	}
	
	public int size() {
		return dogs.size() + cats.size();
	}
	
	public Dog dequeueDogs() {
		return dogs.poll();
	}
	
	public Dog peekDogs() {

		return dogs.peek();
	}
	
	public Cat dequeueCats() {

		return cats.poll();
	}
	
	public Cat peekCats() {
		return cats.peek();
	}
}
/*END of question: implement a program 
for queueing various animals*/









/* question 3-1 : design an algorithm to use a 
single array to implement three stacks.*/

class FullStackException extends Exception {

	private static final long serialVersionUID = 1L;

	public FullStackException(){
        super();
    }

    public FullStackException( String message ){
        super(message);
    }
}


/*solution-b*/
class StackData {

	public int start;
	public int pointer;
	public int size = 0;
	public int capacity;

	public StackData(int start, int capacity) {		
		start = start;
		pointer = start - 1;
		capacity = capacity;
	}
	
	public boolean isWithinStack(int index, int totalsize) {

		// Note: if stack wraps, the head (right side) wraps around to the left. 
		if ( start <= index && index < start + capacity ) { 

			// non-wrapping, or "head" (right side) of wrapping case
			return true;
		} 

		else if ( start + capacity > totalsize && index < (start + capacity) % totalsize) {

			// tail (left side) of wrapping case
			return true;
		}

		return false;
	}
}
/*solution-b*/





class main31 {

	/*solution - a*/
 	static int stackSize = 10;
	static int [] buffer = new int [ stackSize * 3 ];
	
	// for keeping the index of the stack 
	static int [] stackPointer = {-1, -1, -1};

	public static void push(int stackNum, int value) throws Exception {
		
		// Check that we have space for the next element 
		if ( stackPointer[stackNum] + 1 >= stackSize) { 
			throw new FullStackException();
		}

		// Increment stack pointer and then update top value	
		stackPointer[stackNum]++;
		buffer[ absTopOfStack(stackNum) ] = value;	
	}

	public static int pop(int stackNum) throws Exception {

		if (isEmpty(stackNum)) {
			throw new EmptyStackException();
		}

		int value = buffer[absTopOfStack(stackNum)]; // Get top

		buffer[absTopOfStack(stackNum)] = 0; // Clear index
		stackPointer[stackNum]--; // Decrement pointer

		return value;
	}

	static int peek(int stackNum ) {

		if ( isEmpty(stackNum) ) {
			throw new EmptyStackException();
		}

		return buffer[absTopOfStack(stackNum)];
	}

	static boolean isEmpty(int stackNum){
		return stackPointer[stackNum] == -1;
	}
	
	static int absTopOfStack(int stackNum) {
		return stackNum * stackSize + stackPointer[stackNum];
	}
	/*END of solution - a*/




	/*solution - b*/
	static int numberofstacks = 3;
	static int defaultsize = 4;
	static int totalsize = defaultsize * numberofstacks;
	
	static StackData [] stacks = {   new StackData(0, defaultsize), 
									 new StackData(defaultsize, defaultsize), 
									 new StackData(defaultsize * 2, defaultsize)
								};

	static int [] buffer1 = new int [totalsize];

	public static int numberOfElements() {
		return stacks[0].size + stacks[1].size + stacks[2].size;
	}
	
	public static int nextElement(int index) {

		if (index + 1 == totalsize) {
			return 0;
		} 

		else {
			return index + 1;
		}
	}
	
	public static int previousElement(int index) {

		if (index == 0) {
			return totalsize - 1;
		} 

		else {
			return index - 1;
		}
	}
	
	public static void shift( int stackNum ) {

		StackData stack = stacks[stackNum];

		if (stack.size >= stack.capacity ) {

			int nextStack = (stackNum + 1) % numberofstacks;
			shift(nextStack); // make some room
			stack.capacity++;
		}

		for (int i = (stack.start + stack.capacity - 1) % totalsize; // end of array
					  stack.isWithinStack(i, totalsize); 
					  i = previousElement(i)) {

			buffer1[i] = buffer1[previousElement(i)];
		}

		buffer1[stack.start] = 0;
		stack.start = nextElement(stack.start); // move start start
		stack.pointer = nextElement(stack.pointer); // move stack pointer

		stack.capacity--; // return capacity to original
	}
	
	/* Expand stack by shifting over other stacks */
	public static void expand(int stackNum) {

		shift((stackNum + 1) % numberofstacks);
		stacks[stackNum].capacity++;
	}

	public static void push1(int stackNum, int value) throws Exception {

		StackData stack = stacks[stackNum];

		if ( stack.size >= stack.capacity ) {

			if ( numberOfElements() >= totalsize ) { // Totally full
				throw new Exception("Out of space."); 
			} 

			else { // just need to shift things around
				expand(stackNum);
			}
		}

		/* 
		 Find the index of the top element in the array + 1, 
		 and increment the stack pointer 
		 */	

		stack.size++;
		stack.pointer = nextElement(stack.pointer);		
		buffer1[stack.pointer] = value;	
	}

	static int pop1(int stackNum) throws Exception {

		StackData stack = stacks[stackNum];		

		if (stack.size == 0) {
			throw new Exception("Trying to pop an empty stack.");
		}

		int value = buffer1[stack.pointer];

		buffer1[stack.pointer] = 0;
		stack.pointer = previousElement(stack.pointer);
		stack.size--;

		return value;
	}

	static int peek1(int stackNum) {
		StackData stack = stacks[stackNum];			
		return buffer1[stack.pointer];
	}

	static boolean isEmpty1(int stackNum) {
		StackData stack = stacks[stackNum];
		return stack.size == 0;
	}

	public static String arrayToString(int[] array) {

		StringBuilder sb = new StringBuilder();

		for (int v : array) {
			sb.append(v + ", ");
		}

		return sb.toString();
	}
	/*END of solution - b*/

}
/*END solution 3-1: design an algorithm to use a 
single array to implement three stacks.*/









/*question 3-2 : design an algorithm for a stack 
which, in addition to push and pop, also has a 
function min which returns the minimum element*/

class NodeWithMin {

    public int value;
    public int min;

    public NodeWithMin(int v, int min){

        this.value = v;
        this.min = min;
    }
}


/*solution - a*/
class StackWithMin extends Stack<NodeWithMin> {

    public void push(int value) {

        int newMin = Math.min(value, min());
        super.push( new NodeWithMin(value, newMin) );
    }
    
    public int min() {
        
    	if (this.isEmpty()) {
    		return Integer.MAXVALUE;
    	} 

    	else {
    		return peek().min;
    	}    	
    }
}
/*END of solution-a*/




/*solution - b*/
class StackWithMin2 extends Stack<Integer> {

	Stack<Integer> s2;
	
	public StackWithMin2() {
		s2 = new Stack<Integer>();		
	}
	
	public void push(int value){

		if (value <= min()) {
			s2.push(value);
		}

		super.push(value);
	}
	
	public Integer pop() {

		int value = super.pop();

		if ( value == min() ) {
			s2.pop();			
		}

		return value;
	}
	
	public int min() {

		if ( s2.isEmpty() ) {
			return Integer.MAXVALUE;
		} 

		else {
			return s2.peek();
		}
	}
}
/*END of solution - b*/

/*ENd of solution 3-2 : design an algorithm 
for a stack which, in addition to push and 
pop, also has a function min which returns 
the minimum element*/









/*question 3-3: design an algorithm for a 
stack which when reached in capacity, start 
a new stack with the same capacity*/

class Node {

	public Node above;
	public Node below;
	public int data;
	
	public Node(int data) {
		this.data = data;
	}
}



class myStack {
	
	public Node top;
	public Node bottom;

	private int capacity;
	public int size;

	HashSet<Integer> hashSet = new HashSet<Integer>();

	public myStack (int cap){

		this.top = null;
		this.bottom = null;
		this.capacity = cap;
		this.size = 0;
	}

	public static int randomInt(int n) {
		return (int)( Math.random() * n);
	}

	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}
	
	public boolean isFull() { 
		return capacity == size; 
	}
	
	public boolean push(int v) {

		if ( size >= capacity ) {
			return false;
		}
			
		size++;

		Node n = new Node(v);

		if ( size == 1 ) {
			bottom = n;
		} 
			
		if ( top != null ){
			top.above = n;
		}

		if ( n != null){
			n.below = top;
		}

		top = n;

		return true;
	}	

	public int min(){

		if ( top == null) 
			return -1;

		Node curr = this.top;
		int min = this.peek(); 

		while ( curr != null) {

			if ( curr.data < min){
                min = curr.data;
            }

			curr = curr.below;
		}

		return min; 
	}
	
	public int pop() {

		Node t = top;
		top = top.below;
		size--;
		return t.data;
	}

	public int peek (){	
		return this.top.data; 
	}
	
	public boolean isEmpty() { 		
		return size == 0; 
	}
	
	public int removeBottom() {

		Node b = bottom;

		bottom = bottom.above;

		if (bottom != null) 
			bottom.below = null;

		size--;
		return b.data;
	}

	public void display() {

		if ( top == null) 
			return;

		Node curr = this.top;
		int min = -1 ; 

		System.out.println("\n\n");

		while(curr != null){

			System.out.println( curr.data);

			curr = curr.below;

			if ( curr != null){
				System.out.println("↑");
			}
		}

		System.out.println();
	}		
}



class SetOfStacks {
	
	ArrayList<myStack> stacks = new ArrayList<myStack>();
	public int capacity;
	
	public SetOfStacks( int capacity ) { 
		this.capacity = capacity; 
	}
	
	public myStack getLastStack() {
		
		if (stacks.size() == 0) {
			return null;
		}

		return stacks.get( stacks.size() - 1 );
	}
    	
	public void push(int v) {

		myStack last = getLastStack();

		if (last != null && !last.isFull() ) { // add to last
			last.push(v);
		} 

		else { 
			myStack stack = new myStack(capacity);
			stack.push(v);
			stacks.add(stack);		
		}
	}
	
	public int pop() {

		myStack last = getLastStack();
		int v = last.pop();

		// after poping, if the size become zero, remove the stack 
		if (last.size == 0) {
			stacks.remove( stacks.size() - 1 );
		}

		return v;
	}
	
	public int popAt(int index) {
		return leftShift(index, true);
	}
	
	public int leftShift(int index, boolean removeTop) {

		myStack stack = stacks.get(index);

		int removeditem;

		if (removeTop) {
			removeditem = stack.pop();
		}
			
		else {
			removeditem = stack.removeBottom();
		} 
			
		if (stack.isEmpty()) {
			stacks.remove(index);
		} 

		else if (stacks.size() > index + 1) { 
			int v = leftShift( index + 1, false );
			stack.push(v);
		}
		
		return removeditem;
	}
	
	public boolean isEmpty() {
		myStack last = getLastStack();
		return last == null || last.isEmpty();
	}

}
/*END of question 3-3 : design an algorithm 
for a stack which when reached in capacity, 
start a new stack with the same capacity */












/*question 3-4: considering three rods and an entire stack, move 
the entire stack to another rod, obeying that, only one disk can 
be moved at a time, each move consists of taking the upper disk 
from one of the stacks and placing it on top of another stack and 
no disk may be placed on top of a smaller disk.*/


// tower of Hanoi 
class Tower {

	private Stack<Integer> disks;
	private int index;

	public Tower(int i) {
		disks = new Stack<Integer>();
		index = i;
	}
	
	public int index() {
		return index;
	}
	
	public void add(int d) {

		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.println("Error placing disk " + d);
		}
		 
		else {
			disks.push(d);
		}
	}
	
	public void moveTopTo(Tower t) {
		int top = disks.pop();
		t.add(top);
	}
	
	public void print() {
		System.out.println("Contents of Tower " + index() + ": " + disks.toString());
	}
	
    public void moveDisks(int n, Tower destination, Tower buffer){

		if (n > 0) {

			String tag = "move" + n + "disksfrom" + this.index + "to" + destination.index + "withbuffer" + buffer.index; 
			System.out.println("<" + tag + ">");
			moveDisks(n - 1, buffer, destination);
			System.out.println("<movetopfrom" + this.index + "to" + destination.index + ">");
			System.out.println("<before>");
			System.out.println("<sourceprint>");
			this.print();

			System.out.println("</sourceprint>");
			System.out.println("<destinationprint>");
			destination.print();
			System.out.println("</destinationprint>");
			System.out.println("</before>");
			moveTopTo(destination);
			System.out.println("<after>");
			System.out.println("<sourceprint>");
			this.print();

			System.out.println("</sourceprint>");
			System.out.println("<destinationprint>");
			destination.print();
			System.out.println("</destinationprint>");
			System.out.println("</after>");
			System.out.println("</movetopfrom" + this.index + "to" + destination.index + ">");
			buffer.moveDisks(n - 1, destination, this);
			System.out.println("</" + tag + ">");
		}
	}
}
/*END of solution 3-4: considering three rods and an entire 
stack, move the entire stack to another rod, obeying that, 
only one disk can be moved at a time, each move consists of 
taking the upper disk from one of the stacks and placing it 
on top of another stack and no disk may be placed on top of 
a smaller disk.*/








/*question 3-5 : Implement a MyQueue class 
which implements a queue using two stacks*/
// Goldman Sachs 
class MyQueue<T> {

	Stack<T> stackNewest, stackOldest;
	
	public MyQueue() {
		stackNewest = new Stack<T>();
		stackOldest = new Stack<T>();
	}
	
	public int size() {		
		return stackNewest.size() + stackOldest.size();
	}
	
	public void add( T value ) {

		// Push onto stack1
		stackNewest.push(value);
	}

    public T peek() {

        shiftStacks();
        return stackOldest.peek(); 
    }
    
    public T remove() {

        shiftStacks();
        return stackOldest.pop(); // pop the oldest item.
    }

	private void shiftStacks() {

		if ( stackOldest.isEmpty() ) { 

			while (!stackNewest.isEmpty()) {

				stackOldest.push(stackNewest.pop());
			}
		}
	}	

	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static int randomIntInRange(int min, int max) {
		return min + randomInt(max + 1 - min);
	}
}
/*END of question 3-5 : Implement a MyQueue 
class which implements a queue using two 
stacks*/








/*question 3-6 : design an algorithm to sort a stack 
in ascending order. The only functions that should be 
used : push, pop, peek and isEmpty*/

class main36 {

	/*solution - a*/
	static int c = 0;
	
	public static Stack<Integer> mergesort( Stack<Integer> inStack) {

		if (inStack.size() <= 1) {
			return inStack;
		}

		Stack<Integer> left = new Stack<Integer>();
		Stack<Integer> right = new Stack<Integer>();

		int count = 0;

		// divide the stack in two parts
		while ( inStack.size() != 0 ) {

			count++;
			c++;

			if (count % 2 == 0) {
				left.push(inStack.pop());
			} 

			else {
				right.push(inStack.pop());
			}
		}

		left = mergesort(left);
		right = mergesort(right);

		while (left.size() > 0 || right.size() > 0) {

			if ( left.size() == 0 ) {
				inStack.push(right.pop());
			}

			else if (right.size() == 0) {
				inStack.push(left.pop());
			}

			// big.compareTo(small) > 0
			// small.compareTo(big) < 0
			// one.compareTo(two) == 0 if one == two 

			else if (right.peek().compareTo(left.peek()) <= 0) {
				inStack.push( left.pop() );
			}

			else {
				inStack.push(right.pop());
			}
		}

		Stack<Integer> reverseStack = new Stack<Integer>();

		while (inStack.size() > 0){
			c++;
			reverseStack.push(inStack.pop());
		}
		return reverseStack;
	}

	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static int randomIntInRange(int min, int max) {
		return randomInt(max + 1 - min) + min;
	}
	/*END of solution - a*/
	



	/*solution - b*/
	public static Stack<Integer> sort (Stack<Integer> s) {

		Stack<Integer> r = new Stack<Integer>();

		while(!s.isEmpty()){

			int tmp = s.pop();

			while( !r.isEmpty() && r.peek() > tmp ) {
				s.push( r.pop() );
			}

			r.push(tmp);
		}
		
		return r;
	}
	/*END of solution - b*/

}
/*END of solution 3-6 : design an algorithm to sort 
a stack in ascending order. The only functions that 
should be used : push, pop, peek and isEmpty*/









public class mySQ {

	/* question: implement the data-structure 
	for the priority queue */

	/* In computer science, a priority queue is an abstract data type which is like 
	a regular queue or stack data structure, but where additionally each element 
	has a "priority" associated with it. In a priority queue, an element with high 
	priority is served before an element with low priority. If two elements have the 
	same priority, they are served according to their order in the queue.

	While priority queues are often implemented with heaps, they are conceptually distinct 
	from heaps. A priority queue is an abstract concept like "a list" or "a map"; just as a 
	list can be implemented with a linked list or an array, a priority queue can be implemented 
	with a heap or a variety of other methods such as an unordered array.*/	
		 
	/* END of solution: implement the data-structure 
	for the priority queue */



	public static int randomInt(int n) {
		return (int) (Math.random() * n);
	}

	public static int randomIntInRange(int min, int max){
		return randomInt(max + 1 - min) + min;
	}
	


	/*question: learn about the
    queue data-structure*/
    public static void learnQueue() {

         /* 1. queue.poll() removes the element from the queue, if there is no element it returns NULL 
         2. queue.remove() do the same but throws NoSuchElementException exception in case 
        there is no element

         3. queue.peek() only copy the value and if there is no elelemnt, provides NULL
         4. queue.element() only copy the value and if there is no elelemnt, throws exception 
    
         5. queue.add() - throws an exception if the operation fails,
         6. queue.offer()  - returns a special value (either null or false, depending on the operation)*/

        Queue<String> queue = new LinkedList<String>();

        int [] arr = {12, 2, 34, 9, 56, 11 };

        for ( int j = 0; j < arr.length; j ++){
            queue.add(arr[j]);
        }

        System.out.println("Is Queue empty : " + queue.isEmpty());
        System.out.println("Elements of queue = " + queue);
        System.out.println("Size of Queue : " + queue.size());

        Object obj = queue.element();
        System.out.println("Element at head position = " + obj);
        Iterator iq = queue.iterator();
        
        while (iq.hasNext()) {
        	queue.remove();
        }
        
        System.out.println("Is Queue empty : "+ queue.isEmpty());        
    }
    /*END of solution: learn about 
    the queue data-structure*/




	 
	public static void main(String[] args) throws Exception {

		/*question 3-1: design an algorithm to use a single array to implement three stacks*/

		/* TEST SOLUTION - A */
		/*push(2, 4);
		System.out.println("Peek 2: " + peek(2));
		push(0, 3);
		push(0, 7);
		push(0, 5);
		System.out.println("Peek 0: " + peek(0));
		pop(0);
		System.out.println("Peek 0: " + peek(0));
		pop(0);
		System.out.println("Peek 0: " + peek(0));*/
		
		/* TEST SOLUTION - B */

		/*main31 mObj = new main31(); 
		mObj.push1(0, 10);*/
		
		/*
		m.push1(1, 20);
		m.push1(2, 30);*/
		
		/*push1(1, 21);
		push1(0, 11);
		push1(0, 12);
		
		pop1(0);
		
		push1(2, 31);
		
		push1(0, 13);
		push1(1, 22);
		
		push1(2, 31);
		push1(2, 32);
		push1(2, 33);
		push1(2, 34);

		System.out.println("Final Stack: " + arrayToString(buffer1));
		
		pop1(1);
		push1(2, 35);
		
		System.out.println("Final Stack: " + arrayToString(buffer1));*/
		/* END OF TEST SOLUTION - B*/
		/* END test 3-1 : design an algorithm to use a single array to implement three stacks. */







		/* question 3-2 */
		/*StackWithMin stack = new StackWithMin();
		StackWithMin2 stack2 = new StackWithMin2();

		System.out.println();

		for (int i = 0; i < 7; i++) {

			int value = randomIntInRange(0, 100);
			stack2.push(value);
			System.out.println(value);
			
		}

		System.out.println("\n"+ "top value of the stack2 is = "+stack2.min() +  "\n");

		for (int i = 0; i < 7; i++) {

			int value = randomIntInRange(0, 100);
			stack.push(value);
			System.out.println(value);
		}

		System.out.println("\n"+ "stack min value is : stack.peek().min : "+stack.peek().min + "  stack.min() : "+  stack.min() +"\n");*/
		/* END test 3-2 */







		/* question 3-3 : design an algorithm for a stack which when 
		reached in capacity, start a new stack with the same capacity */

		
		/*int capacitypersubstack = 5;
		SetOfStacks set = new SetOfStacks(capacitypersubstack);
		
		for (int i = 0; i < 34; i++) {
			set.push(i);
		}

		for (int i = 0; i < 34; i++) {
			System.out.println("Popped " + set.pop());
		}*/	
			
		
		/* END : test  3-3 : design an algorithm for a stack which when 
		reached in capacity, start a new stack with the same capacity */







		/* question 3-4 */
		/*int n = 5;
		Tower[] towers = new Tower[3];
		for (int i = 0; i < 3; i++) {
			towers[i] = new Tower(i);
		}
		for (int i = n - 1; i >= 0; i--) {
			towers[0].add(i);
		}
		
		// Copy and paste output into a .XML file and open it with internet explorer.
		//towers[0].print();
		towers[0].moveDisks(n, towers[2], towers[1]);
		//towers[2].print();*/
		/* END test 3-4 */







		/* question 3-5 : Implement a MyQueue class which implements a queue using two stacks */
		/*MyQueue<Integer> myqueue = new MyQueue<Integer>();	
		
		// Let's test our code against a "real" queue
		Queue<Integer> testqueue = new LinkedList<Integer>();
		
		for (int i = 0; i < 15; i++) {

			int choice = randomIntInRange(0, 10);

			if (choice <= 5) { // enqueue

				int element = randomIntInRange(1, 10);
				testqueue.add(element);
				myqueue.add(element);
				System.out.println("Enqueued " + element);
			} 


			else if ( testqueue.size() > 0 ) {

				int top1 = testqueue.remove();
				int top2 = myqueue.remove();

				if (top1 != top2) { // Check for error
					System.out.println("******* FAILURE - DIFFERENT TOPS: " + top1 + ", " + top2);
				} 
				System.out.println("Dequeued " + top1);
			}

			
			if (testqueue.size() == myqueue.size()) {

				if (testqueue.size() > 0 && testqueue.peek() != myqueue.peek()) {

					System.out.println("******* FAILURE - DIFFERENT TOPS: " + testqueue.peek() + ", " + myqueue.peek() + " ******");
				}
			} 

			else {
				System.out.println("******* FAILURE - DIFFERENT SIZES ******");
			}
		}*/
		/* END test 3-5 */







		/* question 3-6 */
		/*

		for (int k = 1; k < 10; k++) {

			main36.c = 0;
			Stack<Integer> s = new Stack<Integer>();

			for (int i = 0; i < 10 * k; i++) {

				int r = randomIntInRange( 0,  100 );
				s.push(r);
			}

			s = main36.mergesort(s);
			int last = Integer.MAXVALUE;

			while(!s.isEmpty()) {
				int curr = s.pop();
				if (curr > last) {
					System.out.println("Error: " + last + " " + curr);
				}
				last = curr;
			}

			System.out.println(main36.c);	
		}*/
		/* END test 3-6 */






		/* question : animal class */
		/*AnimalQueue animals = new AnimalQueue();
		animals.enqueue(new Cat("Callie"));
		animals.enqueue(new Cat("Kiki"));
		animals.enqueue(new Dog("Fido"));
		animals.enqueue(new Dog("Dora"));
		animals.enqueue(new Cat("Kari"));
		animals.enqueue(new Dog("Dexter"));
		animals.enqueue(new Dog("Dobo"));
		animals.enqueue(new Cat("Copa"));
		
		System.out.println(animals.dequeueAny().name());	
		System.out.println(animals.dequeueAny().name());	
		System.out.println(animals.dequeueAny().name());	
		
		animals.enqueue(new Dog("Dapa"));
		animals.enqueue(new Cat("Kilo"));
		
		while (animals.size() != 0) {
			System.out.println(animals.dequeueAny().name());	
		}*/
		/* END test animal puzzle */



	}

}

