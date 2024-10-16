package src.pile;

import java.util.ArrayList;
import src.card.CardInterface;

public interface PileInterface {

    public CardInterface drawTopCard();
    public CardInterface drawBottomCard();

    public int getSize();
    public boolean isEmpty();

    public ArrayList<CardInterface> getCards();
    public CardInterface getCard(int index);
    public void addCard(CardInterface card);
}
