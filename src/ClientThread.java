import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientThread extends Thread 
{
    //Static makes it one integer for entire class vs one for each instance.
    //Needed if you don't want the number reset with each instance creation.
    public static AtomicInteger numFinished = new AtomicInteger(0); 
    
    private int clientNum;
    private long startTime;
    public long endTime;
    
    private String userChoice;
    
    public ClientThread(int clientN, long startTime, String userChoice) 
    {
        clientNum = clientN;
        
        //Not sure if this is needed but left it in case - T Justin
//        this.startTime = startTime;
        
        this.userChoice = userChoice;
    }
    
    public static int getNumFinished() 
    {
        return numFinished.get();
    }
    
    @Override
    public void run() 
    {
        try
        {
            startTime = System.nanoTime();
            ServerConnect.serverResponse(userChoice, "Client " + clientNum + ": ", false );
        
            endTime = System.nanoTime()-startTime;
            
            // Indicate the client has finished
            numFinished.incrementAndGet(); 
        }
        catch (IOException ioe) 
        {
            System.exit(1);
        }
    }
}
