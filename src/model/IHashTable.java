package model;

public interface IHashTable<K,V> {
    void insertHashLinear(HashTable<K,V> table, K hashFunction, V value);
    boolean remove(K key);
    int count();
    boolean contains(K key);
}
