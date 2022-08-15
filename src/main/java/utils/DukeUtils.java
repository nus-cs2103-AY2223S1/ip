package utils;

public class DukeUtils {

    public static void sendGreetings() {
        printMessages(Constants.MSG_GREETINGS);
    }

    public static void sendExit() {
        printMessages(Constants.MSG_EXIT);
    }

    public static void printWithIndent(String message) {
        System.out.println("\t" + message);
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
