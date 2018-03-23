package iteratordemo;

import java.util.Arrays;

/**
 * 集合类，依赖于MyIterator
 * @author guanzheng
 *
 * @param <T>
 */

public class MyCollection<T> implements ICollection<T> {
	
	private T[] arys;
	private int index = -1;
	private int capacity = 5;
	private int realCapacity;
	

	
	public MyCollection(){
		this.arys = (T[]) new Object[capacity];
	}
	
	@Override
	public IIterator<T> iterator() {
		
		return new MyIterator<T>(this);
	}

	@Override
	public void add(T t) {
		
		index++;
		if(index==capacity){
			capacity *=2;
			this.arys = Arrays.copyOf(arys, capacity);
		}
		this.arys[index] = t;
		realCapacity++;
	}

	@Override
	public T get(int index) {
		
		return this.arys[index];
	}
	
	private int getCount(){
		return realCapacity;
	}
	
	private static class MyIterator<T> implements IIterator<T>{
		
		private MyCollection<T> collection;
		private int cursor = 0;
		MyIterator(MyCollection<T> collection){
			this.collection = collection;
		}

		@Override
		public boolean hasNext() {
			
			if(cursor < collection.getCount()){
				return true;
			}
			return false;
		}

		@Override
		public boolean hasPrevious() {
			
			if(cursor > 0){
				return true;
			}
			return false;
		}

		@Override
		public T next() {
			
			return collection.get(cursor++);
		}

		@Override
		public T previous() {
			
			return collection.get(cursor--);
		}
		
	}

}
