package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exceptions.DukeException;

public class UnmarkStatusCommand extends Command {
    private int toggleTask;

    public UnmarkStatusCommand(int toggleTask) {
        this.toggleTask = toggleTask;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task success = taskList.unmarkStatus(this.toggleTask);
        storage.save(taskList);
        ui.showToggleSuccess(success);
    }
}
