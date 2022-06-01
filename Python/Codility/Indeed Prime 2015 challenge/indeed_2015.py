

#  LongestPassword


# You would like to set a password for a bank account. However, there are three restrictions on the format of the password:

# it has to contain only alphanumerical characters (a−z, A−Z, 0−9);
# there should be an even number of letters;
# there should be an odd number of digits.
# You are given a string S consisting of N characters. String S can be divided into words by splitting it at, and removing, the spaces. The goal is to choose the longest word that is a valid password. You can assume that if there are K spaces in string S then there are exactly K + 1 words.

# For example, given "test 5 a0A pass007 ?xy1", there are five words and three of them are valid passwords: "5", "a0A" and "pass007". Thus the longest password is "pass007" and its length is 7. Note that neither "test" nor "?xy1" is a valid password, because "?" is not an alphanumerical character and "test" contains an even number of digits (zero).

# Write a function:

# int solution(char *S);
# that, given a non-empty string S consisting of N characters, returns the length of the longest word from the string that is a valid password. If there is no such word, your function should return −1.

# For example, given S = "test 5 a0A pass007 ?xy1", your function should return 7, as explained above.

# Assume that:

# N is an integer within the range [1..200];
# string S consists only of printable ASCII characters and spaces.
# In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.





def solution(S):
    longest = -1
    num_of_letters = 0
    num_of_digits = 0
    num_of_others = 0
    
    for letter in S:
        if letter.isalpha():
            num_of_letters += 1
        elif letter.isdigit():
            num_of_digits += 1
        elif letter == " ":
            # Check whether it's a valid password.
            if num_of_others == 0 and \
               num_of_letters % 2 == 0 and \
               num_of_digits % 2 == 1:
                if longest < num_of_letters + num_of_digits:
                    longest = num_of_letters + num_of_digits
            
            # Reset the counters for the next word.
            num_of_letters = 0
            num_of_digits = 0
            num_of_others = 0
        else:
            num_of_others += 1

    # Check whether the last word is a valid password.
    if num_of_others == 0 and \
       num_of_letters % 2 == 0 and \
       num_of_digits % 2 == 1:
        if longest < num_of_letters + num_of_digits:
            longest = num_of_letters + num_of_digits


    return longest






#  FloodDepth
# You are helping a geologist friend investigate an area with mountain lakes. A recent heavy rainfall has flooded these lakes and their water levels have reached the highest possible point. Your friend is interested to know the maximum depth in the deepest part of these lakes.

# We simplify the problem in 2-D dimensions. The whole landscape can be divided into small blocks and described by an array A of length N. Each element of A is the altitude of the rock floor of a block (i.e. the height of this block when there is no water at all). After the rainfall, all the low-lying areas (i.e. blocks that have higher blocks on both sides) are holding as much water as possible. You would like to know the maximum depth of water after this entire area is flooded. You can assume that the altitude outside this area is zero and the outside area can accommodate infinite amount of water.

# For example, consider array A such that:

#     A[0] = 1
#     A[1] = 3
#     A[2] = 2
#     A[3] = 1
#     A[4] = 2
#     A[5] = 1
#     A[6] = 5
#     A[7] = 3
#     A[8] = 3
#     A[9] = 4
#     A[10] = 2
# The following picture illustrates the landscape after it has flooded:



# The gray area is the rock floor described by the array A above and the blue area with dashed lines represents the water filling the low-lying areas with maximum possible volume. Thus, blocks 3 and 5 have a water depth of 2 while blocks 2, 4, 7 and 8 have a water depth of 1. Therefore, the maximum water depth of this area is 2.

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A consisting of N integers, returns the maximum depth of water.

# Given array A shown above, the function should return 2, as explained above.

# For the following array:

#     A[0] = 5
#     A[1] = 8
# the function should return 0, because this landscape cannot hold any water.

# Assume that:

# N is an integer within the range [1..100,000];
# each element of array A is an integer within the range [1..100,000,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).




from collections import namedtuple

def solution(A):
    if len(A) < 3:
        return 0
    
    # The blocks in the stack is in strictly height descending order.
    # For the first block in the stack, its max_depth is maximum water
    # depth of its (exclusive) left area.
    # The other blocks' max_depth is the maximum water depth between its
    # previous block in the stack and itself, both exclusive.
    Block = namedtuple("Block", ["height", "max_depth"])
    stack = [Block(A[0],0)]
    
    for height in A[1:]:
        if height == stack[-1].height:
            # These two adjacent blocks have the same height. They act
            # totally the same in building any water container.
            continue
        elif height < stack[-1].height:
            stack.append(Block(height, 0))
        else:
            max_depth = 0
            
            # Let the current iterating block be C, the previous two
            # blocks in the stack be A and B. And their positions are
            # demoed as:
            #             C
            # A           C
            # A ... B ... C
            # while the blocks between A and B are omitted. So do the
            # blocks between B and C.
            #
            # The additional_depth consider the blocks A, B, and C only,
            # and igonres all the omitted blocks, such as:
            #       C
            # A     C
            # A  B  C   (no block is between A and B, or B and C)
            #
            # HOWEVER, the additional_depth is not always the maximum
            # water depth between A and C, because there may be some
            # water between A and B, or B and C, as exists in the omitted
            # blocks. We need to adjust the additional_depth to get the
            # maximum water depth between A and C, both exclusive.
            while len(stack) > 1 and height > stack[-1].height:
                additional_depth = min(stack[-2].height, height) - \
                                   stack[-1].height
                max_depth = max(max_depth, stack[-1].max_depth) + \
                            additional_depth
                stack.pop()
            
            # Combine leftward same-or-less-height blocks. These dropped
            # blocks are never going to be part of the remaining water
            # container.
            while len(stack) > 0 and height >= stack[-1].height:
                max_depth = max(max_depth, stack[-1].max_depth)
                stack.pop()
            
            stack.append(Block(height, max_depth))
    
    overall_max_depth = 0
    for block in stack:
        if block.max_depth > overall_max_depth:
            overall_max_depth = block.max_depth
    
    return overall_max_depth





#  SlalomSkiing


# You are a skier participating in a giant slalom. The slalom track is located on a ski slope, goes downhill and is fenced by barriers on both sides. The barriers are perpendicular to the starting line located at the top of the slope. There are N slalom gates on the track. Each gate is placed at a distinct distance from the starting line and from the barrier on the right-hand side (looking downhill).

# You start from any place on the starting line, ski down the track passing as many gates as possible, and finish the slalom at the bottom of the slope. Passing a gate means skiing through the position of the gate.

# You can ski downhill in either of two directions: to the left or to the right. When you ski to the left, you pass gates of increasing distances from the right barrier, and when you ski to the right, you pass gates of decreasing distances from the right barrier. You want to ski to the left at the beginning.

# Unfortunately, changing direction (left to right or vice versa) is exhausting, so you have decided to change direction at most two times during your ride. Because of this, you have allowed yourself to miss some of the gates on the way down the slope. You would like to know the maximum number of gates that you can pass with at most two changes of direction.

# The arrangement of the gates is given as an array A consisting of N integers, whose elements specify the positions of the gates: gate K (for 0 ≤ K < N) is at a distance of K+1 from the starting line, and at a distance of A[K] from the right barrier.

# For example, consider array A such that:

#   A[0] = 15
#   A[1] = 13
#   A[2] = 5
#   A[3] = 7
#   A[4] = 4
#   A[5] = 10
#   A[6] = 12
#   A[7] = 8
#   A[8] = 2
#   A[9] = 11
#   A[10] = 6
#   A[11] = 9
#   A[12] = 3


# The picture above illustrates the example track with N = 13 gates and a course that passes eight gates. After starting, you ski to the left (from your own perspective). You pass gates 2, 3, 5, 6 and then change direction to the right. After that you pass gates 7, 8 and then change direction to the left. Finally, you pass gates 10, 11 and finish the slalom. There is no possible way of passing more gates using at most two changes of direction.

# Write a function:

# int solution(int A[], int N);
# that, given a zero-indexed array A consisting of N integers, describing the positions of the gates on the track, returns the maximum number of gates that you can pass during one ski run.

# For example, given the above data, the function should return 8, as explained above.

# For the following array A consisting of N = 2 elements:

#   A[0] = 1
#   A[1] = 5
# the function should return 2.

# Assume that:

# N is an integer within the range [1..100,000];
# each element of array A is an integer within the range [1..1,000,000,000];
# the elements of A are all distinct.
# Complexity:

# expected worst-case time complexity is O(N*log(N));
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).



def LongestIncreasingSubsequence(seq):
    ''' The classic dynamic programming solution for longest increasing
        subsequence. More details could be found:
        https://en.wikipedia.org/wiki/Longest_increasing_subsequence
        http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
        http://stackoverflow.com/questions/3992697/longest-increasing-subsequence
    '''
    # smallest_end_value[i] = j means, for all i-length increasing
    # subsequence, the minmum value of their last elements is j.
    smallest_end_value = [None] * (len(seq) + 1)
    # The first element (with index 0) is a filler and never used.
    smallest_end_value[0] = -1
    # The length of the longest increasing subsequence.
    lic_length = 0

    for i in range(len(seq)):
        # Binary search: we want the index j such that:
        #     smallest_end_value[j-1] < seq[i]
        #     AND
        #     (  smallest_end_value[j] > seq[i]
        #        OR
        #        smallest_end_value[j] == None
        #     )
        # Here, the result "lower" is the index j.
        lower = 0
        upper = lic_length
        while lower <= upper:
            mid = (upper + lower) // 2
            if seq[i] < smallest_end_value[mid]:
                upper = mid - 1
            elif seq[i] > smallest_end_value[mid]:
                lower = mid + 1
            else:
                raise "Should never happen: " + \
                      "the elements of A are all distinct"
        
        if smallest_end_value[lower] == None:
            smallest_end_value[lower] = seq[i]
            lic_length += 1
        else:
            smallest_end_value[lower] = \
                min(smallest_end_value[lower], seq[i])

    return lic_length
    
def solution(A):
    # We are solving this question by creating two mirrors.
    bound = max(A) + 1
    multiverse = []
    for point in A:
        # The point in the double-mirror universe.
        multiverse.append(bound * 2 + point)
        # The point in the mirror universe.
        multiverse.append(bound * 2 - point)
        # The point in the original universe.
        multiverse.append(point)

    return LongestIncreasingSubsequence(multiverse)


