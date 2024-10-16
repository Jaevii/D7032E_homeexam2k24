package src.card;

public interface CardInterface {

    public String getFrontside();
    public CardType getBackside();

    boolean frontsideSideUp();
    public void flipCard();

    String toString();
}
