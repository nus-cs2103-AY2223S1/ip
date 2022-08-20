package duke;

/**
 * Encapsulate a command that allows user to mark existing Tasks as done,
 * which is-a Command.
 *
 * @author: Jonas Png
 */
public class MarkCommand extends Command {

    private int taskNumber;


    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Mark specified task as done and update data file.
     *
     * @param tasks list of existing tasks
     * @param ui user interface to be shown
     * @param storage to rewrite the data file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task markedTask = tasks.mark(taskNumber);
        ui.showMarkedTask(markedTask);
        tasks.updateStorage(storage);
    }
}
