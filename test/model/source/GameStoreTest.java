package model.source;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {

    private GameStore test;
    private Scanner sc;
    private FileReader f;

    //The file simulates the input of the console

    public void setUpScenario1() {
        test = new GameStore(3,3);


    }

    public void setUpScenario2() throws FileNotFoundException {
        test = new GameStore(3,3);
        f = new FileReader("data/TestInputGames.txt");
        sc = new Scanner(f);
        String sh1 = "A";
        String sh2 = "B";
        String sh3 = "C";


        int qSh1 = 4;
        int qSh2 = 5;
        int qSh3 = 2;

        test.addShelve(sh1,qSh1);
        test.addShelve(sh2,qSh2);
        test.addShelve(sh3,qSh3);

    }

    public void setUpSceneario3() throws FileNotFoundException {
        FileReader f2 = new FileReader("data/TestInputClients.txt");
        Scanner sc2 = new Scanner(f2);
        test = new GameStore(3,3);
        f = new FileReader("data/TestInputGames.txt");
        sc = new Scanner(f);

        String sh1 = "A";
        String sh2 = "B";
        String sh3 = "C";
        String line = "";

        int qSh1 = 4;
        int qSh2 = 5;
        int qSh3 = 2;

        test.addShelve(sh1,qSh1);
        test.addShelve(sh2,qSh2);
        test.addShelve(sh3,qSh3);

        for (int i = 0; i <3 ; i++) {
            int quantity = sc.nextInt();
            for (int j = 0; j <quantity ; j++) {
                int codeGame = sc.nextInt();
                int priceGame = sc.nextInt();
                int quantityGame = sc.nextInt();
                test.shelveAddGame(test.getShelves().get(i).getCode(), codeGame, priceGame, quantityGame);
            }

        }
        int clients = sc2.nextInt();

        sc2.nextLine();
        for(int i = 0; i<clients; i++){
            line = sc2.nextLine();
            System.out.println(line);
            String[] sLine = line.split(" ");
            ArrayList<String> gameCodes = new ArrayList<>();
            for(int j = 1; j<sLine.length; j++){
                gameCodes.add(sLine[j]);
            }
            test.addClient(sLine[0], gameCodes);
        }
    }


    /**
     *Checks if the programs adds a new shelf with its correct code
     */

    @Test
    void addShelve(){
        setUpScenario1();
        String sh1 = "A";
        String sh2 = "B";
        String sh3 = "C";

        int qSh1 = 4;
        int qSh2 = 5;
        int qSh3 = 2;

        test.addShelve(sh1,qSh1);
        test.addShelve(sh2,qSh2);
        test.addShelve(sh3,qSh3);

        assertEquals(test.getShelves().get(0).getCode(),sh1);
        assertEquals(test.getShelves().get(1).getCode(),sh2);
        assertEquals(test.getShelves().get(2).getCode(),sh3);




    }

    //May change of assert?
    @Test
    void shelveAddGame() throws FileNotFoundException {
        setUpScenario2();
        for (int i = 0; i <3 ; i++) {
            int quantity = sc.nextInt();
            for (int j = 0; j <quantity ; j++) {
                int codeGame = sc.nextInt();
                int priceGame = sc.nextInt();
                int quantityGame = sc.nextInt();
                test.shelveAddGame(test.getShelves().get(i).getCode(), codeGame, priceGame, quantityGame);
            }

        }

        assertFalse(test.getShelves().get(0).getGames().isEmpty());
        assertFalse(test.getShelves().get(1).getGames().isEmpty());
        assertFalse(test.getShelves().get(2).getGames().isEmpty());



    }



    //Explain this
    @Test
    void proccessFindGame() throws FileNotFoundException {
        setUpSceneario3();
        Client client = test.getClients().get(3);
        assertTrue(test.proccessFindGame(client));
    }

    //Explain this
    @Test
    void proccessPayGame() throws FileNotFoundException {
        setUpSceneario3();
        Client client = test.getClients().get(4);
        assertFalse(test.proccessPayGame(client));

    }


}