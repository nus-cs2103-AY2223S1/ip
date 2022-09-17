package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for <code>DeleteCommand</code>.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String getResponse(TaskList tasks, Storage storage) throws DukeException {
        Task removedTask = tasks.removeTask(index);
        return "Yay! I have removed this task from your list \n"
                + removedTask.toString();
    }

    /**
     * Check if the command exit duke.
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

