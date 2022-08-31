package duke;

/**
 * Ui Class handling response message.
 *
 * @author Elbert Benedict
 */
public class Ui {
    /**
     * Returns Welcome Message.
     *
     * @return Welcome Message.
     */
    public static String getWelcomeMessage() {
        return "Hello from Botto\nWhat can I do for you?\n";
    }

    /**
     * Returns Goodbye Message.
     *
     * @return Goodbye Message.
     */
    public static String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns message regarding the newly added task.
     *
     * @param task the newly added task.
     * @param taskNumber the number of tasks in the Tasklist.
     * @return message regarding the newly added task.
     */
    public static String getTaskAddedMessage(Task task, int taskNumber) {
        String message = "Got it. I've added this task:\n"
                            + task.toString() + "\n"
                            + "Now you have " + taskNumber + " tasks in the list.\n";

        return message;
    }

    /**
     * Returns message for the tasklist.
     *
     * @param taskList the list of tasks.
     */
    public static String getTaskListMessage(TaskList taskList) {
        String message = "Here are the tasks in your list:\n" + taskList.toString();
        return message;
    }

    /**
     * Returns deleted task message.
     *
     * @param task the newly deleted task.
     * @param taskLeft the number of tasks left in the list.
     * @return deleted task message.
     */
    public static String getDeletedTaskMessage(Task task, int taskLeft) {
        String message = "Noted. I have removed this task:\n"
                            + task + "\n"
                            + "Now you have " + taskLeft + " tasks in the list.\n";

        return message;
    }

    /**
     * Returns marked task message.
     *
     * @param task the newly marked task.
     * @return marked task message.
     */
    public static String getMarkedTaskMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n"
                            + task + "\n";

        return message;
    }

    /**
     * Returns unmarked task message.
     *
     * @param task the task that is just unmarked.
     * @return unmarked task message.
     */
    public static String getUnmarkedTaskMessage(Task task) {
        String message = "OK, I've marked this task as not done yet:\n"
                            + task;

        return message;
    }

    /**
     * Returns the error message of a DukeException.
     *
     * @param error The DukeException which message is to be printed.
     * @return error message of DukeException.
     */
    public static String getDukeErrorMessage(DukeException error) {
        return error.getMessage() + "\n";
    }

    /**
     * Returns the list of filtered tasks message.
     *
     * @param filteredTasks the filtered tasks.
     */
    public static String getFilteredTasksMessage(TaskList filteredTasks) {
        String message = "Here are the matching tasks in your list:\n"
                            + filteredTasks.toString();

        return message;
    }
}
