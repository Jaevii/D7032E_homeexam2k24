package src.market;

import java.util.ArrayList;

import src.pile.PileInterface;

public class SaladMarket extends Market {

    // Salad Market slot structure
    // (P1)  (P2)  (P3)
    // |0|   |2|   |4|
    // |1|   |3|   |5|

    private final static int MARKET_SIZE = 6;

    public SaladMarket() {
        super(MARKET_SIZE);
    }

    public void initMarket(ArrayList<PileInterface> piles) {
        for (int i = 0; i < MARKET_SIZE; i++) {

            int pileIndex = i / 2;

            this.addCard(piles.get(pileIndex).drawTopCard(), i);
        }
    }
}
