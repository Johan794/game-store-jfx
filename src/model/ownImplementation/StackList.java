package model.ownImplementation;

public class StackList<E>{

    private Node<E> lastNode;

    public StackList(){}

    public StackList(E value){
        lastNode = new Node<E>(value);
    }

    public void push(E value){
        if(lastNode == null){
            lastNode = new Node<E>(value);
        }else{
            Node<E> node = new Node<>(value);
            node.setBehind(lastNode);
            lastNode = node;
        }
    }

    public E pop(){
        E value = lastNode.getValue();
        if(lastNode.getBehind() != null){
            lastNode = lastNode.getBehind();
        }else{
            lastNode = null;
        }
        return value;
    }

    public E top(){
        return lastNode.getValue();
    }

    public boolean isEmpty(){
        if(lastNode == null){
            return true;
        }else{
            return false;
        }
    }
}