package model.source;

import model.ownImplementation.classes.HashTable;

public class Shelve {

    private String code;
    private HashTable<Integer, Game> games;

    public Shelve(String code, int size){
        this.code = code;
        this.games = new HashTable<>(size);
    }
}
