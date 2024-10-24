package src.logic;

import src.game.GameStateInterface;
import src.player.PlayerInterface;

public interface PlayerLogicInterface {
    
    void playTurn(PlayerInterface thisPlayer, GameStateInterface gameState);
}
