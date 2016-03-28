import java.io.*;

public class Client 
{
    public static void main(String[] args) throws IOException 
    {
        //Validation for hostName and portNumber
        if (args.length != 2) 
        {
            System.err.println("Usage: java Client <host name> <port number>");
            System.exit(1);
        }
        
        //parse input args onload for hostName and portNumber
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        ServerConnect.hostName = hostName;
        ServerConnect.portNumber = portNumber;
        
        Menu menu = new Menu();
        menu.execute(); 
    }
    
}
