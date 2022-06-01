# SqlSum
# ------

# Given a table elements with the following structure:

#   create table elements (
#       v integer not null
#   );
# write an SQL query that returns the sum of the numbers in column v.

# For example, given:

#   v
#   ---
#   2
#   10
#   20
#   10
# your query should return 42.


# SELECT SUM(v) FROM elements








# StrSymmetryPoint
# ================


# Write a function:

# int solution(char *S);
# that, given a string S, returns the index (counting from 0) of a character such that the part of the string to the left of that character is a reversal of the part of the string to its right. The function should return −1 if no such index exists.

# Note: reversing an empty string (i.e. a string whose length is zero) gives an empty string.

# For example, given a string:

# "racecar"

# the function should return 3, because the substring to the left of the character "e" at index 3 is "rac", and the one to the right is "car".

# Given a string:

# "x"

# the function should return 0, because both substrings are empty.

# Assume that:

# the length of S is within the range [0..2,000,000].
# Complexity:

# expected worst-case time complexity is O(length(S));
# expected worst-case space complexity is O(1) (not counting the storage required for input arguments).



def solution(S):
    sLen = len(S)

    # Symmetry point is possible, when and only when the
    # string's length is odd.
    if sLen % 2 == 0:   return -1

    # With a odd-length string, the only possible symmetry
    # point is the middle point.
    mid = sLen // 2
    begin, end = 0, sLen-1

    # The middle point of an odd-length string is symmetry
    # point, only when the string is symmetry.
    while begin < mid:
        if S[begin] != S[end]:      return -1

        begin += 1
        end -= 1

    return mid




def solution1(S):
    l = len(S)
 
    if l % 2 == 0:
        return -1
 
    mid_point = l // 2
 
    for idx in xrange(0, mid_point):
        if S[idx] != S[l - idx - 1]:
            return -1
 
    return mid_point



#  TreeHeight
#  ----------


# In this problem we consider binary trees, represented by pointer data structures.

# A binary tree is either an empty tree or a node (called the root) consisting of a single integer value and two further binary trees, called the left subtree and the right subtree.

# For example, the figure below shows a binary tree consisting of six nodes. Its root contains the value 5, and the roots of its left and right subtrees have the values 3 and 10, respectively. The right subtree of the node containing the value 10, as well as the left and right subtrees of the nodes containing the values 20, 21 and 1, are empty trees.



# A path in a binary tree is a non-empty sequence of nodes that one can traverse by following the pointers. The length of a path is the number of pointers it traverses. More formally, a path of length K is a sequence of nodes P[0], P[1], ..., P[K], such that node P[I + 1] is the root of the left or right subtree of P[I], for 0 ≤ I < K. For example, the sequence of nodes with values 5, 3, 21 is a path of length 2 in the tree from the above figure. The sequence of nodes with values 10, 1 is a path of length 1. The sequence of nodes with values 21, 3, 20 is not a valid path.

# The height of a binary tree is defined as the length of the longest possible path in the tree. In particular, a tree consisting of only one node has height 0 and, conventionally, an empty tree has height −1. For example, the tree shown in the above figure is of height 2.

# Problem
# Write a function:

# int solution(struct tree * T);
# that, given a non-empty binary tree T consisting of N nodes, returns its height. For example, given tree T shown in the figure above, the function should return 2, as explained above. Note that the values contained in the nodes are not relevant in this task.

# Technical details
# A binary tree can be given using a pointer data structure. Assume that the following declarations are given:

# struct tree {
#   int x;
#   struct tree * l;
#   struct tree * r;
# };
# An empty tree is represented by an empty pointer (denoted by NULL). A non-empty tree is represented by a pointer to an object representing its root. The attribute x holds the integer contained in the root, whereas attributes l and r hold the left and right subtrees of the binary tree, respectively.

# For the purpose of entering your own test cases, you can denote a tree recursively in the following way. An empty binary tree is denoted by None. A non-empty tree is denoted as (X, L, R), where X is the value contained in the root and L and R denote the left and right subtrees, respectively. The tree from the above figure can be denoted as:

#   (5, (3, (20, None, None), (21, None, None)), (10, (1, None, None), None))
# Assumptions
# Assume that:

# N is an integer within the range [1..1,000];
# the height of tree T (number of edges on the longest path from root to leaf) is within the range [0..500].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N).



def solution(T):
    if T.l == None and T.r == None:
        # Has no subtree
        return 0
    elif T.l == None:
        # Only has right subtree
        return 1 + solution(T.r)
    elif T.r == None:
        # Only has left subtree
        return 1 + solution(T.l)
    else:
        # Have two subtrees
        return 1 + max(solution(T.l), solution(T.r))



# solution-b

'''
class Tree(object):
  x = 0
  l = None
  r = None
'''
 
def getHeight(sub_T):
    if sub_T == None:
        return 0
    return max(getHeight(sub_T.l), getHeight(sub_T.r))+1
 
def solution(T):
    return max(getHeight(T.l), getHeight(T.r))





# ArrayInversionCount
#  ------------------


# A zero-indexed array A consisting of N integers is given. An inversion is a pair of indexes (P, Q) such that P < Q and A[Q] < A[P].

# Write a function:

# int solution(int A[], int N);
# that computes the number of inversions in A, or returns −1 if it exceeds 1,000,000,000.

# Assume that:

# N is an integer within the range [0..100,000];
# each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
# For example, in the following array:

#  A[0] = -1 A[1] = 6 A[2] = 3
#  A[3] =  4 A[4] = 7 A[5] = 4
# there are four inversions:

#    (1,2)  (1,3)  (1,5)  (4,5)
# so the function should return 4.

# Complexity:

# expected worst-case time complexity is O(N*log(N));
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.

def mergesort( aList, first, last ):
    ''' Modified merge sort algorithm.
        Record the inversion count during sort.
    '''
    mid = ( first + last ) / 2
    if first < last:
        # Recursive calling
        left_inver = mergesort( aList, first, mid )
        right_inver = mergesort( aList, mid + 1, last )
    else:
        # Terminate condition
        return 0

    first_index = first     # The index for the left part
    second_index = mid + 1  # The index for the right part
    temp = [0] * (last-first+1)     # To hold the sorted content
    temp_index = 0
    merge_inver = 0         # Number of inversion in merging

    while first_index <= mid and second_index <= last:
        if aList[first_index] <= aList[second_index]:
            # Less index indicates less value. No inversion.
            temp[temp_index] = aList[first_index]
            first_index += 1
        else:
            # Greater index has less value. Inversion exists.
            # For exampe:
            #       [     4,   5,   2,   3     ]
            #       | Left part |  |Right Part|
            # and first_index = 1, second_index = 3, mid = 2
            # We need the item "2" to be the position 0. So it
            # has to pass all the unwritten items in left part.
            # Here these unwritten items are "4" and "5". So
            # two more inversions are involved.
            # In general, the left part is sorted. So all the
            # elements, being and after first_index, are greater
            # than element in position second_index. AND all of
            # them have less indexes. As the result,
            # there are mid-first_index+1 new reversions.
            temp[temp_index] = aList[second_index]
            second_index += 1
            merge_inver += mid - first_index + 1
        temp_index += 1

    if first_index != mid+1:
        # Some element in the left part left. They have less
        # indexes, but greater values. Inversion involves.
        # BUT these inversions have already been counted.
        while first_index <= mid:
            temp[temp_index] = aList[first_index]
            first_index += 1
            temp_index += 1

    if second_index != last+1:
        # Some element in the right part left. They have both
        # greater indexes and values than all in the left part.
        # No inversion is involved.
        while second_index <= last:
            temp[temp_index] = aList[second_index]
            second_index += 1
            temp_index += 1

    # Rewrite the sorted content into the original array
    aList[first:last+1] = temp[:]

    return merge_inver + left_inver + right_inver

def solution(A):
    return mergesort( A * 1, 0, len(A)-1)





# solution-b

MAX_NR = 1000000000
 
def mergeSort(alist):
    inversion_count = 0
    if len(alist)>1:
        mid = len(alist)//2
        lefthalf = alist[:mid]
        righthalf = alist[mid:]
 
        inversion_count += mergeSort(lefthalf)
        if (inversion_count > MAX_NR):
            raise
 
        inversion_count += mergeSort(righthalf)
        if (inversion_count > MAX_NR):
            raise
 
        i=0
        j=0
        k=0
        while i<len(lefthalf) and j<len(righthalf):
            if lefthalf[i]<=righthalf[j]:
                alist[k]=lefthalf[i]
                i=i+1
            else:
                alist[k]=righthalf[j]
                j=j+1
                inversion_count += len(lefthalf)-i
                if (inversion_count > MAX_NR):
                    raise
            k=k+1
 
        while i<len(lefthalf):
            alist[k]=lefthalf[i]
            i=i+1
            k=k+1
 
        while j<len(righthalf):
            alist[k]=righthalf[j]
            j=j+1
            k=k+1
 
    return inversion_count
 
def solution(A):
    try:
        return mergeSort(A)
    except:
        return -1






# PolygonConcavityIndex
# ---------------------


# An array A of points in a 2D plane is given. These points represent a polygon: every two consecutive points describe an edge of the polygon, and there is an edge connecting the last point and the first point in the array.

# A set of points in a 2D plane, whose boundary is a straight line, is called a semiplane. More precisely, any set of the form {(x, y) : ax + by ≥ c} is a semiplane. The semiplane contains its boundary.

# A polygon is convex if and only if, for any edge of the polygon, all vertices belong to the semiplane whose boundary contains the edge.

# For example, the polygon consisting of vertices whose Cartesian coordinates are consecutively:

#   (-1, 3)   (3, 1)   (0, -1)   (-2, 1)
# is convex.

# The convex hull of a finite set of points in a 2D plane is the smallest convex polygon that contains all points in this set. For example, the convex hull of a set consisting of six points whose Cartesian coordinates are:

#   (-1, 3)   (3, 1)   (1, 1)
#   (0, -1)   (-2, 1)  (-1, 2)
# is a polygon that has four vertices. When traversed clockwise, its vertices are:

#   (-1, 3)   (3, 1)   (0, -1)   (-2, 1)
# Assume that the following declarations are given:

# struct Point2D {
#   int x;
#   int y;
# };
# Write a function:

# int solution(struct Point2D A[], int N);
# that, given a non-empty zero-indexed array A consisting of N elements describing a polygon, returns −1 if the polygon is convex. Otherwise, the function should return the index of any point that doesn't belong to the convex hull.

# To access the coordinates of the K-th point (where 0 ≤ K < N) use the following syntax:

# A[K].x and A[K].y in all languages except PHP and Perl,
# $A[$K]->x and $A[$K]->y in PHP,
# $A[$K]->{x} and $A[$K]->{y} in Perl.
# For example, given array A such that:

#   A[0].x = -1  A[0].y =  3
#   A[1].x =  3  A[1].y =  1
#   A[2].x =  0  A[2].y = -1
#   A[3].x = -2  A[3].y =  1
# the function should return −1, as explained in the example above.

# Assume that:

# N is an integer within the range [3..10,000];
# the coordinates of each point in array A are integers within the range [−1,000,000,000..1,000,000,000];
# no two edges of the polygon A intersect, other than meeting at their endpoints;
# array A does not contain duplicate points.
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).




def _IsClockwise(point_A, point_B, point_C):
	
    ''' Return the direction from points A -> B -> C.
    '''
    result = (point_B.x - point_A.x) * (point_C.y - point_A.y) -
             (point_B.y - point_A.y) * (point_C.x - point_A.x)
                                    # The direction of a->b->c is:
    if result > 0:      return 1    #   counter-clockwise
    elif result < 0:    return -1   #   clockwise
    else:               return 0    #   a staight line



def solution(A):
    ''' The solution refers to:
        https://www.youtube.com/watch?v=0HZaRu5IupM
    '''
    # Find the lowest point(s) in y-axis.
    lowest_y = A[0].y
    lowest_y_index = []
    for i in xrange(len(A)):
        if A[i].y < lowest_y:
            lowest_y = A[i].y
            lowest_y_index = [i]
        elif A[i].y == lowest_y:
            lowest_y_index.append(i)
        else:
            continue

    # Find a point, which is not the lowest in y-axis and immediately
    # after a lowest-in-y-axis point.
    start_point = lowest_y_index[0]
    lowest_y_array = [False] * len(A)
    for i in lowest_y_index: lowest_y_array[i] = True
    while lowest_y_array[start_point] == True:
        start_point = (start_point + 1) % len(A)
    start_point = (start_point - 1 + len(A)) % len(A)

    # Re-organize the points so that, it is easier to check every three
    # consecutive points in one loop (without module operation %).
    rotated_A = A[start_point : ] + A[ : start_point]
    # We find the start point such that, the direction is non-zero.
    direction = _IsClockwise(rotated_A[-1], rotated_A[0], rotated_A[1])
    extened_A = rotated_A + rotated_A[:2]

    for i in xrange(len(A)):
        temp = _IsClockwise(extened_A[i], extened_A[i+1], extened_A[i+2])
        if temp * direction < 0:
            # Compute the original index and return
            return (i + 1 + start_point)%len(A)

    # Every point is on the convex hull.
    return -1



# solution-b

