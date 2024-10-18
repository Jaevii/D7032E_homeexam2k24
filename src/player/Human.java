package src.player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import src.card.CardInterface;

public class Human implements PlayerInterface {

    private int playerID;
    private boolean online;
    
    private Socket connection;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    
    Scanner in = new Scanner(System.in);
    private ArrayList<CardInterface> hand = new ArrayList<CardInterface>();
    private int score = 0;

    public Human(int playerID, Socket connection, ObjectInputStream inFromClient, ObjectOutputStream outToClient) {
        this.playerID = playerID;
        this.connection = connection;
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;

        this.hand = new ArrayList<CardInterface>(0);

        if (connection == null)
            this.online = false;
        else
            this.online = true;

    }

    @Override
    public int getPlayerID() {
        return this.playerID;
    }

    @Override
    public boolean isBot() {
        return false;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public ArrayList<CardInterface> getHand() {
        return this.hand;
    }

    @Override
    public void addCard(CardInterface card) {
        this.hand.add(card);
    }

    @Override
    public void sendMessage(Object message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String readMessage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readMessage'");
    }
    
}