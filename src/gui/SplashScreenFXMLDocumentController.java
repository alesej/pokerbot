//Shows a Welcome message and has a button to launch the game by creating the
//poker room.

package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SplashScreenFXMLDocumentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startButton;

    @FXML
    private Label label;
     
    @FXML
    private AnchorPane splashPane;



    //Creates the poker room screen.
    @FXML
    void launchGame(ActionEvent event) {
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
        splashPane.getScene().getWindow().hide();
    }

    @FXML
    void initialize() {
        assert splashPane != null : "fx:id=\"splashPane\" was not injected: check your FXML file 'SplashScreenFXMLDocument.fxml'.";
        assert startButton != null : "fx:id=\"startButton\" was not injected: check your FXML file 'SplashScreenFXMLDocument.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'SplashScreenFXMLDocument.fxml'.";

    }
}
