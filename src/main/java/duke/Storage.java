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
    /**
     * The path to the file containing the user data
     */
    public static final String FILE_PATH = "tasks.txt";
    /**
     * The path to the file containing the previous user's data
     */
    public static final String PREVIOUS_TASKS_FILE_PATH = "tasksOld.txt";
    /**
     * Returns a {@code Scanner} to allow the client to read the file
     *
     * @return A {@code Scanner}
     * @throws FileNotFoundException if the file does not exist
     */
    public Scanner getScannerForTasksFile() throws FileNotFoundException {
        File tasksFile = new File(Storage.FILE_PATH);
        if (tasksFile.exists()) {
            return new Scanner(tasksFile);
        }
        throw new FileNotFoundException();
    }

    /**
     * Writes a {@code String} representation of the tasks to disk
     *
     * @param stringToWrite The {@code String} to write
     */
    public void writeToDisk(String stringToWrite) {
        File tasksFile = new File(Storage.FILE_PATH);
        boolean hasTasksFile = tasksFile.exists();
        boolean isTasksFileSaved = hasTasksFile && tasksFile.delete();

        // rename

        if (isTasksFileSaved || !hasTasksFile) {
            try (FileWriter fileWriter = new FileWriter(Storage.FILE_PATH)) {
                fileWriter.write(stringToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unable to save your data");
        }
    }
}
