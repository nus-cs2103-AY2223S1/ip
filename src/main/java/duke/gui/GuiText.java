package duke.gui;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tools.TaskList;

/**
 * This class handles the text that Duke is supposed to say.
 */
public class GuiText {

    private static final String GREET_STRING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String BYE_STRING = "Bye! Hope to see you again soon";
    private static final String START_LISTING_STRING = "Here are the tasks in your list:";
    private static final String END_LISTING_STRING = "That's all!";
    private static final String MARK_STRING = "Marked following task as done:";
    private static final String UNMARK_STRING = "Marked following task as not done:";
    private static final String ADD_TASK_STRING = "Got it! I stored this task:";
    private static final String DELETE_STRING = "The following task is deleted:";
    private static final String FIND_STRING = "Here are the matching tasks in your list:";

    /**
     * Creates a String to be shown by Duke to greet the user.
     *
     * @return A String to greet the user.
     */
    public static String formatGreetString() {
        return GREET_STRING;
    }

    /**
     * Creates a String to be shown by Duke to say bye to the user.
     *
     * @return A String to say bye to the user.
     */
    public static String formatByeString() {
        return BYE_STRING;
    }

    /**
     * Creates a String to be shown by Duke to display a Task together with its index.
     *
     * @param index The index of the Task.
     * @param task The task to be shown.
     * @return A String to display a Task together with its index.
     */
    public static String formatTaskWithIndexString(int index, Task task) {
        return String.format("\n%d. %s", index + 1, task);
    }

    /**
     * Creates a String to be shown by Duke to display all the stored tasks together with their individual index.
     *
     * @param taskList The TaskList containing all the stored tasks.
     * @return A String to display all the stored tasks together with their individual index.
     */
    public static String formatListString(TaskList taskList) {
        String output = START_LISTING_STRING;
        try {
            for (int i = 0; i < taskList.getSize(); i++) {
                output = output.concat(formatTaskWithIndexString(i, taskList.getTask(i)));
            }
            output = output.concat("\n" + END_LISTING_STRING);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    /**
     * Creates a String to be shown by Duke to show the task that has been marked as done.
     *
     * @param index The index of the task.
     * @param task The task that is marked.
     * @return A String to show the task that has been marked as done.
     */
    public static String formatMarkString(int index, Task task) {
        return MARK_STRING + formatTaskWithIndexString(index, task);
    }

    /**
     * Creates a String to be shown by Duke to show the task that has been marked as not done.
     *
     * @param index The index of the task.
     * @param task The task that is unmarked.
     * @return A String to show the task that has been marked as not done.
     */
    public static String formatUnmarkString(int index, Task task) {
        return UNMARK_STRING + formatTaskWithIndexString(index, task);
    }

    /**
     * Creates a String to be shown by Duke to show the task that has been added.
     *
     * @param index The index of the task.
     * @param task The task that is added.
     * @return A String to show the task that has been added.
     */
    public static String formatAddTaskString(int index, Task task) {
        return ADD_TASK_STRING + formatTaskWithIndexString(index, task);
    }

    /**
     * Creates a String to be shown by Duke to show the task that has been deleted.
     *
     * @param index The index of the task.
     * @param task The task that is deleted.
     * @return A String to show the task that has been deleted.
     */
    public static String formatDeleteString(int index, Task task) {
        return DELETE_STRING + formatTaskWithIndexString(index, task);
    }

    /**
     * Creates a String to be shown by Duke to display all the stored tasks that
     * contain the search string, together with their individual index.
     *
     * @param taskList The TaskList containing all the stored tasks.
     * @param searchString The String to search for in the task description.
     * @return A String to display all the stored tasks that contains the search
     *         string, together with their individual index.
     */
    public static String formatFindString(TaskList taskList, String searchString) {
        String output = FIND_STRING;
        try {
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                // TODO: Might consider changing this to improve abstraction
                if (task.getDescription().contains(searchString)) {
                    output = output.concat(formatTaskWithIndexString(i, task));
                }
            }
            output = output.concat("\n" + END_LISTING_STRING);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    /**
     * Creates a String to be shown by Duke to describe the exception that occurred during the program run.
     *
     * @param e The DukeException that occurred during the program run.
     * @return A String to describe the exception that occurred during the program run.
     */
    public static String formatExceptionString(DukeException e) {
        return e.getMessage();
    }
}
