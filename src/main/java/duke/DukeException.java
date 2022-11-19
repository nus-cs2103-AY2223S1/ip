package duke;

/**
 * DukeException class which inherits from Exception.
 *
 * @author Kavan
 */
public class DukeException extends Exception {

    /**
     * Constructs a new Exception with the specified description.
     *
     * @param description Description of Exception.
     */
    public DukeException(String description) {
        super("☹ OOPS!!! " + description);
    }
}
