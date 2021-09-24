package model.source;

import model.ownImplementation.classes.HashTable;

import java.util.ArrayList;

public class GameStore{

    private int cashiers;
    HashTable<Integer, Shelve> shelves;

    public GameStore(int cashiers, int shelves){
        this.cashiers = cashiers;
        this.shelves = new HashTable<>(shelves);
    }

    public void addShelve(String code, int size){
        Shelve shelve = new Shelve(code, size);

    }

    public long strToIntCode(String code){
        long iCode = 0;
        int radix = 127;
        int j = 0;
        for(int i = code.length()-1; i>=0; i--){
            iCode += code.charAt(i)*(Math.pow(radix, j));
            j++;
        }
        return iCode;
    }
}