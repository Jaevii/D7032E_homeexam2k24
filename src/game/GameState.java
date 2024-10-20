package src.game;

import java.util.ArrayList;

import java.net.ServerSocket;

import src.View.View;
import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public class GameState implements GameStateInterface {
    
    // Keep track of the game state // 

    // Current player
    private int currentPlayerID;

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
    }

    // Called by the game loop after every game action
    public void updateGameState(int currentPlayerID, ArrayList<PileInterface> piles, MarketInterface market, ArrayList<PlayerInterface> players) {
        this.currentPlayerID = currentPlayerID;
        this.piles = piles;
        this.market = market;
        this.players = players;
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

    // Setters
    public void setCurrentPlayerID(int currentPlayerID) {
        this.currentPlayerID = currentPlayerID;
    }
}
