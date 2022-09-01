package ted.command;

import ted.exception.TedException;
import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

/**
 * Represents commands that users can input to the bot.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage st) throws TedException;
}
