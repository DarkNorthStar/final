/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   GameConnectorController.java
 *  Description:
 *          This is the game connector controller. The game connector is a small section between starting the game player
 *          and connecting to the game master. The game connector is used for inputing the local ip of the game master to connect to.
 *          Simple holds a function for connecting that returns with an error if it could not connect
 * 
 */
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
        // Trys to connect to a game masters server using the typed in local ip
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
        }
    }
    // Function for kyle (or others to skip the connection process and open the game player)
    public void skipConnection() throws IOException
    {
        App.setRoot("gamePlayer");
    }
}
