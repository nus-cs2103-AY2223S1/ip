package exceptions;

/**
 * An exception that indicates a task already exists.
 */
public class DuplicateException extends DukeException {

    @Override
    public String getMessage() {
        return "The task already exists!";
    }
}
