package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage of the tasks on disk
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for {@code Storage}
     *
     * @param filePath The filepath on the local Unix or Unix-like system
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a {@code Scanner} to allow the client to read the file
     *
     * @return A {@code Scanner}
     * @throws FileNotFoundException if the file does not exist
     */
    public Scanner getScannerForTasksFile() throws FileNotFoundException {
        File tasksFile = new File(filePath);
        if (tasksFile.exists()) {
            return new Scanner(tasksFile);
        }
        throw new FileNotFoundException();
    }

    /**
     * Writes a {@code String} representation of the tasks to disk
     * @param stringToWrite The {@code String} to write
     */
    public void writeToDisk(String stringToWrite) {
        try {
            File tasksFile = new File(filePath);
            if ((tasksFile.exists() && tasksFile.delete()) || !tasksFile.exists()) {
                FileWriter fileWriter = new FileWriter(filePath);
                try {
                    fileWriter.write(stringToWrite);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileWriter.close();
            } else {
                System.out.println("Unable to save your data");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
