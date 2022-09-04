package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    public static final String LINE = "______________________________________";
    private static final String LOGO = "  _   _                   \n"
            + " | \\ | |                  \n"
            + " |  \\| |_   _  __ _ _ __  \n"
            + " | . ` | | | |/ _` | '_ \\ \n"
            + " | |\\  | |_| | (_| | | | |\n"
            + " |_| \\_|\\__, |\\__,_|_| |_|\n"
            + "         __/ |            \n"
            + "        |___/             ";

    /**
     * Returns a message that a specified task has been added to a task list.
     *
     * @param task The specified task that was added.
     * @param tasks The task list that the specified task was added to.
     * @return A message that the specified task has been added to a task list.
     */
    public String showAdded(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n  "
                + task + "\n"
                + tasks.getCountStatement();
    }

    /**
     * Gets a message that a specified task has been deleted from a task list.
     *
     * @param task The specified task that was deleted.
     * @param tasks The task list that the specified task was deleted from.
     * @return A message that a specified task has been deleted from a task list.
     */
    public String showDeleted(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n  "
                + task + "\n"
                + tasks.getCountStatement();
    }

    /**
     * Gets an error message from Duke.
     *
     * @param message The message that describes the error with Duke.
     * @return An error message with the given error description.
     */
    public String showError(String message) {
        return "Oh no :( " + message;
    }

    /**
     * Gets the exit message from Duke.
     *
     * @return The exit message from Duke.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Gets a message listing every task found with a keyword.
     *
     * @param tasks A TaskList containing the tasks found with a keyword.
     * @return A message listing every task found with a keyword.
     */
    public String showFound(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:");
        String[] strings = tasks.convertAllToString();
        if (strings.length == 0) {
            response.append("\nYou have no matching tasks!");
        } else {
            for (int i = 0; i < strings.length; i++) {
                response.append("\n").append(i + 1).append(".").append(strings[i]);
            }
        }
        return response.toString();
    }

    /**
     * Gets a message listing all the tasks in a specified TaskList.
     *
     * @param tasks The specified TaskList to show the list of tasks from.
     * @return A message listing all the tasks in a specified TaskList.
     */
    public String showList(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:");
        String[] strings = tasks.convertAllToString();
        if (strings.length == 0) {
            response.append("\nYou have no tasks in your list! :3");
        } else {
            for (int i = 0; i < strings.length; i++) {
                response.append("\n").append(i + 1).append(".").append(strings[i]);
            }
        }
        return response.toString();
    }

    /**
     * Gets a message that a specified task has been marked as done.
     *
     * @param task The specified task that was marked as done.
     * @return A message that a specified task has been marked as done.
     */
    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Gets a message that a specified task has been marked as done.
     *
     * @param task The specified task that was marked as done.
     * @return A message that a specified task has been marked as done.
     */
    public String showNotMarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Gets a message with every task occurring by/at a specific date.
     *
     * @param tasks A TaskList containing the tasks occurring by/at a specific date.
     * @return A message with every task occurring by/at a specific date.
     */
    public String showOnDate(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list on this date:");
        String[] strings = tasks.convertAllToString();
        if (strings.length == 0) {
            response.append("\nYou have no tasks on this date!");
        } else {
            for (int i = 0; i < strings.length; i++) {
                response.append("\n").append(i + 1).append(".").append(strings[i]);
            }
        }
        return response.toString();
    }

    /**
     * Gets a greeting from Duke.
     *
     * @return The greeting from Duke.
     */
    public String showWelcome() {
        return "Hello from\n" + LOGO + "\nHello! I'm Nyan Cat\nWhat can I do for you?";
    }
}
