//BY FUZIOUS
import java.util.*;
import java.io.*;
class AVL {
    Node root;
    public static void main(String args[]) {
        InputReader sc = new InputReader(System.in);
        AVL ob = new AVL();
        int ch;
        do {
            out.println("Enter 1 to insert");
            out.println("Enter 2 to search");
            out.println("Enter 3 to print the tree");
            out.println("Enter 4 to delete an item");
            out.println("Enter 0 to exit");
            out.flush();
            ch = sc.nextInt();
            if (ch == 1) {
                out.println("Enter value");
                out.flush();
                ob.insert(sc.nextInt());
            } else if (ch == 2) {
                if (ob.contains(ob.root, sc.nextInt()))
                    out.println("yes");
                else
                    out.println("no");
            } else if(ch==3)
                ob.printInorder(ob.root);
            else if(ch==4){
                ob.root=ob.delete(ob.root,sc.nextInt());
            }

        } while (ch != 0);
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
    public static Node minimum(Node curr) {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
    public static boolean contains(Node root, int val) {
        if (search(root, val) != null)
            return true;
        return false;
    }
    public static Node search(Node root, int val) {
        if (root == null || root.key == val)
            return root;
        else if (val < root.key)
            return (search(root.left, val));
        else
            return (search(root.right, val));
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
        else
            return root;
        root.ht=1+Math.max(height(root.left),height(root.right ));
        int bal=bal(root);
        if(bal>1&&val<root.left.key){
            return rightRotate(root);
        }
        if(bal<-1&&val>root.right.key){
            return leftRotate(root);
        }
        if(bal>1&&val>root.left.key){
            root.left=leftRotate(root.left);
            return rightRotate(root);
        }
        if(bal<-1&&val<root.right.key){
            root.right=rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    public static int height(Node X){
        if(X==null)
            return 0;
        return X.ht;
    }
    public static int bal(Node X){
        if(X==null)
            return 0;
        else
            return (height(X.left)-height(X.right));
    }
    public void printInorder(Node node) {
        if (node == null)
            return;
        printInorder(node.left);
        out.print(node.key + " ");
        out.flush();
        printInorder(node.right);
    }
    public static Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.ht = Math.max(height(x.left), height(x.right)) + 1;
        y.ht = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
    public static Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.ht = Math.max(height(y.left), height(y.right)) + 1;
        x.ht = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }
    class Node {
        int key,ht;
        Node left, right;

        Node(int k) {
            key = k;
            left = null;
            right = null;
            ht=1;
        }
    }
    static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[8192];
        private int curChar, snumChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public int[] nextIntArray(int n) {
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }
            return a;
        }

        public String readString() {
            int c = snext();
            while (isSpaceChar(c)) {
                c = snext();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public String nextLine() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = snext();
            } while (!isEndOfLine(c));
            return res.toString();
        }

        public double nextDouble() {
            return (Double.parseDouble(readString()));
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
    static PrintWriter out = new PrintWriter(System.out);
}
