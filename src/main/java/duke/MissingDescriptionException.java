package duke;

/**
 * Exception thrown if missing name for tasks
 */
public class MissingDescriptionException extends DukeException {
    /**
     * Constructor for new instance
     */
    public MissingDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty.");
    }

    /**
     * Returns string representation of exception
     *
     * @return string for message
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a task cannot be empty.";
    }
}
