package duke.command;

import duke.list.TaskList;
import duke.storage.ListLoader;
import duke.ui.Ui;

/**
 * Represents command to Duke to exit the application.
 *
 * @author WR3nd3
 */
public class ExitCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, ListLoader storage) {

        return ui.showGoodbye();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
