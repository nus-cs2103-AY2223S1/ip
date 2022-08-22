package duke.util;

public class OutputFormatter {
    private static final String HORIZONTAL_BAR = "-------------------------";
    private static final String INDENTATION = "    ";

    public static String formatOutput(String output) {
        return HORIZONTAL_BAR + '\n' + INDENTATION + output + '\n' + HORIZONTAL_BAR + '\n' + '\n';
    }
}
