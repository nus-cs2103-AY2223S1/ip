package ted.command;

import ted.storage.Storage;
import ted.task.*;
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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.showExit();
    }

    /**
     * Returns boolean indicating whether to exit program.
     *
     * @return boolean indicating exit status of program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
