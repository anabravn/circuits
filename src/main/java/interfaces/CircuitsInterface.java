package interfaces;

import game.Exceptions.InvalidMoveException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CircuitsInterface extends Remote{
    public boolean init() throws RemoteException;
    public Integer join() throws RemoteException;
    public void move(Integer id, Integer x, Integer y) throws RemoteException, InvalidMoveException;
    public String getBoard() throws RemoteException;
    public boolean getTurn(Integer player) throws RemoteException;
    public Integer checkWinner() throws RemoteException;
}
