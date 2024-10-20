package src.player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Bot extends Player {
    
    public Bot(int playerID, Socket connection, ObjectInputStream inFromClient, ObjectOutputStream outToClient) {
        super(playerID, connection, inFromClient, outToClient);
    }

    @Override
    public boolean isBot() {
        return true;
    }
}
