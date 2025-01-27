import interfaces.CircuitsInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class Client {
   static void clear() {
      System.out.print("\033[H\033[2J"); 
   }

   public static void main(String argv[]){
      String host="localhost";
      Integer porta = 1099;

      String objName = "Circuits:"+porta.toString();
      CircuitsInterface server = null;

      try {
          Registry registry = LocateRegistry.getRegistry(host,porta);
          server = (CircuitsInterface) registry.lookup(objName);
          System.out.println("objeto: '"+objName+"' localizado no servidor: "+host);
      } catch (Exception e) {
          System.err.println(e);
          System.exit(1);
      }

      Scanner scn = new Scanner(System.in);
      Integer player, winner = -1;

      try {
         player = server.join();

         System.out.println("You are player " + (player + 1));
         System.out.println("Waiting for players...");

         while (server.getPlayers() < 2)
            ;
            
         server.init();

         clear();

         while (winner == -1) {
            Integer x, y;

            System.out.println(server.getBoard());

            while (!server.getTurn(player)) {
               winner = server.getWinner();

               if (winner != -1)
                  break;
            }

            clear();
            System.out.println(server.getBoard());

            if (winner == -1) {
               System.out.print("> ");
               x = scn.nextInt();
               y = scn.nextInt();

               System.out.println();

               try {
                  server.move(player, x, y);
               } catch (Exception ex) {
                  System.out.println(ex);
               }
            }
         }

         System.out.printf("\nPlayer %d wins!\n", winner + 1);
         scn.close();
      } catch (RemoteException ex) {
         System.err.println("Unable to connect to server");
         ex.printStackTrace();
      }
   }
}
