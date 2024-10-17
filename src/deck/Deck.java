package src.deck;

import java.util.Collections;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import src.card.CardInterface;
import src.card.CardType;
import src.card.SaladCard;
import src.pile.Pile;
import src.pile.PileInterface;

public class Deck implements DeckInterface {

    private static final int NUMBER_OF_PILES = 3;

    public Deck() {}

    @Override
    public void shuffle(ArrayList<CardInterface> deck) {
        Collections.shuffle(deck);
    }

    @Override
    public ArrayList<PileInterface> createPiles(int numberOfPlayers) {

        ArrayList<PileInterface> piles = new ArrayList<>();

        ArrayList<CardInterface> deckPepper = new ArrayList<>();
        ArrayList<CardInterface> deckLettuce = new ArrayList<>();
        ArrayList<CardInterface> deckCarrot = new ArrayList<>();
        ArrayList<CardInterface> deckCabbage = new ArrayList<>();
        ArrayList<CardInterface> deckOnion = new ArrayList<>();
        ArrayList<CardInterface> deckTomato = new ArrayList<>();

        try (InputStream fInputStream = new FileInputStream("PointSaladManifest.json");
                Scanner scanner = new Scanner(fInputStream, "UTF-8").useDelimiter("\\A")) {

            // Read the entire JSON file into a String
            String jsonString = scanner.hasNext() ? scanner.next() : "";

            // Parse the JSON string into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Get the "cards" array from the JSONObject
            JSONArray cardsArray = jsonObject.getJSONArray("cards");

            // Iterate over each card in the array
            for (int i = 0; i < cardsArray.length(); i++) {
                JSONObject cardJson = cardsArray.getJSONObject(i);

                // Get the criteria object from the card JSON
                JSONObject criteriaObj = cardJson.getJSONObject("criteria");

                // Add each vegetable card to the deck with its corresponding criteria
                deckPepper.add(new SaladCard(criteriaObj.getString("PEPPER"), CardType.PEPPER));
                deckLettuce.add(new SaladCard(criteriaObj.getString("LETTUCE"), CardType.LETTUCE));
                deckCarrot.add(new SaladCard(criteriaObj.getString("CARROT"), CardType.CARROT));
                deckCabbage.add(new SaladCard(criteriaObj.getString("CABBAGE"), CardType.CABBAGE));
                deckOnion.add(new SaladCard(criteriaObj.getString("ONION"), CardType.ONION));
                deckTomato.add(new SaladCard(criteriaObj.getString("TOMATO"), CardType.TOMATO));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Shuffle each deck
        shuffle(deckPepper);
        shuffle(deckLettuce);
        shuffle(deckCarrot);
        shuffle(deckCabbage);
        shuffle(deckOnion);
        shuffle(deckTomato);

        int cardsPerVeggie = 3 * numberOfPlayers;

        ArrayList<CardInterface> deck = new ArrayList<>();
        for (int i = 0; i < cardsPerVeggie; i++) {
            deck.add(deckPepper.remove(0));
            deck.add(deckLettuce.remove(0));
            deck.add(deckCarrot.remove(0));
            deck.add(deckCabbage.remove(0));
            deck.add(deckOnion.remove(0));
            deck.add(deckTomato.remove(0));
        }
        shuffle(deck);

        // divide the deck into 3 piles
        ArrayList<CardInterface> pile1 = new ArrayList<>();
        ArrayList<CardInterface> pile2 = new ArrayList<>();
        ArrayList<CardInterface> pile3 = new ArrayList<>();

        for (int i = 0; i < deck.size(); i++) {
            if (i % NUMBER_OF_PILES == 0) {
                pile1.add(deck.get(i));
            } else if (i % NUMBER_OF_PILES == 1) {
                pile2.add(deck.get(i));
            } else {
                pile3.add(deck.get(i));
            }
        }

        piles.add(new Pile(pile1));
        piles.add(new Pile(pile2));
        piles.add(new Pile(pile3));

        return piles;
    }
    
}
