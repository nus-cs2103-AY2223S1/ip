package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class DeleteCommand implements Command{

    private final int toDelete;

    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.dukePrint(tasks.delete(toDelete));
        storage.refresh(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
