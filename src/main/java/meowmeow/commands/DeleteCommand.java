package meowmeow.commands;
import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Class DeleteCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used to delete a task from the task list.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class DeleteCommand extends Command {

    private int taskNum;

    /**
     * Constructor for DeleteCommand.
     * @param taskNum the number of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Method that executes the DeleteCommand.
     *
     * @param tasks the task list to be operated on by the command.
     *              The task list is used to delete the task from the task list.
     * @param ui the user interface to be used by the command.
     *           The user interface is used to display the result of the command.
     * @param storage the storage to be used by the command.
     *                The storage is used to save and load the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskNum - 1);
    }

    /**
     * isExit method that returns false for DeleteCommand.
     * @return false for DeleteCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
