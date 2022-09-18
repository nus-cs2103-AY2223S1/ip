package duke;

/**
 * Represents the errors encountered by the Duke application
 * during the running of the program.
 */
public class DukeException extends Exception {

    /**
     * Constructs a Duke Exception object.
     *
     * @param s error message.
     */
    public DukeException(String s) {
        super(s);
    }
}
