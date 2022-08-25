package duke.exception;

/**
 * Exception to handle invalid commands or arguments for program Duke
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     * @param msg Error message
     */
    public DukeException(String msg) {
        super("☹ OOPS!!! " + msg);
    }
}
