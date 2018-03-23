package bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ListDemo<T> implements Iterable<T>,Collection<T> {
	
	private Object[] obj;
	private int size;
	private Iterator<T> iterator;

	public int getSize() {
		if(size!=0){
			return size+1;
		}
		return size;
	}
	
	public ListDemo(){
		iterator = new IteratorImpl();
		size = 0;
		obj = new Object[10];
	}
	
	public void addItem(Object o){
		if(size%10==9){
			Object[] objTemp = obj;
			obj = new Object[objTemp.length+10];
			for(int i=0;i<objTemp.length;++i){
				obj[i] = objTemp[i];
			}
		}
		obj[size] = o;
		size++;
	}
	
	public void remove(int index){
		obj[index] = null;
		size--;
	}
	
	public Iterator<T> getIterator(){
		return iterator;
	}
	
	@SuppressWarnings("unchecked")
	public T getObject(int index){
		if(index>size){
			System.err.println("超出范围！");
			throw new IndexOutOfBoundsException("超出范围！");
		}
		return (T) obj[index-1];
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return iterator;
	}
	
	//*****************************************************************************
	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{
			addItem(e);
			result = true;
		}catch(Exception e1){
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
	     // 将c转换为数组a
		
	    Object[] a = c.toArray();
	    Object[] b = new Object[obj.length+(a.length+(10-a.length%10))];
	    int d = size;
	    for(int i=0;i<obj.length;++i){
	    	b[i] = obj[i];
	    }
	    for(int j=0;j<a.length;++j){
	    	b[d] = a[j];
	    	size++;
	    	d++;
	    }
	    obj = b;

	    return a.length != 0; 
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i=0;i<size;i++){
			obj[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		if(null==o){
			for(int i=0;i<size;i++){
				if(obj[i]==null)
					return i>=0;
			}
		}else{
			for(int i=0;i<size;++i){
				if(o.equals(obj[i]))
					return i>=0;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		System.out.println("别搞了，containsAll方法没有实现^-^");
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		if(null==o){
			for(int index=0;index<size;index++){
				if(obj[index]==null){
					remove(index);
					return true;
				}
			}
		}else{
			for(int index=0;index<size;index++){
				if(o.equals(obj[index])){
					remove(index);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		System.out.println("别搞了，removeAll方法没有实现^-^");
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		System.out.println("别搞了，retainAll方法没有实现^-^");
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return Arrays.copyOf(obj,size);
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
	 if (a.length < size)
	        // Make a new array of a's runtime type, but my contents:
	        return (T[]) Arrays.copyOf(obj, size, a.getClass());
	    System.arraycopy(obj, 0, a, 0, size);
	    if (a.length > size)
	        a[size] = null;
	    return a;
	}
	//*****************************************************************************
	
	
	private class IteratorImpl implements Iterator<T>{
		
		private int index = 0;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index != size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			// TODO Auto-generated method stub
			return (T)obj[index++];
		}
		
	}

	
	

}
