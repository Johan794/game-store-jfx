package model;

public class HashTable<K,V>  implements IHashTable<K,V>{

    private HashNode<K,V> node;
    public HashTable() {

    }

    @Override
    public void insertHashLinear(HashTable<K, V> table, K hashFunction, V value) {

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

}
