package duke.response;

import duke.DukeException;

/**
 * A response by Duke.
 */
public abstract class DukeResponse {
    /**
     * The message to print when Duke is started.
     */
    public static String intro() {
        return "Hello! I'm Duke!\n" + "What can I do for you?";
    }

    /**
     * The action to do when the response is run.
     *
     * @throws DukeException If there was an error while running.
     */
    public abstract String run() throws DukeException;

    /**
     * Checks if the response is to tell Duke to exit.
     *
     * @return A boolean.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Returns a boolean indicating if the response has modified the task list.
     *
     * @return A boolean.
     */
    public boolean hasModifiedList() {
        return false;
    }
}
