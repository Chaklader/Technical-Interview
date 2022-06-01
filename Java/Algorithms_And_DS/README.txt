Top 10 Algorithms and Data Structures for Competitive Programming 
url : <http://www.geeksforgeeks.org/top-algorithms-and-data-structures-for-competitive-programming/>


-------------------------------------------------------------------------------
First start with Linear data structures and algorithms.

Arrays
Linked List
Stack
Queues
Then move to basic algorithms :

Sorting - Merge Sort, Insertion Sort, Quick Sort, Number of inversions
Matrix Multiplication (just know the algo if not implement it)
Prime Sieving
Modular Math including multiplication and division
Euclidean Algorithm for GCD, Modular Inverse, Fast Exponentiation
Fibonacci number with matrix multiplication
Probability distribution and expected value
Stats - Mean, Median, Variance, Bayes theorem
The one can learn some popular algorithmic techniques:

Divide and Conquer - Binary Search, Maximum Subarray
Greedy Algorithms - Activity Selection, Huffman encoding
Dynamic Programming - Matrix Chain Multiplication, Knapsack,
Linear Programming - Variable Maximisation, Linear time sorting
String Algorithms - Manacher, LCS, Edit Distance
Then comes some typical non-linear data structures:

Trees - Binary Tree, General Tree, Lowest Common Ancestor
Binary Search Tree - Inorder Traversal, Level order traversal, finding kth largest element, diameter, depth, number of nodes, etc.
Heaps - Array Implementation, Heapify, Heap Sort
Union Find
Hash Table - Linear Probing, Open addressing, Collision avoidance
Then you can learn about Graphs:

Adjacency List, Adjacency Matrix, Weighted Edge Graphs
Basic Traversal algos - Breadth First Search, Depth First Search, etc
Shortest Path Finding Algorithm - Dijkstra, Floyd Warshal, Bellman Ford
Minimum Spanning Tree - Kruskal's Algo, Prim's Algo
By this time you are already pretty good with programming. You will do better than most of undergrad CS students. If you want to learn more and delve deep read more.

Advance Tree and Graph:

Balanced Trees - AVL, Red-Black
Heavy Light Decomposition, B+ Trees, Quad Tree
Advance Graph - Min Cut, Max Flow
Maximum Matching - Hall's Marriage
Hamiltonian Cycle
Edge Graphs / Line Graphs
Strongly Connected Components
Dominant Sub-Graph, Vertex Cover, Travelling Salesman - Approx algos
Advance String Algorithms :

Knuth Morris Pratt Algorithm
Rabin Karp Algorithm
Tries and Compressed Tries
Prefix Trees, Suffix Trees, Suffix Automation - Ukkonen Algorithm
Advance Math:

Fast Fourier Tranformation
Primality Testing
Computational Geometry - Closest point pair, Voronoi diagram, Convex Hull

General Advance topics :
Iterating through all combination / permutation
Bit manipulation
------------------------------------------------------------------------

Without further ado, in no particular order:

1. Plagiarism detection, using Rabin Karp String matching
String matching algorithms are pervasive in software. One particularly fun one, is Rabin Karp, which is used in Plagiarism detection. As a student in CS (or in any major), plagiarism detection should be of interest ;-)
Rabin Karp is relatively easy to implement. See this: Rabin–Karp algorithm - Wikipedia
Rabin Karp has also inspired a string matching routine in Zlib (one of the most popular un/zip libraries ever). See this, directly into the source code.

2. Matching users to servers, using Gayle-Shapely Algorithm for Stable Marriage problem
This is a beautiful algorithm for fair matching. Simple, elegant and effective. In its core form, it’s also straightforward to implement. Has numerous applications. See: Stable marriage problem - Wikipedia

3. A toy implementation of Viterbi algorithm
Ubiquitous in cell phone technology, and many other applications, Viterbi algorithm is a Dynamic Programming based algorithm that finds the most likely sequence of states.
See this toy implementation: http://homepages.ulb.ac.be/~dgon...

4. Music Search using Fast Fourier Transforms (FFT)
Music recognition is done by converting it into frequency domain using FFT. FFT has implementations in number of languages. See this article for a great start: Shazam It! Music Recognition Algorithms, Fingerprinting, and Processing.

5. Implement RSA algorithm 
SSL transport, is the bane of safe existence on Internet these days. One of the most well-known algorithms in secure transport, is RSA, named by the first initials of its inventors.
Implementing RSA is fun and instructive e.g. C code to implement RSA Algorithm(Encryption and Decryption)

6. Safe Browsing (or similar) using Bloom filters
Bloom filters found very rare usage until the world got more online and we hit scale. But these days, we see new applications very frequently.
Chrome browser uses Bloom filters to make preliminary decision on safe browsing. See some novel applications here.

7. Implement an LALR parser
As a CS student, you may have already implemented it as part of your compiler’s class. But if not, then you should. LALR parsing makes syntactic sense of source code, whichever language you use
Many implementations of LALR exist. e.g. Where can I find a _simple_, easy to understand implementation of an LR(1) parser generator?
Also, use YACC to understand LALR parsing better.

8. Treemap using Red Black Trees!
RB Trees are not algorithms, but they are famed enough, that no discussion of tantalizing DS/Algorithms is complete without discussing them.
The smoothest way to see/implement RB Trees, is to look at Treemap implementation in Java.

9. Circle Drawing using Bresenham’s algorithm
Ever wondered, how circles are drawn on the screen, with minimal jaggedness (aliasing)? Bresenham’s elegant algorithm is at play here. See a version here: Circle Generation Algorithm .
A refreshing use of a similar algorithm, is to make properly sized tabs in Chrome. Something we see almost every day. Such hidden gems!

10. Implement PageRank
Can’t miss this. This transformed our lives in ways we never thought possible. Get started here: Pagerank Explained Correctly with Examples

