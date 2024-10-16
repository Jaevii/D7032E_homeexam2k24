package src.pile;

import java.util.ArrayList;
import java.util.Collections;

import src.card.CardInterface;

public class Pile implements PileInterface {
    
    private ArrayList<CardInterface> cards;

    public Pile(ArrayList<CardInterface> cards) {
        this.cards = cards;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    @Override
    public CardInterface drawTopCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    @Override
    public CardInterface drawBottomCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    @Override
    public int getSize() {
        return cards.size();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public ArrayList<CardInterface> getCards() {
        return cards;
    }

    @Override
    public void addCard(CardInterface card) {
        this.cards.add(card);
    }

    @Override
    public CardInterface getCard(int index) {
        return this.cards.get(index);
    }
}
