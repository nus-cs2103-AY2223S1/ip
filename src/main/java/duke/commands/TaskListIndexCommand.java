package duke.commands;

import duke.core.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * An abstract class of commands that interact with the item on a particular
 * index of a TaskList.
 */
public abstract class TaskListIndexCommand extends TaskListCommand {
    public TaskListIndexCommand(String invoker, TaskList taskList) {
        super(invoker, taskList);
    }

    /**
     * Identifies the task to execute the command on, and invokes TaskListIndexCommand::execute.
     *
     * @param parameters Command arguments, used to determine the index of the task to execute on.
     * @return String output of the execution.
     */
    @Override
    public String execute(String parameters) {
        int index = -1;
        Task t;

        if (parameters.equals("")) {
            throw new DukeException("No index given!");
        }

        try {
            index = Integer.parseInt(parameters);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid index!");
        }

        try {
            t = taskList.getTaskAtIndex(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task index out of bounds!");
        }

        return execute(t);
    }

    /**
     * Execution of the action of a command on a particular task.
     * Called by the main execute method after determining the task at the given index.
     *
     * @param t Task to perform an action on.
     * @return String output of the execution.
     */
    protected abstract String execute(Task t);
}
