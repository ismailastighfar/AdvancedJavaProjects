// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server
{
	//initialize socket and input stream
	private Socket		 socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;

	// constructor with port
	public Server(int port)
	{
		// starts server and waits for a connection
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			// takes input from the client socket
           in = new DataInputStream( new BufferedInputStream(socket.getInputStream())); 

            // Collect the nodes and the matrix through the data
            int m = in.readInt();
            int n = in.readInt();

            int first[][] = new int[m][n];
            int second[][] = new int[m][n]; 

            for (int i = 0; i <m; i++)
                for (int j = 0; j < n; j++)
                    first[i][j] = in.readInt();

            for (int i = 0; i <m; i++)
                for (int j = 0; j < n; j++)
                    second[i][j] = in.readInt();

            String opr = in.readUTF();
            
            if (opr == "+") {
                int[][] S = new int[m][n];
                for(int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        S[i][j] = first[i][j] + second[i][j];
                    }
                }
                System.out.println("The sum of the given matrices is: ");
                    for(int i = 0; i < m; i++) {
                        for (int j = 0; j < n; j++) {
                            System.out.print(S[i][j] + "   ");
                        }
                        System.out.println();
        }
            }
            else if(opr == "-") {
                int diff[][] = new int[m][n];  
  
                //Performs subtraction of matrices a and b. Store the result in matrix diff  
                for(int i = 0; i < m; i++){  
                    for(int j = 0; j < n; j++){  
                        diff[i][j] = first[i][j] - second[i][j];  
                    }  
                }  
          
                System.out.println("Subtraction of two matrices: ");  
                for(int i = 0; i < m; i++){  
                    for(int j = 0; j < n; j++){  
                       System.out.print(diff[i][j] + " ");  
                    }  
                    System.out.println();  
                }  
            }  
            
		
			System.out.println("Closing connection");

			// close connection
			socket.close();
			in.close();
		}
		catch(IOException i)
		{
			System.out.println(i);
		}
	}

	public static void main(String args[])
	{
		Server server = new Server(5000);
	}
}
