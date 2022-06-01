#ifndef __LINKEDLIST_h__
#define __LINKEDLIST_h__



struct Node{

    int data;
    Node* next;
    // bool visited;
};

class myLinkedList {

    public:

        Node* head;

        int run();

        myLinkedList();
        ~myLinkedList();

        void display();
        void insert(int value);
        void createCycle();



        /*question 2-1: design an algorithm to remove 
        the duplicates from an unsorted linked list*/
        
        void removeDuplicates_1(Node* head);
        void removeDuplicates_2(Node* head);
        

        /*END of solution 2-1: design an algorithm to 
        remove the duplicates from an unsorted linked 
        list*/






        /*question 2-2: desing an algorithm 
        to getthe k-th number from last in a linked 
        list*/


        Node* ktoLast(Node* head, int k);
        /*END of solution 2-2: desing an algorithm 
        to getthe k-th number from last in a linked 
        list*/




        /*question 2-3 :  design an algorithm to delete 
        a node to middle of a singly linked list given 
        only access to that node*/ 
        
        bool removeNode(Node* node);

        /*END of solution 2-3 :  design an algorithm to 
        delete a node to middle of a singly linked list 
        given only access to that node*/ 



        

        /*question 2-4 : desing an algorithm to 
        partition a linked list around some 
        value*/
        Node* partition(Node* head, int val);


        /*END of solution 2-4 : desing an algorithm to 
        partition a linked list around some 
        value*/






        /*question 2-5: desing an algorithm to add 
        values in two linked list Input keeping the 
        length as same*/

        void sumLinks(Node* list1, Node* list2);
        int listValue(Node* head);
        int lenList(Node* head);
        void padList(Node* list, int pad);
        Node* reverse(Node* head);
        
        /*END of solution 2-5: desing an algorithm to add 
        values in two linked list Input keeping the 
        length as same*/







        /*question 2-6: design an algorithm to find 
        the head of the circular linked list*/
        Node* findCycleBegin(Node* head);
        Node* findCycleBegin1(Node* head);

        /*END of solution 2-6: design an algorithm to find 
        the head of the circular linked list*/
    




        /*question 2-7: design an algorithm 
        to find whether a linked list is palindrome*/
        Node* reverseList(Node* head);
        bool isPalindrome(Node* head);
        /*END of solution 2-7: design an algorithm 
        to find whether a linked list is palindrome*/



    private:

        Node* tail;

}; 

#endif // __LINKEDLIST_h__