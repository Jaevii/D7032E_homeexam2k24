package src.Testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import src.PointSalad;

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

public class InitGameStateTest {

    @Test 
    @DisplayName("Test 1: Test the number of players")
    public void testNumPlayers() {

        assertThrows(IllegalArgumentException.class, () -> {
            PointSalad game = new PointSalad(new String[] {"2", "5"});
        });
    }
    
}
