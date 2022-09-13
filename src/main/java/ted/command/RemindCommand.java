package ted.command;

import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

/**
 * Represents the bot's remind command. A <code>RemindCommand</code> object, when executed,
 * performs all the operations necessary to remind user of upcoming deadlines.
 */
public class RemindCommand extends Command {
    /**
     * Executes the necessary operations when remind is called by user.
     *
     * @param tasks TaskList of bot.
     * @param ui Ui of bot.
     * @param st Storage of bot.
     * @return bot response to remind command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage st) {
        return ui.remindResponse(tasks.getRemindTasks());
    }
}
