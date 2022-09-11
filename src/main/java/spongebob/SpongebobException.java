package spongebob;

/**
 * Represents an exception specific to the spongebob program.
 */
public class SpongebobException extends Exception {
    /**
     * Returns an instance of SpongebobException.
     *
     * @param msg Error message.
     */
    public SpongebobException(String msg) {
        super(msg);
    }
}
