package duke;

/**
 * {@code DukeException} Handles exceptions that are throw in Duke program
 */
public class DukeException extends Exception {

    /**
     * Constructor for {@code DukeException}
     * @param s the error message
     */
    public DukeException(String s) {
        super("  ☹ OOPS!!!\n  " + s);
    }
}
