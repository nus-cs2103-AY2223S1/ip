package duke.command;

import duke.list.TaskList;
import duke.storage.ListLoader;
import duke.ui.Ui;

/**
 * Represents command to Duke to print list.
 *
 * @author WR3nd3
 */
public class HelpCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, ListLoader storage) {
        return ui.showHelp();
    }
}
