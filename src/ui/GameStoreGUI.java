package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.source.GameStore;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GameStoreGUI {
    private GameStore gameStore;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button btInfoShelvesAndClients;

    @FXML
    private Button btAmountCashiersAndShelves;

    @FXML
    private Button btInfoClient;

    @FXML
    private Label lbInfo;

    @FXML
    private Label lbTochange;

    @FXML
    private Label lbAmountS;

    @FXML
    private TextArea txtAInformation;


    @FXML
    private TextField txtAmountCashiers;

    @FXML
    private TextField txtAmountShelves;

    private int amountShelves;
    private int amountClientes;
    private int counter;


    public GameStoreGUI() {
        amountClientes = 0;
        amountShelves = 0;
        counter = 0;

    }

    public void setGameStore(int cashiers , int shelves){
        gameStore = new GameStore(cashiers,shelves);
    }
    public BorderPane getBorderpane(){
        return mainPane;
    }

    @FXML
    public void getAmounts(ActionEvent event) {
        try {
            if(gameStore==null){
                int cashiers = Integer.parseInt(txtAmountCashiers.getText());
                int shelves = Integer.parseInt(txtAmountShelves.getText());
                amountShelves = shelves;
                setGameStore(cashiers,shelves);
                lbInfo.setText("Please give the information for each shelve");
                btInfoShelvesAndClients.setVisible(true);
            }else{
                lbInfo.setText("Please give the information of all the clients");
                amountClientes = Integer.parseInt(txtAmountCashiers.getText());
                btInfoClient.setVisible(true);
            }
            txtAInformation.setVisible(true);


        }catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!!");
            alert.setHeaderText("Something went wrong");
            alert.setContentText("Please type valid values for the amount");
            alert.showAndWait();
        }

    }

    @FXML
    public void getInformation(ActionEvent event) throws IOException {
        if(counter<amountShelves){
            counter+=1;
            PrintWriter pw = new PrintWriter("data/info.txt");
            pw.print(txtAInformation.getText());
            pw.close();
            FileReader fileReader = new FileReader("data/info.txt");
            Scanner sc = new Scanner(fileReader);
           System.out.println(counter);
            //System.out.println(amountShelves);
            String line = sc.nextLine();
            String[] sLine = line.split(" ");
            String code = sLine[0];
            int quantity = Integer.parseInt(sLine[1]);
            gameStore.addShelve(code, quantity);
            for(int j = 0; j<quantity; j++){
                int codeGame = sc.nextInt();
                int priceGame = sc.nextInt();
                int quantityGame = sc.nextInt();
                // System.out.println(code+" "+ " "+codeGame +" "+" "+priceGame+ " "+quantityGame);
                gameStore.shelveAddGame(code, codeGame, priceGame, quantityGame);
            }
            if(counter==3){
                btInfoShelvesAndClients.setText("Continue");
            }


        } else {
            txtAmountShelves.setVisible(false);
            lbAmountS.setVisible(false);
            txtAInformation.setVisible(false);
            btInfoShelvesAndClients.setVisible(false);
            txtAmountCashiers.setText("");
            lbTochange.setText("Amount of clients");
            lbInfo.setText("Complete the fill");

        }
        txtAInformation.setText("");
    }

    @FXML
    public void getInformationClient(ActionEvent event) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("data/info.txt");
        pw.print(txtAInformation.getText());
        pw.close();
        FileReader fileReader = new FileReader("data/info.txt");
        Scanner sc = new Scanner(fileReader);
           System.out.println("Completo las shelves");
            //System.out.println(amountClientes);
            for(int i = 0; i<amountClientes; i++){
                String line = sc.nextLine();
                String[] sLine = line.split(" ");
                ArrayList<String> gameCodes = new ArrayList<>();
                for(int j = 1; j<sLine.length; j++){
                    gameCodes.add(sLine[j]);
                }
                gameStore.addClient(sLine[0], gameCodes);
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Today's operation");
            alert.setHeaderText(null);
            alert.setContentText("This are today's moves");

            //   Exception ex = new FileNotFoundException("Could not find file blabla.txt");

            //    StringWriter sw = new StringWriter();
            // PrintWriter pw1 = new PrintWriter(sw);
            //  ex.printStackTrace(pw1);

             gameStore.setup();
             while(!gameStore.advance()){
              System.out.println("gui");
             }
            String exceptionText = gameStore.getOut();

            Label label = new Label("The result of today was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();


        txtAInformation.setText("");



    }



}
