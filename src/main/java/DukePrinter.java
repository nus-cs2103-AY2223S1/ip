/**
 * Enables printing to screen display to be beautified according to the
 * line separator and tab formatter
 *
 * @author Emily Ong Hui Qi
 */
public class DukePrinter {
    // Horizontal line separator used in beautifying print commands
    private static final String SEPARATOR = "-";
    // Denotes the number of separator symbols to used when printing the input
    private static final int SEPARATOR_SIZE = 60;

    /**
     * Returns a string consisting of the separator symbol repeated for a specified number of times
     *
     * @return Separator symbol repeated for a specified number of times
     */
    private static String getSeparatorLine() {
        return DukePrinter.SEPARATOR.repeat(DukePrinter.SEPARATOR_SIZE);
    }

    public static void print(String input) {
        System.out.println(DukePrinter.getSeparatorLine());
        String[] inputs = input.split("\n");
        for (String value : inputs) {
            System.out.println("\t" + value);
        }
        System.out.println(DukePrinter.getSeparatorLine());
    }
}
