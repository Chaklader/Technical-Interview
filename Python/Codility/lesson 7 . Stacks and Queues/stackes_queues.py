# Fish 
# ---- 

# You are given two non-empty zero-indexed arrays A and B consisting of N integers. Arrays A and B represent N voracious fish in a river, ordered downstream along the flow of the river.

# The fish are numbered from 0 to N − 1. If P and Q are two fish and P < Q, then fish P is initially upstream of fish Q. Initially, each fish has a unique position.

# Fish number P is represented by A[P] and B[P]. Array A contains the sizes of the fish. All its elements are unique. Array B contains the directions of the fish. It contains only 0s and/or 1s, where:

# 0 represents a fish flowing upstream,
# 1 represents a fish flowing downstream.
# If two fish move in opposite directions and there are no other (living) fish between them, they will eventually meet each other. Then only one fish can stay alive − the larger fish eats the smaller one. More precisely, we say that two fish P and Q meet each other when P < Q, B[P] = 1 and B[Q] = 0, and there are no living fish between them. After they meet:

# If A[P] > A[Q] then P eats Q, and P will still be flowing downstream,
# If A[Q] > A[P] then Q eats P, and Q will still be flowing upstream.
# We assume that all the fish are flowing at the same speed. That is, fish moving in the same direction never meet. The goal is to calculate the number of fish that will stay alive.

# For example, consider arrays A and B such that:

#   A[0] = 4    B[0] = 0
#   A[1] = 3    B[1] = 1
#   A[2] = 2    B[2] = 0
#   A[3] = 1    B[3] = 0
#   A[4] = 5    B[4] = 0
# Initially all the fish are alive and all except fish number 1 are moving upstream. Fish number 1 meets fish number 2 and eats it, then it meets fish number 3 and eats it too. Finally, it meets fish number 4 and is eaten by it. The remaining two fish, number 0 and 4, never meet and therefore stay alive.

# Write a function:

# int solution(int A[], int B[], int N);
# that, given two non-empty zero-indexed arrays A and B consisting of N integers, returns the number of fish that will stay alive.

# For example, given the arrays shown above, the function should return 2, as explained above.

# Assume that:

# N is an integer within the range [1..100,000];
# each element of array A is an integer within the range [0..1,000,000,000];
# each element of array B is an integer that can have one of the following values: 0, 1;
# the elements of A are all distinct.
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).





def solution(A, B):
    alive_count = 0        # The number of fish that will stay alive
    downstream = []        # To record the fishs flowing downstream
    downstream_count = 0   # To record the number of elements in downstream

    for index in xrange(len(A)):
        # Compute for each fish
        if B[index] == 1:
            # This fish is flowing downstream. It would
            # NEVER meet the previous fishs. But possibly
            # it has to fight with the downstream fishs.
            downstream.append(A[index])
            downstream_count += 1
        else:
            # This fish is flowing upstream. It would either
            #    eat ALL the previous downstream-flow fishs,
            #    and stay alive.
            # OR
            #    be eaten by ONE of the previous downstream-
            #    flow fishs, which is bigger, and died.
            while downstream_count != 0:
                # It has to fight with each previous living
                # fish, with nearest first.
                if downstream[-1] < A[index]:
                    # Win and to continue the next fight
                    downstream_count -= 1
                    downstream.pop()
                else:
                    # Lose and die
                    break
            else:
                # This upstream-flow fish eat all the previous
                # downstream-flow fishs. Win and stay alive.
                alive_count += 1

    # Currently, all the downstream-flow fishs in stack
    # downstream will not meet with any fish. They will
    # stay alive.
    alive_count += len(downstream)
    return alive_count



def solution1(A, B):

    survivals = 0
    stack = []
     
    for idx in xrange(len(A)):
        if B[idx] == 0:
            while len(stack) != 0:
                if stack[-1] > A[idx]:
                    break
                else:
                    stack.pop()                         
            else:
                survivals += 1
        else:
            stack.append(A[idx])
             
    survivals += len(stack)
     
    return survivals



# Brackets 
# --------

# A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

# S is empty;
# S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
# S has the form "VW" where V and W are properly nested strings.
# For example, the string "{[()()]}" is properly nested but "([)()]" is not.

# Write a function:

# int solution(char *S);
# that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.

# For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.

# Assume that:

# N is an integer within the range [0..200,000];
# string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N) (not counting the storage required for input arguments).


def solution(S):
    if len(S) % 2 == 1:   
        return 0

    matched = {"]":"[", "}":"{", ")": "("}
    to_push = ["[", "{", "("]
    stack = []

    for element in S:
        if element in to_push:
            stack.append(element)
        else:
            if len(stack) == 0:
                return 0
            elif matched[element] != stack.pop():
                return 0

    if len(stack) == 0:
        return 1
    else:
        return 0



# solution-b
def isValidPair(left, right):
    if left == '(' and right == ')':
        return True
    if left == '[' and right == ']':
        return True 
    if left == '{' and right == '}':
        return True   
    return False
 
def solution(S):
    stack = []
     
    for symbol in S:
        if symbol == '[' or symbol == '{' or symbol == '(':
            stack.append(symbol)
        else:
            if len(stack) == 0:
                return 0
            last = stack.pop()
            if not isValidPair(last, symbol):
                return 0
     
    if len(stack) != 0:
        return 0
             
    return 1




#  StoneWall
# You are going to build a stone wall. The wall should be straight and N meters long, and its thickness should be constant; however, it should have different heights in different places. The height of the wall is specified by a zero-indexed array H of N positive integers. H[I] is the height of the wall from I to I+1 meters to the right of its left end. In particular, H[0] is the height of the wall's left end and H[N−1] is the height of the wall's right end.

# The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular). Your task is to compute the minimum number of blocks needed to build the wall.

# Write a function:

# int solution(int H[], int N);
# that, given a zero-indexed array H of N positive integers specifying the height of the wall, returns the minimum number of blocks needed to build it.

# For example, given array H containing N = 9 integers:

#   H[0] = 8    H[1] = 8    H[2] = 5
#   H[3] = 7    H[4] = 9    H[5] = 8
#   H[6] = 7    H[7] = 4    H[8] = 8
# the function should return 7. The figure shows one possible arrangement of seven blocks.



# Assume that:

# N is an integer within the range [1..100,000];
# each element of array H is an integer within the range [1..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
# Elements of input arrays can be modified.



def solution(H):
    stack = []
    block_count = 0    # The number of needing blocks

    for height in H:
        while len(stack) != 0 and height < stack[-1]:
            # If the height of current block is less than
            #    the previous ones, the previous ones have
            #    to end before current point. They have no
            #    chance to exist in the remaining part.
            # So the previous blocks are completely finished.
            stack.pop()
            block_count += 1

        if len(stack) == 0 or height > stack[-1]:
            # If the height of current block is greater than
            #    the previous one, a new block is needed for
            #    current position.
            stack.append(height)

        # Else (the height of current block is same as that
        #    of previous one), they should be combined to
        #    one block.

    # Some blocks with different heights are still in the stack.
    block_count += len(stack)
    return block_count


def solution1(H):

    block_cnt = 0     
    stack = []
     
    for height in H:
        # remove all blocks that are 
        # bigger than my height
        while len(stack) != 0 and stack[-1] > height:
            stack.pop()
         
        if len(stack) != 0 and stack[-1] == height:
            # we already paid for this size
            pass
        else:
            # new block is required, push it's size to the stack
            block_cnt += 1
            stack.append(height)             

    return block_cnt    




# Nesting
# -------


# A string S consisting of N characters is called properly nested if:

# S is empty;
# S has the form "(U)" where U is a properly nested string;
# S has the form "VW" where V and W are properly nested strings.
# For example, string "(()(())())" is properly nested but string "())" isn't.

# Write a function:

# int solution(char *S);
# that, given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.

# For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0, as explained above.

# Assume that:

# N is an integer within the range [0..1,000,000];
# string S consists only of the characters "(" and/or ")".
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(1) (not counting the storage required for input arguments).



# solution -a

def solution(S):
    parentheses = 0

    for element in S:
        if element == "(":
            parentheses += 1
        else:
            parentheses -= 1
            if parentheses < 0:
                return 0

    if parentheses == 0:
        return 1
    else:
        return 0



# solution -b
def solution1(S):

    leftBrackets = 0
     
    for symbol in S:
        if symbol == '(':
            leftBrackets += 1
        else:
            if leftBrackets == 0:
                return 0
            leftBrackets -= 1 
     
    if leftBrackets != 0:
        return 0     
    return 1
