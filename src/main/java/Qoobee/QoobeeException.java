package Qoobee;

/**
 * Represents an exception that would print an error given an invalid command.
 */
public class QoobeeException extends Exception {

    /**
     * Creates an exception to be thrown.
     * @param message The error message to be shown.
     */
    public QoobeeException(String message) {
        super(message);
    }

    /**
     * Returns the String representation of the exception.
     * @return The String representation of the exception.
     */
    @Override
    public String toString() {
        return "OOPS!!! " + super.getMessage();
    }
}
