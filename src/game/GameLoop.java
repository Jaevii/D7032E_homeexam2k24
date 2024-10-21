package src.game;

import java.util.ArrayList;

import src.logic.*;
import src.pile.PileInterface;
import src.player.PlayerInterface;
import src.view.*;

public class GameLoop implements GameLoopInterface {

    private GameState gameState;

    private ViewInterface view;

    private PlayerLogicInterface HumanLogic;
    private PlayerLogicInterface BotLogic;


    public GameLoop(GameState gameState) {

        this.gameState = gameState;

        this.view = new View();
        this.HumanLogic = new HumanLogic();
        this.BotLogic = new BotLogic();
    }
 
    public void startGameLoop() {

        boolean keepPlaying = true;
        boolean cardsAvailable = true;

        ArrayList<PlayerInterface> players = gameState.getPlayers();

        while (keepPlaying) {

            // Get the current player
            PlayerInterface thisPlayer = players.get(gameState.getCurrentPlayerID());
            
            // Display the current player
            if (thisPlayer.isBot()) {
                view.sendToAllPlayers(players, "It is Bot " + thisPlayer.getPlayerID() + "'s turn!");
            } else {
                view.sendToAllPlayers(players, "It is Player " + thisPlayer.getPlayerID() + "'s turn!");
            }

            thisPlayer.sendMessage("\n\nIts your turn!\n");
            thisPlayer.sendMessage("Your hand is:\n\n" + view.displayHand(thisPlayer) + "\n");

            // Print the game state
            view.printGameState(players, gameState.getPiles(), gameState.getMarket());

            // Player's turn
            if (thisPlayer.isBot()) {
                this.BotLogic.playTurn(thisPlayer, gameState);
                // Display the bot's new hand
                view.sendToAllPlayers(players, "Bot " + thisPlayer.getPlayerID() + "'s new hand is now:\n\n" + view.displayHand(thisPlayer) + "\n");
            } else {
                this.HumanLogic.playTurn(thisPlayer, gameState);
                // Display the player's new hand
                view.sendToAllPlayers(players, "Player " + thisPlayer.getPlayerID() + "'s new hand is now:\n\n" + view.displayHand(thisPlayer) + "\n");
            }

            // Refill the market
            gameState.getMarket().refillMarket(gameState.getPiles());

            // Get the next player
            if(gameState.getCurrentPlayerID() == players.size() - 1) {
                gameState.setCurrentPlayerID(0);
            } else {
                gameState.setCurrentPlayerID(gameState.getCurrentPlayerID() + 1);
            }

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
                view.sendToAllPlayers(players, "All piles are empty. The game has ended!");
                break;
            }
        }

        // Display the winner

    }
}
