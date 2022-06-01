# MinMaxDivision
# --------------

# You are given integers K, M and a non-empty zero-indexed array A consisting of N integers. Every element of the array is not greater than M.

# You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0 and N. Every element of the array should belong to some block.

# The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.

# The large sum is the maximal sum of any block.

# For example, you are given integers K = 3, M = 5 and array A such that:

#   A[0] = 2
#   A[1] = 1
#   A[2] = 5
#   A[3] = 1
#   A[4] = 2
#   A[5] = 2
#   A[6] = 2
# The array can be divided, for example, into the following blocks:

# [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
# [2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
# [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
# [2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
# The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.

# Write a function:

# int solution(int K, int M, int A[], int N);
# that, given integers K, M and a non-empty zero-indexed array A consisting of N integers, returns the minimal large sum.

# For example, given K = 3, M = 5 and array A such that:

#   A[0] = 2
#   A[1] = 1
#   A[2] = 5
#   A[3] = 1
#   A[4] = 2
#   A[5] = 2
#   A[6] = 2
# the function should return 6, as explained above.

# Assume that:

# N and K are integers within the range [1..100,000];
# M is an integer within the range [0..10,000];
# each element of array A is an integer within the range [0..M].
# Complexity:

# expected worst-case time complexity is O(N*log(N+M));
# expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).




def blocksNo(A, maxBlock):
    # Initially set the A[0] being an individual block

    blocksNumber = 1    # The number of blocks, that A could
                        # be divided to with the restriction
                        # that, the sum of each block is less
                        # than or equal to maxBlock
    preBlockSum = A[0]

    for element in A[1:]:
        # Try to extend the previous block
        if preBlockSum + element > maxBlock:
            # Fail to extend the previous block, because
            # of the sum limitation maxBlock
            preBlockSum = element
            blocksNumber += 1
        else:
            preBlockSum += element

    return blocksNumber

def solution(K, A):
    blocksNeeded = 0    # Given the restriction on the sum of
                        # each block, how many blocks could
                        # the original A be divided to?
    resultLowerBound = max(A)
    resultUpperBound = sum(A)
    result = 0          # Minimal large sum

    # Handle two special cases
    if K == 1:      return resultUpperBound
    if K >= len(A): return resultLowerBound

    # Binary search the result
    while resultLowerBound <= resultUpperBound:
        resultMaxMid = (resultLowerBound + resultUpperBound) / 2
        blocksNeeded = blocksNo(A, resultMaxMid)
        if blocksNeeded <= K:
            # With large sum being resultMaxMid or resultMaxMid-,
            # we need blocksNeeded/blocksNeeded- blocks. While we
            # have some unused blocks (K - blocksNeeded), We could
            # try to use them to decrease the large sum.
            resultUpperBound = resultMaxMid - 1
            result = resultMaxMid
        else:
            # With large sum being resultMaxMid or resultMaxMid-,
            # we need to use more than K blocks. So resultMaxMid
            # is impossible to be our answer.
            resultLowerBound = resultMaxMid + 1

    return result



# solution-b
def blockSizeIsValid(A, max_block_cnt, max_block_size):
    block_sum = 0
    block_cnt = 0
 
    for element in A:
        if block_sum + element > max_block_size:
            block_sum = element
            block_cnt += 1
        else:
            block_sum += element
        if block_cnt >= max_block_cnt:
            return False
 
    return True
 
def binarySearch(A, max_block_cnt, using_M_will_give_you_wrong_results):
    lower_bound = max(A)
    upper_bound = sum(A)
 
    if max_block_cnt == 1:      return upper_bound
    if max_block_cnt >= len(A): return lower_bound
 
    while lower_bound <= upper_bound:
        candidate_mid = (lower_bound + upper_bound) / 2
        if blockSizeIsValid(A, max_block_cnt, candidate_mid):
            upper_bound = candidate_mid - 1
        else:
            lower_bound = candidate_mid + 1
 
    return lower_bound
 
def solution(K, M, A):
    return binarySearch(A,K,M)





# NailingPlanks
# -------------

# You are given two non-empty zero-indexed arrays A and B consisting of N integers. These arrays represent N planks. More precisely, A[K] is the start and B[K] the end of the K−th plank.

# Next, you are given a non-empty zero-indexed array C consisting of M integers. This array represents M nails. More precisely, C[I] is the position where you can hammer in the I−th nail.

# We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].

# The goal is to find the minimum number of nails that must be used until all the planks are nailed. In other words, you should find a value J such that all planks will be nailed after using only the first J nails. More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].

# For example, given arrays A, B such that:

#     A[0] = 1    B[0] = 4
#     A[1] = 4    B[1] = 5
#     A[2] = 5    B[2] = 9
#     A[3] = 8    B[3] = 10
# four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].

# Given array C such that:

#     C[0] = 4
#     C[1] = 6
#     C[2] = 7
#     C[3] = 10
#     C[4] = 2
# if we use the following nails:

# 0, then planks [1, 4] and [4, 5] will both be nailed.
# 0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
# 0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
# 0, 1, 2, 3, then all the planks will be nailed.
# Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.

# Write a function:

# int solution(int A[], int B[], int N, int C[], int M);
# that, given two non-empty zero-indexed arrays A and B consisting of N integers and a non-empty zero-indexed array C consisting of M integers, returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.

# If it is not possible to nail all the planks, the function should return −1.

# For example, given arrays A, B, C such that:

#     A[0] = 1    B[0] = 4
#     A[1] = 4    B[1] = 5
#     A[2] = 5    B[2] = 9
#     A[3] = 8    B[3] = 10

#     C[0] = 4
#     C[1] = 6
#     C[2] = 7
#     C[3] = 10
#     C[4] = 2
# the function should return 4, as explained above.

# Assume that:

# N and M are integers within the range [1..30,000];
# each element of arrays A, B, C is an integer within the range [1..2*M];
# A[K] ≤ B[K].
# Complexity:

# expected worst-case time complexity is O((N+M)*log(M));
# expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.



def _findFirstNail(plankBegin, plankEnd, nails, preResult):


    # Function: find the nails, that could nail this plank.
    #
    # Input: plankBegin: the begin position of current plank
    #        plankEnd: the end position of current plank
    #        nails: the nails' position and index
    #        preResult: for all of the previous planks, the
    #           first preResult+1 nails in original array
    #           could be sequentially used to nail them.
    #
    # Return: If all these nails are after the preResult's
    #       position, return the first nail's position in
    #       the original nails' array.
    #       Else, return the preResult as the result.

    result = -1     # The index of nail in the original array
    resultPos = -1  # The index of nail in the sorted array

    nailLower = 0
    nailUpper = len(nails) - 1
    nailMid = 0

    # Find the first nail, whose postion >= plankBegin and
    #   position <= plankEnd, with binary search algorithm
    while nailLower <= nailUpper:
        nailMid = (nailLower + nailUpper) / 2
        nailPosMid = nails[nailMid][1]
        if nailPosMid < plankBegin:
            nailLower = nailMid + 1
        elif nailPosMid > plankEnd:
            nailUpper = nailMid - 1
        else:
            nailUpper = nailMid - 1
            result = nails[nailMid][0]
            resultPos = nailMid

    # Cannot find one, which could nail the plank
    if result == -1:                        return -1

    # Linear search all the quanlified nails, and find
    # out the one with the earliest position.
    resultPos += 1
    while resultPos < len(nails):
        # Not quanlified anymore.
        if nails[resultPos][1] > plankEnd:  break
        result = min(result, nails[resultPos][0])
        resultPos += 1
        # If we find a position before the preResult. We could
        # terminate our search and return.
        # With a position before the preResult, the result for
        # this round must <= preResult. And globally, the final
        # result is the maximum of ALL the results in each rounds.
        # So the result of this round actually does not affect
        # the final result.
        if preResult >= result:             return preResult

    return max(result, preResult)

def solution(A, B, C):
    # Sort the nails according to their positions
    nails = sorted(enumerate(C), key = lambda x: x[1])
    result = -1

    for plankIndex in xrange(len(A)):
        # Find a nail for the current plank
        result = _findFirstNail(A[plankIndex], B[plankIndex], nails, result)
        if result == -1:  return -1     # Cannot find such a nail

    return result + 1




# solution-b

PLANK_START = 0
PLANK_END = 1
 
NAIL_ARR_IDX = 0
NAIL_HIT_LOCATION = 1
 
class NoNailFoundException(Exception):
    def __init__(self):
        pass
 
def findPosOfNail(nails, plank, previous_max):
    nail_idx = -1
    result = -1
 
    # logarithmic scan O(log(M))
    lower_idx = 0
    upper_idx = len(nails) - 1
 
    while lower_idx <= upper_idx:
        mid_idx = (lower_idx + upper_idx) // 2
        if nails[mid_idx][NAIL_HIT_LOCATION] < plank[PLANK_START]:
            lower_idx = mid_idx + 1
        elif nails[mid_idx][NAIL_HIT_LOCATION] > plank[PLANK_END]:
            upper_idx = mid_idx - 1
        else:
            upper_idx = mid_idx - 1
            result = nails[mid_idx][PLANK_START]
            nail_idx = mid_idx
 
    if result == -1:
        raise NoNailFoundException
 
    # linear scan O(M)
    nail_idx += 1
    while nail_idx < len(nails):
        if nails[nail_idx][NAIL_HIT_LOCATION] > plank[PLANK_END]:
            break
        result = min(result, nails[nail_idx][NAIL_ARR_IDX])
        if result <= previous_max:
            return result
        nail_idx += 1
 
    if result == -1:
        raise NoNailFoundException
 
    return result
 
def getNrNailsRequired(planks, nails):
    result = 0
    for plank in planks:
        result = max(result, findPosOfNail(nails, plank, result))
 
    return result+1
 
def solution(A, B ,C):
    planks = zip(A,B)
 
    nails = sorted(enumerate(C), key=lambda var: var[1])
 
    try:
        return getNrNailsRequired(planks, nails)
    except NoNailFoundException:
        return -1



