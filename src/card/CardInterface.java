package src.card;

public interface CardInterface {

    String getFrontside();
    CardType getBackside();

    boolean frontsideUp();
    void flipCard();

    String toString();
}
