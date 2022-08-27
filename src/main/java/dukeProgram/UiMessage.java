package dukeProgram;

import java.util.Arrays;

/**
 * The UiMessage class records messages to be shown to the user at a later time.
 */
public class UiMessage {
    private final String[] messages;

    /**
     * Creates a new UiMessage with any amount of given message inputs
     * @param messages the given messages to record
     */
    public UiMessage(String... messages) {
        this.messages = Arrays.stream(messages).toArray(String[]::new);
    }

    /**
     * Retrieves the collection of recorded messages
     * @return an array of Strings that were recorded
     */
    public String[] getMessages() {
        return messages;
    }

    /**
     * Returns a string that separates each recorded message with a new line
     * @return messages separated with a newline between each message
     */
    @Override
    public String toString() {
        return String.join("\n", messages);
    }
}
