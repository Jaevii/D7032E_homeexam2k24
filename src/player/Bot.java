package src.player;

import java.util.ArrayList;
import java.util.Scanner;

import src.card.CardInterface;

public class Bot implements PlayerInterface {
    
    private int playerID;
    
    Scanner in = new Scanner(System.in);
    private ArrayList<CardInterface> hand = new ArrayList<CardInterface>();
    private int score = 0;

    public Bot(int playerID) {
        this.playerID = playerID;

        this.hand = new ArrayList<CardInterface>();

    }

    @Override
    public int getPlayerID() {
        // TODO Auto-generated method stub
        return this.playerID;
    }

    @Override
    public boolean isBot() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return this.score;
    }

    @Override
    public void setScore(int score) {
        // TODO Auto-generated method stub
        this.score = score;
    }

    @Override
    public ArrayList<CardInterface> getHand() {
        // TODO Auto-generated method stub
        return this.hand;
    }

    @Override
    public void addCard(CardInterface card) {
        this.hand.add(card);
    }

    @Override
    public void sendMessage(Object message) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMessage'");
    }

    @Override
    public String readMessage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readMessage'");
    }
    
}
