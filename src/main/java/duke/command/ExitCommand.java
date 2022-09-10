package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * An extension of the Command class, ExitCommand, used to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Returns true to tell that this command makes Duke exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Shows the exit message.
     *
     * @param taskList tasklist that contains tasks to be modified, added, or removed
     * @param ui ui that displays results of user commands
     * @param storage storage that saves or loads the taskList
     * @return string that contains ui message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
