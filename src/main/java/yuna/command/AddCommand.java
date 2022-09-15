package yuna.command;

import yuna.exception.YunaException;
import yuna.storage.Storage;
import yuna.task.Task;
import yuna.task.TaskList;
import yuna.ui.Ui;

/**
 * Command that adds the task to the list.
 *
 * @author Bryan Ng Zi Hao
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task The task to be added to the list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task as provided by the user.
     *
     * @param ui The interactions with user being used.
     * @param storage The storage which the data is being stored.
     * @param taskList The list of tasks to be updated in the storage.
     * @return String representation of Yuna's reply.
     * @throws YunaException There is an error in execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws YunaException {
        taskList.loadTask(task);
        String output = ui.formatMessage("I've added this to your schedule!\n" + task + "\n");
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
