package ca.tabletop;

//****************************************************/
//                  IMPORTS
//****************************************************/
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class CommandReader extends Thread
{

    //****************************************************/
    //                  VARIABLES
    //****************************************************/
    private boolean running = true; // Variable for continueing to listen for commands
    GamePlayerController controller; // The controller to the game player
    
    //****************************************************/
    //                  PUBLIC FUNCTIONS
    //****************************************************/
    // Contructor
    public CommandReader(){}
   

    // Starts the commandReader thread
    @Override
    public void run()
    {
        // Loop reading input until the game stops running
        while(running == true)
        {
            // Read a message if any
            String message = null;
            try {
                message = Client.returnMessage().strip();
            } catch (IOException e) {
                //Error message
                //System.out.println("FAILED TO GET THE MESSAGE FROM THE CLIENT");
            }

            // If the message is not null
            if(message != null)
            {
                // Check if the message starts with '?'
                if(message.charAt(0) == '?')
                {
                    // If the message starts with '?'
                    // Read it as a command
                    try 
                    {
                        readCommand(message);
                    }catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        //Error message
                        System.out.println("FAILED TO READ THE COMMAND");
                    }
                }
                else
                {
                    System.out.println("MESSAGE");
                    // Read it as a message and pass it to the players message box
                    controller.setTextAreaMessages(message);
                }
            }
        }
    }

    // Toggles searching for commands
    public void toggleRunning()
    {
        // Checks if still running
        if(running == true)
        {
            // if running stop
            running = false;
        }
        else
        {
            // Otherwise start
            running = true;
        }
    }
    
    //****************************************************/
    //                  PRIVATE FUNCTIONS
    //****************************************************/
    // Reads and interprets a command
    private void readCommand(String message) throws IOException, UnsupportedAudioFileException, LineUnavailableException 
    {

        // Checks what the command is and completes it
        if(message.equals(App.getMusicNextCommand()))
        {
            // Move to the next music file
            AudioPlayer.next();
        }
        else if(message.equals(App.getMusicPreviousCommand()))
        {
            // Moves to the previous music file
            AudioPlayer.previous();
        }
        else if(message.equals(App.getMusicToggleCommand()))
        {
            // Toggles effect playing
            AudioPlayer.pausePlayEffect();
        }
        else if(message.equals(App.getMusicChangeCommand()))
        {
            // Changes to a specific music file TODO needs the file number to add
            //AudioPlayer.changeMusicFile();
        }
        else if(message.equals(App.getEffectToggleCommand()))
        {
            // Toggles effect playing
            AudioPlayer.pausePlayEffect();
        }
        else if(message.equals(App.getEffectChangeCommand()))
        {
            // Changes to a specific effect file TODO Needs the file number to add
            //AudioPlayer.changeEffectFile();
        }
        else if(message.equals(App.getEnvNextCommand()))
        {
            // Moves to the next environment file
            SlideShow.nextEnvironment();
        }
        else if(message.equals(App.getEnvPreviousCommand()))
        {
            // Moves to the next environment file
            SlideShow.previousEnvironment();
        }
    }
    
    
}
