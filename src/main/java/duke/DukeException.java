package duke;

/**
 * A custom exception for any errors created by Duke
 */
public class DukeException extends Exception {

    /**
     * A constructor for the DukeException class
     *
     * @param errorMessage The error message to display
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
