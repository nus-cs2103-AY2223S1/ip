package iana.exception;

/**
 * Exception thrown by the program.
 */
public class IanaException extends Exception {

    /**
     * Constructor for IanaException.
     * @param errorMessage message that program will display if exception is thrown.
     */
    public IanaException(String errorMessage) {
        super(errorMessage);
    }
}