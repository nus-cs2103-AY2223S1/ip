package duke;

/**
 * A representation for the UI the user will see.
 */
public class Responses {
    /**
     * The message to greet the user with
     */
    public static final String INITIAL_MESSAGE = " Hello! I'm Duke. What can I do for you?";
    /**
     * The message to exit with
     */
    public static final String BYE_MESSAGE = ("      Bye. This doesn't have to be the end!\n");

    /**
     * Returns an indented message.
     *
     * @param message The message to indent
     * @return A {@code String} representing the indented message
     */
    public static String indentedMessage(String message) {
        return "     " + message;
    }
}
