package PracticalTask;

import java.io.*; // Importing necessary classes for input and output operations
import java.net.*; // Importing necessary classes for networking operations (Socket, ServerSocket,
import java.util.HashMap; // Importing HashMap class for storing student data
import java.util.Map; // Importing Map interface for working with key-value pairs

public class StudentServer {
    public static void main(String[] args) throws IOException{
        Map<String, String[]> studentData = new HashMap<>(); // Create a HashMap to store student data
        studentData.put("ICT001", new String[]{"Amal","Distributed Systems"});
        studentData.put("ICT002", new String[]{"Nimal","Computer Networks"});
        studentData.put("ICT003", new String[]{"Kamal","Cloud Computing"});

        int port = 5024;  // Define the port number for the server
        ServerSocket server = new ServerSocket(port); // Create a server socket to listen for incoming connections
        System.out.println("Waiting for a client to connect..."); // Print a message indicating that  the server is waiting for a client to connect
        Socket socket = server.accept(); // Accept the incoming client connection
        System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

        // Create input and output streams for communication with the client
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Create a PrintWriter to send data to the client

        // Receive a student ID from the client
        String studentId = in.readLine();
        System.out.println("Received student ID: " + studentId);

        // Check if the student ID exists in the HashMap
        if (studentData.containsKey(studentId)) {
            String[] studentInfo = studentData.get(studentId); // Retrieve the student information from the HashMap
            String response = "Student Name: " + studentInfo[0] + ", Course: " + studentInfo[1]; // Prepare the response message with student information
            out.println(response); // Send the response back to the client
        } else {
            out.println("Student ID not found."); // Send a message indicating that the student ID was not found
        }

        // Close the streams and socket
        socket.close();
        server.close();
    }
}
