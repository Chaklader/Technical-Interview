# CountSemiprimes
# ---------------


# A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.

# A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers. The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.

# You are given two non-empty zero-indexed arrays P and Q, each consisting of M integers. These arrays represent queries about the number of semiprimes within specified ranges.

# Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.

# For example, consider an integer N = 26 and arrays P, Q such that:

#     P[0] = 1    Q[0] = 26
#     P[1] = 4    Q[1] = 10
#     P[2] = 16   Q[2] = 20
# The number of semiprimes within each of these ranges is as follows:

# (1, 26) is 10,
# (4, 10) is 4,
# (16, 20) is 0.
# Assume that the following declarations are given:

# struct Results {
#   int * A;
#   int M;
# };
# Write a function:

# struct Results solution(int N, int P[], int Q[], int M);
# that, given an integer N and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.

# For example, given an integer N = 26 and arrays P, Q such that:

#     P[0] = 1    Q[0] = 26
#     P[1] = 4    Q[1] = 10
#     P[2] = 16   Q[2] = 20
# the function should return the values [10, 4, 0], as explained above.

# Assume that:

# N is an integer within the range [1..50,000];
# M is an integer within the range [1..30,000];
# each element of arrays P, Q is an integer within the range [1..N];
# P[i] ≤ Q[i].
# Complexity:

# expected worst-case time complexity is O(N*log(log(N))+M);
# expected worst-case space complexity is O(N+M), beyond input storage (not counting the storage required for input arguments).





def solution(N, P, Q):
    from math import sqrt

    # Find out all the primes with Sieve of Eratosthenes
    prime_table = [False]*2+[True]*(N-1)
    prime = []
    prime_count = 0
    for element in xrange(2, int(sqrt(N))+1):
        if prime_table[element] == True:
            prime.append(element)
            prime_count += 1
            multiple = element * element
            while multiple <= N:
                prime_table[multiple] = False
                multiple += element
    for element in xrange(int(sqrt(N))+1, N+1):
        if prime_table[element] == True:
            prime.append(element)
            prime_count += 1

    # Compute the semiprimes information
    semiprime = [0] * (N+1)
    # Find out all the semiprimes.
    # semiprime[i] == 1 when i is semiprime, or
    #                 0 when i is not semiprime.
    for index_former in xrange(prime_count-1):
        for index_latter in xrange(index_former, prime_count):
            if prime[index_former]*prime[index_latter] > N:
                # So large that no need to record them
                break
            semiprime[prime[index_former]*prime[index_latter]] = 1
    # Compute the number of semiprimes until each position.
    # semiprime[i] == k means:
    # in the range (0,i] there are k semiprimes.
    for index in xrange(1, N+1):
        semiprime[index] += semiprime[index-1]

    # the number of semiprimes within the range [ P[K], Q[K] ]
    # should be semiprime[Q[K]] - semiprime[P[K]-1]
    question_len = len(P)
    result = [0]*question_len
    for index in xrange(question_len):
        result[index] = semiprime[Q[index]] - semiprime[P[index]-1]

    return result


# solution-b

def sieve(N):
    semi = set()
    sieve = [True]* (N+1)
    sieve[0] = sieve[1] = False
 
    i = 2
    while (i*i <= N):
        if sieve[i] == True:
            for j in xrange(i*i, N+1, i):
                sieve[j] = False
        i += 1
 
    i = 2
    while (i*i <= N):
        if sieve[i] == True:
            for j in xrange(i*i, N+1, i):
                if (j % i == 0 and sieve[j/i] == True):
                    semi.add(j)
        i += 1
 
    return semi
 
def solution(N, P, Q):
 
    semi_set = sieve(N)
 
    prefix = []
 
    prefix.append(0) # 0
    prefix.append(0) # 1
    prefix.append(0) # 2
    prefix.append(0) # 3
    prefix.append(1) # 4
 
    for idx in xrange(5, max(Q)+1):
        if idx in semi_set:
            prefix.append(prefix[-1]+1)
        else:
            prefix.append(prefix[-1])
 
    solution = []
 
    for idx in xrange(len(Q)):
        solution.append(prefix[Q[idx]] - prefix[P[idx]-1])
 
    return solution




# CountNonDivisible
# ----------------- 

# You are given a non-empty zero-indexed array A consisting of N integers.

# For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.

# For example, consider integer N = 5 and array A such that:

#     A[0] = 3
#     A[1] = 1
#     A[2] = 2
#     A[3] = 3
#     A[4] = 6
# For the following elements:

# A[0] = 3, the non-divisors are: 2, 6,
# A[1] = 1, the non-divisors are: 3, 2, 3, 6,
# A[2] = 2, the non-divisors are: 3, 3, 6,
# A[3] = 3, the non-divisors are: 2, 6,
# A[4] = 6, there aren't any non-divisors.
# Assume that the following declarations are given:

# struct Results {
#   int * C;
#   int L;
# };
# Write a function:

# struct Results solution(int A[], int N);
# that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.

# The sequence should be returned as:

# a structure Results (in C), or
# a vector of integers (in C++), or
# a record Results (in Pascal), or
# an array of integers (in any other programming language).
# For example, given:

#     A[0] = 3
#     A[1] = 1
#     A[2] = 2
#     A[3] = 3
#     A[4] = 6
# the function should return [2, 4, 3, 2, 0], as explained above.

# Assume that:

# N is an integer within the range [1..50,000];
# each element of array A is an integer within the range [1..2 * N].
# Complexity:

# expected worst-case time complexity is O(N*log(N));
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).



def solution(A):
    from math import sqrt

    A_max = max(A)
    A_len = len(A)

    # Compute the frequency of occurrence of each
    # element in array A
    count = {}
    for element in A:
        count[element] = count.get(element,0)+1

    # Compute the divisors for each element in A
    divisors = {}
    for element in A:
        # Every nature number has a divisor 1.
        divisors[element] = [1]
    # In this for loop, we only find out all the
    # divisors less than sqrt(A_max), with brute
    # force method.
    for divisor in xrange(2, int(sqrt(A_max))+1):
        multiple = divisor
        while multiple  <= A_max:
            if multiple in divisors and not divisor in divisors[multiple]:
                divisors[multiple].append(divisor)
            multiple += divisor
    # In this loop, we compute all the divisors
    # greater than sqrt(A_max), filter out some
    # duplicate ones, and combine them.
    for element in divisors:
        temp = [element/div for div in divisors[element]]
        # Filter out the duplicate divisors
        temp = [item for item in temp if item not in divisors[element]]
        divisors[element].extend(temp)

    # The result of each number should be, the array length minus
    # the total number of occurances of its divisors.
    result = []
    for element in A:
        result.append(A_len-sum([count.get(div,0) for div in divisors[element]]))

    return result





def solution(A):
  
    A_max = max(A)
  
    count = {}
    for element in A:
        if element not in count:
            count[element] = 1
        else:
            count[element] += 1
  
    divisors = {}
    for element in A:
        divisors[element] = set([1, element])
  
    # start the Sieve of Eratosthenes
    divisor = 2
    while divisor*divisor <= A_max:
        element_candidate = divisor
        while element_candidate  <= A_max:
            if element_candidate in divisors and not divisor in divisors[element_candidate]:
                divisors[element_candidate].add(divisor)
                divisors[element_candidate].add(element_candidate//divisor)
            element_candidate += divisor
        divisor += 1
  
    result = [0] * len(A)
    for idx, element in enumerate(A):
        result[idx] = (len(A)-sum([count.get(divisor,0) for divisor in divisors[element]]))
  
    return result
