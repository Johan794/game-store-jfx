package model.source;

public class Game {

    private int code;
    private int price;
    private int quantity;

    public Game(int gCode, int price, int quantity){
        this.code = gCode;
        this.price = price;
        this.quantity = quantity;
    }

    public int getCode(){
        return code;
    }
}
