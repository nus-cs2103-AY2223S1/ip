package dukeexceptions;

/**
 * A generic Exception class for Duke to be inherited by Exception subclasses.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param errorMessage Error message to print.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
