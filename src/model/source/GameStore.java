package model.source;

import model.ownImplementation.classes.HashTable;
import model.ownImplementation.classes.QueueList;

import java.util.ArrayList;

public class GameStore{

    private int cashiers;
    private HashTable<Integer, Shelve> shelves;
    private ArrayList<Client> clients;
    private int time;

    public GameStore(int cashiers, int shelves){
        this.cashiers = cashiers;
        this.shelves = new HashTable<>(shelves);
        this.clients = new ArrayList<>();
        this.time = 0;
    }

    public void addShelve(String code, int size){
        Shelve shelve = new Shelve(code, size);
        //System.out.println("Key en la origin: "+(int) strToIntCode(code));
        shelves.insert(shelve, (int) strToIntCode(code), 0);
    }

    public void shelveAddGame(String code, int gCode, int price, int quantity){
        shelves.getNodeBykey((int) strToIntCode(code)).getValue().addGame(gCode, price, quantity);
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

    public void addClient(String code){
        Client client = new Client(code);
        clients.add(client);
    }

    public Game findGame(int gCode){
        Game game = null;
        for(int i = 0; i<shelves.getSize(); i++){
            if(shelves.getNodeByIndex(i).getValue() != null){
                if(shelves.getNodeByIndex(i).getValue().isGame(gCode)){
                    game = shelves.getNodeByIndex(i).getValue().findGame(gCode);
                }
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

    public HashTable<Integer, Shelve> getShelves() {
        return shelves;
    }
}