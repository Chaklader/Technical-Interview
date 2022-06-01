# SOURCE: DOT NET PERLS 
# =============

import time
import random






# SORTING IN PYTHON
# =================


# Contains unsorted, positive and negative integers.
elements = [100, 200, 0, -100]
elements.sort()
print elements


elements = [22, 333, 0, -22, 1000]
view = reversed(sorted(elements))

for element in view:
    print(element)


colors = ["blue", "lavender", "red", "yellow"]

# Sort colors by length, in reverse (descending) order.
for color in sorted(colors, key=lambda color: len(color), reverse=True):
    print(color)




# A dictionary with three pairs.
furniture = {"table": 1, "chair": 2, "desk": 4}
s = sorted(furniture.keys())

# Display the sorted keys.
for key in s:
    print(key, furniture[key])




class Bird:

    def __init__(self, weight):

        # __weight for the private variable 
        self.__weight = weight

    def weight(self):
        return self.__weight

    def __repr__(self):
        return "Bird, weight = " + str(self.__weight)


# Create a list of Bird objects.
birds = []
birds.append(Bird(10))
birds.append(Bird(5))
birds.append(Bird(200))

# Sort the birds by their weights.
birds.sort(key = lambda b: b.weight())

# Display sorted birds.
for b in birds:
    print(b)




# sort a String using Pyhton 
value = "boat"

# Get list comprehension of characters and sort the list.
list = [c for c in value]
list.sort()

# Join the characters together.
result = "".join(list)
print(result)






# FILE INPUT/ OUTPUT IN PYTHON  
# ============================

import os

# The folder containing files.
f = open(os.path.expanduser("~/Desktop/somefile.txt"))
li = os.listdir(os.path.expanduser('~/Desktop'))
# li = os.listdir('/Users/Chaklader/Desktop')


# Get all files.
# directory = "C:\\programs\\"
list = os.listdir(directory)

# Loop and add files to list.
pairs = []

for file in list:

    # Use join to get full file path.
    location = os.path.join(directory, file)
    size = os.path.getsize(location)
    pairs.append((size, file))

pairs.sort(key=lambda s: s[0])

for pair in pairs:
    print pair

if path.exists(path.expanduser('~/Desktop/AZDif.png')):
	print 'Yes'

size = path.getsize(path.expanduser('~/Desktop/AZDif.png'))
print size




# Add paths to a list.
paths = []

paths.append("C:\directory\name")
paths.append("C:\directory\image")
paths.append("C:\directory\folder\index")


# Get common prefix of the paths.
prefix = path.commonprefix(paths)
print(prefix)


result = os.path.join("C:\\programs", "file.py")
# result = os.path.join("C:\\programs\\", "file.py") # also, correct 
print(result)


# Output 
# ------
# C:\programs\file.py
# C:\programs\file.py




name = "/nope.txt"

# See if the path exists.
if os.path.exists(name):

    with open(name) as f:
	   print(f.readline())

try:

    # Does not exist.
    name = "/nope.txt"

    # Attempt to open it.
    with open(name) as f:
	   print(f.readline())

except IOError:

    # Handle the error.
    print("An error occurred")






# DATETIME IN PYTHON  
# ==================

from datetime import datetime, timedelta

# Input string.
s = "August 16, 2012"

# Use strptime.
d = datetime.strptime(s, "%B %d, %Y")
print(d)





def yesterday():
    # Get today.
    today = date.today()

    # Subtract timedelta of 1 day.
    yesterday = today - timedelta(days=1)
    return yesterday

print(date.today())
print(yesterday())




def tomorrow():
    # Add one day delta.
    return date.today() + timedelta(days=1)

print(date.today())
print(tomorrow())




# Create a list of dates.
values = []

values.append(date.today() + timedelta(days=300))
values.append(date.today() + timedelta(days=2))
values.append(date.today() + timedelta(days=1))
values.append(date.today() + timedelta(days=20))

# Sort the list.
values.sort()

# Display.
for d in values:
    print(d)

# This represents 1 day.
a = timedelta(days=1)

# Represents 1 hour.
b = timedelta(hours=1)

# Subtract 1 hour from 1 day.
c = a - b
print(c)
# 23:00:00




# Get access, modification and creation time.
access = path.getatime("/enable1.txt")
modification = path.getmtime("/enable1.txt")
creation = path.getctime("/enable1.txt")


# Display the times.
print(access, modification, creation)


# Convert timestamps to dates.
access_ = date.fromtimestamp(access)
modification_ = date.fromtimestamp(modification)
creation_ = date.fromtimestamp(creation)

print(access_, modification_, creation_)






# SPLIT IN PYTHON  
# ==============

s = "topeka,kansas city,wichita,olathe"

# Separate on comma.
cities = s.split(",")

for city in cities:
    print(city)

# Irregular number of spaces between words.
s = "One two   three"

# Call split with no arguments.
words = s.split()

# Display results.
for word in words:
    print(word)




f = open("C:\perls.txt", "r")

for line in f.readlines():

    line = line.strip()
    print line
    parts = line.split(",")

    for part in parts:
        print("   ", part)


s = "Buffalo;Rochester;Yonkers;Syracuse;Albany;Schenectady"

# Split from the right, only split three.
cities = s.rsplit(";", 3)

# Loop and print.
for city in cities:
    print(city)

# Output
# ------
# Buffalo;Rochester;Yonkers
# Syracuse
# Albany
# Schenectady



s = """This string
has many
lines."""

# Split on line breaks.
lines = s.splitlines()

for line in lines:
    print("[" + line + "]")


s = "123 Oak Street, New York"
t = s.partition(" ") # partition based on the data type 

# Print tuple contents.
print(t)

# Print first element.
print("First element:", t[0])

# ('123', ' ', 'Oak Street, New York')
# First element: 123




# The input string.
s = "Dot Net Perls website"

# Continue while the string has data.
while len(s) > 0:

    # Partition on first space.
    t = s.partition(" ")

    # Display the partitioned part.
    print(t[0])

    print("    ", t)

    # Set string variable to non-partitioned part.
    s = t[2]


s = "This is a split performance test"
print(s.split())
print(s.split(" "))




# Split apart the numbers.
numbers = ""
values = numbers.split(",")

total = 0

for value in values:
    total += int(value)




word = "something"

# Get first four characters.
part = word[:4]
print(part)




values = "AaBbCcDdEe"
# Starting at 0 and continuing until end, take every other char.
evens = values[::2]
print(evens)


# Create a slice object.
example = slice(1, 10, 0)
print (example.start, example.stop, example.step)


# provides pad in the end of list
numbers = [1, 1, 1, 1]

# Expand the list up to 10 elements with zeros.
for n in range(len(numbers), 10):
    numbers.append(0)

# Output
# ------
# [1, 1, 1, 1, 0, 0, 0, 0, 0, 0]




list1 = [10, 20, 30]
list2 = list1[:]
list2[0] = 0

print(list1)
print(list2)


list1 = [10, 20, 30]
list2 = [500,23]

list2.extend(list1)

print list2

# Output
# ------
# [500, 23, 10, 20, 30]




list1 = [100, 200, 300, 400, 500]
print(time.time())

# Version 1: slice
i = 0

while i < 10000000:

    # this is faster than extend 
    list2 = list1[:]
    i += 1

print(time.time())

i = 0
while i < 10000000:

    list2 = []
    list2.extend(list1)
    i += 1

print(time.time())


# Output
# ------

# 1395252155.968703
# 1395252160.704971   Slice  = 4.736 s
# 1395252166.045275   Extend = 5.340 s




v = """This string has
triple "quotes" on it."""

print(v)

s = "abc"

# Loop over string.
for c in s:
    print c

# Loop over string indexes.
for i in range(0, len(s)):

    print(s[i])




list_ = ["a", "b", "c"]
result = "".join(list_)
result2 = ",".join(list_)

# Display results.
print(result)
print(result2)


# Output
# ------
# abc
# a,b,c


value = "finnegans wake"

print(value.count("n"))
print(value.count("n", 0, 6))


s = "voorheesville"

if s.startswith("voo"):
    print("1")

if s.endswith("ville"):
    print("2")

if s.startswith("stuy"):
    print("3")



s = "Paris"
# Justify to left, add periods.
print(s.ljust(10, "."))

# Justify to right.
print(s.rjust(10))


# Paris.....
# Paris




value = "aabc"

# Replace a substring with another.
result = value.replace("bc", "yz")
print(result)

result = value.replace("a", "x", 1)
print(result)




value = "CAT"

if value == "cat":
    print("A") # Not reached.

if value == "CAT":
    print("B")

if str.casefold(value) == "cat":
    print("C")

if str.lower(value) == "cat":
    print("D")


# In a raw string "\" characters do not escape.
raw = r"\directory\123"
val = "\directory\123"

print(raw)
print(val)


# Output
# ------
# \directory\123
# \directoryS




# Format this number with a comma.
result = format(1000, ",")
print(result)
# 1,0000


# Align to the right of 10 chars, filling with ":" and as a string.
result = format("cat", ":>10s")
print(result)

# 1,000
# :::::::cat


# This string contains an umlaut.
value = "Düsseldorf"
print value

# Display letter with escaped umlaut.
print(ascii(value))




import string

# The letters constant is equal to lowercase + uppercase.
print(string.ascii_letters)
print(string.ascii_lowercase)
print(string.ascii_uppercase)




for digit in string.digits:
    print(digit)


print(" " in string.whitespace)
print("\n" in string.whitespace)
print("X" in string.whitespace)




items = ["one", "two", "ten", "eight"]

# Combine string list into a single string.
string_value = ",".join(items)
print(string_value)

# Separate string into a string list.
list_values = string_value.split(",")
print(list_values)




# ORD, CHR AND TRANSLATION IN PYTHON
# =====================

letters = "ABCabc123"

for letter in letters:

    # Get number that represents letter in ASCII.
    number = ord(letter)
    print(letter, "=", number)


# Output
# ------
# A = 65
# B = 66
# C = 67
# a = 97
# b = 98
# c = 99
# 1 = 49
# 2 = 50
# 3 = 51




numbers = [97, 98, 99]

for number in numbers:

    # Convert ASCII-based number to character.
    letter = chr(number)
    print(number, "=", letter)




# Generate string for translation.
initial = ""

for i in range(97, 97 + 26):
    initial += chr(i)

# Generate mapped chars for string.
translated = ""

for i in range(97, 97 + 26):
    translated += chr(i - 10)


print("INITIAL   ", initial)
print("TRANSLATED", translated)


# Create a lookup table.
table = str.maketrans(initial, translated)

# Translate this string.
value = "thank you for visiting"
result = value.translate(table)

print("BEFORE", value)
print("AFTER ", result)


# Output
# ------
# INITIAL    abcdefghijklmnopqrstuvwxyz
# TRANSLATED WXYZ[\]^_`abcdefghijklmnop
# BEFORE thank you for visiting
# AFTER  j^Wda oek \eh l_i_j_d]




def rot13(s):

    result = ""

    # Loop over characters.
    for v in s:

        # Convert to number with ord.
        c = ord(v)

        # Shift number back or forward.
        if c >= ord('a') and c <= ord('z'):

            if c > ord('m'):
                c -= 13
            else:
                c += 13

        elif c >= ord('A') and c <= ord('Z'):

            if c > ord('M'):
                c -= 13
            else:
                c += 13

        # Append to result.
        result += chr(c)

    # Return transformation.
    return result


# Test method.
print(rot13("gandalf"))
print(rot13(rot13("gandalf")))


# Output
# ------
# tnaqnys
# gandalf




# Create translation table.
table = str.maketrans("78", "12", "9")

# Translate this string.
input = "123456789"
result = input.translate(table)

# Write results.
print(input)
print(result)

# 123456789
# 12345612




# Create translation table.
trans = str.maketrans("abcdefghijklmnopqrstuvwxyz",
    "nopqrstuvwxyzabcdefghijklm")

# Apply rot13 translation.
print("gandalf".translate(trans))
print("gandalf".translate(trans).translate(trans))

# tnaqnys
# gandalf




# Display punctuation.
for c in string.punctuation:
    print("[" + c + "]")




# An input string.
name = "hey, my friend!"

for c in name:

    # See if the char is punctuation.
    if c in string.punctuation:
        print("Punctuation: " + c)




# STRIP IN PYTHON 
# ===============


# Has two leading spaces and a trailing one.
value = "  a line "

# Remove left spaces.
value1 = value.lstrip()
print("[" + value1 + "]")

# Remove right spaces.
value2 = value.rstrip()
print("[" + value2 + "]")

# Remove left and right spaces.
value3 = value.strip()
print("[" + value3 + "]")




# [a line ]
# [  a line]
# [a line]




# Has numbers on left and right, and some syntax.
value = "50342=Data,231"

# Strip all digits.
# ... Also remove equals sign and comma.
result = value.strip("0123456789=,")
print(result)




def preprocess(input):
    # String and lowercase the input.
    return input.strip().lower()


# Create a new dictionary.
lookup = {}

# Use preprocess to create key from string.
# ... Use key in the dictionary.
lookup[preprocess("  CAT")] = 10

# Get value from dictionary with preprocessed key.
print(lookup[preprocess("Cat ")])




# Input data.
s = "100200input"

print(s.lstrip("0123456789"))
print(s.lstrip("012"))




def reverse_string(value):

    characters = [c for c in value]
    characters.reverse()
    return "".join(characters)

# Test our string reversal method.
test1 = "cat"
reversed1 = reverse_string(test1)




# Open a file on the disk (please change the file path).
f = open(r"C:\files\gems.txt", "r")

# Create an empty list.
lines = []

# Convert lines into string list.
# strip right side of the  string 
for line in f.readlines():
    lines.append(line.rstrip())

# Display all elements.
for element in lines:
    print("[" + element + "]")




left = ["cat", "dog"]
right = ["bird", "fish"]

# Add two string lists together.
result = left + right

# The four elements are now in one list.
print(result)




left = ["blue", "red"]
right = ["navy", "crimson"]

# Loop over index range.
for i in range(0, len(left)):
    print(left[i], "...", right[i])

print()

# Loop over string lists with zip.
for (left_part, right_part) in zip(left, right):
    print(left_part, "...", right_part)



# blue ... navy
# red ... crimson

# blue ... navy
# red ... crimson




# Strip performance test 
# ----------------------
# Time 1.

# s = "100200input"
print(time.time())


# Specify all digits.
i = 0

while i < 10000000:
    result = s.lstrip("0123456789")
    i += 1


# Time 2.
print(time.time())

# Specify needed digits.
i = 0
while i < 10000000:

    result = s.lstrip("012")
    i += 1

# Time 3.
print(time.time())






# TEXT/ HTML PARSING IN PYTHON 
# ============================

import textwrap

value = """The image in these opening lines evidently refers to a bird
knocking itself out, in full flight, against the outer surface of a glass
pane in which a mirrored sky, with its slightly darker
tint and slightly slower cloud,
presents the illusion of continued space."""

# Wrap this text.
lis = textwrap.wrap(value, width=50)


# Print each line with 50 Characters 
for element in lis:
    print(element)




from html.parser import HTMLParser

# A class that inherits from HTMLParser.
# ... It implements two methods.
class TagParser(HTMLParser):

    def handle_starttag(self, tag, attrs):
	   # Set "tag" field to the name of the opened tag.
    	self.tag = tag

    def handle_data(self, data):
	   # Print data within currently-open tag.
	   print(self.tag + ":", data)




parser = TagParser()
parser.feed("<h1>Python</h1>" +
	    "<p>Is cool.</p>");




import re

# This string contains HTML.
v = """<p id=1>Sometimes, <b>simpler</b> is better,
but <i>not</i> always.</p>"""

# Replace HTML tags with an empty string.
result = re.sub("<.*?>", "", v)
print(result)


# Pattern details

# <      Less-than sign (matches HTML bracket).
# .*?    Match zero or more chars.
#        Match as few as possible.
# >      Greater-than (matches HTML bracket).


# This HTML string contains two comments.
v = """<p>Welcome to my <!-- awesome -->
website<!-- bro --></p>"""


# Remove HTML comments.
result = re.sub("<!--.*?-->", "", v)

print(v)
print(result)




import io

out = io.StringIO()

# Print these string values in a loop.
for i in range(0, 100):

    out.write("Value = ")
    out.write(str(i))
    out.write(" ")


# Get string and display first 20 character.
data = out.getvalue()
print(data[0:20])
out.close()




out = io.StringIO()

# Print to StringIO stream, no end char.
print("Hello", file = out, end="")
print(out.getvalue())




import xml.parsers.expat

# Will store tag names and char data.
list = []

# Create the parser.
parser = xml.parsers.expat.ParserCreate()

# Specify handlers.
parser.StartElementHandler = lambda name, attrs: list.append(name)
parser.CharacterDataHandler = lambda data: list.append(data)

# Parse a string.
parser.Parse("""<?xml version="1.0"?>
<item><name>Sam</name>
<name>Mark</name>
</item>""", True)

# Print the items in our list.
print(list)




# Performance test for output
# --------------------------- 
print(time.time())

# 1. Use StringIO.
for x in range(0, 10000):

    out = io.StringIO()

    for i in range(0, 100):

        out.write("Value = ")
        out.write(str(i))
        out.write(" ")

    # Get string.
    contents = out.getvalue()
    out.close()

    # Test first letter.
    if contents[0] != 'V':
        raise Error

print(time.time())

# 2. Use string appends
for x in range(0, 10000):

    data = ""

    for i in range(0, 100):

        data += "Value = "
        data += str(i)
        data += " "

    # Test first letter.
    if data[0] != 'V':
        raise Error

print(time.time())






# DICTIONARIES IN PYTHON 
# ======================

plants = {}

# Add three key-value tuples to the dictionary.
plants["radish"] = 2
plants["squash"] = 4
plants["carrot"] = 7

# Get syntax 1.
print(plants["radish"])

# Get syntax 2.
print(plants.get("tuna"))
print(plants.get("tuna", "no tuna found"))




lookup = {"cat": 1, "dog": 2}
# The dictionary has no fish key!
print(lookup["fish"])
# this is provide an error during compilation 




animals = {}

animals["monkey"] = 1
animals["tuna"] = 2
animals["giraffe"] = 4

# Use in.
if "tuna" in animals:
    print("Has tuna")

else:
    print("No tuna")

# Use in on nonexistent key.
if "elephant" in animals:
    print("Has elephant")

else:
    print("No elephant")

# Has tuna
# No elephant




animals = {"parrot": 2, "fish": 6}

# Use len built-in on animals.
print("Length:", len(animals))




hits = {"home": 125, "sitemap": 27, "about": 43}
keys = hits.keys()
values = hits.values()

print("Keys:")
print(keys)
print(len(keys))

print("Values:")
print(values)
print(len(values))




hits = {"home": 124, "sitemap": 26, "about": 32}
sorted_keys = sorted(hits.keys())
sorted_values = sorted(hits.values())
print(sorted_keys, sorted_values)




rents = {"apartment": 1000, "house": 1300}
rentItems = rents.items()

for rentItem in rentItems:

    print("Place:", rentItem[0])
    print("Cost:", rentItem[1])
    print("")




data = {"a": 1, "b": 2, "c": 3}

for k, v in data.items():
    print(k, v)

plants = {"radish": 2, "squash": 4, "carrot": 7}

for plant in plants:
    print(plant)


# Output 
# ------
# radish
# carrot
# squash




systems = {"mac": 1, "windows": 5, "linux": 1}

del systems["windows"]
print(systems)




# First dictionary.
pets1 = {"cat": "feline", "dog": "canine"}
pets2 = {"dog": "animal", "parakeet": "bird"}
pets1.update(pets2) # append and update the first dictionary 

print(pets1)
print(pets2)


# Output 
# ------
# {'parakeet': 'bird', 'dog': 'animal', 'cat': 'feline'}
# {'dog': 'animal', 'parakeet': 'bird'}




original = {"box": 1, "cat": 2, "apple": 5}

modified = original.copy()
modified["cat"] = 200
modified["apple"] = 9

print(original)
print(modified)
# {'box': 1, 'apple': 5, 'cat': 2}
# {'box': 1, 'apple': 9, 'cat': 200}




keys = ["bird", "plant", "fish"]
d = dict.fromkeys(keys, 5)
print(d)


# Create list of tuple pairs.
# ... These are key-value pairs.
pairs = [("cat", "meow"), ("dog", "bark"), ("bird", "chirp")]

# Convert list to dictionary.
lookup = dict(pairs)

# Test the dictionary.
print(lookup.get("dog"))
print(len(lookup))




letters = "abcabcdefghi"
frequencies = {}

for c in letters:
    frequencies[c] = frequencies.get(c, 0) + 1

for f in frequencies.items():
    print(f)




# Use dict to create empty dictionary.
values = dict()
values["cat"] = 1

print(values.get("cat"))




values = {"cat": 1, "bird": 2}

copy = dict(values)
copy["cat"] = 400

print(values)
print(copy)




values = [("cat", 1), ("bird", 200)]
lookup = dict(values)

print(values)
print(lookup.get("cat"))
print(lookup.get("bird"))




# Create a dictionary with named arguments.
animals = dict(bird=1, dog=2, fish=9)

print(animals.get("bird"))
print(animals.get("dog"))
print(animals.get("fish"))


# Dictionary Performance Test
# ---------------------------


systems = {"mac": 1, "windows": 5, "linux": 1}

# Time 1.
print(time.time())

# Get version.
i = 0
v = 0
x = 0


while i < 10000000:

    x = systems.get("windows", -1)

    if x != -1:
        v = x

    i += 1


# Time 2.
print(time.time())

# In version.
i = 0
v = 0

while i < 10000000:

    if "windows" in systems:

        v = systems["windows"]
    i += 1

print(time.time())






# LIST IN PYTHON 
# ==============


list = []
list.append(1)
list.append(2)
list.append(6)
list.append(3)

print(list)




list = ["dot", "perls"]

# move insert in index of 1 and move the 
# element of index 1 to higher index 
list.insert(1, "net")

print(list)
# ['dot', 'net', 'perls']


# Two lists.
a = [1, 2, 3]
b = [4, 5, 6]


# Add all elements in list "b" to list "a."
a.extend(b)
print(a)
# [1, 2, 3, 4, 5, 6]


animals = []
animals.append("cat")
animals.append("dog")

count = len(animals)

# Display the list and the length.
print(animals)
print(count)




items = ["book", "computer", "keys", "mug"]

if "computer" in items:
    print(1)

if "atlas" in items:
    print(2)

else:
    print(3)

if "marker" not in items:
    print(4)




list = [400, 500, 100, 2000]

# Reversed.
list.reverse()
print(list)

# Sorted.
list.sort()
print(list)

# Sorted and reversed.
list.reverse()
print(list)


# Output
# ------
# [2000, 100, 500, 400]
# [100, 400, 500, 2000]
# [2000, 500, 400, 100]


def lastchar(s):
    return s[-1]

# Input.
values = ["abc", "bca", "cab"]

# Sort by last character in the strings.
values.sort(key=lastchar)
print(values)

# Sort by the second character in the strings.
# ... Use a lambda expression.
values.sort(key=lambda s: s[1])
print(values)




names = ["Tommy", "Bill", "Janet", "Bill", "Stacy"]

# list doesn't have the discard functionality 
names.remove("Bill")
print(names)

del names[2:]
print(names)

del names[:1]
print(names)




names = ['a', 'a', 'b', 'c', 'a']
value = names.count('a')
print(value)




# Input list.
values = ["uno", "dos", "tres", "cuatro"]

# Locate string.
n = values.index("dos")
print(n, values[n])

# Locate another string.
n = values.index("tres")
print(n, values[n])

# Handle nonexistent string.
try:
    n = values.index("?")
    # Not reached.
    print(n)

except:
    # Value not found.
    print("Not found")


# An input list.
elements = ["spider", "moth", "butterfly", "lizard"]

# Use simple for-loop.
for element in elements:
    print(element)




elements = [0, 10, 20, 30]

for i in range(1, len(elements)):

    a = elements[i - 1]
    b = elements[i]
    print(a, b)    




# Transform string into HTML.
def html(s):
    return "<b>" + s.capitalize() + "</b>"

input = ["rabbit", "mouse", "gorilla", "giraffe"]
list = [html(x) for x in input]

print(list)




list = []
f = open("C:\\programs\\file.txt", "r")

for line in f.readlines():
    list.append(line.strip())

print(list)
list2 = list1[:]


list = [10, 20, 30]
res = str.format("The values are {v[0]}, {v[1]} and {v[2]}", v=list)
print(res)


items = [False, None, True]

if not all(items):
    print(False)

items = [10, 20, 30]

if all(items):
    print(True)


# Output 
# ------
# False
# True




elements = [False, None, "Pomegranate", 0]

if any(elements):
    print(True)

elements = [0, 0, False]

if not any(elements):
    print(False)




cresult = list(filter(lambda n: n > 0, numbers))
print(result)






# SET IN PYTHON 
# =============


# Create a set.
items = {"arrow", "spear", "arrow", "arrow", "rock"}

# Print set.
print(items)
print(len(items))

if "rock" in items:
    print("Rock exists")

if "clock" not in items:
    print("Cloak not found")



items = set()

items.add("cat")
items.add("dog")
items.add("gerbil")

print(items)

# convert a list to a set 
numbers = set([10, 20, 20, 30, 40, 50])
print(numbers)




numbers1 = {1, 3, 5, 7}
numbers2 = {1, 3}

# Is subset.
if numbers2.issubset(numbers1):
    print("Is a subset")


# Is superset.
if numbers1.issuperset(numbers2):
    print("Is a superset")

# Intersection of the two sets.
print(numbers1.intersection(numbers2))




# Two sets.
set1 = {1, 2, 3}
set2 = {6, 5, 4, 3}

# Union the sets.
set3 = set1.union(set2)
print(set3)




a = {"new york", "connecticut", "new jersey"}
b = {"connecticut", "pennsylvania", "maine"}

# Subtract.
c = a - b
print(c)

# Difference.
c = a.difference(b)
print(c)

# Subtract in opposite order.
c = b - a
print(c)

# Difference in opposite order.
c = b.difference(a)
print(c)




# {'new jersey', 'new york'}
# {'new jersey', 'new york'}
# {'pennsylvania', 'maine'}
# {'pennsylvania', 'maine'}


animals = {"cat", "dog", "parrot", "walrus"}
print animals

# Discard nonexistent element, nothing happen/ no error
# discard doesn't work with list 
animals.discard("zebra")
print(animals)

# Discard element that exists.
animals.discard("cat")
print(animals)

# Remove element that exists.
animals.remove("parrot")
print(animals)

# Remove causes an error if the element is not found.
animals.remove("buffalo")




# This dictionary contains key-value pairs.
dictionary = {"cat": 1, "dog": 2, "bird": 3}
print(dictionary)

# This set contains just the dictionary's keys.
keys = set(dictionary)
print(keys) # <type 'set'>

# {'bird': 3, 'dog': 2, 'cat': 1}
# {'cat', 'bird', 'dog'}




values = {10, 20, 30}
result = map(lambda x: x * 100, values)

for value in result:
    print(value)




# Strings to put in frozenset.
keys = ["bird", "plant", "fish"]

# Create frozenset.
f = frozenset(keys)
print(f)

# Cannot add to frozenset.
try:
    f.add("cat")

except AttributeError:
    print("Cannot add")

# However, can use frozenset as key to dictionary.
d = {}
d[f] = "awesome"
print(d)




# frozenset({'plant', 'bird', 'fish'})
# Cannot add
# {frozenset({'plant', 'bird', 'fish'}): 'awesome'}




# Create 3 frozensets.
colors1 = frozenset(["blue", "red"])
colors2 = frozenset(["orange", "black"])
colors3 = frozenset(["black", "white"])


# Create a frozenset of two frozensets.
possible = frozenset([colors1, colors2])

# This frozenset is included.
if colors1 in possible:
    print("Possible:", colors1)

# This one is not.
if colors3 not in possible:
    print("Not possible:", colors3)




# This example will not compile and will provide "objects are unhashable" error 
example = {1, 2, 3}

# TypeError: unhashable type: 'set'
example2 = {example, {5, 6, 7}}






# MAP IN PYTHON 
# =============


# An input list.
items = [1, 2, 3]

# Apply lambda to all elements with map.
for r in map(lambda x: x + 1, items):
    print r




# Original list.
items = [7, 8, 9]

# Map into a new list.
items2 = list(map(lambda z: z * 2, items))

print(items)
print(items2)




names = ["San Jose", "San Francisco", "Santa Fe", "Houston"]
count = sum(map(lambda s: s.startswith("San"), names))

print(count)


# Two input lists.
a = [1, 2, 3]
b = [2, 3, 4, 5]

# Multiply elements of two lists together.
result = list(map(lambda x, y: x * y, a, b))

# Three elements are present.
print(result)
# [2, 6, 12]






# TUPLES IN PYTHON
# ================


a = ()
b = ("one",)
c = ("one", "two")

print(a)
print(len(a))

print(b)
print(len(b))

print(c)
print(len(c))




tuple = ('cat', 'dog', 'mouse')

# This causes an Error/ not possible 
tuple[0] = 'feline'




# Create packed tuple.
pair = ("dog", "cat")
(key, value) = pair

# Display unpacked variables.
print(key)
print(value)




# A trailing comma indicates a tuple.
one_item = "cat",

# A tuple can be specified with no parentheses.
two_items = "cat", "dog"

print(one_item)
print(two_items)




checks = (10, 20, 30)

# Add two tuples.
more = checks + checks
print more

# Multiply tuple.
total = checks * 3
print total


# (10, 20, 30, 10, 20, 30)
# (10, 20, 30, 10, 20, 30, 10, 20, 30)




# Max and min for strings.
friends = ("sandy", "michael", "aaron", "stacy")

print(max(friends))
print(min(friends))

# Max and min for numbers.
earnings = (1000, 2000, 500, 4000)

print(max(earnings))
print(min(earnings))




pair = ("dog", "cat")

if "cat" in pair:
    print("Cat found")

if "bird" not in pair:
    print("Bird not found")




values = (1, 3, 5, 7, 9, 11, 13)

print(values[:])
print(values[1:])
print(values[:1])
print(values[2:4])




# Three-item tuple.
items = ("cat", "dog", "bird")

# Get index of element with value "dog".
index = items.index("dog")
print(index, items[index])




values = (1, 2, 2, 3, 3, 3)
print(values.count(1))
print(values.count(3))
print(values.count(100))




# A tuple with two numbers.
pair = (1, 2)

dict = {}
dict[pair] = "Python"

print(dict[(1, 2)])




# Tuple containing unsorted odd numbers.
odds = (9, 5, 11)

# Convert tuple to list and sort.
list = list(odds)
list.sort()
print(list)

# Convert back list to tuple.
sorted_odds = tuple(list)
print(sorted_odds)




values = ["meow", "bark", "chirp"]

# Use enumerate on list.
for pair in enumerate(values):
    # The pair is a 2-tuple.
    print(pair)

# Unpack enumerate's results in for-loop.
for index, value in enumerate(values):

    # We have already unpacked the tuple.
    print(str(index) + "..." + value)




value = "abcdefgh"
pairs = []

# use space of 2 between the range 
space = 2

for i in range(1, len(value), space):

    one = value[i - 1]
    two = value[i]
    pairs.append((one, two))

# Display list of tuple pairs.
for pair in pairs:
    print(pair)

# ('a', 'b')
# ('c', 'd')
# ('e', 'f')
# ('g', 'h')






# NAMEDTUPLE IN PYTHON  
# ====================


import collections

Emp = collections.namedtuple("Employee", ["id", "title", "salary"])
e = Emp(1, "engineer", 10000)

print(e)
print("Title is", e.title)


# Employee(id=1, title='engineer', salary=10000)
# Title is engineer




# A namedtuple type.
Style = collections.namedtuple("Style", ["color", "size", "width"])
values = ["red", 10, 15]

# Make a namedtuple from the list.
tuple = Style._make(values)
print(tuple)


# Output
# ------
# Style(color='red', size=10, width=15)




# 2 - DIMENTIONAL STORAGE 
# =====================




# Create a list.
elements = []

# Append empty lists in first two indexes.
elements.append([])
elements.append([])

# Add elements to empty lists.
elements[0].append(1)
elements[0].append(2)

elements[1].append(3)
elements[1].append(4)

# Display top-left element.
print(elements[0][0])
print(elements)

for row in elements:
    
    for column in row:
        print(column, end="")
    
    print(end="\n")




# A jagged list.
values = [[10, 20], [30, 40, 50, 60, 70]]

for value in values:
    # Print each row's length and its elements.
    print(len(value), value)




def get_element(elements, x, y):
    # Get element with two coordinates.
    return elements[x + (y * 10)]

def set_element(elements, x, y, value):
    # Set element with two coordinates.
    elements[x + (y * 10)] = value

# Create a list of 100 elements.
elements = []

for i in range(0, 100):
    elements.append(0)


i = 0
for x in range(0, 10):

    for y in range(0, 10):

        set_element(elements, x, y, i)
        i += 1


print(get_element(elements, 0, 0))
print(get_element(elements, 0, 9))
print(get_element(elements, 9, 0))
print(get_element(elements, 9, 9))






# ARRAY IN PYTHON 
# ===============


from array import array


# start a new int arary 
a = array("i", [10, 20, 30])

for value in a:
    print(value)




# Create a Unicode char array.
a = array("u", "python")

# Display letters in array.
for letter in a:
    print(letter)

# Convert array to a list.
# ... Then join it.
s = "".join(a.tolist())
print(s)




# new int array.
a = array("i")

# Append three integers.
a.append(100)
a.append(200)
a.append(300)

# Insert an integer at index 1.
a.insert(1, 900)

# Remove this element.
a.remove(200)

# Count elements with this value.
a.count(900)
print(a)




# Create an int array using int list 
list = list(range(0, 50))
arr = array("i", list)






# CONVERT IN PYTHON 
# =================

vegetables = ("carrot", "squash", "onion")

# Convert tuple to list.
veg2 = list(vegetables)
veg2.append("lettuce")

# Print results.
print(vegetables)
print(veg2)




tup = ("rabbit", "mouse", "gorilla", "giraffe")

s = "".join(tup)
print(s) # type will be String 

s = "/".join(tup)
print(s)




vegetables = {"carrot": 1, "squash": 2, "onion": 4}
items = list(vegetables.items()) # create a list using tuples 

for item in items:
    print(len(item), item)




floating = 1.23456

print(floating)
print(int(floating))


# 1.23456
# 1




# Convert number to string.
number = 123
value = str(number)

# Print the string and its character count.
print(value)
print(len(value))

# Convert string to number.
number2 = int(value)

# Print the number and add one.
print(number2)
print(number2 + 1)




class Test:

    def __init__(self):

        self.size = 1
        self.name = "Python"

    def __repr__(self):

        # Return a string.
        return "Size = " + str(self.size) + ", Name = " + str(self.name)

t = Test()

# Str and repr will both call into __repr__.
s = str(t)
r = repr(t)

# Display results.
print(s)
print(r)

# Size = 1, Name = Python
# Size = 1, Name = Python




value = "cat"
list = [c for c in value]
print list


# Output
# ------
# ['c', 'a', 't']




# convert from string to bytes
value = "carrot"
data = bytes(value, "ascii")
print(data)


# convert bytes into string with decode.
original = data.decode("ascii")
print(original)


# Output
# b'carrot'
# carrot




def bytestomegabytes(bytes):
    return (bytes / 1024) / 1024

def kilobytestomegabytes(kilobytes):
    return kilobytes / 1024


# Convert 100000 bytes to megabytes.
megabytes1 = bytestomegabytes(100000)
print(100000, "bytes =", megabytes1, "megabytes")

# 1024 kilobytes to megabytes.
megabytes2 = kilobytestomegabytes(1024)
print(1024, "kilobytes =", megabytes2, "megabytes")






# LAMBDA IN PYTHON 
# ================

numbers = [10, 20, 30]

# Use list comprehension to multiply all numbers by 10.
# ... They are placed in a new list.
result = [n * 10 for n in numbers]
print result

result = [n * 10 for n in filter(lambda n: n > 10, numbers)]
print(result)

def apply(f, n):
    print(f(n))

# Create two lambdas.
square = lambda n: n * n
cube = lambda n: n * n * n

# Pass lambdas to apply method.
apply(square, 4)
apply(cube, 3)




# Assign variable to lambda expression.
x = lambda: sum(range(1, 4))

# Invoke lambda expression.
y = x()
print(y)




# This lambda has a side effect.
# ... Print returns None.
p = lambda x: print(x)

p("Hello")
p("World")




add_two = lambda n: n + 2
multiply_add_two = lambda n: add_two(n * 2) # Call lambda in lambda.

print(multiply_add_two(3))
print(multiply_add_two(5))




# Method.
def square1(n):
    return n ** 2

# Lambda method.
square2 = lambda n: n ** 2

print(time.time())

# Use def method.
i = 0

while i < 10000000:
    square1(1)
    i += 1

print(time.time())

# Use lambda method.
i = 0
while i < 10000000:

    square2(1)
    i += 1

print(time.time())




line1 = "A cat, a dog  "
line2 = "  a bird, a mountain"

# Use X as an alias for two methods.
x = lambda s: s.strip().upper()

# Call the lambda to shorten the program's source.
line1b = x(line1)
line2b = x(line2)

print(line1b)
print(line2b)






# NONE IN PYTHON
# ==============


# Get length of this string.
s = "value"
print(len(s))

# Cannot get length of None.
s = None
print(len(s))




def test(v):

    # Test for None.
    # ... Print -1 for length if None.
    if v != None:
        print(len(v))

    else:
        print(-1)

# Use None argument.
test(None)

# Use string argument.
test("hello")


items = {"cat" : 1, "dog" : 2, "piranha" : 3}

# Get an element that does not exist and return no error/ None 
v = items.get("giraffe")

# we will get "Not found"
if v == None:
    print("Not found")

print items.get("dog") # 2 
# dict doesn't have the find option 


value = "gerbil"

if not value:
    print("A") # Not reached.

value = None

if not value:
    print("B") # "Not" matches None value.




# SUBSTRING IN PYTHON 
# ===================


s = "abcdefghijklmnopqrs"

# Loop over some indexes.
for n in range(2, 4):

    # Print slices.
    print(n, s[n])
    print(n, s[n:n + 2])
    print(n, s[n:n + 3])
    print(n, s[n:n + 4:2])
    print(n, s[n:n + 6:2])


value = "web"

# Loop over every index in string.
for index in range(0, len(value)):

    # Get one-character substring.
    char = value[index]

    # Display substring.
    print(char)

    # Test substring.
    if char == "w":
        print(True)




def between(value, a, b):

    # the index it will find `a` in value 
    pos_a = value.find(a)

    if pos_a == -1: 
        return ""

    pos_b = value.rfind(b)

    if pos_b == -1: 
        return ""

    adjusted_pos_a = pos_a + len(a)
    
    if adjusted_pos_a >= pos_b: 
        return ""
    
    return value[adjusted_pos_a:pos_b]


def before(value, a):

    pos_a = value.find(a)

    if pos_a == -1: 
        return ""
    
    return value[0:pos_a]


def after(value, a):

    pos_a = value.rfind(a)
    
    if pos_a == -1: 
        return ""

    adjusted_pos_a = pos_a + len(a)
    
    if adjusted_pos_a >= len(value): 
        return ""
    return value[adjusted_pos_a:]


# Test the methods with this literal.
test = "DEFINE:A=TWO"
print(between(test, "DEFINE:", "="))
print(between(test, ":", "="))

print(before(test, ":"))
print(before(test, "="))

print(after(test, ":"))
print(after(test, "DEFINE:"))
print(after(test, "="))


# Output
# ------
# A
# A
# DEFINE
# DEFINE:A
# A=TWO
# A=TWO
# TWO






# RANDOM IN PYTHON 
# ================


import random

i = 0

while i < 10:

    # Get random number in range 0 through 9.
    n = random.randint(0, 9)
    print(n)
    i += 1




# Use random.choice on a list.
arr = [1, 5, 9, 100]
n = random.choice(arr)

# Use random.choice on a tuple.
tup = (2, 4, 6)
y = random.choice(tup)

# Display.
print(n, y)




# This string literal contains all lowercase ASCII letters.
values = "abcdefghijklmnopqrstuvwxyz"

for i in range(0, 10):

    # random.choice returns a random character.
    letter = random.choice(values)
    print(letter)




# Create a list of words.
words = []

words.append("hello,")
words.append("cat,")
words.append("food")
words.append("buy")
words.append("free")
words.append("click")
words.append("here")


# Create sentence of five words.
result = ""

for i in range(0, 5):
    result += random.choice(words) + " "

# Fix trailing punctuation and capitalize.
result = result.strip(" ,")
result = result.capitalize()
result += "!"

print(result)






# REGEX IN PYTHON 
# ===============


import re


# Pattern:
# This uses metacharacters to describe what strings can be matched. 
# The "\w" means "word character." The plus means "one or more."


# Pattern: (d\w+)\W(d\w+)

# d        Lowercase letter d.
# \w+      One or more word characters.
# \W       A non-word character.


# Sample strings.
list = ["dog dot", "do don't", "dumb-dumb", "no match"]

# Loop.
# match just try to the first starting point 
for element in list:

    # Match if two words starting with letter d.
    m = re.match("(d\w+)\W(d\w+)", element)

    # See if success.
    if m:
        print(m.groups())


# Output
# ------
# ('dog', 'dot')
# ('do', 'don')
# ('dumb', 'dumb')




# Search scans through the input string and tries to match at any location. 
# Input.
value = "voorheesville"

m = re.search("(vi.*)", value)

if m:
    # This is reached.
    print("search:", m.group(1))

m = re.match("(vi.*)", value)
if m:
    # This is not reached.
    print("match:", m.group(1))


# Output
# ------
# search: ville
# Pattern details

# Pattern: (vi.*)

# vi       The lowercase letters v and i together.
# .*       Zero or more characters of any type.




# Split. The re.split() method accepts a pattern argument. 
# This pattern specifies the delimiter. 

# Input string.
value = "one 1 two 2 three 3"

# Separate on one or more non-digit characters.
result = re.split("\D+", value) # type is list 

# Print results.
for element in result:
    print(element)


# Output
# ------
# 1
# 2
# 3

# Pattern details
# Pattern: \D+
# \D+      One or more non-digit characters.




# Findall. This is similar to split(). Findall accepts a pattern that 
# indicates which strings to return in a list. It is like split() but 
# we specify matching parts, not delimiters.


# Input.
value = "abc 123 def 456 dot map pat"

# Find all words starting with d or p.
list = re.findall("[dp]\w+", value)

# Print result.
print(list)


# Output
# ------
# ['def', 'dot', 'pat']

# Pattern details

# Pattern: [dp]\w+

# [dp]     A lowercase d, or a lowercase p.
# \w+      One or more word characters.


value = "123 456 7890"

# Loop over all matches found.
for m in re.finditer("\d+", value):

    print(m.group(0))
    print("start index:", m.start())


# Output
# ------
# 123
# start index: 0
# 456
# start index: 4
# 7890
# start index: 8




# For the start, we use the character "^" and for the end, we use the "$" sign
# The match method tests from the leftmost part of the string. So to test the 
# end, we use ".*" to handle these initial characters.

list = ["123", "4cat", "dog5", "6mouse"]

for element in list:

    # See if string starts in digit.
    m = re.match("^\d", element)
    if m:
        print("START:", element)

    # See if string ends in digit.
    m = re.match(".*\d$", element)
    if m:
        print("  END:", element)

# Output
# ------
# START: 123
#   END: 123
# START: 4cat
#   END: dog5
# START: 6mouse

# Pattern details
# ---------------
# ^\d     Match at the start, check for single digit.
# .*\d$   Check for zero or more of any char.
#         Check for single digit.
#         Match at the end.




# check the repeats of the patterns 
values = ["cat100", "---200", "xxxyyy", "jjj", "box4000", "tent500"]

for v in values:

    # Require 3 letters OR 3 dashes.
    # ... Also require 3 digits.
    m = re.match("(?:(?:\w{3})|(?:\-{3}))\d\d\d$", v)

    if m:
        print("  OK:", v)

    else:
        print("FAIL:", v)


# Output
# ------
# OK: cat100
# OK: ---200
# FAIL: xxxyyy
# FAIL: jjj
# FAIL: box4000
# FAIL: tent500

# Pattern details
# ---------------
# (?:    The start of a non-capturing group.
# \w{3}  Three word characters.
# |      Logical or: a group within the chain must match.
# \-     An escaped hyphen.
# \d     A digit.
# $      The end of the string.




# A string.
name = "Clyde Griffiths"

# Match with named groups.
m = re.match("(?P<first>\w+)\W+(?P<last>\w+)", name)

# Print groups using names as id.
if m:
    print(m.group("first"))
    print(m.group("last"))


# Output
# ------
# Clyde
# Griffiths

# Pattern details
# ---------------
# Pattern:         (?P<first>\w+)\W+(?P<last>\w+)

# (?P<first>\w+)   First named group.
# \W+              One or more non-word characters.
# (?P<last>\w+)    Second named group.




name = "Roberta Alden"

# Match names.
m = re.match("(?P<first>\w+)\W+(?P<last>\w+)", name)

if m:

    # Get dict.
    d = m.groupdict()

    # Loop over dictionary with for-loop.
    for t in d:
        print("  key:", t)
        print("value:", d[t])


# Output
# ------
# key: last
# value: Alden
# key: first
# value: Roberta




# For searching with no pattern, prefer the in-operator than regex 

input = "max"

if "x" in input:
    print(1)

if re.search("x", input):
    print(2)

print(time.time())

# Version 1: in.
c = 0
i = 0
while i < 1000000:

    if "x" in input:
        c += 1
    i += 1

print(time.time())

# Version 2: re.search.
i = 0

while i < 1000000:

    if re.search("x", input):
        c += 1
    i += 1

print(time.time())



# Output
# ------
# 1
# 2
# 1381081435.177
# 1381081435.615 [in        = 0.438 s]
# 1381081437.224 [re.search = 1.609 s]




def stringmatch(s):

    # Check for "ca+t" with if-statements and loop.
    if len(s) >= 3 and s[0] == 'c' and s[len(s) - 1] == 't':

        for v in range(1, len(s) - 2):

            if s[v] != 'a':
                return False

        return True
    return False


def stringmatch_re(s):

    # Check for "ca+t" with re.
    m = re.match(r"ca+t", s)
    if m:
        return True
    return False


# Test these strings.
tests = ["ct", "cat", "caaat", "dog", "car"]
for t in tests:
    print(stringmatch(t), stringmatch_re(t), t)


# Output
# ------
# False False ct
# True True cat
# True True caaat
# False False dog
# False False car




# count the number of non-white space patterns 
def wordcount(value):

    # Find all non-whitespace patterns.
    list = re.findall("(\S+)", value)
    # Return length of resulting list.
    return len(list)

value = "To be or not to be, that is the question."
print(wordcount(value))

value = "Stately, plump Buck Mulligan came from the stairhead"
print(wordcount(value))

value = ""
print(wordcount(value))




# Re.sub. In regular expressions, sub stands for substitution. The re.sub 
# method applies a method to all matches. It evaluates a pattern, and for 
# each match calls a method (or lambda).


def multiply(m):

    # Convert group 0 to an integer.
    v = int(m.group(0))
    # Multiply integer by 2.
    # ... Convert back into string and return it.
    return str(v * 2)

# Use pattern of 1 or more digits.
# ... Use multiply method as second argument.
result = re.sub("\d+", multiply, "10 20 30 40 50")
print(result)


# Output
# 20 40 60 80 100




# An example string.
v = "running eating reading"

# Replace words starting with "r" and ending in "ing"
# ... with a new string.
v = re.sub(r"r.*?ing", "ring", v)
print(v)


# Output
# ring eating ring




def add(m):

    # Convert.
    v = int(m.group(0))
    # Add 2.
    return str(v + 1)

# Call re.subn.
result = re.subn("\d+", add, "1 2 3 4 5")

print("Result string:", result[0])
print("Number of substitutions:", result[1])

# Output
# ------
# Result string: 11 21 31 41 51
# Number of substitutions: 5




# The input string.
input = "laugh eat sleep think"

# Use lambda to add "ing" to all words.
result = re.sub("\w+", lambda m: m.group(0) + "ing", input)

# Display result.
print(result)


# Output
# ------
# laughing eating sleeping thinking




plants = {"flower": 1, "tree": 1, "grass": 1}

def modify(m):

    v = m.group(0)

    # If string is in dictionary, return different string.
    if v in plants:
        return "PLANT"

    # Do not change anything.
    return v

# Modify to remove all strings within the dictionary.
result = re.sub("\w+", modify,
    "bird flower dog fish tree")

print(result)

# Output
# ------
# bird PLANT dog fish PLANT






# MATH IN PYTHON 
# ==============


import math

# Fractional number.
n = 100.7

# Absolute value.
print(math.floor(n))
print(math.ceil(n))


# Input list.
values = [0.9999999, 1, 2, 3]

# Sum values in list.
r = sum(values)
print(r)

# Sum values with fsum.
r = math.fsum(values)
print(r)




# Truncate this value.
value1 = 123.45
truncate1 = math.trunc(value1)
print(truncate1)
# 123

# Truncate another value.
value2 = 345.67
truncate2 = math.trunc(value2)
print(truncate2)




# Use math.pow method.
a = math.pow(2, 3)
b = 2 ** 3

print(a)
print(b)




value1 = 9
value2 = 16
value3 = 100

print(math.sqrt(value1))
print(math.sqrt(value2))
print(math.sqrt(value3))




# This returns the value of e.
print(math.e)
print(math.pi)




# Negative number.
n = -100.5
print(abs(n))
print(abs(100.5))
print(abs(0))




number = 1.23456

# Use round built-in.
# ... This rounds up or down depending on the last digit.
print(round(number))
print(round(number, 0))
print(round(number, 1))
print(round(number, 2))
print(round(number, 3))




number = 1.1

# Use math.ceil to round up.
result = math.ceil(number)
print(result)
print(round(number))




values = [10, 20, 5]

# The sum of the 3 values is this number.
result = sum(values)
print(result)

result = sum(values, 1000)
print(result)




# ... It cannot be summed with sum.
values = [10, [20, 30]]

# This will cause a TypeError.
result = sum(values)
print(result)






# ERROR IN PYTHON 
# =============== 

try:
    x = 1 / 0

except ZeroDivisionError:
    print("Tried to divide by zero")




def mistake(name):
    # Raise an example with custom string.
    raise Exception(name + " caused exception")

# Call method.
mistake("Voorheesville")




try:
    # This causes an exception.
    f = open("abc")

except:

    print("Except hit")
    # Raise the exception again.
    raise


while True:

    # Read int from console.
    denominator = int(input())

    # Use int as denominator.
    try:
        i = 1 / denominator

    except:
        print("Error")

    else:
        print("OK")




try:
    # An error occurs.
    x = 1 / 0

except:
    # Except clause:
    print("Error encountered")

finally:
    # Finally clause:
    print("Finally clause reached")




try:
    f = open("does-not-exist")

except IOError as err:
    
    # We can use IOError as an instance.
    print("Error:", err)
    print("Number:", err.errno)




def outer(n):
    return 100 / n

def inner(n):
    return outer(n)

# This causes an error.
# ... Python provides a stack trace that shows the call stack.
inner(0)




print(time.time())

# Version that causes exception.
v = 0
i = 0

while i < 10000000:

    try:
        x = 10 / v

    except ZeroDivisionError:
        x = 0
    i += 1

print(time.time())

# Version that uses if-check.
v = 0
i = 0

while i < 10000000:

    if v != 0:
        x = 10 / v
    else:
        x = 0
    i += 1

print(time.time())


# Output
# ------
# 1346178493.989
# 1346178499.7   (Version 1 = 5.711 s)
# 1346178501.788 (Version 2 = 2.088 s)




# AssertionError
# --------------

value = 10
value2 = 100

# Assert if this expression is not true.
assert(value + value2 != 110)
print("DONE")


# IndentationError
# ----------------

# An incorrectly-formatted for-loop.
for n in range(0, 1):

    print(n)
        print(n)

# Output
# ------
#     print(n)
#     ^
# IndentationError: unexpected indent


# IOError
# -------

# An invalid path.
name = "/nope.txt"

# Attempt to open the file.
with open(name) as f:
    print(f.readline())


# Output
# ------
# Traceback (most recent call last):
#   File "...", line 7, in <module>
#     with open(name) as f:
# IOError: [Errno 2] No such file or directory: '/nope.txt'


# A file that does not exist.
name = "/nope.txt"


# See if the path exists.
if os.path.exists(name):

    # Open the file.
    with open(name) as f:
    print(f.readline())


try:

    # Does not exist.
    name = "/nope.txt"

    # Attempt to open it.
    with open(name) as f:
    print(f.readline())


except IOError:
    # Handle the error.
    print("An error occurred")


# KeyError
# --------

# Create dictionary with three entries.
values = {"a" : 1, "b" : 2, "c" : 3}

# Using the value directly can cause an error.
try:
    print(values["d"])

except KeyError:
    print("KeyError encountered")

# We use get to safely get a value.
print(values.get("d"))

# Output
# ------
# KeyError encountered
# None


# NameError: Import
# -----------------


from datetime import date
# from datetime import timedelta ## timedelta is not imported 


today = date.today()
yesterday = today - timedelta(days=1)
print(yesterday)

# Output
# ------
# Traceback (most recent call last):
#   File "C:\programs\file.py", line 8, in <module>
#     yesterday = today - timedelta(days=1)
# NameError: name 'timedelta' is not defined


# SyntaxError
# -----------

cats = ["Fluffy", "Mildred", "Joe"]

for cat in cats
    print(cat)

# Output
# ------
#   File "C:\programs\file.py", line 5
#     for cat in cats
#           ^
# SyntaxError: invalid syntax






# UPPER / LOWER IN PYTHON 
# =======================


value = "Tree"

# Uppercase the string.
x = value.upper()
print(x)

# Lowercase the string.
y = value.lower()
print(y)


# Output
# ------
# TREE
# tree




value1 = "ABC123"
value2 = "abc123"

# Method can be used in an if-statement.
if value1.isupper():
    print(1)

# Call methods on both strings.
print(value1.isupper())
print(value2.isupper())

print(value1.islower())
print(value2.islower())




# An input string.
s = "perls"

# Capitalize and assign.
s = s.capitalize()
print(s)




value = "the unbearable lightness of being"

# Convert to title case.
result = value.title()
print(result)




value1 = "A Car"
value2 = "A car"
value3 = "a Car"
value4 = "A123"
value5 = "a123"

# Test istitle method.
print(value1.istitle())
print(value2.istitle())
print(value3.istitle())
print(value4.istitle())
print(value5.istitle())




value = "intuitive"

print(time.time())

# Version 1: lower.
i = 0

while i < 10000000:

    v = value.lower()
    i += 1

print(time.time())

# Version 2: islower and lower.
i = 0

while i < 10000000:

    if not value.islower(): 
        v = value.lower()
    
    i += 1

print(time.time())






# FIND IN PYTHON 
# ==============


value = "cat picture is cat picture"

# Find first index of this string.
i = value.find("picture")
print(i)

# Find first index (of this string) after previous index.
b = value.find("picture", i + 1)
print(b)




value = "ralph waldo emerson"
i = value.find("python")

if i != -1:
    # Not reached.
    print("String found")

else:
    print("String not found")




value = "cat picture is cat picture"
location = -1

while True:

    # Advance location by 1.
    location = value.find("picture", location + 1)

    # Break if not found.
    if location == -1: 
        break

    # Display result.
    print(location)




value = "cat picture is cat picture"

# Get rightmost index of this string.
i = value.rfind("picture")
print(i)

# Get rightmost index within this range of characters.
# ... We search the left four words.
b = value.rfind("picture", 0, i - 1)
print(b)




value = "cat picture is cat picture"

# Start with length of string.
i = len(value)

while True:

    # Find rightmost string in this range.
    i = value.rfind("picture", 0, i)

    # Check for not found.
    if i == -1: break
    print(i)




value = "abc def"

# Use index method.
i = value.index("def")
print(i)

# This causes an exception.
b = value.index("xyz")




filename = "cat.png"

# See if the string contains this substring.
if ".png" in filename:
    print("Is PNG image")

# This is evaluated to true.
if ".jpg" not in filename:
    print("Is NOT JPG image")






# CLASS IN PYTHON 
# ===============


class Box:

    def area(self):
        return self.width * self.height

    def __init__(self, width, height):
        self.width = width
        self.height = height

# Create an instance of Box.
x = Box(10, 2)

# Print area.
print(x.area())




class A:
    def width(self):
        print("a, width called")

class B(A):
    def size(self):
        print("b, size called")

# Create new class instance.
b = B()
# Call method on B.
b.size()
# Call method from base class.
b.width()




class A:

    # Init.
    def __init__(self, value):
        self.__value = value

    # Two-underscore name.
    __value = 0

# Create the class.
a = A(5)

# [1] Cannot use two-underscore name.
# print(a.__value)

# [2] Must use mangled name.
print(a._A__value)








class A:
    def hello(self):
        print("A says hello")

class B(A):
    def hello(self):
        print("B says hello")

# Use the derived class.
b = B()
b.hello()

# See if B inherits from A.
if issubclass(B, A):
    print(1)

# See if A inherits from B.
if issubclass(A, B):
    # Not reached.
    print(2)

# See if A inherits from itself.
if issubclass(A, A):
    print(3)










class A:
    def welcome(self):
        # Not called.
        print("Welcome")

# This is an instance of A.
a = A()

if isinstance(a, A):
    print(1)

# This is an instance of the list class.
b = [1, 2, 3]

if isinstance(b, A):
    # Not reached.
    print(2)

if isinstance(b, list):
    print(3)









class Snake:

    def __init__(self, type):
        self.type = type

    def __repr__(self):
        return "Snake, type = " + self.type

# Create Snake instance.
# ... Print its repr.
s = Snake("Anaconda")
print(s)

# Get repr of Snake.
value = repr(s)
print(value)








class Box:
    @classmethod
    def example(cls, code):
        # This method can be used as an instance or static method.
        print("Method called:", code)

# Use classmethod as a static method.
Box.example("cat")

# Use classmethod as an instance method.
b = Box()
b.example("dog")







class Box:
    @staticmethod
    def Message(a):
        print("Box Message", a)

# Call static method with type.
Box.Message(1)

# Call static method with instance.
b = Box()
b.Message(2)








class Snake:
    def getname(self):
        return self._name

    def setname(self, value):
        # When property is set, capitalize it.
        self._name = value.capitalize()

    name = property(getname, setname)

# Create a snake instance.
s = Snake()

# Set name property.
s.name = "rattle"

# Get name property.
print(s.name)








class Shape:
    def name(self):
        print("Shape")

class Circle(Shape):
    def name(self):
        print("Circle")
        # Call name method from parent class.
        super().name()

# Create Circle and call name.
c = Circle()
c.name()








class Snake:

    def __init__(self, name, color, unique_id):
    
        self.name = name
        self.color = color
        self.unique_id = unique_id
    
    def __hash__(self):
    
        # Hash on a unique value of the class.
        return int(self.unique_id)


# Hash now is equal to the unique ID values used.
p = Snake("Python", "green", 55)
print(hash(p))

p = Snake("Python", "green", 105)
print(hash(p))







class Cat:
    def __init__(self, color):
        self.color = color

cat1 = Cat("black")
cat2 = Cat("orange")

# Each object has a unique id.
# ... The ids may vary between runs.
print(id(cat1))
print(id(cat2))











# CONSOLE IN PYTHON 
# ========================

print("Enter number:")
# Get input and convert it to an integer.
value = input()
number1 = int(value)

print("Again:")
value = input()
number2 = int(value)

print("Product:")
# Multiply the two numbers.
print(number1 * number2)







while True:
    try:
        # Get input with prompt.
        code = input("Code: ")
        # Attempt to parse input.
        value = int(code)
        break;
    except:
        print("Invalid code")

print("Value:", value)







# Open this file for writing.
f = open("C:\\profiles\\perls.txt", "w")

# Print lines to the file.
print("Some text", file=f)
print("Some more text", file=f)






# Change end argument to avoid newline.
print("Hi, ", end="")
print("how are you?")





# Print formatted string.
value = 10
print(str.format("There are {} apples", value))









# WHILE IN PYTHON
# ===============


def m():
    # Get random number.
    n = random.randint(0, 3)
    print(n)
    # Return true if number is less than 3.
    return n <= 2



# Call method until it returns false.
while m():
    # Do nothing in the loop.
    pass








list = ["cat", "dog", "panther", "parakeet"]

for element in list:
    # Test for this element.
    if element == "panther":
        continue

    # Display element.
    print("Pet", element)







elements = ["cat", "dog", "horse", None, "gerbil"]

def random_element():
    # Return random element from list.
    return random.choice(elements)

# Use iter until a None element is returned.
for element in iter(random_element, None):
    print(element)







elements = ["cat", "dog", "horse", None, "gerbil"]

# Iter returns each element, one after another.
for element in iter(elements):
    print(element)





values = [1, 10, 100, 1000]
i = iter(values)

# Call the next built-in on an iter.
# ... This style of code is not often useful.
value = next(i)
print(value)

value = next(i)
print(value)

value = next(i)
print(value)








# TYPE IN PYTHON
# ==============


Cat = type("Cat", (object,), dict())
cat = Cat()

# Set weight to 4.
setattr(cat, "weight", 10)

# Get the weight.
value = getattr(cat, "weight")
print(value)








# Create class type with default attributes (fields).
Cat = type("Cat", (object,), {"paws": 4, "weight": -1})
cat = Cat()

# Access attributes.
print("Paws =", cat.paws)
print("Weight =", cat.weight)








class Box:
    pass

box = Box()

# Create a width attribute.
setattr(box, "width", 15)

# The attribute exists.
if hasattr(box, "width"):
    print(True)

# Delete the width attribute.
delattr(box, "width")

# Width no longer exists.
if not hasattr(box, "width"):
    print(False)








# BYTE AND BYTEARRAY IN PYTHON 
# ============================



elements = [0, 200, 50, 25, 10, 255]

# Create bytearray from list of integers.
values = bytearray(elements)

# Modify elements in the bytearray.
values[0] = 5
values[1] = 0

# Display bytes.
for value in values:
    print(value)







elements = [5, 10, 0, 0, 100]

# Create immutable bytes object.
data = bytes(elements)

# Loop over bytes.
for d in data:
    print(d)






data = bytes([10, 20, 30, 40])

# We can read values from a bytes object.
print(data[0])

# We cannot assign elements.
data[0] = 1






values = [5, 10, 15, 20]
arr = bytearray(values)

# Assign first two elements to new list.
arr[0:2] = [100, 0, 0]

# The array is now modified.
for v in arr: 
    print(v)







data = bytes(b"abc")

# Get a slice from the bytes object.
first_part = data[0:2]

# Display values from slice.
for element in first_part: 
    print(element)








data = bytes(b"python")

# This sequence is found.
index1 = data.find(b"on")
print(index1)

# This sequence is not present.
index2 = data.find(b"java")
print(index2)






data = bytes([100, 20, 10, 200, 200])

# Test bytes object with "in" operator.
if 200 in data:
    print(True)

if 0 not in data:
    print(False)






left = bytearray(b"hello ")
right = bytearray(b"world")

# Combine two bytearray objects with plus.
both = left + right
print(both)






initial = [100, 255, 255, 0]
print(initial)

# Convert the list to a byte array.
b = bytearray(initial)
print(b)

# Convert back to a list.
result = list(b)
print(result)







# Create a bytearray from a string with ASCII encoding.
arr = bytearray("abc", "ascii")
print(arr)

# Convert bytearray back into a string.
result = arr.decode("ascii")
print(result)







# Create bytearray and append integers as bytes.
values = bytearray()
values.append(0)
values.append(1)
values.append(2)
print(values)

# Delete the first element.
del values[0:1]
print(values)

# Insert at index 1 the value 3.
values.insert(1, 3)
print(values)








# ValueError. Numbers inserted into a bytearray or bytes 
# object must be between 0 and 255 inclusive. If we try to 
# insert an out-of-range number, we will receive a ValueError. 
# Python that causes ValueError

# This does not work.
values = bytes([3000, 4000, 5000])
print("Not reached")







value = b"aaabbb"

# Use bytes replace method.
result = value.replace(b"bbb", b"ccc")
print(result)







# Create a bytes object with no "bytes" keyword.
value1 = b"desktop"
print(value1)

# Use bytes keyword.
value2 = bytes(b"desktop")
print(value2)

# Compare two bytes objects.
if value1 == value2:
    print(True)







value = b"users"

# Compare bytes with startswith and endswith.
if value.startswith(b"use"):
    print(True)

if value.endswith(b"s"):
    print(True)






# A bytes object with comma-separate values.
data = b"cat,dog,fish,bird,true"

# Split on comma-byte.
elements = data.split(b",")

# Print length and list contents.
print(len(elements))
print(elements)

# Combine bytes objects into a single bytes object.
result = b",".join(elements)
print(result)







view = memoryview(b"abc")

# Print the first element.
print(view[0])

# Print the element count.
print(len(view))
print(view.tolist())






print(time.time())

# Version 1: append to list.
for i in range(0, 1000000):
    x = list()
    for v in range(0, 255):
        x.append(v)

print(time.time())

# Version 2: append to bytearray.
for i in range(0, 1000000):
    x = bytearray()
    for v in range(0, 255):
        x.append(v)

print(time.time())






# DELETE IN PYTHON 
# ================




values = [100, 200, 300, 400, 500, 600]
# Use del to remove by an index or range of indexes.
del values[2]
print(values)

values = [100, 200, 300, 400, 500, 600]
# Use remove to remove by value.
values.remove(300)
print(values)






elements = ["A", "B", "C", "D"]
# Remove first element.
del elements[:1]
print(elements)

elements = ["A", "B", "C", "D"]
# Remove two middle elements.
del elements[1:3]
print(elements)

elements = ["A", "B", "C", "D"]
# Remove second element only.
del elements[1]
print(elements)








colors = {"red" : 100, "blue" : 50, "purple" : 75}
# Delete the pair with a key of "red."
del colors["red"]
print(colors)







# FILE HANDELLING IN PYTHON 
# =========================


# Open a file on the disk.
f = open(r"C:\perls.txt", "r")

# Print all its lines.
for line in f.readlines():
    # Modify the end argument.
    print(line, end="")






name = r"C:\perls.txt"

# Open the file in a with statement.
with open(name) as f:
    print(f.readline(), end="")

# Repeat.
with open(name) as f:
    print(f.readline(), end="")







import pickle

# Input list data.
list = ["one", "two", "three"]
print("before:", list)

# Open the file and call pickle.dump.
with open("f.pickle", "wb") as f:
    pickle.dump(list, f)

# Open the file and call pickle.load.
with open("f.pickle", "rb") as f:
    data = pickle.load(f)
    print("after:", data)



# Output

# before: ['one', 'two', 'three']
# after: ['one', 'two', 'three']






# Create new empty file.
# ... If the file exists, it will be cleared of content.
f = open("C:\\programs\\test.file", "w")






# Open a file.
f = open(r"C:\programs\file.txt", "r")

# Stores character counts.
chars = {}

# Loop over file and increment a key for each char.
for line in f.readlines():
    for c in line.strip():
    # Get existing value for this char or a default of zero.
    # ... Add one and store that.
    chars[c] = chars.get(c, 0) + 1

# Print character counts.
for item in chars.items():
    print(item)






# Read file in binary form.
# ... Specify "b" for binary read and write.
f = open(r"C:\stage-perls-cf\file-python", "rb")

# Read the entire file.
data = f.read()

# Print length of result bytes object.
# ... Print first three bytes (which are gzip).
print(len(data))
print(data[0])
print(data[1])
print(data[2])










import json, urllib, itertools


# for k, g in itertools.groupby('AAAABBBCCD'):
#   print k, list(g)


# from os import listdir
# from os.path import isfile, join


# mypath = "/Users/Chaklader/Downloads/data_json"
# onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]
# count = 0

# lis = []



# description  :  This 2941 square foot single family home has 4 bedrooms and 4.0 bathrooms. It is located at 2525 Westerham Way Thompsons Station, Tennessee.
# price  :  $360,990 and up
# long  :  -86.91139079999999
# details  :  {u'facts': [u'Baths: 3 full, 1 half', u'Single Family', u'Built in 2016', u'Price/sqft: $123', u'Built by: Goodall Homes', u'Community name: Canterbury', u"Sales office: 2525 Westerham Way, Thompson's Station, TN, 37179"], u'construction': [u'Stories: 2'], u'other': [u'Floor size: 2,941 sqft'], u'features': [u'Parking: 2 spaces']}
# main_features  :  [u'4 beds', u'4 baths', u'2,941 sqft']
# address  :  The Alexandria Plan, Canterbury Thompsons Station, TN 37179
# lat  :  35.802011
# listing_type  :  New Construction
# photo_url  :  http://photos2.zillowstatic.com/p_h/ISdonmss91u7h11000000000.jpg


# id , user_d , retype ,      subtype,   price , city , state , country , 
# incrs, 1,  Residential, Single Family, 3434300,   ,       ,           , 


# description ,  bedrooms , bathrooms, relistingby, builtin , resize , 
#             ,     4   ,     3,        reagent,    2015 ,     3245 ,    


# contact_name , contact_phone , contact_email ,  contact_website ,  
# admin        ,       


# contact_address ,   show_image , pictures , ip, dttm, dttm_modified,  
# address , apt, postal , classification , headline , cats , dogs , smoking 
# usermail ,  permanent , latitude, longitude , listing_type , flag , featured_till , 
# mlsid , zid , bank_status   




# for on in onlyfiles:

#   name = str.format("/Users/Chaklader/Downloads/data_json/{v}", v = on)
#   lis.append(name)

# count = 0

# print len(lis)

# for li in lis:

#   count += 1
#   print count 
#   fo = open( name, "r")
#   json_response = json.loads(fo.read())

#   for res in json_response:
#       print res, " : ",json_response[res]

#   if count > 5:
#       break
#   print "\n\n"



# /Users/Chaklader/Downloads/data_json

# response = urllib.urlopen('https://udacity.com/public-api/v0/courses')
# json_response = json.loads(response.read())

# count =  0

# for course in json_response['courses']:

#   count += 1

#   if count < 5:
#       print  count
#       print course['title']
#       print course['homepage']

# def reverse (str_):

#   if str_ != "":
#       return str_[-1:] + reverse(str_[:-1])

#   else :
#       return ""
# db = MySQLdb.connect("localhost","buildersusacana","(K{G90r~o0Ef","builders_susacanad" )







# assume text input can fit in memory
# assume text input is ascii
# assume that I can make many passes over the text document, because python replace searches the entire document.
# assume I can make more than one pass over the document / not a streaming algorithm
# assume that there are many edge cases I missed such as Inc. that can be added to the replacement list
# assume I can replace . with _ or some other special character to deal with abbreviations / Initials


# import sys
# import re
import collections

WordDictionary = {}


def ReadFile(articleUrl):
    fh = open(articleUrl,"r")
    return fh.read()


def Populate(sentenceItem,sentenceIndex):

    for currentWord in sentenceItem.split(" "):
        currentWord = currentWord.replace(".","").replace('"','')
        if(currentWord not in WordDictionary):
            WordDictionary[currentWord] = [sentenceIndex]
        else:
            WordDictionary[currentWord] += [sentenceIndex]


def Display():
    for key in sorted(WordDictionary.keys()):
        print(key + "  {",len(WordDictionary[key])," : ",WordDictionary[key], "} \n")



if(len(sys.argv)> 1):

    fileName = sys.argv[1]

    # make the article text lowercase and strip out special characters
    articleText = ReadFile(fileName).lower().replace("\n"," ").replace(","," ").replace("-"," ").replace('"',' ')

    #remove commonly known edge cases Mrs. / Mr.  This list can be expanded, there are lots of missing edge cases
    articleText = articleText.replace("mrs.","mrs").replace("mr.","mr").replace("ms.","ms")

    articleText = articleText.replace("? ",". ").replace("! ",". ")
    # convert all of the terminations to the same termination string

    # this regex will look for any pattern of repeating . and letter which might indicate abbreviations / acronyms
    abbreviationPattern = r"((\w\.){2,})\s"

    # I do not handle cases like Inc. Corp. ...ect they can be added to the replacement list if necessary
    result = re.findall(abbreviationPattern,articleText)

    # I use a greedy regex so need to get the outer selection
    for abbreviation in result:
        articleText = articleText.replace(abbreviation[0],abbreviation[0].replace(".","_"))
        #print (abbreviation[0])

    # print (articleText)

    sentences = articleText.split(". ")
    for s in sentences:
        Populate(s,sentences.index(s))
    #print (sentences)

    # display the sorted dictionary
    Display()
else:
    print("expects file location as command line argument")






# ITERTOOLS
# =========


from itertools import *
import fractions
#  make the imports 


for i in chain([1, 2, 3], ['a', 'b', 'c']):
    print(i, end=' ')
print()
#  1 2 3 a b c




def make_iterables_to_chain():
    yield [1, 2, 3]
    yield ['a', 'b', 'c']
for i in chain.from_iterable(make_iterables_to_chain()):
    print(i, end=' ')

print()
# 1 2 3 a b c




for i in zip([1, 2, 3], ['a', 'b', 'c']):
    print(i)

# (1, 'a')
# (2, 'b')
# (3, 'c')




r1 = range(3)
r2 = range(2)

print('zip stops early:')
print(list(zip(r1, r2)))

r1 = range(3)
r2 = range(2)

print('\nzip_longest processes all of the values:')
print(list(zip_longest(r1, r2)))


# zip stops early:
# [(0, 0), (1, 1)]

# zip_longest processes all of the values:
# [(0, 0), (1, 1), (2, None)]



print('Stop at 5:')
for i in islice(range(100), 5):
    print(i, end=' ')
print('\n')

print('Start at 5, Stop at 10:')
for i in islice(range(100), 5, 10):
    print(i, end=' ')
print('\n')

print('By tens to 100:')
for i in islice(range(100), 0, 100, 10):
    print(i, end=' ')
print('\n')



# Stop at 5:
# 0 1 2 3 4

# Start at 5, Stop at 10:
# 5 6 7 8 9

# By tens to 100:
# 0 10 20 30 40 50 60 70 80 90


r = islice(count(), 5)
i1, i2 = tee(r)

print('i1:', list(i1))
print('i2:', list(i2))


# i1: [0, 1, 2, 3, 4]
# i2: [0, 1, 2, 3, 4]




r = islice(count(), 5)
i1, i2 = tee(r)

print('r:', end=' ')
for i in r:
    print(i, end=' ')
    if i > 1:
        break
print()

print('i1:', list(i1))
print('i2:', list(i2))


# r: 0 1 2
# i1: [3, 4]
# i2: [3, 4]



def times_two(x):
    return 2 * x


def multiply(x, y):
    return (x, y, x * y)


print('Doubles:')
for i in map(times_two, range(5)):
    print(i)

print('\nMultiples:')
r1 = range(5)
r2 = range(5, 10)

for i in map(multiply, r1, r2):
    print('{:d} * {:d} = {:d}'.format(*i))

print('\nStopping:')

r1 = range(5)
r2 = range(2)

for i in map(multiply, r1, r2):
    print(i)


# Doubles:
# 0
# 2
# 4
# 6
# 8

# Multiples:
# 0 * 5 = 0
# 1 * 6 = 6
# 2 * 7 = 14
# 3 * 8 = 24
# 4 * 9 = 36

# Stopping:
# (0, 0, 0)
# (1, 1, 1)



values = [(0, 5), (1, 6), (2, 7), (3, 8), (4, 9)]

for i in starmap(lambda x, y: (x, y, x * y), values):
    print('{} * {} = {}'.format(*i))


# 0 * 5 = 0
# 1 * 6 = 6
# 2 * 7 = 14
# 3 * 8 = 24
# 4 * 9 = 36


for i in zip(count(1), ['a', 'b', 'c']):
    print(i)

# (1, 'a')
# (2, 'b')
# (3, 'c')


# import fractions
start = fractions.Fraction(1, 3)
step = fractions.Fraction(1, 3)

for i in zip(count(start, step), ['a', 'b', 'c']):
    print('{}: {}'.format(*i))


# 1/3: a
# 2/3: b
# 1: c


for i in zip(range(7), cycle(['a', 'b', 'c'])):
    print(i)

# (0, 'a')
# (1, 'b')
# (2, 'c')
# (3, 'a')
# (4, 'b')
# (5, 'c')
# (6, 'a')




for i in repeat('over-and-over', 5):
    print(i)

# over-and-over
# over-and-over
# over-and-over
# over-and-over
# over-and-over




for i, s in zip(count(), repeat('over-and-over', 5)):
    print(i, s)

# 0 over-and-over
# 1 over-and-over
# 2 over-and-over
# 3 over-and-over
# 4 over-and-over





for i in map(lambda x, y: (x, y, x * y), repeat(2), range(5)):
    print('{:d} * {:d} = {:d}'.format(*i))

# 2 * 0 = 0
# 2 * 1 = 2
# 2 * 2 = 4
# 2 * 3 = 6
# 2 * 4 = 8



def should_drop(x):
    print('Testing:', x)
    return x < 1

for i in dropwhile(should_drop, [-1, 0, 1, 2, -2]):
    print('Yielding:', i)


# Testing: -1
# Testing: 0
# Testing: 1
# Yielding: 1
# Yielding: 2
# Yielding: -2




def should_take(x):
    print('Testing:', x)
    return x < 2

for i in takewhile(should_take, [-1, 0, 1, 2, -2]):
    print('Yielding:', i)


# Testing: -1
# Yielding: -1
# Testing: 0
# Yielding: 0
# Testing: 1
# Yielding: 1
# Testing: 2





def check_item(x):
    print('Testing:', x)
    return x < 1

for i in filter(check_item, [-1, 0, 1, 2, -2]):
    print('Yielding:', i)

# Testing: -1
# Yielding: -1
# Testing: 0
# Yielding: 0
# Testing: 1
# Testing: 2
# Testing: -2
# Yielding: -2






def check_item(x):
    print('Testing:', x)
    return x < 1

for i in filterfalse(check_item, [-1, 0, 1, 2, -2]):
    print('Yielding:', i)

# Testing: -1
# Testing: 0
# Testing: 1
# Yielding: 1
# Testing: 2
# Yielding: 2
# Testing: -2





every_third = cycle([False, False, True])
data = range(1, 10)

for i in compress(data, every_third):
    print(i, end=' ')
print()


# 3 6 9 




# grouping the data
# -----------------

import functools
import operator
import pprint


@functools.total_ordering
class Point:

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __repr__(self):
        return '({}, {})'.format(self.x, self.y)

    def __eq__(self, other):
        return (self.x, self.y) == (other.x, other.y)

    def __gt__(self, other):
        return (self.x, self.y) > (other.x, other.y)


# Create a dataset of Point instances
data = list(map(Point,
                cycle(islice(count(), 3)),
                islice(count(), 7)))

print('Data:')
pprint.pprint(data, width=35)
print()

# Try to group the unsorted data based on X values
print('Grouped, unsorted:')
for k, g in groupby(data, operator.attrgetter('x')):
    print(k, list(g))
print()

# Sort the data
data.sort()
print('Sorted:')
pprint.pprint(data, width=35)
print()

# Group the sorted data based on X values
print('Grouped, sorted:')
for k, g in groupby(data, operator.attrgetter('x')):
    print(k, list(g))
print()





# Data:
# [(0, 0),
#  (1, 1),
#  (2, 2),
#  (0, 3),
#  (1, 4),
#  (2, 5),
#  (0, 6)]

# Grouped, unsorted:
# 0 [(0, 0)]
# 1 [(1, 1)]
# 2 [(2, 2)]
# 0 [(0, 3)]
# 1 [(1, 4)]
# 2 [(2, 5)]
# 0 [(0, 6)]

# Sorted:
# [(0, 0),
#  (0, 3),
#  (0, 6),
#  (1, 1),
#  (1, 4),
#  (2, 2),
#  (2, 5)]

# Grouped, sorted:
# 0 [(0, 0), (0, 3), (0, 6)]
# 1 [(1, 1), (1, 4)]
# 2 [(2, 2), (2, 5)]





#  Combining the inputs 
#  --------------------


print(list(accumulate(range(5))))
print(list(accumulate('abcde')))


# [0, 1, 3, 6, 10]
# ['a', 'ab', 'abc', 'abcd', 'abcde']





def f(a, b):
    print(a, b)
    return b + a + b

print(list(accumulate('abcde', f)))


# a b
# bab c
# cbabc d
# dcbabcd e
# ['a', 'bab', 'cbabc', 'dcbabcd', 'edcbabcde']





FACE_CARDS = ('J', 'Q', 'K', 'A')
SUITS = ('H', 'D', 'C', 'S')

DECK = list(
    product(
        chain(range(2, 11), FACE_CARDS),
        SUITS,
    )
)

for card in DECK:
    print('{:>2}{}'.format(*card), end=' ')
    if card[1] == SUITS[-1]:
        print()

# 2H  2D  2C  2S
#  3H  3D  3C  3S
#  4H  4D  4C  4S
#  5H  5D  5C  5S
#  6H  6D  6C  6S
#  7H  7D  7C  7S
#  8H  8D  8C  8S
#  9H  9D  9C  9S
# 10H 10D 10C 10S
#  JH  JD  JC  JS
#  QH  QD  QC  QS
#  KH  KD  KC  KS
#  AH  AD  AC  AS




FACE_CARDS = ('J', 'Q', 'K', 'A')
SUITS = ('H', 'D', 'C', 'S')

DECK = list(
    product(
        SUITS,
        chain(range(2, 11), FACE_CARDS),
    )
)

for card in DECK:
    print('{:>2}{}'.format(card[1], card[0]), end=' ')
    if card[1] == FACE_CARDS[-1]:
        print()

# 2H  3H  4H  5H  6H  7H  8H  9H 10H  JH  QH  KH  AH
# 2D  3D  4D  5D  6D  7D  8D  9D 10D  JD  QD  KD  AD
# 2C  3C  4C  5C  6C  7C  8C  9C 10C  JC  QC  KC  AC
# 2S  3S  4S  5S  6S  7S  8S  9S 10S  JS  QS  KS  AS






def show(iterable):
    for i, item in enumerate(iterable, 1):
        print(item, end=' ')
        if (i % 3) == 0:
            print()
    print()


print('Repeat 2:\n')
show(list(product(range(3), repeat=2)))

print('Repeat 3:\n')
show(list(product(range(3), repeat=3)))




# Repeat 2:
# (0, 0) (0, 1) (0, 2)
# (1, 0) (1, 1) (1, 2)
# (2, 0) (2, 1) (2, 2)

# Repeat 3:

# (0, 0, 0) (0, 0, 1) (0, 0, 2)
# (0, 1, 0) (0, 1, 1) (0, 1, 2)
# (0, 2, 0) (0, 2, 1) (0, 2, 2)
# (1, 0, 0) (1, 0, 1) (1, 0, 2)
# (1, 1, 0) (1, 1, 1) (1, 1, 2)
# (1, 2, 0) (1, 2, 1) (1, 2, 2)
# (2, 0, 0) (2, 0, 1) (2, 0, 2)
# (2, 1, 0) (2, 1, 1) (2, 1, 2)
# (2, 2, 0) (2, 2, 1) (2, 2, 2)






def show(iterable):

    first = None
    for i, item in enumerate(iterable, 1):
        if first != item[0]:
            if first is not None:
                print()
            first = item[0]
        print(''.join(item), end=' ')
    print()


print('All permutations:\n')
show(permutations('abcd'))

print('\nPairs:\n')
show(permutations('abcd', r=2))


# All permutations:

# abcd abdc acbd acdb adbc adcb
# bacd badc bcad bcda bdac bdca
# cabd cadb cbad cbda cdab cdba
# dabc dacb dbac dbca dcab dcba

# Pairs:

# ab ac ad
# ba bc bd
# ca cb cd
# da db dc






def show(iterable):

    first = None
    for i, item in enumerate(iterable, 1):
        if first != item[0]:
            if first is not None:
                print()
            first = item[0]
        print(''.join(item), end=' ')
    print()

print('Unique pairs:\n')
show(combinations('abcd', r=2))


# Unique pairs:
# ab ac ad
# bc bd
# cd



def show(iterable):

    first = None
    for i, item in enumerate(iterable, 1):
        if first != item[0]:
            if first is not None:
                print()
            first = item[0]
        print(''.join(item), end=' ')
    print()

print('Unique pairs:\n')
show(combinations_with_replacement('abcd', r=2))

# Unique pairs:

# aa ab ac ad
# bb bc bd
# cc cd
# dd





# initialize
a = []


# create the table (name, age, job)
a.append(["Nick", 30, "Doctor"])
a.append(["John",  8, "Student"])
a.append(["Paul", 22, "Car Dealer"])
a.append(["Mark", 66, "Retired"])


# sort the table by age
import operator

#  sort based on the index-1 element 
a.sort(key=operator.itemgetter(1))


# print the table
print(a)
# [['John', 8, 'Student'], ['Paul', 22, 'Car Dealer'], ['Nick', 30, 'Doctor'], ['Mark', 66, 'Retired']]



func = operator.itemgetter(1)
func(a)
# ['Paul', 22, 'Car Dealer']
func(a[0])
# 8

# To do it in a different way, you can use lambda:
a.sort(key=lambda x: x[1])

# And reverse it:
a.sort(key=operator.itemgetter(1), reverse=True)



a.sort(key=operator.itemgetter(1,2))




# lambda in python 
# ----------------





