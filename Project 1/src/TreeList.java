
/**
 * Tree list
 * <p>
 * An implementation of a Tree list with  key and info
 */
 public class TreeList extends MyList {
    private AVLTree tree = new AVLTree();
	 /**
   * public Item retrieve(int i)
   *
   * returns the item in the ith position if it exists in the list.
   * otherwise, returns null
   */
  public Item retrieve(int i)
  {
	return tree.treeRetrieve(i);
  }

    /**
     * public int insert(int i, int k, String s)
     * <p>
     * inserts an item to the ith position in list  with key k and  info s.
     * returns -1 if i<0 or i>n otherwise return 0.
     */
    public int insert(int i, int k, String s) {
        return tree.treeInsert(i, k, s );
    }

    /**
     * public int delete(int i)
     * <p>
     * deletes an item in the ith position from the list.
     * returns -1 if i<0 or i>n-1 otherwise returns 0.
     */
    public int delete(int i) {
        return 42;    // to be replaced by student code
    }

}
