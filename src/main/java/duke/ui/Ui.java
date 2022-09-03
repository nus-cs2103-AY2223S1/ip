package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a helper class to format and retrieve various strings that Lurch will use to respond.
 */
public class Ui {
    /**
     * Returns a string to use to greet users at app launch.
     *
     * @return String with a greeting.
     */
    public static String getGreetingMessage() {
        return Message.GREETING.toString();
    }

    /**
     * Returns a task status string with a prefix and indented task string.
     *
     * @param prefix Prefix to task string.
     * @param task Task to convert to string
     * @return String with a prefix and indented task string.
     */
    public static String getTaskStatusString(Message prefix, Task task) {
        return String.format("%s\n    %s", prefix, task);
    }

    /**
     * Returns a list of tasks line by line.
     *
     * @param taskList Task list to convert to a string.
     * @return String with a list of tasks line by line.
     * @throws DukeException If task list is empty.
     */
    public static String getTaskListString(TaskList taskList) throws DukeException {
        return taskList.getListString();
    }

    /**
     * Returns a string to use to dismiss users at app exit.
     * @return String with a goodbye.
     */
    public static String getTerminationString() {
        return Message.BYE.toString();
    }

    /**
     * Returns a formatted error message from an exception object.
     *
     * @param exc DukeException object.
     * @return Formatted error message.
     */
    public static String getErrorMessageString(DukeException exc) {
        return String.format("%s\n%s", Message.ERROR, exc);
    }
}
