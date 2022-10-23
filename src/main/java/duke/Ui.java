package duke;

import java.util.Scanner;

/**
 * Interacts with the console of Duke users, displaying operations, status, and errors.
 */
public class Ui {
    private static final String MESSAGE_GREETING = "Good Day! ~ Duke at your service <3 ~" +
            "\n\nType 'help' to view the available commands O_O";
    private static final String MESSAGE_FAREWELL = "Have a nice day ~ Duke out ~";
    private static final String MESSAGE_HELP = "Commands:"
            + "\n\nKeyword Only Commands:"
            + "\n\t-help (You are currently looking at the help page)"
            + "\n\t-list (Shows outstanding tasks)"
            + "\n\t-bye (Close Duke application)"
            + "\n\nKeyword + Numerical Label(According to 'list') Commands:"
            + "\n\n\t-mark (Declare an existing task as complete)"
            + "\n\t-unmark (Declare an existing task as incomplete)"
            + "\n\t-delete (Delete an existing task)"
            + "\n\nKeyword + Body Commands:"
            + "\n#Use spacing between keyword and body of command"
            + "\n\n\t-todo (Create a task todo)"
            + "\n\t-deadline (Create a task to complete by a deadline using '/YYYYMMDD' format after body)"
            + "\n\t-event (Create a task to attend to on a specific timing using '/<timing>' after body)"
            + "\n\t-find (Displays all tasks that contain a given search term)";

    protected String getGreetingMessage() {
        return MESSAGE_GREETING;
    }

    protected String getFarewellMessage() {
        return MESSAGE_FAREWELL;
    }

    protected String getHelpMessage() {
        return MESSAGE_HELP;
    }

    protected String updateTask(Task updatedTask, String status) {
        return String.format("Marked task as %s.\n%s\n", status, updatedTask);
    }

    protected String addTaskConfirmation(Task task, int size) {
        return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in your list.", task, size);
    }

    protected String deleteTaskConfirmation(Task task, int size) {
        return String.format("Noted. I've removed this task:\n\t%s\nNow you have %d tasks in the list.\n", task, size);
    }
}
