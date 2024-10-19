package src.game;

import java.util.ArrayList;

import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public interface GameStateInterface {

    public void updateGameState(int currentPlayerID, ArrayList<PileInterface> piles, MarketInterface market, ArrayList<PlayerInterface> players);
    
}
