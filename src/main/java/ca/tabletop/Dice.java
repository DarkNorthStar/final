/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   Dice.java
 *  Description:
 *              This is a simple class for dice.
 *              it simply houses rolls for dice used in D&D 5E
 */
package ca.tabletop;
//****************************************************/
//                  IMPORTs
//****************************************************/
import java.util.Random;

public class Dice 
{
    //****************************************************/
    //                  VARIABLES
    //****************************************************/
    // Creates a random number generator
    private static Random randomNumbers = new Random();

    //****************************************************/
    //                  ROLLs
    //****************************************************/
    public static int rollpercentile(){return randomNumbers.nextInt(100)+1;}
    public static int rollD4(){return randomNumbers.nextInt(4)+1;}
    public static int rollD6(){return randomNumbers.nextInt(6)+1;} 
    public static int rollD8(){return randomNumbers.nextInt(8)+1;}
    public static int rollD10(){return randomNumbers.nextInt(10)+1;}
    public static int rollD12(){return randomNumbers.nextInt(12)+1;}
    public static int rollD20(){return randomNumbers.nextInt(20)+1;}
}
