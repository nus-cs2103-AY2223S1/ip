package ted.command;

import ted.Storage;
import ted.Ui;
import ted.exception.TedException;
import ted.task.TaskList;

/**
 * A class that encapsulate a DeadlineCommand, to
 * unmark a task in given tasks list
 */
public class UnmarkCommand extends Command {

    /**
     * Index of task to be unmarked
     */
    private int index;

    /**
     * Construct a unmark command
     * @param args
     * @throws TedException
     */
    public UnmarkCommand(String args) throws TedException {
        super(args);
        try {
            this.index = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new TedException("The number of task to be unmarked passed must be a number.");
        }
    }

    /**
     * Unmark the task in tasks and save the whole tasks list to
     * storage.
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
                throw new TedException("The number of task to be unmarked must be greater than 0.");
            }

            if (index > tasks.size()) {
                throw new TedException(
                        String.format(
                                "The number of task to be unmarked must be less than or equal to %d.",
                                tasks.size()
                        )
                );
            }

            tasks.get(index - 1).unmark();
        } catch (NumberFormatException e) {
            throw new TedException("The number of task to be unmarked passed must be a number.");
        }

        try {
            storage.saveTasks(tasks);
        } catch (Exception e) {
            ui.showTaskSavingError(e);
        }
    }
}
