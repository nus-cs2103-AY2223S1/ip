package ted.command;

import ted.exception.TedException;
import ted.storage.Storage;
import ted.task.*;
import ted.ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) throws TedException{
        String temp = tasks.deleteTask(taskIndex);
        ui.deleteResponse(temp, tasks.getSize());
        st.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
