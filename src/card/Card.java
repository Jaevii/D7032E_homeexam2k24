package src.card;

public abstract class Card implements CardInterface {

    private String frontside;
    private CardType backside;
    private boolean frontsideUp;

    public Card(String frontside, CardType backside) {
        this.frontside = frontside;
        this.backside = backside;
        this.frontsideUp = true;
    }

    @Override
    public String getFrontside() {
        return this.frontside;
    }

    @Override
    public CardType getBackside() {
        return this.backside;
    }

    @Override
    public boolean frontsideUp() {
        return this.frontsideUp;
    }

    @Override
    public void flipCard() {
        if (this.frontsideUp) {
            this.frontsideUp = !this.frontsideUp;
        }
    }

    @Override
    public String toString() {

        if (this.frontsideUp) {
            return this.getFrontside() + " (" + this.getBackside() + ")";
        } else {
            return this.getBackside().toString();
        }
    }
    
}
