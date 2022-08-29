package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
/**
 * Represents the user interface class that
 * deals with interactions with the user.
 *
 * @author Leong Jia Hao Daniel
 */
public class Ui {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello, I'm\n" + logo + "How may I help you today?";

    private static final String FAREWELL = "Goodbye! Hope to see you again!";

    private Scanner scanner;

    /**
     * The constructor for the user interface class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out a straight line to organise the code.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints out the welcome message for Duke.
     */
    public void showWelcome() {
        showLine();
        formatMessage(GREETING);
        showLine();
    }

    /**
     * Formats the input message and returns it as a message from Duke.
     *
     * @param message The message that needs to be formatted.
     */
    public void formatMessage(String message) {
        System.out.println("Duke says:");
        System.out.println(message);
    }

    /**
     * The scanner that reads input from the terminal.
     *
     * @return The line that has been read from the terminal.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    /**
     * Prints the error message
     *
     * @param error The error that is caught.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints a custom message when the file is not able to load.
     */
    public void showLoadingError() {
        System.out.println("Error loading the file!");
    }

    /**
     * Prints the list of tasks that the user has.
     *
     * @param arrayList The list of tasks that the user has.
     */
    public void displayList(ArrayList<Task> arrayList) {
        int i = 1;
        String display = "Here are the tasks in your list: ";
        for (Task task : arrayList) {
            display += "\n" + i + ". " + task;
            i++;
        }
        formatMessage(display);
    }

    /**
     * Displays the list of tasks which matches the keyword.
     *
     * @param arrayList The arraylist which tracks the task.
     */
    public void displayMatchingList(ArrayList<Task> arrayList) {
        int i = 1;
        String display = "Here are the matching tasks in your list: ";
        for (Task task : arrayList) {
            display += "\n" + i + ". " + task;
            i++;
        }
        formatMessage(display);
    }
}
