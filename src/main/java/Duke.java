import java.util.Scanner;

public class Duke {
    private static final String LINE_DIVIDER = "    ____________________________________________________________\n";
    private static final String INDENTATION = "     ";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static void main(String[] args) {
        greetUser();
        runDuke();
    }
    public static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "bye":
                    exitDuke();
                    return;
                default:
                    System.out.println(formatStringWithLineDivider(INDENTATION + input));
            }
        }
    }

    public static String formatStringWithLineDivider(String string) {
        return LINE_DIVIDER + string + "\n" + LINE_DIVIDER;
    }
    public static void greetUser() {
        System.out.println(LOGO);
        System.out.println(formatStringWithLineDivider(INDENTATION + "Hello! I'm Duke\n" + INDENTATION + "What can I do for you?"));
    }

    public static void exitDuke() {
        System.out.println(formatStringWithLineDivider(INDENTATION + "Bye. Hope to see you again soon!"));
    }
}
