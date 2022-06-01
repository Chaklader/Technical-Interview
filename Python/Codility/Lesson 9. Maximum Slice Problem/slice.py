# MaxProfit
# ---------


# A zero-indexed array A consisting of N integers is given. It contains daily prices of a stock share for a period of N consecutive days. If a single share was bought on day P and sold on day Q, where 0 ≤ P ≤ Q < N, then the profit of such transaction is equal to A[Q] − A[P], provided that A[Q] ≥ A[P]. Otherwise, the transaction brings loss of A[P] − A[Q].

# For example, consider the following array A consisting of six elements such that:

#   A[0] = 23171
#   A[1] = 21011
#   A[2] = 21123
#   A[3] = 21366
#   A[4] = 21013
#   A[5] = 21367
# If a share was bought on day 0 and sold on day 2, a loss of 2048 would occur because A[2] − A[0] = 21123 − 23171 = −2048. If a share was bought on day 4 and sold on day 5, a profit of 354 would occur because A[5] − A[4] = 21367 − 21013 = 354. Maximum possible profit was 356. It would occur if a share was bought on day 1 and sold on day 5.

# Write a function,

# int solution(int A[], int N);
# that, given a zero-indexed array A consisting of N integers containing daily prices of a stock share for a period of N consecutive days, returns the maximum possible profit from one transaction during this period. The function should return 0 if it was impossible to gain any profit.

# For example, given array A consisting of six elements such that:

#   A[0] = 23171
#   A[1] = 21011
#   A[2] = 21123
#   A[3] = 21366
#   A[4] = 21013
#   A[5] = 21367
# the function should return 356, as explained above.

# Assume that:

# N is an integer within the range [0..400,000];
# each element of array A is an integer within the range [0..200,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).


def solution(A):
    days = len(A)

    # If the number of days is zero or one, there
    # is no time to get profit.
    if days < 2:
        return 0

    max_price_from_here = A[days-1]
    max_profit = 0
    for index in xrange(days-2, -1, -1):
        # max_price_from_here-A[index] means the maximum
        # profit from current day to end.
        max_profit = max(max_profit, max_price_from_here-A[index])
        max_price_from_here = max(A[index], max_price_from_here)

    return max_profit



def solution1(A):

    max_profit = 0
    max_day = 0
    min_day = 200000
     
    for day in A:
        min_day = min(min_day, day)
        max_profit = max(max_profit, day-min_day)
     
    return max_profit




#  MaxSliceSum
#  ===========

# A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].

# Write a function:

# int solution(int A[], int N);
# that, given an array A consisting of N integers, returns the maximum sum of any slice of A.

# For example, given array A such that:

# A[0] = 3  A[1] = 2  A[2] = -6
# A[3] = 4  A[4] = 0
# the function should return 5 because:

# (3, 4) is a slice of A that has sum 4,
# (2, 2) is a slice of A that has sum −6,
# (0, 1) is a slice of A that has sum 5,
# no other slice of A has sum greater than (0, 1).
# Assume that:

# N is an integer within the range [1..1,000,000];
# each element of array A is an integer within the range [−1,000,000..1,000,000];
# the result will be an integer within the range [−2,147,483,648..2,147,483,647].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).


def solution(A):
    max_slice_ending_here = A[0]
    max_slice = A[0]

    for element in A[1:]:
        max_slice_ending_here = max(element, max_slice_ending_here+element)
        max_slice = max(max_slice, max_slice_ending_here)

    return max_slice


def solution1(A):

    max_ending = max_slice = -1000000
    for a in A:
        max_ending = max(a, max_ending +a)
        max_slice = max(max_slice, max_ending)
         
    return max_slice





# MaxDoubleSliceSum 
# =================


# A non-empty zero-indexed array A consisting of N integers is given.

# A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.

# The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].

# For example, array A such that:

#     A[0] = 3
#     A[1] = 2
#     A[2] = 6
#     A[3] = -1
#     A[4] = 4
#     A[5] = 5
#     A[6] = -1
#     A[7] = 2
# contains the following example double slices:

# double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
# double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
# double slice (3, 4, 5), sum is 0.
# The goal is to find the maximal sum of any double slice.

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A consisting of N integers, returns the maximal sum of any double slice.

# For example, given:

#     A[0] = 3
#     A[1] = 2
#     A[2] = 6
#     A[3] = -1
#     A[4] = 4
#     A[5] = 5
#     A[6] = -1
#     A[7] = 2
# the function should return 17, because no double slice of array A has a sum of greater than 17.

# Assume that:

# N is an integer within the range [3..100,000];
# each element of array A is an integer within the range [−10,000..10,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).



def solution(A):
    A_len = len(A)    # The length of array A

    # Get the sum of maximum subarray, which ends this position
    # Method: http://en.wikipedia.org/wiki/Maximum_subarray_problem
    max_ending_here = [0] * A_len
    max_ending_here_temp = 0
    for index in xrange(1, A_len-1):
        max_ending_here_temp = max(0, A[index]+max_ending_here_temp)
        max_ending_here[index] = max_ending_here_temp

    # Get the sum of maximum subarray, which begins this position
    # The same method. But we travel from the tail to the head
    max_beginning_here = [0] * A_len
    max_beginning_here_temp = 0
    for index in xrange(A_len-2, 0, -1):
        max_beginning_here_temp = max(0, A[index]+max_beginning_here_temp)
        max_beginning_here[index] = max_beginning_here_temp

    # Connect two subarray for a double_slice. If the first subarray
    # ends at position i, the second subarray starts at position i+2.
    # Then we compare each double slice to get the one with the
    # greatest sum.
    max_double_slice = 0
    for index in xrange(0, A_len-2):
        max_double_slice = max(max_double_slice,
                 max_ending_here[index] + max_beginning_here[index+2] )

    return max_double_slice


#  solution-b

def solution1(A):

    ending_here = [0] * len(A)
    starting_here = [0] * len(A)
     
    for idx in xrange(1, len(A)):
        ending_here[idx] = max(0, ending_here[idx-1] + A[idx])
     
    for idx in reversed(xrange(len(A)-1)):
        starting_here[idx] = max(0, starting_here[idx+1] + A[idx])
     
    max_double_slice = 0
     
    for idx in xrange(1, len(A)-1):
        max_double_slice = max(max_double_slice, starting_here[idx+1] + ending_here[idx-1])
         
         
    return max_double_slice

