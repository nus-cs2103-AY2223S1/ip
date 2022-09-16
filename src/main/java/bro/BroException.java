package bro;

/**
 * Gets the message to be printed from the super class for exceptions.
 */
public class BroException extends Exception {
    public BroException(String message) {
        super(message);
    }
}
