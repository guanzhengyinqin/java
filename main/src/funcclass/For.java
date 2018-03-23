package funcclass;
public class For {
	    public static <T extends Object>void $(T[] list, Iteration iteration){
	        for (int i = 0; i < list.length; i++) {
	            boolean bool = iteration.iteration(list[i], i);
	            if(!bool) break;
	        }
	    }

	    public interface Iteration<T>{
	        boolean iteration(T currentValue, int index);
	    }
	}