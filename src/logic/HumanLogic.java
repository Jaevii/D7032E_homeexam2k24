package src.logic;

import src.game.GameStateInterface;
import src.player.PlayerInterface;

public class HumanLogic implements PlayerLogicInterface {

    @Override
    public void playTurn(PlayerInterface currentPlayer, GameStateInterface gameState) {
        System.out.println("Player turn");
    }
    
}
