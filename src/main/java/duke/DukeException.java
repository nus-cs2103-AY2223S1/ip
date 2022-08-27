package duke;

/**
 * Class that handles Duke Exceptions
 */
public class DukeException extends Exception {
    /**
     * Constructor for Duke Exception
     * @param s custom error message when a Duke Exception is thrown
     */
    public DukeException(String s) {
        super("☹ OOPS!!! " + s);
    }
}
