# PassingCars
# -----------


# A non-empty zero-indexed array A consisting of N integers is given. The consecutive elements of 
# array A represent consecutive cars on a road.

# Array A contains only 0s and/or 1s:

# 0 represents a car traveling east,
# 1 represents a car traveling west.
# The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is 
# passing when P is traveling to the east and Q is traveling to the west.

# For example, consider array A such that:

#   A[0] = 0
#   A[1] = 1
#   A[2] = 0
#   A[3] = 1
#   A[4] = 1
# We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A of N integers, returns the number of pairs of passing cars.

# The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.

# For example, given:

#   A[0] = 0
#   A[1] = 1
#   A[2] = 0
#   A[3] = 1
#   A[4] = 1
# the function should return 5, as explained above.

# Assume that:

# N is an integer within the range [1..100,000];
# each element of array A is an integer that can have one of the following values: 0, 1.
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.

def solution(A):
    west = 0    # The number of west-driving cars so far
    passing = 0 # The number of passing

    for index in xrange(len(A)-1,-1,-1):
        # Travel the list from the end to the beginning
        if A[index] == 0:    # A east-driving car
            passing += west
            if passing > 1000000000:
                return -1
        else:                # A west-driving car
            west += 1

    return passing





# CountDiv
# --------



# Write a function:

# int solution(int A, int B, int K);
# that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:

# { i : A ≤ i ≤ B, i mod K = 0 }
# For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.

# Assume that:

# A and B are integers within the range [0..2,000,000,000];
# K is an integer within the range [1..2,000,000,000];
# A ≤ B.
# Complexity:

# expected worst-case time complexity is O(1);
# expected worst-case space complexity is O(1).


# solution-a
def solution(A, B, K):    
    if A % K == 0:  
    	return (B - A) // K + 1
    else:           
    	return (B - (A - A % K )) // K


# solution-b
def solution1(A, B, K):
    if B < A or K <= 0:
        raise Exception("Invalid Input")
 
    min_value =  ((A + K -1) // K) * K
 
    if min_value > B:
      return 0
 
    return ((B - min_value) // K) + 1






# MinAvgTwoSlice
# --------------

# A non-empty zero-indexed array A consisting of N integers is given. A pair of integers 
# (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains 
# at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] 
# divided by the length of the slice. To be precise, 
# the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).

# For example, array A such that:

#     A[0] = 4
#     A[1] = 2
#     A[2] = 2
#     A[3] = 5
#     A[4] = 1
#     A[5] = 5
#     A[6] = 8
# contains the following example slices:

# slice (1, 2), whose average is (2 + 2) / 2 = 2;
# slice (3, 4), whose average is (5 + 1) / 2 = 3;
# slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
# The goal is to find the starting position of a slice whose average is minimal.

# Write a function:
# -----------------

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A consisting of N integers, returns the starting 
# position of the slice with the minimal average. If there is more than one slice with a minimal 
# average, you should return the smallest starting position of such a slice.

# For example, given array A such that:

#     A[0] = 4
#     A[1] = 2
#     A[2] = 2
#     A[3] = 5
#     A[4] = 1
#     A[5] = 5
#     A[6] = 8
# the function should return 1, as explained above.

# Assume that:

# N is an integer within the range [2..100,000];
# each element of array A is an integer within the range [−10,000..10,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.


#  return the starting position of the slice with min average 
#  where, the average equals (A[P] + A[P + 1] + ... + A[Q])/(Q − P + 1)


# solution-a
def solution(A):

    min_avg_value = (A[0] + A[1])/2.0   # The mininal average
    min_avg_pos = 0     # The begin position of the first
                        # slice with mininal average

    for index in xrange(0, len(A)-2):

        # Try the next 2-element slice
        if (A[index] + A[index+1]) / 2.0 < min_avg_value:
            min_avg_value = (A[index] + A[index+1]) / 2.0
            min_avg_pos = index

        # Try the next 3-element slice
        if (A[index] + A[index+1] + A[index+2]) / 3.0 < min_avg_value:
            min_avg_value = (A[index] + A[index+1] + A[index+2]) / 3.0
            min_avg_pos = index

    # Try the last 2-element slice
    if (A[-1]+A[-2])/2.0 < min_avg_value:
        min_avg_value = (A[-1]+A[-2])/2.0
        min_avg_pos = len(A)-2

    return min_avg_pos



# solution-b
def solution1(A):

    min_idx = 0
    min_value = 10001
 
    for idx in xrange(0, len(A)-1):
        if (A[idx] + A[idx+1])/2.0 < min_value:
            min_idx = idx
            min_value = (A[idx] + A[idx+1])/2.0
            
        if idx < len(A)-2 and (A[idx] + A[idx+1] + A[idx+2])/3.0 < min_value:
            min_idx = idx
            min_value = (A[idx] + A[idx+1] + A[idx+2])/3.0
 
    return min_idx








# GenomicRangeQuery
# -----------------

# A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which 
# correspond to the types of successive nucleotides in the sequence. Each nucleotide has an impact 
# factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 
# and 4, respectively. You are going to answer several queries of the form: What is the minimal 
# impact factor of nucleotides contained in a particular part of the given DNA sequence ?

# The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. 
# There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. 
# The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained 
# in the DNA sequence between positions P[K] and Q[K] (inclusive).


# For example, consider string S = CAGCCTA and arrays P, Q such that:

#     P[0] = 2    Q[0] = 4
#     P[1] = 5    Q[1] = 5
#     P[2] = 0    Q[2] = 6
# The answers to these M = 3 queries are as follows:

# The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact 
# factors are 3 and 2 respectively, so the answer is 2. The part between positions 5 and 5 contains a 
# single nucleotide T, whose impact factor is 4, so the answer is 4. The part between positions 0 and 6 
# (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so 
# the answer is 1. Assume that the following declarations are given:

# struct Results {
#   int * A;
#   int M;
# };
# Write a function:

# struct Results solution(char *S, int P[], int Q[], int M);
# that, given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.

# The sequence should be returned as:

# a Results structure (in C), or
# a vector of integers (in C++), or
# a Results record (in Pascal), or
# an array of integers (in any other programming language).
# For example, given the string S = CAGCCTA and arrays P, Q such that:

#     P[0] = 2    Q[0] = 4
#     P[1] = 5    Q[1] = 5
#     P[2] = 0    Q[2] = 6
# the function should return the values [2, 4, 1], as explained above.

# Assume that:

# N is an integer within the range [1..100,000];
# M is an integer within the range [1..50,000];
# each element of arrays P, Q is an integer within the range [0..N − 1];
# P[K] ≤ Q[K], where 0 ≤ K < M;
# string S consists only of upper-case English letters A, C, G, T.
# Complexity:

# expected worst-case time complexity is O(N+M);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.




# solution-a 
def solution(S, P, Q):
	
    result = []
    DNA_len = len(S)
    mapping = {"A":1, "C":2, "G":3, "T":4}

    # next_nucl is used to store the position information
    # next_nucl[0] is about the "A" nucleotides, [1] about "C"
    #    [2] about "G", and [3] about "T"
    # next_nucl[i][j] = k means: for the corresponding nucleotides i,
    #    at position j, the next corresponding nucleotides appears
    #    at position k (including j)
    # k == -1 means: the next corresponding nucleotides does not exist

    next_nucl = [[-1]*DNA_len, [-1]*DNA_len, [-1]*DNA_len, [-1]*DNA_len]


    # Scan the whole DNA sequence, and retrieve the position information
    next_nucl[mapping[S[-1]] - 1][-1] = DNA_len-1

    for index in range(DNA_len-2,-1,-1):
        next_nucl[0][index] = next_nucl[0][index+1]
        next_nucl[1][index] = next_nucl[1][index+1]
        next_nucl[2][index] = next_nucl[2][index+1]
        next_nucl[3][index] = next_nucl[3][index+1]
        next_nucl[mapping[S[index]] - 1][index] = index

    for index in range(0,len(P)):
        if next_nucl[0][P[index]] != -1 and next_nucl[0][P[index]] <= Q[index]:
            result.append(1)
        elif next_nucl[1][P[index]] != -1 and next_nucl[1][P[index]] <= Q[index]:
            result.append(2)
        elif next_nucl[2][P[index]] != -1 and next_nucl[2][P[index]] <= Q[index]:
            result.append(3)
        else:
            result.append(4)

    return result




# solution-b 
def solution1(S, P, Q):
     
    if len(P) != len(Q):
        raise Exception("Invalid input")
     
    last_seen_A = [-1] * len(S)
    last_seen_C = [-1] * len(S)
    last_seen_G = [-1] * len(S)
    last_seen_T = [-1] * len(S)
         
    for idx in xrange(len(S)):
        writeCharToList(S, last_seen_A, 'A', idx)
        writeCharToList(S, last_seen_C, 'C', idx)
        writeCharToList(S, last_seen_G, 'G', idx)
        writeCharToList(S, last_seen_T, 'T', idx)
          
    solution = [0] * len(Q)
     
    for idx in xrange(len(Q)):
        if last_seen_A[Q[idx]] >= P[idx]:
            solution[idx] = 1
        elif last_seen_C[Q[idx]] >= P[idx]:
            solution[idx] = 2
        elif last_seen_G[Q[idx]] >= P[idx]:
            solution[idx] = 3
        elif last_seen_T[Q[idx]] >= P[idx]:
            solution[idx] = 4
        else:    
            raise Exception("Should never happen")
         
    return solution

def writeCharToList(S, last_seen, c, idx):
    if S[idx] == c:
        last_seen[idx] = idx
    elif idx > 0:
        last_seen[idx] = last_seen[idx -1]


# solution-c
def solution2(S, P, Q):

    if len(P) != len(Q):
        raise Exception("Invalid input data!")

    result = []

    #  dictionary for mapping the numeric values 
    mapping = {"A":1, "C":2, "G":3, "T":4}

    for p, q  in zip(P, Q):
        ss =  S[p:q+1]
        min_value = mapping[ss[0]]

        for s in ss:
            if(mapping[s] < min_value):
                min_value = mapping[s]
        result.append(min_value)

    return result
