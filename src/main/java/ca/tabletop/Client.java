/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   Client.java
 *  Version                 1.0    
 *  Date Created :          2023-01-31
 *  Date Last Modifyed:     2023-01-31
 *  Description:
 *              This is the client.
 *              The client is used by the game player to connect to a game masters server.
 * 
 */
package ca.tabletop;

//****************************************************/
//                  IMPORTS
//****************************************************/
import java.io.*;
import java.net.*;

// Client 
public class Client 
{
    //****************************************************/
    //                  Variables
    //****************************************************/
    // Sockets
    private static Socket socket = null; // Socket to Server
    private static DataOutputStream output;
    private static DataInputStream input;
    
    //****************************************************/
    //                  Constructors
    //****************************************************/
    public Client(){};
    public Client(String ip, int port)
    {
        connectToServer(ip, port);
    }

    //****************************************************/
    //                  Class Methods
    //****************************************************/
    // Connect to a server
    public boolean connectToServer(String ip, int port)
    {
        // Trys connecting to a server
        try
        {
            System.out.println("Attempting to connect...");
            socket = new Socket(ip, port);
            System.out.println("Waiting for server...!");
            return true;
        }catch(Exception ex)// Catch for failed to connect
        {
            System.out.println("***Failed to connect to server***");
            return false;
        }
    }

    /**
    * Sends a message to a server
    **/ 
    public void sendMessage(String message)
    {
        // Trys sending a message to a server
        try
        {
            System.out.println("Sending Message...");
            output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(message);
            System.out.println("Message Sent!");
        }catch(IOException ex) // Catch failed to send message
        {
            System.out.println("***Failed to Send***");
        }  
    }

    // Reads a message into the console
    public void readMessage() throws IOException
    {
        input = new DataInputStream(socket.getInputStream());
        String message = input.readUTF();
        System.out.println(message);
    }

    // reads a message and returns it
    public static String returnMessage() throws IOException
    {
        input = new DataInputStream(socket.getInputStream());
        String message = input.readUTF();
        return message;
    }

    /**
    * Closes the client connection
    **/  
    public void close()
    {
        // Trys closeing the client connection
        try
        {
            System.out.println("Closeing Client...");
            socket.close();
            input.close();
            output.close(); 
            System.out.println("Closed!");
        }
        catch(IOException ex)// Catch failed to close client
        {
            System.out.println("***Failed to Close***");
        }
    }


    //****************************************************/
    //                  Getter('s)
    //****************************************************/
    //****************************************************/
    //                  Setter('s)
    //****************************************************/

    
}
