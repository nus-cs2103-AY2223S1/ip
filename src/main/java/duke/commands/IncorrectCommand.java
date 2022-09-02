package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class IncorrectCommand extends Command {

    private String message;

    public IncorrectCommand(String message) {
        super();
        this.message = message;
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showErrorMessage(message);
    }
}
