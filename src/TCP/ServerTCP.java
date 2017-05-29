package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by julieglasdam on 20/05/2017.
 */
public class ServerTCP {
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static int PORT = 8000;
    private static PrintStream printstream;



    // Create new socket, and wait for a client to connect
    public static void openConnection() throws IOException {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Connection open\n");
    }

    public static void handleClient() throws IOException {
            // Wait for client to connect, and accept them when they connect using the right port and IP
            socket = serverSocket.accept();
            System.out.println("Client accepted\n");
            Scanner input = new Scanner(socket.getInputStream());


            String message = input.nextLine();
            while (!message.equals("CLOSE")) {
                System.out.println("CLIENT> " + message);

                printstream = new PrintStream(socket.getOutputStream(), true);
                System.out.println("SERVER> Sending message to client");
                printstream.println("Message received");
                message = input.nextLine();
            }
    }


    public static void main(String[] args) throws IOException {
        openConnection();
        do {
            handleClient();
        } while (true);
    }
}
