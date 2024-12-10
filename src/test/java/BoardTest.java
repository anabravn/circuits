import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest  {
    @Test
    public void TestToString() {
        Board b = new Board(3, 3);
        b.set(2, 2);
        b.set(1, 1);
        b.set(0, 0);
        
        String out = b.toString();
        assertEquals("x     \n  x   \n    x \n", out);
    }
    
    @Test
    public void TestDepthFirstSearch() {
        Board b = new Board(3, 3);
        Integer[] expected = new Integer[]{-1, 0, 1, 0, -1, 2, 3, 6, 5};
        Integer[] actual;

        b.set(0, 0); b.set(0, 1); b.set(0, 2);
        b.set(1, 0);                  b.set(1, 2);
        b.set(2, 0); b.set(2, 1); b.set(2, 2);

        actual = b.DepthFirstSearch(0);
        assertArrayEquals(expected, actual);
    }

    @Nested 
    class TestHasCircuit {

        @Test
        public void TestSimpleCircuit() {
            Board b = new Board(3, 3);
            Integer[] parent;

            b.set(0, 0); b.set(0, 1); b.set(0, 2);
            b.set(1, 0);                  b.set(1, 2);
            b.set(2, 0); b.set(2, 1); b.set(2, 2);

            parent = b.DepthFirstSearch(0);
            assertEquals(true, b.hasCircuit(parent));
        }

        @Test
        public void TestNoCircuit() {
            Board b = new Board(3, 3);
            Integer[] parent;

            b.set(0, 0);                  b.set(0, 2);
            b.set(1, 0);                  b.set(1, 2);
            b.set(2, 0); b.set(2, 1); b.set(2, 2);

            parent = b.DepthFirstSearch(0);
            assertEquals(false, b.hasCircuit(parent));

        }

        @Test
        public void TestSource() {
            Board b = new Board(4, 4);
            Integer[] parent;

            b.set(0, 0); b.set(0, 1); 
            b.set(1, 0); b.set(1, 1); 
                                            b.set(2, 2); b.set(2, 3); 
                                            b.set(3, 2); b.set(3, 3); 

            parent = b.DepthFirstSearch(0);
            assertEquals(true, b.hasCircuit(parent));

            parent = b.DepthFirstSearch(10);
            assertEquals(true, b.hasCircuit(parent));

            parent = b.DepthFirstSearch(5);
            assertEquals(false, b.hasCircuit(parent));

        }

        @Test
        public void TestShape() {
            Board b = new Board(4, 4);
            Integer[] parent;

            b.set(0, 0); b.set(0, 1); b.set(0, 2); b.set(0, 3);
            b.set(1, 0);                                   b.set(1, 3); 
            b.set(2, 0); b.set(2, 1);                  b.set(2, 3); 
                             b.set(3, 1); b.set(3, 2); b.set(3, 3); 

            parent = b.DepthFirstSearch(0);
            assertEquals(true, b.hasCircuit(parent));
        }
    }
}
