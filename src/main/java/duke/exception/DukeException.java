package duke.exception;

/**
 * Represents an error experienced when using Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates an instance of a Duke Exception
     *
     * @param str Error message
     */
    public DukeException(String str) {
        super(str);
    }
}

