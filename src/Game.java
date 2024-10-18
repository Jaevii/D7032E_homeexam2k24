package src;

import src.View.*;
import src.card.*;
import src.deck.*;
import src.market.*;
import src.network.Client;
import src.network.Server;
import src.pile.*;
import src.player.Human;

import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {

        View view = new View();
        SaladDeck deck = new SaladDeck();
        SaladMarket market = new SaladMarket();
        ArrayList<PileInterface> piles;

        piles = deck.createPiles(2);
        market.initMarket(piles);

        Human player1 = new Human(1, null, null, null);

        System.out.println("Player 1's hand before is: \n" + view.displayHand(player1));

        System.out.println(view.printPiles(piles));
        System.out.println(view.printMarket(market));

        player1.addCard(market.takeCard(0));
        player1.addCard(market.takeCard(3));
        player1.addCard(market.takeCard(4));
        player1.addCard(piles.get(0).drawTopCard());

        System.out.println("Player 1's hand after is: \n" + view.displayHand(player1));

        System.out.println(view.printPiles(piles));
        System.out.println(view.printMarket(market));

    }
}