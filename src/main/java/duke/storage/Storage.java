package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Storage class.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Storage {

    /**
     * Represents the path name.
     */
    private final String pathName;

    /**
     * Represents the file writer.
     */
    private FileWriter fw;

    /**
     * Represents a constructor method for Storage.
     * @param s File path
     */
    public Storage(String s) {
        this.pathName = s;
    }

    /**
     * Loads the storage.
     * @return File writer which can be used to write message.
     */
    public FileWriter load() {
        try {
            String[] array = pathName.split("/");
            int length = array.length;
            if (length == 1) {
                this.createFile();
            } else {
                this.createPathAndFile(array, length);
            }
            fw = new FileWriter(pathName, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fw;
    }

    /**
     * Creates file with given pathName.
     * @throws IOException If pathName is invalid.
     */
    private void createFile() throws IOException {
        File txtFile = new File(pathName);
        txtFile.createNewFile();
    }

    /**
     * Creates directory and file with given pathName.
     * @param array Array of paths.
     * @param length Length of array.
     * @throws IOException If pathName is invalid.
     */
    private void createPathAndFile(String[] array, int length) throws IOException {
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

    /**
     * Edits the text file using the file writer.
     * @param s Message to be appended.
     * @throws IOException If pathName is invalid.
     */
    public void editStorage(String s) throws IOException {
        fw.write(s);
        fw.close();
    }
}
