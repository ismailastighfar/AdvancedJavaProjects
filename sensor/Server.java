import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.EventQueue;

public class Server {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)){
            System.out.println("listening to port:5000");
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket+" connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            receiveFile("sonsor.csv");

            try (BufferedReader br = new BufferedReader(new FileReader("sonsor.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                System.out.println(line);
                }
            }
            
            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
            
        } catch (Exception e){
            e.printStackTrace();
        }

       

            EventQueue.invokeLater(() -> {
    
                var ex = new Chart();
                ex.setVisible(true);
            });
        
    }

    private static void receiveFile(String fileName) throws Exception{
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        
        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }

    
}