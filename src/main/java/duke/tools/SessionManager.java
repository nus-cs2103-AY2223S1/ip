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
    }

    /**
     * Ends the duke session.
     */
    public static void stopSession() {
        int endDuration = 3000;
        // Code reuse from stackoverflow
        // https://stackoverflow.com/questions/2258066/java-run-a-function-after-a-specific-number-of-seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        javafx.application.Platform.exit();
                        System.exit(0);
                    }
                },
                endDuration
        );
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
