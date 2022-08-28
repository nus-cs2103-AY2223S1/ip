package Commands;

import Duke.Constants;
import Duke.DukeException;
import Duke.TaskList;
import Duke.Storage;
import Duke.Ui;

import Tasks.Task;

/**
 * Delete command which inherits from Command
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public String execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (idx < 0 || idx > t.getSize()) {
            throw new DukeException(Constants.invalidIndex);
        }
        Task deleted = t.deleteTask(idx);
        storage.save(t.stringfy());
        return ui.printDeleteTask(deleted, t.getSize());
    }

}
