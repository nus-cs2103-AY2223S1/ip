package duke;

/**
 * Represents a user interface that deals with interaction with users.
 */
public class Ui {

    /**
     * Handles what to show during Duke's startup.
     *
     * @return response of duke in string.
     */
    public static String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Handles user interface for loading error.
     *
     * @return response of duke in string.
     */
    public String showLoadingError() {
        return "Storage file not found at specified path!";
    }

    /**
     * Handles user interface for when adding new tasks.
     *
     * @param taskToAdd task to be shown on ui.
     * @param tasks list of tasks to get number of existing tasks.
     * @return response of duke in string.
     */
    public String showAddedTask(Task taskToAdd, TaskList tasks) {
        return "Got it. I've added this task:\n"
                + taskToAdd.toString() + "\n"
                + "Now you have " + tasks.getLength() + " tasks in the list";
    }

    /**
     * Handles user interface for deleting tasks.
     *
     * @param taskToRemove task to be shown on ui.
     * @param tasks list of tasks to get number of existing tasks.
     * @return response of duke in string.
     */
    public String showDeletedTask(Task taskToRemove, TaskList tasks) {
        return "Noted. I've removed this task:\n" + taskToRemove.toString()
                + "\nNow you have " + tasks.getLength() + " tasks in the list";
    }

    /**
     * Handles user interface for marking task as done.
     *
     * @param markedTask task to be shown on ui.
     * @return response of duke in string.
     */
    public String showMarkedTask(Task markedTask) {
        return "Nice! I've marked this task as done:\n" + markedTask.toString();
    }

    /**
     * Handles user interface for marking task as not done.
     *
     * @param unmarkedTask task to be shown.
     * @return response of duke in string.
     */
    public String showUnmarkedTask(Task unmarkedTask) {
        return "OK, I've marked this task as not done yet:\n"
                + unmarkedTask.toString();
    }

    /**
     * Prints out all existing tasks in list.
     *
     * @param tasks list of existing tasks.
     * @return response of duke in string.
     */
    public String showList(TaskList tasks) {
        return "Here are the tasks in your list:\n"
                + tasks.toString();
    }

    /**
     * Handles user interface exiting program.
     *
     * @return response of duke in string.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles user interface for showing matching tasks.
     *
     * @param tasks list of matching tasks to show.
     * @return response of duke in string.
     */
    public String showFilteredList(TaskList tasks) {
        return "Here are the matching tasks in your list:\n"
                + tasks;
    }
}
