package manmeowth.command;

import manmeowth.list.TaskList;
import manmeowth.storage.ListLoader;
import manmeowth.ui.Ui;

/**
 * Represents command to ManMeowth to exit the application.
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
