package model;

public class Node<E>{

    private Node<E> next;
    private Node<E> behind;
    private Node<E> left;
    private Node<E> right;
    private E value;

    public Node(){}

    public Node(E value){
        this.value = value;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getBehind() {
        return behind;
    }

    public void setBehind(Node<E> behind) {
        this.behind = behind;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
