package OS2.AUD6.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer extends Thread {

    DatagramSocket socket;


    public UDPServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    @Override
    public void run() {
        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Waiting for connection:");

        while(true) {
            try {
                socket.receive(packet);
                String receivedData = new String(buffer);
                System.out.println("RECEIVED: " + receivedData);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                byte[] message = ("Hello Client").getBytes();
                packet = new DatagramPacket(message, message.length, packet.getAddress(), packet.getPort());
                socket.send(packet);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SocketException {
        new UDPServer(9000).start();
    }

}
