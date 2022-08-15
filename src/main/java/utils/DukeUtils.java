package utils;

public class DukeUtils {

    public static int wordCount(String input) {
        return input.trim().split("\\s+").length;
    }

    public static boolean isNumeric(String input) {
        return input.chars().allMatch(Character::isDigit);
    }

    public static void printWithIndent(String message) {
        System.out.println("\t" + message);
    }

    public static void printMessage(String message) {
        printLine();
        printWithIndent(message);
        printLine();
    }

    public static void printMessages(String... messages) {
        printLine();
        for (String m : messages) {
            printWithIndent(m);
        }
        printLine();
    }

    public static void printLine() {
        System.out.println("\t────────────────────────────────────────────────────────────");
    }
}
