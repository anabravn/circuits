import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    Game g;

    @BeforeEach
    public void Setup() {
        g = new Game(10);
    }

    @Test
    public void TestPlaceOccupied() {
        Exception ex = assertThrows(InvalidMoveException.class, () -> {
            g.place(1, 2, 2);
            g.place(0, 2, 2);
        });

        assertTrue(ex.getMessage().contains("Célula ocupada"));
    }

    @Test
    public void TestPlaceOutOfBonds() {
        Exception ex = assertThrows(InvalidMoveException.class, () -> {
            g.place(1, 20, 20);
        });

        assertTrue(ex.getMessage().contains("Célula inexistente"));
    }

    @Test
    public void TestPlaceValid() {
        assertDoesNotThrow(() -> {
            g.place(1, 0, 0);
            g.place(0, 0, 1);
        });
    }
}
