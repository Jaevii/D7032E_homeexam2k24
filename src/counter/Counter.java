package src.counter;

import java.util.ArrayList;

import src.card.CardInterface;
import src.card.CardType;

public class Counter {
    
    // Count the number of a specific vegetable in the hand
    public int countVegetable(ArrayList<CardInterface> hand, CardType vegetable) {
        int count = 0;
        for (CardInterface card : hand) {
            if (!card.frontsideUp()) {
                if (card.getBackside().equals(vegetable)) {
                    count++;
                }
            }   
        }
        return count;
    }

    // Count the total number of vegetables in the hand
    public int countTotalVegetables(ArrayList<CardInterface> hand) {
        int count = 0;
        for (CardInterface card : hand) {
            if (!card.frontsideUp()) {
                count++;
            }
        }
        return count;
    }
}
