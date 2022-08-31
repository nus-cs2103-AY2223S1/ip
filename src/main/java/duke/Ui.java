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
    public void showWelcome() {
        String dukeGreeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(dukeGreeting);
    }

    /**
     * Prints the goodbye message.
     *
     */
    public void showGoodbye() {
        String dukeGoodbye = "    " + "Bye. Hope to see you again soon!";
        System.out.println(dukeGoodbye);
    }

    /**
     * Prints the error message arising from invalid user input.
     *
     */
    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the error message arising from issues in loading or storing tasks locally.
     *
     */
    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the tasks currently in the task list.
     *
     */
    public void showTasks(TaskList tasks) {
        if (tasks.length() != 0) {
            System.out.println("    " + "Here are the tasks in your list:\n");
            System.out.println(tasks.toString());
        } else {
            System.out.println("    " + "There are no tasks in your list.\n");
        }

    }

    /**
     * Prints a success message for marking a task as complete.
     *
     * @param taskNumber Index of the task as in the task list.
     */
    public void showMarkSuccess(int taskNumber) {
        System.out.println("    " + "Nice! I've marked task "
                + (taskNumber + 1) + " as done.\n");
    }

    /**
     * Prints a success message for marking a task as incomplete.
     *
     * @param taskNumber Index of the task as in the task list.
     */
    public void showUnmarkSuccess(int taskNumber) {
        System.out.println("    " + "OK, I've marked task "
                + (taskNumber + 1) + " as not done yet.\n");
    }

    /**
     * Prints a success message for removing a task.
     *
     * @param taskNumber Index of the task as in the task list.
     * @param tasks Current list of tasks stored by the bot.
     */
    public void showRemoveTaskSuccess(int taskNumber, TaskList tasks) {
        System.out.println("    " + "Noted. I've removed task " + (taskNumber + 1) + ".\n"
                + "    " + "Now you have " + tasks.length() + " tasks in the list.\n");
    }

    /**
     * Prints a success message for adding a task.
     *
     * @param tasks Current list of tasks stored by the bot.
     */
    public void showAddTaskSuccess(TaskList tasks) {
        System.out.println("    " + "Task added. You now have "
                + tasks.length() + " tasks in the list.\n");
    }

    /**
     * Prints the tasks matching the user query string.
     *
     * @param searchInput The query string to search for.
     * @param tasks The current <code>TaskList</code>
     */
    public void showMatchingTasks(String searchInput, TaskList tasks) {
        System.out.println("    " + "Here are the matching tasks in your list:\n");
        System.out.println(tasks.getMatchingTasksRepresentation(searchInput));
    }
}
