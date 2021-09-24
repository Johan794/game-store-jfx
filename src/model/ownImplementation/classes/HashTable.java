package model.ownImplementation.classes;

import model.ownImplementation.interfaces.IHashTable;

public class HashTable<K,V>  implements IHashTable<K,V> {

    private HashNode<K, V> node;
    private int size;

    public HashTable(int size) {
        this.size = size;
        node = new HashNode<>();
        buildTable(size,node);
    }


    @Override
    public void insert(V value, K key, int index) {
        int keyV = (int) key;
        int i = hashFuntion(keyV,index);
        HashNode<K,V> nodeToInsert = getNode(i,node);
        if(nodeToInsert.getKey()==null && nodeToInsert.getValue()==null){
            nodeToInsert.setKey(key);
            nodeToInsert.setValue(value);
        }else{
            insert(value,key,index+1);
        }

    }


    @Override
    public boolean remove(K key) {
        HashNode<K,V> toRemove = getNodeBykey(key);
        if(toRemove == null){
            return false;
        }else{
            toRemove = new HashNode<>();
            return true;
        }
    }


    @Override
    public void buildTable(int size, HashNode<K,V> hashNode) {
        if(size!=0){
            hashNode.setNext(new HashNode<>());
            buildTable(size-1,hashNode.getNext());
        }
    }

    @Override
    public int hashFuntion(int key, int i) {
        return  (key+i)%size;
    }

    @Override
    public HashNode<K, V> getNodeBykey(K key) {
        return getNodeByKey(key,node);
    }

    private HashNode<K,V>getNodeByKey(K key, HashNode<K,V> current){
        if(current.getKey()==key){
            return current;
        }else{
            return getNodeByKey(key, current.getNext());
        }
    }

    //Obtiene el primero
    public HashNode<K, V> getNode() {
        return node;
    }

    //Obtiene los demas
    public HashNode<K, V> getNode(int index, HashNode<K,V> current) {
        if(index!=0){
              current = current.getNext();
             return getNode(index-1,current);
        }else{
            return current;
        }
    }

    public int getSize() {
        return size;
    }
}
