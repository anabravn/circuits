import server.CircuitsServer;
import interfaces.CircuitsInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server{
    public static void main(String argv[]){
        Integer porta=1099;
        String hostname="localhost";

        String objName = "Circuits:"+porta.toString();
        CircuitsInterface op = new CircuitsServer();
	    
        System.setProperty("java.rmi.server.hostname", hostname); 

        try {
            op = (CircuitsInterface) UnicastRemoteObject.exportObject(op,0);
        } catch (RemoteException e) {
            System.err.println("erro ao vincular objeto ");
            e.printStackTrace();
            System.exit(1);
        }

        /* Registrando o objeto remoto no rmiregisty */
        try {
            Registry regOP = LocateRegistry.getRegistry();
            regOP.bind(objName, op);
            System.out.println(objName + " registrado no servidor");
        } catch (Exception e) {
            System.err.println("Impossivel registrar o objeto");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
}
