package Commands;

import Duke.*;
import Tasks.Task;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        super();
        this.idx = idx;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        if (idx < 0 || idx > t.getSize()) {
            throw new DukeException(Constants.invalidIndex);
        }
        Task deleted = t.deleteTask(idx);
        storage.save(t.stringfy());
        ui.printDeleteTask(deleted, t.getSize());
    }

}
