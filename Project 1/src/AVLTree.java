

/**
 * AVLTree
 * <p>
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 */
public class AVLTree {

    private final AVLNode sentinel = new AVLNode(null, -1, 0); // item=null, height=-1 and size=0.
    private AVLNode root = sentinel;

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() { // check if tree is empty <--> if root is null
        return root == sentinel;
    }

    /**
     * public String search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    public String search(int k) { // search for the node with key = k in the tree and returns the value using nodeSearch()
        AVLNode node = nodeSearch(k);
        if (node == sentinel || node.getKey() != k) { // if we didn't find the node
            return null;
        } else {
            return node.getValue();// if the key k is in the tree we return its value
        }
    }

    // Returns sentinel if Tree is empty, otherwise the node with key k if found, and if not found the parent of where we would insert the key k without rotations.
    private AVLNode nodeSearch(int k) { // search for the node with key = k in the tree and returns the node
        if (this.empty()) {
            return sentinel;
        }
        AVLNode node = this.root;
        while (true) {
        /*    if (node.getKey() == k) { // UNNECESSARY CASE, INCLUDED IN THE THIRD ONE
                return node; // if we found the node with key = k then we return the node
            }
        */

            if (node.getKey() < k && node.right != sentinel) { // if node.getKey < k we go to the right son go the node
                node = node.right;
            } else if (node.getKey() > k && node.left != sentinel) { // if node.getKey > k we go to the left son go the node
                node = node.left;
            } else {
                return node; // we either found the node, or we're returning the parent of where we would insert it (before rotations).
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
        if (this.empty()) {
            root = newNode;
            return 0;
        }
        AVLNode searchRes = nodeSearch(k);
        if (searchRes.getKey() == k) { // if the key is already in the tree
            return -1;
        }
        AVLNode parent = searchRes;
        newNode.setParent(parent);
        if (parent.getKey() > k) {
            parent.setLeft(newNode);
            if (parent.getRight() != sentinel) {
                // we added a second child, thus we don't need any further updating/checking.
                return 0;
            }
        }
        if (parent.getKey() < k) {
            parent.setRight(newNode);
            if (parent.getLeft() != sentinel) {
                return 0;
            }
        }

        AVLNode ancestor;
        for (ancestor = parent; ancestor != sentinel; ancestor = ancestor.getParent()) {
            ancestor.updateHeight();

            assert (2 <= ancestor.BF()) && (ancestor.BF() <= 2); // should always be true in an AVL tree.

            if (ancestor.BF() == 2 && ancestor.getLeft().BF() == 1) {
                rotateRight(ancestor); // rotate right
                return 1;
            } else if (ancestor.BF() == 2 && ancestor.getLeft().BF() == -1) {
                rotateLeftThenRight(ancestor); // rotate left then right
                return 2;
            } else if (ancestor.BF() == -2 && ancestor.getRight().BF() == 1) {
                rotateLeft(ancestor); // rotate left
                return 1;
            } else if (ancestor.BF() == -2 && ancestor.getRight().BF() == -1) {
                rotateRightThenLeft(ancestor); // rotate right then left
                return 2;
            }

        }
        return 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // hello idk what to do and i am bored i
    ////////////////////////////////////////////////////////////////////////////////////
    public void rotateRight(AVLNode node) {
        // TODO
    }

    public void rotateLeftThenRight(AVLNode node) {
        // TODO
    }

    public void rotateLeft(AVLNode node) {
        // TODO
    }

    public void rotateRightThenLeft(AVLNode node) {
        // TODO
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
        if (root == null) {
            return null;
        }
        AVLNode node = root;
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
        if (root == null) {
            return null;
        }
        AVLNode node = root;
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
        return root.getSize();
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
        return this.root;
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
        private Item item;
        private AVLNode left = sentinel;
        private AVLNode right = sentinel;
        private AVLNode parent = sentinel;
        private int height;
        private int size;

        private AVLNode(Item item, int height, int size) {
            this.item = item;
            this.height = height;
            this.size = size;

        }

        private AVLNode(int key, String value, int height, int size) {
            this(new Item(key, value), height, size);

        }

        public AVLNode(int key, String value) {
            this(key, value, 0, 1);
        }

        public int getKey() {
            return this.item.getKey();
        }

        public String getValue() {
            return this.item.getInfo();
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

        public void updateHeight() {
            if (this.left.getHeight() > this.right.getHeight()) {
                this.height = this.left.getHeight() + 1;
            } else {
                this.height = this.right.getHeight() + 1;
            }
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
