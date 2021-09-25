package model.source;


import model.ownImplementation.classes.HashTable;

import java.util.ArrayList;

public class Shelve {

    private String code;
    private ArrayList<Game> games;

    public Shelve(String code, int size){
        this.code = code;
        this.games = new ArrayList<>();
    }

    public void addGame(Game game){
        games.add(game);
    }

    public boolean isGame(int gCode){
        boolean found = false;
        for(int i = 0; i<games.size() && !found; i++){
            if(games.get(i).getCode() == gCode){
                found = true;
            }
        }
        return found;
    }

    public Game findGame(int gCode){
        boolean found = false;
        Game game = null;
        for(int i = 0; i<games.size() && !found; i++){
            if(games.get(i).getCode() == gCode){
                game = games.get(i);
                found = true;
            }
        }
        return game;
    }

    public String getCode(){
        return code;
    }
}
