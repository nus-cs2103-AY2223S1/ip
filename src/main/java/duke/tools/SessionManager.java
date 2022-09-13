package duke.tools;

import java.util.ArrayList;

import duke.exceptions.DukeException;

/**
 * This class manages the objects created for the session.
 */
public class SessionManager {

    /** The tasks stored by the user. */
    private static TaskList taskList;
    /** The storage to manage data files. */
    private static Storage storage;

    /**
     * Private constructor to prevent object creation.
     */
    private SessionManager() {}

    /**
     * Initializes the SessionManager with the TaskList and Storage for the session.
     */
    public static void startSession() {
        storage = new Storage();
        try {
            taskList = storage.loadFromFile();
        } catch (DukeException e) {
            taskList = new TaskList(new ArrayList<>());
        }

        // Assertions to ensure that objects are created successfully.
        assert taskList != null;
        assert taskList.getStoredTasks() != null;
        assert storage != null;
        try {
            assert false;
            throw new Error("Warning: Assertions not enabled.");
        } catch (AssertionError e) {
            // Ensure that assert statements are working.
        }
    }

    /**
     * Retrieves the TaskList object for the session
     *
     * @return The TaskList object for the session
     */
    public static TaskList getTaskList() {
        return taskList;
    }

    /**
     * Retrieves the Storage object for the session
     *
     * @return The Storage object for the session
     */
    public static Storage getStorage() {
        return storage;
    }
}
