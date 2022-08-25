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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.tedResponse(tasks.list());
    }

    /**
     * Returns boolean indicating whether to exit program.
     *
     * @return boolean indicating exit status of program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
