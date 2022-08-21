package duke;

/**
 * Represents Exceptions related to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException object with the given description.
     *
     * @param description description for the exception.
     */
    public DukeException(String description) {
        super(description);
    }
}
