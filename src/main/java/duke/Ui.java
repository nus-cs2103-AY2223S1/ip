package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Class interacting with user input and printing output.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class Ui {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    private Scanner scanner;

    /**
     * Creates ui object to handle input and output.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints Duke logo and greeting message.
     */
    public void printGreeting() {
        System.out.println(LOGO);
        System.out.println(GREETING);
    }

    /**
     * Returns a string read from input.
     *
     * @return A line of user input.
     */
    public String read() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner for reading input.
     */
    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    /**
     * Prints a message with the specified list of tasks.
     *
     * @param list A list of tasks in string.
     */
    public void printList(String list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    /**
     * Prints a message with the size of specified list.
     *
     * @param size The size of the list.
     */
    public void printSizeOfList(int size) {
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Prints a message of marking of specified task as complete.
     *
     * @param task The task that is marked as complete.
     */
    public void printMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        printTask(task);
    }

    /**
     * Prints a message of marking of specified task as incomplete.
     *
     * @param task The task that is marked as incomplete.
     */
    public void printUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        printTask(task);
    }

    /**
     * Prints a message of deleting specified task.
     *
     * @param task The task that is deleted.
     */
    public void printDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:");
        printTask(task);
    }

    /**
     * Prints a message of adding specified task.
     *
     * @param task The task that is added.
     */
    public void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:");
        printTask(task);
    }

    /**
     * Prints a specified error message.
     *
     * @param message The error message
     */
    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printTask(Task task) {
        System.out.println(task);
    }
}
