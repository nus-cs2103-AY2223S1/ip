package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class that handles output to be shown to user.
 */
public class Ui {

    private final Scanner input = new Scanner(System.in);
    private static final String exitMessage = "Bye. Hope to see you again soon!";

    /**
     * Shows welcome message upon initialization.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showMessage("Hello from\n" + logo);
    }

    /**
     * Shows goodbye message upon exit.
     */
    public void showExit() {
        showMessage(exitMessage);
    }

    public static String getExit() {
        return exitMessage;
    }

    /**
     * Shows prompt for user input.
     */
    public void showPrompt() {
        System.out.print("> ");
    }

    /**
     * Reads in command and returns it.
     *
     * @return Full command read in by scanner.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Indicates to the user that an error has occurred.
     *
     * @param errorMessage Error message to be displayed to user.
     */
    public void showError(String errorMessage) {
        showMessage("ERROR: " + errorMessage);
    }

    /**
     * Error shown upon inability to load previous todo list.
     */
    public void showLoadingError() {
        showError("Error loading contents of data file");
    }

    /**
     * Shows user specified message.
     *
     * @param s Message to be shown to the user.
     */
    public void showMessage(String s) {
        System.out.println("\n" + s + "\n");
    }

    /**
     * Shows list of tasks to user.
     *
     * @param list List of tasks to be shown to the user
     */
    public String showList(ArrayList<Task> list) {
        StringBuilder toReturn = new StringBuilder();
        for (Task task : list) {
            toReturn.append(task).append("\n");
        }
        return toReturn.toString();
    }
}
