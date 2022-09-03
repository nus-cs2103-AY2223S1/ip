package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents an executable command to delete a task at index.
 */
public class DeleteCommand extends Command {
    /* Index of task that will be deleted. */
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns a task deleted status string after deleting task from list.
     *
     * @param tasks TaskList object to delete from.
     * @param storage Storage object to manage save file.
     * @return Task deleted status string.
     * @throws DukeException If idx is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task deleted = tasks.delete(this.idx);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.DELETE, deleted);
    }
}
