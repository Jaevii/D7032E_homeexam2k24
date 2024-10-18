package src.market;

import java.util.ArrayList;

import src.card.CardInterface;

public interface MarketInterface {
    void addCard(CardInterface card, int index);
    boolean isSlotEmpty(int index);

    CardInterface takeCard(int index);
    ArrayList<CardInterface> getCards();
}
