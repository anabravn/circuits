package game;

public class Exceptions {
    public static class InvalidMoveException extends Exception {
        public InvalidMoveException(String m) {
            super(m);
        }
    }
}

