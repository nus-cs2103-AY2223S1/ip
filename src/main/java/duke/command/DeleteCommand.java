package duke.command;

import duke.exception.TedException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

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
