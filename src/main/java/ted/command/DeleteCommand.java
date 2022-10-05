package ted.command;

import ted.Storage;
import ted.exception.TedException;
import ted.task.Task;
import ted.task.TaskList;
import ted.ui.UiController;

/**
 * A class that encapsulate a DeadlineCommand, to
 * delete a task from given task list
 */
public class DeleteCommand extends Command {

    /**
     * Index of task to be deleted
     */
    private int index;

    /**
     * Construct a delete command
     * @param args
     * @throws TedException
     */
    public DeleteCommand(String args) throws TedException {
        super(args);
        try {
            this.index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new TedException("The number of task to be deleted passed must be a number.");
        }
    }

    /**
     * Delete task from task list and save the current
     * task list to storage.
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    @Override
    public void run(TaskList tasks, UiController ui, Storage storage) throws TedException {
        if (tasks.size() == 0) {
            throw new TedException("There is no tasks here. Feel free to add a task.");
        }

        if (index <= 0) {
            throw new TedException("The number of task to be deleted must be greater than 0.");
        }

        if (index > tasks.size()) {
            throw new TedException(String.format(
                    "The number of task to be deleted must be less than or equal to %d.",
                    tasks.size()
            ));
        }

        Task deletedTask = tasks.get(index - 1);
        tasks.delete(index - 1);
        ui.showDeletedTaskSuccess(tasks, deletedTask);

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
