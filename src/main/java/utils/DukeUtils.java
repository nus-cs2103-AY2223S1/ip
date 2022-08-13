package utils;

public class DukeUtils {

    public static void print(String message) {
        System.out.println(message);
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
