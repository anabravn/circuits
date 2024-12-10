import java.util.*;

public class Circuits {
    public static void main(String args[]) {
        Game g = new Game(10);
        Scanner scn = new Scanner(System.in);

        for (int turn = 0; turn < 100; turn++) {
            Integer x, y;

            System.out.println(g.toString());

            System.out.print("x: ");
            x = scn.nextInt();

            System.out.print("y: ");
            y = scn.nextInt();

            g.place(turn % 2, x, y);
        }

        scn.close();

    }
}