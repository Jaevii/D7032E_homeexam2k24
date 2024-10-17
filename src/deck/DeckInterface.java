package src.deck;

import java.util.ArrayList;

import src.card.CardInterface;
import src.pile.PileInterface;

public interface DeckInterface {

    ArrayList<PileInterface> createPiles(int numberOfPlayers);

    void shuffle(ArrayList<CardInterface> deck);
    
}
