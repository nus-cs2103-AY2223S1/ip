package duke;

/**
 * Encapsulate a command that allows user to mark existing Tasks as done,
 * which is-a Command.
 */
public class MarkCommand extends Command {

    private int taskNumber;

    /**
     * Constructs an instance of MarkCommand which inherits from Command.
     *
     * @param taskNumber number of the task that needs to be marked.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task markedTask = tasks.mark(taskNumber);
        tasks.updateStorage(storage);
        return ui.showMarkedTask(markedTask);
    }
}
