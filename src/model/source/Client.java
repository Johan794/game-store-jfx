package model.source;

import model.ownImplementation.classes.*;

public class Client {

    private String code;
    private StackList<Game> games;
    private int gamesQuantity;

    public Client(String code){
        this.code = code;
        this.games = new StackList<>();
        this.gamesQuantity = 0;
    }

    public void addGame(Game game){
        games.push(game);
        gamesQuantity++;
    }

    public String getCode(){
        return code;
    }
}
