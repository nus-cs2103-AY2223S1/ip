package duke.storage;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Handles the creating, loading and saving of tasks.
 *
 * @author Kang Wei
 */
public class Storage {

    /**
     * Stores the path of the .txt file to store
     * a user's tasks.
     */
    private String filePath;

    /**
     * Stores a user's list of tasks.
     */
    private TaskList tasks;

    /**
     * Initialises a Storage object.
     *
     * @param filePath The path of the .txt file to store a
     * user's tasks.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;

        // Create the .txt file if it hasn't already been created.
        CreateFile.makeFile(filePath);

        /**
         * Load the .txt file, and get the list of tasks if the .txt
         * file contains any data.
         */
        tasks = LoadFile.load(filePath);
    }

    /**
     * Gets the current tasks stored in this Storage object.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Saves the current tasks to a .txt file, and store it
     * in filePath.
     */
    public void save() throws DukeException {
        SaveFile.save(tasks, filePath);
    }
}
