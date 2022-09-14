package duke.commons;

import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    private static final String TASK_STRING_FORMAT = "%d. %s";
    /**
     * Generates a greetings message.
     *
     * @return Greeting message.
     */
    public static String formatGreetingMessage() {
        String greeting = "Hello! I'm Alpha\nHow may i help you today?";
        return greeting;
    }

    /**
     * Generates a message of status of TaskList.
     * If TaskList is not empty, include all contents of TaskList.
     *
     * @param taskList List of task to be printed.
     * @return List status message.
     */
    public static String formatListStatusMessage(TaskList taskList) {
        String listStatus;
        if (taskList.getSize() == 0) {
            listStatus = "There are currently no tasks in your list";
            return listStatus;
        }
        listStatus = "Here are the tasks in your list:\n";

        String taskToString = taskList.toStream()
                .map(task -> String.format(TASK_STRING_FORMAT + "\n", taskList.getIndexOf(task) + 1, task))
                .collect(Collectors.joining());

        listStatus = listStatus.concat(taskToString);
        return listStatus;
    }

    /**
     * Generates a message of all task found containing keyword.
     *
     * @param taskList TaskList to search through with keyword.
     * @param keyword Keyword to find.
     * @return Find string message.
     */
    public static String formatFindTaskMessage(TaskList taskList, String keyword) {
        String taskFindOutput;
        if (taskList.isEmpty()) {
            taskFindOutput = "There are currently no tasks in your list";
            return taskFindOutput;
        }
        String taskFoundList = taskList.toStream()
                .filter(task -> task.isFoundInDescription(keyword))
                .map(task -> String.format(TASK_STRING_FORMAT + "\n", taskList.getIndexOf(task) + 1, task))
                .collect(Collectors.joining());
        long taskFoundCount = taskList.toStream()
                .filter(task -> task.isFoundInDescription(keyword))
                .count();

        if (taskFoundCount == 0) {
            taskFindOutput = "There are no matching task found";
            return taskFindOutput;
        }

        taskFindOutput = String.format("There are %d matching task found: \n", taskFoundCount);
        taskFindOutput = taskFindOutput.concat(taskFoundList);
        return taskFindOutput;
    }

    /**
     * Generates a message marking task as done in TaskList.
     *
     * @param index Index of task that was marked.
     * @param task Task marked as done.
     * @return Mark task done message.
     */
    public static String formatMarkAsDoneMessage(int index, Task task) {
        String markAsDoneMessage = "Nice! I've marked this task as done:";
        return String.format(markAsDoneMessage + "\n" + TASK_STRING_FORMAT, index + 1, task);
    }

    /**
     * Generates a message marking task as undone in TaskList.
     *
     * @param index Index of task that was marked.
     * @param task Task marked as undone.
     * @return Mark task undone message.
     */
    public static String formatMarkAsUndoneMessage(int index, Task task) {
        String markAsUndoneMessage = "Ok! I've marked this task as not done yet:";
        return String.format(markAsUndoneMessage + "\n" + TASK_STRING_FORMAT, index + 1, task);
    }

    /**
     * Generates a message of deleting task from TaskList.
     *
     * @param index Index of task that was deleted.
     * @param task Task that was deleted.
     * @return Delete task message.
     */
    public static String formatDeleteTaskMessage(int index, Task task) {
        String taskDeleteMessage = "Noted. I've removed this task:";
        return String.format(taskDeleteMessage + "\n" + TASK_STRING_FORMAT, index + 1, task);
    }

    /**
     * Generates a message of adding task to TaskList.
     *
     * @param task Task that was added.
     * @return Add task message.
     */
    public static String formatAddTaskMessage(Task task) {
        String taskAddMessage = "Got it! I've added this task:\n> ";
        return taskAddMessage + task;
    }

    /**
     * Generates a message of updating task in TaskList.
     *
     * @param index Index of task that was updated.
     * @param task Task that was updated.
     * @return Update task message.
     */
    public static String formatUpdateMessage(int index, Task task) {
        String taskUpdateMessage = "Done! I've updated this task:\n";
        return String.format(taskUpdateMessage + TASK_STRING_FORMAT, index + 1, task);
    }

    /**
     * Creates a message of DukeException that occurred.
     *
     * @param e DukeException containing error message.
     * @return Error message.
     */
    public static String formatExceptionMessage(DukeException e) {
        return e.getMessage();
    }
}
