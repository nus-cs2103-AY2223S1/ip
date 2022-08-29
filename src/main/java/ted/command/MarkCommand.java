package ted.command;

import ted.Storage;
import ted.Ui;
import ted.exception.TedException;
import ted.task.TaskList;

/**
 * A class that encapsulate a DeadlineCommand, to
 * mark a task in tasks list
 */
public class MarkCommand extends Command {

    /**
     * The index of task to be marked
     */
    private int index;

    /**
     * Construct a mark command
     * @param args
     * @throws TedException
     */
    public MarkCommand(String args) throws TedException {
        super(args);
        try {
            this.index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new TedException("The number of task to be marked passed must be a number.");
        }
    }

    /**
     * Mark the task that specified by user.
     * @param tasks
     * @param ui
     * @param storage
     * @throws TedException
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) throws TedException {
        try {
            if (tasks.size() == 0) {
                throw new TedException("There is no tasks here. Feel free to add a task.");
            }

            if (index <= 0) {
                throw new TedException("The number of task to be marked must be greater than 0.");
            }

            if (index > tasks.size()) {
                throw new TedException(String.format(
                        "Error: The number of task to be marked must be less than or equal to %d.", tasks.size()));
            }

            tasks.get(index - 1).markAsDone();
        } catch (NumberFormatException e) {
            throw new TedException("The number of task to be marked passed must be a number.");
        }

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
