
//This class controls the end screen of the GUI.
//Responsible for restarting or exiting the game.


package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EndScreenFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    
    @FXML
    private AnchorPane endPane;

    @FXML
    private Label outcomeText;

    
    //Sets the text to whoever won.
    void setOutcomeText(String text){
        outcomeText.setText(text);
    }
    
    
    //Exit button quits the game.
    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    
    //Play Again function will restart the game by recreating the poker room.
    @FXML
    void playAgain(ActionEvent event) {
        Parent root = null;
        FXMLLoader root1;
        root1 = new FXMLLoader(getClass().getResource("PokerRoomFXML.fxml"));
        try {
            root = (Parent)root1.load();
            
        } catch (IOException ex) {
        }
        PokerRoomFXMLController controller = root1.<PokerRoomFXMLController>getController();
        
        Scene sc = new Scene(root);
        Stage st = new Stage();
        st.setScene(sc);
        st.show();
        //controller.startUp();
        endPane.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        assert outcomeText != null : "fx:id=\"outcomeText\" was not injected: check your FXML file 'EndScreenFXML.fxml'.";

    }
}
