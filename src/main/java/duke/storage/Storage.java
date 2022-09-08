package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Storage class
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Storage {

    /**
     * Represents the path name.
     */
    private final String pathname;

    /**
     * Represents the file writer
     */
    private FileWriter fw;

    /**
     * Represents a constructor method for Storage
     * @param s String representing file path
     */
    public Storage(String s) {
        this.pathname = s;
    }

    /**
     * Loads the storage.
     * @return File writer which can be used to write message
     */
    public FileWriter load() {
        try {
            String[] array = pathname.split("/");
            int length = array.length;
            if (length == 1) {
                File txtFile = new File(pathname);
                txtFile.createNewFile();
            } else {
                String temp = "";
                for (int x = 0; x < length; x++) {
                    if (x == 0) {
                        temp += array[x];
                        File dataFile = new File(temp);
                        dataFile.mkdir();
                    } else {
                        temp += "/" + array[x];
                        File dataFile = new File(temp);
                        if (x == length - 1) {
                            dataFile.createNewFile();
                        } else {
                            dataFile.mkdir();
                        }
                    }
                }
            }
            fw = new FileWriter(pathname, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fw;
    }

    /**
     * Edits the text file using the file writer.
     * @param s message to be appended
     * @throws IOException should it occur
     */
    public void editStorage(String s) throws IOException {
        fw.write(s);
        fw.close();
    }
}
