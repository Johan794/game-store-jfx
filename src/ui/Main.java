package ui;

import model.source.GameStore;

import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    private static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);

        int cases = sc.nextInt();
        int cashier = sc.nextInt();
        int shelves = sc.nextInt();

        GameStore gs = new GameStore(cashier, shelves);

        sc.nextLine();

        String code = "";
        int quantity = 0;
        String line = "";
        int codeGame = 0;
        int priceGame = 0;
        int quantityGame = 0;
        for(int i = 0; i<shelves; i++){
            line = sc.nextLine();
            String[] sLine = line.split(" ");
            code = sLine[0];
            quantity = Integer.parseInt(sLine[1]);
            gs.addShelve(code, quantity);
            for(int j = 0; j<quantity; j++){
                codeGame = sc.nextInt();
                priceGame = sc.nextInt();
                quantityGame = sc.nextInt();
                gs.shelveAddGame(code, codeGame, priceGame, quantityGame);
            }
        }

        int clients = sc.nextInt();

        sc.nextLine();
        for(int i = 0; i<clients; i++){
            line = sc.nextLine();
            System.out.println(line);
            String[] sLine = line.split(" ");
            ArrayList<String> gameCodes = new ArrayList<>();
            for(int j = 1; j< sLine.length; j++){
                gameCodes.add(sLine[i]);
            }
            gs.addClient(sLine[0], gameCodes);
        }
    }
}