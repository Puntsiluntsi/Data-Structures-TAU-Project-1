/**
 * AVLTree
 * <p>
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 */

//TODO:
//    maintain size field
//    utilize nextInSearch method in searchNode and similar.

public class AVLTree {

    private final AVLNode sentinel = new AVLNode(
            null, null, null, new Item(Integer.MIN_VALUE, null), -1, 0);
    private AVLNode root = null;
    // key=MIN_VALUE,value=null, height=-1 and size=0.

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        return root() == sentinel;
    }

    /**
     * public String search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    public String search(int k) {
        AVLNode node = nodeSearch(k);
        if (node == sentinel || node.getKey() != k) {
            // if we didn't find the node
            return null;
        } else {
            return node.getValue();
        }
    }

    /**
     * private AVLNode nodeSearch(int k)
     * <p>
     * returns the node with key k if it exists in the tree
     * otherwise, returns the node to which we would insert a new node of key k as a child
     * (if the tree is empty, returns sentinel).
     */
    private AVLNode nodeSearch(int k) {
        if (this.empty()) {
            return sentinel;
        }
        AVLNode node = this.root();
        while (true) {
        /*
            // UNNECESSARY CASE, INCLUDED IN THE THIRD ONE
            if (node.getKey() == k) {
                return node; // if we found the node with key = k then we return the node
            }
        */

            if (node.getKey() < k && node.right != sentinel) {
                node = node.right;
            } else if (node.getKey() > k && node.left != sentinel) {
                node = node.left;
            } else {
                // we either found the node, or we're returning the parent of where we would insert it (before rotations).
                return node;
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
        if (empty()) {
            this.root = newNode;
            return 0;
        }
        AVLNode searchRes = nodeSearch(k);
        if (searchRes.getKey() == k) { // if the key is already in the tree
            return -1;
        }
        AVLNode parent = searchRes;
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

        int balanceOps = 0;
        AVLNode ancestor;
        for (ancestor = parent; ancestor != sentinel; ancestor = ancestor.getParent()) {

            assert (2 <= ancestor.BF()) && (ancestor.BF() <= 2);
            // should always be true in an AVL tree.

            if (ancestor.BF() == 2) {
                if (ancestor.getLeft().BF() == 1) {
                    rotateRight(ancestor);
                    balanceOps = 1;
                } else {
                    rotateLeftThenRight(ancestor);
                    balanceOps = 2;
                }
                break;
            } else if (ancestor.BF() == -2) {
                if (ancestor.getRight().BF() == -1) {
                    rotateLeft(ancestor);
                    balanceOps = 1;
                } else {
                    rotateRightThenLeft(ancestor);
                    balanceOps = 2;
                }
                break;
            }

            ancestor.updateHeight();

        }
        assert (ancestor == sentinel || ancestor.calculateHeight() == ancestor.getHeight());

        // TODO: add second for loop for updating the remaining ancestors' sizes:
        for (; ancestor!=sentinel; ancestor=ancestor.getParent()){
            ancestor.updateSize();
            assert ancestor.calculateHeight() == ancestor.getHeight();
        }

        return balanceOps;
    }

    // POST: parent.getChild(dir)==child && child.getParent()==parent.
    private void connect(AVLNode parent, AVLNode child, Dir dir) {
        child.setParent(parent);
        parent.setChild(dir, child);
    }


    // new is placed instead of old as old.parent's child.
    // POST: old is detached (old isn't actually a child of old.parent)
    private void replace(AVLNode oldNode, AVLNode newNode) {
        if (oldNode == root) {
            root = newNode;
            root.setParent(sentinel);
        } else {
            connect(oldNode.getParent(), newNode, oldNode.getDir());
        }
    }

    private void rotate(AVLNode oldBase, Dir dir) {
        Dir opposite = dir.opposite();
        AVLNode newBase = oldBase.getChild(opposite);
        assert oldBase != sentinel && newBase != sentinel;
        oldBase.setChild(opposite, newBase.getChild(dir));
        newBase.setChild(dir, oldBase);
        connect(newBase, oldBase, dir);
        oldBase.updateAll();
        newBase.updateAll();
    }

    private void rotateOppThenDir(AVLNode oldBase, Dir dir) {
        Dir opposite = dir.opposite();
        rotate(oldBase.getChild(opposite),opposite);
        rotate(oldBase, dir);
    }

    private void rotateRight(AVLNode oldBase) {
        rotate(oldBase, Dir.LEFT);
    }

    private void rotateLeftThenRight(AVLNode oldBase) {
        rotateOppThenDir(oldBase, Dir.LEFT);
    }

    private void rotateLeft(AVLNode oldBase) {
        rotate(oldBase, Dir.RIGHT);
    }

    private void rotateRightThenLeft(AVLNode oldBase) {
        rotateOppThenDir(oldBase, Dir.RIGHT);
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
    public String min() {
        if (empty()) {
            return null;
        }
        AVLNode node = root();
        while (node.getLeft() != sentinel) {
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
        if (empty()) {
            return null;
        }
        AVLNode node = root();
        while (node.getRight() != sentinel) {
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
        return root().getSize();
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
        if (empty()) {
            return null;
        }
        return root();
    }

    private AVLNode root() {
        return root;
    }


    private enum Dir {
        RIGHT, LEFT;

        Dir opposite() {
            switch (this) {
                case RIGHT:
                    return LEFT;
                case LEFT:
                    return RIGHT;
                default:
                    // will never happen
                    throw new IllegalStateException();
            }
        }

        static Dir rightIff(boolean cond) {
            if (cond) {
                return RIGHT;
            } else {
                return LEFT;
            }
        }
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
        private AVLNode left;
        private AVLNode right;
        private AVLNode parent;
        private int height;
        private int size;

        private AVLNode(AVLNode parent, AVLNode left, AVLNode right, Item item, int height, int size) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.item = item;
            this.height = height;
            this.size = size;

        }

        private AVLNode(Item item, int height, int size) {
            this(sentinel,sentinel, sentinel, item, height, size);
        }

        private AVLNode(int key, String value, int height, int size) {

            this(new Item(key, value), height, size);

        }

        private AVLNode(int key, String value) {
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


        private void setLeft(AVLNode node) {
            this.left = node;
        }

        public void setLeft(IAVLNode node) {
            setLeft((AVLNode) node);
        }

        public AVLNode getRight() {
            return this.right;
        }

        private void setRight(AVLNode node) {
            this.right = node;
        }

        public void setRight(IAVLNode node) {
            setRight((AVLNode) node);
        }

        private void setParent(AVLNode node) {
            this.parent = node;
        }

        public void setParent(IAVLNode node) {
            setParent((AVLNode) node);
        }

        public AVLNode getParent() {
            return this.parent;
        }

        private AVLNode getChild(Dir dir) {
            if (dir == Dir.RIGHT) {
                return getRight();
            } else {
                return getLeft();
            }
        }

        private void setChild(Dir dir, AVLNode node) {
            if (dir == Dir.RIGHT) {
                setRight(node);
            } else {
                setLeft(node);
            }
        }

        private boolean isRightSon() {
            return this.parent.right == this;
        }

        private Dir getDir() {
            return Dir.rightIff(isRightSon());
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getHeight() {
            return this.height;
        }

        private AVLNode nextInSearch(int key) {
            assert (this.getKey() != key);
            return getChild(Dir.rightIff(this.getKey() < key));
        }


        private int calculateHeight() {
            return Math.max(this.left.getHeight(), this.right.getHeight()) + 1;
        }

        private void updateHeight() {
            this.height = calculateHeight();
        }

        private int getSize() {
            return this.size;
        }

        private void incSize() {
            this.size += 1;
        }

        private void decSize() {
            this.size -= 1;
        }

        private int calculateSize() {
            return this.getLeft().getSize() + this.getRight().getSize() + 1;
        }

        private void updateSize() {
            this.size = calculateSize();
        }

        private void updateAll() {
            updateHeight();
            updateSize();
        }


        private int BF() {
            return this.left.getHeight() - this.right.getHeight();
        }

    }
}
