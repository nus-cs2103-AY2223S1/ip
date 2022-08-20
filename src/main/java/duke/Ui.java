package duke;

import java.util.Scanner;

/**
 * Represents a user interface that deals with interaction with users.
 */
public class Ui {

    private Scanner scanner;

    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final String LINE = "_______________________________";

    /**
     * Class constructor for Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Handles what to show during Duke's startup.
     */
    public void showWelcome() {
        showLine();
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    /**
     * Handles line showing.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Handles user interface for loading error.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("Storage file not found at specified path!");
        showLine();
    }

    /**
     * Handles user interface for when adding new tasks.
     *
     * @param taskToAdd task to be shown on ui.
     * @param tasks list of tasks to get number of existing tasks.
     */
    public void showAddedTask(Task taskToAdd, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskToAdd);
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list");
    }

    /**
     * Handles user interface for deleting tasks.
     *
     * @param taskToRemove task to be shown on ui.
     * @param tasks list of tasks to get number of existing tasks.
     */
    public void showDeletedTask(Task taskToRemove, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToRemove);
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list");
    }

    /**
     * Handles user interface for marking task as done.
     *
     * @param markedTask task to be shown on ui.
     */
    public void showMarkedTask(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markedTask);
    }

    /**
     * Handles user interface for marking task as not done.
     *
     * @param unmarkedTask task to be shown.
     */
    public void showUnmarkedTask(Task unmarkedTask) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkedTask);
    }

    /**
     * Prints out all existing tasks in list.
     *
     * @param tasks list of existing tasks.
     */
    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    /**
     * Handles user interface exiting program.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Handles user interface for reading user commands.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Handles user interface for errors.
     *
     * @param message message for ui to show.
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
    }

    /**
     * Handles user interface for showing matching tasks.
     *
     * @param tasks list of matching tasks to show.
     */
    public void showFilteredList(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks);
    }
}
