package Command;

import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Tasks.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int idx) {
        super();
        this.index = idx;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        Task deleted = t.deleteTask(index);
        storage.writeFile(t.tasksToString());
        ui.printDeleteTask(deleted, t.getSize());
    }
}
