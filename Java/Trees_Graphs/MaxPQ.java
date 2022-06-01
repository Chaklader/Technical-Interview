package com.codility.Data_Structures;

import java.util.*;

/*question: design a program to implement
generic priority queue using binary heap*/
public class MaxPQ<Key> implements Iterable<Key> {



    
    /*question: median for the heap*/
    class MaxHeapComparator implements Comparator<Integer> {


    // Comparator that sorts integers from highest to lowest
        @Override
        public int compare(Integer o1, Integer o2) {

        // TODO Auto-generated method stub
            if (o1 < o2) {
                return 1;
            } else if (o1 == o2) {
                return 0;
            } else {
                return -1;
            }
        }
    }


    class MinHeapComparator implements Comparator<Integer> {

    /*
     * comparator that sorts integers from lowest to highest
     */
    @Override
    public int compare(Integer o1, Integer o2) {

        if (o1 > o2) {
            return 1;
        } else if (o1 == o2) {
            return 0;
        } else {
            return -1;
        }
    }
}

/*END of solution: median for the heap*/







    ////////////////////////////////////////////////////////////////////////////////
public static void addNewNumber(int randomNumber) {

    /* Note: addNewNumber maintains a condition that maxHeap.size() >= minHeap.size() */
    if (maxHeap.size() == minHeap.size()) {

        if ((minHeap.peek() != null) &&
            randomNumber > minHeap.peek()) {
            maxHeap.offer(minHeap.poll());
        minHeap.offer(randomNumber);
    } else {
        maxHeap.offer(randomNumber);
    }
} else {
    if (randomNumber < maxHeap.peek()) {
        minHeap.offer(maxHeap.poll());
        maxHeap.offer(randomNumber);
    } else {
        minHeap.offer(randomNumber);
    }
}
}

public static double getMedian() {
    /* maxHeap is always at least as big as minHeap. So if maxHeap is empty, then minHeap is also. */
    if (maxHeap.isEmpty()) {
        return 0;
    }
    if (maxHeap.size() == minHeap.size()) {
        return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
    } else {
        /* If maxHeap and minHeap are of different sizes, then maxHeap must have one extra element. Return maxHeap’s top element.*/
        return maxHeap.peek();
    }
}


public static void addNewNumberAndPrintMedian(int randomNumber) {

    addNewNumber(randomNumber);
    System.out.println("Random Number = " + randomNumber);

    printMinHeapAndMaxHeap();
    System.out.println("\nMedian = " + getMedian() + "\n");
}

public static void printMinHeapAndMaxHeap() {

    Integer[] minHeapArray = minHeap.toArray(new Integer[minHeap.size()]);

    Integer[] maxHeapArray = maxHeap.toArray(new Integer[maxHeap.size()]);

    Arrays.sort(minHeapArray, maxHeapComparator);
    Arrays.sort(maxHeapArray, maxHeapComparator);
    System.out.print("MinHeap =");
    for (int i = minHeapArray.length - 1; i >= 0; i--) {
        System.out.print(" " + minHeapArray[i]);
    }

    System.out.print("\nMaxHeap =");

    for (int i = 0; i < maxHeapArray.length; i++) {
        System.out.print(" " + maxHeapArray[i]);
    }
}
    ////////////////////////////////////////////////////////////////////////////////


    // number of items on priority queue
private int N;

    // store items at indices 1 to N
private Key[] pq;

    // optional Comparator
private Comparator<Key> comparator;


public MaxPQ(int initCapacity) {
    pq = (Key[]) new Object[initCapacity + 1];
    N = 0;
}

public MaxPQ() {
    this(1);
}

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param initCapacity the initial capacity of this priority queue
     * @param comparator   the order in which to compare the keys
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {

        this.comparator = comparator;

        pq = (Key[]) new Object[initCapacity + 1];
        N = 0;
    }

    /**
     * Initializes an empty priority queue using the given comparator.
     *
     * @param comparator the order in which to compare the keys
     */
    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    /**
     * Initializes a priority queue from the array of keys.
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param keys the array of keys
     */
    public MaxPQ(Key[] keys) {

        N = keys.length;
        pq = (Key[]) new Object[keys.length + 1];

        for (int i = 0; i < N; i++) {
            pq[i + 1] = keys[i];
        }

        for (int k = N / 2; k >= 1; k--) {
            sink(k);
        }

        assert isMaxHeap();
    }


    /**
     * Returns true if this priority queue is empty.
     *
     * @return <tt>true</tt> if this priority queue is empty;
     * <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return N;
    }

    /**
     * Returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key max() {

        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }


    // helper function to double the size of the heap array
    private void resize(int capacity) {

        assert capacity > N;

        Key[] temp = (Key[]) new Object[capacity];

        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }

        pq = temp;
    }


    /**
     * Adds a new key to this priority queue.
     *
     * @param x the new key to add to this priority queue
     */
    public void insert(Key x) {

        // double size of array if necessary
        if (N >= pq.length - 1) {
            resize(2 * pq.length);
        }

        /*
         * add x, and percolate it up to maintain heap invariant
         */
        pq[++N] = x;
        swim(N);

        assert isMaxHeap();
    }


    /**
     * Removes and returns a largest key on this priority queue.
     *
     * @return a largest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key delMax() {

        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");

        Key max = pq[1];
        exch(1, N--);
        sink(1);

        pq[N + 1] = null;     // to avoid loiterig and help with garbage collection

        if ((N > 0) && (N == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }

        assert isMaxHeap();
        return max;
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k) {

        while (k > 1 && less(k / 2, k)) {

            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {

        while (2 * k <= N) {

            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;

            if (!less(k, j)) {
                break;
            }

            exch(k, j);
            k = j;
        }
    }

    /*****************************************
     * Helper functions for compares and swaps
     ******************************************/
    private boolean less(int i, int j) {

        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {

        Key swap = pq[i];

        pq[i] = pq[j];
        pq[j] = swap;
    }

    /*
     * is pq[1..N] a max heap?
     */
    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    // is subtree of pq[1..N] rooted at k a max heap?
    private boolean isMaxHeap(int k) {

        if (k > N) {
            return true;
        }

        int left = 2 * k, right = 2 * k + 1;

        if (left <= N && less(k, left)) return false;

        if (right <= N && less(k, right)) return false;

        return isMaxHeap(left) && isMaxHeap(right);
    }


    /***************************************************************************
     * Iterator.
     ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on this priority queue
     * in descending order.
     * The iterator doesn't implement <tt>remove()</tt> since it's optional.
     *
     * @return an iterator that iterates over the keys in descending order
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        // create a new pq
        private MaxPQ<Key> copy;


        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {

            if (comparator == null) {
                copy = new MaxPQ<Key>(size());
            } else {
                copy = new MaxPQ<Key>(size(), comparator);
            }

            for (int i = 1; i <= N; i++) {
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }


    /**
     * Unit tests the <tt>MaxPQ</tt> data type.
     */
    public void test() {

        MaxPQ<String> pq = new MaxPQ<String>();

//        while (!pq.isEmpty()) {

//            String item = pq.readString();
//
//            if (!item.equals("-")) {
//                pq.insert(item);
//            }
//
//            else if {
//                (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
//            }
//        }

        System.out.println("(" + pq.size() + " left on pq)");
    }


    private static Comparator<Integer> maxHeapComparator;

    private static Comparator<Integer> minHeapComparator;

    private static PriorityQueue<Integer> maxHeap;

    private static PriorityQueue<Integer> minHeap;


    public static void main(String[] args) {

        maxHeapComparator = new MaxHeapComparator();
        minHeapComparator = new MinHeapComparator();

        /*question: median for the heap*/
        int arraySize = 10;
        int range = 7;


        maxHeap = new PriorityQueue<Integer>(arraySize - arraySize / 2, maxHeapComparator);
        minHeap = new PriorityQueue<Integer>(arraySize / 2, minHeapComparator);

        for (int i = 0; i < arraySize; i++) {
            int randomNumber = (int) (Math.random() * (range + 1));
            addNewNumberAndPrintMedian(randomNumber);
        }
    }

}

