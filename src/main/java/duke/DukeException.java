package duke;

/**
 * Represents the Duke Exception class to deal with exceptions.
 *
 * @author Denzel Tan
 */
public class DukeException extends Exception {
    /**
     * Constructor for the duke.DukeException exception.
     *
     * @param errorMessage the message to show for the current exception
     */
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
