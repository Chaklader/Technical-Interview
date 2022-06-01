# 3-1
class SingleArrayStacks(object):

    def __init__(self, stacksize = 100, number = 3):

        self.stacksize = stacksize
        self.number = number
        self.array = [None] * self.stacksize * self.number
        self.pointer = [-1] * self.number

    def push(self, stacknum, value):

        if self.pointer[stacknum] + 1 >= self.stacksize:
            print "Out of space"

        else:
            self.pointer[stacknum] += 1
            self.array[self.stacktop(stacknum)] = value

    def pop(self, stacknum):

        if self.pointer[stacknum] < 0:
            return "Trying to pop an empty stack."

        else:
            data = self.array[self.stacktop(stacknum)]
            self.array[self.stacktop(stacknum)] = None
            self.pointer[stacknum] -= 1
            return data

    def peek(self, stacknum):

        if self.pointer[stacknum] < 0:
            print "Empty stack"

        else:
            return self.array[self.stacktop(stacknum)]

    def isEmpty(self, stacknum):
        return self.pointer[stacknum] == -1

    def stacktop(self, stacknum):
        return self.stacksize * stacknum + self.pointer[stacknum]
# 3-1






# 3-2

# Approach 1. Use a tuple to store data and min in every stack element
class StackWithMin(object):

	def __init__(self, storage = []):
		self.storage = storage

	def empty(self):
		return len(self.storage) == 0

	def push(self, value):

		if len(self.storage) == 0 or value < self.storage[-1][1]:
			self.storage.append((value, value))

		else:
			self.storage.append((value, self.storage[-1][1]))

	def pop(self):
		return self.storage.pop()[0]

	def get_top(self):
		if len(self.storage) == 0: return None
		return self.storage[-1][0]

	def get_min(self):
		if len(self.storage) == 0: return None
		return self.storage[-1][1]


# Approach 2. Use an additional python list to keep track of mins (Save space)
class StackWithMin2:
    def __init__(self):
        self.stack = []
        self.min = []
        
    def push(self, value):
        self.stack.append(value)
        if len(self.min) == 0 or value <= self.min[-1]:
            self.min.append(value)

    def pop(self):
        if len(self.stack) == 0:
            return None
        data = self.stack.pop()
        if data == self.min[-1]:
            self.min.pop()
        return data

    def get_min(self):
        if len(self.min)==0:
            return None
        return self.min[-1]
# 3-2











# 3-3

class SetOfStacks:
    
    def __init__(self,capacity):
        self.capacity = capacity
        self.stacks = []

    def push(self, value):
        if (len(self.stacks) == 0) or (len(self.stacks[-1]) == self.capacity):
            self.stacks.append([])
        self.stacks[-1].append(value)

    def pop(self):
        if len(self.stacks) == 0:
            return None
        data = self.stacks[-1].pop()
        if len(self.stacks[-1]) == 0:
            self.stacks.pop()
        return data
    
    # Pop operation on a specifit sub-stack. (Index begins with 1)
    # Not "rolling over" version. OK with some stacks not at full capacity
    def popAt(self, index):

        if index < 1 or index > len(self.stacks) or len(self.stacks[index-1]) == 0:
            return None
        else:
            return self.stacks[index-1].pop() 
# 3-3 







# 3-4
class Hanoi:
    
    def __init__(self, size):
        self.towers = [[], [], []]
        self.size = size
        self.towers[0] = [x for x in range(size, 0, -1)]

    def playHanoi(self):

        self.printTowers()
        self.moveDisk(self.size, 1, 2, 3)
        self.printTowers()

    def moveDisk(self, size, fr, helper, to):

        if size == 1:
            data = self.towers[fr-1].pop()
            self.towers[to-1].append(data)
            print "Disk", data, ": Tower", fr, "->", to      

        else:
            self.moveDisk(size - 1, fr, to, helper)
            self.moveDisk(1, fr, helper, to)
            self.moveDisk(size - 1, helper, fr, to)

    def printTowers(self):
        for i in self.towers:
            print i
# 3-4










# 3-5

#  solution -a

"""
3.5

Implement a MyQueue class which implements a queue using two stacks.
"""

class MyQueue(object):

    """
    Implements a queue using two stacks.
    Lazily reverses items from the back_stack to the front_stack when needed.
    """
    def __init__(self):
        self.front_stack = Stack()
        self.back_stack = Stack()

    def eq(self, data):
        self.back_stack.push(data)

    def dq(self):

        if self.front_stack.size == 0:
            self.rebuild()
        return self.front_stack.pop()

    def peek_front(self):
        if self.front_stack.size == 0:
            self.rebuild()
        return self.front_stack.peek()
    
    def rebuild(self):
        """
        Lazily rebuilds the front stack when a value is needed by pop/peek.
        When the front_stack empties, rebuild it by reversing the values in
        the back_stack.
        """
        while self.back_stack.size > 0:
            self.front_stack.push(self.back_stack.pop())



### NODE AND STACK IMPLEMENTATION  ###
from copy import deepcopy
class Node(object):


    def __init__(self, data=None, nextNode=None):

        self.data = data
        self.nextNode = nextNode

    def __str__(self):
        return str(self.data)
        



class Stack(object):


    def __init__(self):
        self.stack = Node()
        self.size = 0

    def push(self, data):
        self.stack = Node(data, self.stack)
        self.size += 1

    def pop(self):
        assert self.stack.data is not None, 'Stack is empty'
        value = self.stack.data
        self.stack.data = self.stack.nextNode.data
        self.stack.nextNode = self.stack.nextNode.nextNode
        self.size -= 1
        return value
    
    def peek(self):
        assert self.size > 0, 'Stack is empty'
        return self.stack.data

    def __str__(self):
        stack_copy = deepcopy(self)
        tempHolder = []
        while stack_copy.size > 0:
            tempHolder.append(stack_copy.pop())
        return ', '.join(map(str, tempHolder[::-1]))


# solution - a



#  solution -b

class MyQueue(object):

	def __init__(self):
		self.s = [[], []]

	def push(self, value):
		self.s[0].append(value)

	def pop(self):

		if len(self.s[1]) > 0:
			return self.s[1].pop()
		while len(self.s[0]) > 0:
			self.s[1].append(self.s[0].pop())
		if len(self.s[1]) == 0:
			return None
		return self.s[1].pop()
	
	def length(self):
		return len(self.s[0]) + len(self.s[1])

	def empty(self):
		return self.length() == 0
#  END of solution -b
#  3-5










#  3-6
class Stack(list):

	def peak(self):
		return self[-1]

	def push(self, item):
		self.append(item)

	def empty(self):
		return len(self) == 0

	def sort_stack(s):

		r = Stack()

		while not s.empty():			

			tmp = s.pop()

			while not r.empty() and r.peak() > tmp:
				s.push(r.pop())

			r.push(tmp)

			while not s.empty() and s.peak() >= r.peak():
				#warning, >= here
				r.push(s.pop())
		return r

#  3-6





#  3-7
class AnimalQueue(object):

	def __init__(self):

		from collections import deque
		self.dog_q = deque()
		self.cat_q = deque()
		self.time_stamp = 0


	def enqueue(self, animal_type, animal_name):

		if animal_type == "dog":
			self.dog_q.appendleft((animal_name, self.time_stamp))
			self.time_stamp += 1
		elif animal_type == "cat":
			self.cat_q.appendleft((animal_name, self.time_stamp))
			self.time_stamp += 1
		else:
			print "invalid animal type"

	def dequeue_any(self):
		dog = self.dog_q.pop() if not len(self.dog_q) == 0 else (None, -1)
		cat = self.cat_q.pop() if not len(self.cat_q) == 0 else (None, -1)
		if dog[1] == -1 and cat[1] == -1:
			return None
		elif dog[1] < cat[1]:
			self.cat_q.append(cat)
			return dog[0]
		else:
			self.dog_q.append(dog)
			return cat[0]

	def dequeue_cat(self):
		if not len(self.cat_q) == 0:
			return self.cat_q.pop()[0]

	def dequeue_dog(self):
		if not len(self.dog_q) == 0:
			return self.dog_q.pop()[0]

# 3-7 









if __name__ == "__main__":
 	print "Hello, Stack and Queue!"

		# 3-1
	# array = SingleArrayStacks()
	# array.push(2, 11)
	# array.push(2, 13)
	# print array.pop(0)  # Trying to pop an empty stack.
	# print array.peek(2) # 13
	# array.push(0, 20)
	# array.push(0, 30)
	# print array.pop(0)  # 30
	# print array.peek(0) # 20

	# 3-1




	# 3-2
	# Testing
	# from random import randrange

	# S1 = StackWithMin()
	# S2 = StackWithMin2()
	# test_list = [randrange(100) for x in xrange(10)]

	# for num in test_list:

	# 	S1.push(num)
	# 	S2.push(num)
	# 	print num, 

	# print ""

	# for i in xrange(len(test_list)):
	# 	print "new pop", S1.pop(), S2.pop()
	# 	print "new min", S1.get_min(), S2.get_min()

	# 3-2




	# 3-3
	# setofstacks = SetOfStacks(8)

	# for i in range(24):
	#     setofstacks.push(i)
	#     print i,
	# print ""

	# for i in range(5):
	#     print "Popped", setofstacks.pop()

	# print "Test for popAt"
	# for i in range(5):
	#     for i in range(3):
	#         print "Popped", setofstacks.popAt(i+1)

	#  3-3




	# 3-4
		# hanoi = Hanoi(4)
	# hanoi.playHanoi()
	#  3-4








	#  3-5
	#  solution -a

	# a = MyQueue()
	# a.eq(1)
	# a.eq(2)
	# print a.dq()
	# print a.peek_front()
	#  END of solution -a







	# solution -b
	# q1 = MyQueue()
	# from Queue import Queue
	# q2 = Queue()


	# #testing
	# from random import randrange

	# for step in xrange(20):

	# 	operation = randrange(10)

	# 	if operation < 7:

	# 		q1.push(operation)
	# 		q2.put(operation)

	# 		print "push", operation

	# 	elif not q2.empty():
	# 		print "pop", q1.pop(), q2.get()


	# while not q2.empty():
	# 	print "pop", q1.pop(), q2.get()
	# solution -b
	# 3-5





	# 3-6
	#testing
	# from random import randrange
	# test_items = [randrange(20) for x in xrange(20)]

	# print test_items
	# S = Stack()

	# for item in test_items:
	# 	S.push(item)
	# S = Stack.sort_stack(S)

	# for i, item in enumerate(sorted(test_items)):
	# 	print "item", item, S[i]

	# 3-6








	# 3-7
	# Q = AnimalQueue()

	# from random import randrange
	# test_list = [randrange(7) for x in xrange(20)]
	# for i, x in enumerate(test_list):

	# 	if x < 4:

	# 		if i%2: 
	# 			animal_type = "cat" 
	# 		else: 
	# 			animal_type = "dog"
	# 		test_list[i] = ("enqueue", Q.enqueue, animal_type, animal_type + "#" + str(i))
	# 	elif x == 4:
	# 		test_list[i] = ("dequeue any", Q.dequeue_any)
	# 	elif x == 5:
	# 		test_list[i] = ("dequeue cat", Q.dequeue_cat)
	# 	elif x == 6:
	# 		test_list[i] = ("dequeue dog", Q.dequeue_dog)


	# for operation in test_list:
	# 	if len(operation) == 4:
	# 		print operation[0], operation[2], operation[3]
	# 		operation[1](operation[2], operation[3])
	# 	else:
	# 		print operation[0],
	# 		print operation[1]()














