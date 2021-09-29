package model.ownImplementation.interfaces;

import model.ownImplementation.classes.HashNode;


public interface IHashTable<K,V> {
    void insert(V value, K key, int index);
    boolean remove(K key);
    void buildTable(int size, HashNode<K,V> hashNode);
    int hashFuntion(int key, int index);
    HashNode<K,V> getNodeByKey(K key);
}
