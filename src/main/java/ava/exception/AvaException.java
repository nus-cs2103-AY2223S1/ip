package ava.exception;

public class AvaException extends Exception {
    /** Default DukeException constructor. */
    public AvaException() {
        super();
    }

    /**
     * DukeException constructor.
     *
     * @param message The error message.
     */
    public AvaException(String message) {
        super(message);
    }
}
