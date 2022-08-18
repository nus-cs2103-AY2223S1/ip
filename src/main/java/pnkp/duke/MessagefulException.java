package pnkp.duke;

/**
 * An exception with both developer-facing and user-facing messages.
 */
public class MessagefulException extends Exception {
    private String message;

    /**
     * Constructor
     * @param description Developer-facing message. Example: "Missing name".
     * @param message User-facing message. Example: "Please enter a name".
     */
    public MessagefulException(String description, String message) {
        super(description);
        this.message = message;
    }

    /**
     * Retrieves the user-facing message.
     * @return The user-facing message.
     */
    public String message() {
        return this.message;
    }
}
