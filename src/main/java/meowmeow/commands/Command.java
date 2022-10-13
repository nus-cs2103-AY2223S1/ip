package meowmeow.commands;
import meowmeow.Storage;
import meowmeow.TaskList;
import meowmeow.Ui;

/**
 * <p>Abstract class Command</p>
 * <p>This class is the superclass of all commands.</p>
 * <p>This class is abstract because it has no implementation.</p>
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks the task list to be operated on by the command.
     * @param ui the user interface to be used by the command.
     * @param storage the storage to be used by the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if the command is an exit command.
     * @return boolean isExit.
     *
     */
    public abstract boolean isExit();

}
