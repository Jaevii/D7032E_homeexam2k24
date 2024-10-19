package src.game;

import src.View.View;
import src.View.ViewInterface;

public class GameLoop implements GameLoopInterface {

    GameState gameState;
    ViewInterface view;

    public GameLoop(GameState gameState) {
        this.gameState = gameState;
        this.view = new View();
    }
 
    public void startGameLoop() {

        boolean keepPlaying = true;

        while (keepPlaying) {

            // Check if the game is over

            // Player's turn

            // Get the next player

        }

        // Display the winner

    }
}
