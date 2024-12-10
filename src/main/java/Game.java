
public class Game {
   Board p1, p2;
   Integer n;
   
   public Game(Integer size) {
        p1 = new Board(size);
        p2 = new Board(size);
        n = size;
   }

   public void place(Integer player, Integer x, Integer y) {
        // TODO: Validar posição
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
