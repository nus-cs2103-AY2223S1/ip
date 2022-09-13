package duke;

/**
 * Encapsulate a command that allows user to update existing Tasks' date,
 * which is-a Command.
 */
public class UpdateDateCommand extends Command {

    private int taskNumber;

    private String updatedDate;

    /**
     *  Constructs an instance of UpdateCommand which inherits from Command.
     *
     * @param taskNumber task number to update the date
     */
    public UpdateDateCommand(int taskNumber, String updatedDate) {
        this.taskNumber = taskNumber;
        this.updatedDate = updatedDate;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task updatedTask = tasks.updateDate(taskNumber, updatedDate);
        tasks.updateStorage(storage);
        return ui.showUpdatedTask(updatedTask);
    }
}
