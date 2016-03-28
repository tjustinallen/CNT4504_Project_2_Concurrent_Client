import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu 
{    
    Scanner menuInput = new Scanner(System.in);
    public static String[] serverResult = new String[75];
    public static String resultsFromServer;

    public void execute()
    {

        System.out.println("Welcome to Project 1\n");
        
        boolean exit = false;
        
        while (!exit)
        {
            System.out.println("");
            
            System.out.println("Please select one of the following options: \n");
            
            System.out.println("1. Host current Date and Time");
            System.out.println("2. Host uptime");
            System.out.println("3. Host memory use");
            System.out.println("4. Host Netstat");
            System.out.println("5. Host current users");
            System.out.println("6. Host running processes");
            System.out.println("7. Quit");
            System.out.println("");
            System.out.print("Enter selection(1-7): ");
            
            String userChoice = menuInput.next();
            
            //Runs user choice command for multiple clients
            switch (userChoice) 
            {
                case "1":
                    System.out.println("");
                    System.out.println("Client: Server will be requested for Date/Time...");
                    System.out.println("...");
                    Latency("Date/Time", 1 );
                    Latency("Date/Time", 5 );
                    Latency("Date/Time", 10 );
                    Latency("Date/Time", 20);
                    Latency("Date/Time", 30);
                    Latency("Date/Time", 40);
                    Latency("Date/Time", 50);
                    Latency("Date/Time", 60);
                    Latency("Date/Time", 70);
                    Latency("Date/Time", 75);
                    break;
                case "2":
                    System.out.println("");
                    System.out.println("Client: Server will be requested for Uptime");
                    System.out.println("...");
                    Latency("Uptime", 1 );
                    Latency("Uptime", 5 );
                    Latency("Uptime", 10 );
                    Latency("Uptime", 20);
                    Latency("Uptime", 30);
                    Latency("Uptime", 40);
                    Latency("Uptime", 50);
                    Latency("Uptime", 60);
                    Latency("Uptime", 70);
                    Latency("Uptime", 75);
                    break;
                case "3":
                    System.out.println("");
                    System.out.println("Client: Server will be requested for Memory");
                    System.out.println("...");
                    Latency("Memory", 1 );
                    Latency("Memory", 5 );
                    Latency("Memory", 10 );
                    Latency("Memory", 20);
                    Latency("Memory", 30);
                    Latency("Memory", 40);
                    Latency("Memory", 50);
                    Latency("Memory", 60);
                    Latency("Memory", 70);
                    Latency("Memory", 75);
                    break;
                case "4":
                    System.out.println("");
                    System.out.println("Client: Server will be requested for Netstat");
                    System.out.println("...");
                    Latency("Netstat", 1 );
                    Latency("Netstat", 5 );
                    Latency("Netstat", 10 );
                    Latency("Netstat", 20);
                    Latency("Netstat", 30);
                    Latency("Netstat", 40);
                    Latency("Netstat", 50);
                    Latency("Netstat", 60);
                    Latency("Netstat", 70);
                    Latency("Netstat", 75);
                    break;
                case "5":
                    System.out.println("");
                    System.out.println("Client: Server will be requested for Current Users");
                    System.out.println("...");
                    Latency("Current Users", 1 );
                    Latency("Current Users", 5 );
                    Latency("Current Users", 10 );
                    Latency("Current Users", 20);
                    Latency("Current Users", 30);
                    Latency("Current Users", 40);
                    Latency("Current Users", 50);
                    Latency("Current Users", 60);
                    Latency("Current Users", 70);
                    Latency("Current Users", 75);
                    break;
                case "6":
                    System.out.println("");
                    System.out.println("Client: Server will be requested for Running Processes");
                    System.out.println("...");
                    Latency("Running Processes", 1 );
                    Latency("Running Processes", 5 );
                    Latency("Running Processes", 10 );
                    Latency("Running Processes", 20);
                    Latency("Running Processes", 30);
                    Latency("Running Processes", 40);
                    Latency("Running Processes", 50);
                    Latency("Running Processes", 60);
                    Latency("Running Processes", 70);
                    Latency("Running Processes", 75);
                    break;
                case "7":
                    System.out.println("\n");
                    System.out.println("Client: Thank you for using our software, have a nice day.");
                    exit = true;
                    return;
                default:
                    System.out.println("\n");
                    System.out.println("Client: \"" + userChoice + "\"" + " is an invalid option.");
                    break; 
            //end switch
            }
        //end while
        }       
    //end execute()
    }
        
    private void Latency(String ServerChoice, int numClients)
    {
        //Send numClients to ServerConnect()
        ServerConnect.numClients = numClients;

        // Set number of completes runs to zero
        ClientThread.numFinished = new AtomicInteger(0);
        
        System.out.println("Client: Preparing to call Server with request for " + ServerChoice +" with " + numClients + " Client thread(s)..." );
        ClientThread[] clients = new ClientThread[numClients]; 
        // total time for all processing
        long tTime = 0; 
        
        //Printed Client progress comments for user
        System.out.println("...");
        System.out.println("Client: Creating " + numClients + " Client thread(s) and requesting " + ServerChoice + " for each  of the " + numClients + " Client thread(s)...");
        System.out.println("...");
        System.out.println("Client: All " + numClients + " Client thread(s) have been created...");
        System.out.println("...");
        System.out.println("Client: Calling Server with request for " + ServerChoice +"..." );
        System.out.println("...");
        
        try 
        {    
            String srvrResponse = ServerConnect.serverResponse(ServerChoice, "Server: ", true );
            
            //Also count total time at this level
            long topStartTime = System.nanoTime();
             
            //Create and start all the client threads
            for(int x = 0; x < numClients; x++) 
            { 
                //Create new client thread
                clients[x] = new ClientThread(x, System.nanoTime(), ServerChoice );
            }
            
            for (int x =0; x < numClients; x++) 
            {
                //Start this thread (calls RUN)
                clients[x].start();
            }
           
            //Loop until all client threads are done
            while (ClientThread.getNumFinished() < numClients)
            {
                Thread.sleep(5000);
            }
            
            long topEndTime = System.nanoTime();

            //This ensures all thread have really finished writing to the console
            Thread.sleep(5000);
            
            for(int x = 0; x < numClients; x++) 
            {                   
                long time = clients[x].endTime/1000;
                
                //Printed process results for each Client thread
                System.out.println("--------------------------------------------------------------------------------------------");
                System.out.println("Client " + (x + 1) + " requests Server for " + ServerChoice + "...");
                System.out.println("     " + serverResult[x]);
                System.out.println("Client " + (x + 1) + " request processed in: " + time + " microseconds");
                                
                tTime += time;
            }
            
            //Printed process results for all Client threads
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println(srvrResponse);
            System.out.println("");
            System.out.println("********************************************************************************************");
            System.out.println("RESULTS FOR " + numClients + " CLIENT(S)");
            System.out.println("------------------------");
            System.out.println("     Time for all clients to complete:     " + ( topEndTime - topStartTime ) + " microseconds");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("     Total completion time:                " + tTime + " microseconds");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("     Average completion time:              " + tTime/numClients + " microseconds");
            System.out.println("********************************************************************************************");
            System.out.println();
            System.out.println();
            
        }
        catch (IOException ex)
        {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(InterruptedException iee) 
        {
            iee.printStackTrace();;
            System.exit(1);
        }
    }
}
