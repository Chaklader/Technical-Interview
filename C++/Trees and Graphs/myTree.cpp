#include<iostream>
#include<cmath>
#include<cstdio>
#include<queue>
#include<cstring>
#include<vector>
#include<list>
#include<stack>


using namespace std;
const int MAX = 100;


struct node{

    int data;
    node *right, *left;
};



/*quesion: design a algorithm to 
insert data to the binary tree*/   
void insertNode(node* &head, int data){

    if(head==NULL){

        node *p = new node;
        p->data = data;
        p->left = NULL;
        p->right = NULL;
        head = p;
        return;
    }

    if(data<=head->data){

        if(head->left==NULL){

            node *p = new node;
            p->data = data;
            p->left = NULL;
            p->right = NULL;
            head->left = p;
            return;
        }

        insertNode(head->left, data);
    }

    else{

        if(head->right==NULL){

            node *p = new node;
            p->data = data;
            p->left = NULL;
            p->right = NULL;
            head->right = p;
            return;
        }

        insertNode(head->right,data);
    }
}
/*END of solution: design a algorithm to 
insert data to the binary tree*/ 








/*quesion: design a algorithm to 
delete data from the binary tree*/
void deleteNode(int data){


    // delete the  node from the binary tree 
}
/*END of solution: design a algorithm to 
delete data from the binary tree*/










/*quesion: design a algorithm to get 
the height of the binary tree*/    

int height(node* root){

    if(root==NULL)

        return 0;
    return max(height(root->left), height(root->right))+1;
}

/*END of solution: design a algorithm to get 
the height of the binary tree*/  









/*quesion 4-1 : design a algorithm to 
check whther a binary tree is balanced*/    

bool isBalanced(node* root){

    if(root==NULL)
        return true;

    int diff = height(root->left) - height(root->right);

    if(abs(diff)>1)
        return false;
    else
        return (isBalanced(root->left) && isBalanced(root->right));
}
/*END of solution 4-1 : design a algorithm to 
check whther a binary tree is balanced*/    







/*question 4-2:  Given a directed graph, design an 
algorithm to  find out  whether  there  is a route 
between two nodes*/

bool isRoute(int a, int b, bool graph[][MAX]){

    queue<int> Que;

    bool visited[MAX];
    memset(visited, false, sizeof(visited));

    Que.push(a);
    visited[a] = true;

    int tmp;

    while(!Que.empty()){
   
        tmp = Que.front();
        Que.pop();
   
        for(int i=0; i<MAX; i++){
   
            if(graph[tmp][i] && !visited[i]){
   
                if(b==i)
                    return true;
                Que.push(i);
                visited[i]=true;
            }
        }
    }
    return false;
}

/* ENd solution 4-2 Given a directed graph, 
design an algorithm to find out whether there 
is a route be- tween two nodes. */









/*question 4-3: create minimum 
BST  from  a  sorted   array*/

void createBst(node* &root, int arr[], int start, int end){

    if(start>end)
        return;
    
    if(root==NULL){

        node *ptr = new node;
        int ind = start + (end-start)/2;

        ptr->data = arr[ind];
        ptr->left = NULL;
        ptr->right = NULL;
        root = ptr;

        createBst(root->left, arr, start, ind-1);
        createBst(root->right, arr, ind+1, end);
    }
}

/*END of solution 4-3: create minimum 
BST  from  a  sorted   array*/








/*question 4-4 create linked list 
of the same level of the tree*/

void findLevelLinkedLists(vector<list<node*> > &res, node* root){

    list<node*> li;
    li.push_back(root);

    res.push_back(li);
    int depth = 0;
    
    while(!res[depth].empty()){
    
        list<node*> l;
        list<node*>::iterator iter;

        for(iter = res[depth].begin(); iter!=res[depth].end(); iter++ ){
        
            node *ptr = *iter;
        
            if(ptr->left)
                l.push_back(ptr->left);
        
            if(ptr->right)
                l.push_back(ptr->right);
        }

        res.push_back(l);
        depth++;
    }
}


void createMinimalBst(node* &root, int arr[], int start, int end){

    if(start>end)
        return;
    
    if(root==NULL){

        node *ptr = new node;
        int ind = start + (end-start)/2;

        ptr->data = arr[ind];
        ptr->left = NULL;
        ptr->right = NULL;
        root = ptr;

        createMinimalBst(root->left, arr, start, ind-1);
        createMinimalBst(root->right, arr, ind+1, end);
    }
}


void printLevelLinkedLists(vector<list<node*> > res){

    vector<list<node*> >::iterator iter;

    for(iter = res.begin(); iter!= res.end(); iter++){

        list<node*> li = *iter;
        list<node*>::iterator it;
        for(it = li.begin(); it!=li.end(); it++){
            node* ptr = *it;
            cout<<ptr->data<<" ";
        }
        cout<<'\n';
    }
}
/*END of solution 4-4: create linked 
list of the same level of the tree*/










/*question 4-5: design an algorithm check 
whether binary search tree is valid*/

bool isBst(node* root, int lower, int upper){

    if(root==NULL){

        return true;
    }

    if(root->data<=upper && root->data>=lower){

        return (isBst(root->left, lower, root->data) && isBst(root->right, root->data+1, upper));
    }

    else
        return false;
}

/*END of solution 4-5: design an algorithm check 
whether binary search tree is valid*/











/*question 4-6: design an algorithm to find 
the ‘next’ node (e.g., in-order successor) of 
a given node in a binary search tree*/


/*
node* inOrderSuccessorWithParent(node* root, node* ptr){
    if(ptr->right){
        node *p = ptr;
        while(p->left){
            p = p->left;
        }
        return p;
    }

    else{

        if(ptr==ptr->parent->left){
            return ptr->parent;
        }

        else{
            while(ptr!=ptr->parent->left){
                ptr = ptr->parent;
            }
            return ptr->parent;
        }
    }
}
*/

node* inOrderSuccessor(node* root, int k){

    stack<node*> S;
    node *ptr = root;
    bool searched = false;

    while(true){

        while(ptr){

            S.push(ptr);
            ptr = ptr->left;
        }

        if(S.empty())
            break;

        ptr = S.top();
        S.pop();

        //cout<<ptr->data<<" ";

        if(searched)
            return ptr;

        if(ptr->data==k){
            searched = true;
        }

        ptr = ptr->right;
    }

    return false;
}

/*END of solution 4-6: design an algorithm to find 
the ‘next’ node (e.g., in-order successor) of 
a given node in a binary search tree*/









/*question 4-7: find out the 
common ancestor of the two nodes*/

node* commonAncestor(node *root, node *root1, node *root2){

    if(root==NULL)
        return NULL;
    
    if(root == root1 || root == root2)
        return root;
    
    bool root1_on_left = isDecendent(root->left, root1);
    bool root2_on_left = isDecendent(root->left, root2);
    
    if(root1_on_left && root2_on_left)
        return commonAncestor(root->left, root1, root2);

    else if(root1_on_left || root2_on_left)
        return root;

    else
        return commonAncestor(root->right, root1, root2);
}


//root1 is decendent of root
bool isDecendent(node *root, node *root1){

    if(root == NULL)
        return false;
    if(root == root1)
        return true;
    return isDecendent(root->left, root1) || isDecendent(root->right, root1);
}

/*END of solution 4-7: find out the 
common ancestor of the two nodes*/








/*question 4-8: two very large binary 
trees: T1, with millions of nodes, and 
T2, with hundreds of nodes. Create an 
algorithm to decide if T2 is a subtree 
of T1.*/

bool isIdentical(node* root1, node* root2){

    if(root1 == NULL && root2 == NULL)
        return true;

    if(root1 == NULL || root2 == NULL)
        return false;

    if(root1->data == root2->data && isIdentical(root1->left, root2->left) && isIdentical(root1->right, root2->right))
        return true;
    return false;
}

bool isSubTree(node* root, node* subRoot){

    if(root == NULL && subRoot == NULL)
        return true;
    if(root == NULL || subRoot == NULL)
        return false;
    return isIdentical(root, subRoot) || isSubTree(root->left, subRoot) || isSubTree(root->right, subRoot);
}

/*END of solution 4-8: two very large binary 
trees: T1, with millions of nodes, and 
T2, with hundreds of nodes. Create an 
algorithm to decide if T2 is a subtree 
of T1.*/








/*question 4-9: design an algorithm to 
find all the paths of a BST which sums 
are equal to certain value*/

void printIfSum(int* arr, int sum, int len){

    for(int i=0; i<len; i++){
    
        int s=0;
    
        for(int j=i; j<len; j++){
            s += arr[j];
            if(s == sum){
                for(int k=i; k<=j; k++)
                    cout<<arr[k]<<" ";
                cout<<'\n';
            }
        }
    }
}

void print(node *root, int sum, int *path, int level){

    if(root==NULL){
        return;
    }
    
    path[level] = root->data;
    
    int s = 0;
    
    for(int i=level; i>=0; i--){
    
        s += path[i];
    
        if(s==sum){
    
            for(int j=level; j>=i; j--)
                cout<<path[j]<<" ";
            cout<<'\n';
        }
    }

    if(root->left)
        print(root->left, sum, path, level+1);

    if(root->right)
        print(root->right, sum, path, level+1);
}
/*END of solution 4-9: design an algorithm to 
find all the paths of a BST which sums 
are equal to certain value*/





string result(bool value){

    if (value) {
        
        return "True";
    }

    return "False";
}




int main(){





    /*quesion 4-1 : design a algorithm to 
    check whther a binary tree is balanced*/ 
  
    /*node *root;
    root = NULL;
    int arr[] = {4, 7, 2, 1, 6, 8};

    for(int i=0; i<sizeof(arr)/sizeof(arr[0]); i++)
        insertNode(root, arr[i]);
    
    cout<<"The result is = "<<result(isBalanced(root))<<endl;*/
    
    /*END of solution 4-1 : design a algorithm to 
    check whther a binary tree is balanced*/ 



    


    /*question 4-2:  Given a directed graph, design an 
    algorithm to  find out  whether  there  is a route 
    between two nodes*/
    
    /*freopen("data.in", "r", stdin);
    int n, m, u, v;
    bool dgraph[MAX][MAX];
    cin>>n>>m;
    for(int i=0; i<m; i++){
    
        cin>>u>>v;
        dgraph[u][v] = true;
    }

    cout<<"The result is = " << result(isRoute(0, 3, dgraph)) <<endl;*/


    /*END of solution 4-2:  Given a directed graph, design an 
    algorithm to  find out  whether  there  is a route 
    between two nodes*/








    /*question 4-3: create minimum 
    BST  from  a  sorted   array*/
    

    /*int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    node* root;
    root = NULL;
    createBst(root, arr, 0, 8);
    cout<<root->left->data<<" "<<root->data<<" "<<root->right->data<<'\n';*/
    
    /*END of solution 4-3: create minimum 
    BST  from  a  sorted   array*/




    



    /*question 4-4 create linked list 
    of the same level of the tree*/
    

    /*int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    node* root;
    root = NULL;
    
    createMinimalBst(root, arr, 0, 8);
    vector<list<node*> > res;
    findLevelLinkedLists(res, root);
    printLevelLinkedLists(res);*/
    

    /*END of solution 4-4 create linked list 
    of the same level of the tree*/







    
    /*question 4-5: design an algorithm check 
    whether binary search tree is valid*/
    

    // int arr[] = {1, 2, 2, 4, 5, 6, 7, 8, 9};    //Not a BST
    /*int arr[] = {2, 2, 3, 4, 5, 6, 7, 8, 9};    //is BST
    node* root;
    root = NULL;
    createMinimalBst(root, arr, 0, 8);
    cout<<"The tree is BST ? "<<result(isBst(root, INT_MIN, INT_MAX))<<endl;*/
    

    /*END of solution 4-5: design an algorithm check 
    whether binary search tree is valid*/









    /*question 4-6: design an algorithm to find 
    the ‘next’ node (e.g., in-order successor) of 
    a given node in a binary search tree*/

    /*int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    node* root;
    root = NULL;
    
    createMinimalBst(root, arr, 0, 8);
    node* searchedNode = inOrderSuccessor(root, 4);
    cout<<searchedNode->data<<'\n';*/
    
    /*END of solution 4-6: design an algorithm to find 
    the ‘next’ node (e.g., in-order successor) of 
    a given node in a binary search tree*/









    /*question 4-7: find out the 
    common ancestor of the two nodes*/

    /*int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    node* root;
    root = NULL;
    createMinimalBst(root, arr, 0, 8);
    node *ancestor = commonAncestor(root, root->left->left, root->left->right->right);
    cout<<ancestor->data<<endl;*/

    /*END of solution 4-7: find out the 
    common ancestor of the two nodes*/









    /*question 4-8: two very large binary 
    trees: T1, with millions of nodes, and 
    T2, with hundreds of nodes. Create an 
    algorithm to decide if T2 is a subtree 
    of T1.*/

    /*int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};    // Bigger Tree
    int arr1[] = {1, 2, 3, 4};                  // Subtree
    node *root, *subRoot;
    root = subRoot = NULL;

    createMinimalBst(root, arr, 0, 8);
    createMinimalBst(subRoot, arr1, 0, 3);
    //cout<<isIdentical(root->left->left->left, subRoot->left);
    cout<<isSubTree(root, subRoot)<<endl;*/

    /*END of solution 4-8: two very large binary 
    trees: T1, with millions of nodes, and T2, 
    with hundreds of nodes. Create an algorithm 
    to decide if T2 is a subtree of T1.*/







    /*question 4-9: design an algorithm to 
    find all the paths of a BST which sums 
    are equal to certain value*/

    /*int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    node *root;

    root = NULL;
    createMinimalBst(root, arr, 0, 8);
    
    int path[100];
    print(root, 7, path, 0);*/
    
    /*END of solution 4-9: design an algorithm to 
    find all the paths of a BST which sums 
    are equal to certain value*/

}
