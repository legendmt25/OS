package OS2.AUD6.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Worker extends Thread {
    Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;

        System.out.println(String.format("Connected: %s | %d", socket.getInetAddress(), socket.getPort()));
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());

            br.lines().forEach(System.out::println);
            pw.println(String.format("Server message to: %s", socket.getInetAddress()));
            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
            try {
                br.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
