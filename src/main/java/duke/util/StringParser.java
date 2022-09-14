package duke.util;

/**
 * Static class used solely to format strings
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
        return "\t" + txt.replaceAll("\n", "\n\t");
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
}
