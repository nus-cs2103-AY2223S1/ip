package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {
    private Task task;

    public UnmarkCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        task.markAsNotDone();
        ui.UnmarkTask(task);
        storage.update(tasks.getTasks());
    }
}
