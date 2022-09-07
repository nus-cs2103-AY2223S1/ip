package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents an executable command to mark a task at index as done.
 */
public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns a task marked status string.
     *
     * @param tasks TaskList object to update.
     * @param storage Storage object to manage save file.
     * @return Task marked status string.
     * @throws DukeException If index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task marked = tasks.mark(this.idx);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.MARK, marked);
    }
}
