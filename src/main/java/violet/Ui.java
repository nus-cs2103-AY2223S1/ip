package violet;

import java.util.Scanner;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________\n";
    private static final String BYE = "1.bye: exits the program\n";
    private static final String LIST = "2.List: Show the list using this format: list\n";
    private static final String FIND = "3.Find: Find a task using a keyword in this format: find <keyword>\n";
    private static final String PRIORITY = "4.Priority: Adds a priority, from low, medium and high using this format: "
            + "priority <index> <priority level>\n";
    private static final String MARK = "5.Mark: Indicate that a task is done using this format: mark <index>\n";
    private static final String UNMARK = "6.Unmark: Indicate that a task is not done using this format:"
            + " unmark <index>\n";
    private static final String DELETE = "7.Delete: Delete a task in the list using this format: delete <index>\n";
    private static final String TODO = "8.Todo: Set a todo task using this format: todo <task_description>\n";
    private static final String DEADLINE = "9.Deadline: Set a deadline task using this format: "
            + "deadline <task_description> /by yyyy-mm-ddT:Hours:Minutes:Seconds\n";
    private static final String EVENT = "10.Event: Set an event task using this format: "
            + "deadline <task_description> /at yyyy-mm-ddT:Hours:Minutes:Seconds\n";

    private static final String[] listOfCommands = new String[] {BYE, LIST, FIND, PRIORITY, MARK,
            UNMARK, DELETE, TODO, DEADLINE, EVENT };

    /** Scanner to read input from the user */
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the greeting message when Violet is opened.
     */
    public static String printGreeting() {
        String greetingMessage = "I will travel anywhere to meet your request. I am an auto memory doll."
                + " Violet Evergarden at your service.\n";
        return greetingMessage + "Here are some tasks that were left from before:\n";
    }

    /**
     * Prints the exit message when Violet is closed.
     */
    public static String exit() {
        return "Farewell. I hope to be in your service again.\n";
    }

    public static String showLine() {
        return LINE;
    }

    public static String showHelp() {
        String message = "Here are the list of commands I understand:\n";
        for (String s : listOfCommands) {
            message += s;
        }
        return message;
    }

    /**
     * Shows an error when loading a file.
     */
    public static void showLoadingError() {
        showLine();
        System.out.println("Error: Cannot load file!");
        showLine();
    }
}
