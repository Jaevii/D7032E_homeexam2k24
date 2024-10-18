package src;

import src.card.*;
import src.deck.*;
import src.market.*;
import src.pile.*;

import java.util.ArrayList;

public class GameInstance {
    public static void main(String[] args) {

        int numPlayers = 2;
        ArrayList<PileInterface> piles;

        // Create a new deck
        SaladDeck deck = new SaladDeck();
        piles = deck.createPiles(numPlayers);

        // Create a new market
        SaladMarket market = new SaladMarket();
        market.initMarket(piles);

        // Print the market
        System.out.println("Market:" + market.getCards());
    }
}
