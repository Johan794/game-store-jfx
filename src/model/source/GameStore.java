package model.source;

import model.ownImplementation.classes.HashTable;

import java.util.ArrayList;

public class GameStore{

    private ArrayList<Game> storeGames;
    private ArrayList<Publication> review;
    private ArrayList<Publication> critics;
    private ArrayList<HashTable<String,Game>> gameStands;
}