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
    @FXML private TextField textFieldIp;
    @FXML private Text textErrorMessage;

    //****************************************************/
    //                  PUBLIC FUNCTIONS
    //****************************************************/
    // Attempts to connect to a server
    @FXML
    public void connectToServer() throws IOException
    {   
        /* // Trys to connect to a server
        boolean connected = App.connectToServer(textFieldIp.getText().strip());
        // Check if the connection was sucessfull
        if(connected == true)
        {
            // If sucessful load gamePlayer
            App.setRoot("gamePlayer");
        }
        else
        {
            // If not output error message
            textErrorMessage.setText("FAILED TO CONNECT"); 
        } */

        App.setRoot("gamePlayer"); // TODO UNCOMMENT AND DELETE
    }
    
}
