package anya;

/**
 * Represents exception that can be found in Anya ChatBot.
 */

public class AnyaException extends Exception {

    private String message;

    /**
     * Construct an exception for Anya.
     */
    public AnyaException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}

