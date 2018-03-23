package encrypt.bean;

import java.util.HashMap;

public class RemouldHashMap<K,V> extends HashMap<K, V> {

    private static final long serialVersionUID = 1651598577442877278L;

    public RemouldHashMap<K,V> rPut(K key,V value){
        this.put(key, value);
        return this;
    }

}
