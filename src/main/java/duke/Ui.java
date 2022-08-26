package duke;

import java.util.Scanner;

/**
 * Represents a <code>Ui</code> that handles user interaction.
 */
public class Ui {
    private static final String MESSAGE_WELCOME = "Hi there! Baymax at your service. Let me retrieve your stored task list!";
    private static final String MESSAGE_BYE = "Goodbye!";
    private static final String LINE = "__________________________________";
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prints a line separator.
     */
    public static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints a welcome message.
     */
    public static void printWelcome() {
        printLine();
        System.out.println(MESSAGE_WELCOME);
    }

    /**
     * Prints a goodbye message.
     */
    public static void printBye() {
        System.out.println(MESSAGE_BYE);
    }

    /**
     * Prints successful load message.
     */
    public static void printSuccessfulLoad() {
        System.out.println("Found it!");
        printLine();
    }

    /**
     * Prints failed load message.
     */
    public static void printFailedLoad() {
        System.out.println("Seems like you don't have a task list. I've initialized one for you!");
        printLine();
    }

    /**
     * Prints <code>Task</code> added into the <code>TaskList</code> message.
     *
     * @param task <code>Task</code> to be added.
     * @param size The number of tasks in the <code>TaskList</code> after this addition.
     */
    public static void printAddTask(Task task, int size) {
        System.out.println("Got it. I've added this task:\n " + task);
        printNumberOfTasks(size);
    }

    /**
     * Prints the number of tasks in the <code>TaskList</code>.
     *
     * @param size The number of tasks in the <code>TaskList</code>.
     */
    public static void printNumberOfTasks(int size) {
        if (size < 2) {
            System.out.println("Now you have " + size + " task in the list.");
        } else {
            System.out.println("Now you have " + size + " tasks in the list.");
        }
    }

    /**
     * Prints <code>Task</code> deleted from the <code>TaskList</code> message.
     *
     * @param task <code>Task</code> to be deleted.
     * @param size The number of tasks in the <code>TaskList</code> after this deletion.
     */
    public static void printDeleteTask(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n  " + task);
        printNumberOfTasks(size);
    }

    /**
     * Prints <code>Task</code> marked as done message.
     *
     * @param task <code>Task</code> to be marked as done.
     */
    public static void printTaskMarkedDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints <code>Task</code> marked as not done message.
     *
     * @param task <code>Task</code> to be marked as not done.
     */
    public static void printTaskMarkedUndone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Prints error message.
     *
     * @param error Error message to be printed.
     */
    public static void printError(String error) {
        System.out.println(error);
    }

    /**
     * Prints enter command message.
     *
     * @return Command message inputted by the user.
     */
    public static String readCommand() {
        System.out.println("Please enter a command: ");
        return sc.nextLine();
    }

}
