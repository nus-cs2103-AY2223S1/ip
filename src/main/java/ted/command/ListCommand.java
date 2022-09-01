package ted.command;

import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

/**
 * Represents the bot's list command. A <code>ListCommand</code> object, when executed,
 * performs all the operations necessary when TaskList is to be listed.
 */
public class ListCommand extends Command {
    /**
     * Executes the necessary operations when list is called by user.
     *
     * @param tasks TaskList of bot.
     * @param ui Ui of bot.
     * @param st Storage of bot.
     * @return bot response to list command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage st) {
        return ui.listResponse(tasks.list());
    }
}
