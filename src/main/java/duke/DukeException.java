package duke;

/**
 * Creates an exception that deals with the error in this specific program
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
