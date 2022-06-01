#include <iostream>
#include <unordered_map>
#include "myLinkedList.h"

using namespace std;


myLinkedList::myLinkedList(){
    this->head = nullptr;
}

myLinkedList::~myLinkedList(){

    Node* current = head;

    while (current != 0){

        Node* next = current->next;
        delete current;
        current = next;
    }

    head = nullptr;

    cout << "List successfully deleted from memory" << std::endl;
}




/*qustion: design an algorithm to insert 
new node to the tail of the linked list*/
void myLinkedList::insert(int value) {

    if (head == NULL){

        head = new Node();

        tail = head;
        head->next = nullptr;
        head->data = value;
    }
    
    else {
        tail->next = new Node();
        tail = tail->next;
        tail->data = value;
        tail->next = nullptr;
    }
}
/*END of solution: design an algorithm to insert 
new node to the tail of the linked list*/





/*qustion: design an algorithm to create 
cycle to the linked list*/
void myLinkedList::createCycle(){

    tail->next = head->next;  
}
/*END of solution: design an algorithm 
to create cycle to the linked list*/





/*qustion: design an algorithm to 
display the linked list*/
void myLinkedList::display(){

    Node* temp;

    if (head == NULL){
        return;
    }

    temp = head;

    while (temp != NULL){
        cout << temp->data << ' ';
        temp = temp->next;
    }

    cout << std::endl;
}
/*END of solution: design an algorithm 
to display the linked list*/










/*question 2-1: design an algorithm to remove 
the duplicates from an unsorted linked list*/



/*solution-a*/
// First implementation uses undordered_map run time is O(n) with O(n) space

void myLinkedList::removeDuplicates_1(Node* head){

    if (head == NULL){
        return;
    }

    unordered_map<int,bool> map;
    Node* current = head;
    map[current->data] = 1;

    while (current->next){

        if (map[current->next->data] == 0) {
            map[current->next->data] = 1;
            current = current->next;
        }

        else {

            Node* temp = current->next;
            current->next = current->next->next;  
            cout << "Duplicate Found: " << temp->data << " removed" << std::endl;
            delete temp;
        }
    }
}
/*END of solution-a*/






/*solution-b*/
// This implementation uses no additional data 
// structure run time is O(n^2) with O(1) space

void myLinkedList::removeDuplicates_2(Node* head) {

    if (head == NULL){
        return;
    }

    Node* current = head;
    Node* runner;

    while (current)
    {
        runner = current;
        
        while (runner->next){

            if (current->data == runner->next->data){

                Node* temp = runner->next;
                runner->next = runner->next->next;
                std::cout << "Duplicate Found: " << temp->data << " removed" << std::endl;
                delete temp;
            }

            else {
                runner = runner->next;
            }
        }

        current = current->next;
    }
}
/*END of solution-b*/


/*END of solution 2-1: design an algorithm to remove 
the duplicates from an unsorted linked list*/








/*question 2-2: desing an algorithm to get
the k-th number from last in a linked list*/

// this implementation runs in O(n) time with O(1) space
// the end of the list corresponds to k = 1
Node* myLinkedList::ktoLast(Node* head, int k){

    Node* current = head;
    int length = 0;

    // find the length of the list
    while (current)
    {
        length++;
        current = current->next;
    }
    
    // reset back to head
    current = head;
    
    if (k > length || k < 1){
        return nullptr;
    }

    int count = 0;

    while (length - count != k){
        count++;
        current = current->next;
    }

    return current;
}
/*END of solution 2-2: desing an algorithm 
to getthe k-th number from last in a linked 
list*/









/*question 2-3 :  design an algorithm to delete a node 
to middle of a singly linked list given only access to 
that node*/ 

// runs in O(1) time with O(1) space (note: this is 
// only true if you are not iterating through the list)

bool myLinkedList::removeNode(Node* node){

    if (node == NULL || node->next == NULL){
        return false;
    }

    // copy the next nodes information to the current
    // node and then delete the next node updating the pointers
    Node* temp = node->next;
    node->data = temp->data;
    node->next = temp->next;

    delete temp;

    return true;
}

/*END of solution 2-3 :  design an algorithm to 
delete a node to middle of a singly linked list 
given only access to that node*/ 








/*question 2-4 : desing an algorithm to 
partition a linked list around some value*/

// runs in O(n) time with O(n) space
// there are various ways to do this, but I end up deleting the original list
// and returning the merged lower and upper value lists

Node* myLinkedList::partition(Node* head, int val){

    if (head == NULL){
        return nullptr;
    }

    myLinkedList* upper = new myLinkedList();
    myLinkedList* lower = new myLinkedList();
    Node* current = head;

    while (current) {

        if (current->data < val){
            lower->insert(current->data);
        }

        else {
            upper->insert(current->data);
        }

        current = current->next;
    }


    // reset current back to the head
    current = lower->head;
    Node* lowNode = lower->head;

    // find the end of the lower list
    while (lowNode->next){

        lowNode = lowNode->next;
    }

    lowNode->next = upper->head;

    return current;
}

/*END of solution 2-4 : desing an algorithm to 
partition a linked list around some 
value*/










/*question 2-5: desing an algorithm to add 
values in two linked list Input keeping the 
length as same*/

int lenList(Node* head);
void padList(Node* head, int pad);

void myLinkedList::sumLinks(Node* list1, Node* list2){

    if (list1 == NULL && list2 == NULL){
        return;
    }

    int carry = 0;
    int val = 0;

    Node* l1 = list1;
    Node* l2 = list2;

    // determine if a list is longer and if so, pad that list
    int l1Len = lenList(l1);
    int l2Len = lenList(l2);

    if (l1Len > l2Len){
        padList(l2, l1Len - l2Len);
    }

    if (l2Len > l1Len){
        padList(l1, l2Len - l1Len);
    }

    while (l1 || l2){

        l1->data += carry;

        val = l1->data + l2->data;

        if (val > 9){
            carry = 1;
            val = val - 10;
        }

        else {
            carry = 0;
        }

        l1->data = val;

        l1 = l1->next;
        l2 = l2->next;
    }

  
    // if the final digit had a carry we need to add one to the end
    if (carry){

        // reset the head and find the last element
        l1 = list1;

        while (l1->next){
            l1 = l1->next;
        }

        l1->next = new Node();
        l1->next->data = 1;
        l1->next->next = nullptr;
    }
}


// helper function to display to list value normally
int myLinkedList::listValue(Node* head){

    int value = 0;
    double digit = 0;
    Node* temp = head;

    // I did this implementation because I wanted to avoid O(n) space
    while (temp){

        value += pow(10.0,digit) * temp->data;
        digit++;
        temp = temp->next;
    }

    return value;
}



// helper function to determine the length of a list
int myLinkedList::lenList(Node* head){    

    Node* temp = head;
    int len = 0;

    while (temp){
        temp = temp->next;
        len++;
    }

    return len;
}


// helper function to pad the list with a different length with 0 zeros
void myLinkedList::padList(Node* list, int pad){

    int count = 0;
    Node* temp = list;

    // get to the end of the list
    while (temp->next){
        temp = temp->next;
    }

    while (count != pad){

        temp->next = new Node();
        temp = temp->next;
        temp->data = 0;
        temp->next = nullptr;
        count++;
    }
}


// this is used for partB
Node* myLinkedList::reverse(Node* head){

    if (head == NULL){
        return nullptr;
    }

    Node* previous = nullptr;

    while (head){

        Node* temp = head->next;
        head->next = previous;
        previous = head;
        head = temp;
    }

    head = previous;

    return head;
}
/*END of solution 2-4: desing an algorithm to add 
values in two linked list Input keeping the 
length as same*/









/*question 2-6: design an algorithm to find 
the head of the circular linked list*/
// runs in O(n) time with O(1) space


/*solution-a*/
Node* myLinkedList::findCycleBegin(Node* head){

    if (head == NULL){
        return nullptr;
    }

    Node* slow = head;
    Node* fast = head;

    int diff = 0;

    while (fast != NULL && fast->next != NULL){

        slow = slow->next;
        fast = fast->next->next;

        // collision 
        if (slow == fast){
            break;
        }
    }

    // a check to make sure that there was in fact a loop
    if (fast == NULL || fast->next == NULL){
        return nullptr;
    }

    // slow is set to the head and then both are moved at the same pace
    slow = head;
    while (slow != fast){

        slow = slow->next;
        fast = fast->next;
    }

    // once they meet, they are at the start of the loop
    return slow;
}
/*END of solution-a*/





/*solution-b*/
Node* findCycleBegin1(Node* head){

    if (head == NULL) {
        return NULL;
    }

    Node* current = head;

    while (current->visited != true){
        current->visited = true;
        current = current->next;
    }

    return current;
}
/*END of solution-b*/

/*END of solution 2-6: design an algorithm to find 
the head of the circular linked list*/









/*question 2-7: design an algorithm to find 
whether a linked list is palindrome*/

// I am a fan of the reverse and compare approach so I will
// use that technique. Runs in O(n) time with O(n) space
Node* reverse(Node *head);


bool myLinkedList::isPalindrome(Node* head){

    Node* temp = head;
    myLinkedList* rev = new myLinkedList();

    int len = 0;
    int count = 0;

    // copy the list
    while (temp){

        rev->insert(temp->data);
        temp = temp->next;
        len++;
    }

    // reset to head
    temp = head;

    // reverse the list
    rev->head = reverseList(rev->head);
    Node* revHead = rev->head;

    // now compare the two but only up to half way
    while (count != len / 2){

        if (revHead->data != temp->data){
            return false;
        }

        revHead = revHead->next;
        temp = temp->next;
        count++;
    }

    return true;
}


// a helper function that reverses a linked list
Node* myLinkedList::reverseList(Node* head){

    if (head == NULL){

        return nullptr;
    }

    Node* previous = nullptr;

    while (head){

        Node* temp = head->next;
        head->next = previous;
        previous = head;
        head = temp;
    }

    head = previous;

    return head;
}
/*END of solution 2-7: design an algorithm to 
find whether a linked list is palindrome*/









int myLinkedList::run() {


    // printf("Hello world from the run function\n");
    
    /*question 2-1: design an algorithm to remove 
    the duplicates from an unsorted linked list*/

    /*myLinkedList* myList = new myLinkedList();
    myList->insert(5);
    myList->insert(7);
    myList->insert(12);
    myList->insert(7);
    myList->insert(16);
    myList->insert(16);
    myList->insert(25);
    myList->insert(11);
    myList->insert(5);

    cout << "The original list is: ";
    myList->display();

    // change to removeDuplicates_2 to run that implementation
    removeDuplicates_1(myList->head);

    cout << "The list with duplicated removed is: ";
    myList->display();
    delete myList;*/
    

    /*END of solution 2-1: design an algorithm 
    to remove the duplicates from an unsorted 
    linked list*/







    /*question 2-2: desing an algorithm 
    to getthe k-th number from last in a linked 
    list*/

    /*myLinkedList* myList = new myLinkedList();
    myList->insert(5);
    myList->insert(7);
    myList->insert(12);
    myList->insert(7);
    myList->insert(16);
    myList->insert(18);
    myList->insert(25);
    myList->insert(11);
    myList->insert(5);

    cout << "The list is: ";
    myList->display();

    cout << "The 4th to last element is: ";
    cout << ktoLast(myList->head, 4)->data << std::endl;
    delete myList;*/
    

    /*END of solution 2-2: desing an algorithm 
    to getthe k-th number from last in a linked 
    list*/






    /*question 2-3 :  design an algorithm to delete a node 
    to middle of a singly linked list given only access to 
    that node*/ 

    /*myLinkedList* myList = new myLinkedList();
    myList->insert(5);
    myList->insert(7);
    myList->insert(12);
    myList->insert(7);
    myList->insert(16);
    myList->insert(18);
    myList->insert(25);
    myList->insert(11);
    myList->insert(5);

    // just get a node in the list
    Node* temp = myList->head->next->next->next->next;

    cout << "The original list is: ";
    myList->display();

    cout << "Removing node with value: " << temp->data << std::endl;
    removeNode(temp);

    cout << "The new list is: ";
    myList->display();

    delete myList;*/

    /*END of solution 2-3 :  design an algorithm to delete a node 
    to middle of a singly linked list given only access to 
    that node*/ 










    /*question 2-4 : desing an algorithm to 
    partition a linked list around some 
    value*/

    /*myLinkedList* myList = new myLinkedList();
    myList->insert(5);
    myList->insert(14);
    myList->insert(17);
    myList->insert(22);
    myList->insert(16);
    myList->insert(18);
    myList->insert(2);
    myList->insert(1);
    myList->insert(5);

    cout << "The original list is: ";
    myList->display();

    Node* newHead = partition(myList->head, 13);
    delete myList;

    myLinkedList* newList = new myLinkedList();
    newList->head = newHead;

    cout << "The list partitioned around value 13 is: ";
    newList->display();
    delete newList;*/


    /*END of solution 2-4 : desing an algorithm to 
    partition a linked list around some 
    value*/









    /*question 2-5: desing an algorithm to add 
    values in two linked list Input keeping the 
    length as same*/

    /*myLinkedList* list1 = new myLinkedList();
    list1->insert(5);
    list1->insert(3);
    list1->insert(6);

    myLinkedList* list2 = new myLinkedList();
    list2->insert(2);
    list2->insert(1);
    list2->insert(6);

    std::cout << listValue(list1->head) << " + " << listValue(list2->head) << " = ";
    sumLinks(list1->head,list2->head);
    std::cout << listValue(list1->head) << std::endl;

    delete list1;
    delete list2;
    std::cout << std::endl;

    myLinkedList* list3 = new myLinkedList();
    list3->insert(7);
    list3->insert(4);
    list3->insert(2);
    list3->insert(8);
    list3->insert(9);

    myLinkedList* list4 = new myLinkedList();
    list4->insert(2);
    list4->insert(6);

    std::cout << listValue(list3->head) << " + " << listValue(list4->head) << " = ";
    sumLinks(list3->head,list4->head);
    std::cout << listValue(list3->head) << std::endl;
    delete list3;
    delete list4;



    myLinkedList* list5 = new myLinkedList();
    list5->insert(7);
    list5->insert(4);
    list5->insert(2);
    list5->insert(8);
    list5->insert(9);

    myLinkedList* list6 = new myLinkedList();
    list6->insert(2);
    list6->insert(6);

    std::cout << std::endl << "-- Now with lists that are originally in forward order --" << std::endl;
    list5->head = reverse(list5->head);
    list6->head = reverse(list6->head);
    std::cout << listValue(list5->head) << " + " << listValue(list6->head) << " = ";
    sumLinks(list5->head,list6->head);
    std::cout << listValue(list5->head) << std::endl;
    delete list5;
    delete list6;*/



    /*END of solution 2-5: desing an algorithm 
    to add values in two linked list Input keeping 
    the length as same*/










    /*question 2-6: design an algorithm to find 
    the head of the circular linked list*/

    /*myLinkedList* list1 = new myLinkedList();
    list1->insert(5);
    list1->insert(3);
    list1->insert(6);
    list1->insert(8);
    list1->insert(1);
    list1->insert(2);

    std::cout << "For the list: ";
    list1->display();
    std::cout << "A cycle begins at value: ";

    // create a cycle in the list
    list1->createCycle();

    Node* beginCycle = findCycleBegin(list1->head);
    std::cout << beginCycle->data << std::endl;*/


    /*END of solution 2-6: design an algorithm to find 
    the head of the circular linked list*/








    /*question 2-7: design an algorithm 
    to find whether a linked list is 
    palindrome*/

    /*myLinkedList* list1 = new myLinkedList();
    list1->insert(5);
    list1->insert(3);
    list1->insert(6);
    list1->insert(8);
    list1->insert(1);
    list1->insert(2);

    cout << "Is the list: ";
    list1->display();
    cout << "a palindrome: " << isPalindrome(list1->head) << std::endl;

    myLinkedList* list2 = new myLinkedList();
    list2->insert(5);
    list2->insert(3);
    list2->insert(6);
    list2->insert(6);
    list2->insert(3);
    list2->insert(5);

    std::cout << std::endl << "Is the list: ";
    list2->display();
    std::cout << "a palindrome: " << isPalindrome(list2->head) << std::endl;*/
    

    /*END of solution 2-7: design an algorithm 
    to find whether a linked list is palindrome*/


    return 0;
}


int main(int argc, char const *argv[])
{
    /* code */
    
    myLinkedList obj;
    return obj.run();
}