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

    public void sendToAllPlayers(String message) {
		for(PlayerInterface player : players) {
			player.sendMessage(message);
		}
	}

    public PlayerInterface getPlayer(int playerID) {
        return players.get(playerID);
    }

    public ArrayList<PlayerInterface> getPlayers() {
        return players;
    }
    
    public Server(int numberPlayers, int numberOfBots) throws Exception {
        players.add(new Human(0, null, null, null)); // add this instance as a player
        // Open for connections if there are online players
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
}
