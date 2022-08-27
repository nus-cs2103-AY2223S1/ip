package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * <p>Class ListCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used when the user enters the "list" command.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class ListCommand extends Command {

    /**
     * Method that executes the ListCommand.
     * Prints out the tasks in the task list to the user interface.
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
        tasks.printTaskList();
    }

    /**
     * isExit method that returns false for ListCommand.
     * @return false for ListCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
