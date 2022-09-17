package duke.util;

/**
 * Static class used solely to format and parse strings
 */
public final class StringParser {
    private static final String LINE = "   ______________________________";

    /**
     * Returns txt indented at every linebreak.
     *
     * @param txt Text to indent
     * @return String
     */
    public static String addIndent(String txt) {
        return "    " + txt.replaceAll("\n", "\n    ");
    }

    /**
     * Returns text wrapped between 2 lines.
     *
     * @param txt Text to wrapped between 2 lines
     * @return String
     */
    public static String addWrapper(String txt) {
        return String.format("%s%n%s%n%s%n", LINE, txt, LINE);
    }

    /**
     * Checks if string contains whitespace in between characters and start with letter
     *
     * @param txt Text to be validated
     * @return
     */
    public static boolean isAlphanumericVariableName(String txt) {
        String tested = txt.trim();
        return !tested.matches("[a-zA-Z][a-zA-Z0-9]*");
    }

    /**
     * Adds right padding to a text
     *
     * @param txt to be padded
     * @param padding padding size
     * @return formatted string
     */
    public static String rightPad(String txt, int padding) {
        return String.format("%-" + padding + "s", txt);
    }
}
