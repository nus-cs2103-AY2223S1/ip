package duke.exceptions;

/**
 * No command found exception.
 */
public class NoCommandException extends DukeException {

    /**
     * No command found exception constructor.
     */
    public NoCommandException(String errorString) {
        super(errorString);
    }
}
