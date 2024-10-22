package src.Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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
import src.player.Human;
import src.player.PlayerInterface;
import src.score.SaladScoreCalc;
import src.score.ScoreCalcInterface;
import src.game.GameStateInterface;


// 13. Calculate the score for each player according to the point cards in hand. 

public class ScoreTest {

    private ArrayList<PlayerInterface> players;
    private PlayerInterface player1;
    private PlayerInterface player2;
    private ScoreCalcInterface scoreCalc;

    @BeforeEach
    public void setUp() {
        players = new ArrayList<PlayerInterface>();
        player1 = new Human(0, null, null, null, false);
        player2 = new Human(1, null, null, null, false);
        players.add(player1);
        players.add(player2);
        
        scoreCalc = new SaladScoreCalc();
    }

    @Test
    @DisplayName("")
    public void mostLettuce(){
        
    }

    
    
}
