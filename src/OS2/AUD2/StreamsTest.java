package OS2.AUD2;

import java.io.*;

public class StreamsTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("./test.txt");

        //PrintWriter pw = new PrintWriter(new FileWriter("test.txt", true));

        //copyStream(fileInputStream, System.out);

        usingRandomAccessFile(new File("./test.txt"));
    }

    static void copyStream(InputStream is, OutputStream os) throws IOException {
        int c = 0;
        while((c = is.read()) != -1) {
            os.write(c);
            os.flush();
        }

        is.close();
        os.close();
    }

    static void correctReading(InputStream is) throws IOException {
        byte[] buffer = new byte[100];
        int length = 100;
        int offset = 0;
        int readBytes = 0;

        while ((readBytes = is.read(buffer, offset, length)) != -1) {
            length -= readBytes;
            offset += readBytes;
        }

        is.close();
    }

    private static void usingRandomAccessFile(File f) throws IOException {
        RandomAccessFile write = new RandomAccessFile(f, "rw");
        write.writeInt(32);
        write.writeInt(32);
        write.writeInt(32);
        write.close();

        RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");
        Integer val = randomAccessFile.readInt();
        while(val != null) {
            System.out.println(val);
            val = randomAccessFile.readInt();
        }
        randomAccessFile.close();
    }

}
