package src.Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import src.PointSalad;
import src.card.CardInterface;
import src.card.CardType;
import src.deck.SaladDeck;
import src.game.GameSetup;
import src.market.MarketInterface;
import src.market.SaladMarket;
import src.pile.PileInterface;
import src.game.GameSetupInterface;
import src.game.GameStateInterface;

/* 
1. There can be between 2 and 6 players. 

2. The deck consists of 108 cards. A card has two sides, one with the criteria consisting of scoring rules, 
the other with one of six different vegetables (Pepper, Lettuce, Carrot, Cabbage, Onion, Tomato). There are 18 of each vegetable. 

3.  Form the deck so that 
    a.  2 Players: Use 6 random vegetable of each (36 cards in total) 
    b.  3 Players: Use 9 random vegetable of each (54 cards in total) 
    c.  4 Players: Use 12 random vegetable of each (72 cards in total) 
    d.  5 Players: Use 15 random vegetable of each (90 cards in total) 
    e.  6 Players: Use the entire deck 
Do not reveal which cards were removed 

4.  Shuffle the cards and create three roughly equal draw piles with roughly equal draw piles with point card sides visible. 

5.  Flip over two cards from each draw pile to form the vegetable market. 

6.  Randomly choose a start player 
*/

public class GameStateTest {

    @Test 
    @DisplayName("Test 1: Test too few players")
    public void testTooFewPlayers() {

        String[] args = {"0", "0"};

        assertThrows(IllegalArgumentException.class, () -> {
            PointSalad game = new PointSalad(args);
        });
    }

    @Test 
    @DisplayName("Test 1: Test too many players")
    public void testTooManyPlayers() {

        String[] args = {"4", "4"};

        assertThrows(IllegalArgumentException.class, () -> {
            PointSalad game = new PointSalad(args);
        });
    }

    @Test 
    @DisplayName("Test 2: Deck consists of 108 cards, 18 of each vegetable")
    public void testDeckSize() {

        SaladDeck deck = new SaladDeck();

        // Random number of players between 2 and 6
        int numberOfPlayers = (int) (Math.random() * 5) + 2;

        deck.createPiles(numberOfPlayers);
        ArrayList<CardInterface> cards = deck.getTestDeck();

        // Decksize should be 108
        int totalSize = 0;

        // Count of each vegetable should be 18
        int pepperCount = 0;
        int lettuceCount = 0;
        int carrotCount = 0;
        int cabbageCount = 0;
        int onionCount = 0;
        int tomatoCount = 0;

        for (CardInterface card : cards) {

            CardType type = card.getBackside();

            switch (type) {

                case PEPPER:
                    pepperCount += 1;
                    break;
                case LETTUCE:
                    lettuceCount += 1;
                    break;
                case CARROT:
                    carrotCount += 1;
                    break;
                case CABBAGE:
                    cabbageCount += 1;
                    break;
                case ONION:
                    onionCount += 1;
                    break;
                case TOMATO:
                    tomatoCount += 1;
                    break;
            }

            totalSize += 1;
        }

        assertEquals(108, totalSize);
        assertEquals(18, pepperCount);
        assertEquals(18, lettuceCount);
        assertEquals(18, carrotCount);
        assertEquals(18, cabbageCount);
        assertEquals(18, onionCount);
        assertEquals(18, tomatoCount);
    }
    
    @Test
    @DisplayName("Test 3: Test deck size for 2 players")
    public void testDeckSize2Players() {

        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(2);

        // Decksize should be 36
        int totalSize = 0;

        for (PileInterface pile : piles) {
            totalSize += pile.getSize();
        }

        assertEquals(36, totalSize);
    }

    @Test
    @DisplayName("Test 3: Test deck size for 3 players")
    public void testDeckSize3Players() {

        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(3);

        // Decksize should be 54
        int totalSize = 0;

        for (PileInterface pile : piles) {
            totalSize += pile.getSize();
        }

        assertEquals(54, totalSize);
    }

    @Test
    @DisplayName("Test 3: Test deck size for 4 players")
    public void testDeckSize4Players() {

        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(4);

        // Decksize should be 72
        int totalSize = 0;

        for (PileInterface pile : piles) {
            totalSize += pile.getSize();
        }

        assertEquals(72, totalSize);
    }

    @Test
    @DisplayName("Test 3: Test deck size for 5 players")
    public void testDeckSize5Players() {

        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(5);

        // Decksize should be 90
        int totalSize = 0;

        for (PileInterface pile : piles) {
            totalSize += pile.getSize();
        }

        assertEquals(90, totalSize);
    }

    @Test
    @DisplayName("Test 3: Test deck size for 6 players")
    public void testDeckSize6Players() {

        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(6);

        // Decksize should be 108
        int totalSize = 0;

        for (PileInterface pile : piles) {
            totalSize += pile.getSize();
        }

        assertEquals(108, totalSize);
    }

    @Test
    @DisplayName("Test 4: Test draw piles")
    public void testDrawPiles() {

        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(6);

        // Draw piles should be roughly equal
        int pile1Size = piles.get(0).getSize();
        int pile2Size = piles.get(1).getSize();
        int pile3Size = piles.get(2).getSize();

        int totalSize = pile1Size + pile2Size + pile3Size;

        int averageSize = totalSize / 3;

        assertTrue(Math.abs(pile1Size - averageSize) <= 1);
        assertTrue(Math.abs(pile2Size - averageSize) <= 1);
        assertTrue(Math.abs(pile3Size - averageSize) <= 1);
    }

    @Test
    @DisplayName("Test 5: Test vegetable market")
    public void testVegetableMarket() {

        int numberOfPlayers = (int) (Math.random() * 5) + 2;

        SaladDeck deck = new SaladDeck();
        ArrayList<PileInterface> piles = deck.createPiles(numberOfPlayers);

        int pile1SizeBefore = piles.get(0).getSize();
        int pile2SizeBefore = piles.get(1).getSize();
        int pile3SizeBefore = piles.get(2).getSize();

        // Flip over two cards from each draw pile to form the vegetable market
        MarketInterface market = new SaladMarket();
        market.refillMarket(piles);

        // Market should not be empty
        for (int i = 0; i < 6; i++) {
            assertFalse(market.isSlotEmpty(i));
        }

        // Check that each pile has two less cards
        assertEquals(pile1SizeBefore - 2, piles.get(0).getSize());
        assertEquals(pile2SizeBefore - 2, piles.get(1).getSize());
        assertEquals(pile3SizeBefore - 2, piles.get(2).getSize());
    }

    @Test
    @DisplayName("Test 6: Test start player")
    public void testStartPlayer() {
            
            String[] args = {"1", "4"};
    
            GameSetupInterface game = new GameSetup(args);
            GameStateInterface gameState = game.getState();
    
            // Randomly choose a start player
            int startPlayer = gameState.getCurrentPlayerID();
    
            assertTrue(startPlayer >= 0 && startPlayer < 4);
    }
}
