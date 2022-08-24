package Exceptions;

/**
 * A EventJennyTaskException to represent exceptions thrown in the EventJennyTask class.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class EventJennyTaskException extends IllegalArgumentException {
    /**
     * Constructor of a EventJennyTaskException.
     * @param errorMessage error message of the exception.
     */
    public EventJennyTaskException(String errorMessage) {
        super(errorMessage);
    }
}
