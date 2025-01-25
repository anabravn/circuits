package game;
import java.util.ArrayList;

public class Game {
   ArrayList<Board> boards = new ArrayList<>();
   Integer n, p, t = 0;
   
   public Game(Integer size, Integer players) {
        n = size;
        p = players;
        for(int i = 0; i < players; i++) {
            boards.add(new Board(size));
        }
   }

   public Game(Integer size) {
        this(size, 2);
   }

   public Integer checkWinner() {
        for (int i = 0; i < p; i++) {
            if (boards.get(i).hasCircuit()) {
                return i;
            }
        }

        return -1;
   }

   private boolean isOccupied(Integer x, Integer y) throws Exceptions.InvalidMoveException {
        for(int i = 0; i < p; i++) {
            try {
                if(boards.get(i).get(x, y))
                    return true;
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new Exceptions.InvalidMoveException("Célula inexistente");
            }
        }

        return false;
   }

   public void move(Integer player, Integer x, Integer y) throws  Exceptions.InvalidMoveException {
        if (isOccupied(x, y)) 
            throw new Exceptions.InvalidMoveException("Célula ocupada");

        if(t.equals(player)) {
            boards.get(player).set(x, y);
            t = (t + 1) % p;
        } else {
            throw new Exceptions.InvalidMoveException("Jogador " + (player + 1) + " não pode jogar agora.");
        }
    }

   public Integer getTurn() {
        return t;
   }

   public String toString() {
        String out = "";

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                String chr = "- ";

                for(int k = 0; k < p; k++) {
                    if(boards.get(k).get(j, i)) 
                        chr = Integer.toString(k + 1) + " ";
                }

                out = out.concat(chr);
            }
            out = out.concat("\n");
        }

        return out;
   }
}
