/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   MainMenuController.java
 *  Description:
 *             This is the main menu controller is the staring spot for any user of the system.
 *             From here the user can launch the game player or game master or go into the game creator to set thing up
 */
package ca.tabletop;
//****************************************************/
//                  IMPORTS
//****************************************************/
import java.io.IOException;
import javafx.fxml.FXML;

public class MainMenuController {

    //startGameCreator
    @FXML
    private void startGameCreator() throws IOException {
        App.setRoot("gameCreator");
    }

    //startGameMaster
    @FXML
    private void startGameMaster() throws IOException {
        App.setRoot("gameMaster");
    }

    //startGamePlayer
    @FXML
    private void startGamePlayer() throws IOException {
        App.setRoot("gamePlayer");
    }

    //startGameConnector
    @FXML
    private void startGameConnector() throws IOException
    {
        App.setRoot("gameConnector");
    }
}
