package duke.commands;

import duke.core.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public abstract class TaskListIndexCommand extends TaskListCommand {
    public TaskListIndexCommand(String invoker, TaskList taskList) {
        super(invoker, taskList);
    }

    @Override
    public String execute(String parameters) {
        int index = -1;
        Task t;

        if (parameters.equals("")) {
            throw new DukeException("No index given!");
        } else {

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
    }
    protected abstract String execute(Task t);
}
