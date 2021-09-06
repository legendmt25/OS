package OS2.AUD6.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient extends Thread {
    String host;
    int port;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            socket = new Socket(host, port);
            pw = new PrintWriter(socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                pw.println(br.readLine());
                pw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPClient client = new TCPClient("localhost", 9000);
        client.start();
    }
}
