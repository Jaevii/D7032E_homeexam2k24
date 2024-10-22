package src.view;

import java.util.ArrayList;

import src.game.GameStateInterface;
import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public interface ViewInterface {
    
    String displayHand(PlayerInterface player);

    void sendToAllPlayers(ArrayList<PlayerInterface> players, String message);

    String printPiles(ArrayList<PileInterface> piles);

    String printMarket(MarketInterface market);

    void printGameState(GameStateInterface gameState);
}
