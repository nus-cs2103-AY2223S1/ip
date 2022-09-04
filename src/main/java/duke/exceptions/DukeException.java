package duke.exceptions;

/**
 * Parent class of all Duke related class.
 */
public abstract class DukeException extends Exception {

    /**
     * Parent of all Duke related component
     * 
     * @param errorMsg Message about the error.
     */
    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
