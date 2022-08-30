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

    private static final String GREETING = "Hello, I'm Duke! How may I help you today?\n"
            + "You can add a deadline, task or event and I will remember it for you!";

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
    public String showWelcome() {
        return formatMessage(GREETING);
    }

    /**
     * Formats the input message and returns it as a message from Duke.
     *
     * @param message The message that needs to be formatted.
     */
    public String formatMessage(String message) {
        String output = "Duke says:\n";
        return output + message;
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
    public String showError(Exception error) {
        return "Duke sighs:\n" + error.toString();
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
    public String displayList(ArrayList<Task> arrayList) {
        int i = 1;
        String display = "Here are the tasks in your list: ";
        for (Task task : arrayList) {
            display += "\n" + i + ". " + task;
            i++;
        }
        return formatMessage(display);

    }

    /**
     * Displays the list of tasks which matches the keyword.
     *
     * @param arrayList The arraylist which tracks the task.
     */
    public String displayMatchingList(ArrayList<Task> arrayList) {
        int i = 1;
        String display = "Here are the matching tasks in your list: ";
        for (Task task : arrayList) {
            display += "\n" + i + ". " + task;
            i++;
        }
        return formatMessage(display);
    }
}
