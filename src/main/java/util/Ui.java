package util;

public class Ui {
    public static String formatLine(String input) {
        return "\t " + input + "\n";
    }

    public static String formatParagraph(String paragraph) {
        String divider = "\t____________________________________________________________\n";
        return divider + paragraph + divider;
    }

    public static String formatLinesIntoParagraph(String... lines) {
        String res = "";
        for (String line : lines) {
            res += formatLine(line);
        }

        return formatParagraph(res);
    }

    public static void printIntroMessage() {
        String introParagraph = Ui.formatLine("Hello! I'm duke.")
                + Ui.formatLine("What can I do for you?");
        String formattedIntro = Ui.formatParagraph(introParagraph);

        System.out.println(formattedIntro);
    }
}
