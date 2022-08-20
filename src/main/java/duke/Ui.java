package duke;

import java.util.Scanner;

/**
 * Represents a user interface that deals with interaction with users.
 *
 * @author: Jonas Png
 */
public class Ui {

    private Scanner scanner;

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final String line = "_______________________________";

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
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    /**
     * Handles line showing.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Handles user interface for loading error.
     */
    public void showLoadingError() {
        String temp = line + "Storage file not found at specified path!" + line;
        System.out.println(temp);
    }

    /**
     * Handles user interface for when adding new tasks.
     */
    public void showAddedTask(Task itemToAdd, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(itemToAdd);
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list");
    }

    /**
     * Handles user interface for deleting tasks.
     */
    public void showDeletedTask(Task itemToRemove, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(itemToRemove);
        System.out.println("Now you have " + tasks.getLength() + " tasks in the list");
    }

    /**
     * Handles user interface for marking task as done.
     */
    public void showMarkedTask(Task markedTask) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(markedTask);
    }

    /**
     * Handles user interface for marking task as not done.
     */
    public void showUnmarkedTask(Task unmarkedTask) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(unmarkedTask);
    }

    /**
     * Prints out all existing tasks in list.
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
        System.out.println(line);
    }

    /**
     * Handles user interface fro reading user commands.
     */
    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    /**
     * Handles user interface for errors.
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
    }
}
