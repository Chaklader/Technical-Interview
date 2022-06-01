
# question:  sort a dictionary based on the second 
# value of the tuple and get the top k elements

A = {'a':(3, 15), 'b':(1, 4), 'c': (10,11)}
from collections import OrderedDict

value = OrderedDict(sorted(A.iteritems(), key=operator.itemgetter(1), reverse=False)[:2])
# END of solution 




# question: print an element of matrix in zig-zag manner 
# END of solution 




# Question 1.1 Implement an algorithm to determine if a string has all 
# unique characters. What if you cannot use additional data structures?
import unittest


def hasAllUniqueCharsNoDS(inputstring):

    #no data structures
    #O(n^2)
    for char in inputstring:

        foundcount=0

        for char2 in inputstring:

            if char==char2:
                foundcount=foundcount+1
            if foundcount>1:
                return False
    return True


def hasAllUniqueCharsList(inputstring):

    #using a list
    #O(n^2)
    charlist = []
    for char in inputstring:

        #checking if char is in the list is O(n) according to http://wiki.python.org/moin/TimeComplexity
        if char in charlist:
            return False
        else:
            charlist.append(char)
    return True


def hasAllUniqueCharsList2(inputstring):
    #another way to use a list
    #O(n)
    if len(inputstring) > 256: #assume inputstring is ASCII
        return False
    else:
        charList = [False] * 256;
        for char in inputstring:
            if charList[ord(char)]:
                return False
            else:
                charList[ord(char)] = True
        return True


def hasAllUniqueChars(inputstring):

    #using a hash table (here as a dictionary of key/values)
    chardict = {}
    for char in inputstring:
        #checking if char is in the dict is O(1). In practice however, this is expected to perform worse than using lists for small numbers of n.
        if char in chardict:
            return False
        else:
            chardict[char]=True
    return True


def hasAllUniqueCharsSet(inputstring):
    #using a hash table (here as a set data structure)
    if len(inputstring) > 256: return False
    return len(set(inputstring)) == len(inputstring)
    
def hasAllUniqueCharsBitVector(inputstring):
    #using a bit vector
    #here as assumption in book, assume string only use  
    if len(inputstring) > 26: return False

    checker = 0

    for char in inputstring:
        if checker & (1 << ord(char)):
            return False
        checker |= (1 << ord(char))
    return True

#testing

#positive test case
teststringtrue = "abcdefghijkl"
#negative test case
teststringfalse = "aabvcgdfgbvxbj"

#list of all functions to test
funclist = [hasAllUniqueCharsNoDS,hasAllUniqueCharsList,hasAllUniqueCharsList2,hasAllUniqueChars, hasAllUniqueCharsSet, hasAllUniqueCharsBitVector]



for func in funclist:

    print "Testing function " + str(func)

    if func(teststringtrue):
        print "Test 1 passed"

    else:
        print "Test 1 failed"
    
    if not func(teststringfalse):
        print "Test 2 passed"

    else:
        print "Test 2 failed"





#! /usr/bin/env python
# Author: Victor Terron (c) 2014
# Email: `echo vt2rron1iaa32s | tr 132 @.e`
# License: GNU GPLv3

""" Simple, Pythonic solutions to question 1-1 """

def no_duplicates(str_):
    """ Determine if str_ has all unique characters """
    return len(str_) == len(set(str_))

def no_duplicates_no_structures(str_):
    """ Now without using additional data structures """

    for letter in str_:
        if str_.count(letter) > 1:
            return False
    else:
        return True

class NoDuplicatesTest(unittest.TestCase):

    # Two-element tuples: input string and expected result
    TEST_DATA = [
        ('a', True),
        ('aa', False),
        ('ab', True),
        ('ab ', True),
        ('', True),
        (' ', True),
        ('  ', False),
        ('qwerty', True),
        ('qwerte', False)]

    def test_no_duplicates_both_versions(self):

        for str_, expected_result in self.TEST_DATA:

            result1 = no_duplicates(str_)
            self.assertEqual(result1, expected_result)

            # Test the version without additional data structures too
            result2 = no_duplicates_no_structures(str_)
            self.assertEqual(result2, expected_result)



# ================================================
def allunique(s):

    charsTable = set()
  
    for c in s:

        if c in charsTable:
            return False

        charsTable.add(c)
    return True


def pairs(seq):

    n = len(seq)

    for i in range(n):
        yield seq[i], seq[(i + 1) % n]


def allunique2(s):

   srtStr = sorted(s)

   for (c1, c2) in pairs(srtStr):
       if c1 == c2:
           return False
   return True



class ExpectedResult(unittest.TestCase):

    def testEmptyStr(self):
        '''allunique must return True with empty strings'''
        result = allunique('')
        self.assertTrue(result)

    def testSingleCharStr(self):
        '''allunique must return True with single character strings'''
        result = allunique('a')
        self.assertTrue(result)

    def testDoubleCharStr(self):
        '''allunique must return True with double character strings that don't have the same characters'''
        result = allunique('ab')
        self.assertTrue(result)

    def testStrWithTwoConsChar(self):
        '''allunique must return False with strings that have two same consecutive characters'''
        result = allunique('aab')
        self.assertFalse(result)

    def testStrWithTwoNonConsChar(self):
        '''allunique must return False with strings that have two same non consecutive characters'''
        result = allunique('aba')
        self.assertFalse(result)

    def testStrWithNoConsChar(self):
        '''allunique must return True with strings that have all unique characters'''
        result = allunique('promise')
        self.assertTrue(result)

# END of question 1-1 




# question 1-2 
# ------------


#Implement a function that reverses a string
#using recursion here to avoid recopying the string, as described in the discussion of "string buffer".

def reverseStringRecursive(str1):

    if str1 != "":
        return str1[-1:] + reverseStringRecursive(str1[:-1])
    else:
        return ""

#testing
palindrome = "abcdedcba"
nonpalindrome = "hello!"

#palindrome can be reversed and would still be equal to itself, by definition.
if palindrome == reverseStringRecursive(palindrome):
    print "Test 1 passed"

if nonpalindrome != reverseStringRecursive(nonpalindrome):
    print "Test 2 passed"


def revStr(s):
    return s[::-1]


# testing of the 1-2
# ------------------

class TestReversal(unittest.TestCase):


    def testEmpty(self):
        '''revStr must return the same string for empty strings'''        
        self.assertEqual('', revStr(''))

    def testSingle(self):
        '''revStr must return the same string for single character strings'''
        self.assertEqual('a', revStr('a'))

    def testDoubleEq(self):
        '''revStr must return the same string for double same-character strings'''
        self.assertEqual('aa', revStr('aa'))

    def testDoubleDiff(self):
        '''revStr must return the reversed for double character strings'''
        self.assertEqual('ba', revStr('ab'))

    def testValid(self):
        '''revStr must return the reversed of known strings'''
        words = ( ('mary', 'yram'),
                  ('peter', 'retep'),
                  ('anna', 'anna') )
        for (orig, exp) in words:
            expected = revStr(orig)
            self.assertEqual(expected, exp)

    def testInvalid(self):
        '''revStr must not match the non-reversed of known strings'''
        words = (('hello', 'olle'), ('henry', 'yrnhe'))

        for (orig, nonexp) in words:
            expected = revStr(orig)
            self.assertNotEqual(expected, nonexp)


# END of solution 1-2 
# -------------------








# question 1-3  // anagram of question
# ------------------------------------
#Given two strings, write a method to decide if one is a permutation of the other.

# O(n^2)
def isPermutation(s1, s2):

    if len(s1) != len(s2):
        return False

    else:
        for char in s1:
            if s2.find(char) == -1:
                return False
            else:
                s2 = s2.replace(char,"",1)
        return True

# big O complexity depends on python list sort complexity, which should be better than O(n^2)
def isPermutationSort(s1,s2):
    #sort both strings, check if they are equal
    if len(s1)!=len(s2): return False
    return sorted(s1) == sorted(s2)


#O(n)
def isPermutationHash(s1,s2):

    #using a dict as a hash table to count occurences, then comparing the 2 dict
    charcountdict1 = makeCharCountDict(s1)
    charcountdict2 = makeCharCountDict(s2)
    
    if len(charcountdict1) != len(charcountdict2):
        return false
        
    for char in charcountdict1:
        if not (char in charcountdict2 and charcountdict1[char]==charcountdict2[char]):
            return False        
    return True


    
#testing
#permutation
postest1 = ["abcdefgh","abcdefhg"]

#not permutation
negtest2 = ["abcdefgh","gfsdgsdffsd"]

#not permutation
negtest3 = ["abcdefgh","gfsdgsdf"]


#list of all functions to test
funclist = [isPermutation,isPermutationSort,isPermutationHash]

for func in funclist:

    print "Testing function " + str(func)    

    if func(postest1[0],postest1[1]):
        print "Test 1 passed"

    if not func(negtest2[0],negtest2[1]):
        print "Test 2 passed"

    if not func(negtest3[0],negtest3[1]):
        print "Test 3 passed"
                


def anagram(s1, s2):

    s1 = s1.lower()
    s2 = s2.lower()

    # sort, convert to str and strip
    s1 = ''.join(sorted(s1)).strip()
    s2 = ''.join(sorted(s2)).strip()
    return s1 == s2

def anagram2(string1, string2):

    ''' We just check if the counts of each character are the same '''
    from collections import Counter
    return Counter(string1)==Counter(string2)




class TestAnagram(unittest.TestCase):
    
    def testEmpty(self):
        '''anagram must return true for empty strings'''
        self.assertTrue(anagram('', ''))

    def testSingle(self):
        '''anagram must return true for single character strings'''
        self.assertTrue(anagram('a', 'a'))

    def testDoubleEq(self):
        '''anagram must return true for double same-character strings'''
        self.assertTrue(anagram('aa', 'aa'))

    def testValid(self):
        '''anagram must return true for known anagrams'''
        words = ( ('So dark the con          of man', 'Madonna of        The Rocks'),
                  (' ba ', ' Ab   ') )
        for (x, y) in words:
            self.assertTrue(anagram(x, y))

    def testInvalid(self):
        '''anagram must return false for known non-anagrams'''
        words = ( ('hello', 'ello'),
                  (' anne ', ' annea   ') )
        for (x, y) in words:
            self.assertFalse(anagram(x, y))

# END of question 1-3
# -------------------




# question 1-4
# ------------

"""write a method to replace all space in a string with '%20'
"""

# using a list to store each char and change space to '%20', then join list into a string
def replaceSpace(string):

	charList = []
	for char in string:
		if char == ' ':
			char = '%20'
		charList.append(char)
	return ''.join(charList)

#test
inputStr = " Smith    q m "
expectOutput = "%20Smith%20%20%20%20q%20m%20"

if replaceSpace(inputStr) == expectOutput:
	print "test passed"

else:
	print "test failed"


# -------------------------------------------------------------------------------
#Write a method to replace all spaces in a string with '%20'.
#Treat the string as a char array to make it challenging. Otherwise you would just use string replacement.
#Assume the char array has 2 spaces at the end for every one space in the body, so that you dont have to resize the array.



#making an interface to use the back of the array as a queue
class BackQueue:

    def __init__(self, instring):
        self.instring = instring
        self.queuelength = 0
        
    def addToQueue(self,char):
        #find first open space from end and add the char
        backindex=(len(self.instring)-1) - self.queuelength
        self.instring[backindex]=char
        self.queuelength += 1
        
    def getFromQueue(self):
        #pop the queue
        returnchar = self.instring[len(self.instring)-1]
        self.shiftQueue()
        self.queuelength -= 1
        return returnchar

    def shiftQueue(self):        

        backindex=len(self.instring)-1
        shiftcount=0

        while shiftcount < self.queuelength:

            self.instring[backindex] = self.instring[backindex-1]
            backindex -= 1
            shiftcount += 1

        
def changeSpacesForURL(spacestring):

    index=0
    bqueue = BackQueue(spacestring)
    
    while index<len(spacestring):
    
        if (index + bqueue.queuelength) == len(spacestring):
            #we need to operate differently if the back queue is full. direct transfer.
            spacestring[index]=bqueue.getFromQueue()
    
        else:

            thischar=spacestring[index]

            if bqueue.queuelength>0:            

                spacestring[index]=bqueue.getFromQueue()

                if thischar==" ":
                    bqueue.addToQueue("%")
                    bqueue.addToQueue("2")
                    bqueue.addToQueue("0")
                else:
                    bqueue.addToQueue(thischar)

            else:
                #we need to operate differently depending on if the back queue is empty (direct transfer instead of using queue).
                if thischar== " ":
                    spacestring[index]="%"
                    bqueue.addToQueue("2")
                    bqueue.addToQueue("0")
                else:
                    spacestring[index]=thischar
        index += 1
    return spacestring


    
#testing

inputstring = ["M","r"," ","J","o","h","n"," ","S","m","i","t","h"," "," "," "," "]
expectedoutputstring = ["M","r","%","2","0","J","o","h","n","%","2","0","S","m","i","t","h"]

outstring=""

for char in changeSpacesForURL(inputstring):
    outstring = outstring+char

expectedoutstring=""

for char in expectedoutputstring:
    expectedoutstring = expectedoutstring+char

if outstring == expectedoutstring:
    print "Passed Test 1"


#edge case where string is all spaces, 2 that need to be replaced, and 4 for the expansion room, 6 spaces total
inputstring = [" "," "," "," "," "," "]
expectedoutputstring = ["%","2","0","%","2","0"]

outstring=""
for char in changeSpacesForURL(inputstring):
    outstring = outstring+char

expectedoutstring=""
for char in expectedoutputstring:
    expectedoutstring = expectedoutstring+char

if outstring == expectedoutstring:
    print "Passed Test 2"


# ------------ ------------ ------------ ------------ ------------ ------------ ------------ ------------
def replaceSpaces(s):
    return '%20'.join(s.split())

def replace_spaces(string, size):
    return string[:size].replace(' ', '%20')



class TestReplace(unittest.TestCase):

    def testEmpty(self):


        '''replaceSpaces must return empty for empty strings'''
        s = replaceSpaces('')
        self.assertEqual('', s)

    def testSingle(self):
        
        '''replaceSpaces must return the original string for single character strings'''
        s = replaceSpaces('a')
        self.assertEqual('a', s)

    def testWordNoSpace(self):
        '''replaceSpaces must return the original string for strings without spaces'''
        s = replaceSpaces('hello')
        self.assertEqual('hello', s)

    def testWordHeadTrailSpace(self):
        '''replaceSpaces must remove heading and trailing spaces'''
        words = ( ('hello ', 'hello'),
                  (' hello', 'hello'),
                  (' hello ', 'hello') )
        for orig, expected in words:
            e = replaceSpaces(orig)
            self.assertEqual(e, expected)

    def testWordSpace(self):
        '''replaceSpaces must replace all non heading/trailing spaces with %20'''
        words = ( ('hello world', 'hello%20world'),
                  (' hello    python   ', 'hello%20python'),
                  (' hello my  friend', 'hello%20my%20friend') )
        for orig, expected in words:
            e = replaceSpaces(orig)
            self.assertEqual(e, expected)

# END of question 1-4
# -------------------








# question 1-5
# -------------------
#Implement a method to perform basic string compression using the counts of repeated characters

#aabcccccaaa would become a2blc5a3.
#do nothing if this would not make the string smaller.


def simpleCompress(compstring):

    #Avoid using the + and += operators to accumulate a string within a loop. Since strings are immutable, this creates unnecessary temporary objects and results in quadratic rather than linear running time. Instead, add each substring to a list and ''.join the list after the loop terminates (or, write each substring to a cStringIO.StringIO buffer).
    #Google python style guidelines http://google-styleguide.googlecode.com/svn/trunk/pyguide.html
    outstring=[]
    lastchar=""
    charcount=0

    for char in compstring:

        if char==lastchar:
            charcount += 1

        else:
            if lastchar != "":
                outstring.append(lastchar + str(charcount))
            charcount = 1
        lastchar=char            

    #final write
    outstring.append(lastchar + str(charcount))
    outstring = "".join(outstring)

    if len(outstring)<len(compstring):
        return outstring

    else:
        return compstring


#testing
tocompress = "aabcccccaaa"
if simpleCompress(tocompress) == "a2b1c5a3":
    print "Test 1 Passed"

else:
    print "Test 1 Failed"


tocompress2 = "aabca"
if simpleCompress(tocompress2) == tocompress2:
    print "Test 2 Passed"
else:
    print "Test 2 Failed"


#Modulated version of the solution
def countSame(string, pos):
	#Counts number of same characters following a position
	pos1 = pos
	count = 0
	while pos1 < len(string) and string[pos1] == string[pos]:
		count = count +1
		pos1 = pos1 + 1
	return count


def compress(string):

	outstring = []
	pos = 0

	while pos < len(string):
		outstring.append(string[pos] + str(countSame(string, pos)))
		pos = pos + countSame(string, pos)

	result = ''.join(outstring)
	if len(result) < len(string):
	    return result
	else:
	    retunr string

print compress("aabccc   ccaaa")







def compress(str_):

    if len(str_) < 2:
        return str_

    groups = []
    previous_character = str_[0]
    counter = 1

    for c in str_[1:]:

        if c == previous_character:
            counter += 1
        else:
            groups.append(previous_character + str(counter))
            previous_character = c
            counter = 1

    groups.append(c + str(counter))
    result = ''.join(groups)

    if len(result) < len(str_):
        return result
    else:
        return str_

import itertools


def compress(str_):
    """ Using itertools.groupby().

    There is a function in the standard library, itertools.groupby() (new in
    Python 2.4), that does exactly what we need here: it makes an iterator that
    returns consecutive keys and groups from the input iterable, defaulting to
    an identity function to compute the key value for each element. Example:

    >>> [list(g) for k, g in itertools.groupby('AAAABBBCCD')]
    [['A', 'A', 'A', 'A'], ['B', 'B', 'B'], ['C', 'C'], ['D']]

    """
    
    groups = itertools.groupby(str_)
    compressed = ''.join('{0}{1}'.format(k, len(list(g))) for k, g in groups)

    if len(compressed) < len(str_):
        return compressed

    else:
        return str_


# testing for 1-5
# ---------------

class TestCompress(unittest.TestCase):

    def testEmpty(self):
        '''compress must return empty for empty strings'''
        self.assertEqual('', compress(''))

    def testSingle(self):
        '''compress must return the original string for single character strings'''
        self.assertEqual('a', compress('a'))

    def testCompress(self):
        '''compress must return the expected output for known strings'''
        words = ( ('aaaabbbccd', 'a4b3c2d1'),
                  ('aaabbbbccd', 'a3b4c2d1'),
                  ('abccccdddeeeee', 'a1b1c4d3e5') )
        for (orig, expected) in words:
            e = compress(orig)
            self.assertEqual(expected, e)

    def testNoCompress(self):
        
        '''compress must not compress strings that turn out to have length >= than the original'''
        words = ( ('aabc', 'aabc'),
                  ('abbbc', 'abbbc'),
                  ('abccdee', 'abccdee') )
        for (orig, expected) in words:
            e = compress(orig)
            self.assertEqual(expected, e)
# END of question 1-5
# -------------------






# question of 1-6
# ----------------
#Rotate a NxN matrix 90 degrees
#(assuming clockwise rotation)


class MatrixProcessor:

    def __init__(self, matrix):

        self.n=len(matrix)
        self.m=len(matrix[0])
        self.matrix = matrix

    def __str__(self):
        # Iterating over rows and the matrix via a generator,
        # and joining the cell and then row into the final string output
        return ''.join("%s%s%s" % ("[", ''.join("%s%s%s" % (" ", str(cell), " ")
                                                for cell in row), "]\n") for row in self.matrix)
    def rotate90CW(self):
        # strategy is to make what are now rows into columns, and reverse the order,
        # which mimics the effect of rotation on the matrix.
        # This creates our empty list of columns by the length of the original matrix
        columnlist = [[] for i in range(len(self.matrix))]
        # Now this iterates through the rows and columns and appends those to the new columnlist
        [[columnlist[i].append(row[i]) for i in range(len(row))] for row in self.matrix]
        # reverses the new column list to get the order that is wanted, and assigned back to the original matrix
        self.matrix = [reversed(col) for col in columnlist]

#testing        
mat1= MatrixProcessor([[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16]])

print "M by N matrix has dimensions " + str(mat1.m) + " by " + str(mat1.n)

print "Visual representation of contents:"
print str(mat1)                  


mat1.rotate90CW()

print "Visual representation of contents after rotating 90 degrees clockwise:"
print str(mat1)                  


#sample output

##M by N matrix has dimensions 4 by 4
##Visual representation of contents:
##[ 1  2  3  4 ]
##[ 5  6  7  8 ]
##[ 9  10  11  12 ]
##[ 13  14  15  16 ]
##
##Visual representation of contents after rotating 90 degrees clockwise:
##[ 13  9  5  1 ]
##[ 14  10  6  2 ]
##[ 15  11  7  3 ]
##[ 16  12  8  4 ]


def showAsMatrix(mat):
    [print(i) for i in mat]

def rotate(seq):
    orig = list(seq)            # keep a copy of the original sequence
    STEP = END = -1             # access elements N..0 one-by-one
    r = range(len(seq[0])-1, END, STEP) # the length is fixed, calculate it only once
    for idx, _ in enumerate(seq):
        # list 0 consists of elements list[N][0], list[N-1][0], ..., list[0][0]
        # list 1 consists of elements list[N][1], list[N-1][1], ..., list[0][1], etc.
        seq[idx] = [orig[n][idx] for n in r]
    return seq

def rotate2(seq):
    import copy
    size = len(seq)
    matrix_new = copy.deepcopy(seq)
    for i in xrange(size):
        for j in reversed(range(size)):
            matrix_new[i][size-1-j] = seq[j][i]
    return matrix_new

def rotate3(seq):
    return zip(*seq[::-1])



class TestRotate(unittest.TestCase):


    def testEmpty(self):
        '''rotate must return empty for empty matrices'''
        self.assertEqual(rotate([[]]), [[]])

    def testOneByOne(self):
        '''rotate must return the original for 1x1 matrices'''
        self.assertEqual(rotate([[1]]), [[1]])

    def testTwoByTwo(self):
        '''rotate must reverse 90 degrees 2x2 matrices'''
        orig = [ [1, 2], 
                 [3, 4] ]
        
        exp =  [ [3, 1],
                 [4, 2] ]
        expected = rotate(orig)
        self.assertEqual(expected, exp)

    def testThreeByThree(self):
        '''rotate must reverse 90 degrees 3x3 matrices'''
        orig = [ [1, 2, 3],
                 [4, 5, 6],
                 [7, 8, 9] ]
        
        exp =  [ [7, 4, 1],
                 [8, 5, 2],
                 [9, 6, 3] ]
        expected = rotate(orig)
        self.assertEqual(expected, exp)

# END of qustion 1-6
# ------------------







# question of 1-7
# ---------------

#Write an algorithm such that if an element in an MxN matrixis 0, its entire row
#and column are set to 0.


class MatrixProcessor:

    def __init__(self, matrix):
        self.n=len(matrix)
        self.m=len(matrix[0])
        self.matrix = matrix

    def __str__(self):
        rowstring=""
        for row in self.matrix:
            rowstring = rowstring + "["
            for cell in row:
                rowstring = rowstring + " " + str(cell) + " "
            rowstring = rowstring + "]\n"            
        return rowstring

    def zeroProcess(self):
        columnstozero=[]
        rowstozero=[]
        for rowcount,row in enumerate(self.matrix):
            for cellcount, cell in enumerate(row):
                if cell == 0:
                    columnstozero.append(cellcount)
                    rowstozero.append(rowcount)
        for rownum in rowstozero:
            self.zeroRow(rownum)
        for colnum in columnstozero:
            self.zeroColumn(colnum)

    def zeroRow(self, rownum):
        newRow=[]
        for x in range(0, self.m):
            newRow.append(0)
        self.matrix[rownum]=newRow

    def zeroColumn(self, colnum):
        for row in self.matrix:
            row[colnum]=0
                    
#testing
        
mat1= MatrixProcessor([[1,2,3],[4,5,0],[7,8,9],[0,11,12]])

print "M by N matrix has dimensions " + str(mat1.m) + " by " + str(mat1.n)

print "Visual representation of contents:"
print str(mat1)                  


mat1.zeroProcess()

print "Visual representation of contents after zeroing all rows/columns containing a zero:"
print str(mat1)                  


#sample output

##M by N matrix has dimensions 3 by 4
##Visual representation of contents:
##[ 1  2  3 ]
##[ 4  5  0 ]
##[ 7  8  9 ]
##[ 0  11  12 ]
##
##Visual representation of contents after zeroing all rows/columns containing a zero:
##[ 0  2  0 ]
##[ 0  0  0 ]
##[ 0  8  0 ]
##[ 0  0  0 ]

# END of question 1-7
# -------------------










# question of 1-8
# ---------------

#Assume you have a method isSubstring which checks if one word is a
#substring of another. Given two strings, s1 and s2, write code to check if s2 is
#a rotation of s1 using only one call to isSubstring (e.g.,"waterbottle" is a rotation of "erbottlewat").

def isSubstring(s1,s2):

	# s1 needs to be larger and s2 smaller 
    return s1.find(s2) > -1



def rotatedStringHasSubstring(s1,s2):

    #doubling the string ensures a complete substring regardless of rotation
    if len(s2)!=len(s1):
        return False
    doubles1 = s1 + s1
    return isSubstring(doubles1,s2)



#testing
if rotatedStringHasSubstring("waterbottle","erbottlewat"):
    print "Test 1 Passed"

if not rotatedStringHasSubstring("waterbottle","nope"):
    print "Test 2 Passed"

if not rotatedStringHasSubstring("waterbottlewater","waterbottle"):
    print "Test 3 Passed"


# END of question 1-8
# -------------------










# question: compute the permutation of the provided String 
# --------------------------------------------------------


# solution -a
# -----------

def permutation(lis):

	result = []
	perHelper((), lis, result)
	return result 


def perHelper(prefix, lis, result):
	
	n = len(lis)

	if n == 0:
		result.append(prefix)
		prefix = ()

	else:

		for i in range(n): 
			perHelper( prefix+ (lis[i],) , lis[:i]+ lis[i+1:], result)

t = ["m", "i", "v"]
li = permutation(t)

print t, "\n",li	


# END of soluton -a
# -----------------





# soluton -b
# ----------

def permutation1(lis):

    res = []

    if len(lis) == 1:
        res.append(lis)

    elif len(lis) > 1:

        last_index = len(lis) -1
        last = lis[last_index]
        rest = lis[:last_index]

        if rest is not None and last is not None:
            res = merge(permutation1(rest), last)

    return res
    

def merge(lis, c):

    res = []

    for s in lis:
        for i in range(len(s)+1):
            ps = s[:i]+ c + s[i:]
            res.append(ps)

    return res

# END of soluton -b
# -----------------




# solution -c 
# -----------
# from itertools import permutations
# [i for i in permutations(li,2)]	


# solution -d
[k for k in [(i,li[j])for i in li for j in range(2)] if len(set(k)) != 1]

# END of permutation computation 
# ------------------------------





# main method to get started 
# --------------------------

if __name__ == '__main__':


	# question 1-1
	# ------------

    words = ('mary', 'ariadni')

    for w in words:
        print('allunique({}): {}'.format(w, allunique(w)))
        print('allunique2({}): {}'.format(w, allunique2(w)))

    # END question of 1-1 
    # -------------------





    # question 1-2 
    # ------------

    words = ('mary', 'anna', 'peter')
    for w in words:
        print('revStr({}): {}'.format(w, revStr(w)))

    # END of 1-2 
    # ----------






    # question 1-3
    # ------------

    words = ( ('So dark the con          of man', 'Madonna of        The Rocks'),
              (' ba ', ' Ab   '),
              ('anne', 'annea') )

    for w1, w2 in words:

        print('anagram({}, {}): {}'.format(w1, w2, anagram(w1, w2)))
        print('anagram({}, {}): {}'.format(w1, w2, anagram2(w1, w2)))

    # END question of 1-3
    # -------------------





    # question 1-4 
    # ------------

    words = ( 'hello world    ', '   mr. john  ', '', ' ', )

    for w in words:
        print('replaceSpaces({}): {}'.format(w, replaceSpaces(w)))



    # END of question 1-4
    # -------------------






    # question of 1-5
    # ---------------

    words = ('aabcccccaaa', 'aaabbbbcccccdee', 'abc', 'a', '', ' ', 'aabccdde', 'aab', 'aaabb')

    for w in words:
        print('compress({}): {}'.format(w, compress(w)))

    # question of 1-5
    # ---------------





    # question of 1-6
    # ---------------


    x = [ ['p00', 'p01', 'p02', 'p03'],
          ['p10', 'p11', 'p12', 'p13'],
          ['p20', 'p21', 'p22', 'p23'],
          ['p30', 'p31', 'p32', 'p33'] ]

    print('original matrix')
    showAsMatrix(x)
    y = rotate(x)
    print()
    print('matrix rotated 90 degrees right')
    showAsMatrix(y)
    # END of question 1-6
    # -------------------

    # unittest.main()












