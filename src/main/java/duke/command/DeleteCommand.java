package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a delete command.
     *
     * @param index index of task to be deleted from <code>TaskList</code>
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     *
     * @param taskList list of stored tasks
     * @return successful task deletion message
     * @throws DukeException if user input has wrong format
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        return taskList.deleteTask(index);
    }
}
