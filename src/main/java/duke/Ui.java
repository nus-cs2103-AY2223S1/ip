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
    public void showTasks(TaskList taskList) {
        if (taskList.length() != 0) {
            System.out.println("    " + "Here are the tasks in your list:\n");
            System.out.println(taskList.toString());
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
        System.out.println( "    " + "Nice! I've marked task " + (taskNumber + 1) + " as done.\n");
    }

    /**
     * Prints a success message for marking a task as incomplete.
     *
     * @param taskNumber Index of the task as in the task list.
     */
    public void showUnmarkSuccess(int taskNumber) {
        System.out.println("    " + "OK, I've marked task " + (taskNumber + 1) + " as not done yet.\n");
    }

    /**
     * Prints a success message for removing a task.
     *
     * @param taskNumber Index of the task as in the task list.
     * @param taskList Current list of tasks stored by the bot.
     */
    public void showRemoveTaskSuccess(int taskNumber, TaskList taskList) {
        System.out.println("    " + "Noted. I've removed task " + (taskNumber + 1) + ".\n" +
                "    " + "Now you have " + taskList.length() +  " tasks in the list.\n");
    }

    /**
     * Prints a success message for adding a task.
     *
     * @param taskList Current list of tasks stored by the bot.
     */
    public void showAddTaskSuccess(TaskList taskList) {
        System.out.println("    " + "Task added. You now have " + taskList.length() + " tasks in the list.\n");
    }
}
