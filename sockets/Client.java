
// A Java program for a Client
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client
{
	// initialize socket and input output streams
	private Socket socket		 = null;
	private DataOutputStream output = null;

	// constructor to put ip address and port
	public Client(String address, int port) throws IOException
	{
		// establish a connection
		try
		{
			socket = new Socket(address, port);
			System.out.println("Connected");

			// sends output to the socket
            output = new DataOutputStream(socket.getOutputStream());
		}
		catch(UnknownHostException u)
		{
			System.out.println(u);
		}
		catch(IOException i)
		{
			System.out.println(i);
		}


        int m, n, i, j;
        Scanner in = null;
        try {
            in = new Scanner(System.in);
            System.out.println("Enter the number "
                               + "of rows of the matrix");
            m = in.nextInt();
            System.out.println("Enter the number "
                               + "of columns of the matrix");
            n = in.nextInt();
  
            // Declare the matrix
            int first[][] = new int[m][n];
            int second[][] = new int[m][n];

  
            // Read the matrix values
            System.out.println("Enter the elements of the matrix");
            for (i = 0; i < m; i++)
                for (j = 0; j < n; j++)
                    first[i][j] = in.nextInt();

            System.out.println("Enter the elements of the matrix");
            for (i = 0; i < m; i++)
                for (j = 0; j < n; j++)
                    second[i][j] = in.nextInt();  
                    
                    output.writeInt(m);
                    output.writeInt(n);
                    output.flush();
                    for ( i = 0; i < m; i++)
                        for ( j = 0; j < n; j++)
                            output.writeInt(first[i][j]);
                            output.flush();
                    for ( i = 0; i < m; i++)
                    for ( j = 0; j < n; j++)
                        output.writeInt(second[i][j]);
                        output.flush();
                    
             String opr = in.next();
             output.writeBytes(opr);
             output.flush();

}
        catch (Exception e) {
        }
        finally {
            in.close();
        }

		// close the connection
		try
		{
			output.close();
			socket.close();
		}
		catch(IOException o)
		{
			System.out.println(o);
		}
	}

	public static void main(String args[]) throws IOException
	{
		Client client = new Client("127.0.0.1", 5000);
	}
}

    
