package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnMarkCommand extends Command {
    private final int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task task = taskList.unMarkItem(index);
        ui.showOutput("OK, I've marked this task as not done yet:\n  " + task + "\n");
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
