package amanda.command;

import amanda.manager.*;
import amanda.ui.*;
import amanda.exception.*;

public class UnmarkCommand extends Command {

    public UnmarkCommand(int taskNo) {
        super(null, taskNo);
    }

    @Override
    public void execute(TaskManager tasks, Ui ui, StoreManager store) throws AmandaException {
        if (taskNo > tasks.getList().size() | taskNo <= 0) {
            throw new AmandaException("     That task does not even exist. Why are you wasting my time?");
        }
        tasks.getList().get(taskNo - 1).undoTask();
        store.store(tasks);
        ui.showUnmarkCommand(tasks, taskNo);

    }
}
