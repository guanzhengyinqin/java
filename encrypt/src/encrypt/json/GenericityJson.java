package encrypt.json;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

public class GenericityJson<K,V> implements Iterable<V>,Serializable {

	
	private static final long serialVersionUID = 1186269994127387919L;
	private JSONObject json;
	private List<K> arrayList;
	private int index = 0;
	
	public GenericityJson(JSONObject json){
		this.json = json;
		arrayList = new ArrayList<K>();
	}
	
	
	public void put(K key,V value){
		if(null==json.put(key,value)){
			arrayList.add(key);
		}
	}
	
	@SuppressWarnings("unchecked")
	public V getValue(K key){
		return (V) json.get(key);
	}
	
	@SuppressWarnings("unchecked")
	public V getValueByIndex(int index){
		if(index>size()){
			throw new IndexOutOfBoundsException();
		}else{
			return (V) json.get(arrayList.get(index).toString());
		}
		
		/*
		Iterator<K> it = json.keys();
		
		for(int i=0;i<=index;i++){
			it.next();
		}
		return (V) it.next();
		*/
	}
	
	public JSONObject getJson(){
		return json;
	}
	
	public GenericityJson<K, V> remove(K key){
		json.remove(key);
		return this;
	}

	public int size(){
		return json.size();
	}

	//@SuppressWarnings("unchecked")
	@Override
	public Iterator<V> iterator() {
		// TODO Auto-generated method stub
		return new JSONIterator<V>();
	}
	
	@SuppressWarnings("unchecked")
	public GenericityJson<K, V> fromObject(Object object){
		this.json = JSONObject.fromObject(object);
		arrayList.clear();
		Iterator<K> it = json.keys();
		while(it.hasNext()){
			arrayList.add(it.next());
		}
		return this;
	}
	
	@SuppressWarnings("hiding")
	private class JSONIterator<V> implements Iterator<V>{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index < size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public V next() {
			// TODO Auto-generated method stub
			return (V) json.get(arrayList.get(index++).toString());
		}
		
	}
	
	
}


