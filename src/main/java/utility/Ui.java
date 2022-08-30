package utility;
import java.util.Scanner;

/**
 * Represents class for user interface
 */
public class Ui {
    private static final String DIVIDER = "\t___________________________\n";
    private final Scanner sc;

    /**
     * Instantiates a new user interface
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome text
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Reads next line from the scanner
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        }
        return "";
    }

    /**
     * Prints text with DIVIDER
     *
     * @param output message to be printed
     */
    public void printWithDivider(String output) {
        System.out.println(DIVIDER);
        System.out.print(output);
        System.out.println(DIVIDER);
    }

    /**
     * Prints bye text
     */
    public void bye() {
        String output = "\tBye. Hope to see you again soon!\n";
        printWithDivider(output);
    }

    /**
     * Prints the list of tasks
     *
     * @param tasks String representation of tasks
     */
    public void list(String tasks) {
        String output = "\tHere are the tasks in your list:\n";
        output += tasks;
        printWithDivider(output);
    }

    /**
     * Prints the text for adding tasks
     *
     * @param message String representation on the task added
     * @param numOfTasks number of tasks in the TaskList
     */
    public void add(int numOfTasks, String message) {
        String output = "\tGot it. I've added this task:\n";
        output += message;
        output += String.format("\tNow you have %d tasks in the list.\n", numOfTasks);
        printWithDivider(output);
    }

    /**
     * Prints the item that is marked
     *
     * @param message String representation of the item marked
     */
    public void mark(String message) {
        String output = "\tNice! I've marked this task as done:\n";
        output += message;
        printWithDivider(output);
    }

    /**
     * Prints the item that is unmarked
     *
     * @param message String representation of the item unmarked
     */
    public void unmark(String message) {
        String output = "\tOK, I've marked this task as not done yet:\n";
        output += message;
        printWithDivider(output);
    }

    /**
     * Prints the text for deleting tasks
     *
     * @param message String representation on the task deleted
     * @param numOfTasks number of tasks in the TaskList
     */
    public void delete(int numOfTask, String message) {
        String output = "\tNoted. I've removed this task:\n";
        output += message;
        output += String.format("\tNow you have %d tasks in the list.\n", numOfTask);
        printWithDivider(output);
    }

    /**
     * Prints the error message
     *
     * @param message error type
     */
    public void showLoadingError(String message) {
        switch (message) {
            case "empty command":
            printWithDivider("\t☹ OOPS!!! The description cannot be empty.\n");
            break;

            case "invalid command":
            printWithDivider("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            break;
            
            case "empty command mark":
            printWithDivider("\t☹ OOPS!!! The description of mark cannot be empty.\n");
            break;

            case "invalid command mark":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: mark <position in list>\n");
            break;

            case "empty command unmark":
            printWithDivider("\t☹ OOPS!!! The description of unmark cannot be empty.\n");
            break;

            case "invalid command unmark":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: unmark <position in list>\n");
            break;

            case "empty command delete":
            printWithDivider("\t☹ OOPS!!! The description of delete cannot be empty.\n");
            break;

            case "invalid command delete":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: delete <position in list>\n");
            break;

            case "empty todo":
            printWithDivider("\t☹ OOPS!!! The description of todo cannot be empty.\n");
            break;

            case "empty deadline":
            printWithDivider("\t☹ OOPS!!! The description of deadline cannot be empty.\n");
            break;

            case "invalid command deadline":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: deadline <desciption> /by <yyyy-mm-dd HH:MM>\n");
            break;

            case "empty event":
            printWithDivider("\t☹ OOPS!!! The description of a event cannot be empty.\n");
            break;

            case "invalid command event":
            printWithDivider("\t☹ OOPS!!! It must be in the format of: event <desciption> /at <yyyy-mm-dd HH:MM>\n");
            break;

            case "empty taskslist":
            printWithDivider("\tTasklist is empty\n");
            break;

        }
    }
}
