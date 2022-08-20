package Commands;

import Duke.Constants;
import Duke.DukeException;
import Duke.TaskList;
import Duke.Storage;
import Duke.Ui;

import Tasks.Task;

public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (idx < 0 || idx > t.getSize()) {
            throw new DukeException(Constants.invalidIndex);
        }
        Task tsk = t.unmarkTask(idx);
        storage.save(t.stringfy());
        ui.printUnmark(tsk);
    }

}
