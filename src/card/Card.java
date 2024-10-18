package src.card;

public abstract class Card implements CardInterface {

    private String frontside;
    private CardType backside;
    private boolean frontsideSideUp;

    public Card(String frontside, CardType backside) {
        this.frontside = frontside;
        this.backside = backside;
        this.frontsideSideUp = true;
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
    public boolean frontsideSideUp() {
        return this.frontsideSideUp;
    }

    @Override
    public void flipCard() {
        if (this.frontsideSideUp) {
            this.frontsideSideUp = !this.frontsideSideUp;
        }
    }

    @Override
    public String toString() {

        if (this.frontsideSideUp) {
            return "Card: " + this.getFrontside();
        } else {
            return "Card: " + this.getBackside().toString();
        }
    }
    
}
