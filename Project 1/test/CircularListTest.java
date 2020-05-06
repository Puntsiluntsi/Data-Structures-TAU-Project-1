import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class CircularListTest {

    public static void main(String[] args) {
    		assert false;
			int k,insertIndex,delIndex;
			String s;
			int count=1;
			boolean check=true;
			Random rd = new Random();
			for(int j=0;j<5;j++) {
				int length=rd.nextInt(10);
				System.out.println("Test number: "+j);
				System.out.println("length of array: "+length);
				System.out.println();
				List<Integer> compare = new ArrayList<Integer>();
				CircularList x = new CircularList(length);
				for (int i = 0; i < length; i++) {
					insertIndex= rd.nextInt(count); // storing random integers in an array
					k=rd.nextInt(10);
					s=String.valueOf(k);
					compare.add(insertIndex,k);
					System.out.println(x);
					System.out.println();
					System.out.println("insertIndex=" + insertIndex+", key="+s);
					x.insert(insertIndex, k, s);
					count++;
					System.out.println();
				}
				if(x.curLen !=compare.size()) {
					System.out.println("Error!!! insert, not same size");
					check=false;
				}
				for (int i = 0; i<x.curLen; i++) {
					if (x.retrieve(i).getKey()!=compare.get(i)) {
						System.out.println("Error!!! insert, put breakpoint here");
						check=false;
					}
					if (x.retrieve(i).getKey()!=compare.get(i)) {
						System.out.println("check");
						check=false;
					}
		        // printing each array element
		      }
				for (int i = 0; i<(x.curLen /2); i++)
				{
					delIndex=rd.nextInt(x.curLen);
					compare.remove(delIndex);
					x.delete(delIndex);
				}
				if(x.curLen !=compare.size())
					{
						System.out.println("Error!!! delete, not same size");
						check=false;
					}
				for (int i = 0; i<x.curLen; i++) {
					if (x.retrieve(i).getKey()!=compare.get(i)) {
						System.out.println("Error!!! delete, put breakpoint here");
						check=false;
					}
					if (x.retrieve(i).getKey()!=compare.get(i)) {
						System.out.println("check");
						check=false;
					}
		        // printing each array element
		      }
			count=1;
			System.out.println("");
			}
			if (check) System.out.println("Done");
			else System.out.println("there was an error! check where");
		}

//    @Test
//    void retrieve() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void getcurLen() {
//    }
//
//    @Test
//    void size() {
//    }
}