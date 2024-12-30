
public class Game {
   Board p1, p2;
   Integer n;
   
   public Game(Integer size) {
        p1 = new Board(size);
        p2 = new Board(size);
        n = size;
   }

   private boolean checkValid(Board a, Board b, Integer x, Integer y) {
        return !a.get(x, y) && !b.get(x, y);
   }
   public void place(Integer player, Integer x, Integer y) throws InvalidMoveException {
        try {
            if (!checkValid(p2, p1, x, y)) 
                throw new InvalidMoveException("Célula ocupada");
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new InvalidMoveException("Célula inexistente");
        }

        if (player == 0) {
            p1.set(x, y);
        } else {
            p2.set(x,y);
        }
   }

   public String toString() {
        String out = "";

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                String chr;

                if (p1.get(j, i)) {
                    chr = "x ";
                } else if(p2.get(j, i)) {
                    chr = "o ";
                } else {
                    chr = "- ";
                }


                out = out.concat(chr);
            }
            out = out.concat("\n");
        }

        return out;
   }
}


class InvalidMoveException extends Exception {
    public InvalidMoveException(String m) {
        super(m);
    }
}