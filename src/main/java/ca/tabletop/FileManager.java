package ca.tabletop;
//****************************************************/
//                  IMPORTS
//****************************************************/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

public class FileManager 
{
    //****************************************************/
    //                  CONSTANTS
    //****************************************************/
    // File extentions
    private static final String AUDIO_EXTENTION = ".wav"; // Audio file extension
    private static final String IMAGE_EXTENTION = ".jpg"; // Image file extension

    // Folder and file type constant variables 
    private static final String MUSIC_PATH = "src\\main\\gameFiles\\music\\"; // Path to where the music game files are stored
    private static final  String MUSIC_FILETYPE = "M"; // The music filetype

    private static final String IMAGE_PATH = "src\\main\\gameFiles\\images\\"; // Path to where the image game files are stored
    private static final String IMAGE_FILETYPE = "I"; // The image filetype

    private static final String EFFECT_PATH = "src\\main\\gameFiles\\effects\\"; // Path to where the effect game files are stored
    private static final String EFFECT_FILETYPE = "E"; // The effect filetype

    //****************************************************/
    //                  LOAD FILE FUNCTIONS
    //****************************************************/
    /**Collects and returns all music files.
     * Collects all of the music files found in the music folder
     * and then returns them.
     * @return a List of music files.
     */
    public static List<File> loadMusicFiles()
    {
        return loadFiles(MUSIC_PATH, MUSIC_FILETYPE, AUDIO_EXTENTION) ; // Return the loaded files
    }
    /**Collects and returns all image files.
     * Collects all of the image files found ub the image folder
     * and returns them.
     * @return A List of all image files.
     */
    public static List<File> loadImageFiles()
    {
        return loadFiles(IMAGE_PATH, IMAGE_FILETYPE, IMAGE_EXTENTION); // Return the loaded files
    } 
    /**Collects and returns all effect files.
     * Collects all of the effect files found in the effects folder
     * and returns them
     * @return A List of all the effect files.
     */
    public static List<File> loadEffectFiles()
    {
        return loadFiles(EFFECT_PATH, EFFECT_FILETYPE, AUDIO_EXTENTION); // Return the loaded files
    } 
    
    //****************************************************/
    //                  LOAD FXML ELEMENT FUNCTIONS
    //****************************************************/
    // Loads a listview with files
    public static void loadAListView(List<File> files, ListView<String> listViewToAddTo)
    {
        for (File file : files) 
        {
            listViewToAddTo.getItems().add(file.getName());    
        }
    }
    // Loads a comboBox with files
    public static void loadAComboBox(ComboBox<String> comboBox, List<File> files)
    {
        for (File file : files) 
        {
          comboBox.getItems().add(file.getName()); 
        } 
    }

    //****************************************************/
    //                  ADD FILE FUNCTIONS
    //****************************************************/
    // Add a music file to the system
    public static List<File> addMusicFile(List<File> files)
    {
        return addFile(MUSIC_PATH, MUSIC_FILETYPE, files, ".wav");
    }
    // Add a image file to the system
    public static List<File> addImageFile(List<File> files)
    {
        return addFile(IMAGE_PATH, IMAGE_FILETYPE, files, ".jpg");
    }
    // Add a effect file to the system
    public static List<File> addEffectFile(List<File> files )
    {
        return addFile(EFFECT_PATH, EFFECT_FILETYPE, files, ".wav");
    }

    //****************************************************/
    //                  PRIVATE FUNCTIONS
    //****************************************************/
    // Adds a file to the system
    private static List<File> addFile(String folder, String fileType, List<File> files, String extention)
    {
        // Opens the system file dialog to get a file
        File file = openFileDialog();

        // Copy the file to its new location
        copyFile(file, folder + fileType + (files.size()+1) + extention);

        // add the file to the list of files
        files.add(file);

        // return the list of files
        return files;
    }

    /**Collects and returns files found in a folder.
     * Collects all the files from a folder and adds them to a listView.
     * @param folder The path to a folder as a String.
     * @param fileType The title of the file type to collect.
     * @return A List of files
     */
    private static List<File> loadFiles(String folder, String fileType, String extention )
    {
        // Variables
        int fileCounter = 0; // Number of files
        List<File> files = new ArrayList<File>(); // Files found
        boolean moreFiles = true; // Are more files

        // Search for files
        while(moreFiles == true)
        {
            // Load possable file
            File file = new File(folder + fileType + (fileCounter+1) + extention);

            // if the file exists
            if(file.exists() == true)
            {
                // Add the file to files
                files.add(file);

                // Increase the file counter
                fileCounter++;
            }
            else
            {
                // No more files
                moreFiles = false;
            }
        }
            // Return Files
            return files;
    }

    /**Copys a file to a new path
     * Copys a file from one location to another location using a file
     * and a path to a destination.
     * @param copyFrom A File to copy.
     * @param copyTo A path to copy the file to.
     */
    private static void copyFile(File copyFrom, String copyTo)
    {
        // Trys to copy the file
        try
        {
            File copy = new File(copyTo);
            Files.copy(copyFrom.toPath(), copy.toPath());
        }
        catch(IOException ex)
        {
            System.out.print("ERROR FAILED TO COPY FILE");
        }
    }

    /**Opens file dialog
     * Opens up the systems file dialog for the user to select a file
     * the selected file is then returned
     * @return The File selected in the systems file dialog.
     */
    private static File openFileDialog()
    {
        // Empty file
        File file = null;

        // Open dialog to select a file
        FileChooser FileChooser = new FileChooser();
        file = FileChooser.showOpenDialog(null);

        // Return the file selected
        return file;
    };
}
