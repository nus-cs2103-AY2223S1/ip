package duke.command;

import duke.TaskList;
import duke.models.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        Task t = tasks.getTask(index);
        t.markAsNotDone();
        ui.showTaskUnmarkMessage(t);
    }
}
