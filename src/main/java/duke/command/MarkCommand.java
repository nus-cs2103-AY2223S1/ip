package duke.command;

import duke.exception.TedException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

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
