#  CountFactors
#  ------------


# A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.

# For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).

# Write a function:

# int solution(int N);
# that, given a positive integer N, returns the number of its factors.

# For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.

# Assume that:

# N is an integer within the range [1..2,147,483,647].
# Complexity:

# expected worst-case time complexity is O(sqrt(N));
# expected worst-case space complexity is O(1).

def solution(N):
    candidate = 1
    result = 0
    while candidate * candidate < N:
        # N has two factors: candidate and N // candidate
        if N % candidate == 0:      result += 2

        candidate += 1

    # If N is square of some value.
    if candidate * candidate == N:  result += 1

    return result


def solution1(N):
    cnt = 0
    i = 1
    while ( i * i <= N):
        if (N % i == 0):
            if i * i == N:
               cnt += 1
            else:
                cnt += 2
        i += 1
    return cnt





# MinPerimeterRectangle
# ---------------------

# An integer N is given, representing the area of some rectangle.

# The area of a rectangle whose sides are of length A and B is A * B, and the perimeter is 2 * (A + B).

# The goal is to find the minimal perimeter of any rectangle whose area equals N. The sides of this rectangle should be only integers.

# For example, given integer N = 30, rectangles of area 30 are:

# (1, 30), with a perimeter of 62,
# (2, 15), with a perimeter of 34,
# (3, 10), with a perimeter of 26,
# (5, 6), with a perimeter of 22.
# Write a function:

# int solution(int N);
# that, given an integer N, returns the minimal perimeter of any rectangle whose area is exactly equal to N.

# For example, given an integer N = 30, the function should return 22, as explained above.

# Assume that:

# N is an integer within the range [1..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(sqrt(N));
# expected worst-case space complexity is O(1).



def solution(N):
    from math import sqrt

    for i in xrange(int(sqrt(N)), 0, -1):
        if N % i == 0:
            return 2*(i+N/i)



def solution(N):

    import math
    if N <= 0:
      return 0
   
    for i in xrange(int(math.sqrt(N)), 0, -1):
        if N % i == 0:
            return 2*(i+N/i)
             
    raise Exception("should never reach here!")   


# Flags 
# -----

# A non-empty zero-indexed array A consisting of N integers is given.

# A peak is an array element which is larger than its neighbours. More precisely, it is an index P such that 0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].

# For example, the following array A:

#     A[0] = 1
#     A[1] = 5
#     A[2] = 3
#     A[3] = 4
#     A[4] = 3
#     A[5] = 4
#     A[6] = 1
#     A[7] = 2
#     A[8] = 3
#     A[9] = 4
#     A[10] = 6
#     A[11] = 2
# has exactly four peaks: elements 1, 3, 5 and 10.

# You are going on a trip to a range of mountains whose relative heights are represented by array A, as shown in a figure below. You have to choose how many flags you should take with you. The goal is to set the maximum number of flags on the peaks, according to certain rules.



# Flags can only be set on peaks. What's more, if you take K flags, then the distance between any two flags should be greater than or equal to K. The distance between indices P and Q is the absolute value |P − Q|.

# For example, given the mountain range represented by array A, above, with N = 12, if you take:

# two flags, you can set them on peaks 1 and 5;
# three flags, you can set them on peaks 1, 5 and 10;
# four flags, you can set only three flags, on peaks 1, 5 and 10.
# You can therefore set a maximum of three flags in this case.

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A of N integers, returns the maximum number of flags that can be set on the peaks of the array.

# For example, the following array A:

#     A[0] = 1
#     A[1] = 5
#     A[2] = 3
#     A[3] = 4
#     A[4] = 3
#     A[5] = 4
#     A[6] = 1
#     A[7] = 2
#     A[8] = 3
#     A[9] = 4
#     A[10] = 6
#     A[11] = 2
# the function should return 3, as explained above.

# Assume that:

# N is an integer within the range [1..400,000];
# each element of array A is an integer within the range [0..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.


def solution(A):
    from math import sqrt

    A_len = len(A)
    next_peak = [-1] * A_len
    peaks_count = 0
    first_peak = -1

    # Generate the information, where the next peak is.
    for index in xrange(A_len-2, 0, -1):
        if A[index] > A[index+1] and A[index] > A[index-1]:
            next_peak[index] = index
            peaks_count += 1
            first_peak = index
        else:
            next_peak[index] = next_peak[index+1]
    if peaks_count < 2:
        # There is no peak or only one.
        return peaks_count

    max_flags = 1
    max_min_distance = int(sqrt(A_len))
    for min_distance in xrange(max_min_distance + 1, 1, -1):
        # Try for every possible distance.
        flags_used = 1
        flags_have = min_distance-1 # Use one flag at the first peak
        pos = first_peak
        while flags_have > 0:
            if pos + min_distance >= A_len-1:
                # Reach or beyond the end of the array
                break
            pos = next_peak[pos+min_distance]
            if pos == -1:
                # No peak available afterward
                break
            flags_used += 1
            flags_have -= 1
        max_flags = max(max_flags, flags_used)

    return max_flags






#  Peaks 
#  -----


# A non-empty zero-indexed array A consisting of N integers is given.

# A peak is an array element which is larger than its neighbors. More precisely, it is an index P such that 0 < P < N − 1,  A[P − 1] < A[P] and A[P] > A[P + 1].

# For example, the following array A:

#     A[0] = 1
#     A[1] = 2
#     A[2] = 3
#     A[3] = 4
#     A[4] = 3
#     A[5] = 4
#     A[6] = 1
#     A[7] = 2
#     A[8] = 3
#     A[9] = 4
#     A[10] = 6
#     A[11] = 2
# has exactly three peaks: 3, 5, 10.

# We want to divide this array into blocks containing the same number of elements. More precisely, we want to choose a number K that will yield the following blocks:

# A[0], A[1], ..., A[K − 1],
# A[K], A[K + 1], ..., A[2K − 1],
# ...
# A[N − K], A[N − K + 1], ..., A[N − 1].
# What's more, every block should contain at least one peak. Notice that extreme elements of the blocks (for example A[K − 1] or A[K]) can also be peaks, but only if they have both neighbors (including one in an adjacent blocks).

# The goal is to find the maximum number of blocks into which the array A can be divided.

# Array A can be divided into blocks as follows:

# one block (1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2). This block contains three peaks.
# two blocks (1, 2, 3, 4, 3, 4) and (1, 2, 3, 4, 6, 2). Every block has a peak.
# three blocks (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). Every block has a peak. Notice in particular that the first block (1, 2, 3, 4) has a peak at A[3], because A[2] < A[3] > A[4], even though A[4] is in the adjacent block.
# However, array A cannot be divided into four blocks, (1, 2, 3), (4, 3, 4), (1, 2, 3) and (4, 6, 2), because the (1, 2, 3) blocks do not contain a peak. Notice in particular that the (4, 3, 4) block contains two peaks: A[3] and A[5].

# The maximum number of blocks that array A can be divided into is three.

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A consisting of N integers, returns the maximum number of blocks into which A can be divided.

# If A cannot be divided into some number of blocks, the function should return 0.

# For example, given:

#     A[0] = 1
#     A[1] = 2
#     A[2] = 3
#     A[3] = 4
#     A[4] = 3
#     A[5] = 4
#     A[6] = 1
#     A[7] = 2
#     A[8] = 3
#     A[9] = 4
#     A[10] = 6
#     A[11] = 2
# the function should return 3, as explained above.

# Assume that:

# N is an integer within the range [1..100,000];
# each element of array A is an integer within the range [0..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(N*log(log(N)));
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).



def solution(A):
    from math import sqrt

    A_len = len(A)
    peaks_until_here = [0]*A_len

    # Retrieve how many peaks exist from beginning to current
    # position.
    for index in xrange(1, A_len-1):
        peaks_until_here[index] = peaks_until_here[index-1]
        if A[index] > A[index-1] and A[index] > A[index+1]:
            peaks_until_here[index] += 1
    if A_len < 3 or peaks_until_here[-2] == 0:
        # The array is too short to have a peak. OR
        # There is no peak in this array.
        return 0
    peaks_until_here[-1] = peaks_until_here[-2]

    max_blocks = 0
    # Compute every possible partition plan, and find out the
    # one with most blocks.
    for candidate in xrange(1, int(sqrt(A_len))+1, 1):
        if A_len % candidate == 0:
            blocks, block_size = candidate, A_len//candidate

            # Check the first block.
            if peaks_until_here[0] < peaks_until_here[block_size-1]:
                # Check the following blocks.
                for each_block in xrange(block_size, A_len, block_size):
                    if peaks_until_here[each_block-1] ==
                       peaks_until_here[each_block+block_size-1]:
                        # No peak is found in the next block
                        # This partition plan is not accepted
                        break
                else:
                    max_blocks = blocks

            if candidate * candidate == A_len:
                # If candidate is equal to sqrt(A_len) exactly,
                # candidate would equal to A_len//candidate.
                continue

            block_size, blocks = candidate, A_len//candidate
            # Check the first block.
            if peaks_until_here[0] < peaks_until_here[block_size-1]:
                # Check the following blocks.
                for each_block in xrange(block_size, A_len, block_size):
                    if peaks_until_here[each_block-1] ==
                       peaks_until_here[each_block+block_size-1]:
                        # No peak is found in the next block
                        # This partition plan is not accepted
                        break
                else:
                    return blocks

    return max_blocks




def solution1(A):
    peaks = []
 
    for idx in xrange(1, len(A)-1):
        if A[idx-1] < A[idx] > A[idx+1]:
            peaks.append(idx)
 
    if len(peaks) == 0:
        return 0
 
    for size in xrange(len(peaks), 0, -1):
        if len(A) % size == 0:
            block_size = len(A) // size
            found = [False] * size
            found_cnt = 0
            for peak in peaks:
                block_nr = peak//block_size
                if found[block_nr] == False:
                    found[block_nr] = True
                    found_cnt += 1
 
            if found_cnt == size:
                return size
 
    return 0


