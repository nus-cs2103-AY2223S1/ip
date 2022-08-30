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
     * The message to print when Duke is stopped.
     */
    public static String outro() {
        return "Bye. Hope to see you again soon!";
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
    public abstract boolean isExit();
}
