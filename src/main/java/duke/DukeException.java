package duke;

/** Represents an exception specific to Duke. */
public class DukeException extends Exception {

    /**
     * Initialises the DukeException.
     *
     * @param errorMessage Error message to be printed out.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
