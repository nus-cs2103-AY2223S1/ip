package duke;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Returns the error message from a series of strings.
     *
     * @param message the error messages
     * @return the final error message
     */
    public String showError(String... message) {
        return String.join(" \n\n", message);
    }

    /**
     * Prints loading error message.
     *
     * @return the loading error message
     */
    public void showLoadingError() {
        System.err.println("☹ OOPS!!! I'm sorry, but I can't load your tasks :-(");
    }

    /**
     * Prints message after adding task with current TaskList size.
     *
     * @param task the task that was added
     * @param size the size of the TaskList after adding the task
     */
    public String showAddTask(Task task, int size) {
        String output = size == 1 ? " task in the list." : " tasks in the list.";
        return "Got it. I've added this task:\n"
                + task
                + "\nNow you have "
                + size
                + output;
    }

    /**
     * Prints message after deleting task with current TaskList size.
     *
     * @param task the task that was deleted
     * @param size the size of the TaskList after deleting the task
     */
    public String showDeleteTask(Task task, int size) {
        String output = size == 1 ? " task in the list." : " tasks in the list.";
        return "Got it. I've deleted this task:\n"
                + task
                + "\nNow you have "
                + size
                + output;
    }

    /**
     * Returns string representation of the TaskList.
     *
     * @param tasks
     * @return
     */
    public String showList(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Returns exit message.
     *
     * @return exit message
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }


    /**
     * Prints welcome message.
     */
    public static String showWelcome() {
        String logo = "TASKY\n";
        return "Hello! I'm " + logo
                + "What can I do for you?";
    }
}
