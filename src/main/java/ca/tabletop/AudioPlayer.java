package ca.tabletop;

//****************************************************/
//                  IMPORTS
//****************************************************/
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.scene.control.ComboBox;

/**
 * Audio player class
 * The audio player is used by the game master and game player to control the music and effects.
 */
public class AudioPlayer 
{
   //****************************************************/
   //                  VARIABLES
   //****************************************************/
   // Music and effect files
   private static List<File> loadedMusic = new ArrayList<>(); // List of loaded music files as files.
   private static List<File> loadedEffects = new ArrayList<>(); // List of loaded effect files as files.
   private static Clip readyMusic; // Variable to store the ready to play music clip.
   private static Clip readyEffect; // Variable to store the ready to play effect clip.
   
   // Audio input streams
   private static AudioInputStream musicStream; // The music input stream for playing music.
   private static AudioInputStream effectStream; // the effect input stream for playing effects.

   // Trackers
   private static int currentMusicFile = 0; // Keeps track of the current loaded music file.
   private static int currentEffectFile = 0; // Keeps track of the current loaded effect file.


   //****************************************************/
   //                  PUBLIC FUNCTIONS
   //****************************************************/
   // Function that takes the music and effect files and readys them for use in the audio player
   public static void readyAudioPlayer(List<File> musicFiles, List<File> effectFiles) throws UnsupportedAudioFileException, IOException, LineUnavailableException
   {
      // Load passed music and effect files into the audio player.
      loadedMusic = musicFiles;
      loadedEffects = effectFiles;

      // Ready music for playback.
      musicStream = AudioSystem.getAudioInputStream(loadedMusic.get(currentMusicFile));
      readyMusic = AudioSystem.getClip();
      readyMusic.open(musicStream);

      // Ready effects for playback.
      effectStream = AudioSystem.getAudioInputStream(loadedEffects.get(currentEffectFile));
      readyEffect = AudioSystem.getClip();
      readyEffect.open(effectStream); 
   }

   //****************************************************/
   //                  MUSIC PLAYER CONTROLS
   //****************************************************/
   // Toggles music playing.
   public static void pausePlayMusic()
      {
         // Check if the music is playing.
         if(readyMusic.isRunning())
         {
            // If it is stop the music.
            readyMusic.stop();
         }
         else
         {
            // If its not start the music.
            readyMusic.start();
         }
      }

   // Changes the current music to the next one.
   public static void next(ComboBox<String> comboBox) throws IOException, UnsupportedAudioFileException, LineUnavailableException
      {
         // If the current music File isn't the last.
         if(currentMusicFile < loadedMusic.size() - 1)
         {
            // Move the current music file tracker to the next File.
            currentMusicFile++;

            // Close the current Clip and AudioInputStream.
            readyMusic.close();
            musicStream.close();

            // Get a new AudioInputStream.
            musicStream = AudioSystem.getAudioInputStream(loadedMusic.get(currentMusicFile));

            // Ready the new music Clip.
            readyMusic = AudioSystem.getClip();
            readyMusic.open(musicStream);

            if(comboBox != null)
            {
               // Update the music selection ComboBox to new music File.
               comboBox.getSelectionModel().select(currentMusicFile);
            }
            System.out.println("Music forwards");
         }
      }
   // Changes the current music to the next one.
   public static void next() throws IOException, UnsupportedAudioFileException, LineUnavailableException
      {
         // If the current music File isn't the last.
         if(currentMusicFile < loadedMusic.size() - 1)
         {
            // Move the current music file tracker to the next File.
            currentMusicFile++;

            // Close the current Clip and AudioInputStream.
            readyMusic.close();
            musicStream.close();

            // Get a new AudioInputStream.
            musicStream = AudioSystem.getAudioInputStream(loadedMusic.get(currentMusicFile));

            // Ready the new music Clip.
            readyMusic = AudioSystem.getClip();
            readyMusic.open(musicStream);

            System.out.println("Music forwards");
         }
      }
   // Changes the current music to the previous one.
   public static void previous(ComboBox<String> comboBox) throws IOException, UnsupportedAudioFileException, LineUnavailableException
      {
         // If the current music file isnt the first.
         if(currentMusicFile > 0)
         {
            // Move the current music file tracker to the previous File.
            currentMusicFile = currentMusicFile - 1;

            // Close the current Clip and AudioInputStream
            readyMusic.close();
            musicStream.close();

            // Get a new AudioInputStream
            musicStream = AudioSystem.getAudioInputStream(loadedMusic.get(currentMusicFile));

            // Ready the new music clip
            readyMusic = AudioSystem.getClip();
            readyMusic.open(musicStream);

            if(comboBox != null)
            {
               // Update the music selection ComboBox to the new music file.
               comboBox.getSelectionModel().select(currentMusicFile);
            }
            System.out.println("Music back");
         }
      }
   // Changes the current music to the previous one.
   public static void previous() throws IOException, UnsupportedAudioFileException, LineUnavailableException
      {
         // If the current music file isnt the first.
         if(currentMusicFile > 0)
         {
            // Move the current music file tracker to the previous File.
            currentMusicFile = currentMusicFile + 1;

            // Close the current Clip and AudioInputStream
            readyMusic.close();
            musicStream.close();

            // Get a new AudioInputStream
            musicStream = AudioSystem.getAudioInputStream(loadedMusic.get(currentMusicFile));

            // Ready the new music clip
            readyMusic = AudioSystem.getClip();
            readyMusic.open(musicStream);

            System.out.println("Music back");
         }
      }
   
   // Changes the music File to a selected index
   public static void changeMusicFile(int index) throws IOException, UnsupportedAudioFileException, LineUnavailableException
      {
         // Close the File and AudioInputStream.
         readyMusic.close();
         musicStream.close();

         // Update the music to passed index
         currentMusicFile = index;

         // Get a new AudioInputStream.
         musicStream = AudioSystem.getAudioInputStream(loadedMusic.get(currentMusicFile));

         // Ready the new music Clip.
         readyMusic = AudioSystem.getClip();
         readyMusic.open(musicStream);
      }

   //****************************************************/
   //                  EFFECT PLAYER CONTROLS
   //****************************************************/
   // Toggles effect playing.
   public static void pausePlayEffect()
      {
         // Check if the effect is playing.
         if(readyEffect.isRunning())
         {
            // If it is not stop the effect.
            readyEffect.stop();
         }
         else
         {
            // If it is start the music.
            readyEffect.start();
         }
      }

   // Changes the effect File based on ComboBox selection.
   public static void changeEffectFile(int index) throws IOException, UnsupportedAudioFileException, LineUnavailableException
      {
         // Close the File and the AudioInputStream.
         readyEffect.close();
         effectStream.close();

         // Get a new AudioInputStream.
         effectStream = AudioSystem.getAudioInputStream(loadedEffects.get(index));

         // ready the new effect Clip.
         readyEffect = AudioSystem.getClip();
         readyEffect.open(effectStream);
      }

   //****************************************************/
   //                  GETTERS
   //****************************************************/
   // Functions that return the currently readyed files index
   public static int getCurrentMusicFile(){return currentMusicFile;}
   public static int getCurrentEffectFile(){return currentEffectFile;}  
}
