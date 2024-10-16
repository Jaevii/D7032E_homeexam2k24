package src.deck;

import java.util.ArrayList;

import src.card.CardInterface;
import src.pile.PileInterface;

public interface DeckInterface {

    void shuffle();

    void createDeck(int numberOfPlayers);

    ArrayList<PileInterface> createPiles();
    
}
