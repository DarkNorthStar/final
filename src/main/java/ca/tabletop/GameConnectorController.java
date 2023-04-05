package ca.tabletop;
//****************************************************/
//                  IMPORTS
//****************************************************/
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GameConnectorController 
{
    //****************************************************/
    //                  FXML OBJECTS
    //****************************************************/
    @FXML private TextField textFieldIp; // The textField for entering a local ip
    @FXML private Text textErrorMessage; // The text for outputing errors

    //****************************************************/
    //                  PUBLIC FUNCTIONS
    //****************************************************/
    // Attempts to connect to a server
    @FXML public void connectToServer() throws IOException
    {   
        /* // Trys to connect to a game masters server using the typed in local ip
        boolean connected = App.connectToServer(textFieldIp.getText().strip());

        // Check if the connection was sucessfull
        if(connected == true)
        {
            // If sucessful load the game player
            App.setRoot("gamePlayer");
        }
        else
        {
            // If not output error message to the screen
            textErrorMessage.setText("FAILED TO CONNECT"); 
        } */

        App.setRoot("gamePlayer"); // TODO uncomment AND comment the rest of connectToServer(). This is used to access the game player without a connection.
    }
}
