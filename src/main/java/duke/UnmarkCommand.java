package duke;

/**
 * Encapsulate a command that allows user mark existing Tasks as not done,
 * which is-a Command.
 */
public class UnmarkCommand extends Command {

    private int taskNumber;


    /**
     * Class constructor for UnmarkCommand.
     *
     * @param taskNumber task number to mark as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Mark specified task as not done and update datafile.
     *
     * @param tasks list of existing tasks.
     * @param ui user interface to be shown.
     * @param storage to rewrite the data file.
     * @throws DukeException if something went wrong with the update of data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = tasks.unmark(taskNumber);
        tasks.updateStorage(storage);
        return ui.showUnmarkedTask(unmarkedTask);
    }
}
