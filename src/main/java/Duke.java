import java.util.Scanner;

public class Duke {
    // For adding some colour
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void printWithIndent(String toPrint) {
        System.out.println("\t" + toPrint.replace("\n", "\n\t"));
    }

    public static void printLine() {
        printWithIndent("____________________________________________________________");
    }

    public static void greet() {
        printLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printWithIndent("Hello from\n" + ANSI_CYAN + logo + ANSI_RESET);
        printWithIndent("How can I help you today?");
        printLine();
    }

    public static void echo(String input) {
        printLine();
        printWithIndent(input);
        printLine();
    }

    public static boolean isExit(String input) {
        return input.equals("bye");
    }

    public static void exit() {
        printLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printLine();
    }
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().strip();
            if (isExit(input)) {
                exit();
                break;
            } else {
                echo(input);
            }
        }
    }
}
