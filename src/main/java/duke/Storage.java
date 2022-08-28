package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class Storage {
    private String filePath;

    /**
     * A constructor to specify the path
     * @param filePath the path to save the file and load the file from
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the file
     * @param str The strings to be written in the file
     */
    public void update(String str) {
        try {
            System.out.println(filePath);
            FileWriter fw = new FileWriter(filePath);
            fw.write(str);
            fw.close();
        }  // Catch block to handle the exception
        catch (IOException ex) {
            System.out.print("Invalid Path");
        }
    }
}
