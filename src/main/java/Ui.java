import java.util.Scanner;

public class Ui {
    // For adding some colour
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";

    private Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine().strip();
    }
    public void printWithIndent(String toPrint) {
        System.out.println("\t" + toPrint.replace("\n", "\n\t"));
    }

    public void printLine() {
        printWithIndent("____________________________________________________________");
    }

    public void printError(String error) {
        printWithIndent(ANSI_RED + "☹ Oh no! " + error + ANSI_RESET);
    }

    public void printTaskCount(int taskCount) {
        printWithIndent(String.format("Now you have %d tasks in the list.", taskCount));
    }

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
