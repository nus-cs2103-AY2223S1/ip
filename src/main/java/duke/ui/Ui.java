package duke.ui;

import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner input = new Scanner(System.in);
    
    /**
     * Shows welcome message upon initialization.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
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
        System.out.println("\nERROR: " + errorMessage + "\n");
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
    public void showList(ArrayList<Task> list) {
        System.out.println();
        for (Task task : list) {
            System.out.println(task);
        }
        System.out.println();
    }
}
