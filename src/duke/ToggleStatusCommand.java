package duke;

import duke.exceptions.CannotFindTaskException;

public class ToggleStatusCommand extends Command {
    private Task toggleTask;

    public ToggleStatusCommand(String toggleTask) {
        this.toggleTask = new ToDo(toggleTask);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws CannotFindTaskException {
        Task success = taskList.toggleTaskStatus(this.toggleTask);
        ui.showToggleSuccess(success);
    }
}

