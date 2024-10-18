package src.market;

import java.util.ArrayList;

import src.card.CardInterface;
import src.pile.PileInterface;

public interface MarketInterface {
    void addCard(CardInterface card, int index);
    boolean isSlotEmpty(int index);

    CardInterface takeCard(int index);
    ArrayList<CardInterface> getCards();
}
