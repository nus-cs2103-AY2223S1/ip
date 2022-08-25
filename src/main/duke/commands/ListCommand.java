package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list:";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showSuccessMessage(MESSAGE_SUCCESS);
        tasks.iterate();
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
