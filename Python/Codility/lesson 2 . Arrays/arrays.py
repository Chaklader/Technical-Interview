
# OddOccurrencesInArray
# ---------------------

# A non-empty zero-indexed array A consisting of N integers is given. The array contains 
# an odd number of elements, and each element of the array can be paired with another element 
# that has the same value, except for one element that is left unpaired.

# For example, in array A such that:

#   A[0] = 9  A[1] = 3  A[2] = 9
#   A[3] = 3  A[4] = 9  A[5] = 7
#   A[6] = 9

# the elements at indexes 0 and 2 have value 9,
# the elements at indexes 1 and 3 have value 3,
# the elements at indexes 4 and 6 have value 9,
# the element at index 5 has value 7 and is unpaired.
# Write a function:

# class Solution { public int solution(int[] A); }

# that, given an array A consisting of N integers fulfilling the above conditions, returns 
# the value of the unpaired element.

# For example, given array A such that:

#   A[0] = 9  A[1] = 3  A[2] = 9
#   A[3] = 3  A[4] = 9  A[5] = 7
#   A[6] = 9
# the function should return 7, as explained in the example above.

# Assume that:

# N is an odd integer within the range [1..1,000,000];
# each element of array A is an integer within the range [1..1,000,000,000];
# all but one of the values in A occur an even number of times.

# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(1), beyond input storage (not counting 
# the storage required for input arguments).
# Elements of input arrays can be modified.


# solution-a
def solution(A):
    result = 0
    
    for number in A:
        result ^= number

    return result




# CyclicRotation 
# --------------

# A zero-indexed array A consisting of N integers is given. Rotation of the array 
# means that each element is shifted right by one index, and the last element of 
# the array is also moved to the first place.

# For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]. The goal 
# is to rotate array A K times; that is, each element of A will be shifted to the right 
# by K indexes.

# Write a function:

# class Solution { public int[] solution(int[] A, int K); }

# that, given a zero-indexed array A consisting of N integers and an integer K, returns the 
# array A rotated K times.

# For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return 
# [9, 7, 6, 3, 8].

# Assume that:

# N and K are integers within the range [0..100];
# each element of array A is an integer within the range [−1,000..1,000].
# In your solution, focus on correctness. The performance of your solution will not be the 
# focus of the assessment.


def solution(A, K):
    # write your code in Python 2.7
    if len(A) == 0:
        return A
    
    K = K % len(A)
    return A[-K:] + A[:-K]



# solution-b
def reverse(arr, i, j):
    for idx in xrange((j - i + 1) / 2):
        arr[i+idx], arr[j-idx] = arr[j-idx], arr[i+idx]
 
def solution(A, K):
    l = len(A)
    if l == 0:
        return []
         
    K = K%l
     
    reverse(A, l - K, l -1)
    reverse(A, 0, l - K -1)
    reverse(A, 0, l - 1)
 
    return A



# ZooPlus AG test

# question: Given an array of values, design and code 
# an algorithm that returns whether there are two duplicates 
# within k indices of each other? k indices and within plus 
# or minus l (value) of each other? Do all, even the latter, 
# in O(n) running time and O(k) space.

# Example test:    '00-44  48 5555 8361' 
# WRONG ANSWER  (got 004-448-555-583-6- 8-361 expected 004-448-555-583-61) 

# Example test:    '0 - 22 1985--324' 
# WRONG ANSWER  (got 022-198-532-5---324 expected 022-198-53-24) 

# Example test:    '555372654' 

