package ted.command;

import ted.exception.TedException;
import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

/**
 * Represents the bot's delete command. A <code>DeleteCommand</code> object, when executed,
 * performs all the operations necessary when a task is deleted.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates DeleteCommand object pertaining to a specific task in the TaskList.
     *
     * @param taskIndex the task in the TaskList to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the necessary operations when a task is deleted.
     *
     * @param tasks TaskList of bot.
     * @param ui Ui of bot.
     * @param st Storage of bot.
     * @return bot response to delete command.
     * @throws TedException if error occurs while deleting task or updating file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage st) throws TedException {
        String temp = tasks.deleteTask(taskIndex);
        st.updateFile(tasks);
        return ui.deleteResponse(temp, tasks.getSize());
    }
}
