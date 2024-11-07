public class Board {
    boolean[][] board;
    Integer width;
    Integer height;
    Integer size;

    /**
     * Cria um novo tabuleiro
     * @param w Largura do tabuleiro
     * @param h Altura do tabuleiro
     */
    public Board(Integer w, Integer h) {
        board = new boolean[w][h];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                board[i][j] = false;
            }
        }

        width = w;
        height = h;
        size = w * h;
    }

    /**
     * Liga uma célula do tabuleiro.
     */
    public void set(int x, Integer y) {
        board[y][x] = true;
    }

    /**
     * Desliga uma célula do tabuleiro.
     */
    public boolean get(Integer x, Integer y) {
        return board[y][x];
    }

    /** 
     * Retorna uma representação do tabuleiro em uma String, com 
     * 'x' representando as células ligadas.
     */
    public String toString() {
        String out = "";
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                out = out.concat(board[i][j] ? "x " : "  ");
            }
            out = out.concat("\n");
        }

        return out;
    }


    private Integer leftChild(Integer id) {
        Integer node = id + width;

        if (node >= size) {
            return -1;
        }

        return node;
    }

    private Integer rightChild(Integer id) {
        Integer node = id + 1;
        
        if (node % width == 0 || node >= size) {
            return -1;
        }

        return node;
    }

    private Integer getX(Integer id) {
        return id % width;
    }

    private Integer getY(Integer id) {
        return id / width;
    }

    /**
     * Retorna true se a célula está ligada.
     * Usado para identificar os nós válidos do grafo.
     * @param id
     */
    private boolean getValue(int id) {
        int x = getX(id), y = getY(id);
        return get(x, y);
    }
    

    Integer[] DepthFirstSearch() {
        Integer[] parent = new Integer[size];

        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }

        for(int node = 0; node < size; node++) {
            if (getValue(node) && parent[node] == -1) {
                parent = dfsVisit(node, parent);
            }
       }

        return parent;
    }

    Integer[] dfsVisit(Integer node, Integer[] parent) {
        Integer l = leftChild(node);
        Integer r = rightChild(node);

        if(l != -1 && getValue(l)) {
            if(parent[l] == -1) {
                parent[l] = node;
                dfsVisit(l, parent);
            }
        }

        if(r != -1 && getValue(r)) {
            if(parent[r] == -1) {
                parent[r] = node;
                dfsVisit(r, parent);
            }
        }

        return parent;
    }
}