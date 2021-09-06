package OS2.AUD6.TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class TCPServer extends Thread {
    int port;

    public TCPServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        HashSet<HttpWorker> workers = new HashSet<>();
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(port);
            System.out.println("Waiting for connections");
            while(true) {
                Socket client = socket.accept();
                new HttpWorker(client).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPServer server = new TCPServer(9000);
        server.start();
    }
}
