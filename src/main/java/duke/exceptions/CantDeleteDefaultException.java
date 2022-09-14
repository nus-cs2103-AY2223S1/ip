package duke.exceptions;

/**
 * Exception thrown when trying to delete a default command
 */
public class CantDeleteDefaultException extends DukeException {
    private static final String DESCRIPTION = "You cannot delete %s!";

    public CantDeleteDefaultException(String name) {
        super(String.format(DESCRIPTION, name));
    }
}
