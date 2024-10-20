package src.game;

import src.View.*;
import src.logic.*;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public class GameLoop implements GameLoopInterface {

    GameState gameState;

    ViewInterface view;

    PlayerLogicInterface HumanLogic;
    PlayerLogicInterface BotLogic;


    public GameLoop(GameState gameState) {

        this.gameState = gameState;

        this.view = new View();
        this.HumanLogic = new HumanLogic();
        this.BotLogic = new BotLogic();
    }
 
    public void startGameLoop() {

        boolean keepPlaying = true;
        boolean cardsAvailable = true;

        while (keepPlaying) {

            // Get the current player
            PlayerInterface thisPlayer = gameState.getPlayers().get(gameState.getCurrentPlayerID());

            // Check if the game is over
            cardsAvailable = false;

            for (PileInterface pile : gameState.getPiles()) {
                if (!pile.isEmpty()) {
                    cardsAvailable = true;
                    break;
                }
            }

            if (!cardsAvailable) {
                keepPlaying = false;
                break;
            }

            // Print the game state
            view.printGameState(gameState.getPlayers(), gameState.getPiles(), gameState.getMarket());

            boolean validChoice = false;
            while(!validChoice) {}

            // Player's turn
            if (thisPlayer.isBot()) {
                this.BotLogic.playTurn(thisPlayer, gameState);
            } else {
                this.HumanLogic.playTurn(thisPlayer, gameState);
            }

            // Refill the market
            gameState.getMarket().refillMarket(gameState.getPiles());

            // Get the next player
            if(gameState.getCurrentPlayerID() == gameState.getPlayers().size() - 1) {
                gameState.setCurrentPlayerID(0);
            } else {
                gameState.setCurrentPlayerID(gameState.getCurrentPlayerID() + 1);
            }
        }

        // Display the winner

    }
}
