/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   CommandReader.java
 *  Description:
 *              This is the command reader.
 *              The command reader is used by the game player to read commands sent over from the game master.
 *              The command reader runs in its own thread so that it doesnt stop the rest of the application while looking for commands.
 *              When the command reader reads a message from the server it interprets it as a message, command, quest, or event
 *              and triggers functions of the game player based on that.
 * 
 */
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
    public CommandReader(GamePlayerController gamePlayerController){ controller = gamePlayerController;}
   

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
                else if(message.charAt(0) == 'Q' && message.charAt(1) == '/')
                {
                    controller.setTextAreaQuests(message);
                }
                else if(message.charAt(0) == 'E' && message.charAt(1) == '/')
                {
                    controller.addTotextAreaEvents(message);
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
        int index = 0; // Int for the index found at the end of a change command
        String indexString = ""; // String for getting the index out of the message
        // Checks for indexes in the command
        if(message.length() > App.getEffectChangeCommand().length())
        {
            try
            {
                // Start at the first possable numeric index
                // Collect all digits between here and the end of the string
                for(int i = 14; i < message.length(); i++)
                {
                    // Add to the index string
                    indexString = indexString + message.charAt(i);
                }
                // Try parseing to a int
                index = Integer.parseInt(indexString);
                // Set message without the index
                message = App.getEffectChangeCommand();
            }
            catch(NumberFormatException ex)
            {
                System.out.println("Not a number");
            }
        }
        else if(message.length() > App.getMusicChangeCommand().length())
        {
            try
            {
                // Start at the first possable numeric index
                // Collect all digits between here and the end of the string
                for(int i = 13; i < message.length(); i++)
                {
                    // Add to the index string
                    indexString = indexString + message.charAt(i);
                }
                // Try parseing to a int
                index = Integer.parseInt(indexString);
                // Set message without the index
                message = App.getMusicChangeCommand();
            }
            catch(NumberFormatException ex)
            {
                // Error Message to console
                System.out.println("Not a number");
            }
        }

        // Checks what the command is and completes it
        if(message.equals(App.getMusicNextCommand()))
        {
            // Move to the next music file
            AudioPlayer.next();
            System.out.println("Command: Next Music");
        }
        else if(message.equals(App.getMusicPreviousCommand()))
        {
            // Moves to the previous music file
            AudioPlayer.previous();
            System.out.println("Command: Previous Music");
        }
        else if(message.equals(App.getMusicToggleCommand()))
        {
            // Toggles effect playing
            AudioPlayer.pausePlayMusic();
            System.out.println("Command: Pause Play Music");
        }
        else if(message.equals(App.getMusicChangeCommand()))
        {
            // Changes to a specific music file
            AudioPlayer.changeMusicFile(index);
            System.out.println("Command: Change Music to " + index);
        }
        else if(message.equals(App.getEffectToggleCommand()))
        {
            // Toggles effect playing
            AudioPlayer.pausePlayEffect();
            System.out.println("Command: Pause Play Effect");
        }
        else if(message.equals(App.getEffectChangeCommand()))
        {
            // Changes to a specific effect file
            AudioPlayer.changeEffectFile(index);
            System.out.println("Command: Change Effect to " + index);
        }
        else if(message.equals(App.getEnvNextCommand()))
        {
            // Moves to the next environment file
            controller.setImageViewEnvironment(SlideShow.nextEnvironment());
            System.out.println("Command: Next Environment");
        }
        else if(message.equals(App.getEnvPreviousCommand()))
        {
            // Moves to the next environment file
            controller.setImageViewEnvironment(SlideShow.previousEnvironment());
            System.out.println("Command: Previous Environment");
        }
    }
    
    
}
