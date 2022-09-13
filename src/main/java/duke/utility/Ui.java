package duke.utility;
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
     *
     * @return String representation of command given in the scanner
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
     * Represents the bye text in String format
     *
     * @return String representation of the bye text
     */
    public String bye() {
         return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Represents list of tasks in String format that will be printed
     * to the user
     *
     * @param tasks String representation of tasks
     * @return String representation of the lists of tasks
     */
    public String list(String tasks) {
        String output = "Here are the tasks in your list:\n";
        output += tasks;
        return output;
    }

    /**
     * Represents the text for adding tasks in String format that will be printed
     * to the user
     *
     * @param message String representation on the task added
     * @param numOfTasks number of tasks in the TaskList
     * @return String representation for the adding of tasks
     */
    public String add(int numOfTasks, String message) {
        String output = "Got it. I've added this task:\n";
        output += message;
        output += String.format("Now you have %d tasks in the list.\n", numOfTasks);
        return output;
    }

    /**
     * Represents the item that is marked in String format that
     * will be printed to the user
     *
     * @param message String representation of the item marked
     * @return String representation for the marking of tasks
     */
    public String mark(String message) {
        String output = "Nice! I've marked this task as done:\n";
        output += message;
        return output;
    }

    /**
     * Represents the item that is unmark in String format that
     * will be printed to the user
     *
     * @param message String representation of the item unmarked
     * @return String representation for the unmarking of tasks
     */
    public String unmark(String message) {
        String output = "OK, I've marked this task as not done yet:\n";
        output += message;
        return output;
    }

    /**
     * Represents the item that is deleted in String format that
     * will be printed to the user
     *
     * @param message String representation on the task deleted
     * @param numOfTask number of tasks in the TaskList
     * @return String representation for the deleting of tasks
     */
    public String delete(int numOfTask, String message) {
        String output = "Noted. I've removed this task:\n";
        output += message;
        output += String.format("Now you have %d tasks in the list.\n", numOfTask);
        return output;
    }

    /**
     * Represents the item that is found using the keyword in String format that
     * will be printed to the user
     *
     * @param tasks tasks that is found using the keyword in String format
     * @return String representation for the tasks found using the keyword
     */
    public String find(String tasks) {
        String output = "Here are the matching tasks in your list:\n";
        output += tasks;
        return output;
    }

    /**
     * Represents the error message String format that
     * will be printed to the user
     *
     * @param message error messages
     * @return String representation for the error message to be printed to user
     */
    public String showLoadingError(String message) {
        switch (message) {
        case "empty command":
        return "OOPS!!! The description cannot be empty.\n";

        case "invalid command":
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";

        case "empty command mark":
        return "OOPS!!! The description of mark cannot be empty.\n";

        case "invalid command mark":
        return "OOPS!!! It must be in the format of: mark <position in list>\n";

        case "empty command unmark":
        return " OOPS!!! The description of unmark cannot be empty.\n";

        case "invalid command unmark":
        return "OOPS!!! It must be in the format of: unmark <position in list>\n";

        case "empty command delete":
        return "OOPS!!! The description of delete cannot be empty.\n";

        case "invalid command delete":
        return "OOPS!!! It must be in the format of: delete <position in list>\n";

        case "empty todo":
        return "OOPS!!! The description of todo cannot be empty.\n";

        case "empty deadline":
        return "OOPS!!! The description of deadline cannot be empty.\n";

        case "invalid command deadline":
        return "OOPS!!! It must be in the format of: deadline <desciption> /by <yyyy-mm-dd HH:MM>\n";

        case "empty event":
        return "OOPS!!! The description of event cannot be empty.\n";

        case "invalid command event":
        return "OOPS!!! It must be in the format of: event <desciption> /at <yyyy-mm-dd HH:MM>\n";

        case "empty taskslist":
        return "Tasklist is empty\n";

        case "empty command find":
        return "OOPS!!! The description of find cannot be empty.\n";
        }
        return "";
    }
}
