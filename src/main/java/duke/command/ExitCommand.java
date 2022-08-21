package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

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
