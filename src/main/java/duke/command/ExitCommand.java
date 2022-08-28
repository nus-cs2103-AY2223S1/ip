package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to exit the Duke app.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }
}
