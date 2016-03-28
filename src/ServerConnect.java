import java.io.*;
import java.net.*;
import java.util.*;

public class ServerConnect 
{ 
    public static String hostName;
    public static int portNumber; 
    
    public static String serverResponse(String userChoice, String Preface, boolean ShowResults) throws IOException 
    {
   
        try (Socket socket = new Socket(hostName, portNumber);               
            // Get the writer and reader
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {     

            //Send request from client to the server
            output.println(userChoice);
            
            //Holds the returned value (from the server) of the client request
            String fromServer;
            
            while ((fromServer = input.readLine()) != null) 
            {

                if (ShowResults) 
                {
                    Menu.resultsFromServer = (Preface + fromServer);
                }     
            } 
        }
        catch (UnknownHostException e) 
        {
            System.err.println("Client: Don't know about host " + hostName);
            System.exit(1);
        }
        catch (IOException e) 
        {
            System.err.println("Client: Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
        
        return "Client: All Client thread(s) processes completed successfully...Result times listed below";
    }
}
