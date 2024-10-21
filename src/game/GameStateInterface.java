package src.game;

import java.util.ArrayList;

import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public interface GameStateInterface {

    int getCurrentPlayerID();

    ArrayList<PileInterface> getPiles();

    MarketInterface getMarket();

    ArrayList<PlayerInterface> getPlayers();

    void setCurrentPlayerID(int currentPlayerID);

    void setPiles(ArrayList<PileInterface> piles);

    void setMarket(MarketInterface market);
    
}
