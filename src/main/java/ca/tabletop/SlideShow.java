/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   SlideShow.java
 *  Description:
 *          This is the slideshow class. The slideshow is used by both the game master and game player to display and manage images to be shown on the screen.
 *          Both game master and game player use the slide show to display image the perk of having them both use the same slideshow system is keeping them synced to gether is much easier.
 */
package ca.tabletop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class SlideShow 
{
    // Variables
    private static List<Image> environmentImages = new ArrayList<>(); // List of images loaded into the slide show
    
    private static int currentEnvironment; // The index of the current image being shown

    // Changes the image to the next one
    public static Image nextEnvironment() throws IOException
    {
        // Check if the image is last one to avoid out of bounds error
        if(currentEnvironment+1 < environmentImages.size())
        {
            // Change to the next environment
            currentEnvironment++;

            // Set the environment to the current enviroment
            return environmentImages.get(currentEnvironment);  
        }
        return environmentImages.get(currentEnvironment);  
    }

    // Changes the image to the previous one
    public static Image previousEnvironment() throws IOException
    {
        // Check if the current image is the first to avoid out of bounds error
        if(currentEnvironment > 0)
        {
            // Change to the previous environment
            currentEnvironment--;
            
            // Set the environment to the current enviroment
            return environmentImages.get(currentEnvironment);
        }
        // Set the environment to the current enviroment
        return environmentImages.get(currentEnvironment);
    }

    // Function for getting the current environment image
    public static Image getEnvironmentImage(int imageNumber) 
    {
        return environmentImages.get(imageNumber);
    }
    // Function for getting the number of images
    public static int getNumberOfImages() 
    {
        return environmentImages.size();
    }

    public static void readyImages(List<File> images)
    {
        // Converts all file images to images for use in the slideshow
        for (File image : images) 
        {
            Image newImage = new Image(image.toURI().toString());
            environmentImages.add(newImage);
        }
    }
}
