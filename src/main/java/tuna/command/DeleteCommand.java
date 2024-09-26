package tuna.command;

import tuna.TunaException;
import tuna.task.Task;
import tuna.utility.Storage;
import tuna.utility.TaskList;
import tuna.utility.Ui;

/**
 * Represents a Delete Task Command. A DeleteCommand object contains the index of the task to be deleted.
 */
public class DeleteCommand extends Command {

    /** Index of the task to be deleted */
    private int index;

    /**
     * Creates a DeleteCommand object.
     *
     * @param index index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super(CommandType.DELETE);
        this.index = index - 1;
    }

    /**
     * Executes the Delete Task command, deleting the task at the specified index.
     *
     * @param tasks TaskList object.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TunaException {
        if (index < 0 || index >= tasks.getTotalTasks()) {
            throw new TunaException("Oops! Seems like the index you entered is out of range");
        }
        assert index >= 0;
        assert index < tasks.getTotalTasks();
        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        return ui.deletedTaskMessage(deletedTask, tasks.getTotalTasks());
    }
}
