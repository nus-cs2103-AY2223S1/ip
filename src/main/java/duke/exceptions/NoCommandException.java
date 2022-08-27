package duke.exceptions;

/**
 * No Command Exception Class
 */
public class NoCommandException extends DukeException {

    /**
     * No command found exception constructor.
     */
    public NoCommandException(String errorString) {
        super(errorString);
    }
}
