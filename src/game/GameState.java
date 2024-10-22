package src.game;

import java.util.ArrayList;

import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public class GameState implements GameStateInterface {
    
    // Keep track of the game state // 

    // Current player
    private int currentPlayerID;
    private int winnerID;

    // Piles
    private ArrayList<PileInterface> piles;

    // Market cards
    private MarketInterface market;

    // Player cards
    private ArrayList<PlayerInterface> players;

    public GameState(int currentPlayerID, ArrayList<PileInterface> piles, MarketInterface market, ArrayList<PlayerInterface> players) {
        this.currentPlayerID = currentPlayerID;
        this.piles = piles;
        this.market = market;
        this.players = players;
        this.winnerID = -1;
    }

    // Getters
    public int getCurrentPlayerID() {
        return currentPlayerID;
    }

    public ArrayList<PileInterface> getPiles() {
        return piles;
    }

    public MarketInterface getMarket() {
        return market;
    }

    public ArrayList<PlayerInterface> getPlayers() {
        return players;
    }

    public int getWinnerID() {
        return winnerID;
    }

    // Setters
    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }

    public void setPiles(ArrayList<PileInterface> piles) {
        this.piles = piles;
    }

    public void setMarket(MarketInterface market) {
        this.market = market;
    }

    public void setWinnerID(int winnerID) {
        this.winnerID = winnerID;
    }
}
