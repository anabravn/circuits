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

        /* Definição do nome do objeto */
        String objName = "Circuits:"+porta.toString();
        CircuitsInterface op = new CircuitsServer();
	    

        /*
         * Alteração da propriedad hostname na JVM que roda este processo
         * A propriedade java.rmi.server.hostname da máquina virtual java
         * representa o nome que deve ser associado aos stubs dos clientes
         * para que eles possam se vicular ao servidor.
         * É comum utilizar o ip da máquina na rede, ou algum outro nome
         * para a máquina que seja visivel pelos clientes.
         */
        System.setProperty("java.rmi.server.hostname", hostname); 

        /* Exportando o objeto remoto */
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
