package mort.command;

import mort.storage.Storage;
import mort.task.TaskList;
import mort.ui.Ui;

/**
 * Represents a command to exit the Duke program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getExitMessage();
    }
}
