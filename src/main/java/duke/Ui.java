package duke;

/**
 * Represents a user interface for user interaction with Duke chatbot.
 *
 * @author Conrad
 */
public class Ui {

    /**
     * Returns the welcome message.
     *
     * @return String containing the welcome message.
     */
    public String showWelcome() {
        String dukeGreeting = "Hello! I'm Duke\nWhat can I do for you?";
        return dukeGreeting;
    }

    /**
     * Returns the goodbye message.
     *
     * @return String containing the goodbye message.
     */
    public String showGoodbye() {
        String dukeGoodbye = "Bye. Hope to see you again soon!";
        return dukeGoodbye;
    }

    /**
     * Returns the error message arising from invalid user input.
     *
     * @return String containing the error message.
     */
    public String showError(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns the error message arising from issues in loading or storing tasks locally.
     *
     * @return String containing the error message.
     */
    public String showLoadingError(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns the tasks currently in the task list.
     *
     * @return String containing the list of tasks.
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
     * Returns a success message for marking a task as complete.
     *
     * @param taskNumber Index of the task as in the task list.
     * @return String containing a success message.
     */
    public String showMarkSuccess(int taskNumber) {
        return "Nice! I've marked task "
                + (taskNumber + 1) + " as done.\n";
    }

    /**
     * Returns a success message for marking a task as incomplete.
     *
     * @param taskNumber Index of the task as in the task list.
     * @return String containing a success message.
     */
    public String showUnmarkSuccess(int taskNumber) {
        return "OK, I've marked task "
                + (taskNumber + 1) + " as not done yet.\n";
    }

    /**
     * Returns a success message for removing a task.
     *
     * @param taskNumber Index of the task as in the task list.
     * @param tasks Current list of tasks stored by the bot.
     * @return String containing a success message.
     */
    public String showRemoveTaskSuccess(int taskNumber, TaskList tasks) {
        return "Noted. I've removed task " + (taskNumber + 1) + ".\n"
                + "Now you have " + tasks.length() + " tasks in the list.\n";
    }

    /**
     * Returns a success message for adding a task.
     *
     * @param tasks Current list of tasks stored by the bot.
     * @return String containing a success message.
     */
    public String showAddTaskSuccess(TaskList tasks) {
        return "Task added. You now have "
                + tasks.length() + " tasks in the list.\n";
    }

    /**
     * Returns the tasks matching the user query string.
     *
     * @param searchInput The query string to search for.
     * @param tasks The current <code>TaskList</code>.
     * @return String containing the matching tasks.
     */
    public String showMatchingTasks(String searchInput, TaskList tasks) {
        String matchingStart = "Here are the matching tasks in your list:\n";
        return matchingStart + tasks.getMatchingTasksRepresentation(searchInput);
    }
}
