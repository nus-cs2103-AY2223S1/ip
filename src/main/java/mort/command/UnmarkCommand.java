package mort.command;

import mort.exception.MortException;
import mort.storage.Storage;
import mort.task.TaskList;
import mort.ui.Ui;

/**
 * Represents a command to mark a task in the task list as incomplete.
 */
public class UnmarkCommand extends Command {
    /** Index of the task to be marked as incomplete */
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MortException {
        try {
            String unmarkMessage = tasks.unmarkTask(this.taskNumber - 1);
            storage.save(tasks);
            return unmarkMessage;
        } catch (IndexOutOfBoundsException e) {
            throw new MortException(ui.getMissingTaskError(CommandWord.UNMARK, this.taskNumber));
        }
    }
}
