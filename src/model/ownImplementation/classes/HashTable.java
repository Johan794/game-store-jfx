package model.ownImplementation.classes;

import model.ownImplementation.interfaces.IHashTable;

public class HashTable<T,V>  implements IHashTable<T,V> {

    private HashNode<T, V> node;
    private int size;

    public HashTable(int size) {
        this.size = size;
        node = new HashNode<>();
        buildTable(size,node);
    }

    private int keyToInt(T key){
        String val = String.valueOf(key);
        return strToIntCode(val);
    }

    public int strToIntCode(String code){
        int iCode = 0;
        int radix = 127;
        int j = 0;
        for(int i = code.length()-1; i>=0; i--){
            iCode += code.charAt(i)*(Math.pow(radix, j));
            j++;
        }
        return iCode;
    }

    @Override
    public void insert(V value, T key, int index) {
        int keyV = keyToInt(key);
        int i = hashFuntion(keyV,index);
        HashNode<T,V> nodeToInsert = getNode(i,node);
        if(nodeToInsert.getKey()==null && nodeToInsert.getValue()==null){
            nodeToInsert.setKey(key);
            nodeToInsert.setValue(value);
        }else{
            insert(value,key,index+1);
        }

    }


    @Override
    public boolean remove(T key) {
        HashNode<T,V> toRemove = getNodeBykey(key);
        if(toRemove == null){
            return false;
        }else{
            toRemove = new HashNode<>();
            return true;
        }
    }


    @Override
    public void buildTable(int size, HashNode<T,V> hashNode) {
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
    public HashNode<T, V> getNodeBykey(T key) {
        return getNodeByKey(key,node);
    }

    private HashNode<T,V>getNodeByKey(T key, HashNode<T,V> current){
        if(current.getKey()==key){
            return current;
        }else{
            return getNodeByKey(key, current.getNext());
        }
    }

    //Obtiene el primero
    public HashNode<T, V> getNode() {
        return node;
    }

    //Obtiene los demas
    public HashNode<T, V> getNode(int index, HashNode<T,V> current) {
        if(index!=0){
              current = current.getNext();
             return getNode(index-1,current);
        }else{
            return current;
        }
    }

    public HashNode<T, V> getNodeByIndex(int index){
        if(index == 0){
            return node;
        }else{
            return getNodeByIndex(node.getNext(), index, 1);
        }
    }

    private HashNode<T, V> getNodeByIndex(HashNode<T, V> current, int index, int cIndex){
        if(cIndex == index){
            return current;
        }else{
            return getNodeByIndex(current.getNext(), index, cIndex+1);
        }
    }

    public int getSize() {
        return size;
    }
}
