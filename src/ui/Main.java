package ui;
import model.*;

public class Main{
    public static void main(String[] args) {
        StackList<String> list = new StackList<>();
        list.push("Prueba 1");
        list.push("Prueba 2");
        list.push("Prueba 3");
        list.push("Prueba 4");
        list.push("Prueba ultima");

        QueueList<String> queue = new QueueList<>();
        queue.enqueue("Prueba 1");
        queue.enqueue("Prueba 2");
        queue.enqueue("Prueba 3");
        queue.enqueue("Prueba 3");
        queue.enqueue("Prueba 4");
        queue.enqueue("Prueba ultima");

        System.out.print("\n ----- \t Stack Debug \t -----\n");

        System.out.println(list.top());
        list.pop();
        System.out.println(list.top());
        list.push("Prueba ultima");

        System.out.print("\n ----- \t Stack \t -----\n");

        while(!list.isEmpty()){
            System.out.println(list.pop());
        }

        System.out.print("\n ----- \t Queue \t -----\n");

        while(!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }
    }
}