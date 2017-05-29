package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;


/**
 * Created by julieglasdam on 26/05/2017.
 */
public class ClientUDP {
    private static DatagramSocket datagramSocket;
    private static final int PORT = 8000;
    private static DatagramPacket inpacket, outpacket;
    private static byte[] buffer;
    private static InetAddress host;
    private static String message, response;

    public static void connectToHost() throws UnknownHostException {
        host = InetAddress.getLocalHost();
    }

    public static void recieveMessageFromServer() throws IOException {
        inpacket = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(inpacket);
        response = new String(inpacket.getData(), 0, inpacket.getLength());
        System.out.println("SERVER>" + response);

    }

    public static void sendMessageToServer() throws IOException {
        Scanner userEntry = new Scanner(System.in);
        System.out.print("CLIENT> ");
        message = userEntry.nextLine();
        outpacket = new DatagramPacket(message.getBytes(), message.length(), host, PORT);
        datagramSocket.send(outpacket);

    }

    public static void accessServer() throws IOException {
        datagramSocket = new DatagramSocket();
        buffer = new byte[256];

        do {
            sendMessageToServer();
            recieveMessageFromServer();
        } while (!message.equals("CLOSE"));
    }

    public static void main(String[] args) throws IOException {
        connectToHost();
        accessServer();
    }

}
