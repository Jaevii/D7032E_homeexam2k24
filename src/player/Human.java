package src.player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Human extends Player {

    public Human(int playerID, Socket connection, ObjectInputStream inFromClient, ObjectOutputStream outToClient) {
        super(playerID, connection, inFromClient, outToClient);
    }

    @Override
    public boolean isBot() {
        return false;
    }
}