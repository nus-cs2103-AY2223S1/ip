package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getNumTasks()) {
            throw new DukeException("Invalid task number.");
        }
        Task task = tasks.getTask(index);
        task.markAsNotDone();
        storage.update(tasks);
        ui.showUnmarkedMessage(task);
    };

    @Override
    public boolean isExit() {
        return false;
    };

}
