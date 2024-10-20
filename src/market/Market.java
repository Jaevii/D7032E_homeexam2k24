package src.market;

import java.util.ArrayList;
import src.card.CardInterface;
import src.pile.PileInterface;

public abstract class Market implements MarketInterface {

    private ArrayList<CardInterface> marketCards;

    public Market(int size) {
        this.marketCards = new ArrayList<CardInterface>(size);

        // Initialize the market with null elements
        for (int i = 0; i < size; i++) {
            this.marketCards.add(null);
        }
    }

    @Override
    public void addCard(CardInterface card, int index) {
        card.flipCard();
        this.marketCards.set(index, card);
    }

    @Override
    public CardInterface takeCard(int index) {

        CardInterface card = this.marketCards.get(index);

        this.marketCards.set(index, null);

        return card;
    }

    @Override
    public boolean isSlotEmpty(int index) {
        return this.marketCards.get(index) == null;
    }

    @Override
    public ArrayList<CardInterface> getCards() {
        return this.marketCards;
    }

    @Override
    public abstract void refillMarket(ArrayList<PileInterface> piles);
}
