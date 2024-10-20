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
    private ArrayList<PlayerInterface> players;

    public Server(int numberPlayers, int numberOfBots) throws Exception {

        players.add(new Human(0, null, null, null)); // add this instance as a player
        
        for (int i = 0; i < numberOfBots; i++) {
            players.add(new Bot(i + 1)); // add a bot
        }

        if (numberPlayers > 1)
            aSocket = new ServerSocket(2048);
        for (int i = numberOfBots + 1; i < numberPlayers + numberOfBots; i++) {
            
            Socket connectionSocket = aSocket.accept();

            ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
            
            players.add(new Human(i, connectionSocket, inFromClient, outToClient)); // add an online client
            
            System.out.println("Connected to player " + i);
            
            outToClient.writeObject("You connected to the server as player " + i + "\n");
        }
    }

    public ArrayList<PlayerInterface> getPlayers() {
        return players;
    }
}
