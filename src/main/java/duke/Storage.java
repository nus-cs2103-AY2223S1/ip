package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
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

    private Optional<Scanner> getScannerForAFile(String filePath) {
        File tasksFile = new File(filePath);
        if (!tasksFile.exists()) {
            return Optional.empty();
        }
        try {
            return Optional.of(new Scanner(tasksFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Returns a {@code Optional&lt;Scanner&gt;} to allow the client to read the file containing the current commands.
     *
     * @return A {@code Optional&lt;Scanner&gt;}
     */
    public Optional<Scanner> getScannerForTasksFile() {
        return getScannerForAFile(Storage.FILE_PATH);
    }

    /**
     * Returns a {@code Optional&lt;Scanner&gt;} to allow the client to read the file containing the tasks before
     * the last command.
     *
     * @return A {@code Optional&lt;Scanner&gt;}
     */
    public Optional<Scanner> getScannerForPreviousTasksFile() {
        return getScannerForAFile(Storage.PREVIOUS_TASKS_FILE_PATH);
    }

    /**
     * Checks if the current tasks file exists.
     *
     * @return A {@code boolean}
     */
    public boolean isCurrentTasksFilePresent() {
        Path currentTasksFile = Paths.get(Storage.FILE_PATH);
        return Files.exists(currentTasksFile);
    }

    /**
     * Writes a {@code String} representation of the tasks to disk
     *
     * @param stringToWrite The {@code String} to write
     */
    public void writeToDisk(String stringToWrite) {
        Path currentTasksFile = Paths.get(Storage.FILE_PATH);
        Path previousTasksFile = Paths.get(Storage.PREVIOUS_TASKS_FILE_PATH);
        boolean hasTasksFile = Files.exists(currentTasksFile);
        try {
            saveToPreviousTasksFile(previousTasksFile, hasTasksFile);
            Files.writeString(currentTasksFile, stringToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToPreviousTasksFile(Path previousTasksFile, boolean hasTasksFile) throws IOException {
        if (hasTasksFile) {
            saveCurrentFileAsPreviousTasksFile();
        } else { // if it does not, create an empty file to prevent UNDO from throwing an Exception
            Files.writeString(previousTasksFile, "");
        }
    }

    private void saveCurrentFileAsPreviousTasksFile() throws IOException {
        Path currentTasksFilePath = Paths.get(Storage.FILE_PATH);
        Path previousTasksFilePath = Paths.get(Storage.PREVIOUS_TASKS_FILE_PATH);
        Files.move(currentTasksFilePath, previousTasksFilePath, StandardCopyOption.REPLACE_EXISTING);
    }
}
