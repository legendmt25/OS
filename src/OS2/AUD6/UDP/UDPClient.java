package OS2.AUD6.UDP;

import java.io.IOException;
import java.net.*;

public class UDPClient extends Thread {
    String host;
    int port;
    InetAddress address;
    DatagramSocket socket;
    String message;

    public UDPClient(String host, int port, String message) throws UnknownHostException, SocketException {
        this.host = host;
        this.port = port;
        this.message = message;
        this.address = InetAddress.getByName(host);
        socket = new DatagramSocket();
    }

    @Override
    public void run() {
        byte[] buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        try {
            socket.send(packet);
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.receive(packet);
            System.out.println(new String(packet.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        new UDPClient("localhost", 9000, "Hello Server").start();
    }

}
