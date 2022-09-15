package ted.command;

import ted.exception.TedException;
import ted.storage.Storage;
import ted.task.TaskList;
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
     * @return bot response to unmark command.
     * @throws TedException if error occurs while unmarking task or updating file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage st) throws TedException {
        String temp = tasks.unmarkTask(taskIndex);
        st.updateFile(tasks);
        return ui.unmarkResponse(temp);
    }
}
