package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JSONObject implements Map<String, Object>,Iterable<Object> {
	
	private net.sf.json.JSONObject json = new net.sf.json.JSONObject();
	
	public net.sf.json.JSONObject put(Object key,Object value){
		json.put(key, value);
		return json;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return json.toString();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		json.clear();
	}

	@Override
	public boolean containsKey(Object arg0) {
		// TODO Auto-generated method stub
		return json.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		// TODO Auto-generated method stub
		return json.containsValue(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		// TODO Auto-generated method stub
		return json.entrySet();
	}

	@Override
	public Object get(Object arg0) {
		// TODO Auto-generated method stub
		return json.get(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return json.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return json.keySet();
	}

	@Override
	public Object put(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		json.put(arg0, arg1);
		return json;
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> arg0) {
		// TODO Auto-generated method stub
		json.putAll(arg0);
	}

	@Override
	public Object remove(Object arg0) {
		// TODO Auto-generated method stub
		return json.remove(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return json.size();
	}

	@Override
	public Collection<Object> values() {
		// TODO Auto-generated method stub
		return json.values();
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return json.keys();
	}




}
