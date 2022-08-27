package duke;

/**
 * Represents an exception thrown when an error occurs during duke chatbot execution.
 */
public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }
}
