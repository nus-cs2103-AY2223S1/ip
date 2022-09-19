package duke.exceptions;

/**
 * Parent class of all Duke related class.
 */
public abstract class DukeException extends Exception {

    /**
     * Creates a duke exception
     *
     * @param errorMsg Message about the error.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
