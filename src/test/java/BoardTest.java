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
        Integer[] expected = new Integer[]{-1, 0, 1, 0, -1, 2, 3, 6, 7};
        Integer[] actual;

        b.set(0, 0); b.set(0, 1); b.set(0, 2);
        b.set(1, 0); b.set(1, 2);
        b.set(2, 0); b.set(2, 1); b.set(2, 2);

        actual = b.DepthFirstSearch();
        assertArrayEquals(expected, actual);
    }
}
