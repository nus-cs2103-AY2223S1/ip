package amanda.command;

import amanda.manager.*;
import amanda.ui.*;
import amanda.exception.*;

public class MarkCommand extends Command {

    public MarkCommand(int taskNo) {

        super(null, taskNo);
    }

    @Override
    public void execute(TaskManager tasks, Ui ui, StoreManager store) throws AmandaException {
        if (taskNo > tasks.getList().size() | taskNo <= 0) {
            throw new AmandaException("     That task does not even exist. Why are you wasting my time?");
        }
        tasks.getList().get(taskNo - 1).doTask();
        store.store(tasks);
        ui.showMarkCommand(tasks,taskNo);
    }
}
