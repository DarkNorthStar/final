package ca.tabletop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class SlideShow 
{
    private static List<Image> environmentImages = new ArrayList<>();
    private static int currentEnvironment;

    public static Image nextEnvironment() throws IOException
    {
        if(currentEnvironment+1 < environmentImages.size())
        {
            // Change to the next environment
            currentEnvironment++;

            // Set the environment to the current enviroment
            return environmentImages.get(currentEnvironment);  
        }
        return environmentImages.get(currentEnvironment);  
    }

    public static Image previousEnvironment() throws IOException
    {
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
