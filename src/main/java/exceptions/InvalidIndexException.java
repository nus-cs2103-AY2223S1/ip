package exceptions;

/**
 * An exception that indicates the index of a task is invalid.
 */
public class InvalidIndexException extends DukeException {

    @Override
    public String getMessage() {
        return "The task does not exist!";
    }

}
