package model.ownImplementation.interfaces;

import model.ownImplementation.classes.HashNode;
import model.ownImplementation.classes.HashTable;

public interface IHashTable<K,V> {
    void insert(HashTable<K,V> table, int hashFunction, V value, K key);
    boolean remove(K key);
    int count();
    boolean contains(K key);
    void buildTable(int size, HashNode<K,V> hashNode);
}
