package duke;

/**
 * DukeException is an exception that is thrown when user enter invalid input.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     * @param eMessage error message.
     */
    public DukeException(String eMessage) {
        super(eMessage);
    }
}
