package duke;

/**
 * Encapsulate a command that allows user to mark existing Tasks as not done,
 * which is-a Command.
 */
public class UnmarkCommand extends Command {

    private int taskNumber;


    /**
     * Constructs an instance of UnmarkCommand which inherits from Command.
     *
     * @param taskNumber task number to mark as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = tasks.unmark(taskNumber);
        tasks.updateStorage(storage);
        return ui.showUnmarkedTask(unmarkedTask);
    }
}
