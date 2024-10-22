package src.game;

import java.util.ArrayList;
import java.util.Scanner;

import src.deck.DeckInterface;
import src.deck.SaladDeck;
import src.market.MarketInterface;
import src.market.SaladMarket;
import src.network.Server;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public class GameSetup implements GameSetupInterface {

    private int numberOfHumans;
    private int numberOfBots;

    private int currentPlayerID;

    private ArrayList<PlayerInterface> players;
    private MarketInterface market;
    private ArrayList<PileInterface> piles;

    public GameSetup(String[] args) {

        // Parse the user input
        parseInput(args);

        // Setup the game
        setupGame();
        setupServer();

    }

    public void parseInput(String[] userInput) {

        if (userInput.length == 0) {

            System.out.println("Please enter the number of players (1-6): ");
            try (Scanner in = new Scanner(System.in)) {
                this.numberOfHumans = in.nextInt();
                System.out.println("Please enter the number of bots (0-5): ");
                this.numberOfBots = in.nextInt();
            }

        } else if (userInput[0].matches("\\d+")) {
                
                this.numberOfHumans = Integer.parseInt(userInput[0]);
                this.numberOfBots = Integer.parseInt(userInput[1]);

        } else {
            System.out.println("Invalid input. Exiting...");
            System.exit(0);
        }
    }

    public void setupGame() {

        // Create the deck
        DeckInterface deck = new SaladDeck();

        // Create the market
        this.market = new SaladMarket();

        // Create the piles
        this.piles = deck.createPiles(this.numberOfHumans + this.numberOfBots);

        // Initialize the market
        this.market.refillMarket(this.piles);
 
        // Randomly select the starting player
        this.currentPlayerID = (int) (Math.random() * (this.numberOfHumans + this.numberOfBots));
    }

    public void setupServer() {

        int numberOfPlayers = this.numberOfHumans + this.numberOfBots;

        // Control the number of players
        if (this.numberOfHumans < 1 || this.numberOfHumans > 6) {
            throw new IllegalArgumentException("Invalid number of players. Number of total players must be between 2 and 6");
        }

        if (this.numberOfBots < 0 || this.numberOfBots > 5) {
            throw new IllegalArgumentException("Invalid number of bots.");
        }

        if (numberOfPlayers < 2 || numberOfPlayers > 6) {
            throw new IllegalArgumentException("Invalid number of players.");
        }

        // Create the server
        try {
            System.out.println("Creating server...");
            Server server = new Server(this.numberOfHumans, this.numberOfBots);
            System.out.println("Server created.");
            this.players = server.getPlayers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GameState getState() {
        return new GameState(this.currentPlayerID, this.piles, this.market, this.players);
    }
}
