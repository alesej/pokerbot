


/*
 * This Class represents the cards, with a variable for the value and another 
 * for the suit.
 */
package pokerbot;

public class Card {

    private int value;
    private int suit;

    public Card() {
    }

    public Card(int value, int suite) {
        this.value = value;
        this.suit = suite;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    
    //Custom toString Method to represent the cards as a string for the GUI.
    @Override
    public String toString() {
        String valueString = String.valueOf(value + 1);
        String suitString = String.valueOf(suit);
        switch (value) {
            case 13:
                valueString = "Ace";
                break;
            case 12:
                valueString = "King";
                break;
            case 11:
                valueString = "Queen";
                break;
            case 10:
                valueString = "Jack";
                break;
            default:
                break;
        }
        switch (suit) {
            case 1:
                suitString = "Spades";
                break;
            case 2:
                suitString = "Clubs";
                break;
            case 3:
                suitString = "Hearts";
                break;
            case 4:
                suitString = "Diamonds";
                break;
            default:
                break;
        }

        return valueString + " of " + suitString;
    }

}
