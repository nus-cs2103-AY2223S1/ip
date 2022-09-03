package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command to exit the Duke client.
 */
public class ExitCommand extends Command {
    /**
     * Returns the goodbye string from Message.
     *
     * @param tasks TaskList object to update.
     * @param storage Storage object to manage save file.
     * @return Goodbye string.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.getTerminationString();
    }
}
