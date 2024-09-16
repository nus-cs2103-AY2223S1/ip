package duke;

/**
 * Represents an exception in the duke.Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructor for a duke.Duke Exception.
     *
     * @param errorMessage Error Message for the duke.Duke Exception.
     */
    public DukeException (String errorMessage) {
        super (errorMessage);
    }
}
