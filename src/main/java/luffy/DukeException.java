package luffy;

/**
 * DukeException class to throw TaskList related exceptions.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException class.
     *
     * @param message Message to instantiate exception
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
