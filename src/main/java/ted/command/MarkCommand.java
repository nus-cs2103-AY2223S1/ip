package ted.command;

import ted.exception.TedException;
import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

/**
 * Represents the bot's mark command. A <code>MarkCommand</code> object, when executed,
 * performs all the operations necessary when a task is marked.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates MarkCommand object pertaining to a specific task in the TaskList.
     *
     * @param taskIndex the task in the TaskList to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the necessary operations when a task is marked.
     *
     * @param tasks TaskList of bot.
     * @param ui Ui of bot.
     * @param st Storage of bot.
     * @throws TedException if error occurs while marking task or updating file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) throws TedException {
        ui.markResponse(tasks.markTask(taskIndex));
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
