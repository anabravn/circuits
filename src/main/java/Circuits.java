import java.util.*;

public class Circuits {
    public static void main(String args[]) {
        Game g = new Game(10);
        Scanner scn = new Scanner(System.in);
        Integer winner = -1;

        for (int turn = 0; turn < 100; turn++) {
            Integer x, y;

            System.out.println(g.toString());

            System.out.print("x: ");
            x = scn.nextInt();

            System.out.print("y: ");
            y = scn.nextInt();

            try {
                g.move(turn % 2, x, y);
                winner = g.checkWinner();
            } catch (InvalidMoveException ex) {
                System.out.println(ex);
                turn--;
            }

            if (winner != -1) {
                System.out.printf("\nPlayer %d wins!\n", winner);
                break;
            }
        }

        scn.close();
    }
}