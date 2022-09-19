package pony;

import java.lang.RuntimeException;
public class PonyException extends RuntimeException {

    /**
     * Constructor for PonyException.
     *
     * @param message Message to be displayed by the exception is thrown.
     */
    public PonyException(String message) {
        super(message);
    }

}
