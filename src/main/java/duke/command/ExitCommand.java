package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/** A class that represents a command to deactivate the bot. */
public class ExitCommand extends Command {

    /**
     * Does nothing, as there is no action for the bot to execute when deactivating.
     *
     * @param tasks The TaskList object that is keeping track of all the current tasks.
     * @param ui The UI object that displays messages to the user.
     * @param storage The storage used to save the task to file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
