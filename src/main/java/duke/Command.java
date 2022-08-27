package duke;

/**
 * Represents the commands that Duke can accept to do various tasks
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks Tasklist object that Duke uses to store tasks
     * @param ui Ui object that Duke uses to display information to user
     * @param storage Storage object that Duke uses to handle file IO operations
     * @return a string with the message to display to the user
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Checks if command is an ExitCommand.
     *
     * @return True if Command is an ExitCommand, false otherwise
     */
    public abstract boolean isExit();

}
