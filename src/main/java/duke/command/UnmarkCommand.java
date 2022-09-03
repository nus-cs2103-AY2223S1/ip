package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents an executable command to mark a task at index as undone.
 */
public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns a task unmarked status string.
     *
     * @param tasks TaskList object to update.
     * @param storage Storage object to manage save file.
     * @return Task unmarked status string.
     * @throws DukeException If index is out of bounds.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task unmarked = tasks.unmark(this.idx);
        storage.save(tasks);
        return Ui.getTaskStatusString(Message.UNMARK, unmarked);
    }
}
