package duke.ui;

import java.time.LocalDate;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    /** Name of bot. */
    public static final String NAME = "Duke";

    /** Greeting message to welcome the user. */
    private static final String GREETING_MESSAGE = "Hello! I'm " + NAME + "\n"
            + "What can I do for you?\n";

    /** Bye message when user exit the program. */
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!\n";

    /** Print the welcome message. */
    public static String showWelcomeMessage() {
        return GREETING_MESSAGE;
    }

    /** Print the bye message. */
    public static String showByeMessage() {
        return BYE_MESSAGE;
    }

    /** Print the error message. */
    public static String showError(Exception e) {
        return e.toString();
    }

    /** Print the reply when a new task is added. */
    public static String showAddTaskMessage(Task task, int size) {
        String reply = "Got it. I've added this task:\n"
                + task + "\nNow you have " + size + " tasks in the list.";
        return reply;
    }

    /** Print the message when a task is deleted. */
    public static String showDeleteTaskMessage(Task task, int size) {
        String reply = "Noted. I've removed this task:\n"
                + task + "\nNow you have " + size + " tasks in the list.";
        return reply;
    }

    /** Print the message when a task is clone. */
    public static String showCloneTaskMessage(Task task, int size) {
        String reply = "Noted. I've cloned this task:\n"
                + task + "\nNow you have " + size + " tasks in the list.";
        return reply;
    }

    /** Print the message when a task is marked. */
    public static String markTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /** Print the message when a task is marked. */
    public static String unMarkTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /** Print the message when a date command is called. */
    public static String showTasksOnDateMessage(LocalDate date) {
        return "Here are the tasks on: " + date + "\n";
    }

    public static String showFindTaskMessage(String keyword) {
        return "Based on your keyword:" + keyword + " these are the tasks found:\n";
    }

    /** Print the message when a list command is called. */
    public static String showPrintListMessage() {
        return "Here are the tasks in your list:\n";
    }
}
