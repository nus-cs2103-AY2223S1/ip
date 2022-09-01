package ted.command;

import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

/**
 * Represents the bot's bye command. A <code>ByeCommand</code> object, when executed,
 * performs all the operations necessary when user exits the bot program.
 */
public class ByeCommand extends Command {
    /**
     * Executes the necessary operations when user exits the program.
     *
     * @param tasks TaskList of bot.
     * @param ui Ui of bot.
     * @param st Storage of bot.
     * @return response to bye command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage st) {
        return ui.showExit();
    }
}
