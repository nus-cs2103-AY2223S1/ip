/**
 * Class that represents a command to be executed.
 */
public class Command {

    /**
     * Executes the command.
     *
     * @param taskList List of tasks
     * @param ui The Ui
     * @param storage The Storage
     * @throws ZeusException If user input is invalid
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ZeusException {
    }

    /**
     * Returns false to indicate user has not exited.
     *
     * @return Boolean that shows user has not exited
     */
    public boolean isExit() {
        return false;
    }
}
