package UDP;

import java.io.IOException;
import java.net.*;

/**
 * Created by julieglasdam on 26/05/2017.
 */
public class ServerUDP {
    private static DatagramSocket datagramSocket;
    private static int PORT = 8000;
    private static DatagramPacket inpacket, outpacket;
    private static byte[] buffer;
    private static int clientPORT;



    // Create new socket, and wait for a client to connect
    public static void openConnection() throws SocketException {
            // Create new socket
            datagramSocket = new DatagramSocket(PORT);
            System.out.println("Connection open\n");
    }

    public static void handleClient() throws IOException {
        InetAddress clientAddress = null;
        String messagein, messageout;
        buffer = new byte[256];
        inpacket = new DatagramPacket(buffer, buffer.length);

        datagramSocket.receive(inpacket);

        clientPORT = inpacket.getPort();
        clientAddress = inpacket.getAddress();

        messagein = new String(inpacket.getData(), 0, inpacket.getLength());
        System.out.println("CLIENT>" + messagein);

        messageout = "Message received";
        outpacket = new DatagramPacket(messageout.getBytes(), messageout.length(), clientAddress, clientPORT);

        datagramSocket.send(outpacket);
        System.out.println("SERVER> Sending message to client");


    }


    public static void main(String[] args) throws IOException {
        openConnection();
        do {
            handleClient();
        } while (true);

    }
}
