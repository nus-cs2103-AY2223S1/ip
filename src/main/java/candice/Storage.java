package candice;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Abstraction for information stored locally for future usage.
 */
public class Storage {
    /** Path to the file used for storage of information */
    private final Path storagePath;

    /**
     * Constructor for a Storage object that encapsulates the path to the storage file.
     *
     * @param storagePath The path to the storage file.
     */
    public Storage(Path storagePath) {
        this.storagePath = storagePath;
    }

    public Path getPath() {
        return this.storagePath;
    }

    /**
     * Adds a task to the storage file.
     *
     * @param taskDescription The String representation of a task to be added to the file.
     */
    public void addTask(String taskDescription) {
        try {
            Files.writeString(storagePath, taskDescription, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Updates the storage text to match the Strings inputted.
     *
     * @param taskDescriptionArray An array of String representation of the tasks used to update the storage file.
     */
    public void update(String[] taskDescriptionArray) {
        StringBuilder newTaskListText = new StringBuilder();

        for (String task: taskDescriptionArray) {
            newTaskListText.append(task);
        }
        try {
            Files.writeString(this.storagePath, newTaskListText.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
