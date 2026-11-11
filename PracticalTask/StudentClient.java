package PracticalTask;

import java.io.*; // Importing necessary classes for input and output operations
import java.net.*; // Importing necessary classes for networking operations (Socket, ServerSocket

public class StudentClient {
    public static void main(String[] args) throws IOException{

        int port = 5024;

        // Connect to the server on localhost and port
        Socket socket = new Socket("localhost", port);
        System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());

        // Create input and output streams for communication with the server
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );

        // Create an output stream to send data to the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Read a student ID from the keyboard and send it to the server
        BufferedReader keyboard = new BufferedReader(
            new InputStreamReader(System.in)
        );
        System.out.print("Enter a student ID to send to the server: ");
        String studentId = keyboard.readLine();

        out.println(studentId); // Send the student ID to the server

        // Receive a response from the server
        String reply = in.readLine();
        System.out.println("Server Says: " + reply); // Print the response from the server  

        // If the student exists, print the student information
        if (reply != null && reply.startsWith("Student Name:")) {
            System.out.println("Student information received from the server.");
            // Parse the course from the server's single-line reply
            // Format: "Student Name: <name>, Course: <course>"
            int courseIndex = reply.indexOf(", Course:");
            String course = (courseIndex != -1) ? reply.substring(courseIndex + ", Course:".length()).trim() : "Unknown";
            System.out.println("Course: " + course); // Print the course information
        } else {
            System.out.println("Student ID not found.");
        }

        // Close the streams and socket
        socket.close();
        
    }
}
