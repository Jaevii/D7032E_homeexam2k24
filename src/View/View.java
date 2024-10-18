package src.View;

import java.util.ArrayList;

import src.card.CardInterface;
import src.card.CardType;
import src.market.MarketInterface;
import src.pile.PileInterface;
import src.player.PlayerInterface;
import src.service.Counter;

public class View implements ViewInterface {

    private static final String LINE_STRING = "\n----------------------------------------------------------------------------------------------------------";

    @Override
    public String displayHand(PlayerInterface player) {

        ArrayList<CardInterface> hand = player.getHand();
        Counter c = new Counter();

        String handString = "Criteria:\t";
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).frontsideSideUp() && hand.get(i).getBackside() != null) {
                handString += "[" + i + "] " + hand.get(i).getFrontside() + " (" + hand.get(i).getBackside().toString() + ")"
                        + "\t";
            }
        }

        handString += "\nVegetables:\t";
        // Sum up the number of each vegetable and show the total number of each
        // vegetable
        for (CardType vegetable : CardType.values()) {
            int count = c.countVegetables(hand, vegetable);
            if (count > 0) {
                handString += vegetable + ": " + count + "\t";
            }
        }
        return handString;


    }

    @Override
    public void sendToAllPlayers(ArrayList<PlayerInterface> players, String message) {
        for (PlayerInterface player : players) {
            player.sendMessage(message);
        }
    }

    @Override
    public String printMarket(MarketInterface market) {
        ArrayList<CardInterface> cards = market.getCards();

        String marketString = LINE_STRING + "\nMarket:" + LINE_STRING + "\n";

        int i = 0;
        String upperString = "";
        String lowerString = "";

        for (CardInterface card : cards) {

            if (card == null) {
                if (i % 2 == 0) {
                    upperString += "Empty Slot\t";
                } else {
                    lowerString += "Empty Slot\t";
                }
            } else {
                if (i % 2 == 0) {
                    upperString += card.toString() + "\t";
                } else {
                    lowerString += card.toString() + "\t";
                }
            }

            i++;
        }

        marketString += upperString + "\n" + lowerString + "\n";

        return marketString;
    }

    @Override
    public String printPiles(ArrayList<PileInterface> piles) {
        String pilesString = "Piles: \n";

        for (int i = 0; i < piles.size(); i++) {
            pilesString += "Pile " + i + ": " + piles.get(i).toString() + "\n";
        }

        return pilesString;
    }
    
}
