package ca.tabletop;

// Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * JavaFX App
 */
public class App extends Application
{
    //****************************************************/
    //                  CONSTANTS
    //****************************************************/
    // Server Constants
    private static final int DEFAULT_PORT = 5969; // Port used for the server
    // Command Constants
    // Constants for all commands sent by the game master instance
    private static final String MUSIC_NEXT_COMMAND = "?music-next";
    private static final String MUSIC_PREVIOUS_COMMAND = "?music-previous";
    private static final String MUSIC_TOGGLE_COMMAND = "?music-toggle";
    private static final String MUSIC_CHANGE_COMMAND = "?music-change";
    private static final String EFFECT_TOGGLE_COMMAND = "?effect-toggle";
    private static final String EFFECT_CHANGE_COMMAND = "?effect-change";
    private static final String ENV_NEXT_COMMAND = "?env-next";
    private static final String ENV_PREVIOUS_COMMAND = "?env-previous";

    //****************************************************/
    //                  VARIABLES
    //****************************************************/
    private static Scene scene; // The main window
    // Collections of files that are loaded into the system
    private static List<File> musicFiles = FileManager.loadMusicFiles(); // music
    private static List<File> effectFiles = FileManager.loadEffectFiles(); // effect
    private static List<File> imageFiles = FileManager.loadImageFiles(); // images
    // Server / Client Variables
    private static Server server; // Server for game master instances
    private static Client client; // Client for game player instances
    private static TabletopDatabase tabletopDatabase; // Database

    // Functions called when game master is launched
    public static void startGameMaster() throws IOException, UnsupportedAudioFileException, LineUnavailableException, SQLException
    {
        // Starts the server for the game master to send messages to connected instances of game players
        server = new Server(DEFAULT_PORT);
        // Loads the music and effect files into the audio player to be played
        AudioPlayer.readyAudioPlayer(musicFiles, effectFiles);
        // Loads images into the slide show to be displayed
        SlideShow.readyImages(imageFiles);
    }
    // Function called when the game player is launched. used to load the data needed for the game player
    public static void startGamePlayer(GamePlayerController controller) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException
    {
        // Loads the music and effect files into the audio player to be played
        AudioPlayer.readyAudioPlayer(musicFiles, effectFiles);
        // Loads the image files into the slide show to be displayed
        SlideShow.readyImages(imageFiles);
        // Starts the command reader thread to wait for commands comming in from the game master
        CommandReader commandReader = new CommandReader(controller);
        commandReader.start();
    }
    //****************************************************/
    //                  DATABASE CONTROLS
    //****************************************************/
    public static Character getCharacter(int id) throws SQLException
    {
        return tabletopDatabase.getCharacter(id);
    }
    public static void setCharacter(Character character) throws SQLException
    {
        tabletopDatabase.setCharacter(character);
    }
    public static Character getNPC(int id) throws SQLException
    {
        return tabletopDatabase.getNPC(id);
    }
    public static void setNPC(Character character) throws SQLException
    {
        tabletopDatabase.setNPC(character);
    }
    public static Monster getMonster(int id) throws SQLException
    {
        return tabletopDatabase.getMonster(id);
    }
    public static void setMonster(Monster monster) throws SQLException
    {
        tabletopDatabase.setMonster(monster);
    }
    public static void addQuest(Quest quest) throws SQLException
    {
        tabletopDatabase.addQuest(quest);
    }
    //****************************************************/
    //                  SERVER CONTROLS
    //****************************************************/
    // Functions to be used by the game master to send commands to the game players
    public static boolean acceptConnection(){return server.acceptSocket();}
    public static void sendMessageToPlayer(int player, String message){server.sendMessage(player, message);}
    public static void sendNextEnvironment() throws IOException{server.sendCommand(ENV_NEXT_COMMAND);}
    public static void sendPreviousEnvironment() throws IOException{server.sendCommand(ENV_PREVIOUS_COMMAND);}
    public static void sendNextMusic() throws IOException{server.sendCommand(MUSIC_NEXT_COMMAND);}
    public static void sendPreviousMusic() throws IOException{server.sendCommand(MUSIC_PREVIOUS_COMMAND);}
    public static void sendToggleMusic() throws IOException{server.sendCommand(MUSIC_TOGGLE_COMMAND);}
    public static void sendToMusic(int index) throws IOException{server.sendCommand(MUSIC_CHANGE_COMMAND + index);}
    public static void sendToggleEffect() throws IOException{server.sendCommand(EFFECT_TOGGLE_COMMAND);}
    public static void sendToEffect(int index) throws IOException{server.sendCommand(EFFECT_CHANGE_COMMAND + index);}
    public static void sendQuest(Quest quest) throws IOException{server.sendCommand("Q/"+ quest.toString());}

    //****************************************************/
    //                  CLIENT CONTROLS
    //****************************************************/
    // Connects to a server using the passed ip and DEFAULT_PORT
    public static boolean connectToServer(String ip)
    {
        // Creates a empty client
        client = new Client();
        // Connects the client to the server using the ip and port
        return client.connectToServer(ip, DEFAULT_PORT);
    }
    //****************************************************/
    //                  FXML FUNCTIONS
    //****************************************************/
    // Startup  function
    @Override
    public void start(Stage stage) throws IOException, SQLException 
    {
        scene = new Scene(loadFXML("mainMenu"), 1280, 720);
        stage.setScene(scene);
        stage.show();
        tabletopDatabase = new TabletopDatabase();
    }
    // Sets the root fxml document of the main window
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    // Loads FXML documents
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    // Main
    public static void main(String[] args) {
        launch();
    }

    //****************************************************/
    //                  GETTERS
    //****************************************************/
    // Function that returns the music files in the system
    public static List<File> getMusicFiles(){return musicFiles;}
    // Function that returns the effect files in the system
    public static List<File> getEffectFiles(){return effectFiles;}
    // function that returns the image files in the system
    public static List<File> getImageFiles(){return imageFiles;}
    // Command Getters that return the constants for the commands the game master can send to game players
    public static String getMusicNextCommand(){return MUSIC_NEXT_COMMAND;}
    public static String getMusicPreviousCommand(){return MUSIC_PREVIOUS_COMMAND;}
    public static String getMusicToggleCommand(){return MUSIC_TOGGLE_COMMAND;}
    public static String getMusicChangeCommand(){return MUSIC_CHANGE_COMMAND;}
    public static String getEffectToggleCommand(){return EFFECT_TOGGLE_COMMAND;}
    public static String getEffectChangeCommand(){return EFFECT_CHANGE_COMMAND;}
    public static String getEnvNextCommand(){return ENV_NEXT_COMMAND;}
    public static String getEnvPreviousCommand(){return ENV_PREVIOUS_COMMAND;}

    //****************************************************/
    //                  SETTERS
    //****************************************************/
    // Function to adds a music file to the system
    public static File addMusic()
    {
        // add the file to the list of files
        musicFiles = FileManager.addMusicFile(musicFiles);
        // Return the last file added
        return musicFiles.get(musicFiles.size()-1);
    }
    // Function to adds a effect file to the system
    public static File addEffect()
    {
        effectFiles = FileManager.addEffectFile(effectFiles);
        return effectFiles.get(musicFiles.size()-1);
    }
    // Function to adds a image file to the system
    public static File addImage()
    {
        imageFiles = FileManager.addImageFile(effectFiles);
        return imageFiles.get(imageFiles.size()-1);
    }

}