package commands;

import ui.Ui;
import storage.Storage;
import tasks.TaskList;

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
