/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   GameCreatorController.java
 *  Description:
 *          This is the game creator controller. the game creator is the place for adding files to the system.
 *          The game creator is the hub for collecting the files and other parts of the system you want to add.
 *          It houses functions for adding the diffrent files to the system (effect, image, music) and can open the other creators for additions to the database.
 * 
 */
package ca.tabletop;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class GameCreatorController 
{
    //****************************************************/
    //                  FXML OBJECTS
    //****************************************************/
    @FXML private ListView<String> listViewMusic; // ListView for the loaded music files
    @FXML private ListView<String> listViewImages; // ListView for the loaded image files
    @FXML private ListView<String> listViewEffects; // ListView for the loaded effect files

    //****************************************************/
    //                  INITIALIZE
    //****************************************************/
    // Loads the listviews with the files in the system
    @FXML
    protected void initialize()
    {
        // Load effect listview
        FileManager.loadAListView(App.getEffectFiles(), listViewEffects);

        // Load image listview
        FileManager.loadAListView(App.getImageFiles(), listViewImages);

        // Load music listview
        FileManager.loadAListView(App.getMusicFiles(), listViewMusic);
    }
    //****************************************************/
    //                  ADD BUTTON FUNCTIONS
    //****************************************************/
    // Function called when the add music button is clicked
    // Adds a music file to the system and the listview
    public void addMusic() throws IOException
    {
        listViewMusic.getItems().add(App.addMusic().getName());
    }
    // Function clled when the add image button is clicked
    // Adds a image file to the system and the listview
    public void addImage() throws IOException
    {  
        listViewImages.getItems().add(App.addImage().getName());    
    }
    // Function called when the add effect button is clicked
    // Adds a effect file to the system and the listview
    public void addEffect() throws IOException
    {
        listViewEffects.getItems().add(App.addEffect().getName());    
    }

    //****************************************************/
    //                  MENU NAV FUNCTIONS
    //****************************************************/
    // Returns the application to the main menu
    @FXML
    private void returnMainMenu() throws IOException 
    {
        App.setRoot("mainMenu");
    }
    // Opens the character creator
    @FXML
    private void openCharacterCreator() throws IOException 
    {
        App.setRoot("characterCreator");
    }
    @FXML
    private void openNPCreator() throws IOException
    {
        App.setRoot("npcCreator");
    }
    @FXML private void openMonsterCreator() throws IOException
    {
        App.setRoot("monsterCreator");
    }
}