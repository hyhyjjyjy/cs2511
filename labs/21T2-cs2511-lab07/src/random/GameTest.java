package random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    private int array4[] = { 62, 52, 3, 58, 67, 5, 11, 46};
    private int arrayMinus4[] = {39, 13, 98, 5, 43, 89, 20, 23};
    private int array0[] = { 60, 48, 29, 47, 15, 53, 91, 61};

    @Test
    public void randomTest1() {
        Game game = new Game(4);
        // array4[] = { 62, 52, 3, 58, 67, 5, 11, 46};
        for(int i = 0; i < 8; i++) {
            assertEquals(array4[i] < 50, game.battle());
        }
    }
    @Test
    public void randomTest3() {
        Game game = new Game(0);
        // array0[] = { 60, 48, 29, 47, 15, 53, 91, 61};
        for(int i = 0; i < 8; i++) {
            assertEquals(array0[i] < 50, game.battle());
        }
    }

}