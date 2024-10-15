package src.cards;

public abstract class Card implements CardInterface {

    private String criteria;
    private CardType type;
    private boolean criteriaSideUp = true;

    public Card(String criteria, CardType type) {
        this.criteria = criteria;
        this.type = type;
    }

    @Override
    public String getCriteria() {
        // TODO Auto-generated method stub
        return this.criteria;
    }

    @Override
    public CardType getType() {
        // TODO Auto-generated method stub
        return this.type;
    }

    @Override
    public void setCriteria(String criteria) {
        // TODO Auto-generated method stub
        this.criteria = criteria;
    }

    @Override
    public void setType(CardType type) {
        // TODO Auto-generated method stub
        this.type = type;
    }

    @Override
    public boolean criteriaSideUp() {
        // TODO Auto-generated method stub
        return this.criteriaSideUp;
    }

    @Override
    public void flipCard() {
        // TODO Auto-generated method stub
        this.criteriaSideUp = !this.criteriaSideUp;
    }
    
}
