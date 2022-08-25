package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command {

    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markTask(idx);
        ui.showMarkTask(task);
    }

    @Override
    public String toString() {
        return "Mark command for index: " + this.idx;
    }
}
