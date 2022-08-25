package duke;

import duke.exceptions.DukeException;

public class MarkStatusCommand extends Command {
    private int toggleTask;

    public MarkStatusCommand(int toggleTask) {
        this.toggleTask = toggleTask;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task success = taskList.markStatus(this.toggleTask);
        ui.showToggleSuccess(success);
    }
}

