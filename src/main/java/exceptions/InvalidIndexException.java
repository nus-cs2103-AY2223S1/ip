package exceptions;

/**
 * An exception that indicates the index of a task is invalid.
 */
public class InvalidIndexException extends ByuException {

    @Override
    public String getMessage() {
        return "The task does not exist!";
    }

}
