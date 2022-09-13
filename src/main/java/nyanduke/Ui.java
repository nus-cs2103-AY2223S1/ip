package nyanduke;

import java.util.ArrayList;

import nyanduke.task.Task;
import nyanduke.task.TaskList;

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
        return "Okie! Me has added this task:\n  "
                + task + "\n"
                + tasks.getCountStatement();
    }

    /**
     * Gets a message that the specified tasks have been deleted from a task list.
     *
     * @param deletedTasks The specified tasks that were deleted.
     * @param tasks The task list that the specified task was deleted from.
     * @return A message that a specified task has been deleted from a task list.
     */
    public String showDeleted(ArrayList<Task> deletedTasks, TaskList tasks) {
        StringBuilder response = new StringBuilder();
        if (deletedTasks.size() == 1) {
            response.append("Okie! Me has removed this task:\n");
        } else if (!deletedTasks.isEmpty()) {
            response.append("Okie! Me has removed these tasks:\n");
        } else {
            assert false : "Ui::showDeleted called with empty ArrayList.";
        }
        for (Task deletedTask : deletedTasks) {
            response.append("  ")
                    .append(deletedTask.toString())
                    .append("\n");
        }
        response.append(tasks.getCountStatement());
        return response.toString();
    }

    /**
     * Gets an error message from NyanDuke.
     *
     * @param message The message that describes the error with NyanDuke.
     * @return An error message with the given error description.
     */
    public String showError(String message) {
        return "Oh no :c " + message;
    }

    /**
     * Gets the exit message from NyanDuke.
     *
     * @return The exit message from NyanDuke.
     */
    public String showExit() {
        return "Bye :3";
    }

    /**
     * Gets a message listing every task found with a keyword.
     *
     * @param tasks A TaskList containing the tasks found with a keyword.
     * @return A message listing every task found with a keyword.
     */
    public String showFound(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the meow-tching tasks in your list:");
        String[] strings = tasks.convertAllToString();
        int len = strings.length;
        if (len == 0) {
            response.append("\nYou have no meow-tching tasks!");
        } else {
            for (int i = 0; i < len; i++) {
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
     * @param markedTasks The specified task that were marked as done.
     * @return A message that a specified task has been marked as done.
     */
    public String showMarked(ArrayList<Task> markedTasks) {
        StringBuilder response = new StringBuilder();
        if (markedTasks.size() == 1) {
            response.append("Yay! Me has marked this task as done:");
        } else if (!markedTasks.isEmpty()) {
            response.append("Yay! Me has marked these tasks as done:");
        } else {
            assert false : "Ui::showMarked called with empty ArrayList.";
        }
        for (Task markedTask : markedTasks) {
            response.append("\n  ")
                    .append(markedTask.toString());
        }
        return response.toString();
    }

    /**
     * Gets a message that a specified task has been marked as done.
     *
     * @param unmarkedTasks The specified task that was marked as done.
     * @return A message that a specified task has been marked as done.
     */
    public String showNotMarked(ArrayList<Task> unmarkedTasks) {
        StringBuilder response = new StringBuilder();
        if (unmarkedTasks.size() == 1) {
            response.append("OK, I've marked this task as not done ;-; :");
        } else if (!unmarkedTasks.isEmpty()) {
            response.append("OK, I've marked these tasks as not done ;-; :");
        } else {
            assert false : "Ui::showNotMarked called with empty ArrayList.";
        }
        for (Task unmarkedTask : unmarkedTasks) {
            response.append("\n  ")
                    .append(unmarkedTask.toString());
        }
        return response.toString();
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
            response.append("\nYou have no tasks on this date! :3");
        } else {
            for (int i = 0; i < strings.length; i++) {
                response.append("\n").append(i + 1).append(".").append(strings[i]);
            }
        }
        return response.toString();
    }

    /**
     * Gets a greeting from NyanDuke.
     *
     * @return The greeting from NyanDuke.
     */
    public String showWelcome() {
        return "Hello from\n" + LOGO + "\nHello! I'm Nyan Cat\nWhat can I do for you?";
    }
}
