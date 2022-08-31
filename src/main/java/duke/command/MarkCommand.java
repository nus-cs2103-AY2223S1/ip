package duke.command;

import duke.exception.FileIoException;
import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A command class that encapsulates the action of mark or unmark a specific task in the task list.
 */
public class MarkCommand extends Command {

    private final boolean toMark;
    private final int indexOfTaskToMark;

    /**
     * Constructs a MarkCommand instance.
     *
     * @param toMark              Indicates whether to mark or unmark the specific task.
     * @param indexOfTaskToDelete Indicates the index of the task that needs to be marked or unmarked.
     */
    public MarkCommand(boolean toMark, int indexOfTaskToDelete) {
        this.toMark = toMark;
        this.indexOfTaskToMark = indexOfTaskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws InvalidIndexException, FileIoException {
        if (indexOfTaskToMark <= 0 || indexOfTaskToMark > tasks.getNumOfRemainingTasks()) {
            throw new InvalidIndexException();
        }

        Task taskToMark;
        if (toMark) {
            taskToMark = tasks.markTask(indexOfTaskToMark);
        } else {
            taskToMark = tasks.unmarkTask(indexOfTaskToMark);
        }
        storage.save(tasks);
        ui.showTask(taskToMark);
    }

}
