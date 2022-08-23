package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;

    }

    public String load() throws IOException {
        int index = filePath.lastIndexOf('/');

        File file = new File(filePath.substring(0, index));
        boolean dirExists = file.mkdirs();
        file = new File(filePath);
        String content = new Scanner(file).useDelimiter("\\Z").next();
        return content;
    }

    public void write(String data) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }

}
