package spongebob;

/**
 * Represents an exception specific to the Duke program.
 */
public class SpongebobException extends Exception {
    /**
     * Returns an instance of DukeException.
     *
     * @param msg Error message.
     */
    public SpongebobException(String msg) {
        super(msg);
    }
}
