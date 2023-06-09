package ca.tabletop;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GamePlayerController 
{
    //****************************************************/
    //                  FXML OBJECTS
    //****************************************************/
    @FXML private ImageView imageViewEnvironment;
    @FXML private TextArea textAreaMessages;
    @FXML private TextArea textAreaDice;

    //****************************************************/
    //                  INITIALIZE
    //****************************************************/
    @FXML
    protected void initialize() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        App.startGamePlayer(this);

        // Set the environment image to the current image
        imageViewEnvironment.setImage(SlideShow.getEnvironmentImage(0));
    }

    // Sets the image view image for use in the command reader
    public void setImageViewEnvironment(Image imageToSet)
    {
        imageViewEnvironment.setImage(imageToSet);
    }

    // Sets the message
    public void setTextAreaMessages(String message) 
    {
        textAreaMessages.setText(message);
    }

    //****************************************************/
    //                  UTILITY
    //****************************************************/
    // Mutes the audioPlayer
    @FXML
    public void mute()
    {
        //TODO ADD MUTE
    }

    //****************************************************/
    //                  DICE
    //****************************************************/
    @FXML public void rollpercentile(){textAreaDice.setText(Integer.toString(Dice.rollpercentile()));}
    @FXML public void rollD4(){textAreaDice.setText(Integer.toString(Dice.rollD4()));}
    @FXML public void rollD6(){textAreaDice.setText(Integer.toString(Dice.rollD6()));}
    @FXML public void rollD8(){textAreaDice.setText(Integer.toString(Dice.rollD8()));}
    @FXML public void rollD10(){textAreaDice.setText(Integer.toString(Dice.rollD10()));}
    @FXML public void rollD12(){textAreaDice.setText(Integer.toString(Dice.rollD12()));}
    @FXML public void rollD20(){textAreaDice.setText(Integer.toString(Dice.rollD20()));}
}
