package Duke.store;

import Duke.exceptions.FileFailedToLoadException;
import Duke.task.Task;
import Duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The {@code Storage} class enables users to store tasks in a file.
 * This file enables saving of tasks to ensure users do not need to
 * recreate all tasks during every run of duke.
 */
public class Storage {

    private final Scanner scanner; // Scanner that reads the tasks from a text file.
    private final String filepath; // Path name of file that tasks are stored in.

    /**
     * Constructor for a duke class.
     *
     * @param path String containing filepath to store tasks in.
     */
    public Storage(String path) throws FileFailedToLoadException, IOException {
        filepath = path;
        File file = new File(filepath);
        createFileIfFileDoesNotExist(file);
        scanner = new Scanner(file);
    }

    private void createFileIfFileDoesNotExist(File file) throws IOException, FileFailedToLoadException {
        if (file.exists()) {
            return;
        }
        boolean isMakeDirectoryUnsuccessful = file.getParentFile().mkdirs();
        boolean isFileCreatedUnsuccessful = file.createNewFile();
        if (isFileCreatedUnsuccessful || isMakeDirectoryUnsuccessful) {
            throw new FileFailedToLoadException();
        }
    }

    /**
     * Return a scanner that outputs content in the saved file.
     */
    public Scanner load() {
        return scanner;
    }

    /**
     * Stores all tasks in the taskList into a text file.
     */
    public void storeTask(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filepath);
        StringBuilder input = new StringBuilder();
        for (Object o : taskList.getTasks()) {
            Task task = (Task) o;
            input.append(task.toStorageString());
        }
        fileWriter.write(input.toString());
        fileWriter.close();
    }
}
