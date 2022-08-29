package duke;

/**
 * Special Exception subclass for Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates new DukeException class.
     *
     * @param error Description of exception raised.
     */
    public DukeException(String error) {
        super(error);
    }
}
