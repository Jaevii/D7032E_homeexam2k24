package src.player;

import java.util.ArrayList;

import src.card.CardInterface;

public interface PlayerInterface {
    
    // Player identity
    int getPlayerID();
    boolean isBot();

    // Player score
    int getScore();
    void setScore(int score);

    // Player cards
    ArrayList<CardInterface> getHand();
    void addCard(CardInterface card);

    // Player messaging
    void sendMessage(Object message);
    String readMessage();
}
