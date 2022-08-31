package duke.ui;

import duke.task.Task;

import java.time.LocalDate;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /** Name of bot. */
    public static final String NAME = "Duke";

    /** Greeting message to welcome the user. */
    private final static String GREETING_MESSAGE = "Hello! I'm " + NAME + "\n"
            + "What can I do for you?\n";

    /** Bye message when user exit the program. */
    private final static String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";

    /** Print the welcome message. */
    public static void showWelcomeMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    /** Print the bye message. */
    public static void showByeMessage() {
        System.out.println(BYE_MESSAGE);
    }

    /** Print the error message. */
    public static void showError(Exception e) {
        System.out.println("The following error occur: " + e);
    }

    /** Print the reply when a new task is added. */
    public static void showAddTaskMessage(Task task, int size) {
        String reply = "Got it. I've added this task:\n" +
                task + "\nNow you have " + size + " tasks in the list.";
        System.out.println(reply);
    }

    /** Print the message when a task is deleted. */
    public static void showDeleteTaskMessage(Task task, int size) {
        String reply = "Noted. I've removed this task:\n" +
                task + "\nNow you have " + size + " tasks in the list.";
        System.out.println(reply);
    }

    /** Print the message when a task is marked. */
    public static void markTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /** Print the message when a task is marked. */
    public static void unMarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    /** Print the message when a date command is called. */
    public static void  showPrintTasksOnSpecificDateMessage(LocalDate date) {
        System.out.println("Here are the tasks on: " + date + "\n");
    }

    /** Print the message when a list command is called. */
    public static void showPrintListMessage() {
        System.out.println("Here are the tasks in your list:\n");
    }

    /** Print the line to separate outputs. */
    public static void showLine() {
        System.out.println("____________________________________________");
    }
}
