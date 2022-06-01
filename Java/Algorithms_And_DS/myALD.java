
// Knapsack with Duplicate Items  



  // Top 10 Algorithms and Data Structures for Competitive Programming 
  // url : <http://www.geeksforgeeks.org/top-algorithms-and-data-structures-for-competitive-programming/>
  // implement the common data-structures here


  /*question: design a program to implement 
  minimum heap*/




  /* Fibonacci Heap Implementation*/
  class FibonacciHeap<T> {
    //~ Static fields/initializers ---------------------------------------------

    private static final double oneOverLogPhi = 1.0 / Math.log((1.0 + Math.sqrt(5.0)) / 2.0);

    //~ Instance fields --------------------------------------------------------

    /**
     * Points to the minimum node in the heap.
     */
    private FibonacciHeapNode<T> minNode;

    /**
     * Number of nodes in the heap.
     */
    private int nNodes;

    //~ Constructors -----------------------------------------------------------

    /**
     * Constructs a FibonacciHeap object that contains no elements.
     */
    public FibonacciHeap()
    {
    } // FibonacciHeap

    //~ Methods ----------------------------------------------------------------

    /**
     * Tests if the Fibonacci heap is empty or not. Returns true if the heap is
     * empty, false otherwise.
     *
     * <p>Running time: O(1) actual</p>
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return minNode == null;
    }

    // isEmpty

    /**
     * Removes all elements from this heap.
     */
    public void clear()
    {
        minNode = null;
        nNodes = 0;
    }

    // clear

    /**
     * Decreases the key value for a heap node, given the new value to take on.
     * The structure of the heap may be changed and will not be consolidated.
     *
     * <p>Running time: O(1) amortized</p>
     *
     * @param x node to decrease the key of
     * @param k new key value for node x
     *
     * @exception IllegalArgumentException Thrown if k is larger than x.key
     * value.
     */
    public void decreaseKey(FibonacciHeapNode<T> x, double k)
    {
        if (k > x.key) {
            throw new IllegalArgumentException(
                "decreaseKey() got larger key value");
        }

        x.key = k;

        FibonacciHeapNode<T> y = x.parent;

        if ((y != null) && (x.key < y.key)) {
            cut(x, y);
            cascadingCut(y);
        }

        if (x.key < minNode.key) {
            minNode = x;
        }
    }

    // decreaseKey

    /**
     * Deletes a node from the heap given the reference to the node. The trees
     * in the heap will be consolidated, if necessary. This operation may fail
     * to remove the correct element if there are nodes with key value
     * -Infinity.
     *
     * <p>Running time: O(log n) amortized</p>
     *
     * @param x node to remove from heap
     */
    public void delete(FibonacciHeapNode<T> x)
    {
        // make x as small as possible
        decreaseKey(x, Double.NEGATIVE_INFINITY);

        // remove the smallest, which decreases n also
        removeMin();
    }

    // delete

    /**
     * Inserts a new data element into the heap. No heap consolidation is
     * performed at this time, the new node is simply inserted into the root
     * list of this heap.
     *
     * <p>Running time: O(1) actual</p>
     *
     * @param node new node to insert into heap
     * @param key key value associated with data object
     */
    public void insert(FibonacciHeapNode<T> node, double key)
    {
        node.key = key;

        // concatenate node into min list
        if (minNode != null) {
            node.left = minNode;
            node.right = minNode.right;
            minNode.right = node;
            node.right.left = node;

            if (key < minNode.key) {
                minNode = node;
            }
        } else {
            minNode = node;
        }

        nNodes++;
    }

    // insert

    /**
     * Returns the smallest element in the heap. This smallest element is the
     * one with the minimum key value.
     *
     * <p>Running time: O(1) actual</p>
     *
     * @return heap node with the smallest key
     */
    public FibonacciHeapNode<T> min()
    {
        return minNode;
    }

    // min

    /**
     * Removes the smallest element from the heap. This will cause the trees in
     * the heap to be consolidated, if necessary.
     *
     * <p>Running time: O(log n) amortized</p>
     *
     * @return node with the smallest key
     */
    public FibonacciHeapNode<T> removeMin()
    {
        FibonacciHeapNode<T> z = minNode;

        if (z != null) {
            int numKids = z.degree;
            FibonacciHeapNode<T> x = z.child;
            FibonacciHeapNode<T> tempRight;

            // for each child of z do...
            while (numKids > 0) {
                tempRight = x.right;

                // remove x from child list
                x.left.right = x.right;
                x.right.left = x.left;

                // add x to root list of heap
                x.left = minNode;
                x.right = minNode.right;
                minNode.right = x;
                x.right.left = x;

                // set parent[x] to null
                x.parent = null;
                x = tempRight;
                numKids--;
            }

            // remove z from root list of heap
            z.left.right = z.right;
            z.right.left = z.left;

            if (z == z.right) {
                minNode = null;
            } else {
                minNode = z.right;
                consolidate();
            }

            // decrement size of heap
            nNodes--;
        }

        return z;
    }

    // removeMin

    /**
     * Returns the size of the heap which is measured in the number of elements
     * contained in the heap.
     *
     * <p>Running time: O(1) actual</p>
     *
     * @return number of elements in the heap
     */
    public int size()
    {
        return nNodes;
    }

    // size

    /**
     * Joins two Fibonacci heaps into a new one. No heap consolidation is
     * performed at this time. The two root lists are simply joined together.
     *
     * <p>Running time: O(1) actual</p>
     *
     * @param h1 first heap
     * @param h2 second heap
     *
     * @return new heap containing h1 and h2
     */
    public static <T> FibonacciHeap<T> union(
        FibonacciHeap<T> h1,
        FibonacciHeap<T> h2)
    {
        FibonacciHeap<T> h = new FibonacciHeap<T>();

        if ((h1 != null) && (h2 != null)) {
            h.minNode = h1.minNode;

            if (h.minNode != null) {
                if (h2.minNode != null) {
                    h.minNode.right.left = h2.minNode.left;
                    h2.minNode.left.right = h.minNode.right;
                    h.minNode.right = h2.minNode;
                    h2.minNode.left = h.minNode;

                    if (h2.minNode.key < h1.minNode.key) {
                        h.minNode = h2.minNode;
                    }
                }
            } else {
                h.minNode = h2.minNode;
            }

            h.nNodes = h1.nNodes + h2.nNodes;
        }

        return h;
    }

    // union

    /**
     * Creates a String representation of this Fibonacci heap.
     *
     * @return String of this.
     */
    public String toString()
    {
        if (minNode == null) {
            return "FibonacciHeap=[]";
        }

        // create a new stack and put root on it
        Stack<FibonacciHeapNode<T>> stack = new Stack<FibonacciHeapNode<T>>();
        stack.push(minNode);

        StringBuffer buf = new StringBuffer(512);
        buf.append("FibonacciHeap=[");

        // do a simple breadth-first traversal on the tree
        while (!stack.empty()) {
            FibonacciHeapNode<T> curr = stack.pop();
            buf.append(curr);
            buf.append(", ");

            if (curr.child != null) {
                stack.push(curr.child);
            }

            FibonacciHeapNode<T> start = curr;
            curr = curr.right;

            while (curr != start) {
                buf.append(curr);
                buf.append(", ");

                if (curr.child != null) {
                    stack.push(curr.child);
                }

                curr = curr.right;
            }
        }

        buf.append(']');

        return buf.toString();
    }

    // toString

    /**
     * Performs a cascading cut operation. This cuts y from its parent and then
     * does the same for its parent, and so on up the tree.
     *
     * <p>Running time: O(log n); O(1) excluding the recursion</p>
     *
     * @param y node to perform cascading cut on
     */
    protected void cascadingCut(FibonacciHeapNode<T> y)
    {
        FibonacciHeapNode<T> z = y.parent;

        // if there's a parent...
        if (z != null) {
            // if y is unmarked, set it marked
            if (!y.mark) {
                y.mark = true;
            } else {
                // it's marked, cut it from parent
                cut(y, z);

                // cut its parent as well
                cascadingCut(z);
            }
        }
    }

    // cascadingCut

    protected void consolidate()
    {
        int arraySize =
        ((int) Math.floor(Math.log(nNodes) * oneOverLogPhi)) + 1;

        List<FibonacciHeapNode<T>> array =
        new ArrayList<FibonacciHeapNode<T>>(arraySize);

        // Initialize degree array
        for (int i = 0; i < arraySize; i++) {
            array.add(null);
        }

        // Find the number of root nodes.
        int numRoots = 0;
        FibonacciHeapNode<T> x = minNode;

        if (x != null) {
            numRoots++;
            x = x.right;

            while (x != minNode) {
                numRoots++;
                x = x.right;
            }
        }

        // For each node in root list do...
        while (numRoots > 0) {
            // Access this node's degree..
            int d = x.degree;
            FibonacciHeapNode<T> next = x.right;

            // ..and see if there's another of the same degree.
            for (;;) {
                FibonacciHeapNode<T> y = array.get(d);
                if (y == null) {
                    // Nope.
                    break;
                }

                // There is, make one of the nodes a child of the other.
                // Do this based on the key value.
                if (x.key > y.key) {
                    FibonacciHeapNode<T> temp = y;
                    y = x;
                    x = temp;
                }

                // FibonacciHeapNode<T> y disappears from root list.
                link(y, x);

                // We've handled this degree, go to next one.
                array.set(d, null);
                d++;
            }

            // Save this node for later when we might encounter another
            // of the same degree.
            array.set(d, x);

            // Move forward through list.
            x = next;
            numRoots--;
        }

        // Set min to null (effectively losing the root list) and
        // reconstruct the root list from the array entries in array[].
        minNode = null;

        for (int i = 0; i < arraySize; i++) {
            FibonacciHeapNode<T> y = array.get(i);
            if (y == null) {
                continue;
            }

            // We've got a live one, add it to root list.
            if (minNode != null) {
                // First remove node from root list.
                y.left.right = y.right;
                y.right.left = y.left;

                // Now add to root list, again.
                y.left = minNode;
                y.right = minNode.right;
                minNode.right = y;
                y.right.left = y;

                // Check if this is a new min.
                if (y.key < minNode.key) {
                    minNode = y;
                }
            } else {
                minNode = y;
            }
        }
    }

    // consolidate

    /**
     * The reverse of the link operation: removes x from the child list of y.
     * This method assumes that min is non-null.
     *
     * <p>Running time: O(1)</p>
     *
     * @param x child of y to be removed from y's child list
     * @param y parent of x about to lose a child
     */
    protected void cut(FibonacciHeapNode<T> x, FibonacciHeapNode<T> y)
    {
        // remove x from childlist of y and decrement degree[y]
        x.left.right = x.right;
        x.right.left = x.left;
        y.degree--;

        // reset y.child if necessary
        if (y.child == x) {
            y.child = x.right;
        }

        if (y.degree == 0) {
            y.child = null;
        }

        // add x to root list of heap
        x.left = minNode;
        x.right = minNode.right;
        minNode.right = x;
        x.right.left = x;

        // set parent[x] to nil
        x.parent = null;

        // set mark[x] to false
        x.mark = false;
    }

    // cut

    /**
     * Make node y a child of node x.
     *
     * <p>Running time: O(1) actual</p>
     *
     * @param y node to become child
     * @param x node to become parent
     */
    protected void link(FibonacciHeapNode<T> y, FibonacciHeapNode<T> x)
    {
        // remove y from root list of heap
        y.left.right = y.right;
        y.right.left = y.left;

        // make y a child of x
        y.parent = x;

        if (x.child == null) {
            x.child = y;
            y.right = y;
            y.left = y;
        } else {
            y.left = x.child;
            y.right = x.child.right;
            x.child.right = y;
            y.right.left = y;
        }

        // increase degree[x]
        x.degree++;

        // set mark[y] false
        y.mark = false;
    }

    // link
}

// FibonacciHeap
/* 
 * JGraphT : a free Java graph-theory library
 * 
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (barak_naveh@users.sourceforge.net)
 *
 * (C) Copyright 2003-2007, by Barak Naveh and Contributors.
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/* --------------------------
 * FibonnaciHeapNode.java
 * --------------------------
 * (C) Copyright 1999-2007, by Nathan Fiedler and Contributors.
 *
 * Original Author:  Nathan Fiedler
 * Contributor(s):   John V. Sichi
 *
 * $Id: FibonacciHeapNode.java 568 2007-09-30 00:12:18Z perfecthash $
 *
 * Changes
 * -------
 * 03-Sept-2003 : Adapted from Nathan Fiedler (JVS);
 *
 *      Name    Date            Description
 *      ----    ----            -----------
 *      nf      08/31/97        Initial version
 *      nf      09/07/97        Removed FibHeapData interface
 *      nf      01/20/01        Added synchronization
 *      nf      01/21/01        Made Node an inner class
 *      nf      01/05/02        Added clear(), renamed empty() to
 *                              isEmpty(), and renamed printHeap()
 *                              to toString()
 *      nf      01/06/02        Removed all synchronization
 *      JVS     06/24/06        Generics
 *
 */

/**
 * Implements a node of the Fibonacci heap. It holds the information necessary
 * for maintaining the structure of the heap. It also holds the reference to the
 * key value (which is used to determine the heap structure).
 *
 * @author Nathan Fiedler
 */
class FibonacciHeapNode<T>
{
    //~ Instance fields --------------------------------------------------------

    /**
     * Node data.
     */
    T data;

    /**
     * first child node
     */
    FibonacciHeapNode<T> child;

    /**
     * left sibling node
     */
    FibonacciHeapNode<T> left;

    /**
     * parent node
     */
    FibonacciHeapNode<T> parent;

    /**
     * right sibling node
     */
    FibonacciHeapNode<T> right;

    /**
     * true if this node has had a child removed since this node was added to
     * its parent
     */
    boolean mark;

    /**
     * key value for this node
     */
    double key;

    /**
     * number of children of this node (does not count grandchildren)
     */
    int degree;

    //~ Constructors -----------------------------------------------------------

    /**
     * Default constructor. Initializes the right and left pointers, making this
     * a circular doubly-linked list.
     *
     * @param data data for this node
     * @param key initial key for node
     */
    public FibonacciHeapNode(T data, double key)
    {
        right = this;
        left = this;
        this.data = data;
        this.key = key;
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Obtain the key for this node.
     *
     * @return the key
     */
    public final double getKey()
    {
        return key;
    }

    /**
     * Obtain the data for this node.
     */
    public final T getData()
    {
        return data;
    }

    /**
     * Return the string representation of this object.
     *
     * @return string representing this object
     */
    public String toString()
    {
        if (true) {
            return Double.toString(key);
        } else {
            StringBuffer buf = new StringBuffer();
            buf.append("Node=[parent = ");

            if (parent != null) {
                buf.append(Double.toString(parent.key));
            } else {
                buf.append("---");
            }

            buf.append(", key = ");
            buf.append(Double.toString(key));
            buf.append(", degree = ");
            buf.append(Integer.toString(degree));
            buf.append(", right = ");

            if (right != null) {
                buf.append(Double.toString(right.key));
            } else {
                buf.append("---");
            }

            buf.append(", left = ");

            if (left != null) {
                buf.append(Double.toString(left.key));
            } else {
                buf.append("---");
            }

            buf.append(", child = ");

            if (child != null) {
                buf.append(Double.toString(child.key));
            } else {
                buf.append("---");
            }

            buf.append(']');

            return buf.toString();
        }
    }

    // toString
}
/* Fibonacci Heap Implementation*/
















/**
 * Minimum heap implementation. See [Cormen et al 1999] for formal theory. 
 * Maintains all elements in a min-heap, such that the minimum element will
 * be the top-most node in the heap at all times. Among many other uses, heaps are ideal for 
 * representing priority queues. 
 */
class Heap<T> {

  private int size;
  final private List<Node> heap;
  final private Comparator<T> comparator;

  private class Node {

    public T element;
    public int position;
}    

  /**
   * Create a new heap
   * @param comparator A comparator that handles elements of type T
   */
  public Heap( Comparator<T> comparator ) {
    size = 0;
    //Allocate space
    heap = new ArrayList<Node>();

    //Comparator
    this.comparator = comparator;

    //initialy clear
    //for (int i=0;i<maxSize;i++) heap[i] = null;
}


  /**
   * Insert element into the heap. O(lg n) where n is the number of elements/nodes in the heap  
   * @param element new element to be inserted
   */
  public void insert( final T element ) {

    size++;
    Node node = new Node();
    node.element = element;
    node.position = size-1;
    heap.add(node);
    decreaseKey( node );
    //return node;
}

public final void clear() {
    heap.clear();
    size = 0;
}

  /**
   * Return a reference to the top-most element on the heap. The method does not change the state
   * of the heap in any way. O(k).
   * @return Reference to top-most element of heap
   */
  public final T top() {
    return heap.get(0).element;
}

  //bound check missing

  /**
   * Pop an element of the heap. O(lg n) where n is the number of elements in heap.
   */
  public T pop() {
    T returnNode = top();
    exchange( 0, size-1 );
    heap.remove(size-1);
    size--;

    //if any elements left in heap, do minHeapify
    if (size>0) {
      minHeapify( heap.get(0) );
  }
  
  return returnNode;
}

  //  private final void reinsert( final Node n ) {
  //    if ( !decreaseKey(n) ) {
  //      minHeapify(n);
  //    }
  //  }

public final int size() {
    return size;
}


private final boolean decreaseKey( final Node node ) {
    int index = node.position;
    boolean modified = false;

    //    while ( index>0 &&  (heap[parent(index)]).compareTo( heap[index]) >= 0 ) {
    while ( index>0 &&  comparator.compare(heap.get(parent(index)).element, heap.get(index).element ) >= 0 ) {
      exchange( index, parent(index) );
      index = parent(index);
      modified = true;
  }

  return modified;
}

private final void minHeapify( final Node node ) {
    int smallest;
    int index = node.position;
    int left = left(index);
    int right = right(index);

    //  if (left<size && (heap[left]).compareTo(heap[index]) <= 0 )
    if (left<size && comparator.compare(heap.get(left).element, heap.get(index).element) <= 0 )
      smallest= left;
  else
      smallest = index;

    //    if (right<size && (heap[right]).compareTo(heap[smallest]) <=0 )
  if (right<size && comparator.compare(heap.get(right).element, heap.get(smallest).element ) <=0 )
      smallest= right;
  if (smallest!= index) {
      exchange( index, smallest );
      minHeapify( heap.get(smallest) );
  }
}

private final void exchange( final int index, final int index2 ) {
    Node temp = heap.get(index);
    temp.position = index2;

    Node temp2 = heap.get(index2);
    temp2.position = index;

    heap.set(index, temp2 );
    heap.set( index2, temp);


    //Update posistion in Node
    //    heap.get(index).position=index;
    //    heap.get(index2).position=index2;
}


private final int parent(final int i) {
    return i/2;
}

private final int left(final int i) {
    return 2*i;
}

private final int right(final int i) {
    return 2*i+1;
}

  /**
   * Returns an iterator that iterates over all elements of the heap, in no particular order
   * @return
   */
  public final Iterator<T> iterator() {
    return new Iterator<T>() {
      private Iterator<Node> iterator = heap.iterator(); 
      @Override
      public boolean hasNext() { return iterator.hasNext(); }
      @Override
      public T next() { return iterator.next().element; }
      @Override
      public void remove() { }
  };
}

  //  public void printHeap() {
  //    int step =1;
  //    int i = 0;
  //    for (int n=0;n<size;n++) {
  //      i++;
  //      //System.out.print(""+ ((Contact)heap[n].item).relativeV + "*");
  //      if (i%step == 0 ) {
  //        step *=2; i=0;
  //        System.out.println("");
  //      }
  //    }
  //
  //    System.out.println("");
  //  }
}
/*END of solution: min heap 
implementation*/











/*question: design a program 
to implement Map data-structure
and write the necessary unit tests*/
class MyEntry<K, V> {


  private final K key;
  private V value;

  public MyEntry(K key, V value) {

    this.key = key;
    this.value = value;
}

public K getKey() {

    return key;
}

public V getValue() {

    return value;
}

public void setValue(V value) {

    this.value = value;
}
} 



class MyMap<K, V> {


	private int size;
	private int DEFAULT_CAPACITY = 16;

	@SuppressWarnings("unchecked")
	private MyEntry<K, V>[] values; 

	public MyMap(){

		values = new MyEntry[DEFAULT_CAPACITY];
	}


	// containsKey(Object key)         // boolean
	// containsValue(Object value)     // boolean 
	// clear()                         //  void 


	// return type in V 
	public V put(K key, V value) {

     boolean insert = true;
     V prevVal = null; 

     for (int i = 0; i < size; i++) {

	        // update the key
       if (values[i].getKey().equals(key)) {

          prevVal = values[i].getValue();
          values[i].setValue(value);
          insert = false;
      }

  }

  if (insert) {

     ensureCapa();
     values[size++] = new HashEntry<K, V>(key, value);
 }

 return prevVal; 
}


private void ensureCapa() {

   if (size == values.length) {

     int newSize = values.length * 2;
     values = Arrays.copyOf(values, newSize);
 }
}


public V get(K key) {

   for (int i = 0; i < size; i++) {

     if (values[i] != null) {

       if (values[i].getKey().equals(key)) {

         return values[i].getValue();
     }

 }

}

return null;
}

public int size() {

   return size;
}



public V remove(K key) {

        // for Hashtable it will throw 
        // java.lang.NullPointerException exception 
    if( key == null ) return null; 

    V prev = null; 

    for (int i = 0; i < size; i++) {

       if (values[i].getKey().equals(key)) {

          prev = values[i];	        	
          values[i] = null;

          size--;
          condenseArray(i);
      }
  }

  return prev;
}


private void condenseArray(int start) {

    for (int i = start; i < size; i++) {

       values[i] = values[i + 1];
   }
}


public Set<K> keySet() {

    Set<K> set = new HashSet<K>();

    for (int i = 0; i < size; i++) {

       set.add(values[i].getKey());
   }

   return set;
}

} 



// write the unit tests for the implemented Map
class MyMapTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testMapMap() {

		// MyMap
        MyMap<String, Integer> map = new MyMap<String, Integer>();
        map.put("Lars", 1);
        map.put("Lars", 2);
        map.put("Lars", 1);
        assertEquals(map.get("Lars"), 1);

        for (int i = 0; i < 100; i++) {

           map.put(String.valueOf(i), i);
       }

       assertEquals(map.size(), 101);
       assertEquals(map.get("51"), 51);
   }

} 
/*END of solution: design a program 
to implement Map data-structure
and write the necessary unit tests*/










/*question: design a program to 
implement Stack data-structure and 
write necessary unit tests*/

/*List is an interface and Stack 
is an implementation of List*/

class MyStack<E> {


  private int size = 0;
  private static final int DEFAULT_CAPACITY = 10;
  private Object elements[];


  public MyStack() {

    elements = new Object[DEFAULT_CAPACITY];
}


    // null push() will return null as well  
public E push(E e) {

    if (size == elements.length) {

      ensureCapa();
  }

  elements[size++] = e;
  return e; 
}


private void ensureCapa() {

    int newSize = elements.length * 2;
    elements = Arrays.copyOf(elements, newSize);
}

public E pop() {

 if (size == 0) 
    throw new EmptyStackException();

E e = (E) elements[--size];
elements[size] = null;
return e;
}


public E peek(){

   if (size == 0) 
      throw new EmptyStackException();

  return (E)elements[size-1];
}

public int size(){

    return size;
}

public boolean isEmpty(){

    return size == 0;
}


    // write a customized toString method 
public String toString(){

    String str ="";
    int count =0;

    while(!isEmpty()){

        str += "\n" + (++count) + " " + pop() + " ";
    }

    return str; 
}

} 



MyStackTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testStackArray() {

        MyStack<Integer> stack = new MyStack<Integer>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(3);
        stack.push(4);

        assertTrue(4 == stack.pop());
        assertTrue(3 == stack.pop());
        assertTrue(3 == stack.pop());
        assertTrue(2 == stack.pop());
        assertTrue(1 == stack.pop());
    }

} 

/*END of solution: design a program to 
implement Stack data-structure and 
write necessary unit tests*/











/*question: design a program to 
implement Queue data-structure and 
write necessary unit tests*/


/*Queue is an interface and LinkedList 
is an implementation of Queue*/


/*solution-a*/
class MyQueue<E> {


  private int size = 0;
  private static final int DEFAULT_CAPACITY = 10;
  private Object elements[];


  public MyQueue() {

    elements = new Object[DEFAULT_CAPACITY];
}


    // offer(), add() return boolean 
    // if it's not possible to add, add() throws exception 
    // even adding null increases the size 
public Boolean offer(E e) {

    if (size == elements.length) {

      ensureCapa();
  }

  elements[size++] = e;
  return true;
}

    // offer(), add() return boolean 
public Boolean add (E e) {

    if (size == elements.length) {

      ensureCapa();
  }

  elements[size++] = e;
  return true;
}

private void ensureCapa() {

    int newSize = elements.length * 2;
    elements = Arrays.copyOf(elements, newSize);
}


    // poll() deletes the front element, condenses and returns boolean 
    // if there is no element in the queue, returns null  
    // doesn't take argument 
public Boolean poll() {

 if(size < 1 ) return null; 

 int index = 0;

 if(index < size){

    E obj = (E)elements[index];
    elements[index] = null;

    int tmp = index;

    while(tmp < size){

      elements[tmp] = elements[tmp+1];
      elements[tmp+1] = null;
      tmp++;
  }

  size--;
  return true;
} 

return false;      
}


    // remove() deletes the front element and returns boolean 
    // if there is no element in the queue, throws java.util.NoSuchElementException exception
    // take argument to delete particular element 
public Boolean remove() {

   if(size < 1 ) throw new NoSuchElementException(); 

   int index = 0;

   if(index < size){

      E obj = (E)elements[index];
      elements[index] = null;

      int tmp = index;

      while(tmp < size){

        elements[tmp] = elements[tmp+1];
        elements[tmp+1] = null;
        tmp++;
    }

    size--;
    return true;
} 

return false;      
}


    // peek() returns the front element w/o executing any deletetion 
    // if the Queue is empty, returns null 
public E peek(){

    if (size < 1 ) return null; 

    return (E)elements[0];
}

    // element() returns the front element w/o executing any deletetion 
    //  if the Queue is empty, throw exception NoSuchElementException
public E element(){

    if (size <1 ) throw new NoSuchElementException();
    return (E)elements[0];
}

public int size(){

    return size;
}
} 
/*END of solution-a*/


/*solution-b*/
class QueueEntry<E>{

    private E e; 

    public QueueEntry(E e ){

        this.e = e; 
    }

}

class MyQueue1<E> {

    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private QueueEntry<E> elements [];


    public MyQueue1() {

      elements = new QueueEntry[DEFAULT_CAPACITY];
  }


    // offer(), add() return boolean 
    // if it's not possible to add, add() throws exception 
  public Boolean offer(E e) {

      if (size == elements.length) {

        ensureCapa();
    }

    elements[size++] = new QueueEntry<E>(e);
    return true;
}

    // offer(), add() return boolean 
public Boolean add (E e) {

  if (size == elements.length) {

    ensureCapa();
}

elements[size++] = new QueueEntry<E>(e);
return true;
}

private void ensureCapa() {

  int newSize = elements.length * 2;
  elements = Arrays.copyOf(elements, newSize);
}

    // poll() deletes the front element, condenses and returns boolean 
    // if there is no element in the queue, returns null  
    // doesn't take argument 
public Boolean poll() {

   if(size < 1 ) return null; 

   int index = 0;

   if(index < size){

      E obj = (E)elements[index];
      elements[index] = null;

      int tmp = index;

      while(tmp < size){

        elements[tmp] = elements[tmp+1];
        elements[tmp+1] = null;
        tmp++;
    }

    size--;
    return true;
} 

return false;      
}


    // remove() deletes the front element and returns boolean 
    // if there is no element in the queue, throws java.util.NoSuchElementException exception
    // take argument to delete particular element 
public Boolean remove() {

    if(size < 1 ) throw new NoSuchElementException(); 

    int index = 0;

    if(index < size){

        E obj = (E)elements[index];
        elements[index] = null;

        int tmp = index;

        while(tmp < size){

          elements[tmp] = elements[tmp+1];
          elements[tmp+1] = null;
          tmp++;
      }

      size--;
      return true;
  } 

  return false;      
}



    // peek() returns the front element w/o executing any deletetion 
    // if the Queue is empty, returns null 
public E peek(){

    if (size < 1 ) return null; 

    return (E)elements[0];
}

    // element() returns the front element w/o executing any deletetion 
    //  if the Queue is empty, throw exception NoSuchElementException
public E element(){

    if (size <1 ) throw new NoSuchElementException();
    return (E)elements[0];
}

public int size(){

    return size;
}

} 
/*END OF SOLUTION-b*/




// implementation using node and ll 
/*solution-c*/
class QueueNode<E>{

    public E e; 
    public QueueNode next; 

    public QueueNode(E e ){

        this.e = e; 
    }

    public QueueNode( ){


    }
}

class MyQueue1<E> {

    private int size;
    // private static final int DEFAULT_CAPACITY = 10;
    // private QueueNode<E> elements [];

    QueueNode<E> head, tail; 

    public MyQueue1() {

        // elements = new QueueNode[DEFAULT_CAPACITY];
      this.head = null;
      this.tail = null; 

      size = 0; 
  }


    // offer(), add() return boolean 
    // if it's not possible to add, add() throws exception 
  public Boolean offer(E e) {

   QueueNode<E> node = new QueueNode<E>(e); 

   if(head ==  null && size == 0){

      head = node;
      tail = head; 

      size++; 
      return true; 
  }

  else if (size > 0) {

      tail.next = node;
      tail = node; 

      size++; 
      return true; 
  }

  return false;
}

    // offer(), add() return boolean 
public Boolean add(E e) {

    QueueNode<E> node = new QueueNode(e); 

    if(head ==  null && size == 0){

        head = node;
        tail = head; 

        size++; 
        return true; 
    }

    else if (size > 0) {

        tail.next = node;
        tail = node; 

        size++; 
        return true; 
    }

    return false;
}


public int size(){

    return size;
}



    // poll() deletes the front element, condenses and returns boolean 
    // if there is no element in the queue, returns null  
    // doesn't take argument 
public Boolean poll() {

    if( head ==  null && size == 0 ) return null; 

    else if (size > 0 ){

        head = head.next;
        --size;

        return true; 
    }

    return false;      
}


    // remove() deletes the front element and returns boolean 
    // if there is no element in the queue, throws java.util.NoSuchElementException exception
    // take argument to delete particular element 
public Boolean remove() {

    if(head == null && size < 1 ) throw new NoSuchElementException(); 

    else if (size > 1 ){

        head = head.next;
        size--;

        return true; 
    }

    return false;        
}


     // peek() returns the front element w/o executing any deletetion 
    // if the Queue is empty, returns null 
public E peek(){

    if (size < 1 ) return null; 

    return head.e;
}


    // element() returns the front element w/o executing any deletetion 
    //  if the Queue is empty, throw exception NoSuchElementException
public E element(){

    if (size <1 ) throw new NoSuchElementException();
    return head.e; 
}

} 
/*END OF SOLUTION-c*/



// test for solution-a
class MyQueueTest {


    // The queue to use in all the tests: set this in subclasses.    
    protected Queue q;

    @Test
    public void testNewQueueIsEmpty() {

      assertTrue(q.isEmpty());
      assertEquals(q.size(), 0);
  }


  @Test
  public void testInsertsToEmptyQueue() {

      int numberOfInserts = 6;

      for (int i = 0; i < numberOfInserts; i++) {

        q.offer("zzz");
    }

    assertTrue(!q.isEmpty());
    assertEquals(q.size(), numberOfInserts);
}


@Test
public void testEnqueueThenDequeue() {

  String message = "hello";
  q.offer(message);
  assertEquals(q.poll(), message);
}


@Test
public void testEnqueueThenPeek() {

  String message = "hello";
  q.offer(message);
  int size = q.size();

  assertEquals(q.peek(), message);
  assertEquals(q.size(), size);
}


@Test
public void testFiftyInThenFiftyOut() {

  for (int i = 0; i < 50; i++) {

    q.offer(i);
}

for (int i = 0; i < 50; i++) {

    assertEquals(((Integer)q.poll()).intValue(), i);
}
}


@Test
public void testRemovingDownToEmpty() {

  int numberOfRemoves = (int)(Math.random() * 20 + 1);

  for (int i = 0; i < numberOfRemoves; i++) {

    q.offer("zzz");
}

for (int i = 0; i < numberOfRemoves; i++) {

    q.poll();
}

assertTrue(q.isEmpty());
assertEquals(q.size(), 0);
}


@Test(expected=NoSuchElementException.class)
public void testRemoveOnEmptyQueue() {

  assertTrue(q.isEmpty());
  q.poll();
}


@Test(expected=NoSuchElementException.class)
public void testPeekIntoEmptyQueue() {

  assertTrue(q.isEmpty());
  q.poll();
}
}
/*END of solution: design a program to 
implement Queue data-structure*/







/*question: write a program to 
implement arraylist data-structure*/
class MyArrayList<E> {


    // convert the program to handle 
    // generic data-types 
  private Object[] arr;
  private int size = 0, DEFAULT_CAPACITY = 10;

  public MyArrayList(){

    arr = new Object[DEFAULT_CAPACITY];
}

public E get(int index){

    if(index < size){

      return arr[index];
  } 

  else {

      throw new ArrayIndexOutOfBoundsException();
  }
}


    // add needs to be boolean 
public boolean add(E obj){

    if( arr.length-size <= 5){

      increaseListSize();
  }

  arr[size++] = obj;

  return true;
}


    // returns the remove object by calling remove()
public E remove(int index){

    if(index < size){

      E obj = arr[index];
      arr[index] = null;

      int tmp = index;

      while(tmp < size){

        arr[tmp] = arr[tmp+1];
        arr[tmp+1] = null;
        tmp++;
    }

    size--;
    return obj;
} 

else {

  throw new ArrayIndexOutOfBoundsException();
}         
}


public int size(){

    return size;
}


private void increaseListSize(){

        // if the storage is almost finsihed, increased 
        // the array length by 2 times  
    arr = Arrays.copyOf(arr, arr.length*2);
}



}


// test class for MyArrayList
class myArrayListTest{


	@Test
	public void testArrayList( ){

		MyArrayList mal = new MyArrayList();

		mal.add(new Integer(2));
		mal.add(new Integer(5));
		mal.add(new Integer(1));
		mal.add(new Integer(23));
		mal.add(new Integer(14));

		for(int i=0;i<mal.size();i++){

          System.out.print(mal.get(i)+" ");
      }

      mal.add(new Integer(29));

      System.out.println("Element at Index 5:"+mal.get(5));
      System.out.println("List size: "+mal.size());
      System.out.println("Removing element at index 2: "+mal.remove(2));

      for(int i=0;i<mal.size();i++){

          System.out.print(mal.get(i)+" ");
      }
  }

}
/*END of solution: write a program to 
implement ArrayList data-structure*/









/*question: write a program to implement 
a hash table data-structure*/

// class Hashtable<K, V> implementes 
// Serializable, Cloneable, Map<K,V> interfaces 

/*solution-a*/
class HashEntry<K, V> {


  private final K key;
  private V value;


  public HashEntry(K key, V value) {

    this.key = key;
    this.value = value;
}

public K getKey() {

    return key;
}

public V getValue() {

    return value;
}

public void setValue(V value) {

    this.value = value;
}
} 



class MyHashTable <K, V> {


	private int size;
	private int DEFAULT_CAPACITY = 16;

	private HashEntry<K, V>[] values = new HashEntry[DEFAULT_CAPACITY];




	// return type in V 
	/*1. option for inserting as singly ll  
    node.next = table[pos];
    table[pos] = node;

    2. need to figure out a way for hashing 
    // obj.hashCode() return int */
    public V put(K key, V value) {

       boolean insert = true;
       V prevVal = null; 

       for (int i = 0; i < size; i++) {

	        // update the key
         if (values[i].getKey().equals(key)) {

            prevVal = values[i].getValue();
            values[i].setValue(value);
            insert = false;
        }

    }

    if (insert) {

       ensureCapa();
       values[size++] = new HashEntry<K, V>(key, value);
   }

   return prevVal; 
}



	// get() returns value against the key 
public V get(K key) {

 for (int i = 0; i < size; i++) {

   if (values[i] != null) {

     if (values[i].getKey().equals(key)) {

       return values[i].getValue();
   }

}

}

return null;
}


private void ensureCapa() {

 if (size == values.length) {

   int newSize = values.length * 2;
   values = Arrays.copyOf(values, newSize);
}
}


	// returns the size in int
public int size() {

 return size;
}



public V remove(K key) {

  V prev = null; 

  if ( key == null) 
     throw new NullPointerException();

 else{

  for (int i = 0; i < size; i++) {

    if (values[i].getKey().equals(key)) {

       prev = values[i].getValue();	        	
       values[i] = null;

       size--;
       condenseArray(i);
   }
}
}

return prev;
}


private void condenseArray(int start) {

    for (int i = start; i < size; i++) {

       values[i] = values[i + 1];
   }
}


	// returns the Set of the keys 
public Set<K> keySet() {

    Set<K> set = new HashSet<K>();

    for (int i = 0; i < size; i++) {

       set.add(values[i].getKey());
   }

   return set;
}
} 
/*END OF solution-a*/






// implementation with singly ll 
/*solution-b*/
class Node_{

    Node_ next;
    int data;

    public Node_(int x){

      data = x;
      next = null;    
  }       
}


class HashTable {


    private Node_[] table;
    private int size ;

    public HashTable(int size){

      table = new Node_[ nextPrime(size) ];
      size = 0;
  }


    // set the size of the table as prime number 
  private static int nextPrime( int n ){

      if (n % 2 == 0)
        n++;

    for ( ; !isPrime( n ); n += 2);

        return n;
}

private static boolean isPrime( int n ) {

  if (n == 2 || n == 3) return true;

  if (n == 1 || n % 2 == 0) return false;

        // 3 , 5, 7 
        // String str = "Seattle";
        // System.out.println(str.getClass().getName());
        //  will return: java.lang.String
  for (int i = 3; (int)Math.pow(i,2)<= n; i += 2) {

     if (n % i == 0) return false;
 }

 return true;
}


public boolean isEmpty() {

    return size == 0;
}


	// there is no key, hashing and 
	// insertion is done over the value    
public void insert(int val) {

    size++;
    int pos = hashing(val);        

    Node_ node = new Node_(val);                

    if (table[pos] == null){

       table[pos] = node;
   }                        

   else {

    node.next = table[pos];

            // table[pos] gets down to be always the head 
    table[pos] = node;
} 
}


    // convert the key to a hash value 
private int hashing(Integer x ){

    	// hashCode() converts Integer 
    	// to it's primitive value 
    int hashVal = x.hashCode( );

        // hashVal = hashVal % table.length;
    hashVal %= table.length;

    if (hashVal < 0)
      hashVal += table.length;

  return hashVal;
}



    // remove certain node ny given value 
    // COMMAND+ OPTION +F and select in selection 
    // for replacing all instances of certain words 
public void remove(int val){

    int pos = myhash(val);    

    Node_ start = table[pos];
    Node_ current = start;

    if (start.data == val) {

      size--;
            // move the head forward 
      table[pos] = start.next;
      return;
  }

  while ( current.next != null && current.next.data != val)
      current = current.next;

  if ( current.next == null) {

      System.out.println("\nElement not found\n");
      return;
  }

  size--;

  if (current.next.next == null) {

      current.next = null;
      return;
  }

  current.next = current.next.next;

        // may be not necessary 
  table[pos] = start;
}



    // print the hash table 
public void print() {

    System.out.println();

    for (int i = 0; i < table.length; i++){

      System.out.print ("Bucket " + i + ":  ");             
      Node_ start = table[i];

      while(start != null) {

        System.out.print(start.data +" ");
        start = start.next;
    }

        System.out.println();
    }
} 


    // set the hash table as beginning 
public void makeEmpty(){

    int len = table.length;
    table = new Node_[len];
    size = 0;
}


public int getSize(){

    return size;
}  
}
/*END OF SOLUTION-b*/


/*END OF SOLUTION: write a program to implement 
a hash table data-structure*/








/*question: write a program to implement 
a Set data-structure*/

// HashSet class implements Serializable, 
// Cloneable, Iterable<E>, Collection<E>, Set<E> interfaces 
class MySet<E>{

	// add(E e)             // boolean 
	// clear()              //  void 
	// contains(Object o)   // boolean 
	// isEmpty()            // boolean 
	// remove(Object o)     // boolean 
	// size()               //  int 


	//  clone()             // Object
	// iterator()           // Iterator<E>
}
/*END OF SOLUTION: write a program to implement 
a Set data-structure*/








public class myALD{

	public static void public static void main(String[] args) {
		
		System.out.println("Hello Algorithm");
	}
}