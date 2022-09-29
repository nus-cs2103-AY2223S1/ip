package duke;

/**
 * DukeException is a custom exception class.
 * It formats the error message passed in with "☹ OOPS!!!" as header.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the DukeException class.
     *
     * @param message the error message
     */
    public DukeException(String message) {
        super(Output.wrapper("☹ OOPS!!! " + message + "\n"));
    }
}
