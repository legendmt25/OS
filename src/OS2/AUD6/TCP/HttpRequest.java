package OS2.AUD6.TCP;

import java.util.HashMap;

public class HttpRequest {
    private String method;
    private String uri;
    private String version;
    private HashMap<String, String> headers = new HashMap<>();

    public HttpRequest(String request) {
        String[] lines = request.split("\n");

        String[] firstLine = lines[0].split(" ");
        method = firstLine[0];
        uri = firstLine[1];
        version = firstLine[2];
        for(int i = 1; i < lines.length; ++i) {
            String[] line = lines[i].split(": ");
            headers.putIfAbsent(line[0], line[1]);
        }
    }

    public static HttpRequest of(String data) {
        return new HttpRequest(data);
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getVersion() {
        return version;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }
}
