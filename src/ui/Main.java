package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.source.GameStore;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {
    private static Scanner sc;
    private GameStoreGUI gui;

    public Main(){
        gui = new GameStoreGUI();
    }

    public static void main(String[] args) {
        launch(args);

        System.out.println("Validacion por consola: ");
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
               // System.out.println(code+" "+ " "+codeGame +" "+" "+priceGame+ " "+quantityGame);
                gs.shelveAddGame(code, codeGame, priceGame, quantityGame);
            }
            sc.nextLine();
        }

        int clients = sc.nextInt();

        sc.nextLine();
        for(int i = 0; i<clients; i++){
            line = sc.nextLine();
            String[] sLine = line.split(" ");
            ArrayList<String> gameCodes = new ArrayList<>();
            for(int j = 1; j<sLine.length; j++){
                gameCodes.add(sLine[j]);
            }
            gs.addClient(sLine[0], gameCodes);
        }
        gs.setup();
        while(!gs.advance()){
            //Hola

        }
        System.out.print(gs.getOut());

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("./jfx/mainPane.fxml"));

        fxmlLoader.setController(gui);

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Store");
        primaryStage.show();

        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("./jfx/secondPane.fxml"));
        fxmlLoader1.setController(gui);
        Parent input = fxmlLoader1.load();
        gui.getBorderpane().setCenter(input);
    }
}