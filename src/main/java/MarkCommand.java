import java.io.IOException;

/**
 * Command to mark Task as done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructor to populate the taskIndex field.
     * @param taskIndex Index of the Task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Run the given command as an MarkCommand.
     * @param taskList TaskList containing the list of tasks.
     * @param ui Ui dealing interaction with user.
     * @param storage Storage dealing with loading tasks from the save file and saving task in the save file.
     * @throws IOException If error is encountered saving onto the save file.
     * @throws DukeException If error is encountered when running the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task taskToMark = taskList.getTasks().get(this.taskIndex - 1);
        taskToMark.markAsDone();
        ui.printMarkDone(taskToMark);
        storage.update(taskList);
    }
}
