package model;

public class Publication {

    private String title;
    private String info;
    private Game game;
    private double evaluation;
    private Type type;

    public Publication(String title, String info, Game game, double evaluation, String type){
        this.title = title;
        this.info = info;
        this.game = game;
        this.evaluation = evaluation;
        this.type = Type.valueOf(type);
    }
}
