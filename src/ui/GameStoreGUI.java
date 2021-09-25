package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.source.GameStore;

import java.io.*;
import java.util.Arrays;

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


    public GameStoreGUI() {
        amountClientes = 0;
        amountShelves = 0;

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
                amountShelves = shelves-1;
                setGameStore(cashiers,shelves);
                lbInfo.setText("Please give the information for each shelve");
                btInfoShelvesAndClients.setVisible(true);
            }else{
                lbInfo.setText("Please give the information for each client");
                amountClientes = Integer.parseInt(txtAmountCashiers.getText());
                amountClientes = amountClientes-1;
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
        PrintWriter pw = new PrintWriter("data/info.txt");
        FileReader fileReader = new FileReader("data/info.txt");
        BufferedReader bf = new BufferedReader(fileReader);
        pw.print(txtAInformation.getText());
        pw.close();
        if(amountShelves!=0){
            System.out.println(amountShelves);
            //For shelves
            /*
            while (bf.readLine()!=null){

            }
             */
            amountShelves--;

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
        FileReader fileReader = new FileReader("data/info.txt");
        BufferedReader bf = new BufferedReader(fileReader);
        pw.print(txtAInformation.getText());
        pw.close();
        if(amountClientes!=0){
            System.out.println("Completo las shelves");
            System.out.println(amountClientes);
            //For clients

            /*
            while (bf.readLine()!=null){


            }
             */
            amountClientes--;
        }else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("Look, an Exception Dialog");
            alert.setContentText("Could not find file blabla.txt!");

            Exception ex = new FileNotFoundException("Could not find file blabla.txt");

            StringWriter sw = new StringWriter();
            PrintWriter pw1 = new PrintWriter(sw);
            ex.printStackTrace(pw1);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

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
        }

        txtAInformation.setText("");



    }



}
