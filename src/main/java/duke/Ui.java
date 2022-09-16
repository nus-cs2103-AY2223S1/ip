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
        String output = size == 1 ? " task in your list." : " tasks in your list.";
        return "Okay! I've added this task:\n"
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
        String output = size == 1 ? " task in your list." : " tasks in your list.";
        return "Okay! I've deleted this task:\n"
                + task
                + "\nNow you have "
                + size
                + output;
    }

    /**
     * Returns string representation of the TaskList.
     *
     * @param tasks the TaskList
     * @return the string representation of the TaskList
     */
    public String showList(TaskList tasks) {
        return "Here's what you have:\n" + tasks;
    }

    /**
     * Returns exit message.
     *
     * @return exit message
     */
    public String showBye() {
        return "Bye! Hope to see you again soon! ☺";
    }


    /**
     * Prints welcome message.
     */
    public static String showWelcome() {
        String logo = "TASKY,\n";
        return "☺ Hey there! I'm " + logo
                + "your personal task manager!\n\n"
                + "What can I do for you today?";
    }
}
