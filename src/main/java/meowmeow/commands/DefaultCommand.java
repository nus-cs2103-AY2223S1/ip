package meowmeow.commands;
import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Class DefaultCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used when the user enters an invalid command.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class DefaultCommand extends Command {

    /**
     * Method that executes the DefaultCommand.
     *
     * @param tasks the task list to be operated on by the command.
     *              The task list is used to add the task to the task list.
     * @param ui the user interface to be used by the command.
     *           The user interface is used to display the result of the command.
     * @param storage the storage to be used by the command.
     *                The storage is used to save and load the task list.
     *
     *     Prints an error message to the user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Try typing something else!");
    }

    /**
     * isExit method that returns false for DefaultCommand.
     * @return false for DefaultCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
