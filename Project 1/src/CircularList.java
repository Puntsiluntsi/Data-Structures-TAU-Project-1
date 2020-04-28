

/**
 * Circular list
 * <p>
 * An implementation of a circular list with  key and info
 */

public class CircularList {

    private int maxLen;
    private int curLen;
    private int start;
    private Item[] arr;

    public CircularList(int maxLen) {
        this.maxLen = maxLen;
        this.curLen = 0;
        this.start = 0;
        this.arr = new Item[maxLen];
    }

    private int realPos(int virtualPos) {
        return (start + virtualPos) % maxLen;
    }

    /**
     * public Item retrieve(int i)
     * <p>
     * returns the item in the ith position if it exists in the list.
     * otherwise, returns null
     */
    public Item retrieve(int i) {
        if (i >= curLen || i < 0) { // check if i is in the array bounds
            return null;
        }
        return arr[realPos(i)];  // return the item in i position 
    }

    private void shiftOnceByStep(int start, int stop, int step) {
        for (int j = start; j != (stop + step) % maxLen; j = (j + step) % maxLen) {
            arr[j + step] = arr[j];
        }
    }

    private void pushLeft(int start, int stop) { // pushes all items from "start" to "stop" leftwards (inclusive) (meaning each gets copied to its previous index minus one).
        shiftOnceByStep(start, stop, -1);
    }

    private void pushRight(int start, int stop) { // pushs all items from "start" to "stop" rightwards (inclusive)
        shiftOnceByStep(start, stop, 1);
    }

    /* public int insert(int i, int k, String s)
     *
     * inserts an item to the ith position in list  with key k and  info s.
     * returns -1 if i<0 or i>n  or n=maxLen otherwise return 0.
     */

    public int insert(int i, int k, String s) {
        if (i > curLen || i < 0 || curLen == maxLen) { // check if i is in the array bounds or if the array is full
            return -1;
        }
        if (start + i <= curLen / 2) { // if i is closer to the start than pushleft all the items before i
            pushLeft(realPos(i), start);
            start = (start - 1) % maxLen;
        } else {
            pushRight((i), (curLen));  // if i is closer to the end than pushleft all the items after i
        }
        arr[i] = new Item(k, s); // insert the item and increase curLen by one
        curLen += 1;
        return 0;
    }

    /**
     * public int delete(int i)
     * <p>
     * deletes an item in the ith posittion from the list.
     * returns -1 if i<0 or i>n-1 otherwise returns 0.
     */
    public int delete(int i) {
        if (i >= curLen || i < 0) { // check if i is in the array bounds
            return -1;
        }
        if (start + i <= curLen / 2) { // if i is closer to the start than pushRight all the items before i
            pushRight(start, realPos(i));
            start = (start + 1) % maxLen;
        } else {
            pushLeft(realPos(curLen), realPos(i));  // if i is closer to the end than pushleft all the items after i
        }
        curLen -= 1;   // decrease curLen by one 
        return 0;
    }

}
    
    
    
    