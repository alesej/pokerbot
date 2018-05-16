/*
 * Hand class is responsible for getting all combos of the cards.
 */
package pokerbot;

import java.util.ArrayList;

/**
 *
 * @author Jared
 */
public class Hand {

    private ArrayList<Card> cardHand;

    public Hand() {
    }

    public Hand(ArrayList<Card> cardHand) {
        this.cardHand = cardHand;
    }

    
    //Gets all the 5 card combos of submitted cards.
    //Also happens to have the worst complexity of anything I have ever wrote.
    public ArrayList<ArrayList<Card>> getAllCombos(ArrayList<Card> common) {
        ArrayList<ArrayList<Card>> allCombos = new ArrayList<>();
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(cardHand);
        allCards.addAll(common);

        for (int i = 0; i < allCards.size() - 4; i++) {
            for (int j = i + 1; j < allCards.size() - 3; j++) {
                for (int k = j + 1; k < allCards.size() - 2; k++) {
                    for (int l = k + 1; l < allCards.size() - 1; l++) {
                        for (int m = l + 1; m < allCards.size(); m++) {
                            ArrayList<Card> temp = new ArrayList<>();
                            temp.add(allCards.get(i));
                            temp.add(allCards.get(j));
                            temp.add(allCards.get(k));
                            temp.add(allCards.get(l));
                            temp.add(allCards.get(m));
                            allCombos.add(temp);
                        }
                    }

                }
            }
        }
        return allCombos;
    }

    public ArrayList<Card> getCardHand() {
        return cardHand;
    }

    public void setCardHand(ArrayList<Card> cardHand) {
        this.cardHand = cardHand;
    }

}
