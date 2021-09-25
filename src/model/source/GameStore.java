package model.source;

import model.ownImplementation.classes.HashTable;
import model.ownImplementation.classes.QueueList;

import java.util.ArrayList;

public class GameStore{

    private int cashiers;
    private ArrayList<Shelve> shelves;
    private ArrayList<Client> clients;
    private int time;

    public GameStore(int cashiers, int shelves){
        this.cashiers = cashiers;
        this.shelves = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.time = 0;
    }

    public void addShelve(String code, int size){
        Shelve shelve = new Shelve(code, size);
        shelves.add(shelve);
    }

    public void shelveAddGame(String code, int gCode, int price, int quantity){
        shelves.get(findShelve(code)).addGame(gCode, price, quantity);
    }

    public int findShelve(String code){
        boolean found = false;
        int index = 0;
        for(int i = 0; i<shelves.size(); i++){
            if(shelves.get(i).getCode().equals(code)){
                index = i;
                found = true;
            }
        }
        return index;
    }

    private long strToIntCode(String code){
        long iCode = 0;
        int radix = 127;
        int j = 0;
        for(int i = code.length()-1; i>=0; i--){
            iCode += code.charAt(i)*(Math.pow(radix, j));
            j++;
        }
        return iCode;
    }

    public void addClient(String code){
        Client client = new Client(code);
        clients.add(client);
    }

    public Game findGame(int gCode){
        Game game = null;
        for(int i = 0; i<shelves.size(); i++){
            if(shelves.get(i).isGame(gCode)){
                game = shelves.get(i).findGame(gCode);
            }
        }
        return game;
    }

    public void clientAddGame(String code, int gCode){
        int index = 0;
        boolean found = false;
        for(int i = 0; i<clients.size() && !found; i++){
            if(clients.get(i).getCode().equals(code)){
                index = i;
                found = true;
            }
        }
        clients.get(index).addGame(findGame(gCode));
    }

    public void advance(){

    }
}