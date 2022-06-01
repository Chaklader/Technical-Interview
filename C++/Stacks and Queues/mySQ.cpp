#include<iostream>
#include<string>
#include<stack>
#include<climits>
#include<queue>



#define STACK_NUM 100

using namespace std;





/*3-1*/
struct node{

	int val, preIndex;
};


class stack{

    public:

    	stack(int totalSize = 900){

    		buf = new node[totalSize];
    		stop[0]=stop[1]=stop[2]=-1;

    		this->totalSize = totalSize;
    		cur=0;
    	}
    	
        ~stack(){
    		
            delete[] buf;
        }

        void push(int stackNum, int data){
        
        	if(cur>totalSize){
        
        		cout<<"Stack is full"<<'\n';
        	}
        
        	buf[cur].val = data;
        	buf[cur].preIndex = stop[stackNum];
        	stop[stackNum] = cur;
        	cur++;
        }

        void pop(int stackNum){
        
        	if(cur<=0){

        		cout<<"Stack is empty"<<'\n';
        	}

        	stop[stackNum] = buf[stop[stackNum]].preIndex;
        }


        int top(int stackNum){
        
        	return buf[stop[stackNum]].val;
        }


        bool empty(int stackNum){
        
        	return stop[stackNum]==-1;
        }

    private:

    	node *buf;
    	int stop[3];
    	int totalSize;
    	int cur;

};

/*END of solution 3-1*/





/*3-2*/

class stackWithMin{

	private:
		stack<int> s1, s2;

	public:

		void push(int x){
		
			s1.push(x);
		
			if(min()>=x){
		
				s2.push(x);
			}
		}

		void pop(){
		
			if(min()==s1.top()){

				s2.pop();
			}

			s1.pop();
		}
		
		int min(){
		
			if(s2.empty()){
				return INT_MAX;
			}
			return s2.top();
		}
		
		bool empty(){
		
			return s1.empty();
		}

		int top(){

			return s1.top();
		}

};
/*END of solution 3-2*/










/*question 3-3*/
class newStack{


    private:
    
        stack<int> *myStack;

        int threshold;
        int curStack;
    

    public:
    
        newStack(){
        
            threshold = 10;
            myStack = new stack<int>[STACK_NUM];
            curStack = 0;
        }

        void push(int data){
        
            if(myStack[curStack].size() >= threshold){
        
                curStack++;
            }
        
            myStack[curStack].push(data);
        }


        void pop(){
        
            if(myStack[curStack].empty()){
            
                if(curStack == 0){

                    cout<< "Stack is empty\n" << endl;
                    return;
                }
            
                curStack--;
            }

            myStack[curStack].pop();
        }


        int top(){

            if(myStack[curStack].empty()){

                if(curStack == 0){

                    return INT_MIN;
                }

                curStack--;
            }

            return myStack[curStack].top();
        }


        bool empty(){

            if(curStack == 0){

                return myStack[curStack].empty();
            }

            else
                return false;
        }

        void popAt(int index){

            if(myStack[index].empty()){

                cout<<"sub-stack "<<index<<" is empty"<<'\n';
                return;
            }
            myStack[index].pop();
        }

};
/*END of solution 3-3*/









/*3-4*/


void towerOfHanoi(stack<int> *a, stack<int> *buff, stack<int> *dest, int n){
 
    if(n==0){
 
        return;
    }
 
    towerOfHanoi(a, dest, buff, n-1);
 
    (*dest).push((*a).top());
    (*a).pop();
    towerOfHanoi(buff, a, dest, n-1);
}
/*END of solution 3-4*/









/*3-5*/
//======
class Queue {

    private:
        stack<int> pri, buff;
    
    public:
    
        void EnQueue(int data){

            while(!buff.empty()){

                pri.push(buff.top());
                buff.pop();
            }

            pri.push(data);
        }

    
        int DeQueue(){

            if(pri.empty() && buff.empty()){

                return INT_MIN;
            }
            
            while(!pri.empty()){

                buff.push(pri.top());
                pri.pop();
            }
            
            int tmp = buff.top();
            buff.pop();
            return tmp;
        }
    
        int size(){
    
            return pri.size() + buff.size();
        }
};
/*END of solution 3-5*/







/* question 3-6: */
void sortStack(stack<int> *st){


    stack<int> sorted;

    while(!(*st).empty()){

        int tmp = (*st).top();
        (*st).pop();

        while(!sorted.empty() && tmp > sorted.top()){

            (*st).push(sorted.top());
            sorted.pop();
        }

        sorted.push(tmp);
    }

    while(!sorted.empty()){

        (*st).push(sorted.top());
        sorted.pop();
    }
}
/*END of solution 3-6*/








/*3-7*/

class animal{


    private:
    
        queue<int> dog, cat;
        int order;
    
    public:
    
        animal(){
    
            order = 1;
        }
    
        void enque(string a){
    
            if(a=="dog"){

                dog.push(order);
                order++;
            }

            else if(a=="cat"){

                cat.push(order);
                order++;
            }

            return;
        }
    
        int dequeAny(){
    
            int tmp;
    
            if(dog.front()>cat.front()){
                tmp = cat.front();
                cat.pop();
            }
            
            else{
                tmp = dog.front();
                dog.pop();
            }
            return tmp;
        }
        
        int dequeDog(){
        
            if(dog.empty()){
                return INT_MIN;
            }
        
            int tmp = dog.front();
            dog.pop();
            return tmp;
        }

        int dequeCat(){
        
            if(cat.empty()){
                return INT_MIN;
            }
            int tmp = cat.front();
            cat.pop();
            return tmp;

        }

};
/*END of solution 3-7*/






int main(int argc, char const *argv[]){


	cout << "Stack and Queue" << endl;



	/*3-1*/
	/*stack mystack;

    for(int i=0; i<10; ++i)
        mystack.push(0, i);

    for(int i=10; i<20; ++i)
        mystack.push(1, i);

    for(int i=100; i<110; ++i)
        mystack.push(2, i);

    for(int i=0; i<3; ++i)
        cout<<mystack.top(i)<<" ";

    cout<<endl;

    for(int i=0; i<3; ++i){
    
        mystack.pop(i);
        cout<<mystack.top(i)<<" ";
    }
    
    mystack.push(0, 111);
    mystack.push(1, 222);
    mystack.push(2, 333);
    
    for(int i=0; i<3; ++i)
        cout<<mystack.top(i)<<" ";*/
	
    // =======
	/*3-1*/





    /*3-2*/
    //=====
    /*stackWithMin myStack;

	int arr[] = {6, 3, 5, 2, 2, 9, 2, 8, 1, 1};

	for(int i=0; i<10; i++){

		myStack.push(arr[i]);
		cout<<arr[i]<<" "<<myStack.min()<<'\n';
	}
	for(int i=0; i<10; i++){
		myStack.pop();
		cout<<i<<" "<<myStack.min()<<'\n';
	}*/
    /*3-2*/






	/* 3-3   */
	// =======

	/*newStack sta;
    
    for(int i=0; i<100; i++)
        sta.push(i);
    
    sta.popAt(4);
    
    for(int i=0; i<101; i++){
    
        cout<<sta.top()<<'\n';
        sta.pop();
    }*/
	/*3-3*/







    /*3-4*/
    //=====

    /*stack<int> primary, buffer, destination;

    int n = 10;

    for(int i=n; i>0; i--)
        primary.push(i);

    towerOfHanoi(&primary, &buffer, &destination, 10);

    while(!destination.empty()){

        cout<<destination.top()<<" ";
        destination.pop();
    }*/

    /*3-4*/
    //=====








    /*3-5*/
    //=====
    
    /*Queue q;
    
    for(int i=0; i<10; i++)
        q.EnQueue(i);
    
    cout<<q.DeQueue()<<'\n';
    cout<<q.DeQueue()<<'\n';
    
    for(int i=10; i<20; i++){
    
        q.EnQueue(i);
    }
    
    cout<<q.DeQueue()<<" ";
    cout<<q.DeQueue()<<'\n';*/

    /*3-5*/
    //======







    /*3-6*/

    /*stack<int> my;
    int arr[] = {6, 4, 8, 9, 10, 99, 7, 1, 0};

    for(int i=0; i<sizeof(arr)/sizeof(arr[0]); i++){

        my.push(arr[i]);
    }

    sortStack(&my);

    while(!my.empty()){
        cout<<my.top()<<'\n';
        my.pop();
    }*/
    /*3-6*/






    /*3-7*/
    //=======

    animal a;

    a.enque("dog");
    a.enque("cat");
    a.enque("dog");
    a.enque("dog");
    a.enque("cat");
    a.enque("dog");
    cout<<a.dequeAny()<<'\n';
    cout<<a.dequeDog()<<'\n';

    /*3-7*/
    // ======

	return 0;
}




