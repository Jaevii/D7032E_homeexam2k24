package src.game;

import java.util.ArrayList;

import src.View.View;
import src.deck.SaladDeck;
import src.market.SaladMarket;
import src.pile.PileInterface;

public class GameSetup implements GameSetupInterface {

    String[] userInput;
    

    public GameSetup(String[] args) {

        this.userInput = args;

        // Setup the game
        setupGame();

        // Setup the server
        setupServer();

    }

    public void setupGame() {
        // Create the view
        View view = new View();

        // Create the deck
        SaladDeck deck = new SaladDeck();

        // Create the market
        SaladMarket market = new SaladMarket();

        // Create the piles
        ArrayList<PileInterface> piles = deck.createPiles(2); //TODO: fix

        // Initialize the market
        market.initMarket(piles);
    }

    public void setupServer() {
        // Create the server
    }
}
