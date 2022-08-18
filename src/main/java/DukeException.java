/**
 * Encapsulates specific errors unique to Duke.
 *
 * @author Conrad
 */

public class DukeException extends Exception {

    /**
     * Constructor for creating a Duke-specific exception.
     *
     * @param msg Description of the error.
     */
    public DukeException(String msg) {
        super(msg);
    }

}
