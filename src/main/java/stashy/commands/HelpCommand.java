package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

/**
 * A Command specifically designed to show all the
 * commands available along with their prefixes.
 */
public class HelpCommand extends Command {
    public static final String KEYWORD = "help";

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Shows all the available commands.
     *
     * @param tasks The list of tasks
     * @param ui The UI of this application
     * @param storage The storage used for this application
     * @return The stringtified UI output
     * @throws StashyException If any exception is caught
     */
    @Override
    public String executeString(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        return ui.showCommandList();
    }
}
