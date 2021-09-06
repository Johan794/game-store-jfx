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

        System.out.println(list.top());
        list.pop();
        System.out.println(list.top());

        while(!list.isEmpty()){
            System.out.println(list.top());
            list.pop();
        }
    }
}