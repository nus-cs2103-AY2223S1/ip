package meowmeow.commands;

import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Class HiCommand is a concrete class that implements the Command interface.</p>
 * <p>This class is used when the user enters the "hi" command.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class HiCommand extends Command {

    /**
     * Executes the HiCommand.
     * Prints out a greeting to the user interface.
     *
     * @param tasks the task list to be operated on by the command.
     *              The task list is used to add the task to the task list.
     * @param ui the user interface to be used by the command.
     *           The user interface is used to display the result of the command.
     * @param storage the storage to be used by the command.
     *                The storage is used to save and load the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showWelcome();
    }

    /**
     * Returns false for HiCommand.
     * @return false for HiCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
