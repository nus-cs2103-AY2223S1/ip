package duke.command;

import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;

import static duke.exception.ErrorMessage.INVALID_INDEX;
import static duke.exception.ErrorMessage.MISSING_INDEX;

/**
 * Represents a MarkCommand to mark/unmark task. This class implements Command Interface.
 */
public class MarkCommand implements Command {
    private TaskList tasks;
    private String commandType;
    private String index;

    /**
     * This method is a MarkCommand constructor.
     * A MarkCommand consists of a TaskList, a commandType (mark/ unmark), an index of task to be marked.
     * @param tasks a list of tasks.
     * @param commandType commandType (mark/ unmark).
     * @param index index of task to be marked.
     */
    public MarkCommand(TaskList tasks, String commandType, String index) {
        this.tasks = tasks;
        this.commandType = commandType;
        this.index = index;
    }

    /**
     * Executes mark command to mark/ unmark the indexed task in the TaskList.
     * Returns a string containing commandType and task index (1-indexed).
     * Returned value will be used to call chatbot response message.
     * If the task index is invalid/ missing, the command is not executed.
     *
     * @throws DukeException if the task index is invalid/ missing.
     * @return a string containing commandType and task index.
     */
    public String execute() throws DukeException {
        String taskIndex = index.trim();
        if (taskIndex.equals("") || !taskIndex.matches("[0-9]+")) { // task description empty or not numeric
            throw new DukeException(MISSING_INDEX, commandType);
        } else if (Integer.parseInt(taskIndex) > tasks.getSize() || Integer.parseInt(taskIndex) < 1) {
            throw new DukeException(INVALID_INDEX, "");
        }
        if (commandType.equals("mark")) {
            tasks.markAsDone(Integer.parseInt(taskIndex));
            return String.format("mark %s", taskIndex);
        } else if (commandType.equals("unmark")) {
            tasks.markAsUndone(Integer.parseInt(taskIndex));
            return String.format("unmark %s", taskIndex);
        } else {
            Task t = tasks.deleteTaskAtIndex(Integer.parseInt(taskIndex));
            return String.format("delete %s %s",t.getStatusIcon(), t.getDescription());
        }
    }
}
