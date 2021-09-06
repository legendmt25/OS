package OS2.AUD6.TCP;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HttpWorker extends Thread {
    Socket socket;
    public HttpWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println(String.format("Connected %s : %d", socket.getInetAddress(), socket.getPort()));
        BufferedReader br;
        PrintWriter pw;
        try {
            StringBuilder sb = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = "";
            while(!(line = br.readLine()).equals("")) {
                sb.append(line).append("\n");
            }

            //br.lines().forEach(x -> sb.append(x).append("\n "));

            HttpRequest request = HttpRequest.of(sb.toString());
            pw.println("HTTP/1.1 200 OK\n");
            if(request.getMethod().equals("GET")) {
                if(request.getUri().equals("/time")) {
                    pw.println(String.format("<html><body><h1>%s</h1></body></html>", LocalDateTime.now().format(DateTimeFormatter.ISO_TIME)));
                } else {
                    pw.write("<html><body><h1>/</h1></body></html>");
                }
            }
            pw.flush();

            br.close();
            pw.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
