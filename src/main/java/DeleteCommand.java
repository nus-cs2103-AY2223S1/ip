/**
 * Represents a Delete Command, to delete a task from tasks.
 *
 * @author Elgin
 */
public class DeleteCommand extends Command {
    /** The arguments of the command (e.g. 'sleep /at 2020-12-12'). */
    private final String arguments;

    /**
     * Constructor of DeleteCommand.
     *
     * @param arguments The arguments of the command.
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the delete command and remove a task from tasks.
     *
     * @param tasks All tasks present in Duke.
     * @param ui The UI controller that handles interaction between user and Duke.
     * @param storage Storage that stores all tasks on Disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String deletedTaskDesc = tasks.deleteItem(this.arguments);

        ui.showTaskDeleted(tasks.getTaskLen(), deletedTaskDesc);
    }
}
