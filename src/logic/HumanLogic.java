package src.logic;

import java.util.ArrayList;

import src.card.CardInterface;
import src.game.GameStateInterface;
import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;
import src.view.View;
import src.view.ViewInterface;

public class HumanLogic implements PlayerLogicInterface {

    private ArrayList<PileInterface> piles;
    private MarketInterface market;
    private ViewInterface view;

    @Override
    public void playTurn(PlayerInterface thisPlayer, GameStateInterface gameState) {

        piles = gameState.getPiles();
        market = gameState.getMarket();
        view = new View();

        boolean validChoice = false;
        while (!validChoice) {

            thisPlayer.sendMessage("Take either one point card (1-3) or up to two veggie cards (A-F), i.e: AF.\n");

            String pileChoice = thisPlayer.receiveMessage();
            if (pileChoice.matches("\\d")) {
                int pileIndex = Integer.parseInt(pileChoice) - 1;

                if (pileIndex < 0 || pileIndex > 2) {
                    thisPlayer.sendMessage("\nInvalid choice. Please choose a pile between 1 and 3.\n");
                    continue;
                } else if (piles.get(pileIndex).getCard(0) == null) {
                    thisPlayer.sendMessage("\nThis pile is empty. Please choose another pile.\n");
                    continue;
                } else {
                    thisPlayer.addCard(piles.get(pileIndex).drawTopCard());
                    thisPlayer.sendMessage(
                            "\nYou took a card from pile " + (pileIndex) + " and added it to your hand.\n");
                    validChoice = true;
                }
            } else {
                int takenVeggies = 0;

                for (int charIndex = 0; charIndex < pileChoice.length(); charIndex++) {

                    char inputChar = Character.toUpperCase(pileChoice.charAt(charIndex));

                    if (inputChar < 'A' || inputChar > 'F') {
                        if (takenVeggies == 1) {
                            thisPlayer.sendMessage("\nInvalid choice. Please choose another veggie card from the market.\n");
                        } else {
                            thisPlayer.sendMessage("\nInvalid choice. Please choose up to two veggie cards from the market.\n");
                        }
                        validChoice = false;
                        break;
                    }

                    int veggieIndex = inputChar - 'A';

                    if (market.getCards().get(veggieIndex) == null) {
                        thisPlayer.sendMessage("\nThis veggie is empty. Please choose another pile.\n");
                        validChoice = false;
                        break;
                    } else {
                        if (takenVeggies == 2) {
                            validChoice = true;
                            break;
                        } else {
                            CardInterface chosenCard = market.takeCard(veggieIndex);
                            thisPlayer.addCard(chosenCard);
                            takenVeggies++;
                            thisPlayer.sendMessage(
                                    "\nYou took: " + (chosenCard.getBackside()) + " and added it to your hand.\n");
                            validChoice = true;
                        }
                    }
                }
            }
        }

        // Check if the player has any criteria cards in their hand
        boolean criteriaCardInHand = false;
        for (CardInterface card : thisPlayer.getHand()) {
            if (card.frontsideUp()) {
                criteriaCardInHand = true;
                break;
            }
        }
        if (criteriaCardInHand) {
            // Give the player an option to turn a criteria card into a veggie card
            thisPlayer.sendMessage("\n" + view.displayHand(thisPlayer)
                    + "\nWould you like to turn a criteria card into a veggie card? (Syntax example: n (no) or 1, 2, 3...)");
            String choice = thisPlayer.receiveMessage();
            if (choice.matches("\\d")) {
                int cardIndex = Integer.parseInt(choice) - 1;
                thisPlayer.getHand().get(cardIndex).flipCard();
            }
        }

        // Update the game state
        gameState.setMarket(market);
        gameState.setPiles(piles);
    }
}
