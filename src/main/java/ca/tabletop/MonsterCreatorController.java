package ca.tabletop;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MonsterCreatorController 
{
    //FXML Objects
    @FXML TextField monsterNameInput;
    @FXML TextField hpInput;
    @FXML TextArea traitsInput;
    @FXML TextArea bioInput;

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
        Monster monster = new Monster(monsterNameInput.getText(), Integer.parseInt(hpInput.getText()), traitsInput.getText(), bioInput.getText());

        App.setMonster(monster);

        App.setRoot("mainMenu");
    }

    // Returns the application to the game creator
    @FXML
    private void returnGameCreator() throws IOException 
    {
        App.setRoot("gameCreator");
    }
    
}
