package OS2.AUD1;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilesTest{

    private static void getAllSubFiles(File file, String filter) {
        if(file.isFile()) {
            if(file.getAbsolutePath().contains(filter)) {
                System.out.println(file.getAbsolutePath());
            }
            return;
        }

        for (File listFile : file.listFiles()) {
            getAllSubFiles(listFile, filter);
        }
    }
    public static void main(String[] args) throws IOException {
        File f = new File("./test.txt");
        //f.createNewFile();
        getAllSubFiles(f.getParentFile(), ".xml");

        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<String> arg = br.lines().collect(Collectors.toCollection(ArrayList::new));
        br.close();



        BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));

/*
        arg.forEach(x -> {
            try {
                System.out.println(x);
                bw.write(x);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

 */

        System.out.println(f);

        bw.close();
        System.out.println(f.exists());
    }
}
