package src;

import src.game.GameLoop;
import src.game.GameSetup;
import src.game.GameStateInterface;
import src.network.Client;

public class PointSalad {

    public PointSalad(String[] args) {

        System.out.println("Starting Point Salad with args: " + args[0]);

        // If client
        if (args[0].equals("127.0.0.1")) {

            try {
                new Client(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else { // Else server

            // Create setup
            GameSetup setup = new GameSetup(args);

            // Create game state
            GameStateInterface gameState = setup.getState();

            // Create game loop object
            GameLoop gameInstance = new GameLoop(gameState);

            // Start game loop
            gameInstance.startGameLoop();

        }

        

    }

    public static void main(String[] args) {
        new PointSalad(args);
    }
}