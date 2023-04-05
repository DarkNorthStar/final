package ca.tabletop;

import java.io.IOException;
import java.sql.SQLException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class GameMasterController 
{
    //****************************************************/
    //                  VARIABLES
    //****************************************************/

    private int numberOfPlayers = 0;
    
    // FXML Objects
    @FXML private ComboBox<String> comboBoxEffects;
    @FXML private ComboBox<String> comboBoxMusic;
    @FXML private ImageView imageViewEnvironment;
    @FXML private ListView<String> listViewPlayers;
    @FXML private Button allowConnectionsButton;
    @FXML private TextField textFieldMessage;
    @FXML private TextArea textAreaDice;
    
    //@FXML private ComboBox<Quest> comboBoxQuests;
    @FXML private TextField textFieldQuestTitle;
    @FXML private TextArea textFieldQuestDescription;
    @FXML private TextField textFieldEvent;

    //****************************************************/
    //                  INITIALIZE
    //****************************************************/
    @FXML
    protected void initialize() throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException
    {
        // Starts the server in App
        App.startGameMaster();

        // Fills the comboBoxes for the music and sound effects
        FileManager.loadAComboBox(comboBoxMusic, App.getMusicFiles());
        FileManager.loadAComboBox(comboBoxEffects, App.getEffectFiles());

        // Set Selections to the first option
        comboBoxMusic.getSelectionModel().select(0); // Set the music to the first music file
        comboBoxEffects.getSelectionModel().select(0); // Sets the effect to the first effect file
        imageViewEnvironment.setImage(SlideShow.getEnvironmentImage(0)); // Sets the slideshow image to the first
    };

    //****************************************************/
    //                  QUESTS AND EVENTS
    //****************************************************/
    @FXML
    public void addQuest() throws IOException, SQLException
    {
        Quest quest = new Quest(textFieldQuestTitle.getText(), textFieldQuestDescription.getText());

        App.addQuest(quest);
        App.sendQuest(quest);

        textFieldQuestTitle.clear();
        textFieldQuestDescription.clear();
    }
    /* @FXML
    public void deleteQuest()
    {
        //Cut
    } */
    @FXML
    public void sendEvent() throws IOException
    {
        String event = textFieldEvent.getText();
        App.sendEvent(event);

        // TODO add event to game log
    }
    //****************************************************/
    //                  MUSIC
    //****************************************************/
    // Changes to the next music file 
    @FXML
    public void musicNext() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        //
        AudioPlayer.next(comboBoxMusic);

        // Send a message to players to change to the next music
        App.sendNextMusic();
    }
    // Changes to the previous music file
    @FXML
    public void musicPrevious() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        AudioPlayer.previous(comboBoxMusic);

        // Send message to players to change to the previous music
        App.sendPreviousMusic();
    }

    // Pauses or plays the music file
    @FXML 
    public void playOrPauseMusic() throws IOException
    {
        AudioPlayer.pausePlayMusic();

        // Send a message to players to toggle music
        App.sendToggleMusic();
    }

    // Changes the music file to the selected file from the ComboBox
    @FXML
    public void changeToNewMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        int index = comboBoxMusic.getSelectionModel().getSelectedIndex();
        AudioPlayer.changeMusicFile(index);
        App.sendToMusic(index);
    }


    //****************************************************/
    //                  EFFECT
    //****************************************************/
    // Play or pauses the effect file
    @FXML
    public void playOrPauseEffect() throws IOException
    {
        AudioPlayer.pausePlayEffect();
        App.sendToggleEffect();
    }

    // Changes the effect file to the selected file in the comboBox
    @FXML
    public void changetoNewEffect() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        int index = comboBoxEffects.getSelectionModel().getSelectedIndex();
        AudioPlayer.changeEffectFile(index);
        App.sendToEffect(index);   
    }

    //****************************************************/
    //                  ENVIRONMENT
    //****************************************************/
    // Changes the environment to the next one
    @FXML 
    public void nextEnvironment() throws IOException
    {
        // Set the environment to the current enviroment
        imageViewEnvironment.setImage(SlideShow.nextEnvironment());
        // Send command to players
        App.sendNextEnvironment();
    }
    // Changes the environment to the previous one
    @FXML 
    public void previousEnvironment() throws IOException
    {
        imageViewEnvironment.setImage(SlideShow.previousEnvironment());
        // Send command to players
        App.sendPreviousEnvironment();
    }

    //****************************************************/
    //                  SERVER FUNCTIONS
    //****************************************************/
    // Accepts a incoming connection
    @FXML
    public void acceptConnection() throws InterruptedException
    {
        // if a new connection was accepted 
        if(App.acceptConnection())
        {
            // add one player to the number of players
            numberOfPlayers++;
            // add a player to the list view
            listViewPlayers.getItems().add("Player" + numberOfPlayers);

            //Select the new player
            listViewPlayers.getSelectionModel().clearAndSelect(numberOfPlayers-1);
        }
    }

    // Sends a message over the server to a player
    @FXML
    public void sendMessageToPlayer() throws IOException
    {
        // Find the selected player
        int player = listViewPlayers.getSelectionModel().getSelectedIndex();

        // Get the message to send
        String message = textFieldMessage.getText();

        //Send the message
        App.sendMessageToPlayer(player, message);

        // Clear textFieldMessagd
        textFieldMessage.setText("");
    }

    //****************************************************/
    //                  DICE FUNCTIONS
    //****************************************************/
    // Functions that roll a dice and return the results
    @FXML public void rollpercentile(){textAreaDice.setText(Integer.toString(Dice.rollpercentile()));}
    @FXML public void rollD4(){textAreaDice.setText(Integer.toString(Dice.rollD4()));}
    @FXML public void rollD6(){textAreaDice.setText(Integer.toString(Dice.rollD6()));}
    @FXML public void rollD8(){textAreaDice.setText(Integer.toString(Dice.rollD8()));}
    @FXML public void rollD10(){textAreaDice.setText(Integer.toString(Dice.rollD10()));}
    @FXML public void rollD12(){textAreaDice.setText(Integer.toString(Dice.rollD12()));}
    @FXML public void rollD20(){textAreaDice.setText(Integer.toString(Dice.rollD20()));}
}
