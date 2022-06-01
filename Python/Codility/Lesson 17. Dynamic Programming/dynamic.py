#  NumberSolitaire
#  ---------------



# A game for one player is played on a board consisting of N consecutive squares, numbered from 0 to N − 1. There is a number written on each square. A non-empty zero-indexed array A of N integers contains the numbers written on the squares. Moreover, some squares can be marked during the game.

# At the beginning of the game, there is a pebble on square number 0 and this is the only square on the board which is marked. The goal of the game is to move the pebble to square number N − 1.

# During each turn we throw a six-sided die, with numbers from 1 to 6 on its faces, and consider the number K, which shows on the upper face after the die comes to rest. Then we move the pebble standing on square number I to square number I + K, providing that square number I + K exists. If square number I + K does not exist, we throw the die again until we obtain a valid move. Finally, we mark square number I + K.

# After the game finishes (when the pebble is standing on square number N − 1), we calculate the result. The result of the game is the sum of the numbers written on all marked squares.

# For example, given the following array:

#     A[0] = 1
#     A[1] = -2
#     A[2] = 0
#     A[3] = 9
#     A[4] = -1
#     A[5] = -2
# one possible game could be as follows:

# the pebble is on square number 0, which is marked;
# we throw 3; the pebble moves from square number 0 to square number 3; we mark square number 3;
# we throw 5; the pebble does not move, since there is no square number 8 on the board;
# we throw 2; the pebble moves to square number 5; we mark this square and the game ends.
# The marked squares are 0, 3 and 5, so the result of the game is 1 + 9 + (−2) = 8. This is the maximal possible result that can be achieved on this board.

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A of N integers, returns the maximal result that can be achieved on the board represented by array A.

# For example, given the array

#     A[0] = 1
#     A[1] = -2
#     A[2] = 0
#     A[3] = 9
#     A[4] = -1
#     A[5] = -2
# the function should return 8, as explained above.

# Assume that:

# N is an integer within the range [2..100,000];
# each element of array A is an integer within the range [−10,000..10,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).


def solution(A):
    # The first six items are used for padding only, so that we can have
    # a unified for loop, no matter how many squares are there in input.
    # The first item is never accessed.
    max_so_far = [A[0]] * (len(A) + 6)

    for index in xrange(1, len(A)):
        # Because we have a fixed length of window as 6, the time
        # complexity of max(max_so_far[index : index + 6]) is O(1).
        max_so_far[index + 6] = max(max_so_far[index : index + 6]) +
                                A[index]

    return max_so_far[-1]



# solution-b

NR_POSSIBLE_ROLLS = 6
MIN_VALUE = -10000000001
 
def solution1(A):
    sub_solutions = [MIN_VALUE] * (len(A)+NR_POSSIBLE_ROLLS)
    sub_solutions[NR_POSSIBLE_ROLLS] = A[0]
     
    # iterate over all steps
    for idx in xrange(NR_POSSIBLE_ROLLS+1, len(A)+NR_POSSIBLE_ROLLS):
        max_previous = MIN_VALUE
        for previous_idx in xrange(NR_POSSIBLE_ROLLS):
            max_previous = max(max_previous, sub_solutions[idx-previous_idx-1])
        # the value for each iteration is the value at the A array plus the best value from which this index can be reached
        sub_solutions[idx] = A[idx-NR_POSSIBLE_ROLLS] + max_previous
     
    return sub_solutions[len(A)+NR_POSSIBLE_ROLLS-1]







#  MinAbsSum
#  ---------

# For a given array A of N integers and a sequence S of N integers from the set {−1, 1}, we define val(A, S) as follows:

# val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|
# (Assume that the sum of zero elements equals zero.)

# For a given array A, we are looking for such a sequence S that minimizes val(A,S).

# Write a function:

# int solution(int A[], int N);
# that, given an array A of N integers, computes the minimum value of val(A,S) from all possible values of val(A,S) for all possible sequences S of N integers from the set {−1, 1}.

# For example, given array:

#   A[0] =  1
#   A[1] =  5
#   A[2] =  2
#   A[3] = -2
# your function should return 0, since for S = [−1, 1, −1, 1], val(A, S) = 0, which is the minimum possible value.

# Assume that:

# N is an integer within the range [0..20,000];
# each element of array A is an integer within the range [−100..100].
# Complexity:

# expected worst-case time complexity is O(N*max(abs(A))2);
# expected worst-case space complexity is O(N+sum(abs(A))), beyond input storage (not counting the storage required for input arguments).



def solution(A):
    # Since S could be 1 or -1, it does not matter that
    # each element in A is positive or negative.
    A = [abs(item) for item in A]
    sumOfA = sum(A)

    # Get the number distribution. So we do not need to try
    # each number for multiple times.
    numbers = {}
    for item in A:  numbers[item] = numbers.get(item, 0) + 1

    # This is the KEY point.
    # Typically, we will use possible = [False] * len to track, which numbers
    # could be the result by summing up subsets of A.
    # For a number, appeared for many times, there will be multiple attempts
    # for it. But in this way, when we are trying number n,
    # possible[i] == -1 means it is impossible.
    # possible[i] == i >= 0 means it is possible and there are i n(s) left to use.
    # So for ALL number n(s), we only need ONE scan over the record.
    possible = [-1] * (sumOfA // 2 + 1)
    possible[0] = 0

    for number in numbers:      # Try each distinct number
        for trying in xrange(sumOfA//2+1):
            if possible[trying] >= 0:
                # Can be reached with previous numbers
                possible[trying] = numbers[number]
            elif trying >= number and possible[trying-number] > 0:
                # Cannot be reached with only previous numbers.
                # But could be achieved with previous numbers AND current one.
                possible[trying] = possible[trying-number] - 1

    # Divide the A into two parts: P and Q, with restriction P <= Q.
    # So P <= sumOfA // 2 <= Q. We want the largest possible P, so that
    # Q-P is minimized.
    for halfSum in xrange(sumOfA//2, -1, -1):
        if possible[halfSum] >= 0:
            return sumOfA - halfSum - halfSum

    raise Exception("Should never be here!")
    return 0



