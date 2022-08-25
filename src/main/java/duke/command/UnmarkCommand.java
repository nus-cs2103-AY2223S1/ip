package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UnmarkCommand extends Command {

    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.unmarkTask(idx);
        ui.showUnmarkTask(task);
    }

    @Override
    public String toString() {
        return "Unmark command for index: " + this.idx;
    }
}
