package src;

import src.game.GameLoop;
import src.game.GameLoopInterface;
import src.game.GameSetup;
import src.game.GameSetupInterface;
import src.game.GameStateInterface;
import src.network.Client;

public class PointSalad {

    public PointSalad(String[] args) {

        // If client
        if (args.length > 0 && args[0].equals("127.0.0.1")) {

            try {
                new Client(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else { // Else server

            // Create setup
            GameSetupInterface setup = new GameSetup(args);

            // Create game state
            GameStateInterface gameState = setup.getState();

            // Create game loop object
            GameLoopInterface gameInstance = new GameLoop(gameState);
        }
    }

    public static void main(String[] args) {
        new PointSalad(args);
    }
}