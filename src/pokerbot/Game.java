/*
 * This is a pretty big class and runs the game logic of poker.
 */
package pokerbot;

import java.util.ArrayList;

/**
 *
 * @author Jared
 */
public class Game {

    private final Deck gameDeck;
    private final Hand playerHand;
    private final Hand computerHand;
    private final Hand commonHand;

    //The constructor intiliazes the objects and deals the hands to the player,
    //computer, and common pools.
    public Game() {

        gameDeck = new Deck();
        playerHand = new Hand(gameDeck.dealXCards(2));
        computerHand = new Hand(gameDeck.dealXCards(2));
        commonHand = new Hand(gameDeck.dealXCards(5));

    }

    
    //This function checks every 5 card combo of the 7 cards to find the one
    //with the highest rank.
    public HandEvaluator findBestHand(ArrayList<ArrayList<Card>> combos) {

        HandEvaluator checker = new HandEvaluator();
        int rank = 0;
        int location = 0;
        for (int i = 0; i < combos.size(); i++) {
            checker.findHandRank(combos.get(i));
            if (checker.getHandRank() > rank){
                rank = checker.getHandRank();
                location = i;
            }
        }
        
        
        //Testing
          checker.findHandRank(combos.get(location));
//        System.out.println("Rank: ");
//        System.out.println(checker.getHandRank()+ "\n");
//        System.out.println("Hand: ");
//        System.out.println(combos.get(location).get(0).getValue());
//        System.out.println(combos.get(location).get(1).getValue());
//        System.out.println(combos.get(location).get(2).getValue());
//        System.out.println(combos.get(location).get(3).getValue());
//        System.out.println(combos.get(location).get(4).getValue() + "\n");
        return checker;
    }

    //Find who won the hand, calls the break tie function if needed.
    public int determineWinner() {
        ArrayList<ArrayList<Card>> playerCombos = playerHand.getAllCombos(commonHand.getCardHand());
        ArrayList<ArrayList<Card>> computerCombos = computerHand.getAllCombos(commonHand.getCardHand());
        HandEvaluator player = findBestHand(playerCombos);
        HandEvaluator computer = findBestHand(computerCombos);
        if (player.getHandRank() > computer.getHandRank()) {
            return 1;
        } else if (player.getHandRank() < computer.getHandRank()) {
            return 2;
        } else {
            System.out.println("Breaking Tie");
            return breakTie(player, computer);
        }
    }

    //Function breaks ties between hands by comparing the tie breaker value kickers.
    public int breakTie(HandEvaluator player, HandEvaluator computer) {
        //Testing
//        System.out.println("Player Tie Breakers: ");
//        System.out.println("Breaker1: " + player.getTieBreak());
//        System.out.println("Breaker2: " + player.getSecondTieBreak());
//        System.out.println("Breaker3: " + player.getThirdTieBreak());
//        
//        System.out.println("\nComputer Tie Breakers: ");
//        System.out.println("Breaker1: " + computer.getTieBreak());
//        System.out.println("Breaker2: " + computer.getSecondTieBreak());
//        System.out.println("Breaker3: " + computer.getThirdTieBreak());
        
        if (player.getTieBreak() > computer.getTieBreak()) {
            return 1;
        } else if (player.getTieBreak() < computer.getTieBreak()) {
            return 2;
        } else {
            if (player.getSecondTieBreak() > computer.getSecondTieBreak()) {
                return 1;
            } else if (player.getSecondTieBreak() < computer.getSecondTieBreak()) {
                return 2;
            } else {
                if (player.getThirdTieBreak() > computer.getThirdTieBreak()) {
                    return 1;
                } else if (player.getThirdTieBreak() < computer.getThirdTieBreak()) {
                    return 2;
                } else {
                    return 3;
                }
            }
        }
    }

    
    //Following few functions return the strings of the hands.
    public String returnPlayerHandString() {
        return playerHand.getCardHand().get(0).toString() + "\n" + playerHand.getCardHand().get(1).toString();
    }

    public String returnComputerHandString() {
        return computerHand.getCardHand().get(0).toString() + "\n" + computerHand.getCardHand().get(1).toString();
    }

    public String returnCommonCardsString() {
        return commonHand.getCardHand().get(0).toString() + "\n" + commonHand.getCardHand().get(1).toString() + "\n"
                + commonHand.getCardHand().get(2).toString() + "\n" + commonHand.getCardHand().get(3).toString() + "\n"
                + commonHand.getCardHand().get(4).toString();
    }

    public Hand getCommonHand() {
        return commonHand;
    }

    public Hand getComputerHand() {
        return computerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

}
