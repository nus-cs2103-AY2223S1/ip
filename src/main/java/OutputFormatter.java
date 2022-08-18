public class OutputFormatter {
    private static final String HORIZONTAL_BAR = "-------------------------";

    public static String formatOutput(String output) {
        return HORIZONTAL_BAR + '\n' + output + '\n' + HORIZONTAL_BAR + '\n' + '\n';
    }
}
