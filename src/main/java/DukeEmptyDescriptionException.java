/**
 * A DukeEmptyDescriptionException is thrown if the user tries to add a task without giving it either a name or
 * description.
 */
public class DukeEmptyDescriptionException extends DukeException {

    /**
     * Public constructor for a DukeEmptyDescriptionException.
     *
     * @param message the message to be printed when a DukeEmptyDescriptionException is thrown and caught.
     */
    public DukeEmptyDescriptionException(String message) {
        super(message);
    }
}
