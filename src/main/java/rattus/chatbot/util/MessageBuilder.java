package rattus.chatbot.util;

/**
 * A MessageConstructor class to construct messages from lines of strings.
 */
public class MessageBuilder {
    private final StringBuilder stringBuilder = new StringBuilder();

    /**
     * Builds a line with a list of strings and appends to the stringBuilder.
     *
     * @param strings A list of strings to build a line with.
     */
    public void buildLine(String... strings) {
        if (stringBuilder.length() > 0) {
            stringBuilder.append("\n");
        }
        for (String str : strings) {
            stringBuilder.append(str);
        }
    }

    /**
     * Builds a line and appends to the stringBuilder.
     *
     * @param line A string line to append to stringBuilder.
     */
    public void buildLine(String line) {
        if (stringBuilder.length() > 0) {
            stringBuilder.append("\n");
        }
        stringBuilder.append(line);
    }

    /**
     * Appends lines to the stringBuilder, with each line on a new line.
     *
     * @param lines A list of strings to be appended.
     */
    public void buildLines(String... lines) {
        for (String line : lines) {
            buildLine(line);
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
