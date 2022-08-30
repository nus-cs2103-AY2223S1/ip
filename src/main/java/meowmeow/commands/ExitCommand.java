package meowmeow.commands;
import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Class ExitCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used when the user enters the bye command.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class ExitCommand extends Command {

    /**
     * Method that executes the ExitCommand.
     * Prints a goodbye message to the user interface.
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
        System.out.println("UwU Byebyeeee! Come back soon... Meowmeow misses you already =^._.^= ");
    }

    /**
     * isExit method that returns true for ExitCommand.
     * @return true for ExitCommand.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
