# TieRopes
# --------


# There are N ropes numbered from 0 to N − 1, whose lengths are given in a zero-indexed array A, lying on the floor in a line. For each I (0 ≤ I < N), the length of rope I on the line is A[I].

# We say that two ropes I and I + 1 are adjacent. Two adjacent ropes can be tied together with a knot, and the length of the tied rope is the sum of lengths of both ropes. The resulting new rope can then be tied again.

# For a given integer K, the goal is to tie the ropes in such a way that the number of ropes whose length is greater than or equal to K is maximal.

# For example, consider K = 4 and array A such that:

#     A[0] = 1
#     A[1] = 2
#     A[2] = 3
#     A[3] = 4
#     A[4] = 1
#     A[5] = 1
#     A[6] = 3
# The ropes are shown in the figure below.



# We can tie:

# rope 1 with rope 2 to produce a rope of length A[1] + A[2] = 5;
# rope 4 with rope 5 with rope 6 to produce a rope of length A[4] + A[5] + A[6] = 5.
# After that, there will be three ropes whose lengths are greater than or equal to K = 4. It is not possible to produce four such ropes.

# Write a function:

# int solution(int K, int A[], int N);
# that, given an integer K and a non-empty zero-indexed array A of N integers, returns the maximum number of ropes of length greater than or equal to K that can be created.

# For example, given K = 4 and array A such that:

#     A[0] = 1
#     A[1] = 2
#     A[2] = 3
#     A[3] = 4
#     A[4] = 1
#     A[5] = 1
#     A[6] = 3
# the function should return 3, as explained above.

# Assume that:

# N is an integer within the range [1..100,000];
# K is an integer within the range [1..1,000,000,000];
# each element of array A is an integer within the range [1..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.




def solution(K, A):
    # The number of tied ropes, whose lengths
    # are greater than or equal to K.
    count = 0

    # The length of current rope (might be a tied one).
    length = 0

    for rope in A:
        length += rope  # Tied with the previous one.

        # Find a qualified rope. Prepare to find the
        # next one.
        if length >= K:     count += 1; length = 0

    return count



# solution-b
def solution(K, A):
    cnt = 0
    current = 0
    for part in A:
        current += part
        if current >= K:
            cnt +=1
            current = 0
 
    return cnt





# MaxNonoverlappingSegments
# -------------------------

# Located on a line are N segments, numbered from 0 to N − 1, whose positions are given in zero-indexed arrays A and B. For each I (0 ≤ I < N) the position of segment I is from A[I] to B[I] (inclusive). The segments are sorted by their ends, which means that B[K] ≤ B[K + 1] for K such that 0 ≤ K < N − 1.

# Two segments I and J, such that I ≠ J, are overlapping if they share at least one common point. In other words, A[I] ≤ A[J] ≤ B[I] or A[J] ≤ A[I] ≤ B[J].

# We say that the set of segments is non-overlapping if it contains no two overlapping segments. The goal is to find the size of a non-overlapping set containing the maximal number of segments.

# For example, consider arrays A, B such that:

#     A[0] = 1    B[0] = 5
#     A[1] = 3    B[1] = 6
#     A[2] = 7    B[2] = 8
#     A[3] = 9    B[3] = 9
#     A[4] = 9    B[4] = 10
# The segments are shown in the figure below.



# The size of a non-overlapping set containing a maximal number of segments is 3. For example, possible sets are {0, 2, 3}, {0, 2, 4}, {1, 2, 3} or {1, 2, 4}. There is no non-overlapping set with four segments.

# Write a function:

# int solution(int A[], int B[], int N);
# that, given two zero-indexed arrays A and B consisting of N integers, returns the size of a non-overlapping set containing a maximal number of segments.

# For example, given arrays A, B shown above, the function should return 3, as explained above.

# Assume that:

# N is an integer within the range [0..30,000];
# each element of arrays A, B is an integer within the range [0..1,000,000,000];
# A[I] ≤ B[I], for each I (0 ≤ I < N);
# B[K] ≤ B[K + 1], for each K (0 ≤ K < N − 1).
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).





def solution(A, B):

	
    # No overlapping is possible.
    if len(A) < 2:      return len(A)

    count = 1       # The first segment starts a new cluster.
    end = B[0]
    index = 1       # The second segment.
    while index < len(A):
        # Skip all the segments in this cluster.
        while index < len(A) and A[index] <= end:   index += 1

        # All segments are processed.
        if index == len(A):                         break

        # Start a new cluster.
        count += 1
        end = B[index]

    return count



# 
def solution(A, B):
    if len(A) < 1:
        return 0
     
    cnt = 1
    prev_end = B[0]
     
    for idx in xrange(1, len(A)):
        if A[idx] > prev_end:
            cnt += 1
            prev_end = B[idx]
     
    return cnt
