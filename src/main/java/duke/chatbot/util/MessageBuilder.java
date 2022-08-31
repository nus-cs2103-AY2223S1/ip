package duke.chatbot.util;

/**
 * A MessageConstructor class to construct messages from lines of strings.
 */
public class MessageBuilder {
    /** A string builder to build messages */
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Appends lines to the stringBuilder, with each line on a new line.
     * @param lines A list of strings to be appended.
     */
    public void addLines(String ... lines) {
        for (String line : lines) {
            if (stringBuilder.length() == 0) {
                stringBuilder.append(line);
            } else {
                stringBuilder.append("\n" + line);
            }
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
