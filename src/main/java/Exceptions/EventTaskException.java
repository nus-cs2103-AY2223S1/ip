package Exceptions;

/**
 * A EventTaskException to represent exceptions thrown in the EventTask class.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class EventTaskException extends IllegalArgumentException {
    /**
     * Constructor of a EventTaskException.
     * @param errorMessage error message of the exception.
     */
    public EventTaskException(String errorMessage) {
        super(errorMessage);
    }
}
