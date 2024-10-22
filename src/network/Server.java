package src.network;

import src.player.Human;
import src.player.PlayerInterface;
import src.player.Bot;

import java.util.ArrayList;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket aSocket;
    private ArrayList<PlayerInterface> players = new ArrayList<PlayerInterface>();

    public Server(int numberPlayers, int numberOfBots) throws Exception {

        players.add(new Human(0, null, null, null, false)); // add this instance as a player
        
        for (int i = 0; i < numberOfBots; i++) {
            players.add(new Bot(i + 1, null, null, null)); // add a bot
        }

        if (numberPlayers > 1) {
            try {
                aSocket = new ServerSocket(2048);
                System.out.println("Created socket: " + aSocket);
            } catch (Exception e) {
                System.out.println("Port 2048 is already in use. Please close the other instance of the server and try again.");
                if (aSocket != null && !aSocket.isClosed()) {
                    aSocket.close();
                }
            }
        }

        for (int i = numberOfBots + 1; i < numberPlayers + numberOfBots; i++) {
            
            Socket connectionSocket = aSocket.accept();

            ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
            
            players.add(new Human(i, connectionSocket, inFromClient, outToClient, true)); // add an online client
            
            System.out.println("Connected to player " + i);
            
            outToClient.writeObject("You connected to the server as player " + i + "\n");
        }
    }

    public ArrayList<PlayerInterface> getPlayers() {
        return players;
    }
}
