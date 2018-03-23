package iteratordemo;

public class Test {

	public static void main(String[] args) {
		 ICollection<Integer> collection = new MyCollection<Integer>();  
	        add(collection, 3, 5, 8, 12, 3, 3, 5);  
	        for (IIterator<Integer> iterator = collection.iterator(); iterator.hasNext();) {  
	            System.out.println(iterator.next());  
	        }  
	          
	        System.out.println("-------------");  
	          
	        ICollection collection2 = new MyCollection();
	        add(collection2, "a", "b", "c", 3, 8, 12, 3, 5);
	        for (IIterator iterator = collection2.iterator(); iterator.hasNext();) {
	            System.out.println(iterator.next());
	        }  
	          
	    }  
	      
	    
		static <T> void  add(ICollection<T> c, T... a) {
	        for (T i : a) {
	            c.add(i);
	        }
	}
}
