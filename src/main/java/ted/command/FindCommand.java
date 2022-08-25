package ted.command;

import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

/**
 * Represents the bot's find command. A <code>FindCommand</code> object, when executed,
 * performs all the operations necessary when tasks from TaskList need to be found.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates FindCommand object pertaining to a specific keyword to be searched for
     * in the TaskList.
     *
     * @param keyword the keyword to be searched for in TaskList.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the necessary operations when find is called by user.
     *
     * @param tasks TaskList of bot.
     * @param ui Ui of bot.
     * @param st Storage of bot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.tedResponse(tasks.findTasks(keyword));
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
