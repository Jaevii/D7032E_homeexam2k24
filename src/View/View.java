package src.view;

import java.util.ArrayList;

import src.card.CardInterface;
import src.card.CardType;
import src.counter.Counter;
import src.game.GameStateInterface;
import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;

public class View implements ViewInterface {

    private static final String LINE_STRING = "\n-----------------------------------------------------------------";

    @Override
    public String displayHand(PlayerInterface player) {

        ArrayList<CardInterface> hand = player.getHand();
        Counter c = new Counter();

        String handString = "Criteria:\t";
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).frontsideUp() && hand.get(i).getBackside() != null) {
                int index = i + 1;
                handString += "[" + index + "] " + hand.get(i).getFrontside() + " (" + hand.get(i).getBackside().toString() + ")"
                        + "\t";
            }
        }

        handString += "\nVegetables:\t";
        // Sum up the number of each vegetable and show the total number of each
        // vegetable
        for (CardType vegetable : CardType.values()) {
            int count = c.countVegetable(hand, vegetable);
            if (count > 0) {
                handString += vegetable + ": " + count + "\t";
            }
        }
        return handString;
    }

    @Override
    public void sendToAllPlayers(ArrayList<PlayerInterface> players, String message) {
        for (PlayerInterface player : players) {
            if (!player.isBot()) {
                player.sendMessage(message);
            }
        }
    }

    @Override
    public String printMarket(MarketInterface market) {
        ArrayList<CardInterface> cards = market.getCards();

        String marketString = LINE_STRING + "\nMarket:" + LINE_STRING + "\n";

        int i = 0;
        String upperString = "";
        String lowerString = "";
        char marketIndex = 'A';
        String upperChars = "";
        String lowerChars = "";

        for (CardInterface card : cards) {

            if (card == null) {
                if (i % 2 == 0) {
                    upperString += "Empty Slot\t";
                    upperChars += " [" + marketIndex + "]\t\t";
                } else {
                    lowerString += "Empty Slot\t";
                    lowerChars += " [" + marketIndex + "]\t\t";
                }
            } else {
                if (i % 2 == 0) {
                    upperString += card.toString() + "\t\t";
                    upperChars += " [" + marketIndex + "]\t\t";
                } else {
                    lowerString += card.toString() + "\t\t";
                    lowerChars += " [" + marketIndex + "]\t\t";
                }
            }

            i++;
            marketIndex++;
        }

        marketString += upperString + "\n" + upperChars + "\n\n" + lowerString + "\n" + lowerChars + LINE_STRING;

        return marketString;
    }

    @Override
    public String printPiles(ArrayList<PileInterface> piles) {
        String pilesString = LINE_STRING + "\nPiles:" + LINE_STRING;

        for (int i = 1; i < piles.size() + 1; i++) {
            pilesString +="\n"+ i + ". [" + piles.get(i-1).getCard(0) + "]\nCards left: " + piles.get(i-1).getSize();

            if (i-1 < 2) {
                pilesString += "\n";
            }
        }

        pilesString += LINE_STRING;

        return pilesString;
    }

    @Override
    public void printGameState(GameStateInterface gameState) {
        sendToAllPlayers(gameState.getPlayers(), printPiles(gameState.getPiles()));
        sendToAllPlayers(gameState.getPlayers(), printMarket(gameState.getMarket()));
    }
}
