package src.service;

import java.util.ArrayList;

import src.card.CardInterface;
import src.card.CardType;

public class Counter {
    
    public int countVegetables(ArrayList<CardInterface> hand, CardType vegetable) {
        int count = 0;
        for (CardInterface card : hand) {
            if (card.getBackside().equals(vegetable)) {
                count++;
            }
        }
        return count;
    }
}
