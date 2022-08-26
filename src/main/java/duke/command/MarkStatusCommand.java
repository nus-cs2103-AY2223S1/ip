package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exceptions.DukeException;

public class MarkStatusCommand extends Command {
    private int toggleTask;

    public MarkStatusCommand(int toggleTask) {
        this.toggleTask = toggleTask;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        Task success = taskList.markStatus(this.toggleTask);
        storage.save(taskList);
        ui.showToggleSuccess(success);
    }
}

