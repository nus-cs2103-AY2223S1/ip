package jenny.exceptions;

/**
 * Represents runtime exceptions thrown in the JennyBot application
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class JennyException extends RuntimeException {
    private final String messageScope;

    /**
     * Creates an instance of the exception.
     *
     * @param messageScope where the message originated from.
     * @param errorMessage error message of the exception.
     */
    public JennyException(String messageScope, String errorMessage) {
        super(errorMessage);
        this.messageScope = messageScope;
    }

    /**
     * Returns the scope of where the exception was thrown.
     *
     * @return the scope of where the exception was thrown.
     */
    public String getThrownFrom() {
        return this.messageScope;
    }
}
