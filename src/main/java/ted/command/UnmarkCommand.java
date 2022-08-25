package ted.command;

import ted.exception.TedException;
import ted.storage.Storage;
import ted.task.*;
import ted.ui.Ui;

/**
 * Represents the bot's unmark command. A <code>UnmarkCommand</code> object, when executed,
 * performs all the operations necessary when a task is unmarked.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates UnmarkCommand object pertaining to a specific task in the TaskList.
     *
     * @param taskIndex the task in the TaskList to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the necessary operations when a task is unmarked.
     *
     * @param tasks TaskList of bot.
     * @param ui Ui of bot.
     * @param st Storage of bot.
     * @throws TedException if error occurs while unmarking task or updating file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) throws TedException{
        ui.unmarkResponse(tasks.unmarkTask(taskIndex));
        st.updateFile(tasks);
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
