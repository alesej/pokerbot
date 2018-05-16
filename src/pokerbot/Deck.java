/*
 * Deck Class has an arraylist of all cards in a standard deck. Its responsible for
 * dealing cards.
 */
package pokerbot;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jared
 */
public class Deck {
    
    private ArrayList<Card> fullDeck;

    //The constructor uses a for loop to initialize all the cards and the deck.
    //Also shuffles the deck.
    public Deck() {
        fullDeck = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < 52; i++) {
            if (i % 13 == 0) {
                j++;
            }
            
            Card temp = new Card(i % 13 + 1, j);
            fullDeck.add(temp);
        }
        shuffleDeck();
    }
    
    //Will return a number of cards depending on the input.
    public ArrayList<Card> dealXCards(int x) {
        ArrayList<Card> dealt = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            dealt.add(fullDeck.remove(fullDeck.size() - 1 - i));
        }
        return dealt;
    }
    
    public Deck(ArrayList<Card> fullDeck) {
        this.fullDeck = fullDeck;
    }
    
    public void shuffleDeck() {
        Collections.shuffle(fullDeck);
    }
    
    public ArrayList<Card> getFullDeck() {
        return fullDeck;
    }
    
    public void setFullDeck(ArrayList<Card> fullDeck) {
        this.fullDeck = fullDeck;
    }
    
}
