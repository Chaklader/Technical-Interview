import random
import time 


class Node:
    def __init__(self, key):

        self.key = key
        self.lelfChild = None
        self.rightChild = None

    def __str__(self):

        return "( " + str(self.key) + " ( " + str(self.lelfChild) + " | " + str(self.rightChild) + "))" 


class LinkedList:

    def __init__(self,content):
        self.content = content
        self.next = None

    def __str__(self):
        return "( " + str(self.content) + str(self.next) + " )"



# Question 4-2 
# ------------
# Given a directed graph, design an algorithm to find out whether there is a route
# between two nodes.

class Graph_Node:

    def __init__(self, data):

        self.data = data
        self.neighbours = []

# Solution 4-2 
# ------------



class Tree:

    def __init__(self):

        self.root = None


    def getRoot(self):

        return self.root


    # add key data to the BST -a
    def add(self, key):

        if(self.root == None):
            self.root = Node(key)

        else:
            self._add(key, self.root)


    def _add(self, key, node):

        if(key < node.key):

            if(node.lelfChild != None):
                self._add(key, node.lelfChild)
            else:
                node.lelfChild = Node(key)

        else:
            if(node.rightChild != None):
                self._add(key, node.rightChild)
            else:
                node.rightChild = Node(key)




    # add key data to the BST -b 
    def addUsingKey(self, key):

        treenode = TreeNode(key)

        if self.root == None:
            self.root = treenode

        else:

            buff = self.root
            current =self.root

            while current != None:

                if current.key < treenode.key:

                    buff = current
                    current = current.right

                else:
                    buff = current
                    current = current.left

            if buff.key < treenode.key:
                buff.right = treenode

            else:
                buff.left = treenode



    def find(self, key):

        if(self.root != None):
            return self._find(key, self.root)

        else:
            return None


    def _find(self, key, node):

        if(key == node.key):
            return node

        elif(key < node.key and node.lelfChild != None):
            self._find(key, node.lelfChild)

        elif(key > node.key and node.rightChild != None):
            self._find(key, node.rightChild)


    def deleteTree(self):
        # garbage collector will do this for us. 
        self.root = None



    # in-order traverse of the binary search tree 1 
    def inOrderTraverse(self, node):

        if(node != None):

            self.inOrderTraverse(node.lelfChild)
            print str(node.key) + ' '
            self.inOrderTraverse(node.rightChild)



    # in-order traverse of the binary search tree 1 
    def in_order_search(self, btree):

        if btree is None: 
            return []

        return self.in_order_search(btree.left) + \
        [btree.content] + self.in_order_search(btree.right)


    def depth(self, node):

        if node is None:
            return 0
        else:
            return 1 + max( self.depth(node.lelfChild), self.depth(node.rightChild)) 


    def depth1(self, btree):

        if btree is None:
            return 0

        else:

            if btree.depth != -1:
                #caching depth
                #note that our cache of depth is not being updated using this
                #method, we would want to add a depth update mechanism whenever
                #a node is added/removed if we wanted to cache depth in this way.
                #if we didnt want to store this in the tree, we could cache it in a hash table (dict)
                return btree.depth

            else:
                btree.depth = 1 + max(depth(btree.left), depth(btree.right))
                return btree.depth


    # question 4-1: Implement a function to check if a binary tree is balanced. For the purposes of
    #this question, a balanced tree is defined to be a tree such that the heights of the
    #two subtrees of any node never differ by more than one.

    #O(n^2) naive algorithm
    def is_balanced(self, node):

        if node is None:
            return True 

        else: 
            return abs(self.depth(node.lelfChild) - self.depth(node.rightChild)) <= 1 \
            and self.is_balanced(node.lelfChild) and self.is_balanced(node.rightChild)


    # more effienet way for computing the balanced tree 
    def is_balanced_1(self, node):

        bol, dep = self.is_balanced_helper(node)
        return bol


    def is_balanced_helper(self, node):

        if node is None: 
            return True, 0

        left_balanced, left_depth = self.is_balanced_helper(node.lelfChild)
        right_balanced, right_depth = self.is_balanced_helper(node.rightChild)

        dep = 1 + max(left_depth, right_depth)

        return left_balanced and right_balanced and \
            (abs(self.depth(node.lelfChild) - self.depth(node.rightChild)) <= 1), dep



    #effcient algorithm, get heights of subtrees and check subtrees if balanced at the same time
    def is_balanced_binary_tree2(btree):
        return check_balanced(btree)[0]
        
    def check_balanced(btree):

        if btree is None: 
            return True, 0

        left_balanced, left_depth = check_balanced(btree.left)
        right_balanced, right_depth = check_balanced(btree.right)
        btree.depth = 1 + max(left_depth, right_depth)
        return left_balanced and right_balanced and \
            (abs(depth(btree.left) - depth(btree.right)) <= 1), btree.depth

    # END of solution 4-1
    # -------------------






    # question 4-3: create minimum BST  from a sorted   array

    # Solution - a
    # ------------
    def createBalancedTree1(intarray):

        #use the middle of the array to divide it. this ensures minimal height.
        if len(intarray) == 0:
            return None, None, None

        if len(intarray) == 1:
            return Node(intarray[0]), None, None

        cut = len(intarray) / 2

        return Node(intarray[cut]), \
            createBalancedTree1(intarray[0:cut]), \
            createBalancedTree1(intarray[cut+1:])

    # END of Solution a




    # Solution - b
    # ------------
    def createBalancedTree2(self, intarray):

        if len(intarray) == 0:
            return None

        if len(intarray) == 1:
            return Node(intarray[0])

        cut = len(intarray) / 2
        node =  Node(intarray[cut])

        node.leftChild = self.createBalancedTree2(intarray[0:cut])
        node.rightChild = self.createBalancedTree2(intarray[cut+1:])
 
        return node

    # END of soluton - b








    # question 4-4: Given a binary tree, design an algorithm which 
    # creates a linked list of all the nodes at each depth (e.g. if 
    # you have a tree with depth D , you'll have D linked lists).

    
    # Solution - a
    # ------------
    def binary_tree_to_list_of_linked_lists(self, btree, depth=1, returnlist=[]):

        if len(returnlist) >= depth:

            ll = LinkedList(btree.content)
            ll.next = returnlist[depth-1]
            returnlist[depth-1] = ll

        else:
            returnlist.append(LinkedList(btree.content))

        if not btree.left is None:        
            returnlist =  self.binary_tree_to_list_of_linked_lists(btree.left,depth+1,returnlist)

        if not btree.right is None:
            returnlist = self.binary_tree_to_list_of_linked_lists(btree.right,depth+1,returnlist)

        return returnlist

    # END of soluton - a


    # Solution - b
    # ------------
    def binary_tree_to_list(self, btree):

        if btree is None:
            return []

        ret = [[btree.content]]
        queue = [btree]

        while len(queue) > 0:

            new_queue = []

            for node in queue:

                if node.left is not None:
                    new_queue.append(node.left)

                if node.right is not None:
                    new_queue.append(node.right)

            queue = new_queue
            
            if len(queue) == 0:
                break

            ret.append([x.content for x in queue])

        return ret

    # END of soluton - b
    # ------------------









    # question 4-5: write an algorithm to check 
    # whether a binary search tree is valid 


    # Solution - a
    # ------------
    def valid_bsearch_tree(btree, lbound = -float("inf"), rbound = float("inf")):

        if btree is None: return True

        return (lbound <= btree.content < rbound) and \

            valid_bsearch_tree(btree.left, lbound, btree.content) and \
            valid_bsearch_tree(btree.right, btree.content, rbound)

    # END of soluton - a
    # ------------------




    # Solution - b
    # ------------
    def valid_bsearch_tree2(btree):

        #in-order method
        l = in_order_search(btree)

        if sorted(l) == l:
            return True

        return False

    # END of soluton - b
    # ------------------



    # solution  - c
    # ------------- 
    # min/max solution
    def checkBST(root,mini,maxi):

        if root == None:
            return True

        elif (mini != None and root.key <= mini) or (maxi != None and root.key > maxi):
            return False

        elif checkBST(root.left, mini, root.key) == False or checkBST(root.right, root.key, maxi) == False:
            return False

    return True    

    # END of soluton - c
    # ------------------
    # END of solution 4-5: write an algorithm to check 




    # question : copying bst to array, chacking 
    # if the arr is sorted is easy

    arr = []
    def copyBST(self, root,arr):

        if root == None: return 

        else:

            self.copyBST(root.left, arr)
            arr.append(root.key)
            self.copyBST(root.right,arr)


    # END of soluton : copying bst to array, 
    # chacking if the arr is sorted is easy









    # 4-6: write an algorithm to fint the  'next' node (i.e., in-order 
    # successor) of a given node in a binary search btre.
    #You may assume that each node has a link to its parent.

    def find_next_btree(btree):

        if btree is None: 
            return None

        if btree.right is None:

            #be careful here!
            # write a functio to get the parent of the node 
            # will have some value or the NULL

            ret = btree.parent
            while ret is not None and ret.content <= btree.content:
                ret = ret.parent

            return ret

        else:

            #find left most in right sub-tree
            ret = btree.right
            while ret.left is not None:
                ret = ret.left
            return ret


    # recursive solution
    def find_next_node(tree):

        assert isinstance(tree, Tree)

        if tree.is_leaf():

            print tree.value, tree.is_leaf()
            return tree.value

        if tree.right!=None:
            return find_next_node(tree.right)

        if tree.left!=None:
            return find_next_node(tree.left)




    # find the in-order successor of the BST 
    def inorderSucc(self, treenode):

        if treenode != None:

            if treenode.right != None:
                return leftMostChild(treenode.right)

            else:

                p = treenode.p

                while p != None:

                    if p.left == treenode:
                        break

                    else:

                        treenode = p
                        p = treenode.p

                return p
        return None




    # check the inorder succesor of the BST 
    def in_order_check(btree):

        if btree is None: return

        in_order_check(btree.left)
        print "current node", btree.content
        next_node = find_next_btree(btree)

        if next_node is None:
            print "next node None"

        else:
            print "next node", next_node.content
        in_order_check(btree.right)

    # END of solution : 4-6




    # question: find the left most children of the BST 
    def leftMostChild(self, treenode):

        if treenode == None:
            return None

        else:

            n = treenode
            child = treenode.left

            while child != None:
                n = child
                child = n.left
            return n
    # END of solution: find the left most children of the BST 




    # question 4-7: 

    # Solution follows the chain in which p and q are on the same side. When p and q are no longer on the same side
    # current node must be first common ancestor
    def covers(root, p):

        if root == None:
            return False

        else:

            if root == p:
                return True
            else:
                return covers(root.left, p) or covers(root.right, p)


    def commonAncestorHelper(root, p ,q):

        if root == None:
            return None

        elif root == p or root == q:
            return root

        else:

            is_p_on_left = covers(root.left, p)
            is_q_on_left = covers(root.left, q)

            if is_p_on_left != is_q_on_left:
                return root

            else:

                if is_p_on_left == True:
                    return commonAncestorHelper(root.left, p, q)

                else:
                    return commonAncestorHelper(root.right, p, q)


    def commonAncestor(root, p, q):

        if (not covers(root, p)) or (not covers(root, q)):
            return None

        else:
            return commonAncestorHelper(root, p, q)


    # Mehod 1: if nodes have links to parents, Go up levels to see whether 
    # a node's ancestor is another node's ancestor
    def commonAncestor_1(p, q):

        if p is None:
            return None

        if cover(p, q):
            return p

        current = p
        parent = current.parent

        while parent is not None:

            if (parent is q) or (parent.left is current and cover(parent.right, q))\
               or (parent.right is current and cover(parent.left, q)):
                return parent

            current = parent
            parent = parent.parent
        return None



    # check if n is a descendent of root
    def cover(root, n):

        if root is None:
            return False

        if root is n:
            return True

        return cover(root.left, n) or cover(root.right, n)



    # Method 2: if nodes have no links to parents.
    def commonAncestor_2(root, p, q):

        result = commonAncestor2_helper(root, p, q)

        if result[1]:
            return result[0]
        return None



    def commonAncestor2_helper(root, p, q):

        if root is None:
            return None, False

        if root is p and root is q:
            return root, True
        
        left = commonAncestor2_helper(root.left, p, q)

        if left[1] == True:     # Already found ancestor in the subtree
            return left
        
        right = commonAncestor2_helper(root.right, p, q)

        if right[1] == True:    # Already found ancestor in the subtree
            return right
        
        # If One of subtree return p and one of subtree return q, found common ancestor
        if left[0] != None and right[0] != None:
            return root, True
        
        # If root is p or q and one of the subtrees contain q or p, the root is common ancestor
        # If no p or q in the subtree, return root, False
        elif root is p or root is q:

            isAncestor = True if left[0] != None or right[0] != None else False
            return root, isAncestor
        
        # The rest condition:
        # 1) One of the subtree contains only p or q, another subtree is None, return p or q, False
        # 2) None of subtrees contain p or q, return None, False
        else:
            return left[0] if left[0] != None else right[0], False



    # question 4-8
    # ------------

    #You have two very large binary trees: Tl, with millions of nodes, and T2, with
    #hundreds of nodes. Create an algorithm to decide if T2 is a subtree of Tl.
    def count_children(tree1):

        left=0

        if tree1.left != None:
            left = count_children(tree1.left)

        right=0

        if tree1.right != None:
            right = count_children(tree1.right)

        tree1.children = 1+right+left
        return 1+right+left
                

    def is_subtree_of(t1,t2):    

        if t1==None and t2==None:
            return True

        elif t1==None or t2==None:        
            return False

        if t2.content==t1.content:
            if (is_subtree_of(t1.left, t2.left) and is_subtree_of(t1.right, t2.right)) or is_subtree_of(t1.left,t2) or is_subtree_of(t1.right,t2):       
                return True

        else:
            return is_subtree_of(t1.left,t2) or is_subtree_of(t1.right,t2)


    def isInc(r1, r2):

        if r2 == None:
            return True
        elif r1 == None:
            return False
        elif r1.key == r2.key:
            if matchTree(r1,r2):
                return True
        return isInc(r1.left, r2) or isInc(r1.right, r2)

    def matchTree(r1,r2):

        if r1 == None and r2 == None:
            return True
        elif r1 == None or r2 == None:
            return False
        elif r1.key != r2.key:
            return False
        else:
            return matchTree(r1.left, r2.left) and matchTree(r1.right, r2.right)
    # END of solution: 4-8 





    # question 4-9

    def findSum(node, givenSum, path=None, depth=0):

        if node is None:
            return

        if path is None:
            path = []

        if len(path) > depth:
            path[depth] = node.value

        else:
            path.append(node.value)
        
        # Look up to see if there are paths end up with this node and sum to the given value
        temp = 0

        for i in range(depth, -1, -1):

            temp += path[i]
            if temp == givenSum:
                printPath(path, i, depth)
        
        findSum(node.left, givenSum, path, depth+1)
        findSum(node.right, givenSum, path, depth+1)


    def printPath(path, start, end):

        for i in range(start, end + 1):
            print path[i],
        print ""

    # END of solution 4-9
    # -------------------

# =================== =================== =================== =================== =================== # 
# =================== =================== =================== =================== =================== # 
# =================== =================== =================== =================== =================== # 



# TESTING 
# =======







# testing : 4-1 
# =============

#Testing
#building testcase 1
bt = BinaryTree(random.randint(0, 100))

for c1 in xrange(0,19):

    bt2 = BinaryTree(random.randint(0, 100))
    bt2.left = bt
    bt=bt2

unbalanced_tree=bt



balanced_tree = make_random_balanced_tree(5)

#building testcase 3
#using http://people.eecs.ku.edu/~miller/Courses/268/Materials/Trees/BalancedBinaryTreeHeight10.png
#as an extreme case of a balanced tree that looks unbalanced.
balanced_tree2 = BinaryTree(random.randint(0, 100))
balanced_tree2.left = BinaryTree(random.randint(0, 100))
balanced_tree2.left.right = BinaryTree(random.randint(0, 100))
balanced_tree2.right = BinaryTree(random.randint(0, 100))
balanced_tree2.right.left = BinaryTree(random.randint(0, 100))
balanced_tree2.right.right = BinaryTree(random.randint(0, 100))
balanced_tree2.right.right.right = BinaryTree(random.randint(0, 100))



#building testcase 4
unbalanced_tree2 = BinaryTree(random.randint(0, 100))
unbalanced_tree2.left = BinaryTree(random.randint(0, 100))
unbalanced_tree2.left.right = BinaryTree(random.randint(0, 100))
unbalanced_tree2.right = BinaryTree(random.randint(0, 100))
unbalanced_tree2.right.left = BinaryTree(random.randint(0, 100))
unbalanced_tree2.right.right = BinaryTree(random.randint(0, 100))
unbalanced_tree2.right.right.right = BinaryTree(random.randint(0, 100))
#this unbalances it
unbalanced_tree2.right.right.right.right = BinaryTree(random.randint(0, 100))

#building testcase 5
unbalanced_tree3 = BinaryTree(random.randint(0, 100))
unbalanced_tree3.left = BinaryTree(random.randint(0, 100))
unbalanced_tree3.left.right = BinaryTree(random.randint(0, 100))
unbalanced_tree3.right = BinaryTree(random.randint(0, 100))
unbalanced_tree3.right.right = BinaryTree(random.randint(0, 100))
unbalanced_tree3.right.right.right = BinaryTree(random.randint(0, 100))


test_func = [is_balanced_binary_tree, is_balanced_binary_tree2]
for func in test_func:

    if not func(unbalanced_tree):
        print "Test 1 passed"
    else:
        print "Test 1 not passed"
    if func(balanced_tree):
        print "Test 2 passed"
    else:
        print "Test 2 not passed"
    if func(balanced_tree2):
        print "Test 3 passed"
    else:
        print "Test 3 not passed"
    if not func(unbalanced_tree2):
        print "Test 4 passed"
    else:
        print "Test 4 not passed"
    if not func(unbalanced_tree3):
        print "Test 5 passed"
    else:
        print "Test 5 not passed"

# END of testing 4-1 
# ------------------









# Question 4-2 
# ------------
# Given a directed graph, design an algorithm to find out whether there is a route
# between two nodes.

class Graph_Node:

    def __init__(self, data):

        self.data = data
        self.neighbours = []


def check_route(node_start, node_destination, visited = {}, checked = {}):
    
    if node_start == node_destination:
        return True

    if (str(hash(node_start))+ ',' + str(hash(node_destination))) in checked:
        return checked[(str(hash(node_start))+ ',' + str(hash(node_destination)))]

    nodeFound = False 

    for neighbour in node_start.neighbours:

        if neighbour in visited:
            continue

        visited[neighbour] = True

        nodeFound = check_route(neighbour, node_destination, visited, checked)

        if nodeFound:
            break

    checked[str(hash(node_start))+ ',' + str(hash(node_destination))] = nodeFound
    return nodeFound




#bfs algorithm
from Queue import deque

def  check_route1(node1, node2):

    if node1 is node2:
        return True

    elif node1 is None or node2 is None:
        return False

    visited = set([node1, node2])
    queue = deque([node1])

    while len(queue) > 0:

        # pop() is last in first out 
        # popleft() is first in first out 
        node = queue.popleft()

        for child in node.neighbours:

            if child is node2:
                return True

            elif child not in visited:
                visited.add(child)
                queue.append(child)

    return False


n1 = Graph_Node(1)
n2 = Graph_Node(2)
n3 = Graph_Node(3)
n4 = Graph_Node(4)
n5 = Graph_Node(5)
n6 = Graph_Node(6)


n1.neighbours.append(n2)
n2.neighbours.append(n3)
n2.neighbours.append(n4)
n4.neighbours.append(n5)
n4.neighbours.append(n1)

test_func = [check_route, check_route1]

for func in test_func:

    if func(n1,n5):
        print "Test 1 passed"

    if not func(n5,n1):
        print "Test 2 passed"

    if not func(n1,n6):
        print "Test 3 passed"

    print "\n\n"


# END of Question 4-2 
# -------------------








# 4-3 testing 
# -----------

intarray1=[1,2,3,4,5,6,7,8,9,10,12,15,18,22,43,144,515]
intarray2=[1,2,3,4,5,6,7,8,9,10,12,15,18,22,43,144,515,4123]


node, left, right = createBalancedTree1(intarray1)
node1, left1, right1 = createBalancedTree1(intarray2)
print str(node)
print str(node1)

print str(createBalancedTree2(intarray1))
print str(createBalancedTree2(intarray2))

# END of solution 4-3: create minimum BST from a sorted array 
# -----------------------------------------------------------











# testing 4-4
# ------------
balanced_tree = make_random_balanced_tree(5)

#testing
print balanced_tree
print "\nIs changed to:\n"


count=0
for ll in binary_tree_to_list_of_linked_lists(balanced_tree):

    count += 1
    print str(count) + ": " + str(ll)

print "\nlist version", binary_tree_to_list(balanced_tree)

# END of testing: 4-4
# -------------------











# 4-5 testing 
# -----------
T_set = [make_random_btree()] * 2 + [make_random_bsearch_tree()]*2

for T in T_set:

    if valid_bsearch_tree(T) == valid_bsearch_tree2(T):
        print "pass"

    else:
        print "wrong"

# END of testing 4-5 
# ------------------











# 4-6 testing 
# -----------

# test
bst1 = BinarySearchTree()
bst1.addUsingKey(0)
bst1.addUsingKey(1)
bst1.addUsingKey(-1)
bst1.addUsingKey(2)

print inorderSucc(bst1.root.right)
# END of testing 4-6
# ------------------









# 4-7 testing 
# -----------

root = BinaryTreeNode(10)

root.setLeft(5)
root.setRight(6)
root.left.setLeft(1)
root.left.setRight(2)
root.right.setLeft(3)
root.right.setRight(4)
root.left.right.setLeft(7)

# test case 1: node 7 and 6  --> Common ancestor: 10
# test case 2: node 2 and 5 --> Common ancestor: 5
# test case 3: node 6 and a node not in the tree  --> Common ancestor: None 
tests = [(root.left.right.left, root.right), (root.left.right, root.left), (root.right, BinaryTreeNode(9))]
methods = [commonAncestor_1, commonAncestor_2] 

for test in tests:

    for method in methods:

        if method == commonAncestor_1:
            ancestor = method(test[0], test[1])

        else:
            ancestor = method(root, test[0], test[1])
        print ancestor.value if ancestor is not None else None





# test
bst2 = BinarySearchTree()

n1 = TreeNode(0)
n2 = TreeNode(1)
n3 = TreeNode(-1)
n4 = TreeNode(2)
n5 = TreeNode(-2)

bst2.addNode(n1)
bst2.addNode(n2)
bst2.addNode(n3)
bst2.addNode(n4)
bst2.addNode(n5)

print commonAncestor(bst2.root, n5, n4)
# END of testing 4-7
# ------------------











# testing 4-7 
# -----------



# ------------------test--------------------
#             10
#            /  \
#           5    6
#          / \  / \
#         1  2  3  4
#           /
#          7

root = BinaryTreeNode(10)

root.setLeft(5)
root.setRight(6)
root.left.setLeft(1)
root.left.setRight(2)
root.right.setLeft(3)
root.right.setRight(4)
root.left.right.setLeft(7)

# test case 1: node 7 and 6  --> Common ancestor: 10
# test case 2: node 2 and 5 --> Common ancestor: 5
# test case 3: node 6 and a node not in the tree  --> Common ancestor: None 
tests = [(root.left.right.left, root.right), (root.left.right, root.left), (root.right, BinaryTreeNode(9))]
methods = [commonAncestor_1, commonAncestor_2] 

for test in tests:

    for method in methods:

        if method == commonAncestor_1:
            ancestor = method(test[0], test[1])

        else:
            ancestor = method(root, test[0], test[1])
        print ancestor.value if ancestor is not None else None   

# END of test: 4-7
# ----------------










# testing 4-8
# ------------


bst1 = BinarySearchTree()
for i in range(2):
    bst1.addUsingKey(i+2)

bst2 = BinarySearchTree()
for i in range(3):

    bst2.addUsingKey(i)
    bst2.addUsingKey(-i)

print isInc(bst1.root, bst2.root)
# -------------------------------





balanced_tree = make_random_balanced_tree(19)
sub_balanced_tree = balanced_tree.right.right.right


#testing
count_children(balanced_tree)
count_children(sub_balanced_tree)

print "t1 children: " + str(balanced_tree.children)
print "t2 children: " + str(sub_balanced_tree.children)

start=time.time()
if is_subtree_of(balanced_tree,sub_balanced_tree):
    print "Test case 1 passed"

timer = time.time() - start
print "Test case 1 time: " + str(timer)

start=time.time()
if not is_subtree_of(sub_balanced_tree,balanced_tree):
    print "Test case 2 passed"

timer2=time.time()-start
print "Test case 2 time: " + str(timer2)


balanced_tree = make_random_balanced_tree(20)
sub_balanced_tree = balanced_tree.right.right.right

count_children(balanced_tree)
count_children(sub_balanced_tree)
print "t1 children: " + str(balanced_tree.children)
print "t2 children: " + str(sub_balanced_tree.children)


start=time.time()
if is_subtree_of(balanced_tree,sub_balanced_tree):
    print "Test case 3 passed"

timer3 = time.time()-start
print "Test case 3 time: " + str(timer3)

start=time.time()
if not is_subtree_of(sub_balanced_tree,balanced_tree):
    print "Test case 4 passed"
timer4=time.time()-start
print "Test case 4 time: " + str(timer4)

print "Doubling size of input changed run time by a factor of " + str(timer3/timer) 
# END of testing 4-8
# -------------------











# testing 4-9
# -----------

# class BinaryTreeNode:

#     def __init__(self, value):

#         self.value = value
#         self.left = None
#         self.right = None
#         self.parent = None

#     def setLeft(self, leftvalue):

#         leftnode = BinaryTreeNode(leftvalue)
#         self.left = leftnode

#         if leftnode != None:
#             leftnode.parent = self

#     def setRight(self, rightvalue):

#         rightnode = BinaryTreeNode(rightvalue)
#         self.right = rightnode

#         if rightnode != None:
#             rightnode.parent = self



givenSum = 7

root = BinaryTreeNode(1)
root.setLeft(2)
root.setRight(3)
root.left.setLeft(4)
root.left.setRight(5)
root.right.setLeft(3)
root.right.setRight(4)
root.left.right.setLeft(7)

findSum(root, givenSum)

# END of testing: 4-9
# -------------------
















# make random BST from certain range of data 
#building testcase 1
def make_random_balanced_tree(depth):

    if depth>0:

        tree = BinaryTree(random.randint(0, 100))
        tree.left=make_random_balanced_tree(depth-1)
        tree.right=make_random_balanced_tree(depth-1)
        return tree

    else:
        return None





from random import randrange
def make_random_bsearch_tree1(depth = 2, l = -10, r = 10, parent = None):

    if depth < 0 or l == r: 
        return None

    tree = BinaryTree(randrange(l, r))
    tree.parent = parent
    tree.left = make_random_bsearch_tree(depth - 1, l, tree.content, tree)
    tree.right = make_random_bsearch_tree(depth - 1, tree.content, r, tree)
    return tree





def make_random_bsearch_tree(depth = 3, l = -10, r = 10):

    if depth < 0 or l == r: 
        return None

    tree = BinaryTree(randrange(l, r))
    tree.left = make_random_bsearch_tree(depth - 1, l, tree.content)
    tree.right = make_random_bsearch_tree(depth - 1, tree.content, r)
    return tree








def main():

        #     3
    # 0     4
    #   2      8

    tree = Tree()
    tree.add(3)
    tree.add(4)
    tree.add(0)
    tree.add(8)
    tree.add(115)
    tree.add(2)

    tree.inOrderTraverse(tree.root)  

    print "The depth of the tree is =", tree.depth(tree.root)  

    print "The tree is balanced ? ", tree.is_balanced(tree.root) 
    print "The tree is balanced ? ", tree.is_balanced_1(tree.root)   


if __name__ ==  '__main__':
    main()
    print ('done')


