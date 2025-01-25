package game;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game g;

    @BeforeEach
    public void Setup() {
        g = new Game(10);
    }

    @Nested
    class TestMove {
        @Test
        public void TestMoveOccupied() {
            Exception ex = assertThrows(Exceptions.InvalidMoveException.class, () -> {
                g.move(1, 2, 2);
                g.move(0, 2, 2);
            });

            assertTrue(ex.getMessage().contains("Célula ocupada"));
        }

        @Test
        public void TestMoveOutOfBonds() {
            Exception ex = assertThrows(Exceptions.InvalidMoveException.class, () -> {
                g.move(1, 20, 20);
            });

            assertTrue(ex.getMessage().contains("Célula inexistente"));
        }

        @Test
        public void TestMoveNegative() {
            Exception ex = assertThrows(Exceptions.InvalidMoveException.class, () -> {
                g.move(1, -1, 2);
            });

            assertTrue(ex.getMessage().contains("Célula inexistente"));
        }

        @Test
        public void TestMoveOkay() {
            assertDoesNotThrow(() -> {
                g.move(1, 0, 0);
                g.move(0, 0, 1);
            });
        }
    }
}
