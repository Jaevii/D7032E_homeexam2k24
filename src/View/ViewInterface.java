package src.View;

import java.util.ArrayList;

import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public interface ViewInterface {
    
    String displayHand(PlayerInterface player);

    void sendToAllPlayers(ArrayList<PlayerInterface> players, String message);

    String printPiles(ArrayList<PileInterface> piles);

    String printMarket(MarketInterface market);
}
