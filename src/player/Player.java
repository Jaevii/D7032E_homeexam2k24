package src.player;

import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import src.card.CardInterface;

public abstract class Player implements PlayerInterface{

    private int playerID;
    private boolean online;
    private ArrayList<CardInterface> hand;
    private int score;

    private Socket connection;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    Scanner in = new Scanner(System.in);

    public Player(int playerID, Socket connection, ObjectInputStream inFromClient, ObjectOutputStream outToClient) {
        this.playerID = playerID;
        this.connection = connection;
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;

        this.hand = new ArrayList<CardInterface>();
        this.online = true;
        this.score = 0;
    }

    @Override
    public int getPlayerID() {
        return this.playerID;
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
    public boolean isOnline() {
        return this.online;
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
        if(online) {
            try {
                outToClient.writeObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(isBot()){
            System.out.println(message);                
        }
    }

    @Override
    public String receiveMessage() {
        if(online) {
            try {
                return (String) inFromClient.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                return in.nextLine();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    
}
