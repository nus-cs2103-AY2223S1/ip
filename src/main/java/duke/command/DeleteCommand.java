package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index == -1 || index >= tasks.size()) {
            throw new DukeException(String.format("Task number %d not found! Unable to delete task.", index + 1));
        }
        Task deleted = tasks.remove(index);
        return "Noted. I've removed this task:\n" + deleted.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof DeleteCommand == false) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return index == that.index;
    }
}
