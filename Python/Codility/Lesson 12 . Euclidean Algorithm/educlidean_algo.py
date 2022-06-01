# ChocolatesByNumbers
# -------------------


# Two positive integers N and M are given. Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N − 1.

# You start to eat the chocolates. After eating a chocolate you leave only a wrapper.

# You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.

# More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).

# You stop eating when you encounter an empty wrapper.

# For example, given integers N = 10 and M = 4. You will eat the following chocolates: 0, 4, 8, 2, 6.

# The goal is to count the number of chocolates that you will eat, following the above rules.

# Write a function:

# int solution(int N, int M);
# that, given two positive integers N and M, returns the number of chocolates that you will eat.

# For example, given integers N = 10 and M = 4. the function should return 5, as explained above.

# Assume that:

# N and M are integers within the range [1..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(log(N+M));
# expected worst-case space complexity is O(log(N+M)).


def gcd(a, b):
    # Get the greatest common divisor
    if (a % b == 0):
        return b
    else:
        return gcd(b, a % b)

def solution(N, M):
    lcm = N * M / gcd(N, M) # Least common multiple
    return lcm / M



#  solution-b
def gcd(p, q):
  if q == 0:
    return p
  return gcd(q, p % q)
 
def lcm(p,q):
    return p * (q / gcd(p,q))
 
def solution(N, M):
    return lcm(N,M)/M


def naive(N,M):
    eaten = [False] * N
    at = 0
    cnt = 0
    while eaten[at] != True:
        eaten[at] = True
        at = (at + M) % N
        cnt += 1
         
    return cnt



# CommonPrimeDivisors
# ------------------- 

# A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.

# A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P. For example, 2 and 5 are prime divisors of 20.

# You are given two positive integers N and M. The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.

# For example, given:

# N = 15 and M = 75, the prime divisors are the same: {3, 5};
# N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
# N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
# Write a function:

# int solution(int A[], int B[], int Z);
# that, given two non-empty zero-indexed arrays A and B of Z integers, returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.

# For example, given:

#     A[0] = 15   B[0] = 75
#     A[1] = 10   B[1] = 30
#     A[2] = 3    B[2] = 5
# the function should return 1, because only one pair (15, 75) has the same set of prime divisors.

# Assume that:

# Z is an integer within the range [1..6,000];
# each element of arrays A, B is an integer within the range [1..2,147,483,647].
# Complexity:

# expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
# expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).



def gcd(x, y):
    ''' Compute the greatest common divisor.
    '''
    if x%y == 0:
        return y;
    else:
        return gcd(y, x%y)

def removeCommonPrimeDivisors(x, y):
    ''' Remove all prime divisors of x, which also exist in y. And
        return the remaining part of x.
    '''
    while x != 1:
        gcd_value = gcd(x, y)
        if gcd_value == 1:
            # x does not contain any more
            # common prime divisors
            break
        x /= gcd_value
    return x

def hasSamePrimeDivisors(x, y):
    gcd_value = gcd(x, y)   # The gcd contains all
                            # the common prime divisors

    x = removeCommonPrimeDivisors(x, gcd_value)
    if x != 1:
        # If x and y have exactly the same common
        # prime divisors, x must be composed by
        # the prime divisors in gcd_value. So
        # after previous loop, x must be one.
        return False

    y = removeCommonPrimeDivisors(y, gcd_value)

    return y == 1

def solution(A, B):
    count = 0
    for x,y in zip(A,B):
        if hasSamePrimeDivisors(x,y):
            count += 1
    return count





# solution-b

def gcd(p, q):
  if q == 0:
    return p
  return gcd(q, p % q)
 
def hasSameFactors(p, q):
    if p == q == 0:
        return True
     
    denom = gcd(p,q)
     
    while (p != 1):
        p_gcd = gcd(p,denom)
        if p_gcd == 1:
            break
        p /= p_gcd
    else:
        while (q != 1):
            q_gcd = gcd(q,denom)
            if q_gcd == 1:
                break
            q /= q_gcd
        else:
            return True
     
    return False
 
 
def solution(A, B):
    if len(A) != len(B):
        raise Exception("Invalid input")
    cnt = 0
    for idx in xrange(len(A)):
        if A[idx] < 0 or B[idx] < 0:
            raise Exception("Invalid value")
        if hasSameFactors(A[idx], B[idx]):
            cnt += 1
     
    return cnt



