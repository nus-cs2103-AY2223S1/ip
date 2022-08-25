package ted.command;

import ted.storage.Storage;
import ted.task.*;
import ted.ui.Ui;

/**
 * Represents the bot's add command. A <code>AddCommand</code> object, when executed,
 * performs all the operations necessary when a task is added.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Creates AddCommand object to add a specific task into the TaskList.
     *
     * @param task task to be added into bot TaskList.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the necessary operations when a task is added.
     *
     * @param tasks TaskList of bot.
     * @param ui Ui of bot.
     * @param st Storage of bot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        String temp = tasks.addTask(task);
        ui.addResponse(temp, tasks.getSize());
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
