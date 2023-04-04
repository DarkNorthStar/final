package ca.tabletop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
    private static final int DEFAULT_PORT = 5969;
    // Command Constants
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
    // Server Client Variables
    private static Server server; // Server for game master instances
    //private static int numberOfPlayers = 0;
    private static Client client; // Client for game player instances

    // Functions called when game master is launched
    public static void startGameMaster() throws IOException, UnsupportedAudioFileException, LineUnavailableException
    {
        // Starts the server for the game master
        server = new Server(DEFAULT_PORT);
        // Loads the music and effect files into the audio player
        AudioPlayer.readyAudioPlayer(musicFiles, effectFiles);

        SlideShow.readyImages(imageFiles);
    }
    public static void startGamePlayer(GamePlayerController controller) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        // Loads the music and effect files into the audio player
        AudioPlayer.readyAudioPlayer(musicFiles, effectFiles);

        SlideShow.readyImages(imageFiles);

        CommandReader commandReader = new CommandReader(controller);
        commandReader.start();
    }

    //****************************************************/
    //                  SERVER CONTROLS
    //****************************************************/
    // Functions to be used by the game master to send commands to the game players
    public static boolean acceptConnection(){return server.acceptSocket();}
    public static void sendMessageToPlayer(int player, String message){server.sendMessage(player, message);}
    public static void sendNextEnvironment() throws IOException{server.sendCommand(App.getEnvNextCommand());}
    public static void sendPreviousEnvironment() throws IOException{server.sendCommand(App.getEnvPreviousCommand());}
    public static void sendNextMusic() throws IOException{server.sendCommand(MUSIC_NEXT_COMMAND);}
    public static void sendPreviousMusic() throws IOException{server.sendCommand(MUSIC_PREVIOUS_COMMAND);}
    public static void sendToggleMusic() throws IOException{server.sendCommand(MUSIC_TOGGLE_COMMAND);}
    public static void sendToMusic() throws IOException{server.sendCommand(MUSIC_CHANGE_COMMAND + AudioPlayer.getCurrentMusicFile());}
    public static void sendToggleEffect() throws IOException{server.sendCommand(EFFECT_TOGGLE_COMMAND);}
    public static void sendToEffect() throws IOException{server.sendCommand(App.getEffectChangeCommand() + AudioPlayer.getCurrentEffectFile());}

    //****************************************************/
    //                  CLIENT CONTROLS
    //****************************************************/
    public static boolean connectToServer(String ip)
    {
        client = new Client();
        return client.connectToServer(ip, DEFAULT_PORT);
    }
    //****************************************************/
    //                  FXML FUNCTIONS
    //****************************************************/
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainMenu"), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

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
    // Command Getters
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