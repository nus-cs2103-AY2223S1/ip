package yuna.command;

import yuna.exception.YunaException;
import yuna.storage.Storage;
import yuna.task.Task;
import yuna.task.TaskList;
import yuna.ui.Ui;

/**
 * Command that deletes the task.
 *
 * @author Bryan Ng Zi Hao
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task as determined by the index number.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Yuna's reply.
     * @throws YunaException There is an error in execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws YunaException {
        Task task = taskList.getTask(this.index);
        taskList.remove(this.index - 1);
        storage.writeFile(taskList.getTasks());
        int numTasks = taskList.size();
        String output = ui.formatMessage("I've taken this out from your schedule!\n");
        output += ui.formatMessage(task.toString()) + "\n";
        if (taskList.size() == 1) {
            return output + ui.formatMessage(String.format("You got %d task to do :)", taskList.size()));
        } else {
            return output + ui.formatMessage(String.format("You got %d tasks to do :)", taskList.size()));
        }
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
