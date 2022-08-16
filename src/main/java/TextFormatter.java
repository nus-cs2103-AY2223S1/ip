public class TextFormatter {
    public static String formatLine(String input) {
        return "\t " + input + "\n";
    }

    public static String formatParagraph(String paragraph) {
        String divider = "\t____________________________________________________________\n";
        return divider + paragraph + divider;
    }
}
