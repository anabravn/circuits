package server;
import java.rmi.RemoteException;

import game.*;
import game.Exceptions.InvalidMoveException;
import interfaces.CircuitsInterface;

public class CircuitsServer implements CircuitsInterface {
    Integer players = 0;
    Game game = null;

    public boolean init() throws RemoteException {
       if (players > 1) {
            game = new Game(10, players);
            return true;
       } 

       return false;
    }

    public Integer join() throws RemoteException {
        if (game == null)
            return players++;
        return -1;
    }

    public void move(Integer id, Integer x, Integer y) throws RemoteException, InvalidMoveException {
        if (game != null) {
                game.move(id, x, y);
        }
    }

    public String getBoard() throws RemoteException {
        if (game != null)
            return game.toString();
        return "";
    }

    public boolean getTurn(Integer player) throws RemoteException {
        return game.getTurn().equals(player);
    }

    public Integer checkWinner() throws RemoteException {
        if (game != null) {
            return game.checkWinner();
        }

        return -1;
    }
}
