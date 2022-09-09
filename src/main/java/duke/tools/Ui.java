package duke.tools;

import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    /**
     * Creates a greetings message.
     *
     * @return Greeting message.
     */
    public static String formatGreetingString() {
        String greeting = "Hello!\nHow may i help you today?";
        return greeting;
    }

    /**
     * Creates a farewell message.
     *
     * @return Farewell message.
     */
    public static String formatFarewellString() {
        String farewell = "Bye! Hope to see you again soon!";
        return farewell;
    }

    /**
     * Creates a message of status of TaskList.
     * If TaskList is not empty, include all contents of TaskList.
     *
     * @param taskList List of task to be printed.
     * @return List status message.
     * @throws DukeException If taskList.getTask(i) takes in invalid index.
     */
    public static String formatListStatusString(TaskList taskList) throws DukeException {
        String listStatus;
        if (taskList.getSize() == 0) {
            listStatus = "There are currently no tasks in your list";
            return listStatus;
        }
        listStatus = "Here are the tasks in your list:\n";

        String taskStringFormat = "%d. %s\n";
        String taskToString = taskList.toStream()
                .map(task -> String.format(taskStringFormat, taskList.getIndexOf(task) + 1, task))
                .collect(Collectors.joining());

        listStatus = listStatus.concat(taskToString);
        listStatus = listStatus.concat(String.format("Now you have %d tasks in the list.\n",
                taskList.getSize()));
        return listStatus;
    }

    /**
     * Create a message of all task found containing keyword.
     *
     * @param taskList TaskList to search through with keyword.
     * @param keyword Keyword to find.
     * @return Find string message.
     * @throws DukeException If taskList.getTask(i) takes in invalid index.
     */
    public static String formatFindTaskString(TaskList taskList, String keyword) throws DukeException {
        String taskFindOutput;
        if (taskList.isEmpty()) {
            taskFindOutput = "There are currently no tasks in your list";
            return taskFindOutput;
        }
        String taskStringFormat = "%d. %s\n";
        String taskFoundList = taskList.toStream()
                .filter(task -> task.isFoundInDescription(keyword))
                .map(task -> String.format(taskStringFormat, taskList.getIndexOf(task) + 1, task))
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
     * Creates a message marking task as done in TaskList.
     *
     * @param index Index of task that is marked.
     * @param task Task marked as done.
     * @return Message.
     */
    public static String formatMarkAsDoneString(int index, Task task) {
        String markAsDoneString = "Nice! I've marked this task as done:";
        return String.format(markAsDoneString + "\n%d. %s", index + 1, task);
    }

    /**
     * Prints message marking task as undone in TaskList.
     *
     * @param index Index of task that is marked.
     * @param task Task marked as undone.
     * @return Message.
     */
    public static String formatMarkAsUndoneString(int index, Task task) {
        String markAsUndoneString = "Ok! I've marked this task as not done yet:";
        return String.format(markAsUndoneString + "\n%d. %s", index + 1, task);
    }

    /**
     * Creates a message of deleting task from TaskList.
     *
     * @param index Index of task that is deleted.
     * @param task Task that was deleted.
     * @return Message.
     */
    public static String formatDeleteTaskString(int index, Task task) {
        String taskDeleteString = "Noted. I've removed this task:";
        return String.format(taskDeleteString + "\n%d. %s", index + 1, task);
    }

    /**
     * Creates a message that TaskList is empty and no task can be deleted.
     */
    public static String formatNoTaskToDeleteString() {
        String noTaskToDeleteString = "OOPS!!! There are currently no task to delete";
        return noTaskToDeleteString;
    }

    /**
     * Creates a message of adding task to TaskList.
     *
     * @param task Task that was added.
     * @return Message.
     */
    public static String formatAddTaskString(Task task) {
        String taskAddString = "Got it! I've added this task:\n> ";
        return taskAddString + task;
    }

    /**
     * Creates a message of DukeException that occurred.
     *
     * @param e DukeException containing error message.
     * @return Error message.
     */
    public static String formatExceptionString(DukeException e) {
        return e.getMessage();
    }
}
