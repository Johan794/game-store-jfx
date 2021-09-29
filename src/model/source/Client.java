package model.source;

import model.ownImplementation.classes.*;

public class Client {

    private String code;
    private StackList<Game> games;
    private StackList<Game> paidGames;
    private QueueList<Integer> findGames;
    private int gamesQuantity;

    public Client(String code){
        this.code = code;
        this.games = new StackList<>();
        this.paidGames = new StackList<>();
        this.findGames = new QueueList<>();
        this.gamesQuantity = 0;
    }

    public void addGame(Game game){
        games.push(game);
        gamesQuantity++;
    }

    public void addGameToQueue(int gCode){
        findGames.enqueue(gCode);
        gamesQuantity++;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public StackList<Game> getGames() {
        return games;
    }

    public void setGames(StackList<Game> games) {
        this.games = games;
    }

    public QueueList<Integer> getFindGames() {
        return findGames;
    }

    public void setFindGames(QueueList<Integer> findGames) {
        this.findGames = findGames;
    }

    public int getGamesQuantity() {
        return gamesQuantity;
    }

    public void setGamesQuantity(int gamesQuantity) {
        this.gamesQuantity = gamesQuantity;
    }

    public StackList<Game> getPaidGames() {
        return paidGames;
    }

    public void setPaidGames(StackList<Game> paidGames) {
        this.paidGames = paidGames;
    }
}
