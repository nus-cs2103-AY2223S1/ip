package duke;

/**
 * An exception with both developer-facing and user-facing messages.
 */
public class MessagefulException extends Exception {
    private final String hint;

    /**
     * Constructor
     *
     * @param message Developer-facing message. Example: "Missing name".
     * @param hint    User-facing message. Example: "Please enter a name".
     */
    public MessagefulException(String message, String hint) {
        super(message);
        this.hint = hint;
    }

    /**
     * Retrieves the user-facing message.
     *
     * @return The user-facing message.
     */
    public String getHint() {
        return this.hint;
    }
}
