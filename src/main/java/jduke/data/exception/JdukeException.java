package jduke.data.exception;

/**
 * Signals an exception in the program.
 */
public class JdukeException extends Exception {
    public JdukeException(String message) {
        super(String.format("%s", message));
    }
}
