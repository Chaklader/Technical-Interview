from random import randint




# question: design a linked list 
# -------------------------------

class Node:

    def __init__(self, value):

        self.value = value
        self.next = None

    def __str__(self):
        return str(self.value)

class LinkedList:

    def __init__(self):

        self.head = None
        self.tail = None

    def addNode(self,value):

        node = Node(value)

        if self.head == None:
            self.head = node
            self.tail = node

        else:
            self.tail.next = node
            self.tail = node

    def __str__(self):

        if self.head != None:

            index = self.head
            nodestore = [str(index.value)]

            while index.next != None:

                index = index.next
                nodestore.append(str(index.value))

            return "LinkedList  [ " + "->".join(nodestore) + " ]"

        return "LinkedList  []"


    #remove the first node that have the same value as the given node_value
    def removeNode(self, node_value):

        current = self.head

        if current.value == node_value:
            self.head = self.head.next

        while(current.next != None):

            if current.next.value == node_value:

                current.next = current.next.next
                break

            else:
                current = current.next


def randomLinkedList(length, min, max):

    linkedlist = LinkedList()

    for i in range(length):
        value = randint(min, max)
        linkedlist.addNode(value)
    return linkedlist


# END of solution: design a linked list 
# -------------------------------------







# question 2-1: 
# -------------

# question 2-1: design an algorithm to remove 
# the duplicates from an unsorted linked list

# use a hash table, O(n)
def deleteDups(linkedlist):

    if (linkedlist.head != None):

        currNode = linkedlist.head
        dic =  {currNode.value: True}

        while currNode.next != None:

            if currNode.next.value in dic:
                currNode.next = currNode.next.next

            else:
                dic[currNode.next.value] = True
                currNode = currNode.next


# use no data structure, O(n2)
def deleteDups2(linkedlist):
    
    currNode = linkedlist.head

    while currNode != None:

        runner = currNode

        while runner.next != None:

            if runner.next.value == currNode.value:
                runner.next = runner.next.next

            else:
                runner = runner.next
        currNode = currNode.next


#---------------- test -------

L1 = randomLinkedList(9, 3, 7)
print L1
deleteDups(L1)
print L1
print
L2 = randomLinkedList(9, 3, 7)
print L2
deleteDups2(L2)
print L2


# END of solution 2-1
# -------------------








# question 2-2
# ------------


# if k = 1, return the last element
# /*question 4-2: desing an algorithm to get
# the n-th number from last in a linked list*/
def kth_to_last(linkedlist, k):

    if k <= 0:
        return "invalid k"
    
    pointer2 = linkedlist.head
    
    for i in range(k-1):
    
        if pointer2.next != None:
            pointer2 = pointer2.next
            
        else:
            return "k exceeds the length of linkedlist"
    
    pointer1 = linkedlist.head

    while pointer2.next != None:

        pointer2 = pointer2.next
        pointer1 = pointer1.next

    return pointer1
   
 

#---------------test-----------
L = randomLinkedList(8, 0, 100)
print L
print "The 3th to last element is", kth_to_last(L, 3)


# END of solution 2-2
# -------------------






# question 2-3 
# ------------
# question 2-3 :  design an algorithm to delete a node 
# to middle of a singly linked list given only access to 
# that node

def deleteNode(linkedlist, node):

    if node.next != None:

        node.value = node.next.value
        node.next = node.next.next  
    
    # if the given node is the last one, no way to delete it.
    # Here we set the last one's value to None
    else:
        node.value = None         

#-----------test--------------

L = randomLinkedList(5, 0, 100)
node = L.head.next.next         # Given access to the 3rd node
print L
print "After deleting the node"
deleteNode(L, node)
print L

# END of solution 2-3
# -------------------


# question 2-4 : desing an algorithm to add 
# values in two linked list Input keeping the 
# length as same, 
# Input: (3 -> 1 -> 5) + (5 -> 9 -> 2) 
# Output: 8 -> 0 -> 8

# question 2-5 : design an algorithm to find 
# the head of the circular linked list




# question 2-4 
# ------------

def partition(linkedlist, x):

    if linkedlist.head != None:
    
        p1 = linkedlist.head
        p2 = linkedlist.head.next
    
        while p2 != None:
    
            if p2.value < x:
                p1.next = p2.next
                p2.next = linkedlist.head
                linkedlist.head = p2
                p2 = p1.next
            else:
                p1 = p1.next
                p2 = p1.next



#----------------test-----------------
L = randomLinkedList(10, 0, 50)
x = 25

print L, " , x=25"   
partition(L, x)
print L 


# END of solution 2-4
# -------------------










# question 2-5 
# ------------

#The digits are stored in reverse order
def addLists_rev(L1, L2):

    p1 = L1.head
    p2 = L2.head
    carry = 0
    linkedlist_sum = LinkedList()
    
    while (p1 != None) or (p2 != None) or (carry != 0):
        dig_sum = carry
        if p1 != None:
            dig_sum += p1.value
            p1 = p1.next
        if p2 != None:
            dig_sum += p2.value
            p2 = p2.next
        linkedlist_sum.addNode(dig_sum%10)
        carry = dig_sum/10
    return linkedlist_sum


# The digits are stored in forward order

# Iterative implementation
def addLists_fwd(L1, L2):
    # Create two new linkedlists which are reversed version of L1 and L2
    # Use addLists_rev method, then reverse the sum list
    L1_rev = reverseLinkedlist(L1)
    L2_rev = reverseLinkedlist(L2)
    return reverseLinkedlist(addLists_rev(L1_rev, L2_rev))


# Recursive implementation of addLists_fwd
def addLists_fwd_2(L1, L2):
    # compare length of linked lists and pad the shorter one with 0
    l1_len = lengthOfLinkedlist(L1)
    l2_len = lengthOfLinkedlist(L2)
    if l1_len < l2_len:
        L1 = padInFront(L1, l2_len - l1_len)
    else:
        L2 = padInFront(L2, l1_len - l2_len)   
    # Add lists
    sumandcarry = addListsFwd2Helper(L1.head, L2.head)
    result = LinkedList()
    result.head = sumandcarry[0]   
    # If the carry is not 0, insert this at the front of the linked list 
    if sumandcarry[1] != 0:
        addNodeInFront(result, sumandcarry[1])
    return result


# Helper function for recursive adding lists
def addListsFwd2Helper(p1, p2):
    if (p1 == None) and (p2 == None):
        sumandcarry = [None,0]       # a python list stores sum node and carry
        return sumandcarry
    sumandcarry = addListsFwd2Helper(p1.next, p2.next)
    val = p1.value + p2.value + sumandcarry[1]
    dig_node = insertBefore(sumandcarry[0], val%10) 
    carry = val/10
    return [dig_node, carry]


# Helper function to insert node in the front of a linked list
def addNodeInFront(linkedlist, value):
    node = Node(value)
    node.next = linkedlist.head
    linkedlist.head = node


# Helper function to insert node before a node
def insertBefore(node, value):
    new_node = Node(value)
    new_node.next = node
    return new_node


# Helper function to create a reversed linedlist
def reverseLinkedlist(linkedlist):
    current = linkedlist.head
    newlinkedlist = LinkedList()
    while current != None:
        addNodeInFront(newlinkedlist, current.value)
        current = current.next
    return newlinkedlist


# Helper function to caculate length of a linked list
def lengthOfLinkedlist(linkedlist):
    length = 0
    current = linkedlist.head
    while current != None:
        length += 1
        current = current.next
    return length


# Helper funtion to pad the list with zeros in front
def padInFront(linkedlist, number):
    padlist = LinkedList()
    padlist.head = linkedlist.head
    for i in range(number):
        addNodeInFront(padlist, 0)
    return padlist




#----------------test--------------
L1 = randomLinkedList(3,0,9)
L2 = randomLinkedList(5,0,9)
print L1
print L2
print "In reverse order, the sum is: "
print addLists_rev(L1, L2)
print "In forward order with iterative implementation, the sum is: "
print addLists_fwd(L1, L2)
print 'In forward order with recursive implementation, the sum is: '
print addLists_fwd_2(L1, L2)


# END of solution 2-5
# -------------------







# question 2-6 
# ------------
def findBeginning(linkedlist):

    slow = linkedlist.head
    fast = linkedlist.head

    # Find meetng point
    while (fast != None) and (fast.next != None):

        slow = slow.next
        fast = fast.next.next
        if fast == slow:
            break
    
    # Check whether it is a circular linked list
    if fast == None or fast.next == None:
        return None

    # Move one runner to head. Making them move at same pace, they will meet at the beginning of the loop
    fast = linkedlist.head

    while fast != slow:
        slow = slow.next
        fast = fast.next

    return fast



# -----------------test------------------
nodes_number = 100
nodes_in_loop = 20
L = LinkedList()
current = L.head
store = []                  # store nodes to help creating loop

# Create a linked list
for i in range(nodes_number):
    L.addNode(i)
    current = L.head if i==0 else current.next
    store.append(current)

# Creat loop
current.next = None if nodes_in_loop <= 0 else store[nodes_number - nodes_in_loop]

beginning = findBeginning(L)
print beginning              # 80

# END of solution 2-6
# -------------------







# question 2-7
# ------------

# Iterative approch
def isPalindrome_iter(linkedlist):
    if linkedlist.head == None:
        return None
    fast = linkedlist.head
    slow = linkedlist.head
    firsthalf = []
    while fast != None and fast.next != None:
        firsthalf.append(slow.value)
        slow = slow.next
        fast = fast.next.next
    if fast != None:
        slow = slow.next
    while slow != None:
        if firsthalf.pop() != slow.value:
            return False
        else:
            slow = slow.next
    return True


# Recursive approch
def isPalindrome_recu(linkedlist):
    length = lengthOfLinkedlist(linkedlist)
    current = linkedlist.head
    result = isPalindrome_recu_helper(current, length)
    return result[1]



def isPalindrome_recu_helper(current, length):
    if current == None:
        return [None, True]             
    elif length == 1:
        return [current.next, True]
    elif length == 2:
        return [current.next.next, current.value == current.next.value]
    
    # result is a python list stores two variables 
    result = isPalindrome_recu_helper(current.next, length - 2)

    if (result[0] == None) or (not result[1]):
        return result
    else:
        result[1] = current.value == result[0].value
        result[0] = result[0].next
        return result


def lengthOfLinkedlist(linkedlist):
    length = 0
    current = linkedlist.head
    while current != None:
        length += 1
        current = current.next
    return length



# -------------------test------------------
L1 = randomLinkedList(3, 3, 4)
print "L2:", L1
print "isPalindrome_iter: ", isPalindrome_iter(L1)
print "isPalindrome_recu: ", isPalindrome_recu(L1)
L2 = LinkedList()
for i in range(1,4):
    L2.addNode(i)
for i in range(3, 0, -1):
    L2.addNode(i)
print "L3:", L2
print "isPalindrome_iter: ", isPalindrome_iter(L2)
print "isPalindrome_recu: ", isPalindrome_recu(L2)

# Another method: reverse the list and check if they are the same
def isPalindrome(L1):
    reverseL1 = reverseList(L1)
    return isEqual(L1, reverseL1)

def reverseList(L1):
    reverseL1 = LinkedList()
    current = L1.head
    while current != None:
        reverseL1.addNode(current.value)
        current = current.next
    return reverseL1

def isEqual(L1,L2):
    curr1 = L1.head
    curr2 = L2.head
    while curr1 != None and curr2 != None:
        if curr1.value != curr2.value:
            return False
        curr1 = curr1.next
        curr2 = curr2.next
    if curr1 != None or curr2 != None:
        return False
    else:
        return True


for i in range(27):
    L1 = randomLinkedList(3, 3, 5)
    print L1
    print isPalindrome(L1)

# END of solution 2-7
# -------------------


