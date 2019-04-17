//BY FUZIOUS

import java.util.*;

class BST {
    Node root;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        BST ob = new BST();
        int ch;
        do {
            System.out.println("Enter 1 to insert");
            System.out.println("Enter 2 to search");
            System.out.println("Enter 3 to print the tree");
            System.out.println("Enter 4 to delete an item");
            System.out.println("Enter 0 to exit");
            ch = sc.nextInt();
            if (ch == 1) {
                System.out.println("Enter value");
                ob.insert(sc.nextInt());
            } else if (ch == 2) {
                if (ob.contains(ob.root, sc.nextInt()))
                    System.out.println("yes");
                else
                    System.out.println("no");
            } else if (ch == 3)
                ob.printInorder(ob.root);
            else if (ch == 4) {
                System.out.println("Enter value");
                ob.delete(ob.root, sc.nextInt());
            }
        } while (ch != 0);
    }

    public static boolean contains(Node root, int val) {
        if (search(root, val) != null)
            return true;
        return false;
    }

    public static Node delete(Node root, int key) {
        Node parent = null;
        Node curr = root;
        while (curr != null && curr.key != key) {
            parent = curr;
            if (key < curr.key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (curr == null) {
            return root;
        }

        if (curr.left == null && curr.right == null) {
            if (curr != root) {
                if (parent.left == curr) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            else {
                root = null;
            }
        }

        else if (curr.left != null && curr.right != null) {
            Node son = minimum(curr.right);
            int val = son.key;
            delete(root, son.key);
            curr.key = val;
        }
        else {
            Node child = (curr.left != null) ? curr.left : curr.right;
            if (curr != root) {
                if (curr == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
            else {
                root = child;
            }
        }

        return root;
    }

    public static Node search(Node root, int val) {
        if (root == null || root.key == val)
            return root;
        else if (val < root.key)
            return (search(root.left, val));
        else
            return (search(root.right, val));
    }

    public static Node minimum(Node curr) {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public void insert(int val) {
        root = add(root, val);
    }

    public Node add(Node root, int val) {
        if (root == null)
            return (new Node(val));
        if (val < root.key)
            root.left = add(root.left, val);
        else if (val > root.key)
            root.right = add(root.right, val);
        return root;
    }

    public void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        System.out.print(node.key + " ");
        printInorder(node.right);
    }

    class Node {
        int key;
        Node left, right;

        Node(int k) {
            key = k;
            left = null;
            right = null;
        }
    }
}
