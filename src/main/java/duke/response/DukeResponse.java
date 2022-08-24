package duke.response;

import duke.DukeException;

/**
 * A response by Duke.
 */
public abstract class DukeResponse {
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * The message to print when Duke is started.
     */
    public static void intro() {
        String text = "Hello! I'm Duke!\n" + "What can I do for you?";
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    /**
     * The message to print when Duke is stopped.
     */
    public static void outro() {
        String text = "Bye. Hope to see you again soon!";
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    /**
     * Prints a properly formatted message.
     *
     * @param text The message to print.
     */
    public void message(String text) {
        System.out.println(DIVIDER + "\n" + text + "\n" + DIVIDER);
    }

    /**
     * The action to do when the response is run.
     *
     * @throws DukeException If there was an error while running.
     */
    public abstract void run() throws DukeException;

    /**
     * Checks if the response is to tell Duke to exit.
     *
     * @return A boolean.
     */
    public abstract boolean isExit();
}
