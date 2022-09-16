package byu.exceptions;

/**
 * An exception that indicates a task already exists.
 */
public class DuplicateException extends ByuException {

    @Override
    public String getMessage() {
        return "The task already exists!";
    }
}
