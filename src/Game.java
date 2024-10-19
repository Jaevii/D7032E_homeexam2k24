package src;

import src.game.GameLoop;
import src.game.GameSetup;
import src.game.GameState;

public class Game {

    public Game(String[] args) {
                
        // Create setup
        GameSetup setup = new GameSetup(args);

        // Create game state
        GameState gameState = setup.setupGame();

        // Create game loop object
        GameLoop gameInstance = new GameLoop(gameState);

        // Start game loop
        gameInstance.startGameLoop();

    }

    public static void main(String[] args) {
        new Game(args);
    }
}