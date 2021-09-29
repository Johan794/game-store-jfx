package model.source;

import model.ownImplementation.classes.*;
import model.ownImplementation.classes.Duplex;
import model.ownImplementation.classes.HashTable;
import model.ownImplementation.classes.QueueList;

import java.util.ArrayList;
import java.util.Arrays;

public class GameStore{


    private int cashiers;
    private int occupiedCashiers;
    private int outClients;
    private ArrayList<Shelve> shelves;
    private ArrayList<Client> clients;
    private ArrayList<Duplex<Integer, Client>> findingClients;
    private ArrayList<Client> payingClients;
    private QueueList<Client> payQueue;
    private ArrayList<String> orderedOut;
    private HashTable<Integer, Duplex<String, Integer>> out;
    private int time;

    public GameStore(int cashiers, int shelves){
        this.cashiers = cashiers;
        this.occupiedCashiers = 0;
        this.outClients = 0;
        this.shelves = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.findingClients = new ArrayList<>();
        this.payingClients = new ArrayList<>();
        this.payQueue = new QueueList<>();
        this.orderedOut = new ArrayList<>();
        this.time = 0;
    }

    public void addShelve(String code, int size){
        Shelve shelve = new Shelve(code, size);
        shelves.add(shelve);
    }

    public void shelveAddGame(String code, int gCode, int price, int quantity){
        Game game = new Game(gCode, price, quantity);
        shelves.get(findShelve(code)).addGame(game);
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

    public void addClient(String code, ArrayList<String> gameCodes){
        Client client = new Client(code);
        ArrayList<Duplex<Integer, Integer>> games = new ArrayList<>();
        String sCode = "";
        int sIndex = 0;
        boolean found = false;
        for(int i = 0; i<gameCodes.size(); i++){
            sCode = findGameShelve(Integer.parseInt(gameCodes.get(i)));
            found = false;
            for(int j = 0; j<shelves.size() && !found; j++){
                if(shelves.get(j).getCode().equals(sCode)){
                    sIndex = j;
                    found = true;
                }
            }
            games.add(new Duplex<>(sIndex, Integer.parseInt(gameCodes.get(i))));
        }

        for(int i = 1; i < games.size(); i++){
            for(int j = i; j > 0 && games.get(j-1).getKey() > games.get(j).getKey(); j--){
                Duplex<Integer, Integer> temporal = games.get(j);
                games.set(j,games.get(j-1));
                games.set(j-1,temporal);
            }
        }
        //Aca se ordena :D
        /*

            ██╗███╗░░██╗░██████╗███████╗██████╗░████████╗       ░█████╗░░█████╗░██████╗░███████╗
            ██║████╗░██║██╔════╝██╔════╝██╔══██╗╚══██╔══╝       ██╔══██╗██╔══██╗██╔══██╗██╔════╝
            ██║██╔██╗██║╚█████╗░█████╗░░██████╔╝░░░██║░░░       ██║░░╚═╝██║░░██║██║░░██║█████╗░░
            ██║██║╚████║░╚═══██╗██╔══╝░░██╔══██╗░░░██║░░░       ██║░░██╗██║░░██║██║░░██║██╔══╝░░
            ██║██║░╚███║██████╔╝███████╗██║░░██║░░░██║░░░       ╚█████╔╝╚█████╔╝██████╔╝███████╗
            ╚═╝╚═╝░░╚══╝╚═════╝░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░       ░╚════╝░░╚════╝░╚═════╝░╚══════╝

            ██╗░░██╗███████╗██████╗░███████╗
            ██║░░██║██╔════╝██╔══██╗██╔════╝
            ███████║█████╗░░██████╔╝█████╗░░
            ██╔══██║██╔══╝░░██╔══██╗██╔══╝░░
            ██║░░██║███████╗██║░░██║███████╗
            ╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝╚══════╝
         */


        // Dejo el insert code here porque está melitico 😜

        for(int i = 0; i<games.size(); i++){
            client.addGameToQueue(games.get(i).getValue());
        }
        clients.add(client);
    }

    public Game findGame(int gCode){
        Game game = null;
        boolean found = false;
        for(int i = 0; i<shelves.size() && !found; i++){
            if(shelves.get(i).isGame(gCode)){
                game = shelves.get(i).findGame(gCode);
                found = true;
            }
        }
        return game;
    }

    public String findGameShelve(int gCode){
        String shelve = "";
        boolean found = false;
        for(int i = 0; i<shelves.size() && !found; i++){
            if(shelves.get(i).isGame(gCode)){
                shelve = shelves.get(i).getCode();
                found = true;
            }
        }
        return shelve;
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

    public boolean proccessFindGame(Client client){
        boolean bl = false;
        if(!client.getFindGames().isEmpty()){
            client.getGames().push(findGame(client.getFindGames().dequeue()));
            bl = true;
        }
        return bl;
    }

    public boolean proccessPayGame(Client client){
        boolean bl = false;
        if(!client.getGames().isEmpty()){
            client.getPaidGames().push(client.getGames().pop());
            bl = true;
        }
        return bl;
    }

    public void setup(){
        out = new HashTable<>(clients.size());
        for(int i = 0; i<clients.size(); i++){
            findingClients.add(new Duplex<>(i+1+clients.get(i).getGamesQuantity(), clients.get(i)));
        }
        boolean bl;
        for(int i = 0; i<findingClients.size(); i++){
            bl = true;
            while(bl){
                bl = proccessFindGame(findingClients.get(i).getValue());
            }
        }
            boolean changed = true;
            for(int i = 1; i < findingClients.size()-1 && changed; i++){
                changed = false;
                for(int j = 0; j < findingClients.size()-i; j++){
                    if(findingClients.get(j).getKey() > findingClients.get(j+1).getKey()){
                        changed = true;
                        Duplex temp = findingClients.get(j);
                        findingClients.set(j,findingClients.get(j+1));
                        findingClients.set(j+1, temp);
                    }
                }
            }

        //Aca se ordena :D
        /*
            ██╗███╗░░██╗░██████╗███████╗██████╗░████████╗       ░█████╗░░█████╗░██████╗░███████╗
            ██║████╗░██║██╔════╝██╔════╝██╔══██╗╚══██╔══╝       ██╔══██╗██╔══██╗██╔══██╗██╔════╝
            ██║██╔██╗██║╚█████╗░█████╗░░██████╔╝░░░██║░░░       ██║░░╚═╝██║░░██║██║░░██║█████╗░░
            ██║██║╚████║░╚═══██╗██╔══╝░░██╔══██╗░░░██║░░░       ██║░░██╗██║░░██║██║░░██║██╔══╝░░
            ██║██║░╚███║██████╔╝███████╗██║░░██║░░░██║░░░       ╚█████╔╝╚█████╔╝██████╔╝███████╗
            ╚═╝╚═╝░░╚══╝╚═════╝░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░       ░╚════╝░░╚════╝░╚═════╝░╚══════╝

            ██╗░░██╗███████╗██████╗░███████╗
            ██║░░██║██╔════╝██╔══██╗██╔════╝
            ███████║█████╗░░██████╔╝█████╗░░
            ██╔══██║██╔══╝░░██╔══██╗██╔══╝░░
            ██║░░██║███████╗██║░░██║███████╗
            ╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝╚══════╝
         */
        for (int i = 0; i<findingClients.size(); i++){
            payQueue.enqueue(findingClients.get(i).getValue());
            findingClients.remove(i);
            i--;
        }
    }

    public boolean advance(){
        while(occupiedCashiers<cashiers && !payQueue.isEmpty()){
            Client client = payQueue.dequeue();
            if(client != null) {
                payingClients.add(client);
                occupiedCashiers++;
            }
        }
        for(int i = 0; i<payingClients.size(); i++){
            if(!proccessPayGame(payingClients.get(i))){
                occupiedCashiers--;
                Duplex<String, Integer> dupl = new Duplex<>(payingClients.get(i).getCode(), clientGetTotal(payingClients.get(i)));
                out.insert(dupl, Integer.parseInt(payingClients.get(i).getCode()), 0);
                System.out.println(dupl.getKey());
                orderedOut.add(payingClients.get(i).getCode());
                payingClients.remove(i);
                i--;
            }
        }
        boolean bl = false;
        if((time >= clients.size()) && (payingClients.isEmpty()) && (findingClients.isEmpty()) && (payQueue.isEmpty())){
            bl = true;
        }
        time++;
        return bl;
    }

    public int clientGetTotal(Client client){
        int total = 0;
        StackList<Game> gameCopy = new StackList<>(client.getPaidGames());
        while(!gameCopy.isEmpty()){
            total += gameCopy.pop().getPrice();
        }
        return total;
    }

    public String getOut(){
        System.out.println(Arrays.toString(orderedOut.toArray()));
        String sOut = "";
        for(int i = 0; i<orderedOut.size(); i++){
            HashNode<Integer, Duplex<String, Integer>> node = out.getNodeByKey(Integer.parseInt(orderedOut.get(i)));
            sOut += node.getValue().getKey() + " " + node.getValue().getValue() + "\n";
            sOut += clientStackToString(findClientByCode(node.getValue().getKey())) + "\n";
        }
        return sOut;
    }

    public Client findClientByCode(String code){
        boolean found = false;
        Client client = null;
        for (int i = 0; i<clients.size() && !found; i++){
            if(clients.get(i).getCode().equals(code)){
                client = clients.get(i);
                found = true;
            }
        }
        return client;
    }

    public String clientStackToString(Client client){
        String sOut = "";
        StackList<Game> games = client.getPaidGames();
        while(!games.isEmpty()){
            sOut += games.pop().getCode() + " ";
        }
        return sOut;
    }

    public int getTime(){
        return time;
    }

    public ArrayList<Shelve> getShelves() {
        return shelves;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }
}