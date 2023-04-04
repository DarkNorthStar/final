/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   Server.java
 *  Version                 1.0    
 *  Date Created :          2023-01-31
 *  Date Last Modifyed:     2023-01-31
 *  Description:
 *              This...
 * 
 */
package ca.tabletop;

//****************************************************/
//                  IMPORTS
//****************************************************/ 
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

// Server Class
public class Server 
{
    //****************************************************/
    //                  Variables
    //****************************************************/
    // Sockets
    private ServerSocket serverSocket = null; // Server
    private List<Socket> playerSockets = new ArrayList<Socket>();
    // Data Streams
    private List<DataInputStream> inputs = null;
    private List<DataOutputStream> outputs = null;
    // Holders
    private String message; // Variable for holding a message

    //****************************************************/
    //                  Constructor(s)
    //****************************************************/
    public Server(int port)
    {
        // Trys Creating a server with the passed port
        try
        {
            System.out.println("Creating Server with port: " + port);
            // Creates a new server socket        
            serverSocket = new ServerSocket(port);
            System.out.println("Server Created!\n\n");

        }catch(IOException ex)// Catch Failed to start the server
        {
            // Output error message
            System.out.println("***Server Failed to Start***");
        }
    }
    //****************************************************/
    //                  Class Methods
    //****************************************************/
    /**
    * Accepts a new connection and returns its socket
    * @return Connection success (T or F)
    */
    public boolean acceptSocket()
    {
        // Trys to accept a connection
        try
        {
            DataInputStream input;
            DataOutputStream output;
            System.out.println("Accepting Connection...");
            playerSockets.add(serverSocket.accept());
            System.out.println("Connected!");
            input = new DataInputStream(playerSockets.get(playerSockets.size()-1).getInputStream());
            inputs.add(input);
            output = new DataOutputStream(playerSockets.get(playerSockets.size()-1).getOutputStream());
            outputs.add(output);
            return true;// Connected
        }catch(IOException ex)// Catch for Failed connection
        {
            // Error message
            System.out.println("***Failed to Connect***");
            return false;
        }
    }
    
    /**
     * Prints a passed message to console
     */
    public void readMessage(int player)
    {
        // Try reading the message
        try
        {
            System.out.println("Reading message...");
            message = inputs.get(player).readUTF();
            System.out.println("Message Read!");
        }catch(IOException ex)// Catch failed to read
        {
            System.out.println("***Failed to read***");
        }
        // Output Message
        System.out.println("Outputing Message to console...");
        System.out.println(message);
        System.out.println("Complete!");
    }

    public void sendMessage(int player, String message)
    {
        try
        {
            outputs.get(player).writeUTF(message);   

        }catch(IOException ex)
        {
            // failed to send message
        }
    }

    // Sends a command to all connected players
    public void sendCommand(String command) throws IOException
    {
        for (DataOutputStream output : outputs) 
        {
            output.writeUTF(command);
        }
    }


    /**
    * Closes input server socket and socket
    * */ 
    public void shutdown()
    {
        // Trys to shutdown the server
        try
        {
            System.out.println("Closeing Server...");
            serverSocket.close();
            for (Socket socket : playerSockets) 
            {
                socket.close(); 
            }
            for (DataInputStream input : inputs) 
            {
                input.close();   
            }
            for (DataOutputStream output : outputs) 
            {
                output.close();    
            }
            System.out.println("Closed Server!");
        }catch(IOException ex)// Catch server shutdown fail
        {
            // Output error to console
            System.out.println("***Failed to Close Server***");
        } 
    }


    //****************************************************/
    //                  Getters
    //****************************************************/
    /**
     * Returns a connection socket
     * @return Socket to a client
     */
    public List<Socket> getSockets()
    {
        return playerSockets;
    }
    /**
     * Returns the servers socket
     * @return Socket to the server
     */
    public ServerSocket getServerSocket()
    {
        return serverSocket;
    }

    // Returns message
    public String getmessage()
    {
        return message;
    }
    //****************************************************/
    //                  Setters Methods
    //****************************************************/


}    