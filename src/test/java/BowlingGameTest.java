import org.example.BowlingGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {

    private BowlingGame game = new BowlingGame();

    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++) {
            game.roll(pins);
        }
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Test
    public void testAllGutterBalls() {
        rollMany(20, 0);
        assertEquals(0, game.score());
    }

    @Test
    public void testAllOnes() {
        rollMany(20, 1);
        assertEquals(20, game.score());
    }

    @Test
    public void testOneSpare() {
        rollSpare();
        game.roll(3);
        rollMany(17, 0);
        assertEquals(16, game.score());
    }

    @Test
    public void testOneStrike() {
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);
        assertEquals(24, game.score());
    }

    @Test
    public void testPerfectGame() {
        rollMany(12, 10);
        assertEquals(300, game.score());
    }

    @Test
    public void testAllSpares() {
        for (int i = 0; i < 10; i++) {
            rollSpare();
        }
        game.roll(5);
        assertEquals(150, game.score());
    }

    @Test
    public void testMixedStrikesAndSpares() {
        rollStrike();    // 10 + 5 + 5 = 20
        rollSpare();     // 5 + 5 + 10 = 20
        rollStrike();    // 10 + 10 + 5 = 25
        rollStrike();    // 10 + 5 + 5 = 25
        rollSpare();     // 5 + 5 + 10 = 20
        rollStrike();    // 10 + 0 + 0 = 10
        game.roll(0);    // 0
        game.roll(0);    // 0
        game.roll(0);    // 0
        game.roll(10);   // 10 + 10 + 10 = 20
        rollStrike();    // 10 + 5 + 5 = 25
        rollSpare();     // 5 + 5 + 10 = 20
        game.roll(10);   // bonus roll

        assertEquals(175, game.score());
    }

    @Test
    public void testLastFrameStrike() {
        rollMany(18, 0);
        rollStrike();
        game.roll(10);
        game.roll(10);
        assertEquals(30, game.score());
    }

    @Test
    public void testLastFrameSpare() {
        rollMany(18, 0);
        rollSpare();
        game.roll(5);
        assertEquals(15, game.score());
    }
}
