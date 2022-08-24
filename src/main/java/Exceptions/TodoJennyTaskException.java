package Exceptions;

/**
 * A TodoJennyTaskException to represent exceptions thrown in the TodoJennyTask class.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TodoJennyTaskException extends IllegalArgumentException {
    /**
     * Constructor of a TodoJennyTaskException.
     * @param errorMessage error message of the exception.
     */
    public TodoJennyTaskException(String errorMessage) {
        super(errorMessage);
    }
}
