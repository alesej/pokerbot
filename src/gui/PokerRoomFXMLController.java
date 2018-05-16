//This class controls the GUI of the main Poker Room.
//Displays the cards for the player, computer, and the common cards.
//Has Buttons for dealing cards and determining the winner.


package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pokerbot.Game;

public class PokerRoomFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pokerPane;

    @FXML
    private Label playerCardsList;

    @FXML
    private Label computerCardsList;

    @FXML
    private Label commonCardsLabel;

    @FXML
    private Button dealHandsButton;

    @FXML
    private Button findWinnerButton;
    private Game testGame = new Game();

    
    //Deals the hands by displaying them to the labels.
    //Also determines the winner but does not display it.
    @FXML
    void dealHands(ActionEvent event) {
        playerCardsList.setText(testGame.returnPlayerHandString());
        computerCardsList.setText(testGame.returnComputerHandString());
        commonCardsLabel.setText(testGame.returnCommonCardsString());
        
        
//        int result = testGame.determineWinner();
//        
//        //Testing
////        switch (result) {
////            case 1:
////                System.out.println("Player");
////                break;
////            case 2:
////                System.out.println("Computer");
////                break;
//            case 3:
//                System.out.println("Tie");
//                break;
//        }
    }

    
    //Shows the winner by launching the end screen.
    @FXML
    void determineWinner(ActionEvent event) {

        Parent root = null;
        FXMLLoader root1;
        root1 = new FXMLLoader(getClass().getResource("EndScreenFXML.fxml"));
        try {
            root = (Parent) root1.load();

        } catch (IOException ex) {
        }
        EndScreenFXMLController controller = root1.<EndScreenFXMLController>getController();

        Scene sc = new Scene(root);
        Stage st = new Stage();
        st.setScene(sc);
        st.show();

        int result = testGame.determineWinner();
        switch (result) {
            case 1:
                controller.setOutcomeText("   The Player! ");
                break;
            case 2:
                controller.setOutcomeText("The Computer!");
                break;
            case 3:
                controller.setOutcomeText(" It's a Tie! ");
                break;
        }

        //controller.startUp();
        pokerPane.getScene().getWindow().hide();

    }

    @FXML
    void initialize() {
        assert playerCardsList != null : "fx:id=\"playerCardsList\" was not injected: check your FXML file 'PokerRoomFXML.fxml'.";
        assert computerCardsList != null : "fx:id=\"computerCardsList\" was not injected: check your FXML file 'PokerRoomFXML.fxml'.";
        assert dealHandsButton != null : "fx:id=\"dealHandsButton\" was not injected: check your FXML file 'PokerRoomFXML.fxml'.";
        assert findWinnerButton != null : "fx:id=\"findWinnerButton\" was not injected: check your FXML file 'PokerRoomFXML.fxml'.";

    }
}
