package src.card;

public class Card implements CardInterface {

    private String criteria;
    private CardType type;
    private boolean criteriaSideUp = true;

    public Card(String criteria, CardType type) {
        this.criteria = criteria;
        this.type = type;
    }

    @Override
    public String getCriteria() {
        return this.criteria;
    }

    @Override
    public CardType getType() {
        return this.type;
    }

    @Override
    public boolean criteriaSideUp() {
        return this.criteriaSideUp;
    }

    @Override
    public void flipCard() {
        this.criteriaSideUp = !this.criteriaSideUp;
    }
    
}
