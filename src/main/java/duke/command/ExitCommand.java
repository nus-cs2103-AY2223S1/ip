package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents an Exit Command
 */
public class ExitCommand extends Command {

    /**
     * Updates isExit in ui to be true
     * to exit Duke program
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exit();
    }
}
