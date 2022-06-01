

# LIST PERFORMANCE 
# ================

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







# STRING STRIP TEST 
# =================

s = "100200input"

# Time 1.
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




# OUTPUT TEST 
# =========== 
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

# 2. Use string appends.
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