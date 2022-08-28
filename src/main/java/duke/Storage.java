package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the reading and writing of content to a specified location.
 */
public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Returns the content of the file in the specified location.
     * If the file does not exist in the specified directory, the function will create the path and file.
     *
     * @return the content stored in the file
     * @throws IOException
     */
    public String load() throws IOException {
        int index = filePath.lastIndexOf('/');

        File file = new File(filePath.substring(0, index));
        boolean dirExists = file.mkdirs();
        file = new File(filePath);
        String content = new Scanner(file).useDelimiter("\\Z").next();
        return content;
    }

    /**
     * Stores content into the specified location of a file.
     *
     * @param data the data which will be written into the file
     * @throws IOException
     */
    public void write(String data) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }

}
