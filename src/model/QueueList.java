package model;

public class QueueList<E>  {

    private Node<E> front;
    private Node<E> back;

    public QueueList() {
        this.front = null;
        this.back = null;
    }

    public Node<E> front() {
        return this.front;
    }

    public void setFront(Node<E> first) {
        this.front = first;
    }

    public void enqueue(E e){
        Node<E> node = new Node<E>(e);
        if (front==null){
            front=node;
            back=node;
        }else{
            back.setNext(node);
            back=node;
        }
    }

    public Node<E> dequeue(){
        if (front!=null){
            Node <E> dequeue=front;
            if (front.getNext()==null){
                front=null;
            }else{
                front=front.getNext();
            }
            return dequeue;
        }else{
            return null;
        }
    }

    public Boolean isEmpty(){
        if (front==null){
            return true;
        }else{
            return false;
        }
    }

}