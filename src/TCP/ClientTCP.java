package TCP;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by julieglasdam on 20/05/2017.
 */
public class ClientTCP {
    private static Socket socket = null;
    private static int PORT = 8000;
    private static String HOST = "localhost";
    private static String message, response;
    private static Scanner input;
    private static PrintStream output;

    // Creates a thread that initialize a socket and connect to the server
    public static void connectToSocket() throws IOException {
        socket = new Socket(HOST, PORT);
        System.out.println("Connected to server");
    }


    // Creates a thread that receives messages from the server
    public static void recieveMessageFromServer() {
        response = input.nextLine();
        System.out.println("SERVER> " + response);

    }


    // Creates a thread that sends messages to the server
    public static void sendMessageToServer() {
        Scanner userEntry = new Scanner(System.in);
        System.out.print("CLIENT> ");
        message = userEntry.nextLine();
        output.println(message);

    }

    // Close connection
    public static void closeConnection() throws IOException {
        output.println("Client closed connection");
        socket.close();
        System.out.println("Closed connection");
    }


    public static void somethingWithClient() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintStream(socket.getOutputStream(), true);

        do {
            sendMessageToServer();
            recieveMessageFromServer();
        } while (!message.equals("CLOSE"));
        closeConnection();


    }


    public static void main(String[] args) throws IOException {
        connectToSocket();
        somethingWithClient();
    }
}
