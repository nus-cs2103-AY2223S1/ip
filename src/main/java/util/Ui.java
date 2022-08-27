package util;

/**
 * A collection of all Ui related utilities
 *
 * @author Bryan Lim Jing Xiang
 */
public class Ui {
    /**
     * Formats a single line of text
     *
     * @param input String to be formatted
     * @return Formatted string to be printed
     */
    public static String formatLine(String input) {
        return "\t " + input + "\n";
    }

    /**
     * Adds Ui elements to prettify a block or paragraph of text
     *
     * @param paragraph Paragraph of formatted text to be prettified
     * @return Prettified block of text to be printed
     */
    public static String formatParagraph(String paragraph) {
        String divider = "\t____________________________________________________________\n";
        return divider + paragraph + divider;
    }

    /**
     * Formats and prettifies lines of text to be printed
     *
     * @param lines Lines of text to be formatted and prettified
     * @return Prettified block of text to be printed
     */
    public static String formatLinesIntoParagraph(String... lines) {
        String res = "";
        for (String line : lines) {
            res += formatLine(line);
        }

        return formatParagraph(res);
    }

    /**
     * Prints the introduction message when starting the app
     */
    public static void printIntroMessage() {
        String introParagraph = Ui.formatLine("Hello! I'm duke.") +
                Ui.formatLine("What can I do for you?");
        String formattedIntro = Ui.formatParagraph(introParagraph);

        System.out.println(formattedIntro);
    }
}
