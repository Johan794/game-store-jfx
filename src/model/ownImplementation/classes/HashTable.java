package model.ownImplementation.classes;

import model.ownImplementation.interfaces.IHashTable;

public class HashTable<K,V>  implements IHashTable<K,V> {

    private HashNode<K, V> node;

    public HashTable(int size) {
        node = new HashNode<>();
        buildTable(size,node);
    }

    @Override
    public void insert(HashTable<K, V> table, int hashFunction, V value, K key) {

    }


    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public void buildTable(int size, HashNode<K,V> hashNode) {
        if(size!=0){
            hashNode.setNext(new HashNode<>());
            buildTable(size-1,hashNode.getNext());
        }
    }



}
