package model.source;


import model.ownImplementation.classes.HashTable;

public class Shelve {

    private String code;
    private HashTable<Integer, Game> games;

    public Shelve(String code, int size){
        this.code = code;
        this.games = new HashTable<>(size);
    }

    public void addGame(int gCode, int price, int quantity){
        Game game = new Game(gCode, price, quantity);
        games.insert(game, gCode, 0);
    }

    public boolean isGame(int gCode){
        boolean found = false;
        for(int i = 0; i<games.getSize() && !found; i++){
            if(games.getNodeByIndex(i) != null){
                if(games.getNodeByIndex(i).getValue().getCode() == gCode){
                    found = true;
                }
            }
        }
        return found;
    }

    public Game findGame(int gCode){
        boolean found = false;
        Game game = null;
        for(int i = 0; i<games.getSize() && !found; i++){
            if(games.getNodeByIndex(i) != null){
                if(games.getNodeByIndex(i).getValue().getCode() == gCode){
                    game = games.getNodeByIndex(i).getValue();
                    found = true;
                }
            }
        }
        return game;
    }
}
