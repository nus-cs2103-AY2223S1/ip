package Command;

import Duke.TaskList;
import Duke.Ui;
import Duke.Storage;
import Duke.DukeException;
import Duke.Constants;
import Tasks.Task;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int idx) {
        super();
        this.index = idx;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index > t.getSize()) {
            throw new DukeException(Constants.INVALID_INDEX);
        }
        Task tsk = t.unmarkTask(index);
        storage.writeFile(t.tasksToString());
        ui.printUnmarkTask(tsk);
    }
}
