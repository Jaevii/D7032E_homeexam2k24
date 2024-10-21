package src.logic;

import java.util.ArrayList;

import src.card.CardInterface;
import src.game.GameStateInterface;
import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;
import src.view.View;

public class HumanLogic implements PlayerLogicInterface {

    private ArrayList<PileInterface> piles;
    private MarketInterface market;
    private View view;

    @Override
    public void playTurn(PlayerInterface thisPlayer, GameStateInterface gameState) {

        piles = gameState.getPiles();
        market = gameState.getMarket();
        view = new View();

        boolean validChoice = false;
        while (!validChoice) {

            thisPlayer.sendMessage("Take either one point card (1-3) or up to two veggie cards (A-F).\n");

            //TODO: Fix the logic for text input
            String pileChoice = thisPlayer.receiveMessage();
            if (pileChoice.matches("\\d")) {
                int pileIndex = Integer.parseInt(pileChoice) - 1;
                if (piles.get(pileIndex).getCard(0) == null) {
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
                    if (Character.toUpperCase(pileChoice.charAt(charIndex)) < 'A'
                            || Character.toUpperCase(pileChoice.charAt(charIndex)) > 'F') {
                        thisPlayer.sendMessage(
                                "\nInvalid choice. Please choose up to two veggie cards from the market.\n");
                        validChoice = false;
                        break;
                    }
                    int choice = Character.toUpperCase(pileChoice.charAt(charIndex)) - 'A';
                    int veggieIndex = (choice == 0 || choice == 1 || choice == 2) ? 0
                            : (choice == 3 || choice == 4 || choice == 5) ? 1 : -1;
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
            if (card.frontsideSideUp()) {
                criteriaCardInHand = true;
                break;
            }
        }
        if (criteriaCardInHand) {
            // Give the player an option to turn a criteria card into a veggie card
            thisPlayer.sendMessage("\n" + view.displayHand(thisPlayer)
                    + "\nWould you like to turn a criteria card into a veggie card? (Syntax example: n or 2)");
            String choice = thisPlayer.receiveMessage();
            if (choice.matches("\\d")) {
                int cardIndex = Integer.parseInt(choice);
                thisPlayer.getHand().get(cardIndex).flipCard();
            }
        }

        // Update the game state
        gameState.setMarket(market);
        gameState.setPiles(piles);
    }
}
