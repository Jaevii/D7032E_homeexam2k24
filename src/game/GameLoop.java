package src.game;

import java.util.ArrayList;

import src.card.CardInterface;
import src.logic.*;
import src.player.PlayerInterface;
import src.score.SaladScoreCalc;
import src.score.ScoreCalcInterface;
import src.view.*;

public class GameLoop implements GameLoopInterface {

    private GameStateInterface gameState;

    private ViewInterface view;
    private ScoreCalcInterface scoreCalc;

    private PlayerLogicInterface humanLogic;
    private PlayerLogicInterface botLogic;


    public GameLoop(GameStateInterface gameState) {

        this.gameState = gameState;

        this.view = new View();
        this.scoreCalc = new SaladScoreCalc();

        this.humanLogic = new HumanLogic();
        this.botLogic = new BotLogic();
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
            view.printGameState(gameState);

            // Player's turn
            if (thisPlayer.isBot()) {
                this.botLogic.playTurn(thisPlayer, gameState);
                // Display the bot's new hand
                view.sendToAllPlayers(players, "Bot " + thisPlayer.getPlayerID() + "'s new hand is now:\n\n" + view.displayHand(thisPlayer) + "\n");
            } else {
                this.humanLogic.playTurn(thisPlayer, gameState);
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
            for (CardInterface card : gameState.getMarket().getCards()) {
                if (card != null) {
                    cardsAvailable = true;
                    break;
                }
            }

            if (!cardsAvailable) {
                keepPlaying = false;
                view.sendToAllPlayers(players, "All piles and the market are empty. The game has ended!");
                break;
            }
        }

        // Clear the screen
        view.sendToAllPlayers(players, "\033[H\033[2J");

        // Score calculation
        view.sendToAllPlayers(players, "\n-------------------------------------- CALCULATING SCORES --------------------------------------\n");
        for (PlayerInterface player : players) {
            player.setScore(scoreCalc.calculateScore(player.getHand(), player, players));
        }

        // Find the winner
        int maxScore = 0;
        int winnerID = 0;
        for (PlayerInterface player : players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
                winnerID = player.getPlayerID();
            }
        }

        gameState.setWinnerID(winnerID);

        // Announce the winner
        for (PlayerInterface player : players) {
            if (player.getPlayerID() == winnerID) {
                player.sendMessage("\nCongratulations! You are the winner with a score of " + maxScore);
            } else {
                if(players.get(winnerID).isBot()) {
                    player.sendMessage("\nThe winner is Bot " + winnerID + " with a score of " + maxScore);
                } else {
                player.sendMessage("\nThe winner is Player " + winnerID + " with a score of " + maxScore);
                }
            }
        }


        boolean onlyBots = true;

        for (PlayerInterface player : players) {
            if (!player.isBot()) {
                onlyBots = false;
            }
        }

        // Wait for exit
        while (!onlyBots) {
            
            players.get(0).sendMessage("\nPress 'q' to exit the game.");

            String input = players.get(0).receiveMessage();

            if (input.equals("q")) {
                onlyBots = true;
            }

        }

    }
}
