
public class Board {
    boolean[] board;
    Integer width;
    Integer height;
    Integer size;

    /**
     * Cria um novo tabuleiro
     * @param w Largura do tabuleiro
     * @param h Altura do tabuleiro
     */
    public Board(Integer w, Integer h) {
        height = h;
        width = w;
        size = h * w;

        board = new boolean[size];

        for (int i = 0; i < size; i++) {
            board[i] = false;
        }

    }

    /**
     * Liga uma célula do tabuleiro.
     */
    public void set(Integer x, Integer y) {
        board[getIndex(x, y)] = true;
    }

    public void set(Integer index) {
        board[index] = true;
    }

    /**
     * Retorna true se a célula está ligada.
     */
    public boolean get(Integer x, Integer y) {
        return board[getIndex(x, y)];
    }

    public boolean get(Integer index) {
        return board[index];
    }

    /** 
     * Retorna uma representação do tabuleiro em uma String, com 
     * 'x' representando as células ligadas.
     */
    public String toString() {
        String out = "";
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                out = out.concat(board[getIndex(j, i)] ? "x " : "  ");
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

    private Integer getIndex(Integer x, Integer y) {
        return y * width + x;
    }

    /**
     * Busca em profundidade a partir de um nó fonte
     * no grafo das células ligadas
     * @param source Índice do nó fonte
     * @return Array de índices de nós pais
     */
    public Integer[] DepthFirstSearch(Integer source) {
        Integer[] parent = new Integer[size];

        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }

        if (get(source) && parent[source] == -1) {
            parent = dfsVisit(source, parent);
        }

        return parent;
    }

    private Integer[] dfsVisit(Integer node, Integer[] parent) {
        Integer l = leftChild(node);
        Integer r = rightChild(node);

        if(l != -1 && get(l)) {
            if(parent[l] == -1) {
                dfsVisit(l, parent);
            }
            parent[l] = node;
        }

        if(r != -1 && get(r)) {
            if(parent[r] == -1) {
                dfsVisit(r, parent);
            }
            parent[r] = node;
        }

        return parent;
    }

    public boolean hasCircuit(Integer[] parent) {

        for (int i = 0; i < size - 1; i++ ) {
            if ((i+1) % width != 0 && 
                get(i) && get(i+1) && 
                parent[i] != -1 && parent[i+1] != i) {
                    return true;
                }
        }

        return false;
    }
}
