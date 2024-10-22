package src.Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import src.card.CardInterface;
import src.card.CardType;
import src.card.SaladCard;
import src.player.Human;
import src.player.PlayerInterface;
import src.score.SaladScoreCalc;
import src.score.ScoreCalcInterface;


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
    public void mostOfVegetable(){

        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("MOST TOMATO = 10", CardType.ONION);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1 
        CardInterface pointCardTomato = new SaladCard("", CardType.TOMATO);
        pointCardTomato.flipCard();

        player1.addCard(pointCardTomato);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(10, score);
    }

    @Test
    public void fewestOfVegetable(){

        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("FEWEST TOMATO = 10", CardType.ONION);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player2
        CardInterface pointCardTomato = new SaladCard("", CardType.TOMATO);
        pointCardTomato.flipCard();

        player2.addCard(pointCardTomato);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(10, score);
    }

    @Test
    public void evenOddVegetable(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("PEPPER: EVEN=7, ODD=3", CardType.LETTUCE);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardPepper = new SaladCard("", CardType.PEPPER);
        pointCardPepper.flipCard();

        player1.addCard(pointCardPepper);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(3, score);

        // Add another corresponding point card to player1
        player1.addCard(pointCardPepper);

        score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(7, score);
    }

    @Test
    public void perVegetable(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("2 / LETTUCE", CardType.PEPPER);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();

        int i = (int) (Math.random() * 10);
        for (int j = 0; j < i; j++) {
            player1.addCard(pointCardLettuce);
        }

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(2*i, score);
    }

    @Test
    public void additionSameVegetable(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("LETTUCE + LETTUCE = 5", CardType.PEPPER);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();

        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardLettuce);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(5, score);
    }

    @Test
    public void additionDifferentVegetables(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("CARROT + ONION = 5", CardType.PEPPER);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardCarrot = new SaladCard("", CardType.CARROT);
        pointCardCarrot.flipCard();
        CardInterface pointCardOnion = new SaladCard("", CardType.ONION);
        pointCardOnion.flipCard();

        player1.addCard(pointCardCarrot);
        player1.addCard(pointCardOnion);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(5, score);
    }

    @Test
    public void multiplePerVegetable(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("1 / LETTUCE,  1 / ONION", CardType.PEPPER);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();
        CardInterface pointCardOnion = new SaladCard("", CardType.ONION);
        pointCardOnion.flipCard();

        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardOnion);
        player1.addCard(pointCardOnion);
        player1.addCard(pointCardOnion);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(4, score);
    }

    @Test
    public void multiplePerVegetableNegative() {
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("3/LETTUCE,  -1/ONION,  -1/PEPPER", CardType.PEPPER);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();
        CardInterface pointCardOnion = new SaladCard("", CardType.ONION);
        pointCardOnion.flipCard();
        CardInterface pointCardPepper = new SaladCard("", CardType.PEPPER);
        pointCardPepper.flipCard();

        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardLettuce);

        player1.addCard(pointCardOnion);
        player1.addCard(pointCardOnion);
        player1.addCard(pointCardOnion);

        player1.addCard(pointCardPepper);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(5, score);
    }


    @Test
    public void mostTotalVegetables(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("MOST TOTAL VEGETABLE = 10", CardType.PEPPER);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();
        CardInterface pointCardOnion = new SaladCard("", CardType.ONION);
        pointCardOnion.flipCard();

        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardOnion);
        player1.addCard(pointCardOnion);
        player1.addCard(pointCardOnion);

        player2.addCard(pointCardOnion);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(10, score);
    }

    @Test
    public void fewestTotalVegetables(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("FEWEST TOTAL VEGETABLE = 7", CardType.LETTUCE);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();
        CardInterface pointCardOnion = new SaladCard("", CardType.ONION);
        pointCardOnion.flipCard();

        player1.addCard(pointCardLettuce);
        player2.addCard(pointCardOnion);
        player2.addCard(pointCardOnion);
        player2.addCard(pointCardOnion);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(7, score);
    }

    @Test
    public void perMissingVegetables(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("5 / MISSING VEGETABLE TYPE", CardType.CABBAGE);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();
        CardInterface pointCardOnion = new SaladCard("", CardType.ONION);
        pointCardOnion.flipCard();

        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardOnion);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(20, score);
    }

    @Test
    public void moreThanOfType(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("5 / VEGETABLE TYPE >=3", CardType.CARROT);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();
        CardInterface pointCardOnion = new SaladCard("", CardType.ONION);
        pointCardOnion.flipCard();

        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardLettuce);

        player1.addCard(pointCardOnion);
        player1.addCard(pointCardOnion);
        player1.addCard(pointCardOnion);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(10, score);
    }

    @Test
    public void completeSetOfVegetables(){
        // Add a criteria card to player1
        CardInterface criteriaCard = new SaladCard("COMPLETE SET = 12", CardType.TOMATO);
        player1.addCard(criteriaCard);

        // Add corresponding point card to player1
        CardInterface pointCardLettuce = new SaladCard("", CardType.LETTUCE);
        pointCardLettuce.flipCard();

        CardInterface pointCardOnion = new SaladCard("", CardType.ONION);
        pointCardOnion.flipCard();

        CardInterface pointCardTomato = new SaladCard("", CardType.TOMATO);
        pointCardTomato.flipCard();

        CardInterface pointCardPepper = new SaladCard("", CardType.PEPPER);
        pointCardPepper.flipCard();

        CardInterface pointCardCarrot = new SaladCard("", CardType.CARROT);
        pointCardCarrot.flipCard();

        CardInterface pointCardCabbage = new SaladCard("", CardType.CABBAGE);
        pointCardCabbage.flipCard();


        player1.addCard(pointCardLettuce);
        player1.addCard(pointCardOnion);
        player1.addCard(pointCardTomato);
        player1.addCard(pointCardPepper);
        player1.addCard(pointCardCarrot);
        player1.addCard(pointCardCabbage);

        int score = scoreCalc.calculateScore(player1.getHand(), player1, players);

        assertEquals(12, score);
    }
}
