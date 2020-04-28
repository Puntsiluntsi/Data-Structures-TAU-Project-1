

/**
 *
 * Circular list
 *
 * An implementation of a circular list with  key and info
 *
 */
 
 public class CircularList{
	 
    private int maxLen ;
    private int curLen ; 
    private int start ;
    private Item[] arr;
        
    public CircularList (int maxLen){
        this.maxLen = maxLen;
        this.curLen = 0 ; 
        this.start = 0 ;
        this.arr = new Item[maxLen];
	}

    private int realPos(int virtualPos){
        return (start+virtualPos)%maxLen;
    }
    /**
    * public Item retrieve(int i)
    *
    * returns the item in the ith position if it exists in the list.
    * otherwise, returns null
    */
    public Item retrieve(int i){
        if (i>=curLen || i<0){ // check if i is in the array bounds
            return null;
        }
        return arr[realPos(i)];  // return the item in i position 
    }
    
    private pushLeft(int start, int stop){ // pushes all items from "start" to "stop" leftwards (inclusive) (meaning each gets copied to its previous index minus one). 
        assert (start<=stop);
        for (int j=start; j!=(stop-1)%maxLen; j=(j-1)%maxLen){
            arr[j-1]=arr[j];
        }
    }

    private pushRight(int start, int stop){ // pushs all items from "start" to "stop" rightwards (inclusive)
        assert (start>=stop);
        for (int j=start; j!=(stop+1)Len; j=(j+1)%maxLen){
            arr[j+1]=arr[j];
        }
    }

    /* public int insert(int i, int k, String s) 
    *
    * inserts an item to the ith position in list  with key k and  info s.
    * returns -1 if i<0 or i>n  or n=maxLen otherwise return 0.
    */

    public int insert(int i, int k, String s) {
        if (i>curLen || i<0 || curLen == maxLen){ // check if i is in the array bounds or if the array is full
            return -1;
        }
        item = new Item(k,s);
        if (start+i<=curLen/2){ // if i is closer to the start than pushleft all the items before i
            pushLeft(actualPos(i),start);
            start = (start-1)%maxLen;
        } 
        else { 
            pushRight(actualPos(i),actualPos(curLen));  // if i is closer to the end than pushleft all the items after i
        }
        arr[actualPos(i)] = item; // insert the item and increase curLen by one
        curLen += 1;
        return 0;
    }
    
    /**
    * public int delete(int i)
    *
    * deletes an item in the ith posittion from the list.
    * returns -1 if i<0 or i>n-1 otherwise returns 0.
    */
    public int delete(int i)
    {
        if (i>=curLen || i<0 ){ // check if i is in the array bounds 
            return -1;
        }
        if (start+i<=curLen/2){ // if i is closer to the start than pushRight all the items before i
            pushRight(start,actualPos(i));
            start = (start+1)%maxLen;
        } 
        else { 
            pushLeft(actualPos(curLen),actualPos(i));  // if i is closer to the end than pushleft all the items after i
        }
        curLen -= 1;   // decrease curLen by one 
        return 0;	
    }
        
}
    
    
    
    