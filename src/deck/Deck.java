package src.deck;

import java.util.ArrayList;
import java.util.Collections;

import src.card.CardInterface;
import src.card.CardType;

import src.pile.PileInterface;

public class Deck implements DeckInterface {

    private static final int NUMBER_OF_PILES = 3;
    private int numberOfPlayers;

    private ArrayList<CardInterface> deck;
    private ArrayList<PileInterface> piles;

    public Deck(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        createDeck(numberOfPlayers);
    }

    @Override
    public void shuffle() {
        Collections.shuffle(deck);
    }

    @Override
    public void createDeck(int numberOfPlayers) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDeck'");
    }
}
