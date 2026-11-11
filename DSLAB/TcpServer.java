package DSLAB;

import java.io.*; // Importing necessary classes for input and output operations
import java.net.*; // Importing necessary classes for networking operations (Socket, ServerSocket, etc.)

public class TcpServer{
    public static void main(String[] args) throws IOException{

        int port = 5021; 
        //Create a server on the port 
        ServerSocket server = new ServerSocket(port);
        
        // wait for a client to connect
        System.out.println("Waiting for a client to connect...");
        Socket socket = server.accept(); // Accept the incoming client connection

        System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

        // Create input and output streams for communication with the client
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );

        // Create a PrintWriter to send data to the client
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        // Receive a message from the client
        String message = in.readLine();
        System.out.println("Client Says: " + message);

        // Send a response back to the client
        out.println("Hello from the server!");

        // Close the streams and socket
        socket.close();
        server.close();


    }
}
