package model.source;

import model.ownImplementation.classes.HashTable;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {

    private GameStore test;

    public void setUpScenario1(){
        test = new GameStore(3,6);

    }

    @Test
    void addShelve() {
        setUpScenario1();
        String code = "D";
        int size = 8;
        test.addShelve(code,size);
        assertNotNull(test.getShelves().getNodeBykey((int)test.strToIntCode(code)));
    }

    @Test
    void shelveAddGame() {

    }

    @Test
    void addClient() {
    }

    @Test
    void findGame() {

    }

    @Test
    void clientAddGame() {

    }

    @Test
    void advance() {

    }
}