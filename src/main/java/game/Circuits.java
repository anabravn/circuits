package game;
import java.util.*;

public class Circuits {
    public static void main(String args[]) {
        Game g = new Game(10);
        Scanner scn = new Scanner(System.in);
        Integer winner = -1;
        int turn = 0;

        while (winner == -1) {
            Integer x, y;
            System.out.println("\n" + g.toString());

            System.out.print("x: ");
            x = scn.nextInt();

            System.out.print("y: ");
            y = scn.nextInt();

            try {
                g.move(turn % 2, x, y);
                winner = g.checkWinner();
                turn++;

            } catch (Exceptions.InvalidMoveException ex) {
                System.out.println(ex);
            }
        }

        System.out.println("\n" + g.boards.get(winner).toString());
        System.out.printf("\nPlayer %d wins!\n", winner);
        scn.close();
    }
}