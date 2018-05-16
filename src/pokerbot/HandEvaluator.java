/*
 * This class contains a ton of functions to identify hands and set tie breakers.
 */
package pokerbot;

import java.util.ArrayList;

/**
 *
 * @author Jared
 */
public class HandEvaluator {

    private int handRank;
    private int tieBreak;
    private int secondTieBreak;
    private int thirdTieBreak;
    private ArrayList<Card> currentHand;

    //Initializes the tie breakers to -1, so a 2 which is represented 
    //as a 0 will still win over nothing.
    public HandEvaluator() {
        tieBreak = -1;
        secondTieBreak = -1;
        thirdTieBreak = -1;
    }

    public HandEvaluator(ArrayList<Card> currentHand) {
        tieBreak = -1;
        secondTieBreak = -1;
        thirdTieBreak = -1;
        this.currentHand = currentHand;
    }

    public void setCurrentHand(ArrayList<Card> currentHand) {
        this.currentHand = currentHand;
    }

    public int getHandRank() {
        return handRank;
    }

    //Calls all the hand checking functions and uses a big switch statement to 
    //assign the hand rank.
    public void findHandRank(ArrayList<Card> tempHand) {
        currentHand = tempHand;
        if (checkRoyalFlush()) {
            handRank = 10;
        } else if (checkStraightFlush()) {
            handRank = 9;
        } else if (checkFourofaKind()) {
            handRank = 8;
        } else if (checkFullHouse()) {
            handRank = 7;
        } else if (checkFlush()) {
            handRank = 6;
        } else if (checkStraight()) {
            handRank = 5;
        } else if (checkThreeofaKind()) {
            handRank = 4;
        } else if (checkTwoPair()) {
            handRank = 3;
        } else if (checkOnePair()) {
            handRank = 2;
        } else {
            handRank = 1;
            tieBreak = getXHighestCard(1);
            secondTieBreak = getXHighestCard(2);
            thirdTieBreak = getXHighestCard(3);
        }
        
        
    }

    
    //Checks royal flush by checking card values and calling checkFlush().
    //Parses to an array which makes checking straights easier.
    public boolean checkRoyalFlush() {
        int[] cards = new int[13];
        for (Card temp : currentHand) {
            cards[temp.getValue() - 1]++;
        }
        if (cards[8] == 1
                && cards[9] == 1
                && cards[10] == 1
                && cards[11] == 1
                && cards[12] == 1) {

            return checkFlush();

        }

        return false;
    }

    //Checks straight and flush.
    public boolean checkStraightFlush() {
        return checkFlush() && checkStraight();
    }

    //Checks four of a kind by incrementing a variable if two card values are the same.
    //Also temporarily removes cards to find tie breakers.
    public boolean checkFourofaKind() {
        int cardRepeats;
        for (int i = 0; i < currentHand.size(); i++) {
            cardRepeats = 1;
            for (int k = i + 1; k < currentHand.size(); k++) {
                if (currentHand.get(i).getValue() == currentHand.get(k).getValue()) {
                    cardRepeats++;
                    if (cardRepeats == 4) {
                        tieBreak = currentHand.get(i).getValue();
                        Card triplet1 = currentHand.remove(k);
                        Card triplet2 = currentHand.remove(k - 1);
                        Card triplet4 = currentHand.remove(k - 2);
                        Card triplet3 = currentHand.remove(i);
                        
                        secondTieBreak = getXHighestCard(1);
                        currentHand.add(triplet4);
                        currentHand.add(triplet3);
                        currentHand.add(triplet2);
                        currentHand.add(triplet1);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //Checks for a three of a kind, and then calls the check one pair function
    //with a reduced hand.
    public boolean checkFullHouse() {
        int cardRepeats;
        for (int i = 0; i < currentHand.size(); i++) {
            cardRepeats = 1;
            for (int k = i + 1; k < currentHand.size(); k++) {
                if (currentHand.get(i).getValue() == currentHand.get(k).getValue()) {
                    cardRepeats++;
                    if (cardRepeats == 3) {
                        tieBreak = currentHand.get(i).getValue();
                        Card triplet1 = currentHand.remove(k);
                        Card triplet2 = currentHand.remove(k - 1);
                        Card triplet3 = currentHand.remove(i);
                        boolean onePair = checkOnePair();

                        currentHand.add(triplet3);
                        currentHand.add(triplet2);
                        currentHand.add(triplet1);

                        return onePair;
                    }
                }
            }
        }

        return false;
    }

    //Pretty simple and similar to other functions.
    //Just checks to see if all the suits are the same.
    public boolean checkFlush() {
        int suitRepeats;
        int highCard;
        for (int i = 0; i < currentHand.size(); i++) {
            suitRepeats = 1;
            highCard = currentHand.get(i).getValue();
            for (int k = i + 1; k < currentHand.size(); k++) {
                if (currentHand.get(i).getSuit() == currentHand.get(k).getSuit()) {
                    if (currentHand.get(i).getValue() > highCard) {
                        highCard = currentHand.get(i).getValue();
                    }
                    suitRepeats++;
                    if (suitRepeats == 5) {
                        tieBreak = highCard;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //Checks for a straight using an array.
    public boolean checkStraight() {
        int[] cards = new int[13];
        for (Card temp : currentHand) {
            cards[temp.getValue() - 1]++;
        }
        for (int i = 0; i <= 8; i++) {
            if (cards[i] == 1
                    && cards[i + 1] == 1
                    && cards[i + 2] == 1
                    && cards[i + 3] == 1
                    && cards[i + 4] == 1) {
                tieBreak = i;
                return true;

            }
        }

        return false;
    }

    //Checks for three of a kind the same way as four of a kind, full house, and pair.
    public boolean checkThreeofaKind() {
        int cardRepeats;
        for (int i = 0; i < currentHand.size(); i++) {
            cardRepeats = 1;
            for (int k = i + 1; k < currentHand.size(); k++) {
                if (currentHand.get(i).getValue() == currentHand.get(k).getValue()) {
                    cardRepeats++;
                    if (cardRepeats == 3) {
                        tieBreak = currentHand.get(i).getValue();
                        Card triplet1 = currentHand.remove(k);
                        Card triplet2 = currentHand.remove(k - 1);
                        Card triplet3 = currentHand.remove(i);

                        secondTieBreak = getXHighestCard(1);
                        thirdTieBreak = getXHighestCard(2);
                        currentHand.add(triplet3);
                        currentHand.add(triplet2);
                        currentHand.add(triplet1);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //Finds one pair and then calls the function to check if there is another pair.
    public boolean checkTwoPair() {
        int cardRepeats;
        for (int i = 0; i < currentHand.size(); i++) {
            cardRepeats = 1;
            for (int k = i + 1; k < currentHand.size(); k++) {
                if (currentHand.get(i).getValue() == currentHand.get(k).getValue()) {
                    cardRepeats++;
                    if (cardRepeats == 2) {
                        tieBreak = currentHand.get(i).getValue();
                        Card triplet1 = currentHand.remove(k);
                        Card triplet2 = currentHand.remove(i);
                        boolean onePair = checkOnePair();
                        currentHand.add(triplet2);
                        currentHand.add(triplet1);

                        return onePair;
                    }
                }
            }
        }

        return false;
    }

    //Works in a loop to find cards with the same value.
    public boolean checkOnePair() {
        int cardRepeats;
        for (int i = 0; i < currentHand.size(); i++) {
            cardRepeats = 1;
            for (int k = i + 1; k < currentHand.size(); k++) {
                if (currentHand.get(i).getValue() == currentHand.get(k).getValue()) {
                    cardRepeats++;
                    if (cardRepeats == 2) {
                        if (currentHand.size() < 5) {
                            secondTieBreak = currentHand.get(i).getValue();
                            return true;
                        }
                        tieBreak = currentHand.get(i).getValue();
                        Card triplet1 = currentHand.remove(k);
                        Card triplet2 = currentHand.remove(i);
                        secondTieBreak = getXHighestCard(1);
                        thirdTieBreak = getXHighestCard(2);
                        currentHand.add(triplet2);
                        currentHand.add(triplet1);
                        
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //Odd function to return the value of the highest card in the hand.
    //takes an integer to see if the first, second, or third highest card is needed.
    //This function is need to break a lot of ties.
    public int getXHighestCard(int x) {
        int highest = -1;
        int secondHighest = -1;
        int thirdHighest = -1;
        for (int i = 0; i < currentHand.size(); i++) {
            if (currentHand.get(i).getValue() > highest) {
                if (highest > secondHighest) {
                    if (secondHighest > thirdHighest) {
                        thirdHighest = secondHighest;
                    }
                    secondHighest = highest;
                }
                highest = currentHand.get(i).getValue();
            }
        }

        switch (x) {
            case 1:
                return highest;
            case 2:
                return secondHighest;
            case 3:
                return thirdHighest;
            default:
                return highest;
        }

    }


    public int getSecondTieBreak() {
        return secondTieBreak;
    }

    public int getThirdTieBreak() {
        return thirdTieBreak;
    }

    public int getTieBreak() {
        return tieBreak;
    }
    
    
    
}
