package zeus;

/**
 * A <code>RuntimeException</code> thrown by Duke due to invalid input given by user.
 *
 * @author Derrick Khoo
 */
public class ZeusException extends Exception {
    private String message;

    /**
     * Constructs a new exception which is thrown by Duke with the message intended.
     *
     * @param message the message to be shown to the user
     */
    public ZeusException(String message) {
        this.message = message;
    }

    /**
     * The string representation of this exception.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return this.message;
    }
}
