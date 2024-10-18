package src.market;

import src.card.CardInterface;

public interface MarketInterface {
    void addCard(CardInterface card, int index);
    boolean isSlotEmpty(int index);

    CardInterface takeCard(int index);
}
