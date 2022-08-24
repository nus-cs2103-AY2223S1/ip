package Exceptions;

/**
 * A DeadlineJennyTaskException to represent exceptions thrown in the DeadlineJennyTask class.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class DeadlineJennyTaskException extends IllegalArgumentException {
    /**
     * Constructor of a DeadlineJennyTaskException.
     * @param errorMessage error message of the exception.
     */
    public DeadlineJennyTaskException(String errorMessage) {
        super(errorMessage);
    }
}
