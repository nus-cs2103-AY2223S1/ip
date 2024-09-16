package yuna.command;

import yuna.exception.YunaException;
import yuna.storage.Storage;
import yuna.task.Task;
import yuna.task.TaskList;
import yuna.ui.Ui;

/**
 * Unchecks the task to be not completed.
 *
 * @author Bryan Ng Zi Hao
 */
public class UnMarkCommand extends Command {
    private int index;

    /**
     * Constructor for UnMarkCommand.
     *
     * @param index The task of the said index to be unmarked.
     */
    public UnMarkCommand(int index) {
        this.index = index;
    }

    /**
     * Un-marks the task as undone.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Yuna's reply.
     * @throws YunaException There is an error in execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws YunaException {
        Task task = taskList.getTask(index);
        task.markAsNotDone();
        storage.writeFile(taskList.getTasks());
        String output = "Unmarked!! You should finish this soon! >:(\n";
        output += task;
        assert output.length() > 0 : "The output of execute should always return a message.";
        return output;
    }

    /**
     * Checks if this command will result in the bot to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
