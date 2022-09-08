package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Representation for exit command.
 */
public class ExitCommand extends Command {
    /**
     * Checks if this is an exit command.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        storage.writeFile(taskList);
        ui.exit();
    }
    @Override
    public String getResponse(TaskList taskList, UI ui, Storage storage) {
        storage.writeFile(taskList);
        return ui.getExitMessage();
    }
}
