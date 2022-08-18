package Exceptions;

/**
 * A TodoTaskException to represent exceptions thrown in the TodoTask class.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TodoTaskException extends IllegalArgumentException {
    /**
     * Constructor of a TodoTaskException.
     * @param errorMessage error message of the exception.
     */
    public TodoTaskException(String errorMessage) {
        super(errorMessage);
    }
}
