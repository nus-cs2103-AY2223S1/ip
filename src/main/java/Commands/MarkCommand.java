package Commands;

import Duke.*;
import Tasks.Task;

public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (idx <= 0 || idx > t.getSize()) {
            throw new DukeException(Constants.invalidIndex);
        }
        Task tsk = t.markTask(idx);
        storage.save(t.stringfy());
        ui.printMark(tsk);
    }

}
