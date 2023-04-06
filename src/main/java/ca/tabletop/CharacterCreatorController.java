/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   CharacterCreatorController.java
 *  Description:
 *          This is the character creators controller it houses the functions to be used by the UI.
 *          The character creator controller uses the tabletop database to push and pull data from the database through the main app.
 * 
 */
package ca.tabletop;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CharacterCreatorController 
{
    // FXML Objects (UI elements)
    @FXML TextField characterNameInput; // input for character name
    @FXML TextField classInput; // Input for class
    @FXML TextField levelInput; // Input for level
    @FXML TextField alignmentInput; //Input for alignment
    @FXML TextField xpInput; // Input for xp
    @FXML TextArea bioInput; // Input for bio

    @FXML TextField strengthInput; // input for strength
    @FXML TextField dexterityInput; // input for dexterity
    @FXML TextField constitutionInput; // input for constitution
    @FXML TextField intelligenceInput; // input for intelligence
    @FXML TextField wisdomInput; // input for wisdom
    @FXML TextField charismaInput; // input for charisma
    @FXML TextField hpInput; // input for hp

    @FXML Text errorMessage; // Error message output

    // Boolean for the character being a new character or a update to a an old one
    private boolean newCharacter = true;

    @FXML public void save() throws SQLException, IOException
    {
        if(newCharacter == true){saveNew();}
        else{saveOld();}
    }

    // TODO Function that updates a old characters details in the database
    private void saveOld(){}
    // Function that adds a new character to the database
    private void saveNew() throws SQLException, IOException
    {
        try
        {
            // Create a new character with the data in the character creator
            Character character = new Character(characterNameInput.getText(), classInput.getText(), Integer.parseInt(levelInput.getText()), alignmentInput.getText(), Integer.parseInt(xpInput.getText()), Integer.parseInt(hpInput.getText()),
            bioInput.getText(), Integer.parseInt(strengthInput.getText()), Integer.parseInt(dexterityInput.getText()), Integer.parseInt(constitutionInput.getText()), Integer.parseInt(intelligenceInput.getText()), Integer.parseInt(wisdomInput.getText()), Integer.parseInt(charismaInput.getText()));
            
            // Add the character to the database
            App.setCharacter(character);

            // Move the user back to the main menu of the system
            App.setRoot("mainMenu");
        }   
        catch(Exception ex)
        {
            // Output an error of invalid data if anything wrong happens during the process of adding a new character to the database.
            errorMessage.setText("INVALID DATA");
        }   
    }

    // Returns the application to the game creator
    @FXML
    private void returnGameCreator() throws IOException 
    {
        App.setRoot("gameCreator");
    }
}
