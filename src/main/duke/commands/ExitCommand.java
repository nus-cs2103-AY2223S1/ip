package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_SUCCESS = "See you soon!";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSuccessMessage(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
