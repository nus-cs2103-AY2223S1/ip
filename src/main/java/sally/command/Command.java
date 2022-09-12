package sally.command;

import sally.exception.SallyException;
import sally.storage.Storage;
import sally.task.TaskList;
import sally.ui.Ui;

/**
 * Command abstract class to represent generic command.
 *
 * @author liviamil
 */

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SallyException;
    public abstract boolean isBye();
}
