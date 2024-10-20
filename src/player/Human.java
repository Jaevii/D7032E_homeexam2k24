package src.player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Human extends Player {

    public Human(int playerID, Socket connection, ObjectInputStream inFromClient, ObjectOutputStream outToClient, boolean online) {
        super(playerID, connection, inFromClient, outToClient, online);
    }

    @Override
    public boolean isBot() {
        return false;
    }
}