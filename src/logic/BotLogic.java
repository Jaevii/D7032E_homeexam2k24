package src.logic;

import java.util.ArrayList;

import src.game.GameStateInterface;
import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;
import src.score.ScoreCalc;
import src.card.CardInterface;

public class BotLogic implements PlayerLogicInterface {

    private ArrayList<PileInterface> piles;
    private MarketInterface market;
    private ScoreCalc scoreCalc = new ScoreCalc();

    @Override
    public void playTurn(PlayerInterface thisPlayer, GameStateInterface gameState) {

        piles = gameState.getPiles();
        market = gameState.getMarket();

        // Bot logic
        // The Bot will randomly decide to take either one point card or two veggie
        // cards
        // For point card the Bot will always take the point card with the highest score
        // If there are two point cards with the same score, the bot will take the first
        // one
        // For Veggie cards the Bot will pick the first one or two available veggies

        boolean emptyPiles = false;

        int choice = (int) (Math.random() * 2);

        if (choice == 0) {
                // Take a point card
                int highestPointCardIndex = 0;
                int highestPointCardScore = 0;
                for (int i = 0; i < piles.size(); i++) {
                    if (piles.get(i).getCard(0) != null) {
                        ArrayList<CardInterface> tempHand = new ArrayList<CardInterface>();
                        for (CardInterface handCard : thisPlayer.getHand()) {
                            tempHand.add(handCard);
                        }
                        tempHand.add(piles.get(i).getCard(0));
                        int score = scoreCalc.calculateScore(tempHand, thisPlayer, gameState.getPlayers());
                        if (score > highestPointCardScore) {
                            highestPointCardScore = score;
                            highestPointCardIndex = i;
                        }
                    }
                }
                if (piles.get(highestPointCardIndex).getCard(0) != null) {
                    thisPlayer.addCard(piles.get(highestPointCardIndex).drawTopCard());
                } else {
                    choice = 1; // buy veggies instead
                    emptyPiles = true;
                }
        } else if (choice == 1) {

            int cardsPicked = 0;
            int n = 0;
            for (CardInterface marketCard : market.getCards()) {
                if (marketCard != null && cardsPicked < 2) {
                    thisPlayer.addCard(market.takeCard(n));
                    cardsPicked++;
                }
                n++;
            }
            if (cardsPicked == 0 && !emptyPiles) {
                // Take a point card instead of veggies if there are no veggies left
                int highestPointCardIndex = 0;
                int highestPointCardScore = 0;
                for (int i = 0; i < piles.size(); i++) {
                    if (piles.get(i).getCard(0) != null) {
                        ArrayList<CardInterface> tempHand = new ArrayList<CardInterface>();
                        for (CardInterface handCard : thisPlayer.getHand()) {
                            tempHand.add(handCard);
                        }
                        tempHand.add(piles.get(i).getCard(0));
                        int score = scoreCalc.calculateScore(tempHand, thisPlayer, gameState.getPlayers());
                        if (score > highestPointCardScore) {
                            highestPointCardScore = score;
                            highestPointCardIndex = i;
                        }
                    }
                }
                if (piles.get(highestPointCardIndex).getCard(0) != null) {
                    thisPlayer.addCard(piles.get(highestPointCardIndex).drawTopCard());
                }
            }
        }

        // Update the game state
        gameState.setMarket(market);
        gameState.setPiles(piles);
    }
    
}
