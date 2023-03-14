package utils.inteface;

public interface MyMap<K,V> {

    public void put(K key, V value);

    public void remove(K key);

    public V get(K key);

    public int size();
    public boolean containsKey(K idNum);
}
