package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";

    /** The ui's response to the latest user input. */
    private String response;

    /**
     * Gets the ui response.
     *
     * @return the response from the ui.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Shows a message that a specified task has been added to a task list.
     *
     * @param task The specified task that was added.
     * @param tasks The task list that the specified task was added to.
     */
    public void showAdded(Task task, TaskList tasks) {
        response = "Got it. I've added this task:\n  "
                + task + "\n"
                + tasks.getCountStatement();
    }

    /**
     * Shows a message that a specified task has been deleted from a task list.
     *
     * @param task The specified task that was deleted.
     * @param tasks The task list that the specified task was deleted from.
     */
    public void showDeleted(Task task, TaskList tasks) {
        response = "Noted. I've removed this task:\n  "
                + task + "\n"
                + tasks.getCountStatement();
    }

    /**
     * Shows an error message from Duke.
     *
     * @param message The message that describes the error with Duke.
     */
    public void showError(String message) {
        response = "Oh no :( " + message;
    }

    /**
     * Shows the exit message from Duke.
     */
    public void showExit() {
        response = "Bye. Hope to see you again soon!";
    }

    /**
     * Shows every task found with a keyword.
     */
    public void showFound(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
        String[] strings = tasks.allToString();
        if (strings.length == 0) {
            response.append("\nYou have no matching tasks!");
        } else {
            for (int i = 0; i < strings.length; i++) {
                response.append("\n").append(i + 1).append(".").append(strings[i]);
            }
        }
        this.response = response.toString();
    }

    /**
     * Shows a list of all the tasks in a specified TaskList.
     *
     * @param tasks The specified TaskList to show the list of tasks from.
     */
    public void showList(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        String[] strings = tasks.allToString();
        for (int i = 0; i < strings.length; i++) {
            response.append("\n").append(i + 1).append(".").append(strings[i]);
        }
        this.response = response.toString();
    }

    /**
     * Shows a message that a specified task has been marked as done.
     *
     * @param task The specified task that was marked as done.
     */
    public void showMarked(Task task) {
        response = "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Shows every task occurring by/at a specific date.
     *
     * @param tasks A TaskList containing the tasks occurring by/at a specific date.
     */
    public void showOnDate(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list on this date:");
        String[] strings = tasks.allToString();
        if (strings.length == 0) {
            response.append("\nYou have no tasks on this date!");
        } else {
            for (int i = 0; i < strings.length; i++) {
                response.append("\n").append(i + 1).append(".").append(strings[i]);
            }
        }
        this.response = response.toString();
    }

    /**
     * Shows a message that a specified task has been marked as done.
     *
     * @param task The specified task that was marked as done.
     */
    public void showNotMarked(Task task) {
        response = "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Greets the user when Duke starts running.
     */
    public void showWelcome() {
        this.response = "Hello from\n" + LOGO + "\nHello! I'm Duke\nWhat can I do for you?";
    }
}
