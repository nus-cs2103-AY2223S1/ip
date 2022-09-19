package manmeowth.command;

import manmeowth.list.TaskList;
import manmeowth.storage.ListLoader;
import manmeowth.ui.Ui;

/**
 * Represents command to ManMeowth to print list.
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
