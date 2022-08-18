package duke;

import duke.exceptions.DukeException;

public class UnmarkStatusCommand extends Command {
    private int toggleTask;

    public UnmarkStatusCommand(int toggleTask) {
        this.toggleTask = toggleTask;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task success = taskList.unmarkStatus(this.toggleTask);
        ui.showToggleSuccess(success);
    }
}
