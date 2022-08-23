/**
 * Class that represents a Command to exit the chat.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *
     * @param taskList List of tasks
     * @param ui The Ui
     * @param storage The Storage
     * @throws ZeusException If user input is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.exitMessage();
    }

    /**
     * Returns true to show user has exited.
     * @return boolean to show user has exited.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
