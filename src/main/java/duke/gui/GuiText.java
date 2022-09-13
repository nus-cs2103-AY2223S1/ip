package duke.gui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskWithDateTime;
import duke.tools.TaskList;

/**
 * This class handles the text that Duke is supposed to say.
 */
public class GuiText {

    private static final String GREET_STRING = "Hello! I'm Shiro\nWhat can I do for you?";
    private static final String BYE_STRING = "Bye! Hope to see you again soon";
    private static final String START_LISTING_STRING = "Here are the tasks in your list:";
    private static final String END_LISTING_STRING = "That's all!";
    private static final String MARK_STRING = "Marked following task as done:";
    private static final String UNMARK_STRING = "Marked following task as not done:";
    private static final String ADD_TASK_STRING = "Got it! I stored this task:";
    private static final String DELETE_STRING = "The following task is deleted:";
    private static final String FIND_STRING = "Here are the matching tasks in your list:";
    private static final String SNOOZE_STRING = "The following task is snoozed!";

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
        List<Task> storedTasks = taskList.getStoredTasks();
        String taskInList = storedTasks.stream()
                .map(task -> formatTaskWithIndexString(storedTasks.indexOf(task), task))
                .collect(Collectors.joining());
        String output = START_LISTING_STRING.concat(taskInList);
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
        List<Task> storedTasks = taskList.getStoredTasks();
        String filteredTaskString = storedTasks.stream()
                .map(task -> formatTaskWithIndexString(storedTasks.indexOf(task), task))
                .filter(string -> string.contains(searchString))
                .collect(Collectors.joining());
        String output = FIND_STRING.concat(filteredTaskString).concat(END_LISTING_STRING);
        return output;
    }

    /**
     * Creates a String to be shown by Duke to display all the stored tasks that
     * fall within the starting and ending date and time.
     *
     * @param taskList The TaskList containing all the stored tasks.
     * @param start The starting date and time of the tasks.
     * @param end The ending date and time of the tasks.
     * @return A String to display all the stored tasks that start and end within
     *         the given date and time.
     */
    public static String formatWithinDateTime(TaskList taskList, LocalDateTime start, LocalDateTime end) {
        List<Task> storedTasks = taskList.getStoredTasks();
        String filteredTaskString = storedTasks.stream()
                .filter(task -> {
                    Task.TaskType taskType = task.getTaskType();
                    if (taskType == Task.TaskType.DEADLINE || taskType == Task.TaskType.EVENT) {
                        LocalDateTime dateTime = ((TaskWithDateTime) task).getDateTime();
                        return (dateTime.isAfter(start) || dateTime.isEqual(start))
                                && (dateTime.isBefore(end) || dateTime.isEqual(end));
                    } else {
                        return false;
                    }
                })
                .map(task -> formatTaskWithIndexString(storedTasks.indexOf(task), task))
                .collect(Collectors.joining());
        String output = FIND_STRING.concat(filteredTaskString).concat(END_LISTING_STRING);
        return output;
    }

    /**
     * Creates a String to be shown by Duke to display the task that was snoozed.
     *
     * @param task The task that was snoozed.
     * @return A String to show the task that was snoozed.
     */
    public static String formatSnooze(int index, TaskWithDateTime task) {
        return SNOOZE_STRING + formatTaskWithIndexString(index, task);
    }

    /**
     * Creates a String to be shown by Duke to describe the exception that occurred during the program run.
     *
     * @param e The DukeException that occurred during the program run.
     * @return A String to describe the exception that occurred during the program run.
     */
    public static String formatExceptionString(DukeException e) {
        return e.getGuiMessage();
    }
}
