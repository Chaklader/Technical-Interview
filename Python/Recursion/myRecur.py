import functools
import re
import unittest
import collections
import itertools
import math
import random
import string



#  general methods 
def memoize(f):

    """ Minimalistic memoization decorator (*args / **kwargs)
    Based on: http://code.activestate.com/recipes/577219/ """

    cache = {}
    @functools.wraps(f)
    def memf(*args, **kwargs):
<<<<<<< HEAD

        fkwargs = frozenset(kwargs.items())

        if (args, fkwargs) not in cache:
            cache[args, fkwargs] = f(*args, **kwargs)

        return cache[args, fkwargs]

=======
        fkwargs = frozenset(kwargs.items())
        if (args, fkwargs) not in cache:
            cache[args, fkwargs] = f(*args, **kwargs)
        return cache[args, fkwargs]
>>>>>>> c586b42b63e538829554061f2141f5789b21adcc
    return memf

#  general methods 





# 9.1. A child is running up a staircase with n steps, and can hop either 1
# step, 2 steps, or 3 steps at a time. Implement a method to count how many
# possible ways the child can run up the stairs.

@memoize
def nways(steps, max_steps = 3):
    """ Return in how many possible ways we can run up the stairs """

    # At any given time we can hop up to 'max_steps' steps up the stairs: for
    # each possibility we need to compute the different number of ways in which
    # the remaining steps can be walked. The number of steps that we hop cannot
    # be greater than the number of remaining steps in the staircase (i.e., if
    # there are only two steps left we cannot hop three). Stop when the number
    # of remaining steps is zero: it means that we have reached the top.

    if not steps:
        return 1
<<<<<<< HEAD

    elif steps < 0:
        return 0

=======
    elif steps < 0:
        return 0
>>>>>>> c586b42b63e538829554061f2141f5789b21adcc
    else:
        total = 0
        for n in xrange(1, min(steps, max_steps) + 1):
            total += nways(steps - n, max_steps = max_steps)
        return total
# 9-1









# 9.2. Imagine a robot sitting on the upper left corner of an X by Y grid. The
# robot can only move in two directions: right and down. How many possible
# paths are there for the robot to go from (0, 0) to (X, Y)?
#
# FOLLOW UP
#
# Imagine certain spots are "off limits", such that the robot cannot step on
# them. Design an algorithm to find a path for the robot from the top left to
# the bottom right.


class Grid(object):

    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.forbidden_coords = set()

    def mark_as_forbidden(self, row, column):
        """ Mark (x, y) as off-limits """
        self.forbidden_coords.add((row, column))

    def is_allowed(self, row, column):
        """ Check whether (row, column) is a valid spot.

        Return True if (row, column) is inside the bounds of the grid and has
        not been marked as off-limits via the Grid.mark_as_forbidden() method.

        """

        return (0 <= row    < self.x and
                0 <= column < self.y and
                (row, column) not in self.forbidden_coords)

    @memoize
    def find_path(self, row = 0, column = 0):
        """ Return the path from (row, column) to (x - 1, y - 1) """

        # Finding a path from (x, y) to the destination is the same as moving
        # to its two neighbors, (x - 1, y) and (x, y - 1), and finding the path
        # from there: we keep doing this until a path reaches the destination.
        # Always try the right neighbor first, then, if it does not gives us a
        # valid solution, the bottom one. If the destination cannot be reached,
        # we backtrack returning None.

        destination = (self.x - 1, self.y - 1)
        current = [(row, column)]

        if (row, column) == destination:
            return current

        neighbors = []
        neighbors.append((row, column + 1))
        neighbors.append((row + 1, column))

        for n in neighbors:
            if self.is_allowed(*n):
                path = self.find_path(*n)
                # Subpath reaches bottom right
                if path and path[-1] == destination:
                    return current + path

# 9-2







# 9.3. A magic index in an array A[0...n-1] is defined to be an index such that
# A[i] = i. Given a sorted array of distinct integers, write a method to find a
# magic index, if one exists, in array A.
#
# FOLLOW UP
#
# What if the values are not distinct?


#  solution - a
def magic_index(seq, start = None, end = None):
    """ Return the magic index, or -1 if it does not exist. """

    if start is None:
        start = 0

    if end is None:
        end = len(seq) - 1

    if start > end:
        return -1

    # This is very similar to the binary search problem: we check whether the
    # middle index is a magic one, and if it is not we repeat the same action
    # on the sub-array to its left or to its right. Which side? It depends on
    # the value on the index and its corresponding element:
    #
    # The fact that the elements of the sequence are distinct integers allows
    # us to draw a conclusion if index > seq[index]: the magic index, in case
    # it exists, cannot be on the left side. The index decreases one by one and
    # the (unique) elements by at least one, so (index - n) is guaranteed to be
    # larger than seq[index - n]. In other words: if we reach a point where the
    # index is larger than the corresponding element, we can only hope that the
    # element eventually catches up, since the index increases one by one while
    # the elements can make larger leaps, so to speak.

    index = (start + end) // 2
    if index == seq[index]:
        return index

    if index > seq[index]:
        return magic_index(seq, start=index+1, end=end)
    else:
        return magic_index(seq, start=start, end=index-1)

# END of solution -a



# solution -b

def magic_index_duplicates(seq, start = None, end = None):
    """ Return the magic index, or -1 if it does not exist. """

    if start is None:
        start = 0

    if end is None:
        end = len(seq) - 1

    if start > end:
        return -1

    # If there can be duplicate elements, we cannot determine on which side,
    # left or right, the magic index is. Even if index > seq[index], the same
    # element could be repeated many times, allowing the index to decrease one
    # by one, as we moved to the left, until it eventually caught up with the
    # corresponding element. And the magic index could be on the right, as the
    # index increases one by one while the elements can make larger leaps. We
    # must look, therefore, on both sides. We will start on the left, trying
    # the right side only if the magic index is not found there.
    #
    # But there is a small optimization that we can do here. The first index
    # that, to the right, could be a magic one is seq[index] (as the array is
    # sorted), so we can skip all the elements in positions between index and
    # seq[index] if the latter happens to be larger than the former. In the
    # same way, in case seq[index] is smaller than index we can ignore those
    # elements whose position is larger than seq[index].

    index = (start + end) // 2
    value = seq[index]

    if index == value:
        return index

    # Left sub-array
    left_end = min(index - 1, value)
    left_index = magic_index_duplicates(seq, start=start, end=left_end)
    if left_index != -1:
        return left_index

    # Right sub-array
    right_start = max(index + 1, value)
    return magic_index_duplicates(seq, start=right_start, end=end)
#  END of solution -b
#  9-3






# 9.4. Write a method to return all subsets of a set.
def subsets(seq):
    """ Return a generator of all the subsets. """

    # The subsets of S ∪ {e} are all the subsets of S, P(S), plus P(S) again
    # but where element e has been added. For example, and assuming that the
    # scalar product notation, n * P(S), means "add n to each of the subsets
    # of P(S)":
    #
    # P({})   = {∅}
    # P({a})  = {{∅}, {a}} = P({}) + (a * P({}))
    # P({ab}) = {{∅}, {a}, {b}, {ab}} = P(a) + (b * P{a})
    #
    # This can be generalized to recursively generate all the subsets of S:
    # take a random element of the set, e, and generate P(S - e). Then, for
    # each of these subsets, we generate an additional one with e added. Do
    # this until the base case, an empty set, is reached.

    set_ = list(seq)
    if len(set_) < 1:
        yield set_
        return

    element = [set_.pop()]
    for sub in subsets(set_):
        yield sub
        yield sub + element
# 9-4







# 9.5. Write a method to compute all permutations of a string.

def permutations(str_):
    """ Return a generator of all the permutations of the string. """

    # A string such as 'a' has itself as its only permutation, while for 'ab'
    # the permutations are 'ab' and 'ba'. For 'abc', a three-character string,
    # there are six permutations: 'abc', 'acb', 'bac', 'bca', 'cab', 'cba'. Two
    # of these permutations, 'abc' and 'acb', start with 'a', and what follows
    # this first character is, respectively, 'bc' and 'cb': the two possible
    # permutations of 'cb'. This allows us to see the pattern here: for each
    # letter in the string we need to concatenate to it the sub-permutations
    # that can be generated when the letter is removed from the string.

    if len(str_) == 1:
        yield str_

    for index, letter in enumerate(str_):
        # The string without the index-th letter
        without = str_[:index] + str_[index + 1:]
        for sub_permutation in permutations(without):
            yield letter + sub_permutation
# 9-5







# 9.6. Implement an algorithm to print all valid (e.g., properly opened and
# closed) combinations of n-pairs of parentheses.
#
# EXAMPLE
# Input: 3
# Output: ((())), (()()), (())(), ()(()), ()()()


def parentheses(n):
    """ Return a generator of all the combinations of n-pairs of parentheses. """

    # For a single pair of parentheses there is only one possible combination,
    # P(1) = "()", while P(2) = "(())" and "()()". For three pairs there are
    # five different answers: "((()))", "(()())", "(())()", "()(())", "()()()".
    # As the combinations of parentheses must be valid, there are only three
    # ways in which we can generate P(n) from P(n-1): the additional pair of
    # parentheses can be (a) used to surround P(n-1), (b) prepended to P(n-1)
    # or (c) appended to P(n-1).

    if n == 1:
<<<<<<< HEAD

        yield "()"
        return


    for p in parentheses(n - 1):

=======
        yield "()"
        return

    for p in parentheses(n - 1):
>>>>>>> c586b42b63e538829554061f2141f5789b21adcc
        p1 = "({0})".format(p)
        p2 = "(){0}".format(p)
        p3 = "{0}()".format(p)

        # If p is a sequence of open-closed parentheses, such as '()()', p2 and
        # p3 are the same string, as we prepend and append an additional '()'
        # to it, respectively. Yield only one of them to avoid duplicates.

        yield p1
        yield p2
<<<<<<< HEAD
        
=======
>>>>>>> c586b42b63e538829554061f2141f5789b21adcc
        if p3 != p2:
            yield p3
# 9-6







# 9.7. Implement the "paint fill" function that one might see on many image
# editing programs. That is, given a screen (represented by a two-dimensional
# array of colors), a point, and a new color, fill in the surrounding area
# until the color changes from the original color.

class Screen(object):

    def __init__(self, nrows, ncolumns):
        """ Instantiate a Screen, filled with zeros. """

        self.pixels = [[0] * ncolumns for _ in xrange(nrows)]
        self.xsize = nrows
        self.ysize = ncolumns

    @classmethod
    def make(cls, pixels):
        """ Return a new Screen object, initialized with 'pixels'. """

        xsize = len(pixels)
        ysize = len(pixels[0])
        s = cls(xsize, ysize)
        s.pixels = pixels
        return s

    def __getitem__(self, x):
        """ Return the x-th row. """
        return self.pixels[x]

    def __str__(self):
        """ The string representation, one row per line. """
        return '\n'.join(str(row) for row in self.pixels)

    def is_inside(self, x, y):
        """ Check whether the pixel (x, y) falls within the Screen. """
        return (0 <= x < self.xsize) and (0 <= y < self.ysize)

    def fill(self, x, y, new_color):
        """ Set (x, y) and fill the surrounding area until the color changes. """

        # This algorithm does exactly what we would do if we were painting the
        # area by hand. First, we set the new value of the initial pixel, and
        # then move to the neighbors (left, right, top and bottom) that have
        # the same color. This process is repeated until there are no neighbors
        # left that need to be modified, either because they have a different
        # color or because they fall outside of the screen boundaries.

        if not self.is_inside(x, y):
            return

        # Set new color, remember the old one
        old_color, self[x][y] = self[x][y], new_color

        neighbors = [(x + 1, y), # right
                     (x - 1, y), # left
                     (x, y + 1), # bottom
                     (x, y - 1)] # top

        for nx, ny in neighbors:
            if self.is_inside(nx, ny) and self[nx][ny] == old_color:
                self.fill(nx, ny, new_color)
# 9-7








# 9.8. Given an infinite number of quarters (25 cents), dimes (10 cents),
# nickels (5 cents) and pennies (1 cent), write code to calculate the
# number of ways of representing n cents.

@memoize
def make_change(n, coin=None):
    """ Return the number of ways of representing n cents. """

    # For any given quantity, say n, we can make change using any number of
    # quarters, starting from zero and up to a number whose total does not
    # exceed n (e.g., for 73 cents we could use zero, one or two quarters, but
    # not three). Then, for each of these possibilities, we can use any number
    # of dimes to make change for the remaining quantity, and then nickels. Do
    # this recursively until all there is left are pennies (the base case):
    # when this happens, they are the only way in which we can make change.

    if not n:
        return 1

    coins = (1, 5, 10, 25)

    if not coin:
        # Highest coin we can use for this amount
        coin  = max(c for c in coins if c <= n)

    # There is just one way to make change with pennies
    if coin == 1:
        return 1

    # Next denomination coin (e.g., dime to nickel)
    next_ = coins[coins.index(coin) - 1]

    total = 0
    max_coins = n // coin
    for ncoins in xrange(max_coins + 1):
        remaining = n - (ncoins * coin)
        total += make_change(remaining, coin=next_)
    return total
# 9-8











# 9.9. Write an algorithm to print all ways of arranging eight queens on an 8x8
# chess board so that none of them share the same row, column or diagonal. In
# this case, "diagonal" means all diagonals, not just the two that bisect the
# board.

class ChessBoard(object):

    def __init__(self, n):
        """ Instantiate a n x n chessboard. """

        # Map each row to the column where a queen is placed
        self.queens = dict()
        self.size = n

    def __str__(self):
        """ The string representation of the ChessBoard.

        Example output:

        | | | | | | | |Q|
        | |Q| | | | | | |
        | | | | |Q| | | |
        | | |Q| | | | | |
        |Q| | | | | | | |
        | | | | | | |Q| |
        | | | |Q| | | | |
        | | | | | |Q| | |

        """

        squares = [[' '] * self.size for _ in xrange(self.size)]
        for x, y in self.queens.iteritems():
            squares[x][y] = 'Q'
        rows = ('|'.join(r) for r in squares)
        return '\n'.join('|{0}|'.format(r) for r in rows)

    def place(self, row, column):
        """ Place a queen on square (row, column). """
        self.queens[row] = column

    def is_free(self, row, column):
        """ Check whether (row, column) is a valid square for a new queen.

        Returns False in case there already is a queen placed on the same row,
        column or diagonal; True otherwise. In other words: this method tells
        us if a new queen can be placed on the board without being threatened
        by one of the existing queens.

        """

        # Rows and columns of already-placed queens
        for x, y in self.queens.items():

            if x == row:
                return False

            if y == column:
                return False

            # Two queens are in the same diagonal if the distance between
            # the rows is the same as the distance between the columns.
            if abs(row - x) == abs(column - y):
                return False

        return True

    @classmethod
    def queens_nways(cls, n, board=None, row=0, remaining=None):
        """ Return the number of solutions to the n-queens problem. """

        # Each row and column will be used only once, as otherwise two queens
        # would threaten each other. Therefore, for each row there are only n
        # possibilities, one for each of the n columns where a queen could be
        # placed. This approach allows us to tackle this problem recursively:
        # once a queen has been placed in a valid (non-threatened square) row,
        # we move to the next one and solve the problem of placing n-1 queens
        # on the same board, until each row contains a queen (i.e., until all
        # the queens have been placed).

        if not board:
            board = cls(n)

        if remaining is None:
            remaining = board.size

        # All queens placed
        if not remaining:
            return 1

        total = 0
        for column in xrange(board.size):
            if board.is_free(row, column):
                board_copy = copy.deepcopy(board)
                board_copy.place(row, column)
                total += cls.queens_nways(n, board_copy, row + 1, remaining - 1)
        return total
# 9-9








# 9.10. You have a stack of n boxes, with widths w_{i}, heights h_{i} and
# depths d_{i}. The boxes cannot be rotated and can only be stacked on top of
# one another if each box in the stack is strictily larger than the box above
# it in width, height, and depth. Implement a method to build the tallest stack
# possible, where the height of a stack is the sum of the heights of each box.



# Use total_ordering() with a namedtuple. We need to use a mixin because
# total_ordering() only defines the rich comparison ordering methods that
# are missing from the class, but a namedtuple implements them all. See:
# https://stackoverflow.com/a/12614638/184363

@functools.total_ordering
class _Box(object):

    def __lt__(self, other):
        """ Return whether 'self' can be stacked on top of 'other'. """

        return (self.width  < other.width and
                self.height < other.height and
                self.depth  < other.depth)

_BoxTuple = collections.namedtuple('_BoxTuple', "width height depth")
class Box(_Box, _BoxTuple):
    pass

class Stack(frozenset):
    """ An immutable (and hashable) set of Boxes. """

    def stackable_on(self, bottom):
        """ Return a Stack with the Boxes that can be stacked on 'bottom'. """
        return Stack(box for box in self if box < bottom)

    def subtract(self, box):
        """ Return a new Stack with 'box' removed. """
        return Stack(b for b in self if b != box)

    @staticmethod
    def height(seq):
        """ Return the height of a valid stack of Boxes.

        The Boxes must be given in a sorted sequence, where the first element
        is the bottom of the stack and the last one its top. In other words:
        the box at index i must be strictly larger in width, height and depth
        than the box at index i + 1. AssertionError is raised otherwise.

        """

        if __debug__:
            for index in xrange(0, len(seq) - 1):
                assert seq[index] > seq[index + 1]

        return sum(box.height for box in seq)

    @memoize
    def find_tallest(self):
        """ Return a sorted list with the Boxes that build the highest Stack. """

        # In order to build the highest stack, we need to try each of the boxes
        # as a possible bottom and find the height of the tallest stack than we
        # can obtain with it. This height is equal to the height of the bottom
        # box plus the height of the highest substack -- a recursive algorithm.
        # These substacks can only be built using the boxes that are strictly
        # smaller than the bottom.

        if not self:
            return []

        # Map each box to the highest substack we can build with it as bottom
        substacks = dict()
        for box in self:
            stack_ = self.subtract(box)
            stackable = stack_.stackable_on(box)
            substacks[box] = stackable.find_tallest()

        # Find the box for which the total height (its own height plus that
        # of the tallest substack) is the highest, and use it as the bottom.
        total_height = lambda x: x[0].height + Stack.height(x[1])
        bottom, stack_ = max(substacks.iteritems(), key=total_height)
        return [bottom] + stack_
# 9-10









# 9-11: Given a boolean expression consisting of the symbols 0, 1, &, |, and ^, and
# a desired boolean result value 'result', implement a function to count the
# number of ways of parenthesizing the expression such that it evalues to
# 'result'.
#
# EXAMPLE
# Expression: 1^0|0|1
# Desired result: false (0)
# Output: 2 ways. 1^((0|0)|1) and 1^(0|(0|1))

def get_operator_indexes(str_):
    """ Return a generator of the indexes where operators can be found.

    The returned generator yields, one by one, the indexes of 'str_' where
    there is an operator: either the character '&', '|', or '^'. For example,
    '1^0&0' yields 1 first and then 3, as '^' is the second character of the
    expression and '&' the fourth.

    """

    pattern = "&|\||\^"
    for match in re.finditer(pattern, str_):
        yield match.start()

# A two-level dictionary: map each logical operator (AND, OR, XOR) to the
# output (True or False) to a list of two-element tuples with the inputs that
# yield that result. For example, AND is only True when both inputs are True.

LOGICAL_OPS = collections.defaultdict(dict)
LOGICAL_OPS['&'][True ] = [(True, True)]
LOGICAL_OPS['&'][False] = [(False, False), (True, False), (False, True)]
LOGICAL_OPS['|'][True ] = [(True, True), (True, False), (False, True)]
LOGICAL_OPS['|'][False] = [(False, False)]
LOGICAL_OPS['^'][True ] = [(True,  False), (False, True)]
LOGICAL_OPS['^'][False] = [(True,  True), (False, False)]

@memoize
def parenthesize_count(expression, result):
    """ Return the number of ways of parenthesizing a boolean expression.

    This function counts the number of ways in which the boolean expression
    (given as a string consisting of the symbols 0, 1, &, |, and ^) can be
    parenthesized so that it evaluates to 'result' (either True or False).

    """

    # If the boolean expression has a single symbol, it will be either a one or
    # a zero. This is the base case: '1' is the only way of obtaining a True,
    # and '0' the only one of getting False. In other words: if, and only if,
    # the truth value of the symbol matches the result we are aiming for, we
    # have found a valid solution.

    if len(expression) == 1:
        value = int(expression)
        return int(bool(value) == result)

    # In order to parenthesize the boolean expression, we iterate through its
    # operators, parenthesizing what is to their left and to their right. For
    # example, '0|1&0' can be parenthesized as either '0|(1&0)' or '(0|1)&0'.
    # Now, for each of these inner expressions, the number of ways in which
    # we can obtain the desired boolean result depends on the operator: '&'
    # (AND), '|' (OR) or '^' (XOR). Therefore, we must break down each inner
    # expression, taking into account the combinations that yield the result
    # we are after. For example, if the operator is '&' and 'result' is True,
    # the only valid ways of parenthesizing the two expressions are those for
    # which they both evaluate to True.

    total = 0

    for index in get_operator_indexes(expression):
        left = expression[:index]
        operator_ = expression[index]
        right = expression[index+1:]

        for result_left, result_right in LOGICAL_OPS[operator_][result]:
            total += parenthesize_count(left,  result_left ) * \
                     parenthesize_count(right, result_right)

    return total
# 9-11






#  TEST CASES FOR THE PROGRAMS 
#  ---------------------------

class NwaysTest(unittest.TestCase):


    # 9-1
    def test_nways(self):
        self.assertEqual(nways(1), 1)
        self.assertEqual(nways(2), 2) # (1 + 1) or (2)
        self.assertEqual(nways(3), 4) # (1 + 1) or (2 + 1) or (1 + 2) or (3)
        self.assertEqual(nways(4), 7)
        self.assertEqual(nways(5), 13)
        self.assertEqual(nways(6), 24)
        self.assertEqual(nways(7), 44)
        self.assertEqual(nways(8), 81)
        self.assertEqual(nways(9), 149)
        self.assertEqual(nways(10), 274)
        self.assertEqual(nways(11), 504)
        self.assertEqual(nways(12), 927)
        self.assertEqual(nways(13), 1705)
        self.assertEqual(nways(14), 3136)
        self.assertEqual(nways(15), 5768)
        self.assertEqual(nways(16), 10609)
        self.assertEqual(nways(17), 19513)
        self.assertEqual(nways(18), 35890)
        self.assertEqual(nways(19), 66012)
        self.assertEqual(nways(20), 121415)
        self.assertEqual(nways(21), 223317)
        self.assertEqual(nways(22), 410744)
        self.assertEqual(nways(23), 755476)
        self.assertEqual(nways(24), 1389537)
        self.assertEqual(nways(25), 2555757)
        self.assertEqual(nways(26), 4700770)
        self.assertEqual(nways(27), 8646064)
        self.assertEqual(nways(28), 15902591)
        self.assertEqual(nways(29), 29249425)
        self.assertEqual(nways(30), 53798080)

    # 9-1






    # 9-2
    def test_find_path_simple_case(self):

        # 1 1
        # 1 1

        grid = Grid(2, 2)
        path = grid.find_path()
        expected = [(0, 0), (0, 1), (1, 1)]
        self.assertEqual(path, expected)

    def test_find_path_offlimits_spots(self):

        # 1 0
        # 1 1

        grid = Grid(2, 2)
        grid.mark_as_forbidden(0,1)
        path = grid.find_path()
        expected = [(0, 0), (1, 0), (1, 1)]
        self.assertEqual(path, expected)

        # 1 0 1
        # 1 1 1
        # 1 1 1

        grid = Grid(3, 3)
        grid.mark_as_forbidden(0,1)
        path = grid.find_path()
        expected = [(0, 0), (1, 0), (1, 1), (1, 2), (2, 2)]
        self.assertEqual(path, expected)

    def test_find_path_unreachable_destination(self):

        # 1 0
        # 0 1

        grid = Grid(2, 2)
        grid.mark_as_forbidden(0,1)
        grid.mark_as_forbidden(1,0)
        path = grid.find_path()
        self.assertEqual(path, None)

    def test_find_path_dead_end(self):

        # 1 1 0 0 0
        # 0 1 1 1 0 <-- dead-end corridor
        # 0 1 0 0 0
        # 0 1 1 1 1

        grid = Grid(4, 5)
        offlimits =  [(0, 2), (0, 3), (0, 4), (1, 0), (1, 4),
                      (2, 0), (2, 2), (2, 3), (2, 4), (3, 0)]

        for coords in offlimits:
            grid.mark_as_forbidden(*coords)

        path = grid.find_path()
        expected = [(0, 0), (0, 1), (1, 1), (2, 1), (3, 1), (3, 2), (3, 3), (3, 4)]
        self.assertEqual(path, expected)

    # 9-2






    # 9-3

    # solution -a
    def test_magic_index(self):

        f = magic_index
        self.assertEqual( 1, f([0, 1, 2]))
        self.assertEqual( 1, f([-2, 1, 3]))
        self.assertEqual(-1, f([-2, 0, 1]))
        self.assertEqual(-1, f([-2, -1, 1]))
        self.assertEqual(-1, f([-3, -1, 1]))
        self.assertEqual(-1, f([-3, -2, -1]))
        self.assertEqual( 3, f([-4, 0, 1, 3]))
        self.assertEqual( 1, f([-1, 1, 2, 4]))
        self.assertEqual(-1, f([-1, 0, 1, 4]))
        self.assertEqual(-1, f([-2, -1, 0, 4]))
        self.assertEqual(-1, f([-3, -1, 0, 2]))
        self.assertEqual(-1, f([1, 2, 3, 5, 6]))
        self.assertEqual( 2, f([-5, 1, 2, 3, 5]))
        self.assertEqual( 3, f([-4, -3, 0, 3, 5]))
        self.assertEqual(-1, f([-5, -4, -1, 1, 2]))
        self.assertEqual( 4, f([-3, 0, 1, 2, 4, 6]))
        self.assertEqual(-1, f([-3, -2, -1, 0, 1, 4]))
        self.assertEqual(-1, f([-4, -3, -2, 2, 3, 4]))
        self.assertEqual(-1, f([-3, -2, 1, 4, 5, 6, 7]))
        self.assertEqual(-1, f([-7, -2, 3, 5, 6, 7, 8]))
        self.assertEqual(-1, f([-6, -5, -1, 2, 3, 4, 5]))
        self.assertEqual(-1, f([-5, -3, -1, 1, 6, 7, 8]))
        self.assertEqual(-1, f([-3, -2, -1, 1, 3, 4, 5]))
        self.assertEqual(-1, f([-5, -4, -3, 4, 5, 7, 8, 9]))
        self.assertEqual(-1, f([-7, -3, -2, -1, 1, 6, 7, 8]))
        self.assertEqual(-1, f([-7, -6, 0, 1, 3, 4, 5, 6, 7]))
        self.assertEqual( 5, f([-5, -4, -3, -2, 2, 5, 7, 8, 9]))
        self.assertEqual(-1, f([-7, -6, -5, -4, 2, 3, 4, 5, 6]))
        self.assertEqual( 5, f([-7, -6, -4, -1, 1, 5, 7, 8, 9, 10]))
        self.assertEqual(10, f([-9, -7, -4, 0, 1, 2, 3, 6, 7, 8, 10]))
        self.assertEqual( 8, f([-10, -9, -8, -7, -6, -5, 1, 2, 8, 10]))
        self.assertEqual(-1, f([-10, -9, -8, -7, -5, -1, 0, 1, 5, 6, 9]))
        self.assertEqual(-1, f([-11, -10, -9, -8, -7, -5, -1, 0, 1, 2, 8]))
        self.assertEqual(-1, f([-11, -7, -6, -5, -2, -1, 8, 9, 10, 11, 12]))
        self.assertEqual(-1, f([-12, -9, -4, -3, -2, 1, 2, 3, 4, 7, 8, 12]))
        self.assertEqual( 8, f([-8, -7, -5, -4, -3, -1, 0, 7, 8, 9, 10, 11]))
        self.assertEqual(11, f([-8, -7, -2, -1, 2, 3, 5, 6, 7, 8, 9, 11, 12]))
        self.assertEqual(10, f([-11, -7, -6, -4, 0, 1, 2, 6, 7, 8, 10, 12, 13]))
        self.assertEqual(12, f([-12, -11, -10, -9, -8, -7, 1, 2, 3, 4, 7, 9, 12]))
        self.assertEqual( 6, f([-8, -7, -3, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]))
        self.assertEqual( 9, f([-10, -6, -4, -3, -2, -1, 2, 4, 8, 9, 10, 11, 12]))
        self.assertEqual(11, f([-12, -11, -6, -5, -4, -3, -2, 0, 1, 3, 5, 11, 12]))
        self.assertEqual(11, f([-12, -11, -10, -9, -8, -7, -1, 0, 4, 5, 10, 11, 13]))
        self.assertEqual(12, f([-13, -12, -8, -6, -5, -4, 2, 3, 4, 6, 7, 8, 12, 14]))
        self.assertEqual(-1, f([-10, -8, -7, -4, -3, -1, 0, 1, 2, 3, 4, 5, 6, 9, 12]))
        self.assertEqual(-1, f([-8, -7, -6, -4, -3, 0, 2, 3, 4, 6, 7, 12, 13, 14, 15]))
        self.assertEqual(12, f([-12, -11, -10, -9, -8, -7, -6, -5, -4, 3, 8, 9, 12, 13]))
        self.assertEqual(14, f([-15, -11, -7, -5, -3, 0, 1, 4, 5, 8, 9, 10, 11, 12, 14]))
        self.assertEqual(14, f([-12, -10, -9, -8, -7, -1, 0, 4, 5, 6, 7, 8, 10, 11, 14]))
        self.assertEqual(13, f([-14, -13, -10, -9, -8, -4, -2, -1, 0, 1, 2, 5, 12, 13, 15]))

    # solution-a



    # solution -b

    def test_magic_index_duplicates(self):

        f = magic_index_duplicates
        self.assertEqual( 1, f([-3, 1, 2]))
        self.assertEqual(-1, f([-1, 0, 3]))
        self.assertEqual( 2, f([-3, 0, 2]))
        self.assertEqual(-1, f([-3, 0, 0]))
        self.assertEqual(-1, f([-3, 0, 1]))
        self.assertEqual( 1, f([-3, 1, 1]))
        self.assertEqual(-1, f([-3, -3, -2]))
        self.assertEqual( 0, f([0, 2, 2, 3]))
        self.assertEqual( 0, f([0, 3, 4, 4]))
        self.assertEqual(-1, f([-2, -1, 0, 1]))
        self.assertEqual(-1, f([-5, -5, 1, 2, 2]))
        self.assertEqual( 3, f([-4, -1, 3, 3, 4]))
        self.assertEqual(-1, f([-4, -1, 0, 4, 5]))
        self.assertEqual( 1, f([-6, 1, 1, 3, 4, 5]))
        self.assertEqual( 2, f([-3, 1, 2, 4, 5, 5]))
        self.assertEqual( 3, f([-4, -3, -2, 3, 3, 4]))
        self.assertEqual(-1, f([-6, -4, -4, -4, 0, 2]))
        self.assertEqual( 3, f([-6, -4, 2, 3, 4, 5, 7]))
        self.assertEqual(-1, f([-4, -3, -3, -2, -2, -2]))
        self.assertEqual( 5, f([-7, -6, -4, 0, 2, 5, 5]))
        self.assertEqual( 4, f([-6, -4, -2, 4, 4, 4, 5]))
        self.assertEqual(-1, f([-7, -6, -6, -6, -1, 4, 5]))
        self.assertEqual(-1, f([-5, -4, -3, 0, 1, 2, 3, 6]))
        self.assertEqual( 5, f([-6, -2, -1, 5, 5, 5, 5, 6]))
        self.assertEqual( 3, f([-5, -3, -2, 3, 6, 7, 7, 7]))
        self.assertEqual( 6, f([-8, -4, -3, -2, 1, 4, 6, 8]))
        self.assertEqual(-1, f([-8, -3, 0, 1, 2, 3, 5, 5, 6]))
        self.assertEqual( 6, f([-9, -5, 0, 2, 5, 6, 6, 6, 7]))
        self.assertEqual( 6, f([-8, -7, -4, -4, -3, -2, 6, 7]))
        self.assertEqual(-1, f([-9, -7, -6, -5, 0, 1, 4, 6, 7]))
        self.assertEqual(-1, f([-8, -7, -7, -6, -5, -4, -1, 2, 7]))
        self.assertEqual(-1, f([-8, -8, -7, -7, -7, 0, 1, 1, 7, 8]))
        self.assertEqual( 8, f([-10, -7, -2, 0, 1, 1, 1, 2, 8, 10]))
        self.assertEqual(-1, f([-2, -1, 1, 1, 2, 2, 4, 4, 5, 6, 9]))
        self.assertEqual(-1, f([-10, -10, -8, -6, -1, 1, 3, 3, 5, 8]))
        self.assertEqual(-1, f([-11, -9, -7, -6, 1, 2, 2, 4, 6, 6, 7]))
        self.assertEqual(-1, f([-5, -4, -3, -3, -2, 1, 3, 4, 5, 8, 9]))
        self.assertEqual(-1, f([-10, -10, -7, -3, -1, 1, 3, 9, 10, 10]))
        self.assertEqual( 5, f([-11, -11, -9, 0, 5, 5, 7, 8, 9, 9, 10]))
        self.assertEqual(10, f([-10, -8, -7, -2, -2, -1, 0, 3, 5, 6, 10]))
        self.assertEqual( 8, f([-9, -7, -4, -2, 0, 4, 7, 8, 8, 8, 9, 11]))
        self.assertEqual(12, f([-9, -9, -7, -5, -4, 3, 5, 5, 7, 7, 8, 9, 12]))
        self.assertEqual(11, f([-12, -10, -9, -6, -4, -4, -3, -1, 0, 0, 9, 11]))
        self.assertEqual(-1, f([-14, -10, -7, -4, 0, 2, 2, 5, 6, 8, 9, 10, 11, 11, 13]))
        self.assertEqual(13, f([-14, -13, -9, -9, -8, -7, -7, -7, 1, 5, 9, 10, 13, 13]))
        self.assertEqual(14, f([-9, -8, -6, -6, -5, -4, -4, -3, 1, 3, 5, 6, 13, 14, 14]))
        self.assertEqual(-1, f([-12, -11, -11, -9, -9, -8, -6, -6, -3, -1, 0, 8, 9, 12, 12]))
        self.assertEqual(-1, f([-13, -12, -11, -11, -10, -6, -4, -3, -1, 1, 7, 8, 9, 9, 15]))
        self.assertEqual(12, f([-13, -13, -13, -12, -11, -10, 0, 0, 0, 5, 6, 6, 12, 14, 15]))
        self.assertEqual(13, f([-13, -12, -10, -8, -7, -5, -1, 8, 10, 11, 12, 12, 13, 13, 14]))
    # solution -b
    # 9-3






    # 9-4
    def test_subset(self):

        def f(x):
            """ Return a sorted list with all the subsets.

            The list is sorted by the number of elements in the set and then
            lexicographically. For example: [[], [1], [2], [12]]. Python sorts
            are guaranteed to be stable, so we can build this sort in a series
            of two sorting steps.

            """

            subs = list(subsets(x))
            subs.sort()
            subs.sort(key = len)
            return subs

        self.assertEqual(f('')  , [[]])

        self.assertEqual(f('a') , [[],
                                   ['a']])

        self.assertEqual(f('ab'), [[],
                                   ['a'], ['b'],
                                   ['a', 'b']])

        self.assertEqual(f('abc'), [[],
                                    ['a'], ['b'], ['c'],
                                    ['a', 'b'], ['a', 'c'], ['b', 'c'],
                                    ['a', 'b', 'c']])


    # 9-4




    # 9-5
    def test_permutations(self):

        for _ in xrange(50):

            size = random.randint(1, 8)
            str_ = ''.join(random.sample(string.lowercase, size))
            # itertools.permutations() returns tuples such as ('a', 'b', 'c')
            it = (''.join(s) for s in itertools.permutations(str_, len(str_)))
            expected = sorted(it)
            output = sorted(permutations(str_))

            self.assertEqual(output, expected)
            self.assertEqual(len(output), math.factorial(len(str_)))
            msg = "{0!r}: {1}! = {2} permutations"
            print msg.format(str_, len(str_), len(output))
    # 9-5







    # 9-6
    def test_parentheses(self):

        def f(n):
            """ Return a sorted list with the combinations of parentheses. """
            return sorted(parentheses(n))

        self.assertEqual(f(1), ['()'])
        self.assertEqual(f(2), ['(())', '()()'])
        self.assertEqual(f(3), ['((()))', '(()())', '(())()', '()(())', '()()()'])
    # 9-6








    # 9-7
    def test_fill(self):

        s1 = Screen.make([[0, 0],
                          [0, 2]])
        s1.fill(0, 0, 3)
        self.assertEqual(s1.pixels, [[3, 3],
                                     [3, 2]])

        s2 = Screen.make([[0, 0, 0, 1],
                          [0, 1, 0, 2],
                          [0, 1, 1, 2]])
        s2.fill(1, 2, 4)
        self.assertEqual(s2.pixels, [[4, 4, 4, 1],
                                     [4, 1, 4, 2],
                                     [4, 1, 1, 2]])

        # Unconnected area at top right corner
        s3 = Screen.make([[2, 1, 1, 2, 2],
                          [2, 1, 1, 0, 0],
                          [2, 1, 2, 1, 2],
                          [2, 2, 2, 2, 2]])

        s3.fill(2, 2, 0)
        self.assertEqual(s3.pixels, [[0, 1, 1, 2, 2],
                                     [0, 1, 1, 0, 0],
                                     [0, 1, 0, 1, 0],
                                     [0, 0, 0, 0, 0]])
    # 9-7






    # 9-8

    def test_make_change(self):

        f = make_change
        self.assertEqual(f(0), 1)
        self.assertEqual(f(1), 1)
        self.assertEqual(f(2), 1)
        self.assertEqual(f(3), 1)
        self.assertEqual(f(4), 1)
        self.assertEqual(f(5), 2)
        self.assertEqual(f(6), 2)
        self.assertEqual(f(7), 2)
        self.assertEqual(f(8), 2)
        self.assertEqual(f(9), 2)
        self.assertEqual(f(10), 4)
        self.assertEqual(f(11), 4)
        self.assertEqual(f(12), 4)
        self.assertEqual(f(13), 4)
        self.assertEqual(f(14), 4)
        self.assertEqual(f(15), 6)
        self.assertEqual(f(16), 6)
        self.assertEqual(f(17), 6)
        self.assertEqual(f(18), 6)
        self.assertEqual(f(19), 6)
        self.assertEqual(f(20), 9)
        self.assertEqual(f(21), 9)
        self.assertEqual(f(22), 9)
        self.assertEqual(f(23), 9)
        self.assertEqual(f(24), 9)
        self.assertEqual(f(25), 13)
        self.assertEqual(f(26), 13)
        self.assertEqual(f(27), 13)
        self.assertEqual(f(28), 13)
        self.assertEqual(f(29), 13)
        self.assertEqual(f(30), 18)
        self.assertEqual(f(31), 18)
        self.assertEqual(f(32), 18)
        self.assertEqual(f(33), 18)
        self.assertEqual(f(34), 18)
        self.assertEqual(f(35), 24)
        self.assertEqual(f(36), 24)
        self.assertEqual(f(37), 24)
        self.assertEqual(f(38), 24)
        self.assertEqual(f(39), 24)
        self.assertEqual(f(40), 31)
        self.assertEqual(f(41), 31)
        self.assertEqual(f(42), 31)
        self.assertEqual(f(43), 31)
        self.assertEqual(f(44), 31)
        self.assertEqual(f(45), 39)
        self.assertEqual(f(46), 39)
        self.assertEqual(f(47), 39)
        self.assertEqual(f(48), 39)
        self.assertEqual(f(49), 39)
        self.assertEqual(f(50), 49)
        self.assertEqual(f(51), 49)
        self.assertEqual(f(52), 49)
        self.assertEqual(f(53), 49)
        self.assertEqual(f(54), 49)
        self.assertEqual(f(55), 60)
        self.assertEqual(f(56), 60)
        self.assertEqual(f(57), 60)
        self.assertEqual(f(58), 60)
        self.assertEqual(f(59), 60)
        self.assertEqual(f(60), 73)
        self.assertEqual(f(61), 73)
        self.assertEqual(f(62), 73)
        self.assertEqual(f(63), 73)
        self.assertEqual(f(64), 73)
        self.assertEqual(f(65), 87)
        self.assertEqual(f(66), 87)
        self.assertEqual(f(67), 87)
        self.assertEqual(f(68), 87)
        self.assertEqual(f(69), 87)
        self.assertEqual(f(70), 103)
        self.assertEqual(f(71), 103)
        self.assertEqual(f(72), 103)
        self.assertEqual(f(73), 103)
        self.assertEqual(f(74), 103)
        self.assertEqual(f(75), 121)
        self.assertEqual(f(76), 121)
        self.assertEqual(f(77), 121)
        self.assertEqual(f(78), 121)
        self.assertEqual(f(79), 121)
        self.assertEqual(f(80), 141)
        self.assertEqual(f(81), 141)
        self.assertEqual(f(82), 141)
        self.assertEqual(f(83), 141)
        self.assertEqual(f(84), 141)
        self.assertEqual(f(85), 163)
        self.assertEqual(f(86), 163)
        self.assertEqual(f(87), 163)
        self.assertEqual(f(88), 163)
        self.assertEqual(f(89), 163)
        self.assertEqual(f(90), 187)
        self.assertEqual(f(91), 187)
        self.assertEqual(f(92), 187)
        self.assertEqual(f(93), 187)
        self.assertEqual(f(94), 187)
        self.assertEqual(f(95), 213)
        self.assertEqual(f(96), 213)
        self.assertEqual(f(97), 213)
        self.assertEqual(f(98), 213)
        self.assertEqual(f(99), 213)
        self.assertEqual(f(100), 242)
    # 9-8







    # 9-9
    def test_queens_nways(self):

        f = ChessBoard.queens_nways
        # https://oeis.org/A000170
        self.assertEqual(f(1), 1)
        self.assertEqual(f(2), 0)
        self.assertEqual(f(3), 0)
        self.assertEqual(f(4), 2)
        self.assertEqual(f(5), 10)
        self.assertEqual(f(6), 4)
        self.assertEqual(f(7), 40)
        self.assertEqual(f(8), 92)
        self.assertEqual(f(9), 352)
        self.assertEqual(f(10), 724)
    # 9-9







    # 9-10
    def test_find_tallest(self):

        b1 = Box(3, 4, 1)
        b2 = Box(8, 6, 2)
        b3 = Box(7, 8, 3)
        stack1 = Stack([b1, b2, b3])
        self.assertEqual(stack1.find_tallest(), [b3, b1])


        b1 = Box(1.5, 6.1, 7.3)
        b2 = Box(4.3, 6.3, 4.9)
        b3 = Box(4.1, 6.4, 9.4)
        b4 = Box(1.3, 0.8, 1.2)
        stack2 = Stack([b1, b2, b3, b4])
        self.assertEqual(stack2.find_tallest(), [b3, b1, b4])
    # 9-10





    # 9-11
    def test_parenthesize(self):

        f = parenthesize_count
        self.assertEqual(0,     f("0", True))
        self.assertEqual(1,     f("0", False))
        self.assertEqual(1,     f("1", True))
        self.assertEqual(0,     f("1", False))
        self.assertEqual(1,     f("0^1", True))
        self.assertEqual(0,     f("0^1", False))
        self.assertEqual(1,     f("1&1", True))
        self.assertEqual(0,     f("1&1", False))
        self.assertEqual(0,     f("1&0", True))
        self.assertEqual(1,     f("1&0", False))
        self.assertEqual(0,     f("1^1", True))
        self.assertEqual(1,     f("1^1", False))
        self.assertEqual(2,     f("1|1^0", True))
        self.assertEqual(0,     f("1|1^0", False))
        self.assertEqual(0,     f("0^0^0", True))
        self.assertEqual(2,     f("0^0^0", False))
        self.assertEqual(0,     f("1&1&0", True))
        self.assertEqual(2,     f("1&1&0", False))
        self.assertEqual(0,     f("0|0&1", True))
        self.assertEqual(2,     f("0|0&1", False))
        self.assertEqual(0,     f("1&0&0", True))
        self.assertEqual(2,     f("1&0&0", False))
        self.assertEqual(0,     f("0^1&0", True))
        self.assertEqual(2,     f("0^1&0", False))
        self.assertEqual(2,     f("1|1|0", True))
        self.assertEqual(0,     f("1|1|0", False))
        self.assertEqual(0,     f("0|1^1", True))
        self.assertEqual(2,     f("0|1^1", False))
        self.assertEqual(3,     f("1^0|0|1", True))
        self.assertEqual(2,     f("1^0|0|1", False))
        self.assertEqual(0,     f("1&1&0^0", True))
        self.assertEqual(5,     f("1&1&0^0", False))
        self.assertEqual(3,     f("1^0^0&0", True))
        self.assertEqual(2,     f("1^0^0&0", False))
        self.assertEqual(7,     f("1^1^1^0|1", True))
        self.assertEqual(7,     f("1^1^1^0|1", False))
        self.assertEqual(9,     f("0|1&0&1^1", True))
        self.assertEqual(5,     f("0|1&0&1^1", False))
        self.assertEqual(14,    f("0|0|1^0^0", True))
        self.assertEqual(0,     f("0|0|1^0^0", False))
        self.assertEqual(28,    f("1^0&0&1&1^0", True))
        self.assertEqual(14,    f("1^0&0&1&1^0", False))
        self.assertEqual(104,   f("0^0|1&1^1|0|1", True))
        self.assertEqual(28,    f("0^0|1&1^1|0|1", False))
        self.assertEqual(132,   f("0^0|1&1&0|1|1", True))
        self.assertEqual(0,     f("0^0|1&1&0|1|1", False))
        self.assertEqual(43,    f("0&1&0|0|1|0&1", True))
        self.assertEqual(89,    f("0&1&0|0|1|0&1", False))
        self.assertEqual(249,   f("1&1|1^1|1^1^0^0", True))
        self.assertEqual(180,   f("1&1|1^1|1^1^0^0", False))
        self.assertEqual(84,    f("0&1^1|1^1^1&0|0", True))
        self.assertEqual(345,   f("0&1^1|1^1^1&0|0", False))
        self.assertEqual(174,   f("1|1&1&1&1^1^0&0", True))
        self.assertEqual(255,   f("1|1&1&1&1^1^0&0", False))
        self.assertEqual(0,     f("0|0&1|0&0&1|0^0", True))
        self.assertEqual(429,   f("0|0&1|0&0&1|0^0", False))
        self.assertEqual(276,   f("0|0&1|1^0|0&0|1", True))
        self.assertEqual(153,   f("0|0&1|1^0|0&0|1", False))
        self.assertEqual(203,   f("1^0^1&1^0^1^1&0", True))
        self.assertEqual(226,   f("1^0^1&1^0^1^1&0", False))
        self.assertEqual(1140,  f("1|0^1&1|1^1^1&1|1", True))
        self.assertEqual(290,   f("1|0^1&1|1^1^1&1|1", False))
        self.assertEqual(182,   f("1&0&1^1&1^1|0|0^0", True))
        self.assertEqual(1248,  f("1&0&1^1&1^1|0|0^0", False))
        self.assertEqual(202,   f("1&0|0&1^1^1&1^0&1", True))
        self.assertEqual(1228,  f("1&0|0&1^1^1&1^0&1", False))
        self.assertEqual(2739,  f("0^0|1^1&0&1^0|0|0&1", True))
        self.assertEqual(2123,  f("0^0|1^1&0&1^0|0|0&1", False))
        self.assertEqual(4236,  f("1|0|0&1^1|1|1^0&1&1", True))
        self.assertEqual(626,   f("1|0|0&1^1|1|1^0&1&1", False))
        self.assertEqual(4482,  f("1&1^1^0^1&0|1&1|0|0", True))
        self.assertEqual(380,   f("1&1^1^0^1&0|1&1|0|0", False))
        self.assertEqual(648,   f("0^0&0&1^0&0|0^1^0&0", True))
        self.assertEqual(4214,  f("0^0&0&1^0&0|0^1^0&0", False))
        self.assertEqual(13055, f("1|1|1^1^1|1&1^1&0&1&1", True))
        self.assertEqual(3741,  f("1|1|1^1^1|1&1^1&0&1&1", False))
        self.assertEqual(1849,  f("0&0&1^1|1^0&0^1|0&0&0", True))
        self.assertEqual(14947, f("0&0&1^1|1^0&0^1|0&0&0", False))
        self.assertEqual(1968,  f("0&0^0|1^1|1&0^1^1^1&0", True))
        self.assertEqual(14828, f("0&0^0|1^1|1&0^1^1^1&0", False))
        self.assertEqual(11863, f("1|0&1&1|0|0|0^0|1^1^1", True))
        self.assertEqual(4933,  f("1|0&1&1|0|0|0^0|1^1^1", False))
        self.assertEqual(9805,  f("1^0|1|1^1^1|0|0^1^0&1", True))
        self.assertEqual(6991,  f("1^0|1|1^1^1|0|0^1^0&1", False))
        self.assertEqual(0,     f("0^0|0^0&1&1|0^0&1&0&0", True))
        self.assertEqual(16796, f("0^0|0^0&1&1|0^0&1&0&0", False))
        self.assertEqual(2582,  f("0&1&1|1&1|0^0^1|1&0&1", True))
        self.assertEqual(14214, f("0&1&1|1&1|0^0^1|1&0&1", False))
        self.assertEqual(11540, f("1|0^1&0|1^1^0^0&0^0&1", True))
        self.assertEqual(5256,  f("1|0^1&0|1^1^0^0&0^0&1", False))
        self.assertEqual(16796, f("1&1&0|1|1|1|1|1|0&0|1", True))
        self.assertEqual(0,     f("1&1&0|1|1|1|1|1|0&0|1", False))
        self.assertEqual(7083,  f("0^1|0&1^0^1&1|0|1&0&1", True))
        self.assertEqual(9713,  f("0^1|0&1^0^1&1|0|1&0&1", False))
        self.assertEqual(19782, f("1^1|1^0&0|1|0&1^1&0&1^0", True))
        self.assertEqual(39004, f("1^1|1^0&0|1|0&1^1&0&1^0", False))
        self.assertEqual(20917, f("0^0^1^1^1&0&0|1^1&1|1^1", True))
        self.assertEqual(37869, f("0^0^1^1^1&0&0|1^1&1|1^1", False))
        self.assertEqual(10396, f("0|1&1&1^1&1^1|1&0^0&0&0", True))
        self.assertEqual(48390, f("0|1&1&1^1&1^1|1&0^0&0&0", False))
        self.assertEqual(34209, f("1^1&0|1^0^1&0|1^0&0&1|1", True))
        self.assertEqual(24577, f("1^1&0|1^0^1&0|1^0&0&1|1", False))

    # 9-11






if __name__ == "__main__":
    unittest.main()