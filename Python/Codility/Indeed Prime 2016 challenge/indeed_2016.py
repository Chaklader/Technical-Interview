
#  DwarfsRafting

# A company of dwarfs is travelling across the New Zealand. On reaching the Clutha River the dwarfs need to get across, but recent storms have washed away the bridge. Luckily, a small ferry, in the form of a square raft, is operating.

# The raft is square and has N rows of seats, numbered from 1 to N. Each row contains N seats, labeled with consecutive letters (A, B, C, etc.). Each seat is identified by a string composed of its row number followed by its column number; for example, "9C" denotes the third seat in the 9th row.

# The raft has already been loaded with barrels in some seat positions, and other seats are already occupied by dwarfs. Our company of dwarfs may only take the remaining unoccupied seats. The ferryman wants to accommodate as many dwarfs as possible, but the raft needs to be stable when making the crossing. That is, the following conditions must be satisfied:

# the front and back halves of the raft (in terms of the rows of seats) must each contain the same number of dwarfs;
# similarly, the left and right sides of the raft (in terms of the columns of seats) must each contain the same number of dwarfs.
# You do not have to worry about balancing the barrels; you can assume that their weights are negligible.

# For example, a raft of size N = 4 is shown in the following illustration:



# Barrels are marked as brown squares, and seats that are already occupied by dwarfs are labeled d.

# The positions of the barrels are given in string S. The occupied seat numbers are given in string T. The contents of the strings are separated by single spaces and may appear in any order. For example, in the diagram above, S = "1B 1C 4B 1D 2A" and T = "3B 2D".

# In this example, the ferryman can accommodate at most six more dwarfs, as indicated by the green squares in the following diagram:



# The raft is then balanced: both left and right halves have the same number of dwarfs (four), and both front and back halves have the same number of dwarfs (also four).

# Write a function:

# int solution(int N, char *S, char *T);
# that, given the size of the raft N and two strings S, T that describes the positions of barrels and occupied seats, respectively, returns the maximum number of dwarfs that can fit on the raft. If it is not possible to balance the raft with dwarfs, your function should return -1.

# For instance, given N = 4, S = "1B 1C 4B 1D 2A" and T = "3B 2D", your function should return 6, as explained above.

# Assume that:

# N is an even integer within the range [2..26];
# strings S, T consist of valid seat numbers, separated with spaces;
# each seat number can appear no more than once in the strings;
# no seat number can appear in both S and T simultaneously.
# In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.

def solution(N, S, T):
    quadrant_left_front = (N // 2) * (N // 2)
    quadrant_left_back = (N // 2) * (N // 2)
    quadrant_right_front = (N // 2) * (N // 2)
    quadrant_right_back = (N // 2) * (N // 2)
    boundary = N // 2
    
    # Compute how many slots are available in each quadrant.
    for barrel in S.split():
        # Adjust to 0-based index.
        row = int(barrel[:-1]) - 1
        column = ord(barrel[-1]) - ord("A")
        
        if row < boundary:
            # The barrel is in the front.
            if column < boundary:
                # The barrel is in the left.
                quadrant_left_front -= 1
            else:
                # The barrel is in the right.
                quadrant_right_front -= 1
        else:
            # The barrel is in the back.
            if column < boundary:
                # The barrel is in the left.
                quadrant_left_back -= 1
            else:
                # The barrel is in the right.
                quadrant_right_back -= 1
    
    # lf is short for left front, etc.
    # To keep balance, we need:
    #   1. weight_lf + weight_lb = weight_rf + weight_rb
    #   2. weight_lf + weight_rf = weight_rf + weight_rb
    # Solve the equations and we can get the answer:
    #   1. weight_lf = weight_rb
    #   2. And weight_rf = weight_lb
    allowance_lf = min(quadrant_left_front, quadrant_right_back)
    allowance_rb = min(quadrant_left_front, quadrant_right_back)
    allowance_lb = min(quadrant_left_back, quadrant_right_front)
    allowance_rf = min(quadrant_left_back, quadrant_right_front)
    
    # Minus the seats, which are already occupied by dwarfs.
    for dwarf in T.split():
        # Adjust to 0-based index.
        row = int(dwarf[:-1]) - 1
        column = ord(dwarf[-1]) - ord("A")

        if row < boundary:
            # The dwarf is in the front.
            if column < boundary:
                # The dwarf is in the left.
                allowance_lf -= 1
                if allowance_lf < 0:    return -1
            else:
                # The dwarf is in the right.
                allowance_rf -= 1
                if allowance_rf < 0:    return -1
        else:
            # The dwarf is in the back.
            if column < boundary:
                # The dwarf is in the left.
                allowance_lb -= 1
                if allowance_lb < 0:    return -1
            else:
                # The dwarf is in the right.
                allowance_rb -= 1
                if allowance_rb < 0:    return -1
    
    return allowance_lf + allowance_rb + allowance_lb + allowance_rf






#  RectangleBuilderGreaterArea
#  ---------------------------





#  HilbertMaze
#  -----------







#  TreeProduct
#  -----------
