package Exceptions;

/**
 * A DeadlineTaskException to represent exceptions thrown in the DeadlineTask class.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class DeadlineTaskException extends IllegalArgumentException {
    /**
     * Constructor of a DeadlineTaskException.
     * @param errorMessage error message of the exception.
     */
    public DeadlineTaskException(String errorMessage) {
        super(errorMessage);
    }
}
