import java.util.Arrays;

/**
 * Circular list
 * <p>
 * An implementation of a circular list with  key and info
 */

public class CircularList extends MyList {

    private int maxLen;
    public int curLen;
    private int start;
    private Item[] arr;


    public CircularList(int maxLen) {
        this.maxLen = maxLen;
        this.curLen = 0;
        this.start = 0;
        this.arr = new Item[maxLen];
    }

    private int mod(int a, int b) {
        assert b > 0;
        int remainder = a % b; // java % operator calculates the remainder, not the modulo.
        return remainder >= 0 ? remainder : remainder + b;
    }

    private int realPos(int virtualPos) {
        assert inRange(virtualPos, 0, maxLen);
        if ((start + virtualPos) % maxLen == (-1)) {
            System.out.println();
            System.out.println("IN REAL POS");
            System.out.printf("start=%d, virtualPos=%d, maxlen=%d\n", start, virtualPos, maxLen);
        }
        return (start + virtualPos) % maxLen;
    }

    private boolean inRange(int i, int start, int stop) {
        return start <= i && i < stop;
    }

    private int incInBounds(int i) {
        assert inRange(i, 0, maxLen);
        return i != maxLen - 1 ? i - 1 : maxLen - 1;
    }

    private int decInBounds(int i) {
        assert inRange(i, 0, maxLen);
        return i != 0 ? i - 1 : maxLen - 1;
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

    // pushes all items from "start" to "stop" leftwards (inclusive)
    // (meaning each gets copied to its previous index minus one).
    private void pushLeft(int start, int stop) {
        for (int j = start; j != stop; j =) {
            arr[(j +) % maxLen] = arr[j];
        }
    }

    // pushs all items from "start" to "stop" rightwards (inclusive)
    private void pushRight(int start, int stop) {
        // TODO
    }

    /** public int insert(int i, int k, String s)
     * inserts an item with key k and string s at index i to the list.
     * returns -1 if i is not in bounds or the array is full (curLen==maxLen)
     * otherwise, returns 0.
     */

    public int insert(int i, int k, String s) {
        System.out.println("\n IN INSERT:");
        if (i > curLen || i < 0 || curLen == maxLen) {
            return -1;
        }
        if (i <= curLen / 2) {
            System.out.println("realPos(i)=" + realPos(i) + ", start=" + start + ", curLen=" + curLen);
            pushLeft(realPos(i), start);
            start = (start != 0) ? (start - 1) : (maxLen - 1);
        } else {
            pushRight((i), (curLen));
        }
        arr[i] = new Item(k, s); // insert the item and increase curLen by one
        curLen += 1;
        return 0;
    }

    public String toString() {
        String list = "[";
        for (int i = 0; i < curLen; i++) {
            list += retrieve(i);
            list += ",";
        }
        list += "]";
        String array = Arrays.toString(arr);
        return list + "\n" + "start=" + start + ", curLen=" + curLen +
                ", maxLen=" + maxLen + "\n" + array;
    }

    /**
     * public int delete(int i)
     * <p>
     * deletes an item in the ith posittion from the list.
     * returns -1 if i<0 or i>n-1 otherwise returns 0.
     */
    public int delete(int i) {
        if (i >= curLen || i < 0) {
            return -1;
        }
        if (start + i <= curLen / 2) {
            pushRight(start, realPos(i));
            start = (start + 1) % maxLen;
        } else {
            pushLeft(realPos(curLen), realPos(i));
        }
        curLen -= 1;   // decrease curLen by one 
        return 0;
    }

    //TODO: remove this at the end
    int getMaxLen() {
        return maxLen;
    }

    int size() {
        return curLen;
    }

}
    
    
    
    