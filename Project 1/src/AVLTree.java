

/**
 * AVLTree
 * <p>
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 */
public class AVLTree {

    private AVLNode Root = null;

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() { // check if tree is empty <--> if Root is null
        return Root == null;
    }

    /**
     * public String search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    public String search(int k) { // search for the node with key = k in the tree and returns the value using nodeSearch()
        AVLNode node = nodeSearch(k);
        if (Root == null) {
            return null;

        }
        if (node.getKey() != k) { // check if we got the actual node or its father or null
            return null;
        }
        return node.getValue();// if the key k is in the tree we return its value
    }

    private AVLNode nodeSearch(int k) { // search for the node with key = k in the tree and returns the node
        if (Root == null) {
            return null;
        }

        AVLNode node = Root;
        while (true) {
            if (node.getKey() == k) {
                return node; // if we found the node with key = k then we return the node
            }
            if (node.getKey() < k && node.right != null) { // if node.getKey < k we go to the right son go the node
                node = node.right;
            } else if (node.getKey() > k && node.left != null) { // if node.getKey > k we go to the left son go the node
                node = node.left;
            } else {
                return node; // if there isnt anywere alse to go and the key k is not in the tree we return its father
            }
        }
    }

    /**
     * public int insert(int k, String i)
     * <p>
     * inserts an item with key k and info i to the AVL tree.
     * the tree must remain valid (keep its invariants).
     * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
     * returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, String i) {
        AVLNode newNode = new AVLNode(k, i);
        if (Root == null) {
            Root = newNode;
            return 0;
        }
        AVLNode node = nodeSearch(k);
        if (node.getKey() == k) {
            return -1;
        }
        return 0; // TODO
    }

    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there;
     * the tree must remain valid (keep its invariants).
     * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
     * returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k) {
        return 42;    // to be replaced by student code
    }

    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty
     */
    public String min() {// returns the value of the node with the smallst key
        if (Root == null) {
            return null;
        }
        AVLNode node = Root;
        while (node.getLeft() != null) {// we go left until there isnt any a left son the the node
            node = node.getLeft();
        }
        return node.getValue();
    }

    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty
     */
    public String max() {
        if (Root == null) {
            return null;
        }
        AVLNode node = Root;
        while (node.getRight() != null) {// we go right until there isnt any a right son the the node
            node = node.getRight();
        }
        return node.getValue();
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray() {
        int[] arr = new int[42]; // to be replaced by student code
        return arr;              // to be replaced by student code
    }

    /**
     * public String[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public String[] infoToArray() {
        String[] arr = new String[42]; // to be replaced by student code
        return arr;                    // to be replaced by student code
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     * <p>
     * precondition: none
     * postcondition: none
     */
    public int size() {
        return Root.getSize();
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     * <p>
     * precondition: none
     * postcondition: none
     */
    public IAVLNode getRoot() {
        return this.Root;
    }

    /**
     * public interface IAVLNode
     * ! Do not delete or modify this - otherwise all tests will fail !
     */
    public interface IAVLNode {
        public int getKey(); //returns node's key

        public String getValue(); //returns node's value [info]

        public void setLeft(IAVLNode node); //sets left child

        public IAVLNode getLeft(); //returns left child (if there is no left child return null)

        public void setRight(IAVLNode node); //sets right child

        public IAVLNode getRight(); //returns right child (if there is no right child return null)

        public void setParent(IAVLNode node); //sets parent

        public IAVLNode getParent(); //returns the parent (if there is no parent return null)

        public void setHeight(int height); // sets the height of the node

        public int getHeight(); // Returns the height of the node
    }

    /**
     * public class AVLNode
     * <p>
     * If you wish to implement classes other than AVLTree
     * (for example AVLNode), do it in this file, not in
     * another file.
     * This class can and must be modified.
     * (It must implement IAVLNode)
     */
    public class AVLNode implements IAVLNode {
        private int key;
        private String value;
        private AVLNode left = null;
        private AVLNode right = null;
        private AVLNode parent = null;
        private int height;
        private int size;

        private AVLNode(int key, String value, int height, int size) {
            this.key = key;
            this.value = value;
            this.height = height;
        }

        public AVLNode(int key, String value) {
            this(key, value, 0, 1);
        }

        /* TODO: add sentinel:
         private static final AVLNode sentinel = new AVLNode(0,null,-1,0);
        */


        public int getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public AVLNode getLeft() {
            return this.left;
        }


        public void setLeft(IAVLNode node) {
            this.left = (AVLNode) node;
        }

        public AVLNode getRight() {
            return this.right;
        }

        public void setRight(IAVLNode node) {
            this.right = (AVLNode) node;
        }

        public void setParent(IAVLNode node) {
            this.parent = (AVLNode) node;
        }

        public AVLNode getParent() {
            return this.parent;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return this.height;
        }

        public int getSize() {
            return this.size;
        }

        private void incSize() {
            this.size += 1;
        }

        private void decSize() {
            this.size -= 1;
        }

        private int BF() {
            return this.left.getHeight() - this.right.getHeight();
        }
    }
}
// TODO: iterator which returns path to key k.
