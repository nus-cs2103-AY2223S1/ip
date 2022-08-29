package duke;

/**
 * Message to be displayed on screen with a user and exit status.
 */
public class Message {
    public final String message;
    public final boolean shouldExit;
    public final User user;

    /**
     * Represents the list of valid users.
     */
    public enum User {
        USER,
        DUKE
    }

    /**
     * Creates a message with the given text, exit status, and user.
     * @param message message to be displayed
     * @param shouldExit whether the program should exit
     * @param user user who sent the message
     */
    public Message(String message, boolean shouldExit, User user) {
        this.message = message;
        this.shouldExit = shouldExit;
        this.user = user;
    }
}
