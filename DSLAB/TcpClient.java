package DSLAB;

import java.io.*; // Importing necessary classes for input and output operations
import java.net.*; // Importing necessary classes for networking operations

public class TcpClient{
    public static void main(String[] args) throws IOException{

        int port = 5021;
        // Connect to the server on localhost and port
        Socket socket = new Socket("localhost", port);
        System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());

        // Create input and output streams for communication with the server
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );

        // Create an output stream to send data to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Read a message from the keyboard and send it to the server
        BufferedReader keyboard = new BufferedReader(
            new InputStreamReader(System.in)
        );

        // Send a message to the server
        System.out.print("Enter a message to send to the server: ");
        String message = keyboard.readLine();
        out.println(message);

        // Receive a response from the server
        String reply = in.readLine();
        System.out.println("Server Says: " + reply);

        // Close the streams and socket
        socket.close(); 
        System.out.println("Connection closed.");
    }
}
