package src.Testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import src.card.CardInterface;
import src.card.CardType;
import src.card.SaladCard;
import src.deck.SaladDeck;
import src.game.GameLoop;
import src.game.GameState;
import src.market.MarketInterface;
import src.market.SaladMarket;
import src.pile.PileInterface;
import src.player.Bot;
import src.player.PlayerInterface;
import src.game.GameStateInterface;

/*
7.  On a player’s turn the player may draft one or more cards and add to the player’s hand. Either: 
    a.  One point card from the top of any of the draw piles, or 
    b.  Two veggie cards from those available in the veggie market.  

8.  The player may opt to turn a point card to its vegetable side (but not the other way 
around).  

9.  Show the hand to the other players.  

10. Replace the market with cards from the top of the corresponding point card draw pile  

11. If a draw pile runs out of cards, then draw cards from the bottom of the draw pile with 
the most cards instead. 

12. Continue step 7-12 for the next player until there are no more cards in the Point 
Salad Market 

14. Announce the winner with the highest score.
 */

public class GameLoopTest {

    @Test
    @DisplayName("Test 8. Player may opt to turn a point card to its vegetable side (but not the other way around)")
    void testTurnPointCardToVeggie() {
        CardInterface card = new SaladCard("dummy criteria", CardType.CABBAGE);

        // Criteria side up
        assertTrue(card.frontsideUp());

        // Turn to veggie side
        card.flipCard();

        // Veggie side up
        assertFalse(card.frontsideUp());

        // Turn back to criteria side
        card.flipCard();

        // Veggie side still up
        assertFalse(card.frontsideUp());
    }

    @Test
    @DisplayName("Test 10. Replace the market with cards from the top of the corresponding point card draw pile")
    void testReplaceMarket() {

        // Create a deck
        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(3);

        // Create a market
        MarketInterface market = new SaladMarket();

        // Get the two top cards of each pile
        ArrayList<CardInterface> pileCards = new ArrayList<CardInterface>();
        for (PileInterface pile : piles) {
            pileCards.add(pile.getCard(0));
            pileCards.add(pile.getCard(1));
        }

        // Refill the market
        market.refillMarket(piles);

        // Get the cards in the market
        ArrayList<CardInterface> marketCards = market.getCards();

        // Check if each card is replaced from the correct pile
        for (int i = 0; i < 6; i++) {
            assertEquals(pileCards.get(i), marketCards.get(i));
        }
    }
    
    @Test
    @DisplayName("Test 11. If a draw pile runs out of cards, then draw cards from the bottom of the draw pile with the most cards instead")
    void testDrawFromBottom() {
        // Create a deck
        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(3);

        // Create a market
        MarketInterface market = new SaladMarket();

        // Refill the market
        market.refillMarket(piles);

        // Take one card from pile 1's market
        market.takeCard(0);

        // Remove all cards from pile 1
        while (!piles.get(0).isEmpty()) {
            piles.get(0).drawTopCard();
        }

        int randomPile = (int) (Math.random() * 2 ) + 1;

        System.out.println("randomPile: " + randomPile);

        // Remove one card from one of the other piles
        piles.get(randomPile).drawTopCard();

        CardInterface card;
        // Get the bottom card of the other pile
        if (randomPile == 1) {
            card = piles.get(2).getCard(piles.get(2).getSize() - 1);
        } else {
            card = piles.get(1).getCard(piles.get(1).getSize() - 1);
        }

        // Flip the card to its vegetable side
        card.flipCard();

        // Refill the market
        market.refillMarket(piles);

        // Check if the card is added to the empty pile
        assertEquals(card.toString(), market.takeCard(0).toString());
    }

    @Test
    @DisplayName("Test 12. Continue step 7-12 for the next player until there are no more cards in the Point Salad Market")
    void testEmptyPiles() {

        // Create a deck
        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(3);

        // Create a market
        MarketInterface market = new SaladMarket();

        // Refill the market
        market.refillMarket(piles);

        // Create players
        ArrayList<PlayerInterface> players = new ArrayList<PlayerInterface>();
        for (int i = 0; i < 3; i++) {
            players.add(new Bot(i, null, null, null)); 
        }

        int currentPlayer = (int) (Math.random() * 3);

        GameStateInterface gameState = new GameState(currentPlayer, piles, market, players);

        // Create a game loop
        GameLoop gameLoop = new GameLoop(gameState);

        // Start the game loop
        gameLoop.startGameLoop();

        // Check if the market is empty
        for (CardInterface card : gameState.getMarket().getCards()) {
            assertNull(card);
        }
    }

    @Test
    @DisplayName("Test 14. Announce the winner with the highest score")
    void testWinner() {

        // Create a deck
        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(3);

        // Create a market
        MarketInterface market = new SaladMarket();

        // Refill the market
        market.refillMarket(piles);

        // Create players
        ArrayList<PlayerInterface> players = new ArrayList<PlayerInterface>();
        for (int i = 0; i < 3; i++) {
            players.add(new Bot(i, null, null, null)); 
        }

        int currentPlayer = (int) (Math.random() * 3);

        GameStateInterface gameState = new GameState(currentPlayer, piles, market, players);

        // Create a game loop
        GameLoop gameLoop = new GameLoop(gameState);

        // Start the game loop
        gameLoop.startGameLoop();

        int maxScore = 0;
        int winnerID = 0;
        // Get the winner
        for (PlayerInterface player : players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
                winnerID = player.getPlayerID();
            }
        }

        assertEquals(winnerID, gameState.getWinnerID());
    }

}
