# CountDistinctSlices
# -------------------

# An integer M and a non-empty zero-indexed array A consisting of N non-negative integers are given. All integers in array A are less than or equal to M.

# A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of the elements A[P], A[P + 1], ..., A[Q]. A distinct slice is a slice consisting of only unique numbers. That is, no individual number occurs more than once in the slice.

# For example, consider integer M = 6 and array A such that:

#     A[0] = 3
#     A[1] = 4
#     A[2] = 5
#     A[3] = 5
#     A[4] = 2
# There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).

# The goal is to calculate the number of distinct slices.

# Write a function:

# int solution(int M, int A[], int N);
# that, given an integer M and a non-empty zero-indexed array A consisting of N integers, returns the number of distinct slices.

# If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.

# For example, given integer M = 6 and array A such that:

#     A[0] = 3
#     A[1] = 4
#     A[2] = 5
#     A[3] = 5
#     A[4] = 2
# the function should return 9, as explained above.

# Assume that:

# N is an integer within the range [1..100,000];
# M is an integer within the range [0..100,000];
# each element of array A is an integer within the range [0..M].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).


def solution(M, A):
    accessed = [-1] * (M + 1)   # -1: not accessed before
                                # Non-negative: the previous occurrence position
    front, back = 0, 0
    result = 0

    for front in xrange(len(A)):
        if accessed[A[front]] == -1:
            # Met with a new unique item
            accessed[A[front]] = front
        else:
            # Met with a duplicate item
            # Compute the number of distinct slices between newBack-1 and back
            # position.
            newBack = accessed[A[front]] + 1
            result += (newBack - back) * (front - back + front - newBack + 1) / 2
            if result >= 1000000000:   return 1000000000

            # Restore and set the accessed array
            for index in xrange(back, newBack):
                accessed[A[index]] = -1
            accessed[A[front]] = front

            back = newBack

    # Process the last slices
    result += (front - back + 1) * (front - back + 2) / 2

    return min(result, 1000000000)



# solution-b

def solution(M, A):
    the_sum = 0
    front = back = 0
    seen = [False] * (M+1)
    while (front < len(A) and back < len(A)):
        while (front < len(A) and seen[A[front]] != True):
            the_sum += (front-back+1)
            seen[A[front]] = True
            front += 1
        else:
            while front < len(A) and back < len(A) and A[back] != A[front]:
                seen[A[back]] = False
                back += 1
                 
            seen[A[back]] = False
            back += 1
             
                 
    return min(the_sum, 1000000000)  




# AbsDistinct
# -----------

# A non-empty zero-indexed array A consisting of N numbers is given. The array is sorted in non-decreasing order. The absolute distinct count of this array is the number of distinct absolute values among the elements of the array.

# For example, consider array A such that:

#   A[0] = -5
#   A[1] = -3
#   A[2] = -1
#   A[3] =  0
#   A[4] =  3
#   A[5] =  6
# The absolute distinct count of this array is 5, because there are 5 distinct absolute values among the elements of this array, namely 0, 1, 3, 5 and 6.

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A consisting of N numbers, returns absolute distinct count of array A.

# For example, given array A such that:

#   A[0] = -5
#   A[1] = -3
#   A[2] = -1
#   A[3] =  0
#   A[4] =  3
#   A[5] =  6
# the function should return 5, as explained above.

# Assume that:

# N is an integer within the range [1..100,000];
# each element of array A is an integer within the range [−2,147,483,648..2,147,483,647];
# array A is sorted in non-decreasing order.
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).



def solution(A):
    abs_distinct = 1
    current = max(abs(A[0]), abs(A[-1]))
    index_head = 0
    index_tail = len(A)-1

    while index_head <= index_tail:
        # We travel the array from the greatest
        # absolute value to the smallest.

        former = abs(A[index_head])
        if former == current:
            # Skip the heading elements, whose
            # absolute values are the same with
            # current recording one.
            index_head += 1
            continue

        latter = abs(A[index_tail])
        if latter == current:
            # Skip the tailing elements, whose
            # absolute values are the same with
            # current recording one.
            index_tail -= 1
            continue

        # At this point, both the former and
        # latter has different absolute value
        # from current recorded one.
        if former >= latter:
            # The next greatest value is former
            current = former
            index_head += 1
        else:
            # The next greatest value is latter
            current = latter
            index_tail -= 1

        # Meet with a new absolute value
        abs_distinct += 1

    return abs_distinct




# solution-b

def solution(A):
    return len(set([abs(x) for x in A]))





# CountTringles
# -------------

# A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if it is possible to build a triangle with sides of lengths A[P], A[Q] and A[R]. In other words, triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

# A[P] + A[Q] > A[R],
# A[Q] + A[R] > A[P],
# A[R] + A[P] > A[Q].
# For example, consider array A such that:

#   A[0] = 10    A[1] = 2    A[2] = 5
#   A[3] = 1     A[4] = 8    A[5] = 12
# There are four triangular triplets that can be constructed from elements of this array, namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).

# Write a function:

# int solution(int A[], int N);
# that, given a zero-indexed array A consisting of N integers, returns the number of triangular triplets in this array.

# For example, given array A such that:

#   A[0] = 10    A[1] = 2    A[2] = 5
#   A[3] = 1     A[4] = 8    A[5] = 12
# the function should return 4, as explained above.

# Assume that:

# N is an integer within the range [0..1,000];
# each element of array A is an integer within the range [1..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(N2);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).




def solution(A):
    n = len(A)
    result = 0

    A.sort()

    for first in xrange(n-2):
        third = first + 2
        for second in xrange(first+1, n-1):
            while third < n and A[first] + A[second] > A[third]:
                third += 1
            result += third - second - 1

    return result



# solution-b
def solution(A):
    A.sort()
    print A
    triangle_cnt = 0
     
    for P_idx in xrange(0, len(A)-2):
        Q_idx = P_idx + 1
        R_idx = P_idx + 2
        while (R_idx < len(A)):
            if A[P_idx] + A[Q_idx] > A[R_idx]:
                triangle_cnt += R_idx - Q_idx
                R_idx += 1
            elif Q_idx < R_idx -1:
                    Q_idx += 1
            else:
                R_idx += 1
                Q_idx += 1
                 
    return triangle_cnt



# MinAbsSumOfTwo
# --------------


# Let A be a non-empty zero-indexed array consisting of N integers.

# The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.

# For example, the following array A:

#   A[0] =  1
#   A[1] =  4
#   A[2] = -3
# has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2). 
# The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2. 
# The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5. 
# The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2. 
# The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8. 
# The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1. 
# The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6. 
# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.

# For example, given the following array A:

#   A[0] =  1
#   A[1] =  4
#   A[2] = -3
# the function should return 1, as explained above.

# Given array A:

#   A[0] = -8
#   A[1] =  4
#   A[2] =  5
#   A[3] =-10
#   A[4] =  3
# the function should return |(−8) + 5| = 3.

# Assume that:

# N is an integer within the range [1..100,000];
# each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(N*log(N));
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).


# solution-a

def solution(A):
    A.sort()        # Sort A in non-decreasing order

    if A[0] >= 0:   return A[0] + A[0]      # All non-negative
    if A[-1] <= 0:  return -A[-1] - A[-1]   # All non-positive

    front, back = len(A)-1, 0
    minAbs = A[-1] + A[-1]                  # Final result

    # Travel the array from both ends to some center point.
    # See following post for the proof of this method.
    # https://codesays.com/2014/solution-to-min-abs-sum-of-two-by-codility
    while back <= front:
        temp = abs(A[back] + A[front])

        # Update the result if needed
        if temp < minAbs:  minAbs = temp

        # Adjust the pointer for next trying
        if abs(A[back+1] + A[front]) <= temp:    back += 1
        elif abs(A[back] + A[front-1]) <= temp:  front -= 1
        else:                         back += 1; front -= 1

    return minAbs



# solution-b

def solution(A):
    value = 2000000000
    front_ptr = 0
    back_ptr = len(A)-1
    A.sort()
     
    while front_ptr <= back_ptr: value = min(value, abs(A[front_ptr] + A[back_ptr])) if abs(A[front_ptr]) > abs(A[back_ptr]):
            front_ptr += 1
        else:
            back_ptr -= 1
             
    return value


# solution-c

from itertools import *
def getAbsDiff(t):
  return abs(t[0] + t[1])
 
def solution(A):
  A.sort(key=abs)
  return getAbsDiff(min(chain(izip(A, A),izip(A,A[1:])), key = getAbsDiff))