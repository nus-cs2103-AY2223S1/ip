package duke;

/**
 * MultiLineFormatter class represents a formatter
 * which helps to handle the formatting of multiple
 * lines.
 */
public class MultiLineFormatter {
    private final StringBuilder fullMessage = new StringBuilder();

    /**
     * Adds the message to fullMessage.
     *
     * @param message The message to be added.
     */
    public void add(String message) {
        fullMessage.append(message);
    }

    /**
     * Returns the full message stored.
     *
     * @return The message that is stored in fullMessage.
     */
    public String getFullMessage() {
        return fullMessage.toString();
    }
}
