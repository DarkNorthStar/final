package ca.tabletop;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NPCCreatorController 
{
    // FXML Objects
    @FXML TextField characterNameInput;
    @FXML TextField classInput;
    @FXML TextField levelInput;
    @FXML TextField alignmentInput;
    @FXML TextField xpInput;
    @FXML TextArea bioInput;

    @FXML TextField strengthInput;
    @FXML TextField dexterityInput;
    @FXML TextField constitutionInput;
    @FXML TextField intelligenceInput;
    @FXML TextField wisdomInput;
    @FXML TextField charismaInput;
    @FXML TextField hpInput;

    private boolean newCharacter = true;

    @FXML
    protected void initialize()
    {
        
    }

    @FXML public void save() throws SQLException, IOException
    {
        if(newCharacter == true){saveNew();}
        else{saveOld();}
    }

    private void saveOld()
    {

    }
    private void saveNew() throws SQLException, IOException
    {
        Character character = new Character(characterNameInput.getText(), classInput.getText(), Integer.parseInt(levelInput.getText()), alignmentInput.getText(), Integer.parseInt(xpInput.getText()), Integer.parseInt(hpInput.getText()),
        bioInput.getText(), Integer.parseInt(strengthInput.getText()), Integer.parseInt(dexterityInput.getText()), Integer.parseInt(constitutionInput.getText()), Integer.parseInt(intelligenceInput.getText()), Integer.parseInt(wisdomInput.getText()), Integer.parseInt(charismaInput.getText()));

        App.setNPC(character);

        App.setRoot("mainMenu");
    }

    // Returns the application to the game creator
    @FXML
    private void returnGameCreator() throws IOException 
    {
        App.setRoot("gameCreator");
    }
}
