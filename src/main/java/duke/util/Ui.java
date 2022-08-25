package duke.util;

public class Ui {
    private static final String HORIZONTAL_BAR = "-------------------------";
    private static final String INDENTATION = "    ";

    private static String formatOutput(String output) {
        return HORIZONTAL_BAR + '\n' + INDENTATION + output + '\n' + HORIZONTAL_BAR + '\n' + '\n';
    }

    public void printOutput(String output) {
        System.out.println(formatOutput(output));
    }
}
