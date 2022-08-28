package Commands;

import Duke.Constants;
import Duke.DukeException;
import Duke.TaskList;
import Duke.Storage;
import Duke.Ui;

import Tasks.Task;

/**
 * Mark command which inherits from Command
 */
public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public String execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (idx <= 0 || idx > t.getSize()) {
            throw new DukeException(Constants.invalidIndex);
        }
        Task tsk = t.markTask(idx);
        storage.save(t.stringfy());
        return ui.printMark(tsk);
    }

}
