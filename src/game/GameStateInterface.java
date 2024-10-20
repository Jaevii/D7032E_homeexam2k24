package src.game;

import java.util.ArrayList;

import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public interface GameStateInterface {

    void updateGameState(int currentPlayerID, ArrayList<PileInterface> piles, MarketInterface market, ArrayList<PlayerInterface> players);

    int getCurrentPlayerID();

    ArrayList<PileInterface> getPiles();

    MarketInterface getMarket();

    ArrayList<PlayerInterface> getPlayers();

    void setCurrentPlayerID(int currentPlayerID);
    
}
