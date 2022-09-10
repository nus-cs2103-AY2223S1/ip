package mort.command;

import mort.exception.MortException;
import mort.storage.Storage;
import mort.task.Task;
import mort.task.TaskList;
import mort.ui.Ui;

/**
 * Represents a command to delete a task to the task list.
 */
public class DeleteCommand extends Command {
    /** Index of task to be deleted */
    private int taskNumber;

    /**
     * Constructor to initialize the delete command with the task number.
     * @param taskNumber
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MortException {
        try {
            Task task = tasks.deleteTask(this.taskNumber - 1);
            storage.save(tasks);
            return ui.getDeleteMessage(task, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new MortException(ui.getMissingTaskError(CommandWord.DELETE, this.taskNumber));
        }
    }

}
