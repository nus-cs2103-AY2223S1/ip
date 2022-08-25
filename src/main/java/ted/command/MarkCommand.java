package ted.command;

import ted.exception.TedException;
import ted.storage.Storage;
import ted.task.*;
import ted.ui.Ui;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) throws TedException{
        ui.markResponse(tasks.markTask(taskIndex));
        st.updateFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
