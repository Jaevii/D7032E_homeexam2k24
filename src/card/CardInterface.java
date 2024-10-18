package src.card;

public interface CardInterface {

    String getFrontside();
    CardType getBackside();

    boolean frontsideSideUp();
    void flipCard();

    String toString();
}
