
#  Distinct
#  --------
# Write a function

# int solution(int A[], int N);
# that, given a zero-indexed array A consisting of N integers, returns the number of distinct values in array A.

# Assume that:

# N is an integer within the range [0..100,000];
# each element of array A is an integer within the range [−1,000,000..1,000,000].
# For example, given array A consisting of six elements such that:

#  A[0] = 2    A[1] = 1    A[2] = 1
#  A[3] = 2    A[4] = 3    A[5] = 1
# the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.

# Complexity:

# expected worst-case time complexity is O(N*log(N));
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.


def solution(A):
    if len(A) == 0:
        distinct = 0
    else:
        distinct = 1

        A.sort()
        for index in xrange(1, len(A)):
            if A[index] == A[index-1]:
                # The same element as the previous one
                continue
            else:
                # A new element
                distinct += 1

    return distinct

def solution1(A):
    return len(set(A))

def solution2(A):
    if len(A) == 0:
        return 0
 
    A.sort()
 
    nr_values = 1
    last_value = A[0]
 
    for idx in xrange(1, len(A)):
        if A[idx] != last_value:
            nr_values += 1
            last_value = A[idx]
 
    return nr_values




#  MaxProductOfThree
#  -----------------

# A non-empty zero-indexed array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

# For example, array A such that:

#   A[0] = -3
#   A[1] = 1
#   A[2] = 2
#   A[3] = -2
#   A[4] = 5
#   A[5] = 6
# contains the following example triplets:

# (0, 1, 2), product is −3 * 1 * 2 = −6
# (1, 2, 4), product is 1 * 2 * 5 = 10
# (2, 4, 5), product is 2 * 5 * 6 = 60
# Your goal is to find the maximal product of any triplet.

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.

# For example, given array A such that:

#   A[0] = -3
#   A[1] = 1
#   A[2] = 2
#   A[3] = -2
#   A[4] = 5
#   A[5] = 6
# the function should return 60, as the product of triplet (2, 4, 5) is maximal.

# Assume that:

# N is an integer within the range [3..100,000];
# each element of array A is an integer within the range [−1,000..1,000].
# Complexity:

# expected worst-case time complexity is O(N*log(N));
# expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.

def solution(A):
    A.sort()
    return max(A[0]*A[1]*A[-1], A[-1]*A[-2]*A[-3])

def solution1(A):
    if len(A) < 3:
        raise Exception("Invalid input")
         
    A.sort()     
    return max(A[0] * A[1] * A[-1], A[-1] * A[-2] * A[-3])






# Tringle 
# -------

# A zero-indexed array A consisting of N integers is given. A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:

# A[P] + A[Q] > A[R],
# A[Q] + A[R] > A[P],
# A[R] + A[P] > A[Q].
# For example, consider array A such that:

#   A[0] = 10    A[1] = 2    A[2] = 5
#   A[3] = 1     A[4] = 8    A[5] = 20
# Triplet (0, 2, 4) is triangular.

# Write a function:

# int solution(int A[], int N);
# that, given a zero-indexed array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise.

# For example, given array A such that:

#   A[0] = 10    A[1] = 2    A[2] = 5
#   A[3] = 1     A[4] = 8    A[5] = 20
# the function should return 1, as explained above. Given array A such that:

#   A[0] = 10    A[1] = 50    A[2] = 5
#   A[3] = 1
# the function should return 0.

# Assume that:

# N is an integer within the range [0..100,000];
# each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
# Complexity:

# expected worst-case time complexity is O(N*log(N));
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).



def solution(A):

    A_len = len(A)
    if A_len < 3:
        # N is an integer within the range [0..1,000,000]
        # if the list is too short, it is impossible to
        # find out a triangular.
        return 0

    A.sort()

    for index in xrange(0, A_len-2):
        if A[index]+A[index+1] > A[index+2]:
            return 1
        # The list is sorted, so A[index+i] >= A[index+2]
        # where i>2. If A[index]+A[index+1] <= A[index+2],
        # then A[index]+A[index+1] <= A[index+i], where
        # i>=2. So there is no element in A[index+2:] that
        # could be combined with A[index] and A[index+1]
        # to be a triangular.

    # No triangular is found
    return 0


def solution1(A):
    if len(A) < 3:
        raise Exception("invalid input")
    A.sort()
    for i in xrange(len(A)-2):
        if A[i] + A[i+1] > A[i+2]:
            return 1             
    return 0







# NumberOfDiscIntersections 
# -------------------------


# We draw N discs on a plane. The discs are numbered from 0 to N − 1. A zero-indexed array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].

# We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).

# The figure below shows discs drawn for N = 6 and A as follows:

#   A[0] = 1
#   A[1] = 5
#   A[2] = 2
#   A[3] = 1
#   A[4] = 4
#   A[5] = 0


# There are eleven (unordered) pairs of discs that intersect, namely:

# discs 1 and 4 intersect, and both intersect with all the other discs;
# disc 2 also intersects with discs 0 and 3.
# Write a function:

# int solution(int A[], int N);
# that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.

# Given array A shown above, the function should return 11, as explained above.


def solution(A):

    discs_count = len(A)            # The total number of discs
    range_upper = [0] * discs_count   # The upper limit position of discs
    range_lower = [0] * discs_count   # The lower limit position of discs

    # Fill the limit_upper and limit_lower
    for index in xrange(0, discs_count):
        range_upper[index] = index + A[index]
        range_lower[index] = index - A[index]

    range_upper.sort()
    range_lower.sort()

    range_lower_index = 0
    intersect_count = 0

    for range_upper_index in xrange(0, discs_count):
        # Compute for each disc
        while range_lower_index < discs_count and
            range_upper[range_upper_index] >= range_lower[range_lower_index]:
            # Find all the discs that:
            #    disc_center - disc_radius <= current_center + current_radius
            range_lower_index += 1
        # We must exclude some discs such that:
        #    disc_center - disc_radius <= current_center + current_radius
        #    AND
        #    disc_center + disc_radius <(=) current_center + current_radius
        # These discs are not intersected with current disc, and below the
        #    current one completely.
        # After removing, the left discs are intersected with current disc,
        #    and above the current one.
        # Attention: the current disc is intersecting itself in this result
        #    set. But it should not be. So we need to minus one to fix it.
        intersect_count += range_lower_index - range_upper_index -1
        if intersect_count > 10000000:
            return -1

    return intersect_count






def solution1(A):

    circle_endpoints = []

    for i, a in enumerate(A):
        circle_endpoints += [(i-a, True), (i+a, False)]
 
    circle_endpoints.sort(key=lambda x: (x[0], not x[1]))
 
    intersections, active_circles = 0, 0
 
    for _, is_beginning in circle_endpoints:
        if is_beginning:
            intersections += active_circles
            active_circles += 1
        else:
            active_circles -= 1
        if intersections > 10E6:
            return -1
 
    return intersections





