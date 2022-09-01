package duke;

/**
 * Represents a user interface for user interaction with Duke chatbot.
 *
 * @author Conrad
 */
public class Ui {

    /**
     * Prints the welcome message.
     *
     */
    public String showWelcome() {
        String dukeGreeting = "Hello! I'm Duke\nWhat can I do for you?";
        return dukeGreeting;
    }

    /**
     * Prints the goodbye message.
     *
     */
    public String showGoodbye() {
        String dukeGoodbye = "Bye. Hope to see you again soon!";
        return dukeGoodbye;
    }

    /**
     * Prints the error message arising from invalid user input.
     *
     */
    public String showError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints the error message arising from issues in loading or storing tasks locally.
     *
     */
    public String showLoadingError(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints the tasks currently in the task list.
     *
     */
    public String showTasks(TaskList tasks) {
        if (tasks.length() != 0) {
            String listResponse = "Here are the tasks in your list:\n";
            return listResponse + tasks.toString();
        } else {
            return "There are no tasks in your list.\n";
        }

    }

    /**
     * Prints a success message for marking a task as complete.
     *
     * @param taskNumber Index of the task as in the task list.
     */
    public String showMarkSuccess(int taskNumber) {
        return "Nice! I've marked task "
                + (taskNumber + 1) + " as done.\n";
    }

    /**
     * Prints a success message for marking a task as incomplete.
     *
     * @param taskNumber Index of the task as in the task list.
     */
    public String showUnmarkSuccess(int taskNumber) {
        return "OK, I've marked task "
                + (taskNumber + 1) + " as not done yet.\n";
    }

    /**
     * Prints a success message for removing a task.
     *
     * @param taskNumber Index of the task as in the task list.
     * @param tasks Current list of tasks stored by the bot.
     */
    public String showRemoveTaskSuccess(int taskNumber, TaskList tasks) {
        return "Noted. I've removed task " + (taskNumber + 1) + ".\n"
                + "Now you have " + tasks.length() + " tasks in the list.\n";
    }

    /**
     * Prints a success message for adding a task.
     *
     * @param tasks Current list of tasks stored by the bot.
     */
    public String showAddTaskSuccess(TaskList tasks) {
        return "Task added. You now have "
                + tasks.length() + " tasks in the list.\n";
    }

    /**
     * Prints the tasks matching the user query string.
     *
     * @param searchInput The query string to search for.
     * @param tasks The current <code>TaskList</code>
     */
    public String showMatchingTasks(String searchInput, TaskList tasks) {
        String matchingStart = "Here are the matching tasks in your list:\n";
        return matchingStart + tasks.getMatchingTasksRepresentation(searchInput);
    }
}
