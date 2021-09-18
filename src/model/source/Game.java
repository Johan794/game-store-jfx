package model.source;

public class Game {

    private String title;
    private String info;
    private double evaluation;
    private String code;
    private double quantity;

    public Game(String title, String info, double evaluation, String code, double quantity){
        this.title = title;
        this.info = info;
        this.evaluation = evaluation;
        this.code = code;
        this.quantity = quantity;
    }
}
