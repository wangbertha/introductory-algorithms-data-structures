public class BinarySearchTree {

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = new Node(-1);
        size = 0;
    }

    // MODIFICATION METHODS
    // insert value into tree
    public void insert(int val) {
        Node n = new Node(val);
        Node tr = root;
        boolean i = true;
        if(size==0) {
            root = n;
            size++;
        }
        else {
            while(i) {
                if(tr.value>val) {
                    if(tr.left==null) {
                        tr.left = n;
                        i = false;
                        size++;
                    }
                    else tr = tr.left;
                }
                else {
                    if(tr.right==null) {
                        tr.right = n;
                        i = false;
                        size++;
                    }
                    else tr = tr.right;
                }
            }
        }
    }
    public boolean deleteNode(int val) {
        Node n = getNode(val);
        if (n == null)
            return false;
        // no children
        if (isLeaf(n)) {
            return deleteLeaf(n);
        }
        // 1 child
        else if (n.left == null ^ n.right == null) {
            return deleteOneChild(n);
        }
        // 2 children
        else {
            return swapDown(n);
        }
    }
    private boolean deleteOneChild(Node n) {
        Node nParent = getNodeParent(n);
        if (n == root) {
            if (n.left == null)
                root = n.right;
            else
                root = n.left;
        } else if (nParent.left == n) {
            if (n.left == null)
                nParent.left = n.right;
            else
                nParent.left = n.left;
        } else {
            if (n.left == null)
                nParent.right = n.right;
            else
                nParent.right = n.left;
        }
        size--;
        return true;
    }
    private boolean swapDown(Node n) {
        // find node to swap
        Node nToSwap = getNodeSuccessor(n);
        n.value = nToSwap.value;
        // check if delete conditions are met; delete if true
        if (isLeaf(nToSwap)) {
            return deleteLeaf(nToSwap);
        } else if (nToSwap.left == null ^ nToSwap.right == null) {
            return deleteOneChild(nToSwap);
        } else {
            swapDown(nToSwap);
        }
        return false;
    }
    // delete subtree with root Node val
    public boolean deleteTree(int val) {
        Node n = getNode(val);
        // if val is not in the tree, return false
        if (n == null)
            return false;
        deleteTree(n);
        return true;
    }
    private void deleteTree(Node n) {
        if (n == null)
            return;
        deleteTree(n.left);
        deleteTree(n.right);
        // If n is a leaf
        if (isLeaf(n))
            deleteLeaf(n);
    }
    private boolean deleteLeaf(Node n) {
        if (n == root) {
            root = new Node(-1);
            size = 0;
            return true;
        }
        if (!isLeaf(n))
            return false;
        Node p = getNodeParent(n);
        if (p.value > n.value) {
            p.left = null;
            size--;
        } else {
            p.right = null;
            size--;
        }
        return true;
    }

    // TREE PROPERTY METHODS
    // get count of values stored
    public int size() {
        return size;
    }
    // returns the height of the tree (a node is 1 height)
    public int height() {
        if (size == 0)
            return 0;
        return height(root);
    }
    private int height(Node n) {
        if (n == null)
            return -1;
        if (n.left == null & n.right == null)
            return 1;
        return Math.max(height(n.left), height(n.right)) + 1;
    }
    public int getMin() {
        if (size == 0)
            return -1;
        Node tr = root;
        while (tr.left != null)
            tr = tr.left;
        return tr.value;
    }
    public int getMax() {
        if (size == 0)
            return -1;
        Node tr = root;
        while (tr.right != null)
            tr = tr.right;
        return tr.value;
    }

    // IDENTIFICATION METHODS
    // returns Node if value is in tree
    private Node getNode(int val) {
        Node tr = root;
        while (tr.value != val) {
            if (tr.value > val) {
                if (tr.left == null)
                    return null;
                tr = tr.left;
            } else {
                if (tr.right == null)
                    return null;
                tr = tr.right;
            }
        }
        return tr;
    }
    private Node getNodeParent(Node n) {
        if(n==root) return null;
        int val = n.value;
        Node tr = root;
        Node trParent = root;
        while (tr!=n) {
            if (tr.value > val) {
                if (tr.left == null) return null;
                trParent = tr;
                tr = tr.left;
            } else {
                if (tr.right == null) return null;
                trParent = tr;
                tr = tr.right;
            }
        }
        return trParent;
    }
    public int getValSuccessor(int val) {
        Node n = getNodeSuccessor(getNode(val));
        if (n == null)
            return -1;
        return n.value;
    }
    private Node getNodeSuccessor(Node n) {
        Node tr = n;
        if (tr == null)
            return null;
        if (tr.right != null) {
            tr = tr.right;
            while (tr.left != null)
                tr = tr.left;
            return tr;
        } else {
            Node p = getNodeParent(tr);
            if (p == null)
                return null;
            while (p.value < n.value) {
                p = getNodeParent(p);
                if (p == null)
                    return null;
            }
            return p;
        }
    }
    private boolean isLeaf(Node n) {
        if (n.left == null & n.right == null)
            return true;
        return false;
    }
    // returns Node of the value; if not, returns null
    public boolean contains(int val) {
        Node tr = getNode(val);
        if (tr == null)
            return false;
        else
            return true;
    }
    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root);
    }
    private boolean isBinarySearchTree(Node n) {
        // Check left node
        if(n.left!=null) {
            if(n.value>n.left.value) isBinarySearchTree(n.left);
            else return false;
        }
        // Check right node
        if(n.right!=null) {
            if(n.value<=n.right.value) isBinarySearchTree(n.right);
            else return false;
        }
        return true;
    }

    // PRINT METHODS
    // returns values of tree, from min to max
    public String toString() {
        if(size==0) return "No items in tree.";
        return toSubString(root);
    }
    private String toSubString(Node subRoot) {
        String str = "";
        if(subRoot.left!=null) str=str+toSubString(subRoot.left);
        str=str+subRoot.value+" ";
        if(subRoot.right!=null) str=str+toSubString(subRoot.right);
        return str;
    }

    // NODE CLASS
    private static class Node {
        private Node left;
        private Node right;
        private int value;

        private Node(int val) {
            left = null;
            right = null;
            value = val;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(15);
        bst.insert(12);
        bst.insert(20);
        bst.insert(13);
        bst.insert(6);
        bst.insert(8);
        bst.insert(5);
        bst.insert(7);
        bst.insert(9);
        bst.deleteNode(6);
        System.out.println(bst.size());
        System.out.println(bst.toString());
        System.out.println("Is BST: " + bst.isBinarySearchTree());
    }
}