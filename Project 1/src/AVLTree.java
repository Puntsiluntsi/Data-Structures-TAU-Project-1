

/**
 *
 * AVLTree
 *
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 *
 */

public class AVLTree {

  private AVLNode Root = null;
    /**
   * public boolean empty()
   *
   * returns true if and only if the tree is empty
   *
   */
  public boolean empty() { // check if tree is empty <--> if Root is null
      if(Root == null){
        return true; 
      }
      return false;
  }

 /**
   * public String search(int k)
   *
   * returns the info of an item with key k if it exists in the tree
   * otherwise, returns null
   */
  public String search(int k){ // search for the node with Key = k in the tree and retruns the Value using nodeSearch()
      AVLNode node = nodeSearch(k)
      if (Root == null){
        retrun null;
      }
      if (node.getKey != k){ // check if we got the acutal node or its father or null
        return null;
      }
      return node.getValue;// if the Key k is in the tree we return its Value 
  }
  
  public AVLNode nodeSearch(int k){ // search for the node with Key = k in the tree and retruns the node
	    if (Root == null){
        retrun null;
      }  
      AVLNode node = Root;
      while(true){
        if (node.getKey == k){
          return node; // if we found the node with Key = k then we return the node 
        }
        if(node.getKey < k && node.Right != null){ // if node.getKey < k we go to the right son go the node 
          node = node.Right;
        }
        else if (node.getKey > k && node.Left != null){ // if node.getKey > k we go to the left son go the node 
          node = node.Left;
        }
        else{
          return node; // if there isnt anywere alse to go and the Key k is not in the tree we return its father
        }
      }
  }

  /**
   * public int insert(int k, String i)
   *
   * inserts an item with key k and info i to the AVL tree.
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
   * returns -1 if an item with key k already exists in the tree.
   */
  public int insert(int k, String i) {
      AVLNode newNode = new AVLNode(k,i)
      if (Root == null){
        Root = newNode;
        return 0;
      }
      AVLNode node = nodeSearch(k)
      if (node.getKey == k){ 
        return -1;
      
     
     
     ;	// to be replaced by student code
   }

  /**
   * public int delete(int k)
   *
   * deletes an item with key k from the binary tree, if it is there;
   * the tree must remain valid (keep its invariants).
   * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
   * returns -1 if an item with key k was not found in the tree.
   */
  public int delete(int k)
   {
	   return 42;	// to be replaced by student code
   }

   /**
    * public String min()
    *
    * Returns the info of the item with the smallest key in the tree,
    * or null if the tree is empty
    */
  public String min(){// returns the Value of the node with the smallst key
	    if (Root == null){
        retrun null;
     }
      AVLNode node = Root
      while(node.getLeft != null){// we go left until there isnt any a left son the the node
        node = node.getLeft;
      } 
      return node.getValue; 
  }

   /**
    * public String max()
    *
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty
    */
  public String max(){
      if (Root == null){
        retrun null;
      }
      AVLNode node = Root
      while(node.getRight != null){// we go right until there isnt any a right son the the node
        node = node.getRight;
      } 
      return node.getValue;
  }

  /**
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   */
  public int[] keysToArray()
  {
        int[] arr = new int[42]; // to be replaced by student code
        return arr;              // to be replaced by student code
  }

  /**
   * public String[] infoToArray()
   *
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
   */
  public String[] infoToArray()
  {
        String[] arr = new String[42]; // to be replaced by student code
        return arr;                    // to be replaced by student code
  }

   /**
    * public int size()
    *
    * Returns the number of nodes in the tree.
    *
    * precondition: none
    * postcondition: none
    */
   public int size(){
	    return Root.getSize();
   }
   
     /**
    * public int getRoot()
    *
    * Returns the root AVL node, or null if the tree is empty
    *
    * precondition: none
    * postcondition: none
    */
   public IAVLNode getRoot(){
	   return this.Root;
   }

	/**
	   * public interface IAVLNode
	   * ! Do not delete or modify this - otherwise all tests will fail !
	   */
	public interface IAVLNode{	
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
   *
   * If you wish to implement classes other than AVLTree
   * (for example AVLNode), do it in this file, not in 
   * another file.
   * This class can and must be modified.
   * (It must implement IAVLNode)
   */
  public class AVLNode implements IAVLNode{
    private int Key;
    private String Value;
    private AVLNode Left = null;
    private AVLNode Right = null;
    private AVLNode Parent = null;

    private AVLNode(int key, String value, int height, int size){
        this.Key = key;
        this.Value = value;
        this.Height = height;
    }

    public AVLNode(int key, String value){
        this(key,value,0,1);
    }
    
    /* TODO: add sentinel:
     private static final AVLNode sentinel = new AVLNode(0,null,-1,0);
    */
    
    
    public int getKey(){
            return this.Key;
    }

    public String getValue(){
            return this.Value;
    }

    public void setLeft(IAVLNode node){
            this.Left = node;
    }

    public IAVLNode getLeft(){
            return this.Left; 
    }

    public void setRight(IAVLNode node){
            this.Right = node; 
    }

    public IAVLNode getRight(){
            return this.Right;
    }

    public void setParent(IAVLNode node){
            this.Parent = node;
    }

    public IAVLNode getParent(){
            return this.Parent;
    }

    public void setHeight(int height)
    {
        this.Height = height ; 
    }

    public int getHeight(){
        return this.Height; 
    }

    public void incSize(){
        this.Size += 1;
    }

    public void decSize(){
        this.Size -= 1;
    }

    public int getSize(){
        return this.Size;
    }

    public int BF(){
      return this.Left.getHeight() - this.Right.getHeight();
    }

  }

}
