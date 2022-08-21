package duke;

import java.util.Scanner;

/**
 * Represents the user interface and handles all input and output.
 * Including styles like colours, indentation and lines.
 */
public class Ui {
    // For adding some colour
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";

    private Scanner scanner = new Scanner(System.in);

    /**
     * Takes in user input.
     * @return one line of user input.
     */
    public String readCommand() {
        return scanner.nextLine().strip();
    }

    /**
     * Print line with indentation.
     * If message spans multiple lines, they are indented appropriately,
     * @param toPrint String to print.
     */
    public void printWithIndent(String toPrint) {
        System.out.println("\t" + toPrint.replace("\n", "\n\t"));
    }

    /**
     * Prints an indented line.
     */
    public void printLine() {
        printWithIndent("____________________________________________________________");
    }

    /**
     * Prints error message in red with a sad face.
     * @param error String of the error message.
     */
    public void printError(String error) {
        printWithIndent(ANSI_RED + "☹ Oh no! " + error + ANSI_RESET);
    }

    /**
     * Prints "Now you have {taskCount} tasks in the list."
     * @param taskCount Number of tasks.
     */
    public void printTaskCount(int taskCount) {
        printWithIndent(String.format("Now you have %d tasks in the list.", taskCount));
    }

    /**
     * Greets the user with a coloured logo.
     */
    public void greet() {
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
}
