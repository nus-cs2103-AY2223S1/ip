package duke;

/**
 * Represents a command used in the Duke program.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage
     * @return the message to be printed
     * @throws DukeException if there is an error executing the command
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
