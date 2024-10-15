package src.cards;

public interface CardInterface {

    // Getters
    public String getCriteria();
    public CardType getType();

    // Setters
    public void setCriteria(String criteria);
    public void setType(CardType type);

    boolean criteriaSideUp();
    public void flipCard();

    String toString();
}
