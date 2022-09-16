package duke.exceptions;

public class DukeException extends Exception {

    /**
     * Handles Duke exceptions.
     * @param errorMessage Error Message to be called.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
