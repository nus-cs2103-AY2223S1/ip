package raiden;

/**
 * Represents Exceptions that arise from Raiden's features.
 */
public class RaidenException extends Exception {
    /**
     * Creates RaidenException with the given message.
     *
     * @param message The message of the RaidenException.
     */
    public RaidenException(String message) {
        super(message);
    }

    /**
     * Returns the String representation of the RaidenException.
     *
     * @return The String representation of the RaidenException.
     */
    @Override
    public String toString() {
        return "ERROR\n" + super.getMessage();
    }
}
