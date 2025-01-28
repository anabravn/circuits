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

      if (argv.length > 1) {
         host=argv[0];
      }

     if(argv.length >= 2) {
         porta = Integer.parseInt(argv[1]);
      }

      String objName = "Circuits:"+porta.toString();
      CircuitsInterface server = null;

      try {
          Registry registry = LocateRegistry.getRegistry(host,porta);
          server = (CircuitsInterface) registry.lookup(objName);
          System.out.println("Object: '"+objName+"' localizado no servidor: "+host);
      } catch (Exception e) {
          System.err.println(e);
          System.exit(1);
      }

      Scanner scn = new Scanner(System.in);
      Integer player, winner = -1;

      try {
         player = server.join();

         System.out.println("Você é o jogador " + (player + 1));
         System.out.println("Esperando por jogadores...");

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

         System.out.printf("\nJogador %d vence!\n", winner + 1);
         scn.close();
      } catch (RemoteException ex) {
         System.err.println("Erro ao conectar ao servidor");
         ex.printStackTrace();
      }
   }
}
