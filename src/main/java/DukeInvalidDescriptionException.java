/**
 * A DukeInvalidDescriptionException is thrown if the user tries to add a task without giving it either a name or
 * description, or if no due date or time is given for Events and Deadlines.
 */
public class DukeInvalidDescriptionException extends DukeException {

    /**
     * Public constructor for a DukeEmptyDescriptionException.
     *
     * @param message the message to be printed when a DukeEmptyDescriptionException is thrown and caught.
     */
    public DukeInvalidDescriptionException(String message) {
        super(message);
    }
}
