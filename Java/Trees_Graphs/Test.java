import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Node {

    public int key;

    public Node leftChild;
    public Node rightChild;

    Node(int key) {
        this.key = key;
    }

    Node() {
    }

    public String toString() {
        return "\n" + key + " ";
    }
}

public class Test {


    private static Node root = null;


    public static void addNode(int key) {

        Node newNode = new Node(key);

        if (root == null) {
            root = newNode;
        } else {

            Node focusNode = root;
            Node parent;

            while (true) {

                parent = focusNode;

                if (key < focusNode.key) {

                    focusNode = focusNode.leftChild;

                    if (focusNode == null) {

                        parent.leftChild = newNode;
                        return;
                    }
                } else {

                    focusNode = focusNode.rightChild;

                    if (focusNode == null) {

                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public static void inOrderTraverseTree(Node focusNode) {

        if (focusNode != null) {

            inOrderTraverseTree(focusNode.leftChild);
            System.out.print( focusNode );
            inOrderTraverseTree(focusNode.rightChild);
        }
    }

    public static void main(String[] args) {

        int[] A = {555, 876, 100, 90, 5, 3, 1, 4, 8, 45, 77, 2, 6, 56};

        for (int i = 0; i < A.length; i++) {
            addNode(A[i]);
        }

        inOrderTraverseTree(root);

    }
}


