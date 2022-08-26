package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkDoneCommand extends Command {
    private int index;

    public UnmarkDoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkDone(index);
        ui.showUnmarkDone(tasks.getTask(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

