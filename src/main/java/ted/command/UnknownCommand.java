package ted.command;

import ted.Storage;
import ted.exception.TedException;
import ted.task.TaskList;
import ted.ui.UiController;

/**
 * UnknownCommand does nothing but telling user we don't
 * provide such functionality based on their command.
 */
public class UnknownCommand extends Command {

    public UnknownCommand(String args) {
        super(args);
    }

    @Override
    public void run(TaskList tasks, UiController ui, Storage storage) throws TedException {
        ui.showUnknownCommandError();
    }
}
