package src.card;

public interface CardInterface {

    public String getCriteria();
    public CardType getType();

    boolean criteriaSideUp();
    public void flipCard();

    String toString();
}
