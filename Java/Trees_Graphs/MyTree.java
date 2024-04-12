import java.util.*; 






// Node class for implementing the Binary tree 
class Node {

	private int key;

	private Node leftChild;
    private Node rightChild;

	Node(int key) {
		this.key = key;
	}

	Node (){ }

	public String toString() {
		return "\n"+key+" ";
	}
}





/*question 4-2:  Given a directed graph, design 
an algorithm to find out whether there is a route 
between two nodes*/
class Node {


    private Node adjacent[];
    public int index;

    private String vertex;

    // enum types
    public State state;

    public Node (String vertex, int count) {
        this.vertex = vertex;               

        index = 0;         
        adjacent = new Node[count];        
    }
  
    public void addAdjacent( Node x) {            
        this.adjacent[index] = x;
        index++;
    }

    public Node[] getAdjacent() {
        return adjacent;
    }

    public String getVertex() {
        return vertex;
    }
}



class Graph {

	private Node nodes[];
	public int count = 0;

	public Graph(int num) {		
		nodes = new Node[num];
    }

    public void addNode(Node x) {
        nodes[count++] = x;
    }
    
    public Node[] getNodes() {
        return nodes;
    }
}
/*END solution 4-2: Given a directed graph, design an algorithm 
to find out whether there is a route between two nodes.*/






/*
* Breadth first search in a directed graph using adjacency list

Using the adjacency list makes it faster 
* */
class Graph {

    private int V;

    private LinkedList<Integer> adj[];

    Graph(int v) {

        this.V = v;
        this.adj = new LinkedList[v];

        for (int i = 0; i < v; ++i){
            adj[i] = new LinkedList();
        }        
    }

    /*
     * function to add an edge into the graph
     * */
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /*
     * prints BFS traversal from a given source S
     * */
    public void BFS(int S) {

        /*
         * Mark all the vertices as not visited(By default set as false)
         * */
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[S] = true;
        queue.add(S);


        while (queue.size() != 0) {

            // Dequeue a vertex from queue and print it
            S = queue.poll();
            System.out.print(S + " ");


            // Get all adjacent vertices of the dequeued vertex S
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[S].listIterator();

            while (i.hasNext()) {

                int n = i.next();

                if (!visited[n]) {                

                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }


    public static void main(String args[]) {
        
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex 2)");

        g.BFS(2);
    }
}






/*Question: desing a program to implement DFS 
using adjacency list "Geeks for Geeks"*/
class Graph {


    // No. of vertices
    private int V;  

    // adjacency list 
    // private LinkedList<Integer> adj[];
    private LinkedList<Integer>[] adj[];
 
    Graph(int v){

        this.V = v;
        this.adj = new LinkedList[v];

        for (int i = 0; i < v; ++i){

            // also, correct 
            // adj[i] = new LinkedList<Integer>(); 
            adj[i] = new LinkedList();
        }            
    }

    void addEdge(int v, int w){
        adj[v].add(w); 
    } 

    void performDFS(int v){

        boolean visited[] = new boolean[V];
        DFSUtil(v, visited);
    }

    void DFSUtil(int v, boolean visited[]){

        visited[v] = true;
        System.out.print(v+" ");

        Iterator<Integer> i = adj[v].listIterator();

        while (i.hasNext()){

            int n = i.next();    

            if (!visited[n]){
                DFSUtil(n, visited);    
            }
        }
    }

 
    public static void test( ){

        Graph g = new Graph(4);
 
        // starting from vertex 2: {2, 0, 1, 3}
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        System.out.println("Following is Depth First Traversal "+
                           "(starting from vertex 2)");
 
        g.performDFS(2);
    }
}
/*END of solution: desing a 
program to implement dfs*/












/*question: design a program to deal with (add, 
remove, size etc) String of array in a binary 
tree*/
class binaryTreeOfStrings{

    public binaryTreeOfStrings(){

    }
}
/*END of solution: design a program to deal with 
(add, remove, size etc) String of array in a binary 
tree*/








interface Vocabulary {

    boolean add(String word);
    boolean isPrefix(String prefix);
    boolean contains(String word);
}



/*solution-b*/
class ListVocabulary implements Vocabulary{


    private List<String> words;

    public ListVocabulary() {

        // It's important to use a List that implements RandomAccess,
        // so that Collections.binarySearch() can work in O(logn) time
        words = new ArrayList<String>();
    }
    
  
     // constructor that adds alle the words 
     // and then sorts the underlying list
    public ListVocabulary(Collection<String> words) {

        this();
        this.words.addAll(words);

        Collections.sort(this.words);
    }
    
    public boolean add(String word) {

        int pos = Collections.binarySearch(words, word);

        // pos > 0 means the word is already in the list. Insert only
        // if it's not there yet
        if (pos < 0) {
            words.add(-(pos+1), word);
            return true;
        }

        return false;
    }

    public boolean isPrefix(String prefix) {

        int pos = Collections.binarySearch(words, prefix) ;

        if (pos >= 0) {

            // The prefix is a word. Check the following word, because we are looking 
            // for words that are longer than the prefix
            if (pos +1 < words.size()) {

                String nextWord = words.get(pos+1);
                return nextWord.startsWith(prefix);
            }

            return false;
        }

        pos = -(pos+1);

        // The prefix is not a word. Check where it would be inserted and get the next word
        // If it starts with prefix, return true.
        if (pos == words.size()) {
            return false;
        }

        String nextWord = words.get(pos);
        return nextWord.startsWith(prefix);
    }

    public boolean contains(String word) {

        int pos = Collections.binarySearch(words, word);
        return pos >= 0;
    }
    
    @Override
    public String getName() {
        return getClass().getName();
    }
}
/*END of solution-b*/




/*solution-c*/
class TreeVocabulary extends TreeSet<String> implements Vocabulary {

    // long minimum value = -2^63 and maximum value of 2^63-1
    private static final long serialVersionUID = 1084215309279053589L;
    
    public TreeVocabulary() {
        super();
    }

    public TreeVocabulary(Collection<String> c) {
        super(c);
    }

    public boolean isPrefix(String prefix) {

        String nextWord = ceiling(prefix);

        if (nextWord == null) {
            return false;
        }

        if (nextWord.equals(prefix)) {

            Set<String> tail = tailSet(nextWord, false);

            if (tail.isEmpty()) {
                return false;
            }

            nextWord = tail.iterator().next();
        }

        return nextWord.startsWith(prefix);
    }

    /**
     * There is a mismatch between the parameter types of vocabulary and TreeSet, so
     * force call to the upper-class method
     */
    public boolean contains(String word) {
        return super.contains(word);
    }

    @Override
    public String getName() {
        return getClass().getName();
    }
}
/*END of solution-c*/


/*END of solution: design an algorithm for 
implementing trie data-staucture*/







/*
* The Binary Tree class 
*/
public class BinaryTree {


	Node root;

	BinaryTree (){
		root = null;
	}

    
	/*question: find the depth of a binary tree 
    Given an array of size N and a random number M, 
    generate an array of size M that contains 
    random element from the main array*/
    public static int randomDepth(int m, int n ){

       // look in CTCI 
    }
    /*END of solution*/


	/*question: write an algorithm to perform 
	depth first search for an uni-directional 
	graph with given source


	// matrix for testing 
	int [][] ar = {
						// 1  2  3  4  5  6  7  8  9  10
						{  0, 1, 1, 1, 0, 0, 0, 0, 0, 0}, // 1
						{  0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // 2
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3 
						{  0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, // 4
						{  0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 5 
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
						{  0, 0, 0, 0, 0, 0, 0, 1, 1, 0}, // 7
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8 
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // 10 
				};
	// dfs result : [1	2	7	8	9	10	3	4	5	6]

	int [][] arr1 = {

						// 1  2  3  4  5  6  7  8  9  10  11  12  13
						{  0, 1, 1, 1, 0, 0, 0, 0, 0, 0,  0,  0,  0}, // 1
						{  0, 0, 0, 0, 0, 0, 1, 0, 0, 0,  0,  0,  0}, // 2
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  1,  1,  0}, // 3 
						{  0, 0, 0, 0, 1, 0, 0, 0, 0, 0,  0,  0,  0}, // 4
						{  0, 0, 0, 0, 0, 1, 0, 0, 0, 0,  0,  0,  1}, // 5 
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0}, // 6
						{  0, 0, 0, 0, 0, 0, 0, 1, 1, 0,  0,  0,  0}, // 7
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0}, // 8 
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 1,  0,  0,  0}, // 9
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0},  // 10 
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0},  // 11
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0},  // 12 
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0}  // 13 
				};	

	// dfs result = [1  2  7  8  9  10  3  11  12  4  5  6  13]*/

    /*
        Adjacency Matrix
        ----------------
        Adjacency matrix is a square matrix used to represent a finite graph. 
        The elements of the matrix indicate whether pairs of vertices are 
        adjacent or not in the graph.

    */                    


	/*solution-a*/
    // use the adjacency matrix for the DFS
	public static void depthFirstSearch( int[][] mat, int start ){

		// we will count node# from 1 upwards 
		if (start < 1) {
            return;
        }

		Stack<Integer> stack = new Stack<Integer>();

        // columns 
		int numOfCols = mat[0].length;
        int row, col;

		int [] visited =  new int[numOfCols];

		// may be, row =  strat - 1
		// then, use the value of row 

		visited[start-1] = 1;
		stack.push(start-1);

		System.out.println();
		System.out.print(start + "\t");

        /*
        * check if the stack is empty
        */
		while (!stack.isEmpty() ){

            row = stack.peek();
            col = row;	

		    while (col < numOfCols){
		    	
     	        if(mat[row][col] == 1 && visited[col] == 0){

                    System.out.print( row+1 + "\t");  

                    stack.push(col);
                    visited[col] = 1;

                    row = col;
                    col = 0;
					                                        
	            	continue;                
                }

	            col++;
		    }

            stack.pop();	            	
        }


        boolean isTraverse = isFullTraverse(visited);
	}


    public boolean isFullTraverse(boolean[] visited){

        for ( int i=0; i < visited.length; i++){

            if ( visited[i] ==  0){
                return false;
            }
        }

        return true;
    }
	/*END of solution -a*/







	/*solution-b*/
	// web link: <http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Graph/dfs. l>

    // primitives are initiated with values, 
    // for the booelan it will be false
	// boolean [] bol =  new boolean[10]; 
	public static void depthFirstSearch1(int i, int[][] mat){

		// we will need to provide i-1 as it will be used inside the
		// adjcency matrix 
        // i >= 0
        dfsHelper(i, mat, new boolean[ mat[0].length ] );
	}

    public static void dfsHelper(int row, int[][] mat, boolean[] visited) {

        if(!visited[row]) {

            visited[row] = true; // Mark node as "visited"
            System.out.print( (row+1) + " ");

            for(int col = 0; col < mat[row].length; col++){

                if ( mat[row][col] == 1 && !visited[col] ){
                    dfsHelper(col , mat, visited); // Visit node
                }
            }
        }
    }
	/*END fo solution -b*/

	/* END OF SOLUTION: depth first search 
	with uni-directional */



	/*question: design an algorithm to perform 
	depth first search for a bi-directional graph*/
	public static void dfsBI( int[][] mat, int start){

	}
	/* END of solution: write an algorithm to perform 
	depth first search for a bi-directional graph*/



	/* question: write an algorithm to perform 
	breadth first search
	int [][] arr = {
						// 1  2  3  4  5  6  7  8  9  10
						{  0, 1, 1, 1, 0, 0, 0, 0, 0, 0}, // 1
						{  0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // 2
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 3 
						{  0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // 4
						{  0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 5 
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 6
						{  0, 0, 0, 0, 0, 0, 0, 1, 1, 0}, // 7
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8 
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
						{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0}  // 10 
				};

	//  call : breadthFirstSearch(arr1, 1);
	//  bfs result : [1	2	3	4	7	5	8	9	6	10]

	int [][] arr1 = {

					// 1  2  3  4  5  6  7  8  9  10  11  12  13
					{  0, 1, 1, 1, 0, 0, 0, 0, 0, 0,  0,  0,  0}, // 1
					{  0, 0, 0, 0, 0, 0, 1, 0, 0, 0,  0,  0,  0}, // 2
					{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  1,  1,  0}, // 3 
					{  0, 0, 0, 0, 1, 0, 0, 0, 0, 0,  0,  0,  0}, // 4
					{  0, 0, 0, 0, 0, 1, 0, 0, 0, 0,  0,  0,  1}, // 5 
					{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0}, // 6
					{  0, 0, 0, 0, 0, 0, 0, 1, 1, 0,  0,  0,  0}, // 7
					{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0}, // 8 
					{  0, 0, 0, 0, 0, 0, 0, 0, 0, 1,  0,  0,  0}, // 9
					{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0},  // 10 
					{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0},  // 11
					{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0},  // 12 
					{  0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0,  0,  0}  // 13 
			};

	// call: breadthFirstSearch(arr1, 1);
	// 6 is missing 
	// bfs result = [1 2   3   4   7   11  12  5   8   9   13  10]
	*/

	/*solution-a*/
	public static void breadthFirstSearch(int[][] mat, int start){


		int numOfCols =  mat[0].length; 
        int row, col;

        // array to keep track of visiting 
		int[] visited = new int[numOfCols];

		Queue<Integer> queue = new LinkedList<Integer>();

		visited[start -1 ] = 1;
		queue.add(start-1);

		System.out.println();
		
		while(!queue.isEmpty()){

			row =  queue.remove();
			col = row;

			System.out.print( (col+1) + " ");

			while(col < numOfCols){

				if ( mat[row][col] == 1 && visited[col] == 0){

					queue.add(col);
					visited[col] = 1;
				}

				col++; 
			}
		}

		System.out.println();
	}

    public boolean isFullTraverse(boolean[] visited){

        for ( int i=0; i < visited.length; i++){

            if ( visited[i] ==  0){
                return false;
            }
        }

        return true;
    }
	/*END of solution-a*/





    /* question: write an algorithm to 
    perform breadth first search recursively*/
    public void recursiveBFS(Node root) {

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        recursiveBFSHelper(queue);
    }
    
    public void recursiveBFSHelper(LinkedList<Node> q) {

        if (q.isEmpty()){
            return;
        } 

        Node n = q.pop();

        System.out.println("Node: " + n);

        if (n.left != null) {
            q.push(n.left);
        }

        if (n.right != null) {
            q.push(n.right);
        }

        recursiveBFSHelper(q);
    }


    // breadth frist search in a graph with recursive algorithm
    public void recursiveBFSHelperI(LinkedList<Node> queue) {

        while(!queue.isEmpty()){

            Node node = queue.pop();

            System.out.println("Node: " + node);

            for (Node n: node.getAdjacent()) {
                queue.push(n);
            }

            recursiveBFSHelper(queue);
        }        
    }




	/* question: write an algorithm to perform 
	breadth first search for a bi-directional graph*/
	public static void dfsBI( int[][] mat, int start){

	}
	/* END of solution: write an algorithm to perform 
	breadth first search for a bi-directional graph*/








	/*question: write an algorithm to insert key 
	in the binary search tree*/
	public void addNode(int key) {

		Node newNode = new Node(key);

		// If there is no root this becomes root
		if (root == null) {
			root = newNode;
		} 

		else {

			// Set root as the Node we will start
			// with as we traverse the tree

			Node focusNode = root;
			Node parent;

			while (true) {

				parent = focusNode;

				if (key < focusNode.key) {

					focusNode = focusNode.leftChild;

					if (focusNode == null) {

						parent.leftChild = newNode;
						return; 
					}

				} // end of if 

				else { 

					focusNode = focusNode.rightChild;

					if (focusNode == null) {

						parent.rightChild = newNode;
						return; 
					}
				}
			}            
		}
	}
	/*END of solution: write an algorithm to insert key 
	in the binary search tree */





	// get the height of binary tree 
	public int height(Node root) {

		if (root == null) {
            return 0;
        }
		
		Node focusNode = root; 

		int leftHeight = focusNode.leftChild != null ? height( focusNode.leftChild) : 0;
		int rightHeight = focusNode.rightChild != null ? height( focusNode.rightChild) : 0;

		return 1 + Math.max(leftHeight, rightHeight);
	}

	public static int depth(Node node){

		if (node == null) {
            return 0;
        }

		else {
            return 1 + Math.max(depth(node.leftChild), depth(node.rightChild));
        }
	}



    /*question: design an algorithm to reverse a binary tree*/
    // mirror image of the BST
    // make left sub-tree to right and vice versa 

    /*
    input:

        1
       / \
      2   3
     / \   \
    4   5   6

    output:

         1
       /  \
      3    2
     /    / \
    6    5  4       
    */

    /*solution-a*/
    /*
    ---------
    ALGORITHM
    ---------

    i.   Chnage the left and rigth node with temp storage 
    ii.  Go deeper in the tree recursively in the left direction
    iii. Go deeper in the tree recursively in the left direction
    */  
    public static Node mirrorTree(Node root){

        if(root != null) {
            helper(root);
        }
        
        return root;    
    }   

    public static void helper(Node p){
     
        Node temp = p.leftChild;

        p.leftChild = p.rightChild;
        p.rightChild = temp;
     
        if(p.leftChild != null){
            helper(p.leftChild);
        }
     
        if(p.rightChild!=null){
            helper(p.rightChild);
        }
    }
    /*END of solution-a*/




    /*solution-b*/
    public static Node mirrorTree(Node root) {

        LinkedList<Node> queue = new LinkedList<Node>();
     
        if(root!=null) {
            queue.add(root);
        }
        
        while(!queue.isEmpty()){

            Node p = queue.poll();

            // put the children of the node inside the queue 
            if(p.leftChild!=null){
                queue.add(p.leftChild);
            }
                
            if(p.rightChild!=null){
                queue.add(p.rightChild);
            }

     
            TreeNode temp = p.leftChild;

            p.leftChild = p.rightChild;
            p.rightChild = temp;
        }
     
        return root;    
    }
     /*END of solution-b*/
    /*END of solution: design an algorithm 
    to revsere a binary tree*/








    /*question: design an algorithm 
    to invert a binary tree*/

    /*
    input:

        1
       / \
      2   3
     / \   \
    4   5   6

    after inversion:

    4   5   6
    \ /    /
     2    3
      \  /
       1
     */

    public Node invertTree(Node root){

        if (root==null) {
            return null; // line 1
        }

        if (root.leftChild != null){ // line 2

            Node left = invertTree(root.leftChild); // line 3
            left.rightChild =root; // line 4
        }

        if (root.rightChild != null){ // line 5

            Node right = invertTree(root.rightChild); // line 6
            right.leftChild = root; // line 7
        }

        root.leftChild = null; // line 8
        root.rightChild = null; // line 9

        return root; // line 10
    }
    /*END of solution: design an algorithm 
    to invert a binary tree*/




	/*question: desing an algorithm to convert 
    a binary tree into LinkedList*/

	 // some code 
	/*END of solution: desing an algorithm to 
    convert a binary tree into LinkedList*/








	/*question: desing an algorithm to determine if 
    there exists a cycle in the given graph*/

	 // some code 
	/*ENd of solution: desing an algorithm to determine 
    if there exists a cycle in the given graph*/










    /*
    Usage of the Pre-Order, In-order or Post-Order
    ----------------------------------------------

    The traversal strategy the programmer selects depends on the specific needs of the 
    algorithm being designed. The goal is speed, so pick the strategy that brings you 
    the nodes you require the fastest.

        i.   If you know you need to explore the roots before inspecting any leaves, you 
        pick pre-order because you will encounter all the roots before all of the leaves.

        ii.  If you know you need to explore all the leaves before any nodes, you select 
        post-order because you don't waste any time inspecting roots in search for leaves.

        iii. If you know that the tree has an inherent sequence in the nodes, and you want 
        to flatten the tree back into its original sequence, than an in-order traversal 
        should be used. The tree would be flattened in the same way it was created. A 
        pre-order or post-order traversal might not unwind the tree back into the sequence 
        which was used to create it.
    */


	// METHODS FOR THE TREE TRAVERSAL

	// inOrderTraverseTree : i) X.left ii) X iii) X.right
	/* this prints the integers in ascending order */
	public void inOrderTraverseTree(Node focusNode) {

		if (focusNode != null) {

			inOrderTraverseTree(focusNode.leftChild);
			System.out.print( focusNode );
			inOrderTraverseTree(focusNode.rightChild);
		}
	}


	/*In-order traversal w/ iteration*/
	public static void inOrderTraverseTree2(Node root){

		if (root ==  null){
			return;
        }

		Node node =  root;
		Stack<Node> stack = new Stack<Node>();

        // take all the left nodes          
		while(node !=  null){

			stack.push(node);
			node = node.leftChild;
		}

		while(stack.size() > 0) {

			Node nod =  stack.pop();
			System.out.println(nod); 

            if (nod.rightChild ==  null) {
                continue;
            }

			nod = nod.rightChild;
            
            while(nod !=  null){

                stack.push(nod);
                nod = nod.leftChild;                    
            }	

		}
	}
	/*END of solution: In-order 
    traversal w/ iteration*/




	// preOrderTraverseTree : i) X ii) X.left iii) X.right
	public void preorderTraverseTree(Node focusNode) {

		if (focusNode != null) {

			System.out.println(focusNode);
			preorderTraverseTree(focusNode.leftChild);
			preorderTraverseTree(focusNode.rightChild);
		}
	}


	// postOrderTraverseTree : i) X.left ii) X.right iii) X
	public void postOrderTraverseTree(Node focusNode) {

		if (focusNode != null) {

			preorderTraverseTree(focusNode.leftChild);
			preorderTraverseTree(focusNode.rightChild);
			System.out.println(focusNode);
		}		
	}




	// get certain node from it's key
	/* this method may not be correct */
	public Node findNode(int key) {

		Node focusNode = root;

		while (focusNode.key != key) {

			if ( key < focusNode.key) {
				focusNode = focusNode.leftChild;
			} 

			else {
				focusNode = focusNode.rightChild;
			}

			if (focusNode == null) {
                return null;
            }
		}

		return focusNode;
	}





	/*question: design an alogorithm to remove 
	a node w/ given key from binary search tree*/
	public boolean remove(int key) {

		Node focusNode = root, parent;

        // determine the relationship w/ the parent 
		boolean isItALeftChild = true;

		/*we get the node needs to be removed,  it's parent and 
		the  relation between removable node and it's parent */
		while (focusNode.key != key) {

			parent = focusNode;

			if (key < focusNode.key) {

				isItALeftChild = true;
				focusNode = focusNode.leftChild;
			} 

			else {

				isItALeftChild = false;
				focusNode = focusNode.rightChild;
			}

			if (focusNode == null) 
                return false;
		}

		// no child 
		if (focusNode.leftChild == null && focusNode.rightChild == null) {

			if (focusNode == root){
                root = null;
            }
				
			else if (isItALeftChild){
                parent.leftChild = null;
            }
								
			else {
                parent.rightChild = null;
            }				
		}


		// has left child 
		else if (focusNode.rightChild == null) {

			if (focusNode == root){
                root = focusNode.leftChild;
            }
				
			else if (isItALeftChild){
                parent.leftChild = focusNode.leftChild;
            }				

			else {
                parent.rightChild = focusNode.leftChild;
            }
		}



		//  has right child 
		else if (focusNode.leftChild == null) {

			if (focusNode == root){
                root = focusNode.rightChild;
            }
				
			else if (isItALeftChild){
                parent.leftChild = focusNode.rightChild;
            }
				
			else {
                parent.rightChild = focusNode.rightChild;
            }
		}


		// two children exits 		
		else {

			Node replacement = getReplacementNode(focusNode);

			// focus node doesn't have parent 
			if (focusNode == root){
                root = replacement;
            }
				
			// focus node has parent 
			else if (isItALeftChild){
                parent.leftChild = replacement;
            }
				
			else {
                parent.rightChild = replacement;
            }
				
			replacement.leftChild = focusNode.leftChild;	
		}

		return true;
	}


	public Node getReplacementNode(Node replacedNode) {

		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		Node focusNode = replacedNode.rightChild;

		while (focusNode != null) {

			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.leftChild;
		}


		if (replacement != replacedNode.rightChild){

			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
		}

		return replacement;
	}
	/*END of solution: design an alogorithm to remove 
	a given node from binary search tree*/







	/*By using a key, check whether the 
	node is inside of the BST or not*/
	public boolean isWithinBST (int n) {

		if (n == root.key ){
			return true;
		}

		else {

			Node focusNode = root;
			Node parent; 

			while( focusNode != null){

				parent = focusNode;

				if (focusNode != null){

					if ( n < focusNode.key ){
						focusNode = focusNode.leftChild;
					}

					else {
						focusNode = focusNode.rightChild;
					}
				}

				if (focusNode != null && n == focusNode.key ){
					return true;				
				}
			}
		}

		return false;
	}





	/*question: desing an algorithm to 
    get the node with it's key value*/ 
	public Node getNode (int n){

		if ( n == root.key ){
			return root;
		}

		else {

			Node focusNode = root;
			Node parent; 

			while( focusNode != null ){

				parent = focusNode;

				if ( focusNode != null ){

					if ( n < focusNode.key ){
						focusNode = focusNode.leftChild;
					}

					else {
						focusNode = focusNode.rightChild;
					}
				}

				if ( focusNode != null &&   n == focusNode.key ){
					return focusNode;				
				}
			}
		}

		return null;
	}
    /*END of solution: desing an algorithm 
    to get the node with it's key value*/






	/*
    * get the parent of using the key of certain node
    */ 
	public Node getParent (int n){

		// the int value is not inside the BST
		if (!isBST (n)){
            return null;
        }
			
		if ( n == root.key ){
            return null;
        }
					
		else {

			Node focusNode = root, parent; 

			while(focusNode != null){

				parent = focusNode;

				if (focusNode != null){

					if (n < focusNode.key){
                        focusNode = focusNode.leftChild;
                    }
								
					else {
                        focusNode = focusNode.rightChild;
                    } 											
				}

				if ( focusNode != null && n == focusNode.key){
					return parent;				
                }
			}
		}

		return null;
	}







	/*question : design an algorithm to Check 
    if a binary tree is a valid binary tree*/
	public static boolean isValidBST(Node root, int min, int max) {

		if (root == null) {
            return true; 
        }
	
        // Integer.MAXVALUE, Integer.MINVALUE
		return ( root.key > min ) && 
				( root.key < max ) && 
				isValidBST( root.leftChild, min, root.key ) && 
				isValidBST( root.rightChild , root.key, max ) ;
	}





    // 2nd solution of the validity 
    // in this case we will use queue data-structure, FIFO 
	public static boolean isValidBST(Node root){

		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(root);


		// using breadth first search 
	    while(!queue.isEmpty() ){    


            /*1. Queue.poll() removes the element from the queue, if there is no element it returns null 
            2. Queue.remove() do the same but throws NoSuchElementException exception in case 
            there is no element

            3. Queue.peek() only copy the value and if there is no elelemnt, provides NULL
            4. Queue.element() only copy the value and if there is no elelemnt, throws exception 

            5. Queue.offer() returns a special value (either null or false, depending on the operation).
            6. Queue.add() throws an exception if the operation fails*/

		    Node cur = queue.poll();

		    if ((cur.leftChild != null && cur.key < cur.leftChild.key ) || (cur.rightChild != null && cur.key > cur.rightChild.key)){
		          return false;
		    }

            queue.offer(cur.leftChild);
            queue.offer(cur.rightChild);				
        }    

	    return queue.isEmpty(); 
	}





	/*question: design an algorithm to find 
    whether a binary search tree is valid BST*/	
    public static boolean isValid1 (Node node) {

		if (node ==  null) {
            return true; 
        }

	    if (node.leftChild != null && 
                                (node.key < node.leftChild.key || !isValid1(node.leftChild))){
	        return false;
        }

	    else if (node.rightChild != null && (node.key > node.rightChild.key 
            || !isValid1(node.rightChild))){

	        return false;
        }

	    else 
	        return true;

	}
	/*ENd of solution: design an algorithm to find 
    whether a binary search tree is valid bst*/





	// check if a BST is valid using LinkedList, FIFO
	public static boolean isValid2 (Node root) {


	    LinkedList<Node> nodesToCheck = new LinkedList<>();

        // offer and add almost same
	    nodesToCheck.offer(root);        

	    while (!nodesToCheck.isEmpty()){

	        Node current = nodesToCheck.poll();

            // make an iterative search over 
            // the whole tree w/ conditions 
	        if (current.leftChild != null) {

	            if (current.key < current.leftChild.key)
	                return false;

	            nodesToCheck.offer(current.leftChild);
	        }


	        if (current.rightChild != null){

	            if (current.key > current.rightChild.key)
	                return false;

	            nodesToCheck.offer(current.rightChild);
	        }
	    }

	    return true;
	}
	// END of the solution 






	/*question: design an algorithm to create a 
	binary search tree from passing an array*/ 
    // print the root in the end 
	public Node createBstFromArray( int[] array ){

        // assuming that we are using an unique array 
		if (array.length > 0){

			root =  new Node(array[0]);

			Queue<Node> queue =  new LinkedList<Node>();
			queue.add(root);

			boolean processEnd = false; 
			int j = 1;

			while(!processEnd ){


				// get the current element of the queue 
                // similar to the peek(), it doesn't remove/ poll from the ll 
				Node newNode = (Node) queue.element();

				if (newNode.leftChild == null &&  array[j] < newNode.key ){

					newNode.leftChild = new Node( array[j] );
					j++; 
					queue.add(newNode.leftChild);
				}

				else if ( newNode.rightChild ==  null && array[j] > newNode.key ) {

					newNode.rightChild = new Node( array[j] );
					j++; 
					queue.add( newNode.rightChild );
				}

				else {

                    // is this possible that queue gets null before 
                    // reaching to the condition, array.length == j 
					queue.remove();
				}

				if (j == array.length) {                    
                    processEnd = true; 
                }
			}

			return root; 
		}

		else 
            return null; 		
	}
    /*END of solution: design an algorithm to 
    create a binary search tree from passing an 
    array*/ 









	/*quesion 4-1 : design a algorithm to 
    check whther a binary tree is balanced*/	
	public static boolean isBalanced( Node root) {


		if (root == null){
            return true;
        } 
		
		int heightDiff = depth( root.leftChild ) - depth( root.rightChild );

		if (Math.abs(heightDiff) > 1){
            return false;
        }

		else {
            return isBalanced(root.leftChild) && isBalanced(root.rightChild);			
        }

	}

    // get the height of binary tree 
    public int depth(Node root) {

        if (root == null) {
            return 0;
        }
        
        Node focusNode = root; 

        int leftHeight = focusNode.leftChild != null ? depth( focusNode.leftChild) : 0;
        int rightHeight = focusNode.rightChild != null ? depth( focusNode.rightChild) : 0;

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static int depth1(Node node){

        if (node == null) {
            return 0;
        }

        else {
            return 1 + Math.max(depth(node.leftChild), depth(node.rightChild));
        }
    }
	/*END solution 4-1: design a algorithm to 
    check whther a binary tree is balanced*/    







	/*question 4-2:  Given a directed graph, design an 
    algorithm to  find out  whether  there  is a route 
    between two nodes*/
	public enum State {
		Unvisited, Visited, Visiting;
	} 

	public static Graph createNewGraph(){

		Graph g = new Graph(6);  

		Node [] temp = new Node[6];


        /*

               0
             / | \
            1  2  3
                  |
                  4
                  |
                  5
        */



        // create a graph with name and number of adjacent nodes 
		temp[0] = new Node("a", 3);
		temp[1] = new Node("b", 0);
		temp[2] = new Node("c", 0);
		temp[3] = new Node("d", 1);
		temp[4] = new Node("e", 1);
		temp[5] = new Node("f", 0);


		temp[0].addAdjacent(temp[1]);
		temp[0].addAdjacent(temp[2]);
		temp[0].addAdjacent(temp[3]);

		temp[3].addAdjacent(temp[4]);
		temp[4].addAdjacent(temp[5]);

		for (int i = 0; i < 6; i++) {
			g.addNode(temp[i]);
		}

		return g;
	}


    /*
    * using breadth first search
    */
    public static boolean search(Graph g, Node start, Node end) {  

        LinkedList<Node> q = new LinkedList<Node>();

        for ( Node u : g.getNodes()) {
            u.state = State.Unvisited;
        }

        start.state = State.Visiting;

        q.add(start);
        Node u;

        /*
        ----------
        ALGORITHMS
        ----------
        i.   Take the nodes in the one deeper level of the 
             present level. 

        ii.  If any if them is the destination, return true.

        iii. Otherwise, add them in the queue and repeat the 
             process.
        */

        while(!q.isEmpty()) {

            /*
            * Remove the first element, so, it's empty
            */  
            // u = q.removeFirst();
            u = q.poll();

            if (u != null) {

	            for ( Node v : u.getAdjacent()) {
	            	
	                if (v.state == State.Unvisited) {

	                    if (v == end) {
	                        return true;
	                    } 
	                    
	                    else {
	                        v.state = State.Visiting;
	                        q.add(v);
	                    }
	                }
	            }

	            u.state = State.Visited;
            }
        }

        return false;
    }    
	/* ENd solution 4-2 Given a directed graph, design an algorithm to 
	find out whether there is a route be- tween two nodes. */








	/*question 4-3: create minimum 
    BST  from  a  sorted   array*/
    public void createBalancedTree2(int array[]) {

        // convert the array into a tree and plant it in this
        Arrays.sort(array);
        root = createBalancedTree2( array, 0, array.length - 1 );
    }

	private  Node createBalancedTree2(int arr[], int start, int end){

        // empty array -> empty tree
	    if (end < start ) {
            return null; 
        }

	    // when start == end, the mid = start 
        // avoid overflow
	    int mid = (start + end) / 2;   

	    // create a tip node
	    Node node = new Node(arr[mid]);

	    // convert remaining subarrays (if any) into subtrees
	    node.leftChild = createBalancedTree2(arr, start, mid - 1);
	    node.rightChild = createBalancedTree2(arr, mid + 1, end);

	    return node;
	} 	
	/*END of solution 4-3: create 
    minimum BST from a sorted array*/







	/*question 4-4 create linked list 
    of the same level of the tree*/

    /*solution - a, with "breadth first search"*/
    public static ArrayList<LinkedList<Node>> createLevelLinkedList(Node root) {

		ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();
		
		/* "Visit" the root */
		LinkedList<Node> current = new LinkedList<Node>();

		if (root != null) {
			current.add(root);
		}
		
		while (current.size() > 0){

            /*
				ALGORITHM
				---------

				i. add current nodes to the parent
				ii create a new instance of current to adjoin the childs of the parent 
            */

			result.add(current); // Add previous level
			LinkedList<Node> parents = current; // Go to next level

			current = new LinkedList<Node>(); 

			for (Node parent : parents){

				if (parent.leftChild != null) {
					current.add(parent.leftChild);
				}

				if (parent.rightChild != null) {
					current.add(parent.rightChild );
				}
			}								
		}
		
        return result;
	}


    // print values in the same level of the tree gradually 
	public static void printResult(ArrayList<LinkedList<Node>> result) {

		int depth = 0;		

		for(LinkedList<Node> entry : result) {

			Iterator<Node> i = entry.listIterator();
			System.out.print("Link list at depth " + depth + ":");

			while(i.hasNext()){

				System.out.print(" " + ((Node)i.next()).key);
			}

			System.out.println();
			depth++;	
		}		
	}
	/*END solution -a : BFS*/






	/*solution -b : w/ depth first search (DFS)*/
    public static ArrayList<LinkedList<Node>> createLevelLinkedList1( Node root ) {

        ArrayList< LinkedList<Node> > lists = new ArrayList<LinkedList<Node>>();

        createLevelLinkedList1(root, lists, 0);
        return lists;
    }

	public static void createLevelLinkedList1( Node root, ArrayList<LinkedList<Node>> lists, int level){

        /*
        ALGORITHM
        ---------

            i.   if the list size is the same as the level, create a new instance of list

            ii.  otherwise, get the list using the level

            iii. add the node in the list

            iv. continue the recursive process till where is no child
        */

		if (root == null) {
            return;
        }

		LinkedList<Node> list = null;

		if (lists.size() == level) {
			list = new LinkedList<Node>();
			lists.add(list);  
		} 

		else {
			list = lists.get(level);
        }
		
		list.add(root);

		createLevelLinkedList1(root.leftChild, lists, level + 1);
		createLevelLinkedList1(root.rightChild, lists, level + 1);
	}	
	/* END of solution -b */

	/*ENd of solution 4-4:create linked list 
	of the same level of the tree*/







    /*question 4-5: design an algorithm to find 
    the ‘next’ node (e.g., in-order successor) of 
    a given node in a binary search tree*/
	public  Node inorderSucc( Node n) { 


        /*
        In-order successor of a node is the next 
        node in Inorder traversal of the binary 
        tree 

                     7               
                    / \       
                   /   \      
                  /     \     
                 /       \    
                 5       14       
                / \     / \   
               /   \   /   \  
               4   6   8   18   
                       \
                        9                    
        */ 

        /*
        ALGORITHM
        ---------

        i.  if the parent node  not exist or the right clid exist, 
        retrun the left most child of right child

        ii. if have parent and don't have the right child, 
        */                        



		if (n == null) {
            return null;
        }
		
		if (getParent(n.key) == null || n.rightChild != null) { 
			return leftMostChild( n.rightChild ); 
		} 

        // have parent and dont have the right child
		else { 

			Node child = n;
			Node parent = getParent(child.key);
 
			while (parent != null && parent.leftChild != child){
				child = parent;
				parent = getParent(parent.key);
			} 

			return parent;
		}  
	} 

		
	/*
    * get the smallest node of the right sub-tree 
    */
	public  Node leftMostChild( Node n) {

		if (n == null) {
			return null;
		}

		while ( n.leftChild != null ) {
			n = n.leftChild; 
		}

		return n; 
	}
	/*END of solution 4-5: design an algorithm to find 
    the ‘next’ node (e.g., in-order successor) of 
    a given node in a binary search tree where each 
    node has a link to its parent.*/









	/*question 4-6 : find out the common 
	ancestor of the two nodes*/

	/*solution - a: we use this function to check 
	whether a node is inside of the tree BETTER SOLUTION*/

    // How to deal if the p and q is the same node ?
	public static Node commonAncestor2 (Node root, Node p, Node q) {

		// one or both of the nodes are not inside the tree
		if ( !covers2(root, p) || !covers2(root, q) ) { 
			return null;
		}

		// both nodes are inside the tree, 
        // now find their common ancestor

        // put a conditon here: if both nodes are the same, 
        // call a new method to find their parent and return 

        // else, call this method 
		return commonAncestorHelper( root, p, q );
	}

	// find a node from a bst by key using recursive method 
	public static boolean covers2 ( Node root, Node node) { 

		if ( root == null ) return false;
		if ( root == node ) return true;

		// if we have a true here, the return will be true 
		return covers2( root.leftChild , node ) || covers2(root.rightChild, node); 
	}

	public static Node commonAncestorHelper( Node root, Node p, Node q) {

		if (root == null) return null;
		
		boolean isponleft = covers2 ( root.leftChild , p );
		boolean isqonleft = covers2 ( root.leftChild , q );
 
		if (isponleft != isqonleft) return root;
		
		// nodes are the same sides
		Node childside = isponleft ? root.leftChild : root.rightChild;
		return commonAncestorHelper( childside, p, q );	
	}
	/*END solution - a*/



	/*solution - b*/
    static int TWONODESFOUND = 2;
    static int ONENODEFOUND = 1;
    static int NONODESFOUND = 0;

	public static Node commonAncestor ( Node root, Node p, Node q ) {

		if ( q == p && (root.leftChild == q || root.rightChild == q) )  
			return root;

		// checks every nodes of the left-subtree of the root 
		int nodesFromLeft = covers( root.leftChild, p, q ); // Check left side

        // both of the nodes are in the left 
        // sub-tree of the original root 
		if ( nodesFromLeft == TWONODESFOUND ) {
			
			if( root.leftChild == p || root.leftChild == q ) 
				return root.leftChild;

			else 
				return commonAncestor( root.leftChild , p, q );
		} 

        // one of the node exists in the left sub-tree
        // if the other node present is the root || exists 
        // in the right sub-tree, then, the root is common ancestor 
		else if ( nodesFromLeft == ONENODEFOUND ) {

			if (root == p) return p;
			else if (root == q) return q;
		}

		// check every nodes of the 
        // right side of the root node
		int nodesFromRight = covers( root.rightChild, p, q ); // Check right side

		if (nodesFromRight == TWONODESFOUND) {

			if(root.rightChild == p || root.rightChild == q) 
				return root.rightChild;

			else 
				return commonAncestor( root.rightChild , p, q );
		} 

		else if ( nodesFromRight == ONENODEFOUND ) {

			if (root == p) 
				return p;

			else if (root == q) 
				return q;
		}

		if (nodesFromLeft == ONENODEFOUND && nodesFromRight == ONENODEFOUND) 
			return root;

		else 
            return null;
	}


	public static int covers(Node root, Node p, Node q ){
		
        int ret = NONODESFOUND;

        if (root == null) 
        	return ret;

        if (root == p || root == q) 
        	ret += 1;

        ret += covers( root.leftChild , p, q );

        if( ret == TWONODESFOUND )
        	return ret;

        return ret + covers( root.rightChild , p, q );
	}
	/*END solution - b*/

	/*END of solution 4-6: find out the 
	common ancestor of the two nodes*/







	/*question 4-7: two very large binary 
    trees: T1, with millions of nodes, and 
    T2, with hundreds of nodes. Create an 
    algorithm to decide if T2 is a subtree 
    of T1.*/	
	public static boolean containsTree( Node t1, Node t2) {

		if (t2 == null)
			return true;

		else
			return subTree(t1, t2);
	}
	
	// r1 is the big tree, r2 is the small tree 
	public static boolean subTree( Node r1, Node r2 ){

		if (r1 == null) return false; // big tree empty & subtree still not found.

		/* root is the same */
		if (r1.key == r2.key) {

			if (matchTree(r1,r2)) return true;		
		}	

		return subTree(r1.leftChild , r2) || subTree(r1.rightChild , r2); 
	}


	public static boolean matchTree( Node r1, Node r2) {
		
        // for the sub-tree, leaf-to-leaf match needed 
        // nothing left in the subtree
		if (r1 == null && r2 == null) {
			return true; 
        }

        //  big tree empty & subtree still not found
		if (r1 == null || r2 == null) {
			return false; 
        }

        // data doesn’t match
		if ( r1.key != r2.key ) {
			return false;  
        }

		return (matchTree( r1.leftChild, r2.leftChild) 
               && 
               matchTree( r1.rightChild , r2.rightChild));
				
	}

	/* END solution 4-7:two very large binary 
    trees: T1, with millions of nodes, and 
    T2, with hundreds of nodes. Create an 
    algorithm to decide if T2 is a subtree 
    of T1.*/








	/*question 4-8 : design an algorithm to 
    find all the paths of a BST which sums 
    are equal to certain value*/
	public static void findSum( Node node, int sum) {

		int depth = depth(node);

		// primitives initiated with zeros 
		int[] path = new int[depth];
		findSum( node, sum, path, 0 );
	}

	public static void findSum( Node node, int sum, int[] path, int level ) {

		if (node == null ) return; 
		
		path[level] = node.key; 		
		int t = 0;

		for (int i = level; i >= 0; i-- ){

			t += path[i];

			if (t == sum) {

				print(path, i, level);
			}
		}
		
		findSum( node.leftChild, sum, path, level + 1);
		findSum( node.rightChild , sum, path, level + 1);

		//Remove current node from path. 
		path[level] = Integer.MINVALUE; 
	}
	
	private static void print(int[] path, int start, int end) {

		for (int i = start; i <= end; i++) {

			System.out.print( path[i] + " " );
		}

		System.out.println();
	}	
	/* END solution 4-8 : design an algorithm to find all the 
	paths which sums are equal to certain value */



	





	public static void main(String[] args) {

        /*question: suffix tree*/
        String testString = "mississippi";
        String[] stringList = {"is", "sip", "hi", "sis"};
        SuffixTree tree = new SuffixTree(testString);

        for (String s : stringList) {
            ArrayList<Integer> list = tree.search(s);
            if (list != null) {
                System.out.println(s + ": " + list.toString());
            }
        }
        /*END of solution: suffix tree*/


        /*question: median for the heap*/
        int arraySize = 10;
        int range = 7;

        maxHeapComparator = new MaxHeapComparator();
        minHeapComparator = new MinHeapComparator();
        maxHeap = new PriorityQueue<Integer>(arraySize - arraySize/2, maxHeapComparator);
        minHeap = new PriorityQueue<Integer>(arraySize/2, minHeapComparator);
        
        for(int i = 0; i < arraySize; i++) {
            int randomNumber = (int) (Math.random( ) * (range+1));
            addNewNumberAndPrintMedian(randomNumber);
        }
        /*END of solution: median for the heap*/




		/* Start the bianry search tree */		
		int[] myArr = { 555, 876, 100 , 90, 5, 3, 1, 4, 8, 45, 77, 2, 6, 56 }; 
		BinaryTree myTr = new BinaryTree();
				
		for( int j=0; j < myArr.length; j++){
			myTr.addNode(myArr[j]);
		}

		inOrderTraverseTree2(myTr.root);
		/* End of the initializatin */



		/* question 4-1: check whether the tree is balanced */
		/*
		boolean isBalanced = myTr.isBalanced(myTr.root);
		if (isBalanced) {
			System.out.println("The tree is balanced \n\n");
		}
		*/
		/* END solution 4-1: check whether the tree is balanced */




		/* question 4-2: Given a directed graph, design an algorithm to 
		find out whether there is a route between two nodes.*/

		
		/*Graph g = createNewGraph();
		Node[] n = g.getNodes();
		Node start = n[3];
		Node end = n[5];

		printresult(g, start, end ); */
		
		/* END question 4-2 : Given a directed graph, design an algorithm to 
		find out whether there is a route between two nodes.*/








		/* question 4-3: create a binary tree with minimal height ( balanced tree )*/
		
		/*myTr.createMinimalBST(myArr);
		System.out.println("The root is = "+myTr.root);
		myTr.inOrderTraverseTree2(myTr.root);
		System.out.println("\n\n");*/
		
		/* END solution 4-3: create a binary tree with minimal height ( balanced tree )*/









		/*question 4-4 : create a linked list of all the 
		nodes in the same level of the BST,  BFS testing */ 		
		/*
		ArrayList<LinkedList<Node>> list = createLevelLinkedList(myTr.root);
		printResult(list);*/
		/* END solution 4-4 : create a linked list of all the 
		nodes in the same level of the BST,  BFS testing */ 









		/* question 4-5 : design an algorithm to find the 
		in-order successor of a given node */

		/*
			int myValue = 44 ;
			System.out.println( myTr.inorderSucc( myTr.getNode( myValue )) );
		*/
		/* END solution 4-5 : design an algorithm to find the 
		in-order successor of a given node */









		/* question 4-6 : get the first common ancestor of two nodes */		
		/*
		int firstNodeInteger = 7;
		int secondNodeInteger = 66;
		// try the first solution 
		System.out.println( myTr.commonAncestor( myTr.root, myTr.getNode(firstNodeInteger), myTr.getNode(secondNodeInteger) ));
		// try the second solution 
		System.out.println( myTr.commonAncestor2( myTr.root, myTr.getNode(firstNodeInteger), myTr.getNode(secondNodeInteger) ));
		*/
		/* END solution 4-6 : get the first common ancestor of two nodes */









		/* question 4-7 : check whether one tree is sub-set of the another tree */
		/*
		System.out.println("\n\n");
		int[] array1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		int[] array2 = {2, 4, 5, 8, 9, 10, 11};
		
		// Node t1 = myTr.createTreeFromArray(array1);
	    // Node t2 = myTr.createTreeFromArray(array2);

		Node t1 = myTr.createBstFromArray(array1);
	    Node t2 = myTr.createBstFromArray(array2);	    

	    // check the in-order traversal 
	    // myTr.inOrderTraverseTree(t1);
		// myTr.inOrderTraverseTree(t2);

		if (containsTree(t1, t2))
			System.out.println("t2 is a subtree of t1");

		else
			System.out.println("t2 is not a subtree of t1");

		int[] array3 = {1, 2, 3};
		Node t3 = myTr.createTreeFromArray(array1);
		Node t4 = myTr.createTreeFromArray(array3);

		if (containsTree(t3, t4))
			System.out.println("t4 is a subtree of t3");
		else
			System.out.println("t4 is not a subtree of t3");
		*/
		/* END solution 4-7 : check whether one tree is sub-set of the another tree */










		/*  question 4-8 : algorithm to get all the paths equal to given value */
		/*
		int testValue  = 8;
		System.out.println( myTr.root + " is the root");
		myTr.findSum( myTr.root, testValue );
		*/
		/*END solution 4-8 : algorithm to get all the paths equal to given value*/







		/*START OF THE GRAPH PROBLEMS*/ 

		/* question : design an  algorithm to check whether Undirected Graph is Connected using DFS*/
		
		/*
		int numberofnodes, source;
        Scanner scanner = null;

	 	try{

	    	System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            numberofnodes = scanner.nextInt();
 
		    int adjacencymatrix[][] = new int[numberofnodes + 1][numberofnodes + 1];
		    System.out.println("Enter the adjacency matrix");

		    for (int i = 1; i <= numberofnodes; i++)
		       for (int j = 1; j <= numberofnodes; j++)
	                   adjacencymatrix[i][j] = scanner.nextInt();
	 
	   	    for (int i = 1; i <= numberofnodes; i++)
	            {
	                for (int j = 1; j <= numberofnodes; j++)
	                {	
	                     if (adjacencymatrix[i][j] == 1 && adjacencymatrix[j][i] == 0)
	                     {
	                         adjacencymatrix[j][i] = 1;
	                     }
	                }
	            }			
 
	   	    System.out.println("Enter the source for the graph");
            source = scanner.nextInt(); 
 
            UndirectedConnectivityDfs undirectedConnectivity= new UndirectedConnectivityDfs();
            undirectedConnectivity.dfs(adjacencymatrix, source);	
 
        }

        catch(InputMismatchException inputMismatch){

            System.out.println("Wrong Input format");
        }	

        scanner.close();
        */
		/* END solution : design an  algorithm to check whether Undirected Graph is Connected using DFS*/










		/* question : design an  algorithm to check  whether Directed Graph is Connected using DFS*/

		/*
		int numberofnodes, source;
        Scanner scanner = null;
 		try
        {
	    System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            numberofnodes = scanner.nextInt();
 
	    int adjacencymatrix[][] = new int[numberofnodes + 1][numberofnodes + 1];
	    System.out.println("Enter the adjacency matrix");
	    for (int i = 1; i <= numberofnodes; i++)
	       for (int j = 1; j <= numberofnodes; j++)
                   adjacencymatrix[i][j] = scanner.nextInt();			
 
	    System.out.println("Enter the source for the graph");
            source = scanner.nextInt(); 
 
            DirectedConnectivityDfs directedConnectivity= new DirectedConnectivityDfs();
            directedConnectivity.dfs(adjacencymatrix, source);	
 
        }catch(InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input format");
        }	
        scanner.close();	*/

		/* END of solution: design an  algorithm to check  whether Directed Graph is Connected using DFS*/










		/* question : design an  algorithm to Check whether Directed Graph is Connected using BFS */

		/*
		int numbernonodes, source;
        Scanner scanner = null;
 
        try {

            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            numbernonodes = scanner.nextInt();
 
            int adjacencymatrix[][] = new int[numbernonodes + 1][numbernonodes + 1];
            System.out.println("Enter the adjacency matrix");
            for (int i = 1; i <= numbernonodes; i++)
                for (int j = 1; j <= numbernonodes; j++)
                    adjacencymatrix[i][j] = scanner.nextInt();
 
            System.out.println("Enter the source for the graph");
            source = scanner.nextInt();
 
            DirectedConnectivityBFS directedConnectivity= new DirectedConnectivityBFS();
            directedConnectivity.bfs(adjacencymatrix, source);
 
        } catch (InputMismatchException inputMismatch)
        {
            System.out.println("Wrong Input Format");
        }
        scanner.close();*/

		/* END of solution : design an  algorithm to Check whether Directed Graph is Connected using BFS */










		/* question : design an algorithm to traverse agraph using BFS */

		/*
		int numbernonodes, source;
        Scanner scanner = null;
 
        try
        {
            System.out.println("Enter the number of nodes in the graph");
            scanner = new Scanner(System.in);
            numbernonodes = scanner.nextInt();
 
            int adjacencymatrix[][] = new int[numbernonodes + 1][numbernonodes + 1];
            System.out.println("Enter the adjacency matrix");
            for (int i = 1; i <= numbernonodes; i++)
                for (int j = 1; j <= numbernonodes; j++)
                    adjacencymatrix[i][j] = scanner.nextInt();
 
            System.out.println("Enter the source for the graph");
            source = scanner.nextInt();
 
            System.out.println("The BFS traversal of the graph is ");
            BFS bfs = new BFS();
            bfs.bfs(adjacencymatrix, source);
            System.out.println("\n\n");
 
        } 
        catch (InputMismatchException inputMismatch){

            System.out.println("Wrong Input Format");
        }
        scanner.close();
        */
		/* END of solution : design an algorithm to traverse agraph using BFS */










		/* question : design an algorithm to traverse agraph using DFS  
		the program starts with node 0 */
		/*
	

        try {
        	
              int adjacencymatrix[][] = { 
                                             { 0, 1, 1, 0, 0 },
                                             { 0, 0, 0, 1, 0 },
                                             { 0 ,0, 0, 1, 0 },
                                             { 0, 0, 0, 0, 1 }, 
                                             { 0, 0, 0, 0, 0 }
                                          };                            
             
              int source = 0; 
       
              System.out.println("The DFS Traversal for the graph is given by ");
              DFS dfs = new DFS();
              dfs.dfs(adjacencymatrix, source);          

        }


        catch (InputMismatchException inputMismatch){

              System.out.println("Wrong Input format");
        }

        System.out.println("\n");
        */	
		/* END : design an algorithm to traverse agraph using DFS */


		/*END OF THE GRAPH PROBLEMS*/

	}

}
