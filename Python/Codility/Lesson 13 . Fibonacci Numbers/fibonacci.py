# Ladder
# ======

# You have to climb up a ladder. The ladder has exactly N rungs, numbered from 1 to N. With each step, you can ascend by one or two rungs. More precisely:

# with your first step you can stand on rung 1 or 2,
# if you are on rung K, you can move to rungs K + 1 or K + 2,
# finally you have to stand on rung N.
# Your task is to count the number of different ways of climbing to the top of the ladder.

# For example, given N = 4, you have five different ways of climbing, ascending by:

# 1, 1, 1 and 1 rung,
# 1, 1 and 2 rungs,
# 1, 2 and 1 rung,
# 2, 1 and 1 rungs, and
# 2 and 2 rungs.
# Given N = 5, you have eight different ways of climbing, ascending by:

# 1, 1, 1, 1 and 1 rung,
# 1, 1, 1 and 2 rungs,
# 1, 1, 2 and 1 rung,
# 1, 2, 1 and 1 rung,
# 1, 2 and 2 rungs,
# 2, 1, 1 and 1 rungs,
# 2, 1 and 2 rungs, and
# 2, 2 and 1 rung.
# The number of different ways can be very large, so it is sufficient to return the result modulo 2P, for a given integer P.

# Assume that the following declarations are given:

# struct Results {
#   int * C;
#   int L;
# };
# Write a function:

# struct Results solution(int A[], int B[], int L);
# that, given two non-empty zero-indexed arrays A and B of L integers, returns an array consisting of L integers specifying the consecutive answers; position I should contain the number of different ways of climbing the ladder with A[I] rungs modulo 2B[I].

# For example, given L = 5 and:

#     A[0] = 4   B[0] = 3
#     A[1] = 4   B[1] = 2
#     A[2] = 5   B[2] = 4
#     A[3] = 5   B[3] = 3
#     A[4] = 1   B[4] = 1
# the function should return the sequence [5, 1, 8, 0, 1], as explained above.

# Assume that:

# L is an integer within the range [1..30,000];
# each element of array A is an integer within the range [1..L];
# each element of array B is an integer within the range [1..30].
# Complexity:

# expected worst-case time complexity is O(L);
# expected worst-case space complexity is O(L), beyond input storage (not counting the storage required for input arguments).


def solution(A, B):
    limit    = max(A)                 # The possible largest N rungs
    result   = [0] * len(A)           # The result for each query
    modLimit = (1 << max(B)) - 1      # To avoid big interger in fibs

    # Compute the Fibonacci numbers for later use
    fib    = [0] * (limit+2)
    fib[1] = 1
    for i in xrange(2, limit + 2):
        fib[i] = (fib[i - 1] + fib[i - 2]) & modLimit

    for i in xrange(len(A)):
        # To climb to A[i] rungs, you could either
        # come from A[i]-1 with an one-step jump
        # OR come from A[i]-2 with a two-step jump
        # So from A[i] rungs, the number of different ways of climbing
        # to the top of the ladder is the Fibonacci number at position
        # A[i] + 1
        result[i] = fib[A[i]+1] & ((1<<B[i])-1)

    return result


#  100 / 100

def solution(A, B):
    limit = len(A)                  # The possible largest N rungs
    result = [0] * len(A)           # The result for each query
    B = [(1<<item)-1 for item in B] # Pre-compute B for optimization

    # Compute the Fibonacci numbers for later use
    fib = [0] * (limit+2)
    fib[1] = 1
    for i in xrange(2, limit + 2):
        fib[i] = fib[i - 1] + fib[i - 2]

    for i in xrange(limit):
        # To climb to A[i] rungs, you could either
        # come from A[i]-1 with an one-step jump
        # OR come from A[i]-2 with a two-step jump
        # So from A[i] rungs, the number of different ways of climbing
        # to the top of the ladder is the Fibonacci number at position
        # A[i] + 1
        result[i] = fib[A[i]+1] & B[i]

    return result



# solution-c

def solution(A, B):
    L = max(A)
    P_max = max(B)
  
    fib = [0] * (L+2)
    fib[1] = 1
    for i in xrange(2, L + 2):
        fib[i] = (fib[i-1] + fib[i-2]) & ((1 << P_max) - 1)
  
    return_arr = [0] * len(A)
  
    for idx in xrange(len(A)):
        return_arr[idx] = fib[A[idx]+1] & ((1 << B[idx]) - 1)
  
    return return_arr




#  FibFrog
#  =======


# The Fibonacci sequence is defined using the following recursive formula:

#     F(0) = 0
#     F(1) = 1
#     F(M) = F(M - 1) + F(M - 2) if M >= 2
# A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river (position −1) and wants to get to the other bank (position N). The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number. Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of the bank at position N.

# The leaves on the river are represented in a zero-indexed array A consisting of N integers. Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:

# 0 represents a position without a leaf;
# 1 represents a position containing a leaf.
# The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from position −1 to position N). The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.

# For example, consider array A such that:

#     A[0] = 0
#     A[1] = 0
#     A[2] = 0
#     A[3] = 1
#     A[4] = 1
#     A[5] = 0
#     A[6] = 1
#     A[7] = 0
#     A[8] = 0
#     A[9] = 0
#     A[10] = 0
# The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.

# Write a function:

# int solution(int A[], int N);
# that, given a zero-indexed array A consisting of N integers, returns the minimum number of 
# jumps by which the frog can get to the other side of the river. If the frog cannot reach the 
# other side of the river, the function should return −1.

# For example, given:

#     A[0] = 0
#     A[1] = 0
#     A[2] = 0
#     A[3] = 1
#     A[4] = 1
#     A[5] = 0
#     A[6] = 1
#     A[7] = 0
#     A[8] = 0
#     A[9] = 0
#     A[10] = 0
# the function should return 3, as explained above.

# Assume that:

# N is an integer within the range [0..100,000];
# each element of array A is an integer that can have one of the following values: 0, 1.
# Complexity:

# expected worst-case time complexity is O(N*log(N));
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage 
# required for input arguments).




def fibonacciDynamic(n):
    # Generate and return all the Fibonacci numbers,
    # less than or equal to n, in descending order.
    # n must be larger than or equal to one.
    fib = [0] * (n + 2)
    fib[1] = 1
    for i in xrange(2, n + 2):
        fib[i] = fib[i - 1] + fib[i - 2]
        if fib[i] > n:
            return fib[i-1: 1: -1]
        elif fib[i] == n:
            return fib[i: 1: -1]

def solution(A):
    class Status(object):
        # Object to store the status of attempts
        __slots__ = ('position', 'moves')
        def __init__(self, pos, moves):
            self.position = pos
            self.moves = moves
            return

    lenA = len(A)                        # Array length
    fibonacci = fibonacciDynamic(lenA+1) # Fibonacci numbers
    statusQueue = [Status(-1,0)]    # Initially we are at position
                                    # -1 with 0 move.
    nextTry = 0     # We are not going to delete the tried attemp.
                    # So we need a pointer to the next attemp.
    accessed = [False] * len(A) # Were we in this position before?

    while True:
        if nextTry == len(statusQueue):
            # There is no unprocessed attemp. And we did not
            # find any path yet. So no path exists.
            return -1

        # Obtain the next attemp's status
        currentStatus = statusQueue[nextTry]
        nextTry += 1
        currentPos = currentStatus.position
        currentMoves = currentStatus.moves

        # Based upon the current attemp, we are trying any
        # possible move.
        for length in fibonacci:
            if currentPos + length == lenA:
                # Ohhh~ We are at the goal position!
                return currentMoves + 1
            elif currentPos + length > lenA
                 or A[currentPos + length] == 0
                 or accessed[currentPos + length]:
                # Three conditions are moving too far, no
                # leaf available for moving, and being here
                # before respectively.
                # PAY ATTENTION: we are using Breadth First
                # Search. If we were here before, the previous
                # attemp must achieved here with less or same
                # number of moves. With completely same future
                # path, current attemp will never have less
                # moves to goal than previous attemp. So it
                # could be pruned.
                continue

            # Enqueue for later attemp.
            statusQueue.append(
                Status(currentPos + length, currentMoves+1))
            accessed[currentPos + length] = True



# solution-b

def get_fib_seq_up_to_n(N):
    # there are 26 numbers smaller than 100k
    fib = [0] * (27)
    fib[1] = 1
    for i in xrange(2, 27):
        fib[i] = fib[i - 1] + fib[i - 2]
        if fib[i] > N:
            return fib[2:i]
        else:
            last_valid = i
     
     
     
def solution(A):
    # you can always step on the other shore, this simplifies the algorithm
    A.append(1)
 
    fib_set = get_fib_seq_up_to_n(len(A))
     
    # this array will hold the optimal jump count that reaches this index
    reachable = [-1] * (len(A))
     
    # get the leafs that can be reached from the starting shore
    for jump in fib_set:
        if A[jump-1] == 1:
            reachable[jump-1] = 1
     
    # iterate all the positions until you reach the other shore
    for idx in xrange(len(A)):
        # ignore non-leafs and already found paths
        if A[idx] == 0 or reachable[idx] > 0:
            continue
 
        # get the optimal jump count to reach this leaf
        min_idx = -1
        min_value = 100000
        for jump in fib_set:
            previous_idx = idx - jump
            if previous_idx < 0:
                break
            if reachable[previous_idx] > 0 and min_value > reachable[previous_idx]:
                min_value = reachable[previous_idx]
                min_idx = previous_idx
        if min_idx != -1:
            reachable[idx] = min_value +1
 
    return reachable[len(A)-1]